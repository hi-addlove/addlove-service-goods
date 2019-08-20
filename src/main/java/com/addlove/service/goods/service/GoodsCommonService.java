package com.addlove.service.goods.service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.addlove.service.goods.constants.GoodsResponseCode;
import com.addlove.service.goods.constants.GoodsCommonConstants.ProcedureResult;
import com.addlove.service.goods.dao.GoodsCommonDao;
import com.addlove.service.goods.exception.ServiceException;
import com.addlove.service.goods.model.BasBrandModel;
import com.addlove.service.goods.model.CatCategoryModel;
import com.addlove.service.goods.model.CntContractModel;
import com.addlove.service.goods.model.EtpSupplierModel;
import com.addlove.service.goods.model.OrgDeptModel;
import com.addlove.service.goods.model.OrgManageModel;
import com.addlove.service.goods.model.SkuPluExtendModel;
import com.addlove.service.goods.model.SkuPluModel;
import com.addlove.service.goods.model.StkStoreModel;
import com.addlove.service.goods.model.UserEmployeeModel;
import com.addlove.service.goods.model.UsrUserModel;
import com.addlove.service.goods.util.LoggerEnhance;

/**
 * 公共service层：如供应商、合同、商品等
 * @author lw
 *
 */
@Service
public class GoodsCommonService {
    /**GoodsCommonService类日志 */
    private static final Logger LOGGER = LoggerFactory.getLogger(GoodsCommonService.class);
    
    @Autowired
    private GoodsCommonDao commonDao;
    
    /**
     * 获取供应商
     * @param orgCode
     * @return List<EtpSupplierModel>
     */
    public List<EtpSupplierModel> getSupplierList(String orgCode) {
        return this.commonDao.getSupplierList(orgCode);
    }
    
    /**
     * 通过组织、供应商获取合同
     * @param orgCode
     * @param etpCode
     * @return List<CntContractModel>
     */
    public List<CntContractModel> getCntList(String orgCode, String etpCode){
        return this.commonDao.getCntList(orgCode, etpCode);
    }
    
    /**
     * 通过组织、合同获取商品
     * @param orgCode
     * @param cntId
     * @return List<SkuPluModel>
     */
    public List<SkuPluModel> getSkuList(String orgCode, Long cntId) {
        return this.commonDao.getSkuList(orgCode, cntId);
    }
    
    /**
     * 获取部门
     * @param orgCode
     * @return List<OrgDeptModel>
     */
    public List<OrgDeptModel> getDeptList(String orgCode) {
        return this.commonDao.getDeptList(orgCode);
    }
    
    /**
     * 获取组织
     * @param orgCode
     * @return OrgManageModel
     */
    public OrgManageModel getOrgModel(String orgCode) {
        return this.commonDao.getOrgModel(orgCode);
    }
    
    /**
     * 获取所有组织
     * @return List<OrgManageModel>
     */
    public List<OrgManageModel> getAllOrgModel() {
        return this.commonDao.getAllOrgModel();
    }
    
    /**
     * 获取仓库
     * @param orgCode
     * @return List<StkStoreModel>
     */
    public List<StkStoreModel> getStoreList(String orgCode) {
        return this.commonDao.getStoreList(orgCode);
    }
    
    /**
     * 通过组织、部门、仓库查询商品
     * @param orgCode
     * @param depId
     * @param ckCode
     * @return List<SkuPluExtendModel>
     */
    public List<SkuPluExtendModel> getSkuListByDept(String orgCode, String shOrgCode, Long depId, String ckCode) {
        List<SkuPluExtendModel> skuList = this.commonDao.getSkuListByDept(orgCode, shOrgCode, depId, ckCode);
        List<SkuPluExtendModel> backSkuList = new LinkedList<SkuPluExtendModel>();
        if (null == skuList || skuList.isEmpty()) {
            return backSkuList;
        }
        List<Map<String, Object>> pluList = new LinkedList<Map<String, Object>>();
        for (SkuPluExtendModel skuModel : skuList) {
            Map<String, Object> queryMap = new HashMap<String, Object>();
            queryMap.put("pluId", skuModel.getPluId());
            pluList.add(queryMap);
        }
        //得到商品的可用库存
        List<Map<String, Object>> kcList = this.commonDao.getKcSum(pluList);
        Map<Long, Object> kcMap = new HashMap<Long, Object>();
        for (Map<String, Object> map : kcList) {
            long pluId = Long.parseLong(null != map.get("PLUID") ? map.get("PLUID").toString() : "0") ;
            kcMap.put(pluId, null != map.get("KCCOUNT") ? map.get("KCCOUNT").toString() : "0");
        }
        //去重商品
        Set<SkuPluExtendModel> skuSet = new HashSet<>();
        skuSet.addAll(skuList);
        //将可用库存返回
        for (SkuPluExtendModel skuModel : skuSet) {
            long key = skuModel.getPluId();
            Double kcCount = Double.valueOf(kcMap.get(key).toString());
            if (kcCount > 0) {
                skuModel.setKcCount(kcCount);
                backSkuList.add(skuModel);
            }
        }
        return backSkuList;
    }
    
