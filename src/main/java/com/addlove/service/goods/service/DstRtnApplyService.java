package com.addlove.service.goods.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
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
import com.addlove.service.goods.dao.DstRtnApplyDao;
import com.addlove.service.goods.dao.GoodsCommonDao;
import com.addlove.service.goods.exception.ServiceException;
import com.addlove.service.goods.model.BasFlContentModel;
import com.addlove.service.goods.model.DstRtnApplyBodyModel;
import com.addlove.service.goods.model.DstRtnApplyHeadModel;
import com.addlove.service.goods.model.DstRtnPageModel;
import com.addlove.service.goods.model.SkuPluExtendModel;
import com.addlove.service.goods.util.LoggerEnhance;
import com.github.pagehelper.PageHelper;

/**
 * 门店配送退货申请单(质量退货)逻辑层
 * @author lw
 *
 */
@Service
public class DstRtnApplyService {
    /**DstRtnApplyService类日志 */
    private static final Logger LOGGER = LoggerFactory.getLogger(DstRtnApplyService.class);
    /**
     * 默认单次批量查询plu数量
     */
    public static final int DEFAULT_BATCH_QUERY_SIZE = 500;
    
    @Autowired
    private DstRtnApplyDao rtnApplyDao;
    
    @Autowired
    private GoodsCommonDao commonDao;
    
    /**
     * 查询配送退货单列表
     * @param queryModel
     * @return List<DstRtnApplyHeadModel>
     */
    public List<DstRtnApplyHeadModel> queryRtnPage(DstRtnPageModel queryModel) {
        PageHelper.startPage(queryModel.getPageNo(), queryModel.getPageSize(), true);
        List<DstRtnApplyHeadModel> rtnList = this.rtnApplyDao.queryRtnPage(queryModel);
        if (null != rtnList && !rtnList.isEmpty()) {
            for (DstRtnApplyHeadModel model : rtnList) {
                if (StringUtils.isNotBlank(model.getLrDate()) && model.getLrDate().length() > 19) {
                    model.setLrDate(model.getLrDate().substring(0, 19));
                }
                if (StringUtils.isNotBlank(model.getJzDate()) && model.getJzDate().length() > 19) {
                    model.setJzDate(model.getJzDate().substring(0, 19));
                }
            }
        }
        return rtnList;
    }
    
    /**
     * 新增配送退货单
     * @param headModel
     */
    @Transactional
    public void addRtn(DstRtnApplyHeadModel headModel) {
        this.rtnApplyDao.insertRtnHead(headModel);
        this.rtnApplyDao.insertRtnBodys(headModel.getBodyList());
    }
    
    /**
     * 编辑配送退货单
     * @param headModel
     */
    @Transactional
    public void editRtn(DstRtnApplyHeadModel headModel) {
        this.rtnApplyDao.delRtnHead(headModel.getBillNo());
        this.rtnApplyDao.delRtnBodys(headModel.getBillNo());
        this.rtnApplyDao.insertRtnHead(headModel);
        this.rtnApplyDao.insertRtnBodys(headModel.getBodyList());
    }
    
    /**
     * 删除配送退货数据
     * @param billNo
     */
    @Transactional
    public void delRtn(String billNo) {
        this.rtnApplyDao.delRtnHead(billNo);
        this.rtnApplyDao.delRtnBodys(billNo);
    }
    
    /**
     * 查询配送退货单详情
     * @param billNo
     * @return DstRtnApplyHeadModel
     */
    public DstRtnApplyHeadModel queryRtnDetails(String billNo) {
        DstRtnApplyHeadModel headModel = this.rtnApplyDao.getRtnHead(billNo);
        List<DstRtnApplyBodyModel> bodyList = this.rtnApplyDao.getRtnBodys(billNo);
        headModel.setBodyList(bodyList);
        if (StringUtils.isNotBlank(headModel.getLrDate()) && headModel.getLrDate().length() > 19) {
            headModel.setLrDate(headModel.getLrDate().substring(0, 19));
        }
        if (StringUtils.isNotBlank(headModel.getJzDate()) && headModel.getJzDate().length() > 19) {
            headModel.setJzDate(headModel.getJzDate().substring(0, 19));
        }
        return headModel;
    }
    
    /**
     * 获取退货原因
     * @param flCode
     * @return List<BasFlContentModel>
     */
    public List<BasFlContentModel> getThReason(String flCode) {
        return this.rtnApplyDao.getThReason(flCode);
    }
    
