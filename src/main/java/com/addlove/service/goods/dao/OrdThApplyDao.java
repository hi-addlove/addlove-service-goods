package com.addlove.service.goods.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.addlove.service.goods.model.OrdThApplyBodyModel;
import com.addlove.service.goods.model.OrdThApplyHeadModel;
import com.addlove.service.goods.model.OrdThApplyQueryPageModel;

/**
 * 差异单dao层
 * @author lw
 *
 */
@Repository
public interface OrdThApplyDao {
    /**
     * 分页查询差异单信息
     * @param queryModel
     * @return List<OrdThApplyHeadModel>
     */
    List<OrdThApplyHeadModel> queryOrdThHeadModelByPage(@Param("queryModel") OrdThApplyQueryPageModel queryModel);
    
    /**
     * 将配送差异插入退货申请单:头部信息
     * @param headModel
     */
    void insertOrdThApplyHead(OrdThApplyHeadModel headModel);
    
    /**
     * 将配送差异插入退货申请单:明细信息
     * @param bodyList
     */
    void insertOrdThApplyBody(@Param("bodys") List<OrdThApplyBodyModel> bodyList);
    
    /**
     * 根据退货差异单号查询退货单详情
     * @param billNo
     * @return List<OrdThApplyBodyModel>
     */
    List<Map<String, Object>> queryThApplyBodysByBillNo(String billNo);
}
