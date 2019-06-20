package com.addlove.service.goods.service;

import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.addlove.service.goods.dao.OrdThApplyDao;
import com.addlove.service.goods.model.OrdThApplyBodyModel;
import com.addlove.service.goods.model.OrdThApplyHeadModel;
import com.addlove.service.goods.model.OrdThQueryPageModel;
import com.github.pagehelper.PageHelper;

/**
 * 退货申请单逻辑层
 * @author lw
 *
 */
@Service
public class OrdThApplyService {
    /**OrdThApplyService类日志 */
    private static final Logger LOGGER = LoggerFactory.getLogger(OrdThApplyService.class);
    
    @Autowired
    private OrdThApplyDao ordThApplyDao;
    
    public List<OrdThApplyHeadModel> queryOrdThHeadModelByPage(OrdThQueryPageModel queryModel) {
        PageHelper.startPage(queryModel.getPageNo(), queryModel.getPageSize(), true);
        List<OrdThApplyHeadModel> ordThHeadList = this.ordThApplyDao.queryOrdThHeadModelByPage(queryModel);
        if (null != ordThHeadList && !ordThHeadList.isEmpty()) {
            for (OrdThApplyHeadModel model : ordThHeadList) {
                if (StringUtils.isNotBlank(model.getTjDate()) && model.getTjDate().length() > 19) {
                    model.setTjDate(model.getTjDate().substring(0, 19));
                }
            }
        }
        return ordThHeadList;
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
    
    /**
     * 根据退货差异单号查询退货单详情
     * @param billNo
     * @return List<OrdThApplyBodyModel>
     */
    public List<Map<String, Object>> queryThBodysByBillNo(String billNo) {
        return this.ordThApplyDao.queryThBodysByBillNo(billNo);
    }
}