    /**
     * 获取配送退货单商品
     * @param orgCode
     * @param depId
     * @param ckCode
     * @return Set<SkuPluExtendModel>
     */
    public Set<SkuPluExtendModel> getRtnSkuList(String orgCode, Long depId, String ckCode) {
        long startTime = System.currentTimeMillis();
        Set<SkuPluExtendModel> allSkus = new HashSet<SkuPluExtendModel>();
        List<SkuPluExtendModel> rtnSkus = this.rtnApplyDao.getRtnSkus(orgCode, depId, ckCode);
        if (null != rtnSkus && !rtnSkus.isEmpty()) {
            allSkus.addAll(rtnSkus);
        }
        List<SkuPluExtendModel> otherRtnSkus = this.rtnApplyDao.getOtherDeptRtnSkus(orgCode, depId, ckCode);
        if (null != otherRtnSkus && !otherRtnSkus.isEmpty()) {
            allSkus.addAll(otherRtnSkus);
        }
        //获取商品可用库存数量
        Iterator<SkuPluExtendModel> iterator = allSkus.iterator();
        List<Map<String, Object>> pluList = new LinkedList<Map<String, Object>>();
        while (iterator.hasNext()) {
            SkuPluExtendModel pluModel = iterator.next();
            Map<String, Object> queryMap = new HashMap<String, Object>();
            queryMap.put("pluId", pluModel.getPluId());
            pluList.add(queryMap);
        }
        List<Map<String, Object>> kcList = new ArrayList<>();
        int size = pluList.size();
        if (size > 0) {
            if (DEFAULT_BATCH_QUERY_SIZE < size) {
                int part = size / DEFAULT_BATCH_QUERY_SIZE; // 分批数
                for (int i = 0; i < part; i++) {
                    List<Map<String, Object>> partPluList = new LinkedList<Map<String, Object>>();
                    partPluList.addAll(pluList.subList(0, DEFAULT_BATCH_QUERY_SIZE));
                    List<Map<String,Object>> kcPartList = this.commonDao.getKcSum(partPluList, orgCode);
                    kcList.addAll(kcPartList);
                    pluList.subList(0, DEFAULT_BATCH_QUERY_SIZE).clear();
                }
                if (!pluList.isEmpty()) {
                    List<Map<String,Object>> kcPartList = this.commonDao.getKcSum(pluList, orgCode);
                    kcList.addAll(kcPartList);
                }
            }else {
                kcList = this.commonDao.getKcSum(pluList, orgCode);
            }
        }
        Map<Long, Object> kcMap = new HashMap<Long, Object>();
        for (Map<String, Object> map : kcList) {
            long pluId = Long.parseLong(null != map.get("PLUID") ? map.get("PLUID").toString() : "0") ;
            kcMap.put(pluId, null != map.get("KCCOUNT") ? map.get("KCCOUNT").toString() : "0");
        }
        for (SkuPluExtendModel pluModel : allSkus) {
            if (null != kcMap.get(pluModel.getPluId())) {
                pluModel.setKcCount(Double.valueOf(kcMap.get(pluModel.getPluId()).toString()));
            }else {
                pluModel.setKcCount(0.0);
            }
        }
        long endTime = System.currentTimeMillis();
        LoggerEnhance.info(LOGGER, "查询领用单商品消耗时间:{}", (endTime - startTime));
        return allSkus;
    }
    
    /**
     * 调用存储过程进行单据记账
     * @param map
     * @return Map<String, Object>
     */
    @Transactional
    public Map<String, Object> execRtnAccountProcedure(Map<String, Object> map) {
        long startTime = System.currentTimeMillis();
        this.rtnApplyDao.execRtnAccountProcedure(map);
        long endTime = System.currentTimeMillis();
        LoggerEnhance.info(LOGGER, "调用配送退货单据记账存储过程-【sDst_CDADL_RtnApply()】消耗时间:{}", (endTime - startTime));
        if (null == map) {
            throw new ServiceException(GoodsResponseCode.EXEC_PROCEDURE_ERROR.getCode(), 
                    GoodsResponseCode.EXEC_PROCEDURE_ERROR.getMsg());
        }
        LoggerEnhance.info(LOGGER, "配送退货单据记账结果为--------------------：{}", null != map.get("ps_Message") ? map.get("ps_Message").toString() : "");
        int resultCode = null != map.get("pi_Result") ? Integer.valueOf(map.get("pi_Result").toString()) : -1;
        if (ProcedureResult.EXEC_ERROR_RECORD.getValue() == resultCode 
                || ProcedureResult.EXEC_ERROR_EXIT.getValue() == resultCode) {
            throw new ServiceException(GoodsResponseCode.EXEC_PROCEDURE_ERROR.getCode(), 
                    null != map.get("ps_Message") ? map.get("ps_Message").toString() : GoodsResponseCode.EXEC_PROCEDURE_ERROR.getMsg());
        }
        return map;
    }
}
