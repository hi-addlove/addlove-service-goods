package com.addlove.service.goods.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.addlove.service.goods.dao.OrdJhDao;
import com.addlove.service.goods.dao.OrdThApplyDao;
import com.addlove.service.goods.model.OrdJhBodyModel;
import com.addlove.service.goods.model.OrdJhHeadModel;
import com.addlove.service.goods.model.OrdJhQueryPageModel;
import com.addlove.service.goods.model.OrdThApplyBodyModel;
import com.addlove.service.goods.model.OrdThApplyHeadModel;
import com.github.pagehelper.PageHelper;

/**
 * 配送验收逻辑层
 * @author lw
 *
 */
@Service
public class OrdJhService {
    @Autowired
    private OrdJhDao ordJhDao;
    
    @Autowired
    private OrdThApplyDao ordThApplyDao;
    
    /**
     * 分页查询配送验收数据
     * @param queryModel
     * @return List<OrdJhHeadModel>
     */
    public List<OrdJhHeadModel> queryOrdJhHeadModelByPage(OrdJhQueryPageModel queryModel) {
        PageHelper.startPage(queryModel.getPageNo(), queryModel.getPageSize(), true);
        List<OrdJhHeadModel> ordJhHeadList = this.ordJhDao.queryOrdJhHeadModelByPage(queryModel);
        if (null != ordJhHeadList && !ordJhHeadList.isEmpty()) {
            for (OrdJhHeadModel model : ordJhHeadList) {
                if (StringUtils.isNotBlank(model.getJzDate()) && model.getJzDate().length() > 19) {
                    model.setJzDate(model.getJzDate().substring(0, 19));
                }
                if (StringUtils.isNotBlank(model.getPickDate()) && model.getPickDate().length() > 19) {
                    model.setPickDate(model.getPickDate().substring(0, 19));
                }
            }
        }
        return ordJhHeadList;
    }
    
    /**
     * 根据配送验收单号查询商品信息
     * @param billNo
     * @return List<OrdJhBodyModel>
     */
    public List<OrdJhBodyModel> queryBodysByBillNo(String billNo) {
        return this.ordJhDao.queryBodysByBillNo(billNo);
    }
    
    /**
     * 更新配送验收单：验收人、验收状态、送货确认时间
     * @param model
     */
    @Transactional
    public void updateJhHeadYsrCodeAndStatus(OrdJhHeadModel model) {
        this.ordJhDao.updateJhHeadYsrCodeAndStatus(model);
    }
    
    /**
     * 将配送差异插入退货申请单
     * @param headModel
     * @param bodyModelList
     */
    @Transactional
    public void insertOrdThApply(OrdThApplyHeadModel headModel, List<OrdThApplyBodyModel> bodyList) {
        this.ordThApplyDao.insertOrdThApplyHead(headModel);
        this.ordThApplyDao.insertOrdThApplyBody(bodyList);
    }
}
