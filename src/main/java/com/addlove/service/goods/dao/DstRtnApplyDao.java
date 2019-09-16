package com.addlove.service.goods.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.addlove.service.goods.model.BasFlContentModel;
import com.addlove.service.goods.model.DstRtnApplyBodyModel;
import com.addlove.service.goods.model.DstRtnApplyHeadModel;
import com.addlove.service.goods.model.DstRtnPageModel;
import com.addlove.service.goods.model.SkuPluExtendModel;

/**
 * 门店配送退货申请单(质量退货)dao层
 * @author lw
 *
 */
@Repository
public interface DstRtnApplyDao {
    /**
     * 查询配送退货单列表
     * @param queryModel
     * @return List<DstRtnApplyHeadModel>
     */
    List<DstRtnApplyHeadModel> queryRtnPage(@Param("queryModel") DstRtnPageModel queryModel);
    
    /**
     * 插入配送退货主表
     * @param headModel
     */
    void insertRtnHead(DstRtnApplyHeadModel headModel);
    
    /**
     * 插入配送退货明细表
     * @param bodyList
     */
    void insertRtnBodys(@Param("bodys") List<DstRtnApplyBodyModel> bodyList);
    
    /**
     * 查询配送退货主表
     * @param billNo
     * @return DstRtnApplyHeadModel
     */
    DstRtnApplyHeadModel getRtnHead(String billNo);
    
    /**
     * 查询配送退货明细表
     * @param billNo
     * @return List<DstRtnApplyBodyModel>
     */
    List<DstRtnApplyBodyModel> getRtnBodys(String billNo);
    
    /**
     * 删除配送退货主表
     * @param billNo
     */
    void delRtnHead(String billNo);
    
    /**
     * 删除配送退货明细表
     * @param billNo
     */
    void delRtnBodys(String billNo);
    
    /**
     * 获取退货原因
     * @param flCode
     * @return List<BasFlContentModel>
     */
    List<BasFlContentModel> getThReason(String flCode);
    
    /**
     * 通过组织、部门、仓库查询退货商品
     * @param orgCode
     * @param depId
     * @param ckCode
     * @return List<SkuPluExtendModel>
     */
    List<SkuPluExtendModel> getRtnSkus(@Param("orgCode")String orgCode, @Param("depId")Long depId, 
            @Param("ckCode")String ckCode);
    
    /**
     * 查询多部门退货商品
     * @param orgCode
     * @param depId
     * @param ckCode
     * @return List<SkuPluExtendModel>
     */
    List<SkuPluExtendModel> getOtherDeptRtnSkus(@Param("orgCode")String orgCode, @Param("depId")Long depId, 
            @Param("ckCode")String ckCode);
    
    /**
     * 调用配送退货存储过程进行单据记账
     * @param map
     */
    void execRtnAccountProcedure(Map<String, Object> map);
}
