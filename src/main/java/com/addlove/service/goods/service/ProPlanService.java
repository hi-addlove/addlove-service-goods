package com.addlove.service.goods.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.addlove.service.goods.constants.GoodsResponseCode;
import com.addlove.service.goods.constants.GoodsCommonConstants.ProcedureResult;
import com.addlove.service.goods.dao.ProPlanDao;
import com.addlove.service.goods.exception.ServiceException;
import com.addlove.service.goods.model.ProPlanDoneModel;
import com.addlove.service.goods.model.ProPlanHeadModel;
import com.addlove.service.goods.model.ProPlanQueryPageModel;
import com.addlove.service.goods.util.LoggerEnhance;
import com.github.pagehelper.PageHelper;

/**
 * 生产计划逻辑层
 * @author lw
 *
 */
@Service
public class ProPlanService {
    /**ProPlanService类日志 */
    private static final Logger LOGGER = LoggerFactory.getLogger(ProPlanService.class);
    
    @Autowired
    private ProPlanDao proPlanDao;
    
    /**
     * 生产计划列表
     * @param queryModel
     * @return List<ProPlanHeadModel>
     */
    public List<ProPlanHeadModel> queryProPlanPage(ProPlanQueryPageModel queryModel) {
        PageHelper.startPage(queryModel.getPageNo(), queryModel.getPageSize(), true);
        List<ProPlanHeadModel> planList = this.proPlanDao.queryProPlanPage(queryModel);
        if (null != planList && !planList.isEmpty()) {
            for (ProPlanHeadModel model : planList) {
                if (StringUtils.isNotBlank(model.getLrDate()) && model.getLrDate().length() > 19) {
                    model.setLrDate(model.getLrDate().substring(0, 19));
                }
                if (StringUtils.isNotBlank(model.getJzDate()) && model.getJzDate().length() > 19) {
                    model.setJzDate(model.getJzDate().substring(0, 19));
                }
            }
        }
        return planList;
    }
    
    /**
     * 生产完工列表
     * @param queryModel
     * @return List<ProPlanDoneModel>
     */
    public List<ProPlanDoneModel> queryProPlanDonePage(ProPlanQueryPageModel queryModel) {
        List<ProPlanDoneModel> doneList = this.proPlanDao.queryProPlanDonePage(queryModel);
        if (null != doneList && !doneList.isEmpty()) {
            for (ProPlanDoneModel model : doneList) {
                if (StringUtils.isNotBlank(model.getProduceTime()) && model.getProduceTime().length() > 19) {
                    model.setProduceTime(model.getProduceTime().substring(0, 19));
                }
            }
        }
        return doneList;
    }
    
    /**
     * 插入生产计划信息
     * @param headModel
     */
    @Transactional
    public void insertProPlanInfo(ProPlanHeadModel headModel) {
        this.proPlanDao.insertProPlanHead(headModel);
        this.proPlanDao.insertProPlanBody(headModel.getBodyList());
    }
    
    /**
     * 编辑生产计划信息
     * @param headModel
     */
    @Transactional
    public void updateAllProPlanInfo(ProPlanHeadModel headModel) {
        this.proPlanDao.deleteProPlanHead(headModel.getBillNo());
        this.proPlanDao.deleteProPlanBody(headModel.getBillNo());
        this.proPlanDao.insertProPlanHead(headModel);
        this.proPlanDao.insertProPlanBody(headModel.getBodyList());
    }
    
    /**
     * 删除生产计划信息
     * @param billNo
     */
    @Transactional
    public void deleteProPlanInfo(String billNo) {
        this.proPlanDao.deleteProPlanHead(billNo);
        this.proPlanDao.deleteProPlanBody(billNo);
    }
    
    /**
     * 批量更新完工数量
     * @param doneModels
     */
    @Transactional
    public void updateProPlanDone(List<ProPlanDoneModel> doneModels) {
        this.proPlanDao.updateProPlanDone(doneModels);
    }
    
