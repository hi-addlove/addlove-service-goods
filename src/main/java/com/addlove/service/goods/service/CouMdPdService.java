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
import com.addlove.service.goods.dao.CouMdPdDao;
import com.addlove.service.goods.dao.SkuPdCSDao;
import com.addlove.service.goods.exception.ServiceException;
import com.addlove.service.goods.model.CouMdPdHeadModel;
import com.addlove.service.goods.model.MdPdAccountModel;
import com.addlove.service.goods.model.MdPdPageModel;
import com.addlove.service.goods.model.SkuPluModel;
import com.addlove.service.goods.util.LoggerEnhance;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;

/**
 * 门店盘点逻辑层
 * @author lw
 *
 */
@Service
public class CouMdPdService {
    /**CouMdPdService类日志 */
    private static final Logger LOGGER = LoggerFactory.getLogger(CouMdPdService.class);
    
    @Autowired
    private CouMdPdDao couMdPdDao;
    
    @Autowired
    private SkuPdCSDao skuPdCSDao;
    
    /**
     * 查询盘点列表
     * @param queryModel
     * @return List<CouMdPdHeadModel>
     */
    public List<CouMdPdHeadModel> queryMdPdPage(MdPdPageModel queryModel) {
        PageHelper.startPage(queryModel.getPageNo(), queryModel.getPageSize(), true);
        List<CouMdPdHeadModel> pdList = this.couMdPdDao.queryMdPdPage(queryModel);
        if (null != pdList && !pdList.isEmpty()) {
            for (CouMdPdHeadModel model : pdList) {
                if (StringUtils.isNotBlank(model.getLrDate()) && model.getLrDate().length() > 19) {
                    model.setLrDate(model.getLrDate().substring(0, 19));
                }
                if (StringUtils.isNotBlank(model.getJzDate()) && model.getJzDate().length() > 19) {
                    model.setJzDate(model.getJzDate().substring(0, 19));
                }
                if (StringUtils.isNotBlank(model.getPdStartDate()) && model.getPdStartDate().length() > 19) {
                    model.setPdStartDate(model.getPdStartDate().substring(0, 19));
                }
            }
        }
        return pdList;
    }
    
    /**
     * 新增盘点信息
     * @param headModel
     */
    @Transactional
    public void addPdInfo(CouMdPdHeadModel headModel) {
        this.couMdPdDao.insertPdHead(headModel);
        this.couMdPdDao.insertPdBody(headModel.getBodyList());
    }
    
    /**
     * 编辑盘点信息
     * @param headModel
     */
    @Transactional
    public void editPdInfo(CouMdPdHeadModel headModel) {
        this.couMdPdDao.deleteMdPdHead(headModel.getBillNo());
        this.couMdPdDao.deleteMdPdBody(headModel.getBillNo());
        this.couMdPdDao.insertPdHead(headModel);
        this.couMdPdDao.insertPdBody(headModel.getBodyList());
    }
    
    /**
     * 查询盘点详情
     * @param billNo
     * @return List<Map<String, Object>>
     */
    public List<Map<String, Object>> queryMdPdDetails(String billNo) {
        return this.couMdPdDao.queryMdPdDetails(billNo);
    }
    
    /**
     * 获取盘点商品
     * @param orgCode
     * @param depId
     * @param cycleType
     * @return List<SkuPluModel>
     */
    public List<SkuPluModel> getPdSkuList(String orgCode, Long depId, String cycleType) {
        List<SkuPluModel> deptSkus = this.skuPdCSDao.getPdSkuList(orgCode, depId, cycleType);
        return deptSkus;
    }
    
    /**
     * 删除盘点信息
     * @param billNo
     */
    @Transactional
    public void deleteMdPd(String billNo) {
        this.couMdPdDao.deleteMdPdHead(billNo);
        this.couMdPdDao.deleteMdPdBody(billNo);
    }
    
    /**
     * 获取盘点账面商品数据
     * @param billNo
     * @param orgCode
     * @return List<MdPdAccountModel>
     */
    public List<MdPdAccountModel> getMdPdAccountPlus(String billNo, String orgCode) {
        return this.couMdPdDao.getMdPdAccountPlus(billNo, orgCode);
    }
    
    /**
     * 获取漏盘商品处理方式:0-实际数量按账面数量处理;1-实际数量按0处理
     * @param orgCode
     * @return String
     */
    public String getPdType(String orgCode) {
        final String pdType = "0";
        Map<String, Object> map = this.couMdPdDao.getPdType(orgCode);
        if (null == map || map.isEmpty() || null == map.get("pdType")) {
            return pdType;
        }
        return map.get("pdType").toString();
    }
    
    /**
     * 通过单据号获取盘点主表信息
     * @param billNo
     * @return CouMdPdHeadModel
     */
    public CouMdPdHeadModel getPdHeadByBillNo(String billNo) {
        return this.couMdPdDao.getPdHeadByBillNo(billNo);
    }
    