    /**
     * 通过组织、部门查询生产计划商品
     * @param orgCode
     * @param depId
     * @return List<SkuPluModel>
     */
    public List<SkuPluModel> getProPlanSkuListByDept(String orgCode, Long depId) {
        return this.commonDao.getProPlanSkuListByDept(orgCode, depId);
    }
    
    /**
     * 搜索用户
     * @param searchContent:用户ID、用户编码、用户名
     * @return List<UsrUserModel>
     */
    public List<UsrUserModel> getAllUsers(Map<String, Object> map) {
        return this.commonDao.getAllUsers(map);
    }
    
    /**
     * 获取采购员
     * @param map
     * @return List<UserEmployeeModel>
     */
    public List<UserEmployeeModel> getPurchaseEmp(Map<String, Object> map) {
        return this.commonDao.getPurchaseEmp(map);
    }
    
    /**
     * 获取商品品类
     * @param map
     * @return List<CatCategoryModel>
     */
    public List<CatCategoryModel> getCatCategorys(Map<String, Object> map) {
        return this.commonDao.getCatCategorys(map);
    }
    
    /**
     * 获取商品品牌
     * @param map
     * @return List<BasBrandModel>
     */
    public List<BasBrandModel> getBasBrands(Map<String, Object> map) {
        return this.commonDao.getBasBrands(map);
    }
    
    /**
     * 获取采购退货商品
     * @param orgCode
     * @param cntId
     * @param ckCode
     * @return List<SkuPluExtendModel>
     */
    public List<SkuPluExtendModel> getPurchaseReturnSkus(String orgCode, Long cntId, String ckCode) {
        List<SkuPluExtendModel> skuList = this.commonDao.getPurchaseReturnSkus(orgCode, cntId, ckCode);
        List<SkuPluExtendModel> backSkuList = new LinkedList<SkuPluExtendModel>();
        if (null == skuList || skuList.isEmpty()) {
            return backSkuList;
        }
        List<Map<String, Object>> pluList = new LinkedList<Map<String, Object>>();
        for (SkuPluExtendModel skuModel : skuList) {
            Map<String, Object> queryMap = new HashMap<String, Object>();
            queryMap.put("pluId", skuModel.getPluId());
            pluList.add(queryMap);
        }
        //得到商品的可用库存
        List<Map<String, Object>> kcList = this.commonDao.getKcSum(pluList);
        Map<Long, Object> kcMap = new HashMap<Long, Object>();
        for (Map<String, Object> map : kcList) {
            long pluId = Long.parseLong(null != map.get("PLUID") ? map.get("PLUID").toString() : "0") ;
            kcMap.put(pluId, null != map.get("KCCOUNT") ? map.get("KCCOUNT").toString() : "0");
        }
        //去重商品
        Set<SkuPluExtendModel> skuSet = new HashSet<>();
        skuSet.addAll(skuList);
        //将可用库存返回
        for (SkuPluExtendModel skuModel : skuSet) {
            long key = skuModel.getPluId();
            Double kcCount = Double.valueOf(kcMap.get(key).toString());
            skuModel.setKcCount(kcCount > 0 ? kcCount : 0);
            backSkuList.add(skuModel);
        }
        return backSkuList;
    }
    
