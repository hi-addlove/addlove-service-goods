package com.addlove.service.goods.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.addlove.service.goods.model.CntContractModel;
import com.addlove.service.goods.model.EtpSupplierModel;
import com.addlove.service.goods.model.SkuPluModel;

/**
 * 公共dao层：如供应商、合同、商品等
 * @author lw
 *
 */
@Repository
public interface GoodsCommonDao {
    /**
     * 获取供应商
     * @param orgCode
     * @return List<EtpSupplierModel>
     */
    List<EtpSupplierModel> getSupplierList(String orgCode);
    
    /**
     * 通过组织、供应商获取合同
     * @param orgCode
     * @param etpCode
     * @return List<CntContractModel>
     */
    List<CntContractModel> getCntList(@Param("orgCode")String orgCode, @Param("etpCode")String etpCode);
    
    /**
     * 通过组织、合同获取商品
     * @param orgCode
     * @param cntId
     * @return List<SkuPluModel>
     */
    List<SkuPluModel> getSkuList(@Param("orgCode")String orgCode, @Param("cntId")Long cntId);
}