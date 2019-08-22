package com.addlove.service.goods.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.addlove.service.goods.model.OrdAdlYhBodyModel;
import com.addlove.service.goods.model.OrdAdlYhHeadModel;
import com.addlove.service.goods.model.OrdAdlYhPageModel;
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
     * 要货列表
     * @param queryModel
     * @return List<OrdAdlYhHeadModel> 
     */
    List<OrdAdlYhHeadModel> queryYhPage(@Param("queryModel") OrdAdlYhPageModel queryModel);
    
    /**
     * 插入要货主表
     * @param headModel
     */
    void insertYhHead(OrdAdlYhHeadModel headModel);
    
    /**
     * 插入要货明细
     * @param bodyList
     */
    void insertYhBodys(@Param("bodys") List<OrdAdlYhBodyModel> bodyList);
    
    /**
     * 通过单号获取要货主表
     * @param billNo
     * @return OrdAdlYhHeadModel
     */
    OrdAdlYhHeadModel getYhHead(String billNo);
    
    /**
     * 通过单号获取要货明细
     * @param billNo
     * @return List<OrdAdlYhBodyModel>
     */
    List<OrdAdlYhBodyModel> getYhBodys(String billNo);
    
    /**
     * 删除要货主表
     * @param billNo
     */
    void delYhHead(String billNo);
    
    /**
     * 删除要货明细
     * @param billNo
     */
    void delYhBodys(String billNo);
    
    /**
     * 获取组织下的模板
     * @param orgCode
     * @return OrdYhTempletHeadModel
     */
    List<OrdYhTempletHeadModel> getTempletsByOrgCode(String orgCode);
    
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
    
    /**
     * 获取明日到货数量
     * @param map
     */
    void execMrCountsProcedure(Map<String, Object> map);
    
    void getPcDatas(Map<String, Object> map);
    
    /**
     * 调用存储过程进行要货单据记账
     * @param map
     */
    void execYhAccountProcedure(Map<String, Object> map);
}