    /**
     * 查询生产计划详情
     * @param billNo
     * @return List<Map<String, Object>>
     */
    public List<Map<String, Object>> queryProPlanDetail(String billNo) {
        return this.proPlanDao.queryProPlanDetail(billNo);
    }
    
    /**
     * 执行生产计划记账存储过程
     * @param map
     * @return Map<String, Object>
     */
    @Transactional
    public Map<String, Object> execProPlanAccountProcedure(Map<String, Object> map) {
        long startTime = System.currentTimeMillis();
        this.proPlanDao.execProPlanAccountProcedure(map);
        long endTime = System.currentTimeMillis();
        LoggerEnhance.info(LOGGER, "调用生产计划记账存储过程-【CALL sFrs_ProducePlan_Account_CDADL()】消耗时间:{}", (endTime - startTime));
        if (null == map) {
            throw new ServiceException(GoodsResponseCode.EXEC_PROCEDURE_ERROR.getCode(), 
                    GoodsResponseCode.EXEC_PROCEDURE_ERROR.getMsg());
        }
        LoggerEnhance.info(LOGGER, "生产计划记账结果为--------------------：{}", null != map.get("ps_Message") ? map.get("ps_Message").toString() : "");
        int resultCode = null != map.get("pi_Result") ? Integer.valueOf(map.get("pi_Result").toString()) : -1;
        if (ProcedureResult.EXEC_ERROR_RECORD.getValue() == resultCode 
                || ProcedureResult.EXEC_ERROR_EXIT.getValue() == resultCode) {
            throw new ServiceException(GoodsResponseCode.EXEC_PROCEDURE_ERROR.getCode(), 
                    null != map.get("ps_Message") ? map.get("ps_Message").toString() : GoodsResponseCode.EXEC_PROCEDURE_ERROR.getMsg());
        }
        return map;
    }
    
    /**
     * 执行生产完工存储过程
     * @param doneMaps
     */
    @Transactional
    public void execProPlanDoneProcedure(List<ProPlanDoneModel> doneModels) {
        this.proPlanDao.updateProPlanDone(doneModels);
        long startTime = System.currentTimeMillis();
        for (ProPlanDoneModel model : doneModels) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("ps_BillNo", model.getBillNo());
            map.put("pi_SerialNo", model.getSerialNo());
            this.proPlanDao.execProPlanDoneProcedure(map);
            if (map.isEmpty()) {
                throw new ServiceException(GoodsResponseCode.EXEC_DONE_PROCEDURE_ERROR.getCode(), 
                        GoodsResponseCode.EXEC_DONE_PROCEDURE_ERROR.getMsg());
            }
            LoggerEnhance.info(LOGGER, "调用生产完工存储过程结果为--------------------：{}", null != map.get("ps_Message") ? map.get("ps_Message").toString() : ""
                , "单据号及序列号为--------------------：{}", (map.get("ps_BillNo") + "_" + map.get("pi_SerialNo")));
            int resultCode = null != map.get("pi_Result") ? Integer.valueOf(map.get("pi_Result").toString()) : -1;
            if (ProcedureResult.EXEC_ERROR_RECORD.getValue() == resultCode 
                    || ProcedureResult.EXEC_ERROR_EXIT.getValue() == resultCode) {
                throw new ServiceException(GoodsResponseCode.EXEC_DONE_PROCEDURE_ERROR.getCode(), 
                        null != map.get("ps_Message") ? map.get("ps_Message").toString() : GoodsResponseCode.EXEC_DONE_PROCEDURE_ERROR.getMsg());
            }
        }
        long endTime = System.currentTimeMillis();
        LoggerEnhance.info(LOGGER, "调用生产完工存储过程-【CALL sFrs_ProducePlan_Done_CDADL()】消耗时间:{}", (endTime - startTime));
    }
}
