package com.addlove.service.goods.controller;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.addlove.service.goods.constants.GoodsResponseCode;
import com.addlove.service.goods.constants.GoodsCommonConstants.BillType;
import com.addlove.service.goods.constants.GoodsOrdJhConstants.SaveType;
import com.addlove.service.goods.constants.GoodsOrdJhConstants.YwType;
import com.addlove.service.goods.exception.ServiceException;
import com.addlove.service.goods.message.ResponseMessage;
import com.addlove.service.goods.model.PageModel;
import com.addlove.service.goods.model.ProPlanBodyModel;
import com.addlove.service.goods.model.ProPlanDoneModel;
import com.addlove.service.goods.model.ProPlanHeadModel;
import com.addlove.service.goods.model.ProPlanQueryPageModel;
import com.addlove.service.goods.model.StkStoreModel;
import com.addlove.service.goods.model.valid.CommonQueryDetailReq;
import com.addlove.service.goods.model.valid.ProPlanBodyReq;
import com.addlove.service.goods.model.valid.ProPlanHeadReq;
import com.addlove.service.goods.model.valid.ProPlanQueryPageReq;
import com.addlove.service.goods.service.GoodsCommonService;
import com.addlove.service.goods.service.ProPlanService;
import com.addlove.service.goods.util.DateUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;

/**
 * 生产计划控制层
 * @author lw
 *
 */
@Controller
@RequestMapping(value = "/proPlan")
public class ProPlanController extends BaseController{
    @Autowired
    private ProPlanService proPlanService;
    
    @Autowired
    private GoodsCommonService commonService;
    
