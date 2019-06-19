package com.addlove.service.goods.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import com.addlove.service.goods.model.OrdJhBodyModel;
import com.addlove.service.goods.model.OrdJhHeadModel;
import com.addlove.service.goods.model.OrdJhQueryPageModel;

/**
 * 验收单dao层
 * @author lw
 *
 */
@Repository
public interface OrdJhDao {
    /**
     * 分页查询配送验收信息
     * @param queryModel
     * @return List<OrdJhHeadModel>
     */
    List<OrdJhHeadModel> queryOrdJhHeadModelByPage(@Param("queryModel") OrdJhQueryPageModel queryModel);
    
    /**
     * 根据配送验收单号查询商品信息
     * @param billNo
     * @return List<OrdJhBodyModel>
     */
    List<Map<String, Object>> queryBodysByBillNo(String billNo);
    
    /**
     * 更新配送验收单：验收人、验收状态、送货确认时间
     * @param model
     */
    void updateJhHeadYsrCodeAndStatus(OrdJhHeadModel model);
    
    /**
     * 更新配送验收单明细配送收货数量
     * @param jhModelList
     */
    void updateJhBodyPsShCount(@Param("list") List<OrdJhBodyModel> jhModelList);
    
    /**
     * 查询分货单部门信息
     * @param billNo
     * @return Map<String, Object>
     */
    List<Map<String, Object>> queryFhDepCode(@Param("list") List<Map<String, Object>> billNos);
    
    /**
     * 插入验收主表
     * @param headModel
     */
    void insertOrdJhHead(OrdJhHeadModel headModel);
    
    /**
     * 插入验收明细表
     * @param bodyList
     */
    void insertOrdJhBody(@Param("bodys") List<OrdJhBodyModel> bodyList);
    
    /**
     * 更新记账信息
     * @param model
     */
    void updateJhHeadAccountInfo(OrdJhHeadModel model);
    
    /**
     * 删除主表
     * @param billNo
     */
    void deleteJhHeadModel(String billNo);
    
    /**
     * 删除明细表
     * @param billNo
     */
    void deleteJhBodyModel(String billNo);
}
