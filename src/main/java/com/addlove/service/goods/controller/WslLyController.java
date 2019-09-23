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
import com.addlove.service.goods.constants.GoodsLyConstants.DataStatus;
import com.addlove.service.goods.constants.GoodsLyConstants.FlCode;
import com.addlove.service.goods.constants.GoodsLyConstants.SaveType;
import com.addlove.service.goods.constants.GoodsLyConstants.YwStatus;
import com.addlove.service.goods.constants.GoodsLyConstants.YwType;
import com.addlove.service.goods.context.SysUserDataContextHolder;
import com.addlove.service.goods.exception.ServiceException;
import com.addlove.service.goods.message.ResponseMessage;
import com.addlove.service.goods.model.BasFlContentModel;
import com.addlove.service.goods.model.OrgManageModel;
import com.addlove.service.goods.model.PageModel;
import com.addlove.service.goods.model.SysUserModel;
import com.addlove.service.goods.model.UsrUserModel;
import com.addlove.service.goods.model.WslLyBodyModel;
import com.addlove.service.goods.model.WslLyHeadModel;
import com.addlove.service.goods.model.WslLyPageModel;
import com.addlove.service.goods.model.valid.CommonQueryDetailReq;
import com.addlove.service.goods.model.valid.WslLyBillReq;
import com.addlove.service.goods.model.valid.WslLyBodyReq;
import com.addlove.service.goods.model.valid.WslLyHeadReq;
import com.addlove.service.goods.model.valid.WslLyPageReq;
import com.addlove.service.goods.model.valid.WslLySkuReq;
import com.addlove.service.goods.service.GoodsCommonService;
import com.addlove.service.goods.service.WslLyService;
import com.addlove.service.goods.util.DateUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;

/**
 * 领用单控制层
 * @author lw
 *
 */
@Controller
@RequestMapping(value = "/addlove/ly")
public class WslLyController extends BaseController {
    @Autowired
    private WslLyService wslLyService;
    
    @Autowired
    private GoodsCommonService commonService;
    
