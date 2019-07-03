package com.addlove.service.goods.service;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.addlove.service.goods.dao.ProPlanDao;
import com.addlove.service.goods.model.ProPlanHeadModel;
import com.addlove.service.goods.model.ProPlanQueryPageModel;
import com.github.pagehelper.PageHelper;

/**
 * 生产计划逻辑层
 * @author lw
 *
 */
@Service
public class ProPlanService {
    @Autowired
    private ProPlanDao proPlanDao;
    
    /**
     * 生产计划列表
     * @param queryModel
     * @return List<ProPlanHeadModel>
     */
    public List<ProPlanHeadModel> queryProPlanPage(ProPlanQueryPageModel queryModel) {
        PageHelper.startPage(queryModel.getPageNo(), queryModel.getPageSize(), true);
        List<ProPlanHeadModel> planList = this.proPlanDao.queryProPlanPage(queryModel);
        if (null != planList && !planList.isEmpty()) {
            for (ProPlanHeadModel model : planList) {
                if (StringUtils.isNotBlank(model.getLrDate()) && model.getLrDate().length() > 19) {
                    model.setLrDate(model.getLrDate().substring(0, 19));
                }
                if (StringUtils.isNotBlank(model.getJzDate()) && model.getJzDate().length() > 19) {
                    model.setJzDate(model.getJzDate().substring(0, 19));
                }
            }
        }
        return planList;
    }
    
    /**
     * 插入生产计划信息
     * @param headModel
     */
    @Transactional
    public void insertProPlanInfo(ProPlanHeadModel headModel) {
        this.proPlanDao.insertProPlanHead(headModel);
        this.proPlanDao.insertProPlanBody(headModel.getBodyList());
    }
    
    /**
     * 编辑生产计划信息
     * @param headModel
     */
    @Transactional
    public void updateAllProPlanInfo(ProPlanHeadModel headModel) {
        this.proPlanDao.deleteProPlanHead(headModel.getBillNo());
        this.proPlanDao.deleteProPlanBody(headModel.getBillNo());
        this.proPlanDao.insertProPlanHead(headModel);
        this.proPlanDao.insertProPlanBody(headModel.getBodyList());
    }
    
    /**
     * 删除生产计划信息
     * @param billNo
     */
    @Transactional
    public void deleteProPlanInfo(String billNo) {
        this.proPlanDao.deleteProPlanHead(billNo);
        this.proPlanDao.deleteProPlanBody(billNo);
    }
    
    /**
     * 查询生产计划详情
     * @param billNo
     * @return List<Map<String, Object>>
     */
    public List<Map<String, Object>> queryProPlanDetail(String billNo) {
        return this.proPlanDao.queryProPlanDetail(billNo);
    }
}
