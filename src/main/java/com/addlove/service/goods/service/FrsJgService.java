package com.addlove.service.goods.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.addlove.service.goods.constants.GoodsResponseCode;
import com.addlove.service.goods.constants.GoodsCommonConstants.ProcedureResult;
import com.addlove.service.goods.dao.FrsJgDao;
import com.addlove.service.goods.dao.OrdAdlYhDao;
import com.addlove.service.goods.exception.ServiceException;
import com.addlove.service.goods.model.FrsGyModel;
import com.addlove.service.goods.model.FrsJgCpModel;
import com.addlove.service.goods.model.FrsJgHeadModel;
import com.addlove.service.goods.model.FrsJgPageModel;
import com.addlove.service.goods.model.FrsJgYlModel;
import com.addlove.service.goods.model.SkuPluExtendModel;
import com.addlove.service.goods.util.LoggerEnhance;
import com.github.pagehelper.PageHelper;

/**
 * 加工单逻辑层
 * @author lw
 *
 */
@Service
public class FrsJgService {
    /**FrsJgService类日志 */
    private static final Logger LOGGER = LoggerFactory.getLogger(FrsJgService.class);
    
    /**
     * 默认单次批量查询plu数量
     */
    public static final int DEFAULT_BATCH_QUERY_SIZE = 500;
    
    @Autowired
    private FrsJgDao frsJgDao;
    
    @Autowired
    private OrdAdlYhDao ordAdlYhDao;
    
    
    public List<FrsJgHeadModel> queryJgPage(FrsJgPageModel queryModel) {
        PageHelper.startPage(queryModel.getPageNo(), queryModel.getPageSize(), true);
        List<FrsJgHeadModel> jgList = this.frsJgDao.queryJgPage(queryModel);
        if (null != jgList && !jgList.isEmpty()) {
            for (FrsJgHeadModel model : jgList) {
                if (StringUtils.isNotBlank(model.getLrDate()) && model.getLrDate().length() > 19) {
                    model.setLrDate(model.getLrDate().substring(0, 19));
                }
                if (StringUtils.isNotBlank(model.getJzDate()) && model.getJzDate().length() > 19) {
                    model.setJzDate(model.getJzDate().substring(0, 19));
                }
            }
        }
        return jgList;
    }
    
    /**
     * 新增加工单
     * @param headModel
     */
    @Transactional
    public void addJg(FrsJgHeadModel headModel) {
        this.frsJgDao.insertJgHead(headModel);
        this.frsJgDao.insertJgYls(headModel.getYlList());
        this.frsJgDao.insertJgCps(headModel.getCpList());
    }
    
    /**
     * 编辑加工单
     * @param headModel
     */
    @Transactional
    public void editJg(FrsJgHeadModel headModel) {
        this.frsJgDao.delJgHead(headModel.getBillNo());
        this.frsJgDao.delJgYls(headModel.getBillNo());
        this.frsJgDao.delJgCps(headModel.getBillNo());
        this.frsJgDao.insertJgHead(headModel);
        this.frsJgDao.insertJgYls(headModel.getYlList());
        this.frsJgDao.insertJgCps(headModel.getCpList());
    }
    
    /**
     * 删除加工数据
     * @param billNo
     */
    @Transactional
    public void delJg(String billNo) {
        this.frsJgDao.delJgHead(billNo);
        this.frsJgDao.delJgYls(billNo);
        this.frsJgDao.delJgCps(billNo);
    }
    
    /**
     * 查询加工单详情
     * @param billNo
     * @return FrsJgHeadModel
     */
    public FrsJgHeadModel queryJgDetails(String billNo) {
        FrsJgHeadModel headModel = this.frsJgDao.getJgHead(billNo);
        List<FrsJgYlModel> ylList = this.frsJgDao.getJgYls(billNo);
        List<FrsJgCpModel> cpList = this.frsJgDao.getJgCps(billNo);
        headModel.setYlList(ylList);
        headModel.setCpList(cpList);
        return headModel;
    }
    
