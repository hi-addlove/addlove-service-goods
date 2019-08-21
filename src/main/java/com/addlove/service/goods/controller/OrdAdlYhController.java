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

import com.addlove.service.goods.constants.GoodsAdlYhConstants.DataStatus;
import com.addlove.service.goods.constants.GoodsAdlYhConstants.FlCode;
import com.addlove.service.goods.constants.GoodsAdlYhConstants.SaveType;
import com.addlove.service.goods.constants.GoodsAdlYhConstants.YwStatus;
import com.addlove.service.goods.constants.GoodsAdlYhConstants.YwType;
import com.addlove.service.goods.constants.GoodsResponseCode;
import com.addlove.service.goods.exception.ServiceException;
import com.addlove.service.goods.message.ResponseMessage;
import com.addlove.service.goods.model.BasFlContentModel;
import com.addlove.service.goods.model.OrdAdlYhBodyModel;
import com.addlove.service.goods.model.OrdAdlYhHeadModel;
import com.addlove.service.goods.model.OrdAdlYhPageModel;
import com.addlove.service.goods.model.OrdYhTempletHeadModel;
import com.addlove.service.goods.model.OrgManageModel;
import com.addlove.service.goods.model.PageModel;
import com.addlove.service.goods.model.valid.AdlYhSkuReq;
import com.addlove.service.goods.model.valid.CommonQueryDetailReq;
import com.addlove.service.goods.model.valid.OrdAdlYhBodyReq;
import com.addlove.service.goods.model.valid.OrdAdlYhHeadReq;
import com.addlove.service.goods.model.valid.OrdAdlYhPageReq;
import com.addlove.service.goods.service.CouBsApplyService;
import com.addlove.service.goods.service.GoodsCommonService;
import com.addlove.service.goods.service.OrdAdlYhService;
import com.addlove.service.goods.util.DateUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;

/**
 * ADL要货控制层
 * @author lw
 *
 */
@Controller
@RequestMapping(value = "/adlYh")
public class OrdAdlYhController extends BaseController{
    @Autowired
    private OrdAdlYhService ordAdlYhService;
    
    @Autowired
    private GoodsCommonService commonService;
    
    @Autowired
    private CouBsApplyService couBsApplyService;
    
