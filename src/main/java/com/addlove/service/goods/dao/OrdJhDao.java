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
}