    /**
     * 生产计划列表
     * @param req
     * @return ResponseMessage
     */
    @RequestMapping(value = "/queryProPlanPage", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage queryProPlanPage(@RequestBody @Valid ProPlanQueryPageReq req) {
        ProPlanQueryPageModel queryModel = new ProPlanQueryPageModel();
        queryModel.setPageNo(req.getPageNo());
        queryModel.setPageSize(req.getPageSize());
        queryModel.setOrgCode(req.getOrgCode());
        queryModel.setBillNo(req.getBillNo());
        if (StringUtils.isNotBlank(req.getStartDate())) {
            queryModel.setStartDate(req.getStartDate() + " 00:00:00");
        }
        if (StringUtils.isNotBlank(req.getEndDate())) {
            queryModel.setEndDate(req.getEndDate() + " 23:59:59");
        }
        queryModel.setDataStatus(req.getDataStatus());
        queryModel.setDoneStatus(req.getDoneStatus());
        List<ProPlanHeadModel> planList = this.proPlanService.queryProPlanPage(queryModel);
        PageModel pageModel = new PageModel();
        Page<ProPlanHeadModel> page = (Page<ProPlanHeadModel>) planList;
        pageModel.setPageNo(page.getPageNum());
        pageModel.setPageSize(page.getPageSize());
        pageModel.setResult(page.getResult());
        pageModel.setTotal(page.getTotal());
        return ResponseMessage.ok(pageModel);
    }
    
    /**
     * 生产完工列表
     * @param req
     * @return ResponseMessage
     */
    @RequestMapping(value = "/queryProPlanDonePage", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage queryProPlanDonePage(@RequestBody @Valid ProPlanQueryPageReq req) {
        ProPlanQueryPageModel queryModel = new ProPlanQueryPageModel();
        queryModel.setPageNo(req.getPageNo());
        queryModel.setPageSize(req.getPageSize());
        queryModel.setOrgCode(req.getOrgCode());
        queryModel.setBillNo(req.getBillNo());
        if (StringUtils.isNotBlank(req.getStartDate())) {
            queryModel.setStartDate(req.getStartDate() + " 00:00:00");
        }
        if (StringUtils.isNotBlank(req.getEndDate())) {
            queryModel.setEndDate(req.getEndDate() + " 23:59:59");
        }
        queryModel.setIsDone(req.getIsDone());
        queryModel.setDepId(req.getDepId());
        List<ProPlanDoneModel> doneList = this.proPlanService.queryProPlanDonePage(queryModel);
        PageModel pageModel = new PageModel();
        Page<ProPlanDoneModel> page = (Page<ProPlanDoneModel>) doneList;
        pageModel.setPageNo(page.getPageNum());
        pageModel.setPageSize(page.getPageSize());
        pageModel.setResult(page.getResult());
        pageModel.setTotal(page.getTotal());
        return ResponseMessage.ok(pageModel);
    }
    
    /**
     * 新增生产计划
     * @param req
     * @return ResponseMessage
     */
    @RequestMapping(value = "/addProPlan", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage addProPlan(@RequestBody @Valid ProPlanHeadReq req) {
        if (StringUtils.isBlank(req.getBillNo()) && req.getSaveType() == SaveType.EXEC_ACCOUNT.getValue()) {
            throw new ServiceException(GoodsResponseCode.SAVE_BEFORE_ACCOUNT.getCode(), 
                    GoodsResponseCode.SAVE_BEFORE_ACCOUNT.getMsg());
        }
        ProPlanHeadModel headModel = this.getProPlanHeadModel(req);
        if (req.getSaveType() == SaveType.SAVE.getValue()) {
            this.proPlanService.insertProPlanInfo(headModel);
        }else if (req.getSaveType() == SaveType.EXEC_ACCOUNT.getValue()) {
            //调用存储过程进行记账
            Map<String, Object> accountMap = new HashMap<String, Object>();
            accountMap.put("ps_BillNo", headModel.getBillNo());
            accountMap.put("ps_YwType", headModel.getYwType());
            accountMap.put("pi_UserId", headModel.getJzrId());
            accountMap.put("ps_UserCode", headModel.getJzrCode());
            accountMap.put("ps_UserName", headModel.getJzrName());
            accountMap.put("pd_JzDate", headModel.getJzDate());
            this.commonService.execAccountByCallProcedure(accountMap);
        }else {
            throw new ServiceException(GoodsResponseCode.BILL_OPRATE_ERROR.getCode(), 
                    GoodsResponseCode.BILL_OPRATE_ERROR.getMsg());
        }
        JSONObject json = new JSONObject();
        json.put("billNo", headModel.getBillNo());
        return ResponseMessage.ok(json);
    }
    
    /**
     * 编辑生产计划
     * @param req
     * @return ResponseMessage
     */
    @RequestMapping(value = "/editProPlan", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage editProPlan(@RequestBody @Valid ProPlanHeadReq req) {
        if (StringUtils.isBlank(req.getBillNo())) {
            throw new ServiceException(GoodsResponseCode.BILLNO_NOT_BLANK.getCode(), 
                    GoodsResponseCode.BILLNO_NOT_BLANK.getMsg());
        }
        ProPlanHeadModel headModel = this.getProPlanHeadModel(req);
        //编辑时先删除再插入
        if (req.getSaveType() == SaveType.SAVE.getValue()) {
            this.proPlanService.updateAllProPlanInfo(headModel);
        }else if (req.getSaveType() == SaveType.EXEC_ACCOUNT.getValue()) {
            //调用存储过程进行记账
            Map<String, Object> accountMap = new HashMap<String, Object>();
            accountMap.put("ps_BillNo", headModel.getBillNo());
            accountMap.put("ps_YwType", headModel.getYwType());
            accountMap.put("pi_UserId", headModel.getJzrId());
            accountMap.put("ps_UserCode", headModel.getJzrCode());
            accountMap.put("ps_UserName", headModel.getJzrName());
            accountMap.put("pd_JzDate", headModel.getJzDate());
            this.proPlanService.updateAllProPlanInfo(headModel);
            this.commonService.execAccountByCallProcedure(accountMap);
        }else {
            throw new ServiceException(GoodsResponseCode.BILL_OPRATE_ERROR.getCode(), 
                    GoodsResponseCode.BILL_OPRATE_ERROR.getMsg());
        }
        return ResponseMessage.ok();
    }
    
    /**
     * 删除调拨
     * @param req
     * @return ResponseMessage
     */
    @RequestMapping(value = "/delProPlan", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage delProPlan(@RequestBody @Valid CommonQueryDetailReq req) {
        this.proPlanService.deleteProPlanInfo(req.getBillNo());
        return ResponseMessage.ok();
    }
    
    /**
     * 查询生产计划详情
     * @param req
     * @return ResponseMessage
     */
    @RequestMapping(value = "/queryProPlanDetail", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage queryProPlanDetail(@RequestBody @Valid CommonQueryDetailReq req) {
        List<Map<String,Object>> resultList = this.proPlanService.queryProPlanDetail(req.getBillNo());
        JSONObject backJson = new JSONObject();
        if (null != resultList && !resultList.isEmpty()) {
            JSONObject headJson = new JSONObject();
            Map<String, Object> headMap = resultList.get(0);
            headJson.put("billNo", headMap.get("BILLNO"));
            headJson.put("orgCode", headMap.get("ORGCODE"));
            headJson.put("orgName", headMap.get("ORGNAME"));
            headJson.put("depId", headMap.get("DEPID"));
            headJson.put("depCode", headMap.get("DEPCODE"));
            headJson.put("depName", headMap.get("DEPNAME"));
            headJson.put("ckCode", headMap.get("CKCODE"));
            headJson.put("ckName", headMap.get("CKNAME"));
            headJson.put("requestDate", headMap.get("REQUESTDATE"));
            headJson.put("doneStatus", headMap.get("DONESTATUS"));
            headJson.put("lrDate", headMap.get("LRDATE"));
            headJson.put("userId", headMap.get("USERID"));
            headJson.put("userCode", headMap.get("USERCODE"));
            headJson.put("userName", headMap.get("USERNAME"));
            headJson.put("jzDate", headMap.get("JZDATE"));
            headJson.put("jzrId", headMap.get("JZRID"));
            headJson.put("jzrCode", headMap.get("JZRCODE"));
            headJson.put("jzrName", headMap.get("JZRNAME"));
            headJson.put("dataStatus", headMap.get("DATASTATUS"));
            headJson.put("remark", headMap.get("REMARK"));
            backJson.put("headInfo", headJson);
            JSONArray bodyArray = new JSONArray();
            for (Map<String, Object> map : resultList) {
                JSONObject bodyJson = new JSONObject();
                bodyJson.put("serialNo", map.get("SERIALNO"));
                bodyJson.put("pluId", map.get("PLUID"));
                bodyJson.put("pluCode", map.get("PLUCODE"));
                bodyJson.put("pluName", map.get("PLUNAME"));
                bodyJson.put("spec", map.get("SPEC"));
                bodyJson.put("unit", map.get("UNIT"));
                bodyJson.put("planCount", map.get("PLANCOUNT"));
                bodyJson.put("planTime", map.get("PLANTIME"));
                bodyJson.put("isDone", map.get("ISDONE"));
                bodyJson.put("produceCount", map.get("PRODUCECOUNT"));
                bodyJson.put("producer", map.get("PRODUCER"));
                bodyJson.put("produceTime", map.get("PRODUCETIME"));
                bodyJson.put("produceRemark", map.get("PRODUCEREMARK"));
                bodyJson.put("bomBillNo", map.get("BOMBILLNO"));
                bodyJson.put("remark", map.get("REMARK2"));
                bodyArray.add(bodyJson);
            }
            backJson.put("bodyInfo", bodyArray);
        }
        return ResponseMessage.ok(backJson);
    }
    
    /**
     * 封装生产计划数据
     * @param req
     * @return ProPlanHeadModel
     */
    private ProPlanHeadModel getProPlanHeadModel(ProPlanHeadReq req) {
        List<ProPlanBodyReq> bodyList = req.getBodyList();
        if (null == bodyList || bodyList.isEmpty()) {
            throw new ServiceException(GoodsResponseCode.SKU_NOT_BLANK.getCode(), 
                    GoodsResponseCode.SKU_NOT_BLANK.getMsg());
        }
        ProPlanHeadModel headModel = new ProPlanHeadModel();
        headModel.setOrgCode(req.getOrgCode());
        headModel.setOrgName(req.getOrgName());
        headModel.setDepId(req.getDepId());
        headModel.setDepCode(req.getDepCode());
        headModel.setDepName(req.getDepName());
        List<StkStoreModel> storeList = this.commonService.getStoreList(req.getOrgCode());
        if (null == storeList || storeList.isEmpty()) {
            throw new ServiceException(GoodsResponseCode.CK_NOT_BLANK.getCode(), 
                    GoodsResponseCode.CK_NOT_BLANK.getMsg());
        }
        headModel.setCkCode(storeList.get(0).getCkCode());
        headModel.setCkName(storeList.get(0).getCkName());
        headModel.setRequestDate(req.getRequestDate());
        headModel.setLrDate(DateUtil.getCurrentTime());
        headModel.setUserId(10000000041L);
        headModel.setUserCode("1");
        headModel.setUserName("超级户");
        headModel.setYwType(YwType.PRO_PLAN_ORDER.getValue());
        headModel.setRemark(req.getRemark());
        //调用存储过程生成计划单号
        if (StringUtils.isBlank(req.getBillNo())) {
            Map<String, Object> map = new HashMap<>();
            map.put("ps_BillType", BillType.PRO_PLAN_ORDER.getValue());
            String billNo = this.commonService.getBillNoByCallProcedure(map);
            headModel.setBillNo(billNo);
        }else {
            headModel.setBillNo(req.getBillNo());
        }
        if (req.getSaveType() == SaveType.EXEC_ACCOUNT.getValue()) {
            headModel.setJzDate(DateUtil.getCurrentTime());
            headModel.setJzrId(10000000041L);
            headModel.setJzrCode("1");
            headModel.setJzrName("超级户");
        }
        List<ProPlanBodyModel> bodyModels = new LinkedList<ProPlanBodyModel>();
        for (ProPlanBodyReq bodyReq : bodyList) {
            ProPlanBodyModel bodyModel = new ProPlanBodyModel();
            bodyModel.setBillNo(headModel.getBillNo());
            long serialNo = 1;
            bodyModel.setSerialNo(serialNo++);
            bodyModel.setPluId(bodyReq.getPluId());
            bodyModel.setPluCode(bodyReq.getPluCode());
            bodyModel.setPluName(bodyReq.getPluName());
            bodyModel.setSpec(bodyReq.getSpec());
            bodyModel.setUnit(bodyReq.getUnit());
            bodyModel.setPlanCount(bodyReq.getPlanCount());
            bodyModel.setPlanTime(bodyReq.getPlanTime());
            bodyModel.setRemark(bodyReq.getRemark());
            bodyModels.add(bodyModel);
        }
        headModel.setBodyList(bodyModels);
        return headModel;
    }
}