    /**
     * 查询领用单列表
     * @param req
     * @return ResponseMessage
     */
    @RequestMapping(value = "/queryLyPage", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage queryLyPage(@RequestBody @Valid WslLyPageReq req) {
        WslLyPageModel queryModel = new WslLyPageModel();
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
        queryModel.setDepId(req.getDepId());
        queryModel.setDataStatus(req.getDataStatus());
        queryModel.setYwType(req.getYwType());
        List<WslLyHeadModel> lyList = this.wslLyService.queryLyPage(queryModel);
        Page<WslLyHeadModel> page = (Page<WslLyHeadModel>) lyList;
        PageModel pageModel = new PageModel();
        pageModel.setPageNo(page.getPageNum());
        pageModel.setPageSize(page.getPageSize());
        pageModel.setResult(page.getResult());
        pageModel.setTotal(page.getTotal());
        return ResponseMessage.ok(pageModel);
    }
    
    /**
     * 新增领用单
     * @param req
     * @return ResponseMessage
     */
    @RequestMapping(value = "/addLy", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage addLy(@RequestBody @Valid WslLyHeadReq req) {
        if (StringUtils.isBlank(req.getBillNo()) && req.getSaveType() == SaveType.EXEC_ACCOUNT.getValue()) {
            throw new ServiceException(GoodsResponseCode.SAVE_BEFORE_ACCOUNT.getCode(), 
                    GoodsResponseCode.SAVE_BEFORE_ACCOUNT.getMsg());
        }
        WslLyHeadModel headModel = this.getLyHeadModel(req);
        if (req.getSaveType() == SaveType.SAVE.getValue()) {
            this.wslLyService.addLy(headModel);
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
            //如果是返还类型，还需更新领用单业务状态为“已返还”
            if (YwType.FH.getValue().equals(headModel.getYwType())) {
                this.wslLyService.updateYwStatus(headModel.getLyBillNo(), YwStatus.HAS_FH.getValue());
            }
        }else {
            throw new ServiceException(GoodsResponseCode.BILL_OPRATE_ERROR.getCode(), 
                    GoodsResponseCode.BILL_OPRATE_ERROR.getMsg());
        }
        JSONObject json = new JSONObject();
        json.put("billNo", headModel.getBillNo());
        return ResponseMessage.ok(json);
    }
    
    /**
     * 编辑领用单
     * @param req
     * @return ResponseMessage
     */
    @RequestMapping(value = "/editLy", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage editLy(@RequestBody @Valid WslLyHeadReq req) {
        if (StringUtils.isBlank(req.getBillNo())) {
            throw new ServiceException(GoodsResponseCode.BILLNO_NOT_BLANK.getCode(), 
                    GoodsResponseCode.BILLNO_NOT_BLANK.getMsg());
        }
        WslLyHeadModel headModel = this.getLyHeadModel(req);
        if (req.getSaveType() == SaveType.SAVE.getValue()) {
            this.wslLyService.editLy(headModel);
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
            //如果是返还类型，还需更新领用单业务状态为“已返还”
            if (YwType.FH.getValue().equals(headModel.getYwType())) {
                this.wslLyService.updateYwStatus(headModel.getLyBillNo(), YwStatus.HAS_FH.getValue());
            }
        }else {
            throw new ServiceException(GoodsResponseCode.BILL_OPRATE_ERROR.getCode(), 
                    GoodsResponseCode.BILL_OPRATE_ERROR.getMsg());
        }
        JSONObject json = new JSONObject();
        json.put("billNo", headModel.getBillNo());
        return ResponseMessage.ok(json);
    }
    
    /**
     * 删除领用单
     * @param req
     * @return ResponseMessage
     */
    @RequestMapping(value = "/delLy", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage delLy(@RequestBody @Valid CommonQueryDetailReq req) {
        this.wslLyService.delLy(req.getBillNo());
        return ResponseMessage.ok();
    }
    
    /**
     * 查询领用单详情
     * @param req
     * @return ResponseMessage
     */
    @RequestMapping(value = "/queryLyDetails", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage queryLyDetails(@RequestBody @Valid CommonQueryDetailReq req) {
        WslLyHeadModel headModel = this.wslLyService.queryLyDetails(req.getBillNo());
        return ResponseMessage.ok(headModel);
    }
    
    /**
     * 获取领用单用途
     * @return ResponseMessage
     */
    @RequestMapping(value = "/getLyUse", method = RequestMethod.GET)
    @ResponseBody
    public ResponseMessage getLyUse() {
        List<BasFlContentModel> models = this.wslLyService.getFls(FlCode.LY_USE.getValue());
        return ResponseMessage.ok(models);
    }
    
    /**
     * 获取领用单商品
     * @param req
     * @return ResponseMessage
     */
    @RequestMapping(value = "/getLySkuList", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage getLySkuList(@RequestBody @Valid WslLySkuReq req) {
        if (StringUtils.isBlank(req.getOrgCode())) {
            throw new ServiceException(GoodsResponseCode.ORGCODE_NOT_BLANK.getCode(), 
                    GoodsResponseCode.ORGCODE_NOT_BLANK.getMsg());
        }
        if (null == req.getDepId()) {
            throw new ServiceException(GoodsResponseCode.DEP_ID_NOT_BLANK.getCode(), 
                    GoodsResponseCode.DEP_ID_NOT_BLANK.getMsg());
        }
        if (StringUtils.isBlank(req.getFlCode())) {
            throw new ServiceException(GoodsResponseCode.FL_TYPE_NOT_BLANK.getCode(), 
                    GoodsResponseCode.FL_TYPE_NOT_BLANK.getMsg());
        }
        JSONArray backArray = this.wslLyService.getLySkuList(req.getOrgCode(), req.getDepId(), req.getFlCode());
        return ResponseMessage.ok(backArray);
    }
    
    /**
     * 获取内部领用单据号
     * @param req
     * @return ResponseMessage
     */
    @RequestMapping(value = "/getInnerLyBills", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage getInnerLyBills(@RequestBody @Valid WslLyBillReq req) {
        List<WslLyHeadModel> bills = this.wslLyService.getInnerLyBills(req.getDepId(), req.getBillNo());
        return ResponseMessage.ok(bills);
    }
    
    /**
     * 封装领用单数据
     * @param req
     * @return WslLyHeadModel
     */
    private WslLyHeadModel getLyHeadModel(WslLyHeadReq req) {
        WslLyHeadModel headModel = new WslLyHeadModel();
        List<WslLyBodyReq> bodyList = req.getBodyList();
        if (null == bodyList || bodyList.isEmpty() || req.getLyCount() == 0) {
            throw new ServiceException(GoodsResponseCode.SKU_NOT_BLANK.getCode(), 
                    GoodsResponseCode.SKU_NOT_BLANK.getMsg());
        }
        if (YwType.FH.getValue().equals(headModel.getYwType()) && StringUtils.isBlank(req.getLyBillNo())) {
            throw new ServiceException(GoodsResponseCode.FH_TYPE_BILL_NOT_BLANK.getCode(), 
                    GoodsResponseCode.FH_TYPE_BILL_NOT_BLANK.getMsg());
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
        headModel.setOrgCode(req.getOrgCode());
        headModel.setOrgName(req.getOrgName());
        headModel.setDepId(req.getDepId());
        headModel.setDepCode(req.getDepCode());
        headModel.setDepName(req.getDepName());
        OrgManageModel orgModel = this.commonService.getOrgModel(req.getOrgCode());
        headModel.setInOrgCode(orgModel.getInOrgCode());
        headModel.setCkCode(req.getCkCode());
        headModel.setCkName(req.getCkName());
        headModel.setLyOrgCode(req.getOrgCode());
        headModel.setLyOrgName(req.getOrgName());
        headModel.setLyDepId(req.getDepId());
        headModel.setLyDepCode(req.getDepCode());
        headModel.setLyDepName(req.getDepName());
        headModel.setPurpose(req.getPurpose());
        headModel.setPriceType("1");
        headModel.setLyCount(req.getLyCount());
        headModel.setsTotal(req.getsTotal());
        headModel.setSsTotal(req.getSsTotal());
        headModel.setDataStatus(DataStatus.ENTRY.getValue());
        headModel.setRemark(req.getRemark());
        headModel.setYwType(req.getYwType());
        headModel.setBillType(req.getBillType());
        headModel.setLyBillNo(req.getLyBillNo());
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
        List<WslLyBodyModel> bodyModels = new LinkedList<WslLyBodyModel>();
        long serialNo = 1;
        for (WslLyBodyReq bodyReq : bodyList) {
            if (bodyReq.getLyCount() == 0) {
                continue;
            }
            WslLyBodyModel bodyModel = new WslLyBodyModel();
            serialNo++;
            bodyModel.setBillNo(headModel.getBillNo());
            bodyModel.setSerialNo(serialNo);
            bodyModel.setDepId(headModel.getDepId());
            bodyModel.setDepCode(headModel.getDepCode());
            bodyModel.setDepName(headModel.getDepName());
            bodyModel.setPluId(bodyReq.getPluId());
            bodyModel.setPluCode(bodyReq.getPluCode());
            bodyModel.setPluName(bodyReq.getPluName());
            bodyModel.setBarCode(bodyReq.getBarCode());
            bodyModel.setSpec(bodyReq.getSpec());
            bodyModel.setUnit(bodyReq.getUnit());
            bodyModel.setExPluCode("*");
            bodyModel.setLyCount(bodyReq.getLyCount());
            bodyModel.setPrice(bodyReq.getPrice());
            bodyModel.setsTotal(bodyReq.getsTotal());
            bodyModel.setSsTotal(bodyReq.getSsTotal());
            bodyModel.setRemark(bodyReq.getRemark());
            bodyModel.setPriceType("1");
            bodyModel.setCkPrice(bodyReq.getCkPrice());
            bodyModel.setCkSsTotal(bodyReq.getCkSsTotal());
            bodyModel.setToSerialNo(serialNo);
            bodyModels.add(bodyModel);
        }
        headModel.setBodyList(bodyModels);
        return headModel;
    }
}
