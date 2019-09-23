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
import com.addlove.service.goods.constants.GoodsJgConstants.ClType;
import com.addlove.service.goods.constants.GoodsJgConstants.DataStatus;
import com.addlove.service.goods.constants.GoodsJgConstants.SaveType;
import com.addlove.service.goods.constants.GoodsJgConstants.SkuType;
import com.addlove.service.goods.constants.GoodsJgConstants.YwType;
import com.addlove.service.goods.constants.GoodsOrdJhConstants;
import com.addlove.service.goods.context.SysUserDataContextHolder;
import com.addlove.service.goods.constants.GoodsResponseCode;
import com.addlove.service.goods.constants.GoodsCommonConstants.BillType;
import com.addlove.service.goods.exception.ServiceException;
import com.addlove.service.goods.message.ResponseMessage;
import com.addlove.service.goods.model.FrsGyModel;
import com.addlove.service.goods.model.FrsJgCpModel;
import com.addlove.service.goods.model.FrsJgHeadModel;
import com.addlove.service.goods.model.FrsJgPageModel;
import com.addlove.service.goods.model.FrsJgYlModel;
import com.addlove.service.goods.model.OrgDeptModel;
import com.addlove.service.goods.model.OrgManageModel;
import com.addlove.service.goods.model.PageModel;
import com.addlove.service.goods.model.SkuPluExtendModel;
import com.addlove.service.goods.model.StkDbBodyModel;
import com.addlove.service.goods.model.StkDbHeadModel;
import com.addlove.service.goods.model.StkStoreModel;
import com.addlove.service.goods.model.SysUserModel;
import com.addlove.service.goods.model.UsrUserModel;
import com.addlove.service.goods.model.WslCakeBillPluTPModel;
import com.addlove.service.goods.model.WslCakeBillTPModel;
import com.addlove.service.goods.model.valid.CommonQueryDetailReq;
import com.addlove.service.goods.model.valid.FrsJgCpReq;
import com.addlove.service.goods.model.valid.FrsJgHeadReq;
import com.addlove.service.goods.model.valid.FrsJgPageReq;
import com.addlove.service.goods.model.valid.FrsJgSkuReq;
import com.addlove.service.goods.model.valid.FrsJgYlReq;
import com.addlove.service.goods.service.FrsJgService;
import com.addlove.service.goods.service.GoodsCommonService;
import com.addlove.service.goods.util.DateUtil;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;

/**
 * 加工单控制层
 * @author lw
 *
 */
@Controller
@RequestMapping(value = "/addlove/jg")
public class FrsJgController extends BaseController{
    @Autowired
    private FrsJgService frsJgService;
    
    @Autowired
    private GoodsCommonService commonService;
    