    /**
     * 获取加工商品
     * @param orgCode
     * @param depId
     * @param ckCode
     * @return Set<SkuPluExtendModel>
     */
    public Set<SkuPluExtendModel> getJgSkuList(String orgCode, Long depId, String ckCode) {
        //获取部门商品
        List<SkuPluExtendModel> multiSkus = this.frsJgDao.getMultiSkus(orgCode, depId, ckCode);
        List<SkuPluExtendModel> lyAndBsSkus = this.frsJgDao.getLyAndBsSkus(orgCode, depId, ckCode);
        //去重商品
        Set<SkuPluExtendModel> skuSet = new HashSet<>();
        if (null != multiSkus && !multiSkus.isEmpty()) {
            multiSkus.addAll(lyAndBsSkus);
            skuSet.addAll(multiSkus);
        }else {
            skuSet.addAll(lyAndBsSkus);
        }
        List<Map<String, Object>> pluList = new LinkedList<Map<String, Object>>();
        for (SkuPluExtendModel pluModel : skuSet) {
            Map<String, Object> queryMap = new HashMap<String, Object>();
            queryMap.put("pluId", pluModel.getPluId());
            pluList.add(queryMap);
        }
       //获取商品可用库存数量
        List<Map<String, Object>> kcList = new ArrayList<>();
        int size = pluList.size();
        if (size > 0) {
            if (DEFAULT_BATCH_QUERY_SIZE < size) {
                int part = size / DEFAULT_BATCH_QUERY_SIZE; // 分批数
                for (int i = 0; i < part; i++) {
                    List<Map<String, Object>> partPluList = new LinkedList<Map<String, Object>>();
                    partPluList.addAll(pluList.subList(0, DEFAULT_BATCH_QUERY_SIZE));
                    List<Map<String,Object>> kcPartList = this.ordAdlYhDao.getYhKcSum(partPluList, orgCode);
                    kcList.addAll(kcPartList);
                    pluList.subList(0, DEFAULT_BATCH_QUERY_SIZE).clear();
                }
                if (!pluList.isEmpty()) {
                    List<Map<String,Object>> kcPartList = this.ordAdlYhDao.getYhKcSum(pluList, orgCode);
                    kcList.addAll(kcPartList);
                }
            }else {
                kcList = this.ordAdlYhDao.getYhKcSum(pluList, orgCode);
            }
        }
        Map<Long, Object> kcMap = new HashMap<Long, Object>();
        for (Map<String, Object> map : kcList) {
            long pluId = Long.parseLong(null != map.get("PLUID") ? map.get("PLUID").toString() : "0") ;
            kcMap.put(pluId, null != map.get("KCCOUNT") ? map.get("KCCOUNT").toString() : "0");
        }
        for (SkuPluExtendModel pluModel : skuSet) {
            double kcCount = 0.0;
            if (null != kcMap.get(pluModel.getPluId())) {
                kcCount = Double.valueOf(kcMap.get(pluModel.getPluId()).toString());
            }
            pluModel.setKcCount(kcCount);
        }
        return skuSet;
    }
    
    /**
     * 获取加工工艺数据
     * @param orgCode
     * @param depCode
     * @return List<FrsGyModel>
     */
    public List<FrsGyModel> getJgGys(String orgCode, String depCode) {
        return this.frsJgDao.getJgGys(orgCode, depCode);
    }
    
    /**
     * 调用存储过程进行单据记账
     * @param map
     * @return Map<String, Object>
     */
    @Transactional
    public Map<String, Object> execJgAccountProcedure(Map<String, Object> map) {
        long startTime = System.currentTimeMillis();
        this.frsJgDao.execJgAccountProcedure(map);
        long endTime = System.currentTimeMillis();
        LoggerEnhance.info(LOGGER, "调用加工单据记账存储过程-【CALL sFrs_JgBill_Account()】消耗时间:{}", (endTime - startTime));
        if (null == map) {
            throw new ServiceException(GoodsResponseCode.EXEC_PROCEDURE_ERROR.getCode(), 
                    GoodsResponseCode.EXEC_PROCEDURE_ERROR.getMsg());
        }
        LoggerEnhance.info(LOGGER, "加工单据记账结果为--------------------：{}", null != map.get("ps_Message") ? map.get("ps_Message").toString() : "");
        int resultCode = null != map.get("pi_Result") ? Integer.valueOf(map.get("pi_Result").toString()) : -1;
        if (ProcedureResult.EXEC_ERROR_RECORD.getValue() == resultCode 
                || ProcedureResult.EXEC_ERROR_EXIT.getValue() == resultCode) {
            throw new ServiceException(GoodsResponseCode.EXEC_PROCEDURE_ERROR.getCode(), 
                    null != map.get("ps_Message") ? map.get("ps_Message").toString() : GoodsResponseCode.EXEC_PROCEDURE_ERROR.getMsg());
        }
        return map;
    }
}
