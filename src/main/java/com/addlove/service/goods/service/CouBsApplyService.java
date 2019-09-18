package com.addlove.service.goods.service;

import java.util.Iterator;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.addlove.service.goods.constants.GoodsMdBsConstants.BsType;
import com.addlove.service.goods.dao.CouBsApplyDao;
import com.addlove.service.goods.model.BasFlContentModel;
import com.addlove.service.goods.model.CouBsApplyBodyModel;
import com.addlove.service.goods.model.CouBsApplyHeadModel;
import com.addlove.service.goods.model.CouBsApplyPageModel;
import com.github.pagehelper.PageHelper;

/**
 * 门店报损逻辑层
 * @author lw
 *
 */
@Service
public class CouBsApplyService {
    @Autowired
    private CouBsApplyDao couBsApplyDao;
    
    public List<CouBsApplyHeadModel> queryMdBsPage(CouBsApplyPageModel queryModel) {
        PageHelper.startPage(queryModel.getPageNo(), queryModel.getPageSize(), true);
        List<CouBsApplyHeadModel> bsList = this.couBsApplyDao.queryMdBsPage(queryModel);
        if (null != bsList && !bsList.isEmpty()) {
            Iterator<CouBsApplyHeadModel> iterator = bsList.iterator();
            if (iterator.hasNext()) {
                CouBsApplyHeadModel model = iterator.next();
                if (StringUtils.isNotBlank(model.getLrDate()) && model.getLrDate().length() > 19) {
                    model.setLrDate(model.getLrDate().substring(0, 19));
                }
                if (StringUtils.isNotBlank(model.getJzDate()) && model.getJzDate().length() > 19) {
                    model.setJzDate(model.getJzDate().substring(0, 19));
                }
                if (StringUtils.isNotBlank(model.getShDate()) && model.getShDate().length() > 19) {
                    model.setShDate(model.getShDate().substring(0, 19));
                }
                if (BsType.BS.getValue() == queryModel.getQueryType()) {
                    if (StringUtils.isNotBlank(model.getRemark()) && model.getRemark().contains("冲红原报损单号为")) {
                        iterator.remove();
                    }
                }else {
                    if (StringUtils.isBlank(model.getRemark()) || 
                            (StringUtils.isNotBlank(model.getRemark()) && !model.getRemark().contains("冲红原报损单号为"))) {
                        iterator.remove();
                    }
                }
            }
        }
        return bsList;
    }
    
    /**
     * 保存报损数据
     * @param headModel
     */
    @Transactional
    public void saveBs(CouBsApplyHeadModel headModel) {
        this.couBsApplyDao.deleteBsHead(headModel.getBillNo());
        this.couBsApplyDao.deleteBsBody(headModel.getBillNo());
        this.couBsApplyDao.insertBsHead(headModel);
        this.couBsApplyDao.insertBsBodys(headModel.getBodyList());
    }
    
    /**
     * 查询报损详情
     * @param billNo
     * @return CouBsApplyHeadModel
     */
    public CouBsApplyHeadModel queryMdBsDetails(String billNo) {
        CouBsApplyHeadModel bsHead = this.couBsApplyDao.getBsHead(billNo);
        List<CouBsApplyBodyModel> bodyList = this.couBsApplyDao.getBsBodys(billNo);
        bsHead.setBodyList(bodyList);
        return bsHead;
    }
    
    /**
     * 删除报损数据
     * @param billNo
     */
    @Transactional
    public void deleteBs(String billNo) {
        this.couBsApplyDao.deleteBsHead(billNo);
        this.couBsApplyDao.deleteBsBody(billNo);
    }
    
    /**
     * 获取分类内容
     * @param flCode
     * @return List<BasFlContentModel>
     */
    public List<BasFlContentModel> getFls(String flCode) {
        return this.couBsApplyDao.getFls(flCode);
    }
    
    /**
     * 获取部门报损单号
     * @param depId
     * @param billNo
     * @return List<CouBsApplyHeadModel>
     */
    public List<CouBsApplyHeadModel> getBillsByDep(Long depId, String billNo) {
        List<CouBsApplyHeadModel> bills = this.couBsApplyDao.getBillsByDep(depId, billNo);
        if (null != bills && !bills.isEmpty()) {
            Iterator<CouBsApplyHeadModel> iterator = bills.iterator();
            if (iterator.hasNext()) {
                CouBsApplyHeadModel model = iterator.next();
                if (StringUtils.isNotBlank(model.getRemark()) && model.getRemark().contains("冲红原报损单号为")) {
                    iterator.remove();
                }
            }
        }
        return bills;
    }
}
