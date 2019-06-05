package com.addlove.service.goods.dao;

import java.util.List;

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
    List<OrdJhBodyModel> querySkusByBillNo(String billNo);
}