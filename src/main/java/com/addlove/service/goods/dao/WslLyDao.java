package com.addlove.service.goods.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.addlove.service.goods.model.BasFlContentModel;
import com.addlove.service.goods.model.SkuPluModel;
import com.addlove.service.goods.model.WslLyBodyModel;
import com.addlove.service.goods.model.WslLyHeadModel;
import com.addlove.service.goods.model.WslLyPageModel;

/**
 * 领用单dao层
 * @author lw
 *
 */
@Repository
public interface WslLyDao {
    /**
     * 查询领用单列表
     * @param queryModel
     * @return List<WslLyHeadModel>
     */
    List<WslLyHeadModel> queryLyPage(@Param("queryModel") WslLyPageModel queryModel);
    
    /**
     * 插入领用主表
     * @param headModel
     */
    void insertLyHead(WslLyHeadModel headModel);
    
    /**
     * 插入领用明细表
     * @param bodyList
     */
    void insertLyBodys(@Param("bodys") List<WslLyBodyModel> bodyList);
    
    /**
     * 查询领用单主表
     * @param billNo
     * @return WslLyHeadModel
     */
    WslLyHeadModel getLyHead(String billNo);
    
    /**
     * 查询领用单明细表
     * @param billNo
     * @return List<WslLyBodyModel>
     */
    List<WslLyBodyModel> getLyBodys(String billNo);
    
    /**
     * 删除领用单主表
     * @param billNo
     */
    void delLyHead(String billNo);
    
    /**
     * 删除领用单明细表
     * @param billNo
     */
    void delLyBodys(String billNo);
    
    /**
     * 获取分类内容
     * @param flCode
     * @return List<BasFlContentModel>
     */
    List<BasFlContentModel> getFls(String flCode);
    
    /**
     * 获取领用单可用商品
     * @param orgCode
     * @param depId
     * @return List<SkuPluModel>
     */
    List<SkuPluModel> getLySkus(@Param("orgCode")String orgCode, @Param("depId")Long depId);
    
    /**
     * 获取多部门领用商品
     * @param orgCode
     * @param depId
     * @return List<SkuPluModel>
     */
    List<SkuPluModel> getOtherDeptLySkus(@Param("orgCode")String orgCode, @Param("depId")Long depId);
}
