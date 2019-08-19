package com.addlove.service.goods.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.addlove.service.goods.model.OrdYhCycleModel;
import com.addlove.service.goods.model.OrdYhTempletBodyModel;
import com.addlove.service.goods.model.OrdYhTempletHeadModel;
import com.addlove.service.goods.model.SkuYhPSBodyModel;

/**
 * ADL要货单dao层
 * @author lw
 *
 */
@Repository
public interface OrdAdlYhDao {
    /**
     * 获取组织下的模板
     * @param orgCode
     * @return OrdYhTempletHeadModel
     */
    OrdYhTempletHeadModel getTempletsByOrgCode(String orgCode);
    
    /**
     * 获取模板商品
     * @param orgCode
     * @param modelCode
     * @return List<OrdYhTempletBodyModel>
     */
    List<OrdYhTempletBodyModel> getTempletSkus(@Param("orgCode") String orgCode, @Param("modelCode") String modelCode);
    
    /**
     * 获取要货周期的商品
     * @param orgCode
     * @return List<OrdYhCycleModel>
     */
    List<OrdYhCycleModel> getYhCycleSkus(String orgCode);
    
    /**
     * 获取要货参数商品（包括：最小、最大要货量及倍数）
     * @param orgCode
     * @return List<SkuYhPSBodyModel>
     */
    List<SkuYhPSBodyModel> getYhPSSkus(String orgCode);
}
