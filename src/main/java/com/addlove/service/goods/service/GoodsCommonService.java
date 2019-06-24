package com.addlove.service.goods.service;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.addlove.service.goods.dao.GoodsCommonDao;
import com.addlove.service.goods.model.CntContractModel;
import com.addlove.service.goods.model.EtpSupplierModel;
import com.addlove.service.goods.model.OrgDeptModel;
import com.addlove.service.goods.model.OrgManageModel;
import com.addlove.service.goods.model.SkuPluModel;
import com.addlove.service.goods.model.StkStoreModel;
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
     * 获取仓库
     * @param orgCode
     * @return List<StkStoreModel>
     */
    public List<StkStoreModel> getStoreList(String orgCode) {
        return this.commonDao.getStoreList(orgCode);
    }
    
    /**
     * 调用存储过程生成退货差异单据号
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
        return map;
    }
}