    /**
     * 获取门店报损商品
     * @param orgCode
     * @param depId
     * @return List<SkuPluExtendModel>
     */
    public List<SkuPluExtendModel> getMdBsSkus(String orgCode, Long depId) {
         List<SkuPluExtendModel> skuList = this.commonDao.getMdBsSkus(orgCode, depId);
         List<SkuPluExtendModel> backSkuList = new LinkedList<SkuPluExtendModel>();
         if (null == skuList || skuList.isEmpty()) {
             return backSkuList;
         }
         List<Map<String, Object>> pluList = new LinkedList<Map<String, Object>>();
         for (SkuPluExtendModel skuModel : skuList) {
             Map<String, Object> queryMap = new HashMap<String, Object>();
             queryMap.put("pluId", skuModel.getPluId());
             pluList.add(queryMap);
         }
         //得到商品的可用库存
         List<Map<String, Object>> kcList = this.commonDao.getKcSum(pluList);
         Map<Long, Object> kcMap = new HashMap<Long, Object>();
         for (Map<String, Object> map : kcList) {
             long pluId = Long.parseLong(null != map.get("PLUID") ? map.get("PLUID").toString() : "0") ;
             kcMap.put(pluId, null != map.get("KCCOUNT") ? map.get("KCCOUNT").toString() : "0");
         }
         //去重商品
         Set<SkuPluExtendModel> skuSet = new HashSet<>();
         skuSet.addAll(skuList);
         //将可用库存返回
         for (SkuPluExtendModel skuModel : skuSet) {
             long key = skuModel.getPluId();
             Double kcCount = Double.valueOf(kcMap.get(key).toString());
             if (kcCount > 0) {
                 skuModel.setKcCount(kcCount);
                 backSkuList.add(skuModel);
             }
         }
         return backSkuList;
    }
    
    /**
     * 查询商品可用库存数量
     * @param orgCode
     * @return List<SkuPluExtendModel>
     */
    public List<SkuPluExtendModel> getCanUseSkuCounts(String orgCode) {
        return this.commonDao.getCanUseSkuCounts(orgCode);
    }
    
    /**
     * 获取每个商品的库存总数
     * @param pluSet
     * @return List<Map<String, Object>>
     */
    public List<Map<String, Object>> getKcSum(List<Map<String, Object>> pluIds) {
        return this.commonDao.getKcSum(pluIds);
    }
    
    /**
     * 调用存储过程生成单据号
     * @param map
     * @return billNo
     */
    @Transactional
    public String getBillNoByCallProcedure(Map<String, Object> map) {
        long startTime = System.currentTimeMillis();
        this.commonDao.getBillNoByCallProcedure(map);
        long endTime = System.currentTimeMillis();
        LoggerEnhance.info(LOGGER, "调用生成单据号存储过程-【CALL sSysGetBillNo()】消耗时间:{}", (endTime - startTime));
        if (null == map || map.isEmpty()) {
            return "";
        }
        return map.get("ps_BillNo") != null ? map.get("ps_BillNo").toString() : "";
    }
    
    /**
     * 调用存储过程进行单据记账
     * @param map
     * @return Map<String, Object>
     */
    @Transactional
    public Map<String, Object> execAccountByCallProcedure(Map<String, Object> map) {
        long startTime = System.currentTimeMillis();
        this.commonDao.execAccountByCallProcedure(map);
        long endTime = System.currentTimeMillis();
        LoggerEnhance.info(LOGGER, "调用单据记账存储过程-【CALL PSSY_DB.sStk_LetsGo_ORA()】消耗时间:{}", (endTime - startTime));
        if (null == map) {
            throw new ServiceException(GoodsResponseCode.EXEC_PROCEDURE_ERROR.getCode(), 
                    GoodsResponseCode.EXEC_PROCEDURE_ERROR.getMsg());
        }
        LoggerEnhance.info(LOGGER, "单据记账结果为--------------------：{}", null != map.get("ps_Message") ? map.get("ps_Message").toString() : "");
        int resultCode = null != map.get("pi_Result") ? Integer.valueOf(map.get("pi_Result").toString()) : -1;
        if (ProcedureResult.EXEC_ERROR_RECORD.getValue() == resultCode 
                || ProcedureResult.EXEC_ERROR_EXIT.getValue() == resultCode) {
            throw new ServiceException(GoodsResponseCode.EXEC_PROCEDURE_ERROR.getCode(), 
                    null != map.get("ps_Message") ? map.get("ps_Message").toString() : GoodsResponseCode.EXEC_PROCEDURE_ERROR.getMsg());
        }
        return map;
    }
}