    /**
     * 查询要货列表
     * @param req
     * @return ResponseMessage
     */
    @RequestMapping(value = "/queryYhPage", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage queryYhPage(@RequestBody @Valid OrdAdlYhPageReq req) {
        OrdAdlYhPageModel queryModel = new OrdAdlYhPageModel();
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
        queryModel.setYwStatus(req.getYwStatus());
        queryModel.setDepId(req.getDepId());
        List<OrdAdlYhHeadModel> yhList = this.ordAdlYhService.queryYhPage(queryModel);
        Page<OrdAdlYhHeadModel> page = (Page<OrdAdlYhHeadModel>) yhList;
        PageModel pageModel = new PageModel();
        pageModel.setPageNo(page.getPageNum());
        pageModel.setPageSize(page.getPageSize());
        pageModel.setResult(page.getResult());
        pageModel.setTotal(page.getTotal());
        return ResponseMessage.ok(pageModel);
    }
    
    /**
     * 新增要货
     * @param req
     * @return ResponseMessage
     */
    @RequestMapping(value = "/addYh", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage addYh(@RequestBody @Valid OrdAdlYhHeadReq req) {
        if (StringUtils.isBlank(req.getBillNo()) && req.getSaveType() == SaveType.EXEC_ACCOUNT.getValue()) {
            throw new ServiceException(GoodsResponseCode.SAVE_BEFORE_ACCOUNT.getCode(), 
                    GoodsResponseCode.SAVE_BEFORE_ACCOUNT.getMsg());
        }
        OrdAdlYhHeadModel headModel = this.getYhHeadModel(req);
        if (req.getSaveType() == SaveType.SAVE.getValue()) {
            this.ordAdlYhService.addYh(headModel);
        }else if (req.getSaveType() == SaveType.EXEC_ACCOUNT.getValue()) {
            //调用存储过程进行记账
            Map<String, Object> accountMap = new HashMap<String, Object>();
            accountMap.put("ps_BillNo", headModel.getBillNo());
            accountMap.put("ps_YwType", headModel.getYwType());
            accountMap.put("pi_UserId", headModel.getJzrId());
            accountMap.put("ps_UserCode", headModel.getJzrCode());
            accountMap.put("ps_UserName", headModel.getJzrName());
            accountMap.put("ps_Date", headModel.getJzDate());
            this.ordAdlYhService.execYhAccountProcedure(accountMap);
        }else {
            throw new ServiceException(GoodsResponseCode.BILL_OPRATE_ERROR.getCode(), 
                    GoodsResponseCode.BILL_OPRATE_ERROR.getMsg());
        }
        JSONObject json = new JSONObject();
        json.put("billNo", headModel.getBillNo());
        return ResponseMessage.ok(json);
    }
    
    /**
     * 编辑要货
     * @param req
     * @return ResponseMessage
     */
    @RequestMapping(value = "/editYh", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage editYh(@RequestBody @Valid OrdAdlYhHeadReq req) {
        if (StringUtils.isBlank(req.getBillNo())) {
            throw new ServiceException(GoodsResponseCode.BILLNO_NOT_BLANK.getCode(), 
                    GoodsResponseCode.BILLNO_NOT_BLANK.getMsg());
        }
        OrdAdlYhHeadModel headModel = this.getYhHeadModel(req);
        if (req.getSaveType() == SaveType.SAVE.getValue()) {
            this.ordAdlYhService.editYh(headModel);
        }else if (req.getSaveType() == SaveType.EXEC_ACCOUNT.getValue()) {
            //调用存储过程进行记账
            Map<String, Object> accountMap = new HashMap<String, Object>();
            accountMap.put("ps_BillNo", headModel.getBillNo());
            accountMap.put("ps_YwType", headModel.getYwType());
            accountMap.put("pi_UserId", headModel.getJzrId());
            accountMap.put("ps_UserCode", headModel.getJzrCode());
            accountMap.put("ps_UserName", headModel.getJzrName());
            accountMap.put("ps_Date", headModel.getJzDate());
            this.ordAdlYhService.execYhAccountProcedure(accountMap);
        }else {
            throw new ServiceException(GoodsResponseCode.BILL_OPRATE_ERROR.getCode(), 
                    GoodsResponseCode.BILL_OPRATE_ERROR.getMsg());
        }
        JSONObject json = new JSONObject();
        json.put("billNo", headModel.getBillNo());
        return ResponseMessage.ok(json);
    }
    
    /**
     * 删除要货
     * @param req
     * @return ResponseMessage
     */
    @RequestMapping(value = "/delYh", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage delYh(@RequestBody @Valid CommonQueryDetailReq req) {
        this.ordAdlYhService.delYh(req.getBillNo());
        return ResponseMessage.ok();
    }
    
    /**
     * 查询要货详情
     * @param req
     * @return ResponseMessage
     */
    @RequestMapping(value = "/queryYhDetails", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage queryYhDetails(@RequestBody @Valid CommonQueryDetailReq req) {
        OrdAdlYhHeadModel model = this.ordAdlYhService.queryYhDetails(req.getBillNo());
        return ResponseMessage.ok(model);
    }
    
    /**
     * 获取模板编码
     * @param req
     * @return ResponseMessage
     */
    @RequestMapping(value = "/getYhModelCode", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage getYhModelCode(@RequestBody @Valid AdlYhSkuReq req) {
        if (StringUtils.isBlank(req.getOrgCode())) {
            throw new ServiceException(GoodsResponseCode.ORGCODE_NOT_BLANK.getCode(), 
                    GoodsResponseCode.ORGCODE_NOT_BLANK.getMsg());
        }
        List<OrdYhTempletHeadModel> templets = this.ordAdlYhService.getTempletsByOrgCode(req.getOrgCode());
        return ResponseMessage.ok(templets);
    }
    
    /**
     * 获取要货波次
     * @return ResponseMessage
     */
    @RequestMapping(value = "/getYhBc", method = RequestMethod.GET)
    @ResponseBody
    public ResponseMessage getYhBc() {
        List<BasFlContentModel> models = this.couBsApplyService.getFls(FlCode.YH_BC.getValue());
        return ResponseMessage.ok(models);
    }
    
    /**
     * 获取要货商品
     * @param req
     * @return ResponseMessage
     */
    @RequestMapping(value = "/getYhSkuList", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage getYhSkuList(@RequestBody @Valid AdlYhSkuReq req) {
        if (StringUtils.isBlank(req.getOrgCode())) {
            throw new ServiceException(GoodsResponseCode.ORGCODE_NOT_BLANK.getCode(), 
                    GoodsResponseCode.ORGCODE_NOT_BLANK.getMsg());
        }
        if (null == req.getDepId()) {
            throw new ServiceException(GoodsResponseCode.DEP_ID_NOT_BLANK.getCode(), 
                    GoodsResponseCode.DEP_ID_NOT_BLANK.getMsg());
        }
        if (StringUtils.isBlank(req.getModelCode())) {
            throw new ServiceException(GoodsResponseCode.MODEL_CODE_NOT_BLANK.getCode(), 
                    GoodsResponseCode.MODEL_CODE_NOT_BLANK.getMsg());
        }
        if (StringUtils.isBlank(req.getYhBc())) {
            throw new ServiceException(GoodsResponseCode.YH_BC_NOT_BLANK.getCode(), 
                    GoodsResponseCode.YH_BC_NOT_BLANK.getMsg());
        }
        JSONArray backArray = this.ordAdlYhService.getYhSkuList(req.getOrgCode(), req.getDepId(), req.getModelCode());
        return ResponseMessage.ok(backArray);
    }
    
    /**
     * 封装要货单数据
     * @param req
     * @return OrdAdlYhHeadModel
     */
    private OrdAdlYhHeadModel getYhHeadModel(OrdAdlYhHeadReq req) {
        OrdAdlYhHeadModel headModel = new OrdAdlYhHeadModel();
        List<OrdAdlYhBodyReq> bodyList = req.getBodyList();
        if (null == bodyList || bodyList.isEmpty()) {
            throw new ServiceException(GoodsResponseCode.SKU_NOT_BLANK.getCode(), 
                    GoodsResponseCode.SKU_NOT_BLANK.getMsg());
        }
        headModel.setLrDate(DateUtil.getCurrentTime());
        headModel.setUserId(10000000041L);
        headModel.setUserCode("1");
        headModel.setUserName("超级户");
        headModel.setYwType(YwType.MD_YH.getValue());
        headModel.setOrgCode(req.getOrgCode());
        headModel.setOrgName(req.getOrgName());
        headModel.setDepId(req.getDepId());
        headModel.setDepCode(req.getDepCode());
        headModel.setDepName(req.getDepName());
        OrgManageModel orgModel = this.commonService.getOrgModel(req.getOrgCode());
        headModel.setZbOrgCode(orgModel.getZbOrgCode());
        OrgManageModel zbOrgModel = this.commonService.getOrgModel(headModel.getZbOrgCode());
        headModel.setZbOrgName(zbOrgModel.getOrgName());
        headModel.setSdDate(req.getSdDate());
        headModel.setShAddr(req.getShAddr());
        headModel.setYhCount(req.getYhCount());
        headModel.setYwStatus(YwStatus.NOT_CONFIRM.getValue());
        headModel.setDataStatus(DataStatus.ENTRY.getValue());
        headModel.setYhTotal(req.getYhTotal());
        headModel.setRemark(req.getRemark());
        headModel.setModelCode(req.getModelCode());
        headModel.setYhBc(req.getYhBc());
        if (req.getSaveType() == SaveType.EXEC_ACCOUNT.getValue()) {
            headModel.setJzDate(DateUtil.getCurrentTime());
            headModel.setJzrId(10000000041L);
            headModel.setJzrCode("1");
            headModel.setJzrName("超级户");
        }
        if (StringUtils.isBlank(req.getBillNo())) {
            Map<String, Object> map = new HashMap<>();
            map.put("ps_BillType", YwType.MD_YH.getValue());
            String billNo = this.commonService.getBillNoByCallProcedure(map);
            headModel.setBillNo(billNo);
        }else {
            headModel.setBillNo(req.getBillNo());
        }
        List<OrdAdlYhBodyModel> bodyModels = new LinkedList<OrdAdlYhBodyModel>();
        long serialNo = 1;
        for (OrdAdlYhBodyReq bodyReq : bodyList) {
            OrdAdlYhBodyModel bodyModel = new OrdAdlYhBodyModel();
            bodyModel.setBillNo(headModel.getBillNo());
            bodyModel.setSerialNo(serialNo++);
            bodyModel.setPluId(bodyReq.getPluId());
            bodyModel.setPluCode(bodyReq.getPluCode());
            bodyModel.setPluName(bodyReq.getPluName());
            bodyModel.setExPluCode("*");
            bodyModel.setBarCode(bodyReq.getBarCode());
            bodyModel.setSpec(bodyReq.getSpec());
            bodyModel.setUnit(bodyReq.getUnit());
            bodyModel.setYhCount(bodyReq.getYhCount());
            bodyModel.setQrCount(bodyReq.getQrCount());
            bodyModel.setPrice(bodyReq.getPrice());
            bodyModel.setsTotal(bodyReq.getsTotal());
            bodyModel.setRemark(bodyReq.getRemark());
            bodyModel.setKcCount(bodyReq.getKcCount());
            bodyModel.setmRJhCount(bodyReq.getmRJhCount());
            bodyModels.add(bodyModel);
        }
        headModel.setBodyList(bodyModels);
        return headModel;
    }
}
