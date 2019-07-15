package com.addlove.service.goods.service;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.addlove.service.goods.dao.OrdThDao;
import com.addlove.service.goods.model.OrdThHeadModel;
import com.addlove.service.goods.model.OrdThQueryPageModel;
import com.github.pagehelper.PageHelper;

/**
 * 退货单逻辑层
 * @author lw
 *
 */
@Service
public class OrdThService {
    @Autowired
    private OrdThDao ordThDao;
    
    /**
     * 退货单分页列表
     * @param queryModel
     * @return List<OrdThHeadModel>
     */
    public List<OrdThHeadModel> queryOrdThPage(OrdThQueryPageModel queryModel) {
        PageHelper.startPage(queryModel.getPageNo(), queryModel.getPageSize(), true);
        List<OrdThHeadModel> ordThHeadList = this.ordThDao.queryOrdThPage(queryModel);
        if (null != ordThHeadList && !ordThHeadList.isEmpty()) {
            for (OrdThHeadModel model : ordThHeadList) {
                if (StringUtils.isNotBlank(model.getLrDate()) && model.getLrDate().length() > 19) {
                    model.setLrDate(model.getLrDate().substring(0, 19));
                }
                if (StringUtils.isNotBlank(model.getTjDate()) && model.getTjDate().length() > 19) {
                    model.setTjDate(model.getTjDate().substring(0, 19));
                }
                if (StringUtils.isNotBlank(model.getJzDate()) && model.getJzDate().length() > 19) {
                    model.setJzDate(model.getJzDate().substring(0, 19));
                }
            }
        }
        return ordThHeadList;
    }
    
    /**
     * 插入退货单
     * @param headModel
     */
    @Transactional
    public void insertOrderTh(OrdThHeadModel headModel) {
        this.ordThDao.insertOrdThHead(headModel);
        this.ordThDao.insertOrdThBody(headModel.getBodyList());
    }
    
    /**
     * 编辑时点击“保存”整个退货信息
     * @param headModel
     */
    @Transactional
    public void updateAllThInfo(OrdThHeadModel headModel) {
        this.ordThDao.deleteThHeadModel(headModel.getBillNo());
        this.ordThDao.deleteThBodyModel(headModel.getBillNo());
        this.ordThDao.insertOrdThHead(headModel);
        this.ordThDao.insertOrdThBody(headModel.getBodyList());
    }
    
    /**
     * 查询退货单详情
     * @param billNo
     * @return List<Map<String, Object>>
     */
    public List<Map<String, Object>> queryThDetailsByBillNo(String billNo) {
        return this.ordThDao.queryThDetailsByBillNo(billNo);
    }
    
    /**
     * 删除退货单数据
     * @param billNo
     */
    @Transactional
    public void deleteThData(String billNo) {
       this.ordThDao.deleteThHeadModel(billNo);
       this.ordThDao.deleteThBodyModel(billNo);
    }
}