    /**
     * 查询加工单列表
     * @param req
     * @return ResponseMessage
     */
    @RequestMapping(value = "/queryJgPage", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage queryJgPage(@RequestBody @Valid FrsJgPageReq req) {
        FrsJgPageModel queryModel = new FrsJgPageModel();
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
        List<FrsJgHeadModel> jgList = this.frsJgService.queryJgPage(queryModel);
        Page<FrsJgHeadModel> page = (Page<FrsJgHeadModel>) jgList;
        PageModel pageModel = new PageModel();
        pageModel.setPageNo(page.getPageNum());
        pageModel.setPageSize(page.getPageSize());
        pageModel.setResult(page.getResult());
        pageModel.setTotal(page.getTotal());
        return ResponseMessage.ok(pageModel);
    }
    
    /**
     * 新增加工单
     * @param req
     * @return ResponseMessage
     */
    @RequestMapping(value = "/addJg", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage addJg(@RequestBody @Valid FrsJgHeadReq req) {
        if (StringUtils.isBlank(req.getBillNo()) && req.getSaveType() == SaveType.EXEC_ACCOUNT.getValue()) {
            throw new ServiceException(GoodsResponseCode.SAVE_BEFORE_ACCOUNT.getCode(), 
                    GoodsResponseCode.SAVE_BEFORE_ACCOUNT.getMsg());
        }
        FrsJgHeadModel headModel = this.getJgHeadModel(req);
        if (req.getSaveType() == SaveType.SAVE.getValue()) {
            this.frsJgService.addJg(headModel);
        }else if (req.getSaveType() == SaveType.EXEC_ACCOUNT.getValue()) {
            Map<String, Object> accountMap = new HashMap<String, Object>();
            accountMap.put("ps_BillNo", headModel.getBillNo());
            accountMap.put("pd_Date", headModel.getJzDate());
            accountMap.put("pf_UserID", headModel.getJzrId());
            accountMap.put("ps_UserCode", headModel.getJzrCode());
            accountMap.put("ps_UserName", headModel.getJzrName());
            accountMap.put("ps_ClType", ClType.MANUAL_EXEC.getValue());
            this.frsJgService.execJgAccountProcedure(accountMap);
        }else {
            throw new ServiceException(GoodsResponseCode.BILL_OPRATE_ERROR.getCode(), 
                    GoodsResponseCode.BILL_OPRATE_ERROR.getMsg());
        }
        JSONObject json = new JSONObject();
        json.put("billNo", headModel.getBillNo());
        return ResponseMessage.ok(json);
    }
    
    /**
     * 编辑加工单
     * @param req
     * @return ResponseMessage
     */
    @RequestMapping(value = "/editJg", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage editJg(@RequestBody @Valid FrsJgHeadReq req) {
        if (StringUtils.isBlank(req.getBillNo())) {
            throw new ServiceException(GoodsResponseCode.BILLNO_NOT_BLANK.getCode(), 
                    GoodsResponseCode.BILLNO_NOT_BLANK.getMsg());
        }
        FrsJgHeadModel headModel = this.getJgHeadModel(req);
        if (req.getSaveType() == SaveType.SAVE.getValue()) {
            this.frsJgService.editJg(headModel);
        }else if (req.getSaveType() == SaveType.EXEC_ACCOUNT.getValue()) {
            Map<String, Object> accountMap = new HashMap<String, Object>();
            accountMap.put("ps_BillNo", headModel.getBillNo());
            accountMap.put("pd_Date", headModel.getJzDate());
            accountMap.put("pf_UserID", headModel.getJzrId());
            accountMap.put("ps_UserCode", headModel.getJzrCode());
            accountMap.put("ps_UserName", headModel.getJzrName());
            accountMap.put("ps_ClType", ClType.MANUAL_EXEC.getValue());
            this.frsJgService.execJgAccountProcedure(accountMap);
        }else {
            throw new ServiceException(GoodsResponseCode.BILL_OPRATE_ERROR.getCode(), 
                    GoodsResponseCode.BILL_OPRATE_ERROR.getMsg());
        }
        JSONObject json = new JSONObject();
        json.put("billNo", headModel.getBillNo());
        return ResponseMessage.ok(json);
    }
    
    /**
     * 删除加工单
     * @param req
     * @return ResponseMessage
     */
    @RequestMapping(value = "/delJg", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage delJg(@RequestBody @Valid CommonQueryDetailReq req) {
        this.frsJgService.delJg(req.getBillNo());
        return ResponseMessage.ok();
    }
    
    /**
     * 查询加工单详情
     * @param req
     * @return ResponseMessage
     */
    @RequestMapping(value = "/queryJgDetails", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage queryJgDetails(@RequestBody @Valid CommonQueryDetailReq req) {
        FrsJgHeadModel model = this.frsJgService.queryJgDetails(req.getBillNo());
        return ResponseMessage.ok(model);
    }
    
    /**
     * 获取加工商品
     * @param req
     * @return ResponseMessage
     */
    @RequestMapping(value = "/getJgSkuList", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage getJgSkuList(@RequestBody @Valid FrsJgSkuReq req) {
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
        if (null == req.getSkuType()) {
            throw new ServiceException(GoodsResponseCode.JG_SKU_TYPE_NOT_BLANK.getCode(), 
                    GoodsResponseCode.JG_SKU_TYPE_NOT_BLANK.getMsg());
        }
        Set<SkuPluExtendModel> skuList = null;
        if (SkuType.SKU_YL.getValue() == req.getSkuType()) {
            skuList = this.frsJgService.getJgYlSkuList(req.getOrgCode(), req.getDepId(), req.getCkCode());
            return ResponseMessage.ok(skuList);
        }else if (SkuType.SKU_CP.getValue() == req.getSkuType()) {
            skuList = this.frsJgService.getJgCpSkuList(req.getOrgCode(), req.getDepId());
        }
        return ResponseMessage.ok(skuList);
    }
    
    /**
     * 查询生日蛋糕加工单列表
     * @param req
     * @return ResponseMessage
     */
    @RequestMapping(value = "/queryJgCakePage", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage queryJgCakePage(@RequestBody @Valid FrsJgPageReq req) {
        FrsJgPageModel queryModel = new FrsJgPageModel();
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
        List<WslCakeBillTPModel> cakeList = this.frsJgService.queryJgCakePage(queryModel);
        Page<WslCakeBillTPModel> page = (Page<WslCakeBillTPModel>) cakeList;
        PageModel pageModel = new PageModel();
        pageModel.setPageNo(page.getPageNum());
        pageModel.setPageSize(page.getPageSize());
        pageModel.setResult(page.getResult());
        pageModel.setTotal(page.getTotal());
        return ResponseMessage.ok(pageModel);
    }
    
    /**
     * 查询生日蛋糕加工单详情
     * @param req
     * @return ResponseMessage
     */
    @RequestMapping(value = "/queryJgCakeDetails", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage queryJgCakeDetails(@RequestBody @Valid CommonQueryDetailReq req) {
        WslCakeBillTPModel model = this.frsJgService.queryJgCakeDetails(req.getBillNo());
        return ResponseMessage.ok(model);
    }
    
    /**
     * 生日蛋糕加工记账
     * @param req
     * @return ResponseMessage
     */
    @RequestMapping(value = "/execJgCakeAccount", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage execJgCakeAccount(@RequestBody @Valid FrsJgPageReq req) {
        if (StringUtils.isBlank(req.getBillNo())) {
            throw new ServiceException(GoodsResponseCode.BILLNO_NOT_BLANK.getCode(), 
                    GoodsResponseCode.BILLNO_NOT_BLANK.getMsg());
        }
        Map<String, Object> accountMap = new HashMap<String, Object>();
        accountMap.put("ps_BillNo", req.getBillNo());
        accountMap.put("ps_OrgCode", req.getOrgCode());
        accountMap.put("pd_Date", DateUtil.getCurrentTime());
        SysUserModel sysUserModel = SysUserDataContextHolder.getSysUserData();
        if (null == sysUserModel) {
            throw new ServiceException(GoodsResponseCode.LOGIN_AGAIN.getCode(), 
                    GoodsResponseCode.LOGIN_AGAIN.getMsg());
        }
        UsrUserModel userModel = sysUserModel.getUserModel();
        accountMap.put("ps_UserID", userModel.getUserId());
        accountMap.put("ps_UserCode", userModel.getUserCode());
        accountMap.put("ps_UserName", userModel.getUserName());
        this.frsJgService.execJgCakeAccountProcedure(accountMap);
        return ResponseMessage.ok();
    }
    
    /**
     * 生日蛋糕快速调拨
     * @param req
     * @return ResponseMessage
     */
    @RequestMapping(value = "/dbJgCakeFaster", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage dbJgCakeFaster(@RequestBody @Valid CommonQueryDetailReq req) {
        WslCakeBillTPModel cakeModel = this.frsJgService.queryJgCakeDetails(req.getBillNo());
        if (null == cakeModel) {
            throw new ServiceException(GoodsResponseCode.JG_CAKE_NOT_BLANK.getCode(), 
                    GoodsResponseCode.JG_CAKE_NOT_BLANK.getMsg());
        }
        List<WslCakeBillPluTPModel> bodyList = cakeModel.getBodyList();
        if (null == bodyList || bodyList.isEmpty()) {
            throw new ServiceException(GoodsResponseCode.SKU_NOT_BLANK.getCode(), 
                    GoodsResponseCode.SKU_NOT_BLANK.getMsg());
        }
        StkDbHeadModel headModel = new StkDbHeadModel();
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
        headModel.setEmpId(userModel.getUserId());
        headModel.setEmpCode(userModel.getUserCode());
        headModel.setEmpName(userModel.getUserName());
        headModel.setOrgCode(cakeModel.getJgOrgCode());
        headModel.setOrgName(cakeModel.getJgOrgName());
        FrsGyModel gyModel = this.frsJgService.getDeptByGyPlu(bodyList.get(0).getPluId());
        if (null == gyModel) {
            throw new ServiceException(GoodsResponseCode.JG_GY_NOT_BLANK.getCode(), 
                    GoodsResponseCode.JG_GY_NOT_BLANK.getMsg());
        }
        OrgDeptModel dept = this.commonService.getDeptByCode(headModel.getOrgCode(), gyModel.getDepCode());
        if (null == dept) {
            throw new ServiceException(GoodsResponseCode.JG_GY_DEPT_NOT_BLANK.getCode(), 
                    GoodsResponseCode.JG_GY_DEPT_NOT_BLANK.getMsg());
        }
        headModel.setDepId(dept.getDepId());
        headModel.setDepCode(dept.getDepCode());
        headModel.setDepName(dept.getDepName());
        headModel.setShOrgCode(cakeModel.getThOrgCode());
        headModel.setShOrgName("");
        OrgDeptModel shDept = this.commonService.getDeptByCode(headModel.getShOrgCode(), "01");
        if (null == shDept) {
            throw new ServiceException(GoodsResponseCode.JG_GY_DEPT_NOT_BLANK.getCode(), 
                    GoodsResponseCode.JG_GY_DEPT_NOT_BLANK.getMsg());
        }
        headModel.setShDepId(shDept.getDepId());
        headModel.setShDepCode("01");
        headModel.setShDepName(shDept.getDepName());
        List<StkStoreModel> storeList = this.commonService.getStoreList(headModel.getOrgCode());
        if (null == storeList || storeList.isEmpty()) {
            throw new ServiceException(GoodsResponseCode.CK_NOT_BLANK.getCode(), 
                    GoodsResponseCode.CK_NOT_BLANK.getMsg());
        }
        headModel.setCkCode(storeList.get(0).getCkCode());
        headModel.setCkName(storeList.get(0).getCkName());
        List<StkStoreModel> shStoreList = this.commonService.getStoreList(headModel.getShOrgCode());
        if (null == shStoreList || shStoreList.isEmpty()) {
            throw new ServiceException(GoodsResponseCode.CK_NOT_BLANK.getCode(), 
                    GoodsResponseCode.CK_NOT_BLANK.getMsg());
        }
        headModel.setShCkCode(shStoreList.get(0).getCkCode());
        headModel.setShCkName(shStoreList.get(0).getCkName());
        headModel.setDbCount(0.0);
        headModel.setwCost(0.0);
        headModel.sethCost(0.0);
        headModel.setRemark("生日蛋糕快捷调拨");
        OrgManageModel orgModel = this.commonService.getOrgModel(headModel.getOrgCode());
        headModel.setInOrgCode(orgModel.getInOrgCode());
        OrgManageModel shOrgModel = this.commonService.getOrgModel(headModel.getShOrgCode());
        headModel.setInShOrgCode(shOrgModel.getInOrgCode());
        headModel.setShOrgName(shOrgModel.getOrgName());
        headModel.setDataStatus(DataStatus.ENTRY.getValue());
        headModel.setYwType(GoodsOrdJhConstants.YwType.DB_ORDER.getValue());
        headModel.setRhCost(0.0);
        headModel.setRwCost(0.0);
        headModel.setsTotal(0.0);
       //调用存储过程生成调拨单号
        Map<String, Object> map = new HashMap<>();
        map.put("ps_BillType", BillType.DB_ORDER.getValue());
        String billNo = this.commonService.getBillNoByCallProcedure(map);
        headModel.setBillNo(billNo);
        double headDbCount = 0.0;
        double headSTotal = 0.0;
        //调拨明细
        List<StkDbBodyModel> bodyModels = new LinkedList<StkDbBodyModel>();
        long serialNo = 1;
        for (WslCakeBillPluTPModel cakePluModel : bodyList) {
            StkDbBodyModel bodyModel = new StkDbBodyModel();
            bodyModel.setBillNo(headModel.getBillNo());
            serialNo++;
            bodyModel.setSerialNo(serialNo);
            bodyModel.setPluId(cakePluModel.getPluId());
            bodyModel.setExPluCode(cakePluModel.getExPluCode());
            bodyModel.setExPluName(cakePluModel.getExPluName());
            bodyModel.setPluCode(cakePluModel.getPluCode());
            bodyModel.setPluName(cakePluModel.getPluName());
            bodyModel.setBarCode(cakePluModel.getBarCode());
            bodyModel.setSpec(cakePluModel.getSpec());
            bodyModel.setUnit(cakePluModel.getUnit());
            bodyModel.setPackUnit("");
            bodyModel.setPackQty(0.0);
            bodyModel.setPackCount(0.0);
            bodyModel.setSglCount(0.0);
            double dbCount = cakePluModel.getPluQty();
            bodyModel.setDbCount(dbCount);
            headDbCount = headDbCount + dbCount;
            bodyModel.setHjPrice(0.0);
            bodyModel.setWjPrice(0.0);
            bodyModel.setPrice(cakePluModel.getPrice());
            bodyModel.setjTaxRate(0.0);
            bodyModel.sethCost(0.0);
            bodyModel.setwCost(0.0);
            bodyModel.setRemark(cakePluModel.getRemark());
            bodyModel.setShDepId(1L);
            bodyModel.setShDepCode("01");
            bodyModel.setShDepName("前场");
            bodyModel.setRhCost(0.0);
            bodyModel.setRwCost(0.0);
            double sTotal = cakePluModel.getPluTotal();
            bodyModel.setsTotal(sTotal);
            headSTotal = headSTotal + sTotal;
            bodyModel.setRhJPrice(0.0);
            bodyModel.setRwJPrice(0.0);
            bodyModel.setCarGoNo("");
            bodyModel.setToSerialNo(serialNo);
            bodyModels.add(bodyModel);
        }
        headModel.setDbCount(headDbCount);
        headModel.setsTotal(headSTotal);
        headModel.setBodyList(bodyModels);
        this.frsJgService.insertDbAndExecAccount(cakeModel.getBillNo() ,headModel);
        return ResponseMessage.ok();
    }
    
    /**
     * 组装加工单数据
     * @param req
     * @return FrsJgHeadModel
     */
    private FrsJgHeadModel getJgHeadModel(FrsJgHeadReq req) {
        FrsJgHeadModel headModel = new FrsJgHeadModel();
        List<FrsJgYlReq> ylList = req.getYlList();
        List<FrsJgCpReq> cpList = req.getCpList();
        if (null == ylList || ylList.isEmpty() || req.getYlCount() == 0) {
            throw new ServiceException(GoodsResponseCode.SKU_NOT_BLANK.getCode(), 
                    GoodsResponseCode.SKU_NOT_BLANK.getMsg());
        }
        if (null == cpList || cpList.isEmpty() || req.getCpCount() == 0) {
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
        headModel.setYwType(YwType.MD_JG.getValue());
        headModel.setYwIoType("0");
        headModel.setOrgCode(req.getOrgCode());
        headModel.setOrgName(req.getOrgName());
        headModel.setDepId(req.getDepId());
        headModel.setDepCode(req.getDepCode());
        headModel.setDepName(req.getDepName());
        List<FrsGyModel> jgGys = this.frsJgService.getJgGys(req.getOrgCode(), req.getDepCode());
        if (null == jgGys || jgGys.isEmpty()) {
            throw new ServiceException(GoodsResponseCode.JG_GY_NOT_BLANK.getCode(), 
                    GoodsResponseCode.JG_GY_NOT_BLANK.getMsg());
        }
        headModel.setGyCode(jgGys.get(0).getGyCode());
        headModel.setGyName(jgGys.get(0).getGyName());
        headModel.setYlCount(req.getYlCount());
        headModel.setYlhCost(req.getYlhCost());
        headModel.setYlwCost(req.getYlwCost());
        headModel.setYlTotal(req.getYlTotal());
        headModel.setCpCount(req.getCpCount());
        headModel.setCphCost(req.getCphCost());
        headModel.setCpwCost(req.getCpwCost());
        headModel.setCpTotal(req.getCpTotal());
        headModel.setFlCost(jgGys.get(0).getFlCost());
        headModel.setRemark(req.getRemark());
        headModel.setDataStatus(DataStatus.ENTRY.getValue());
        headModel.setCkCode(req.getCkCode());
        headModel.setCkName(req.getCkName());
        if (req.getSaveType() == SaveType.EXEC_ACCOUNT.getValue()) {
            headModel.setJzDate(DateUtil.getCurrentTime());
            headModel.setJzrId(userModel.getUserId());
            headModel.setJzrCode(userModel.getUserCode());
            headModel.setJzrName(userModel.getUserName());
        }
        if (StringUtils.isBlank(req.getBillNo())) {
            Map<String, Object> map = new HashMap<>();
            map.put("ps_BillType", YwType.MD_JG.getValue());
            String billNo = this.commonService.getBillNoByCallProcedure(map);
            headModel.setBillNo(billNo);
        }else {
            headModel.setBillNo(req.getBillNo());
        }
        List<FrsJgYlModel> ylModels = new LinkedList<FrsJgYlModel>();
        long ylSerialNo = 1;
        for (FrsJgYlReq ylReq : ylList) {
            if (null == ylReq.getYlCount() || ylReq.getYlCount() == 0) {
                continue;
            }
            FrsJgYlModel ylModel = new FrsJgYlModel();
            ylModel.setBillNo(headModel.getBillNo());
            ylModel.setSerialNo(ylSerialNo++);
            ylModel.setPluId(ylReq.getPluId());
            ylModel.setPluCode(ylReq.getPluCode());
            ylModel.setPluName(ylReq.getPluName());
            ylModel.setBarCode(ylReq.getBarCode());
            ylModel.setSpec(ylReq.getSpec());
            ylModel.setUnit(ylReq.getUnit());
            ylModel.setExPluCode("*");
            ylModel.setHjPrice(ylReq.getHjPrice());
            ylModel.setWjPrice(ylReq.getWjPrice());
            ylModel.setPrice(ylReq.getPrice());
            ylModel.setjTaxRate(ylReq.getjTaxRate());
            ylModel.setYlPercent(ylReq.getYlPercent());
            ylModel.setYlCount(ylReq.getYlCount());
            ylModel.setYlhCost(ylReq.getYlhCost());
            ylModel.setYlwCost(ylReq.getYlwCost());
            ylModel.setYlTotal(ylReq.getYlTotal());
            ylModel.setRemark(ylReq.getRemark());
            ylModel.setjTaxcalType(ylReq.getjTaxcalType());
            ylModels.add(ylModel);
        }
        headModel.setYlList(ylModels);
        List<FrsJgCpModel> cpModels = new LinkedList<FrsJgCpModel>();
        long cpSerialNo = 1;
        for (FrsJgCpReq cpReq : cpList) {
            if (null == cpReq.getCpCount() || cpReq.getCpCount() == 0) {
                continue;
            }
            FrsJgCpModel cpModel = new FrsJgCpModel();
            cpModel.setBillNo(headModel.getBillNo());
            cpModel.setSerialNo(cpSerialNo++);
            cpModel.setPluId(cpReq.getPluId());
            cpModel.setPluCode(cpReq.getPluCode());
            cpModel.setPluName(cpReq.getPluName());
            cpModel.setBarCode(cpReq.getBarCode());
            cpModel.setSpec(cpReq.getSpec());
            cpModel.setUnit(cpReq.getUnit());
            cpModel.setExPluCode("*");
            cpModel.setHjPrice(cpReq.getHjPrice());
            cpModel.setWjPrice(cpReq.getWjPrice());
            cpModel.setPrice(cpReq.getPrice());
            cpModel.setjTaxRate(cpReq.getjTaxRate());
            cpModel.setCpPercent(cpReq.getCpPercent());
            cpModel.setCpCount(cpReq.getCpCount());
            cpModel.setCphCost(cpReq.getCphCost());
            cpModel.setCpwCost(cpReq.getCpwCost());
            cpModel.setCpTotal(cpReq.getCpTotal());
            cpModel.setRemark(cpReq.getRemark());
            cpModel.setjTaxcalType(cpReq.getjTaxcalType());
            cpModel.setFpPercent(cpReq.getFpPercent());
            cpModels.add(cpModel);
        }
        headModel.setCpList(cpModels);
        return headModel;
    }
}
