package com.addlove.service.goods.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.addlove.service.goods.model.OrdThBodyModel;
import com.addlove.service.goods.model.OrdThHeadModel;
import com.addlove.service.goods.model.OrdThQueryPageModel;

/**
 * 退货单dao层
 * @author lw
 *
 */
@Repository
public interface OrdThDao {
    /**
     * 退货单分页列表
     * @param queryModel
     * @return List<OrdThHeadModel>
     */
    List<OrdThHeadModel> queryOrdThPage(@Param("queryModel") OrdThQueryPageModel queryModel);
    
    /**
     * 插入退货单主表
     * @param headModel
     */
    void insertOrdThHead(OrdThHeadModel headModel);
    
    /**
     * 批量插入退货单明细表
     * @param bodyList
     */
    void insertOrdThBody(@Param("bodys") List<OrdThBodyModel> bodyList);
    
    /**
     * 查询退货单详情
     * @param billNo
     * @return List<Map<String, Object>>
     */
    List<Map<String, Object>> queryThDetailsByBillNo(String billNo);
    
    /**
     * 删除主表
     * @param billNo
     */
    void deleteThHeadModel(String billNo);
    
    /**
     * 删除明细表
     * @param billNo
     */
    void deleteThBodyModel(String billNo);
}
