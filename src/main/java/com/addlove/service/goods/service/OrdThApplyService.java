package com.addlove.service.goods.service;

import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
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
     * 调用存储过程生成退货差异单据号
     * @param map
     * @return billNo
     */
    @Transactional
    public String getBillNoByCallProcedure(Map<String, Object> map) {
        this.ordThApplyDao.getBillNoByCallProcedure(map);
        if (null == map || map.isEmpty()) {
            return "";
        }
        return map.get("ps_BillNo") != null ? map.get("ps_BillNo").toString() : "";
    }
}
