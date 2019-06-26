package com.addlove.service.goods.service;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.addlove.service.goods.dao.StkDbDao;
import com.addlove.service.goods.model.StkDbHeadModel;
import com.addlove.service.goods.model.StkDbQueryPageModel;
import com.github.pagehelper.PageHelper;

/**
 * 调拨逻辑层
 * @author lw
 *
 */
@Service
public class StkDbService {
    @Autowired
    private StkDbDao stkDbDao;
    
    /**
     * 分页查询调拨
     * @param queryModel
     * @return List<StkDbHeadModel>
     */
    public List<StkDbHeadModel> queryDbPage(StkDbQueryPageModel queryModel) {
        PageHelper.startPage(queryModel.getPageNo(), queryModel.getPageSize(), true);
        List<StkDbHeadModel> dbList = this.stkDbDao.queryDbPage(queryModel);
        if (null != dbList && !dbList.isEmpty()) {
            for (StkDbHeadModel model : dbList) {
                if (StringUtils.isNotBlank(model.getLrDate()) && model.getLrDate().length() > 19) {
                    model.setLrDate(model.getLrDate().substring(0, 19));
                }
                if (StringUtils.isNotBlank(model.getJzDate()) && model.getJzDate().length() > 19) {
                    model.setJzDate(model.getJzDate().substring(0, 19));
                }
            }
        }
        return dbList;
    }
    
    /**
     * 插入调拨信息
     * @param headModel
     */
    @Transactional
    public void insertDbInfo(StkDbHeadModel headModel) {
        this.stkDbDao.insertStkDbHead(headModel);
        this.stkDbDao.insertStkDbBody(headModel.getBodyList());
    }
    
    /**
     * 删除调拨信息
     * @param billNo
     */
    @Transactional
    public void deleteDbInfo(String billNo) {
        this.stkDbDao.deleteStkDbHead(billNo);
        this.stkDbDao.deleteStkDbBody(billNo);
    }
    
    /**
     * 编辑调拨信息
     * @param headModel
     */
    @Transactional
    public void updateAllDbInfo(StkDbHeadModel headModel) {
        this.stkDbDao.deleteStkDbHead(headModel.getBillNo());
        this.stkDbDao.deleteStkDbBody(headModel.getBillNo());
        this.stkDbDao.insertStkDbHead(headModel);
        this.stkDbDao.insertStkDbBody(headModel.getBodyList());
    }
    
    /**
     * 查询调拨详情
     * @param billNo
     * @return List<Map<String, Object>>
     */
    public List<Map<String, Object>> queryDbDetailByBillNo(String billNo) {
        return this.stkDbDao.queryDbDetailByBillNo(billNo);
    }
}