    /**
     * 新增启动盘点
     * @param headModel
     */
    @Transactional
    public void addPdInfoAndStartUp(CouMdPdHeadModel headModel) {
        this.couMdPdDao.insertPdHead(headModel);
        this.couMdPdDao.insertPdBody(headModel.getBodyList());
        this.execStartPdProcedure(headModel.getBillNo());
    }
    
    /**
     * 编辑启动盘点
     * @param headModel
     */
    @Transactional
    public void editPdInfoAndStartUp(CouMdPdHeadModel headModel) {
        this.couMdPdDao.deleteMdPdHead(headModel.getBillNo());
        this.couMdPdDao.deleteMdPdBody(headModel.getBillNo());
        this.couMdPdDao.insertPdHead(headModel);
        this.couMdPdDao.insertPdBody(headModel.getBodyList());
        this.execStartPdProcedure(headModel.getBillNo());
    }
    
    /**
     * 执行启动盘点存储过程
     * @param billNo
     */
    public void execStartPdProcedure(String billNo) {
        long startTime = System.currentTimeMillis();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("ps_BillNo", billNo);
        this.couMdPdDao.execStartPdProcedure(map);
        if (map.isEmpty()) {
            throw new ServiceException(GoodsResponseCode.EXEC_START_PD_PROCEDURE_ERROR.getCode(), 
                    GoodsResponseCode.EXEC_START_PD_PROCEDURE_ERROR.getMsg());
        }
        LoggerEnhance.info(LOGGER, "执行启动盘点存储过程结果为--------------------：{}", null != map.get("ps_Message") ? map.get("ps_Message").toString() : ""
            , "单据号为--------------------：{}", (map.get("ps_BillNo")));
        int resultCode = null != map.get("pi_Result") ? Integer.valueOf(map.get("pi_Result").toString()) : -1;
        if (ProcedureResult.EXEC_ERROR_RECORD.getValue() == resultCode 
                || ProcedureResult.EXEC_ERROR_EXIT.getValue() == resultCode) {
            throw new ServiceException(GoodsResponseCode.EXEC_START_PD_PROCEDURE_ERROR.getCode(), 
                    null != map.get("ps_Message") ? map.get("ps_Message").toString() : GoodsResponseCode.EXEC_START_PD_PROCEDURE_ERROR.getMsg());
        }
        long endTime = System.currentTimeMillis();
        LoggerEnhance.info(LOGGER, "执行启动盘点存储过程-【CALL sCou_MdPd_Start_CDADL()】消耗时间:{}", (endTime - startTime));
    }
    
    /**
     * 执行盘点记账存储过程
     * @param map
     * @return Map<String, Object>
     */
    @Transactional
    public Map<String, Object> execPdAccountProcedure(CouMdPdHeadModel headModel) {
        this.couMdPdDao.deleteMdPdHead(headModel.getBillNo());
        this.couMdPdDao.deleteMdPdBody(headModel.getBillNo());
        this.couMdPdDao.insertPdHead(headModel);
        this.couMdPdDao.insertPdBody(headModel.getBodyList());
        Map<String, Object> accountMap = new HashMap<String, Object>();
        accountMap.put("ps_BillNo", headModel.getBillNo());
        accountMap.put("ps_YwType", headModel.getYwType());
        accountMap.put("pi_UserId", headModel.getJzrId());
        accountMap.put("ps_UserCode", headModel.getJzrCode());
        accountMap.put("ps_UserName", headModel.getJzrName());
        accountMap.put("pd_Date", headModel.getJzDate());
        long startTime = System.currentTimeMillis();
        LoggerEnhance.info(LOGGER, JSON.toJSONString(accountMap));
        this.couMdPdDao.execPdAccountProcedure(accountMap);
        long endTime = System.currentTimeMillis();
        if (accountMap.isEmpty()) {
            throw new ServiceException(GoodsResponseCode.EXEC_PD_ACCOUNT_PROCEDURE_ERROR.getCode(), 
                    GoodsResponseCode.EXEC_PD_ACCOUNT_PROCEDURE_ERROR.getMsg());
        }
        LoggerEnhance.info(LOGGER, "执行盘点记账存储过程结果为--------------------：{}", null != accountMap.get("ps_Message") ? accountMap.get("ps_Message").toString() : ""
            , "单据号为--------------------：{}", (accountMap.get("ps_BillNo")));
        int resultCode = null != accountMap.get("pi_Result") ? Integer.valueOf(accountMap.get("pi_Result").toString()) : -1;
        if (ProcedureResult.EXEC_ERROR_RECORD.getValue() == resultCode 
                || ProcedureResult.EXEC_ERROR_EXIT.getValue() == resultCode) {
            throw new ServiceException(GoodsResponseCode.EXEC_PD_ACCOUNT_PROCEDURE_ERROR.getCode(), 
                    null != accountMap.get("ps_Message") ? accountMap.get("ps_Message").toString() : GoodsResponseCode.EXEC_PD_ACCOUNT_PROCEDURE_ERROR.getMsg());
        }
        LoggerEnhance.info(LOGGER, "执行盘点记账存储过程-【CALL sCou_MdPd_Account_CDADL()】消耗时间:{}", (endTime - startTime));
        return accountMap;
    }
}
