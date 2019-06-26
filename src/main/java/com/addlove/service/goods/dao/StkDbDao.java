package com.addlove.service.goods.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.addlove.service.goods.model.StkDbBodyModel;
import com.addlove.service.goods.model.StkDbHeadModel;
import com.addlove.service.goods.model.StkDbQueryPageModel;

/**
 * 调拨单dao层
 * @author lw
 *
 */
@Repository
public interface StkDbDao {
    /**
     * 分页查询调拨信息
     * @param queryModel
     * @return List<StkDbHeadModel>
     */
    List<StkDbHeadModel> queryDbPage(@Param("queryModel") StkDbQueryPageModel queryModel);
    
    /**
     * 插入调拨主表
     * @param headModel
     */
    void insertStkDbHead(StkDbHeadModel headModel);
    
    /**
     * 插入调拨明细表
     * @param bodyModel
     */
    void insertStkDbBody(@Param("bodys") List<StkDbBodyModel> bodyList);
    
    /**
     * 删除调拨主表
     * @param billNo
     */
    void deleteStkDbHead(String billNo);
    
    /**
     * 删除调拨明细表
     * @param billNo
     */
    void deleteStkDbBody(String billNo);
    
    /**
     * 查询调拨详情
     * @param billNo
     * @return List<Map<String, Object>>
     */
    List<Map<String, Object>> queryDbDetailByBillNo(String billNo);
}
