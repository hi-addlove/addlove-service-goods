package com.addlove.service.goods.controller;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.validation.Valid;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.addlove.service.goods.constants.GoodsResponseCode;
import com.addlove.service.goods.constants.GoodsDstRtnConstants.BillStatus;
import com.addlove.service.goods.constants.GoodsDstRtnConstants.DataStatus;
import com.addlove.service.goods.constants.GoodsDstRtnConstants.FlCode;
import com.addlove.service.goods.constants.GoodsDstRtnConstants.SaveType;
import com.addlove.service.goods.constants.GoodsDstRtnConstants.YwType;
import com.addlove.service.goods.context.SysUserDataContextHolder;
import com.addlove.service.goods.exception.ServiceException;
import com.addlove.service.goods.message.ResponseMessage;
import com.addlove.service.goods.model.BasFlContentModel;
import com.addlove.service.goods.model.DstRtnApplyBodyModel;
import com.addlove.service.goods.model.DstRtnApplyHeadModel;
import com.addlove.service.goods.model.DstRtnPageModel;
import com.addlove.service.goods.model.OrgManageModel;
import com.addlove.service.goods.model.PageModel;
import com.addlove.service.goods.model.SkuPluExtendModel;
import com.addlove.service.goods.model.SysUserModel;
import com.addlove.service.goods.model.UsrUserModel;
import com.addlove.service.goods.model.valid.CommonQueryDetailReq;
import com.addlove.service.goods.model.valid.DstRtnApplyBodyReq;
import com.addlove.service.goods.model.valid.DstRtnApplyHeadReq;
import com.addlove.service.goods.model.valid.DstRtnPageReq;
import com.addlove.service.goods.model.valid.DstRtnSkuReq;
import com.addlove.service.goods.service.DstRtnApplyService;
import com.addlove.service.goods.service.GoodsCommonService;
import com.addlove.service.goods.util.DateUtil;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;

/**
 * 门店配送退货申请单(质量退货)控制层
 * @author lw
 *
 */
@Controller
@RequestMapping(value = "/addlove/dstRtn")
public class DstRtnApplyController extends BaseController {
    @Autowired
    private DstRtnApplyService rtnApplyService;
    
    @Autowired
    private GoodsCommonService commonService;
    
