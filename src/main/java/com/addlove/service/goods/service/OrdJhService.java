package com.addlove.service.goods.service;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.addlove.service.goods.dao.OrdJhDao;
import com.addlove.service.goods.model.OrdJhBodyModel;
import com.addlove.service.goods.model.OrdJhHeadModel;
import com.addlove.service.goods.model.OrdJhQueryPageModel;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;

/**
 * 配送验收/无采购验收逻辑层
 * @author lw
 *
 */
@Service
public class OrdJhService {
    /**OrdJhService类日志 */
    private static final Logger LOGGER = LoggerFactory.getLogger(OrdJhService.class);
    
    @Autowired
    private OrdJhDao ordJhDao;
    
    /**
     * 分页查询配送验收数据
     * @param queryModel
     * @return List<JSONObject>
     */
    public List<JSONObject> queryOrdJhHeadModelByPage(OrdJhQueryPageModel queryModel) {
        PageHelper.startPage(queryModel.getPageNo(), queryModel.getPageSize(), true);
        List<Map<String, Object>> billNos = new LinkedList<Map<String, Object>>();
        List<OrdJhHeadModel> ordJhHeadList = this.ordJhDao.queryOrdJhHeadModelByPage(queryModel);
        List<JSONObject> backList = new LinkedList<JSONObject>();
        if (null != ordJhHeadList && !ordJhHeadList.isEmpty()) {
            for (OrdJhHeadModel model : ordJhHeadList) {
                Map<String, Object> billNoMap = new HashMap<String, Object>();
                billNoMap.put("billNo", model.getRefBillNo());
                billNos.add(billNoMap);
                if (StringUtils.isNotBlank(model.getLrDate()) && model.getLrDate().length() > 19) {
                    model.setLrDate(model.getLrDate().substring(0, 19));
                }
                if (StringUtils.isNotBlank(model.getJzDate()) && model.getJzDate().length() > 19) {
                    model.setJzDate(model.getJzDate().substring(0, 19));
                }
                if (StringUtils.isNotBlank(model.getPickDate()) && model.getPickDate().length() > 19) {
                    model.setPickDate(model.getPickDate().substring(0, 19));
                }
                JSONObject jsonObject = (JSONObject) JSONObject.toJSON(model);
                backList.add(jsonObject);
            }
        }
        //查询出所有分货单的部门信息
        if (null != billNos && !billNos.isEmpty()) {
            List<Map<String, Object>> fhDepts = this.ordJhDao.queryFhDepCode(billNos);
            if (null != fhDepts && !fhDepts.isEmpty()) {
                for (Map<String, Object> dept : fhDepts) {
                    for (JSONObject json : backList) {
                        if (json.getString("refBillNo").equals(dept.get("BILLNO"))) {
                            json.put("fhDepId", dept.get("DEPID"));
                            json.put("fhDepCode", dept.get("DEPCODE"));
                            json.put("fhDepName", dept.get("DEPNAME"));
                        }
                    }
                }
            }
        }else {
            for (JSONObject json : backList) {
                json.put("fhDepId", "");
                json.put("fhDepCode", "");
                json.put("fhDepName", "");
            }
        }
        return backList;
    }
    
    /**
     * 根据配送验收单号查询商品信息
     * @param billNo
     * @return List<OrdJhBodyModel>
     */
    public List<Map<String, Object>> queryBodysByBillNo(String billNo) {
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
     * 更新配送验收单明细配送收货数量
     * @param jhModelList
     */
    @Transactional
    public void updateJhBodyPsShCount(List<OrdJhBodyModel> jhModelList) {
        this.ordJhDao.updateJhBodyPsShCount(jhModelList);
    }
    
    /**
     * 插入验收单
     * @param headModel
     */
    @Transactional
    public void insertOrdJh(OrdJhHeadModel headModel) {
        this.ordJhDao.insertOrdJhHead(headModel);
        this.ordJhDao.insertOrdJhBody(headModel.getBodyList());
    }
    
    /**
     * 编辑时点击“保存”整个验收信息
     * @param headModel
     */
    @Transactional
    public void updateAllJhInfo(OrdJhHeadModel headModel) {
        this.ordJhDao.deleteJhHeadModel(headModel.getBillNo());
        this.ordJhDao.deleteJhBodyModel(headModel.getBillNo());
        this.ordJhDao.insertOrdJhHead(headModel);
        this.ordJhDao.insertOrdJhBody(headModel.getBodyList());
    }
    
    /**
     * 删除验收单数据
     * @param billNo
     */
    @Transactional
    public void deleteJhData(String billNo) {
        this.ordJhDao.deleteJhHeadModel(billNo);
        this.ordJhDao.deleteJhBodyModel(billNo);
    }
    
    /**
     * 通过单号模糊搜索验收单据
     * @param billNo
     * @return List<OrdJhHeadModel>
     */
    public List<OrdJhHeadModel> searchOrdJhByBillNo(String billNo) {
        return this.ordJhDao.searchOrdJhByBillNo(billNo);
    }
}
