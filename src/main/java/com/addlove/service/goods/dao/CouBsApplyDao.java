package com.addlove.service.goods.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.addlove.service.goods.model.BasFlContentModel;
import com.addlove.service.goods.model.CouBsApplyBodyModel;
import com.addlove.service.goods.model.CouBsApplyHeadModel;
import com.addlove.service.goods.model.CouBsApplyPageModel;

/**
 * 门店报损dao层
 * @author lw
 *
 */
@Repository
public interface CouBsApplyDao {
    /**
     * 查询报损列表
     * @param queryModel
     * @return List<CouBsApplyHeadModel>
     */
    List<CouBsApplyHeadModel> queryMdBsPage(@Param("queryModel") CouBsApplyPageModel queryModel);
    
    /**
     * 插入报损主表
     * @param headModel
     */
    void insertBsHead(CouBsApplyHeadModel headModel);
    
    /**
     * 插入报损明细
     * @param headModel
     */
    void insertBsBodys(@Param("bodys") List<CouBsApplyBodyModel> bodyList);
    
    /**
     * 通过单号获取报损主表
     * @param billNo
     * @return CouBsApplyHeadModel
     */
    CouBsApplyHeadModel getBsHead(String billNo);
    
    /**
     * 通过单号获取报损明细
     * @param billNo
     * @return List<CouBsApplyBodyModel>
     */
    List<CouBsApplyBodyModel> getBsBodys(String billNo);
    
    /**
     * 删除报损主表
     * @param billNo
     */
    void deleteBsHead(String billNo);
    
    /**
     * 删除报损明细
     * @param billNo
     */
    void deleteBsBody(String billNo);
    
    /**
     * 获取分类内容
     * @param flCode
     * @return List<BasFlContentModel>
     */
    List<BasFlContentModel> getFls(String flCode);
}
