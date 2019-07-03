package com.addlove.service.goods.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.addlove.service.goods.model.ProPlanBodyModel;
import com.addlove.service.goods.model.ProPlanDoneModel;
import com.addlove.service.goods.model.ProPlanHeadModel;
import com.addlove.service.goods.model.ProPlanQueryPageModel;

/**
 * 生产计划dao层
 * @author lw
 *
 */
@Repository
public interface ProPlanDao {
    /**
     * 生产计划列表
     * @param queryModel
     * @return List<ProPlanHeadModel>
     */
    List<ProPlanHeadModel> queryProPlanPage(@Param("queryModel") ProPlanQueryPageModel queryModel);
    
    /**
     * 生产完工列表
     * @param queryModel
     * @return List<ProPlanDoneModel>
     */
    List<ProPlanDoneModel> queryProPlanDonePage(@Param("queryModel") ProPlanQueryPageModel queryModel);
    
    /**
     * 插入生产计划主表
     * @param headModel
     */
    void insertProPlanHead(ProPlanHeadModel headModel);
    
    /**
     * 插入生产计划明细表
     * @param bodyList
     */
    void insertProPlanBody(@Param("bodys") List<ProPlanBodyModel> bodyList);
    
    /**
     * 删除生产计划主表
     * @param billNo
     */
    void deleteProPlanHead(String billNo);
    
    /**
     * 删除生产计划明细表
     * @param billNo
     */
    void deleteProPlanBody(String billNo);
    
    /**
     * 查询生产计划详情
     * @param billNo
     * @return List<Map<String, Object>>
     */
    List<Map<String, Object>> queryProPlanDetail(String billNo);
}