    /**
     * 查询配送退货单列表
     * @param req
     * @return ResponseMessage
     */
    @RequestMapping(value = "/queryRtnPage", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage queryRtnPage(@RequestBody @Valid DstRtnPageReq req) {
        DstRtnPageModel queryModel = new DstRtnPageModel();
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
        queryModel.setBillStatus(req.getBillStatus());
        queryModel.setUser(req.getUser());
        queryModel.setDepId(req.getDepId());
        queryModel.setCkCode(req.getCkCode());
        List<DstRtnApplyHeadModel> rtnList = this.rtnApplyService.queryRtnPage(queryModel);
        Page<DstRtnApplyHeadModel> page = (Page<DstRtnApplyHeadModel>) rtnList;
        PageModel pageModel = new PageModel();
        pageModel.setPageNo(page.getPageNum());
        pageModel.setPageSize(page.getPageSize());
        pageModel.setResult(page.getResult());
        pageModel.setTotal(page.getTotal());
        return ResponseMessage.ok(pageModel);
    }
    
    /**
     * 新增配送退货单
     * @param req
     * @return ResponseMessage
     */
    @RequestMapping(value = "/addRtn", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage addRtn(@RequestBody @Valid DstRtnApplyHeadReq req) {
        if (StringUtils.isBlank(req.getBillNo()) && req.getSaveType() == SaveType.EXEC_ACCOUNT.getValue()) {
            throw new ServiceException(GoodsResponseCode.SAVE_BEFORE_ACCOUNT.getCode(), 
                    GoodsResponseCode.SAVE_BEFORE_ACCOUNT.getMsg());
        }
        DstRtnApplyHeadModel headModel= this.getRtnHeadModel(req);
        if (req.getSaveType() == SaveType.SAVE.getValue()) {
            this.rtnApplyService.addRtn(headModel);
        }else if (req.getSaveType() == SaveType.EXEC_ACCOUNT.getValue()) {
            Map<String, Object> accountMap = new HashMap<String, Object>();
            accountMap.put("ps_BillNo", headModel.getBillNo());
            accountMap.put("pd_Date", headModel.getJzDate());
            accountMap.put("ps_UserId", headModel.getJzrId());
            accountMap.put("ps_UserCode", headModel.getJzrCode());
            accountMap.put("ps_UserName", headModel.getJzrName());
            this.rtnApplyService.execRtnAccountProcedure(accountMap);
        }else {
            throw new ServiceException(GoodsResponseCode.BILL_OPRATE_ERROR.getCode(), 
                    GoodsResponseCode.BILL_OPRATE_ERROR.getMsg());
        }
        JSONObject json = new JSONObject();
        json.put("billNo", headModel.getBillNo());
        return ResponseMessage.ok(json);
    }
    
    /**
     * 编辑配送退货单
     * @param req
     * @return ResponseMessage
     */
    @RequestMapping(value = "/editRtn", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage editRtn(@RequestBody @Valid DstRtnApplyHeadReq req) {
        if (StringUtils.isBlank(req.getBillNo())) {
            throw new ServiceException(GoodsResponseCode.BILLNO_NOT_BLANK.getCode(), 
                    GoodsResponseCode.BILLNO_NOT_BLANK.getMsg());
        }
        DstRtnApplyHeadModel headModel= this.getRtnHeadModel(req);
        if (req.getSaveType() == SaveType.SAVE.getValue()) {
            this.rtnApplyService.editRtn(headModel);
        }else if (req.getSaveType() == SaveType.EXEC_ACCOUNT.getValue()) {
            Map<String, Object> accountMap = new HashMap<String, Object>();
            accountMap.put("ps_BillNo", headModel.getBillNo());
            accountMap.put("pd_Date", headModel.getJzDate());
            accountMap.put("ps_UserId", headModel.getJzrId());
            accountMap.put("ps_UserCode", headModel.getJzrCode());
            accountMap.put("ps_UserName", headModel.getJzrName());
            this.rtnApplyService.execRtnAccountProcedure(accountMap);
        }else {
            throw new ServiceException(GoodsResponseCode.BILL_OPRATE_ERROR.getCode(), 
                    GoodsResponseCode.BILL_OPRATE_ERROR.getMsg());
        }
        JSONObject json = new JSONObject();
        json.put("billNo", headModel.getBillNo());
        return ResponseMessage.ok(json);
    }
    
    /**
     * 查询配送退货单详情
     * @param req
     * @return ResponseMessage
     */
    @RequestMapping(value = "/queryRtnDetails", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage queryRtnDetails(@RequestBody @Valid CommonQueryDetailReq req) {
        DstRtnApplyHeadModel headModel = this.rtnApplyService.queryRtnDetails(req.getBillNo());
        return ResponseMessage.ok(headModel);
    }
    
    /**
     * 删除配送退货数据
     * @param req
     * @return ResponseMessage
     */
    @RequestMapping(value = "/delRtn", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage delRtn(@RequestBody @Valid CommonQueryDetailReq req) {
        this.rtnApplyService.delRtn(req.getBillNo());
        return ResponseMessage.ok();
    }
    
    /**
     * 获取退货原因
     * @return ResponseMessage
     */
    @RequestMapping(value = "/getThReason", method = RequestMethod.GET)
    @ResponseBody
    public ResponseMessage getThReason() {
        List<BasFlContentModel> models = this.rtnApplyService.getThReason(FlCode.TH_REASON.getValue());
        return ResponseMessage.ok(models);
    }
    
    /**
     * 获取配送退货单商品
     * @param req
     * @return ResponseMessage
     */
    @RequestMapping(value = "/getRtnSkuList", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage getRtnSkuList(@RequestBody @Valid DstRtnSkuReq req) {
        if (StringUtils.isBlank(req.getOrgCode())) {
            throw new ServiceException(GoodsResponseCode.ORGCODE_NOT_BLANK.getCode(), 
                    GoodsResponseCode.ORGCODE_NOT_BLANK.getMsg());
        }
        if (null == req.getDepId()) {
            throw new ServiceException(GoodsResponseCode.DEP_ID_NOT_BLANK.getCode(), 
                    GoodsResponseCode.DEP_ID_NOT_BLANK.getMsg());
        }
        if (StringUtils.isBlank(req.getCkCode())) {
            throw new ServiceException(GoodsResponseCode.CK_NOT_BLANK.getCode(), 
                    GoodsResponseCode.CK_NOT_BLANK.getMsg());
        }
        Set<SkuPluExtendModel> skuModels = this.rtnApplyService.getRtnSkuList(req.getOrgCode(), req.getDepId(), req.getCkCode());
        return ResponseMessage.ok(skuModels);
    }
    
    /**
     * 组装配送退货数据
     * @param req
     * @return DstRtnApplyHeadModel
     */
    private DstRtnApplyHeadModel getRtnHeadModel(DstRtnApplyHeadReq req) {
        DstRtnApplyHeadModel headModel = new DstRtnApplyHeadModel();
        List<DstRtnApplyBodyReq> bodyList = req.getBodyList();
        if (null == bodyList || bodyList.isEmpty() || req.getThCount() == 0) {
            throw new ServiceException(GoodsResponseCode.SKU_NOT_BLANK.getCode(), 
                    GoodsResponseCode.SKU_NOT_BLANK.getMsg());
        }
        headModel.setLrDate(DateUtil.getCurrentTime());
        SysUserModel sysUserModel = SysUserDataContextHolder.getSysUserData();
        if (null == sysUserModel) {
            throw new ServiceException(GoodsResponseCode.LOGIN_AGAIN.getCode(), 
                    GoodsResponseCode.LOGIN_AGAIN.getMsg());
        }
        UsrUserModel userModel = sysUserModel.getUserModel();
        headModel.setUserId(userModel.getUserId());
        headModel.setUserCode(userModel.getUserCode());
        headModel.setUserName(userModel.getUserName());
        headModel.setThOrgCode(req.getOrgCode());
        headModel.setThOrgnNme(req.getOrgName());
        OrgManageModel thOrgModel = this.commonService.getOrgModel(req.getOrgCode());
        headModel.setInThOrgCode(thOrgModel.getOrgCode());
        OrgManageModel orgModel = this.commonService.getOrgModel(thOrgModel.getZbOrgCode());
        headModel.setOrgCode(orgModel.getOrgCode());
        headModel.setOrgName(orgModel.getOrgName());
        headModel.setInOrgCode(orgModel.getInOrgCode());
        headModel.setDepId(req.getDepId());
        headModel.setDepCode(req.getDepCode());
        headModel.setDepName(req.getDepName());
        headModel.setOrgType("0");
        headModel.setCntId(req.getCntId());
        headModel.setHtCode(req.getHtCode());
        headModel.setHtName(req.getHtName());
        headModel.setThCount(req.getThCount());
        headModel.setThTotal(req.getThTotal());
        headModel.setJyMode("9");
        headModel.setJsCode("9");
        headModel.setYwType(YwType.PS_RTN.getValue());
        headModel.setYwIoType("0");
        headModel.setRemark(req.getRemark());
        headModel.setDataStatus(DataStatus.ENTRY.getValue());
        headModel.setCkCode(req.getCkCode());
        headModel.setCkName(req.getCkName());
        headModel.setThReason(req.getThReason());
        headModel.setwThTotal(req.getwThTotal());
        headModel.setPlnThDate(req.getPlnThDate());
        headModel.setsTotal(req.getsTotal());
        headModel.setShType("1");
        headModel.setStlCurrThTotal(req.getStlCurrThTotal());
        headModel.setBillStatus(BillStatus.WAIT_CONFIRM.getValue());
        headModel.setAdlPsThSqType("1");
        if (req.getSaveType() == SaveType.EXEC_ACCOUNT.getValue()) {
            headModel.setJzDate(DateUtil.getCurrentTime());
            headModel.setJzrId(userModel.getUserId());
            headModel.setJzrCode(userModel.getUserCode());
            headModel.setJzrName(userModel.getUserName());
        }
        if (StringUtils.isBlank(req.getBillNo())) {
            Map<String, Object> map = new HashMap<>();
            map.put("ps_BillType", headModel.getYwType());
            String billNo = this.commonService.getBillNoByCallProcedure(map);
            headModel.setBillNo(billNo);
        }else {
            headModel.setBillNo(req.getBillNo());
        }
        List<DstRtnApplyBodyModel> bodyModels = new LinkedList<DstRtnApplyBodyModel>();
        long serialNo = 1;
        for (DstRtnApplyBodyReq bodyReq : bodyList) {
            if (bodyReq.getThCount() == 0) {
                continue;
            }
            DstRtnApplyBodyModel bodyModel = new DstRtnApplyBodyModel();
            bodyModel.setBillNo(headModel.getBillNo());
            bodyModel.setSerialNo(serialNo++);
            bodyModel.setPluId(bodyReq.getPluId());
            bodyModel.setPluCode(bodyReq.getPluCode());
            bodyModel.setPluName(bodyReq.getPluName());
            bodyModel.setBarCode(bodyReq.getBarCode());
            bodyModel.setExPluCode("*");
            bodyModel.setSpec(bodyReq.getSpec());
            bodyModel.setUnit(bodyReq.getUnit());
            bodyModel.setThPrice(bodyReq.getThPrice());
            bodyModel.setThCount(bodyReq.getThCount());
            bodyModel.setThTotal(bodyReq.getThTotal());
            bodyModel.setRemark(bodyReq.getRemark());
            bodyModel.setDepId(headModel.getDepId());
            bodyModel.setThReason(bodyReq.getThReason());
            bodyModel.setwThPrice(bodyReq.getwThPrice());
            bodyModel.setwThTotal(bodyReq.getwThTotal());
            bodyModel.setPrice(bodyReq.getPrice());
            bodyModel.setsTotal(bodyReq.getsTotal());
            bodyModel.setStlCurrThPrice(bodyReq.getStlCurrThPrice());
            bodyModel.setStlCurrThTotal(bodyReq.getStlCurrThTotal());
            bodyModels.add(bodyModel);
        }
        headModel.setBodyList(bodyModels);
        return headModel;
    }
}
