package com.addlove.service.goods.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.addlove.service.goods.model.OrdThApplyBodyModel;
import com.addlove.service.goods.model.OrdThApplyHeadModel;

/**
 * 门店退货申请dao层
 * @author lw
 *
 */
@Repository
public interface OrdThApplyDao {
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
}
