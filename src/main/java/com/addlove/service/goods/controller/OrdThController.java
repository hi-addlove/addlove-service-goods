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
import com.addlove.service.goods.constants.GoodsOrdJhConstants.DataStatus;
import com.addlove.service.goods.constants.GoodsOrdJhConstants.SaveType;
import com.addlove.service.goods.constants.GoodsOrdJhConstants.YwType;
import com.addlove.service.goods.exception.ServiceException;
import com.addlove.service.goods.message.ResponseMessage;
import com.addlove.service.goods.model.OrdThBodyModel;
import com.addlove.service.goods.model.OrdThHeadModel;
import com.addlove.service.goods.model.OrdThQueryPageModel;
import com.addlove.service.goods.model.OrgManageModel;
import com.addlove.service.goods.model.PageModel;
import com.addlove.service.goods.model.valid.CommonQueryDetailReq;
import com.addlove.service.goods.model.valid.OrdThBodyReq;
import com.addlove.service.goods.model.valid.OrdThHeadReq;
import com.addlove.service.goods.model.valid.OrdThQueryPageReq;
import com.addlove.service.goods.service.GoodsCommonService;
import com.addlove.service.goods.service.OrdThService;
import com.addlove.service.goods.util.DateUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;

/**
 * 退货单控制层
 * @author lw
 *
 */
@Controller
@RequestMapping(value = "/ordTh")
public class OrdThController extends BaseController {
    @Autowired
    private OrdThService ordThService;
    
    @Autowired
    private GoodsCommonService commonService;
    
    /**
     * 分页查询退货单
     * @param req
     * @return ResponseMessage
     */
    @RequestMapping(value = "/queryOrderTh", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage queryOrderTh(@RequestBody @Valid OrdThQueryPageReq req) {
        OrdThQueryPageModel queryModel = new OrdThQueryPageModel();
        queryModel.setPageNo(req.getPageNo());
        queryModel.setPageSize(req.getPageSize());
        queryModel.setOrgCode(req.getOrgCode());
        queryModel.setYwType(req.getYwType());
        queryModel.setBillNo(req.getBillNo());
        if (StringUtils.isNotBlank(req.getStartDate())) {
            queryModel.setStartDate(req.getStartDate() + " 00:00:00");
        }
        if (StringUtils.isNotBlank(req.getEndDate())) {
            queryModel.setEndDate(req.getEndDate() + " 23:59:59");
        }
        queryModel.setDataStatus(req.getDataStatus());
        queryModel.setDepCode(req.getDepCode());
        queryModel.setSupCode(req.getSupCode());
        List<OrdThHeadModel> thList = this.ordThService.queryOrdThPage(queryModel);
        PageModel pageModel = new PageModel();
        Page<OrdThHeadModel> page = (Page<OrdThHeadModel>) thList;
        pageModel.setPageNo(page.getPageNum());
        pageModel.setPageSize(page.getPageSize());
        pageModel.setResult(page.getResult());
        pageModel.setTotal(page.getTotal());
        return ResponseMessage.ok(pageModel);
    }
    
    /**
     * 新增采购退货
     * @param req
     * @return ResponseMessage
     */
    @RequestMapping(value = "/addPurchaseReturn", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage addPurchaseReturn(@RequestBody @Valid OrdThHeadReq req) {
        if (StringUtils.isBlank(req.getBillNo()) && req.getSaveType() == SaveType.EXEC_ACCOUNT.getValue()) {
            throw new ServiceException(GoodsResponseCode.SAVE_BEFORE_ACCOUNT.getCode(), 
                    GoodsResponseCode.SAVE_BEFORE_ACCOUNT.getMsg());
        }
        OrdThHeadModel headModel = this.getOrdThHeadModel(req);
        if (req.getSaveType() == SaveType.SAVE.getValue()) {
            this.ordThService.insertOrderTh(headModel);
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
     * 编辑采购退货
     * @param req
     * @return ResponseMessage
     */
    @RequestMapping(value = "/editPurchaseReturn", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage editPurchaseReturn(@RequestBody @Valid OrdThHeadReq req) {
        if (StringUtils.isBlank(req.getBillNo())) {
            throw new ServiceException(GoodsResponseCode.BILLNO_NOT_BLANK.getCode(), 
                    GoodsResponseCode.BILLNO_NOT_BLANK.getMsg());
        }
        OrdThHeadModel headModel = this.getOrdThHeadModel(req);
        if (req.getSaveType() == SaveType.SAVE.getValue()) {
            this.ordThService.updateAllThInfo(headModel);
        }else if (req.getSaveType() == SaveType.EXEC_ACCOUNT.getValue()) {
            Map<String, Object> accountMap = new HashMap<String, Object>();
            accountMap.put("ps_BillNo", headModel.getBillNo());
            accountMap.put("ps_YwType", headModel.getYwType());
            accountMap.put("pi_UserId", headModel.getJzrId());
            accountMap.put("ps_UserCode", headModel.getJzrCode());
            accountMap.put("ps_UserName", headModel.getJzrName());
            accountMap.put("pd_JzDate", headModel.getJzDate());
            this.ordThService.updateAllThInfo(headModel);
            //调用存储过程进行记账
            this.commonService.execAccountByCallProcedure(accountMap);
        }else {
            throw new ServiceException(GoodsResponseCode.BILL_OPRATE_ERROR.getCode(), 
                    GoodsResponseCode.BILL_OPRATE_ERROR.getMsg());
        }
        return ResponseMessage.ok();
    }
    
    /**
     * 删除退货单据
     * @param req
     * @return ResponseMessage
     */
    @RequestMapping(value = "/delOrdTh", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage delOrdTh(@RequestBody @Valid CommonQueryDetailReq req) {
        this.ordThService.deleteThData(req.getBillNo());
        return ResponseMessage.ok();
    }
    
    /**
     * 查询退货单详情
     * @param req
     * @return ResponseMessage
     */
    @RequestMapping(value = "/queryThDetailsByBillNo", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage queryThDetailsByBillNo(@RequestBody @Valid CommonQueryDetailReq req) {
        List<Map<String, Object>> resultList = this.ordThService.queryThDetailsByBillNo(req.getBillNo());
        JSONObject backJson = new JSONObject();
        if (null != resultList && !resultList.isEmpty()) {
            JSONObject headJson = new JSONObject();
            Map<String, Object> headMap = resultList.get(0);
            headJson.put("billNo", headMap.get("BILLNO"));
            headJson.put("lrDate", headMap.get("LRDATE"));
            headJson.put("userId", headMap.get("USERID"));
            headJson.put("userCode", headMap.get("USERCODE"));
            headJson.put("userName", headMap.get("USERNAME"));
            headJson.put("tjDate", headMap.get("TJDATE"));
            headJson.put("tjrId", headMap.get("TJRID"));
            headJson.put("tjrCode", headMap.get("TJRCODE"));
            headJson.put("tjrName", headMap.get("TJRNAME"));
            headJson.put("jzDate", headMap.get("JZDATE"));
            headJson.put("jzrId", headMap.get("JZRID"));
            headJson.put("jzrCode", headMap.get("JZRCODE"));
            headJson.put("jzrName", headMap.get("JZRNAME"));
            headJson.put("jzrName", headMap.get("JZRNAME"));
            headJson.put("ywType", headMap.get("YWTYPE"));
            headJson.put("zdrId", headMap.get("ZDRID"));
            headJson.put("zdrCode", headMap.get("ZDRCODE"));
            headJson.put("zdrName", headMap.get("ZDRNAME"));
            headJson.put("billType", headMap.get("BILLTYPE"));
            headJson.put("refBillType", headMap.get("REFBILLTYPE"));
            headJson.put("refBillNo", headMap.get("REFBILLNO"));
            headJson.put("orgCode", headMap.get("ORGCODE"));
            headJson.put("orgName", headMap.get("ORGNAME"));
            headJson.put("inOrgCode", headMap.get("INORGCODE"));
            headJson.put("zbOrgCode", headMap.get("ZBORGCODE"));
            headJson.put("zbOrgName", headMap.get("ZBORGNAME"));
            headJson.put("depId", headMap.get("DEPID"));
            headJson.put("depCode", headMap.get("DEPCODE"));
            headJson.put("depName", headMap.get("DEPNAME"));
            headJson.put("supCode", headMap.get("SUPCODE"));
            headJson.put("supName", headMap.get("SUPNAME"));
            headJson.put("cntId", headMap.get("CNTID"));
            headJson.put("htCode", headMap.get("HTCODE"));
            headJson.put("htName", headMap.get("HTNAME"));
            headJson.put("jyMode", headMap.get("JYMODE"));
            headJson.put("jsCode", headMap.get("JSCODE"));
            headJson.put("ckCode", headMap.get("CKCODE"));
            headJson.put("ckName", headMap.get("CKNAME"));
            headJson.put("dataStatus", headMap.get("DATASTATUS"));
            headJson.put("thCount", headMap.get("THCOUNT"));
            headJson.put("hCost", headMap.get("HCOST"));
            headJson.put("wCost", headMap.get("WCOST"));
            headJson.put("jtaxTotal", headMap.get("JTAXTOTAL"));
            headJson.put("sTotal", headMap.get("STOTAL"));
            headJson.put("cjTotal", headMap.get("CJTOTAL"));
            headJson.put("psCost", headMap.get("PSCOST"));
            headJson.put("thReason", headMap.get("THREASON"));
            headJson.put("ckMode", headMap.get("CKMODE"));
            headJson.put("wpsTotal", headMap.get("WPSTOTAL"));
            headJson.put("xTaxTotal", headMap.get("XTAXTOTAL"));
            headJson.put("telePhone", headMap.get("TELEPHONE"));
            headJson.put("fax", headMap.get("FAX"));
            headJson.put("linkMan", headMap.get("LINKMAN"));
            headJson.put("lkmTel", headMap.get("LKMTEL"));
            headJson.put("remark", headMap.get("REMARK"));
            headJson.put("refThBillNo", headMap.get("REFTHBILLNO"));
            backJson.put("headInfo", headJson);
            JSONArray bodyArray = new JSONArray();
            for (Map<String, Object> map : resultList) {
                JSONObject bodyJson = new JSONObject();
                bodyJson.put("serialNo", map.get("SERIALNO"));
                bodyJson.put("pluId", map.get("PLUID"));
                bodyJson.put("pluCode", map.get("PLUCODE"));
                bodyJson.put("pluName", map.get("PLUNAME"));
                bodyJson.put("exPluCode", map.get("EXPLUCODE"));
                bodyJson.put("barCode", map.get("BARCODE"));
                bodyJson.put("spec", map.get("SPEC"));
                bodyJson.put("unit", map.get("UNIT"));
                bodyJson.put("carGoNo", map.get("CARGONO"));
                bodyJson.put("pluType", map.get("PLUTYPE"));
                bodyJson.put("hjPrice", map.get("HJPRICE2"));
                bodyJson.put("wjPrice", map.get("WJPRICE2"));
                bodyJson.put("jTaxRate", map.get("JTAXRATE2"));
                bodyJson.put("price", map.get("PRICE2"));
                bodyJson.put("psPrice", map.get("PSPRICE2"));
                bodyJson.put("packUnit", map.get("PACKUNIT2"));
                bodyJson.put("packQty", map.get("PACKQTY2"));
                bodyJson.put("packCount", map.get("PACKCOUNT2"));
                bodyJson.put("sglCount", map.get("SGLCOUNT2"));
                bodyJson.put("thCount", map.get("THCOUNT2"));
                bodyJson.put("hCost", map.get("HCOST2"));
                bodyJson.put("wCost", map.get("WCOST2"));
                bodyJson.put("jTaxTotal", map.get("JTAXTOTAL2"));
                bodyJson.put("sTotal", map.get("STOTAL2"));
                bodyJson.put("cjTotal", map.get("CJTOTAL2"));
                bodyJson.put("cjRate", map.get("CJRATE2"));
                bodyJson.put("psCost", map.get("PSCOST2"));
                bodyJson.put("remark", map.get("REMARK2"));
                bodyJson.put("hjsTotal", map.get("HJSTOTAL2"));
                bodyJson.put("wjsTotal", map.get("WJSTOTAL2"));
                bodyJson.put("thReason", headMap.get("THREASON2"));
                bodyJson.put("wPsPrice", map.get("WPSPRICE2"));
                bodyJson.put("wPsTotal", map.get("WPSTOTAL2"));
                bodyJson.put("xTaxRate", map.get("XTAXRATE2"));
                bodyJson.put("xTaxTotal", map.get("XTAXTOTAL2"));
                bodyArray.add(bodyJson);
            }
            backJson.put("bodyInfo", bodyArray);
        }
        return ResponseMessage.ok(backJson);
    }
    
    /**
     * 封装退货单数据
     * @param req
     * @return OrdThHeadModel
     */
    private OrdThHeadModel getOrdThHeadModel(OrdThHeadReq req) {
        List<OrdThBodyReq> bodyList = req.getBodyList();
        if (null == bodyList || bodyList.isEmpty()) {
            throw new ServiceException(GoodsResponseCode.SKU_NOT_BLANK.getCode(), 
                    GoodsResponseCode.SKU_NOT_BLANK.getMsg());
        }
        OrdThHeadModel headModel = new OrdThHeadModel();
        headModel.setLrDate(DateUtil.getCurrentTime());
        headModel.setUserId(10000000041L);
        headModel.setUserCode("1");
        headModel.setUserName("超级户");
        headModel.setYwType(YwType.PURCHASE_RETURN.getValue());
        headModel.setBillType(req.getBillType());
        headModel.setRefBillNo(req.getRefBillNo());
        headModel.setOrgCode(req.getOrgCode());
        headModel.setOrgName(req.getOrgName());
        OrgManageModel orgModel = this.commonService.getOrgModel(req.getOrgCode());
        headModel.setInOrgCode(orgModel.getInOrgCode());
        headModel.setZbOrgCode(req.getOrgCode());
        headModel.setDepId(req.getDepId());
        headModel.setDepCode(req.getDepCode());
        headModel.setDepName(req.getDepName());
        headModel.setSupCode(req.getSupCode());
        headModel.setSupName(req.getSupName());
        headModel.setCntId(req.getCntId());
        headModel.setHtCode(req.getHtCode());
        headModel.setHtName(req.getHtName());
        headModel.setJyMode(req.getJyMode());
        headModel.setJsCode(req.getJsCode());
        headModel.setCkCode(req.getCkCode());
        headModel.setCkName(req.getCkName());
        headModel.setDataStatus(DataStatus.ENTRY.getValue());
        headModel.setThCount(req.getThCount());
        headModel.sethCost(req.gethCost());
        headModel.setwCost(req.getwCost());
        headModel.setjTaxTotal(req.getjTaxTotal());
        headModel.setsTotal(req.getsTotal());
        headModel.setCjTotal(req.getCjTotal());
        headModel.setPsCost(req.getPsCost());
        headModel.setThReason(req.getThReason());
        headModel.setWpsTotal(req.getWpsTotal());
        headModel.setxTaxTotal(req.getxTaxTotal());
        headModel.setTelephone(req.getTelephone());
        headModel.setFax(req.getFax());
        headModel.setLinkMan(req.getLinkMan());
        headModel.setLkmTel(req.getLkmTel());
        headModel.setRemark(req.getRemark());
        //调用存储过程生成采购退货单号
        if (StringUtils.isBlank(req.getBillNo())) {
            Map<String, Object> map = new HashMap<>();
            map.put("ps_BillType", BillType.PURCHASE_RETURN.getValue());
            String billNo = this.commonService.getBillNoByCallProcedure(map);
            headModel.setBillNo(billNo);
        }else {
            headModel.setBillNo(req.getBillNo());
        }
        if (req.getSaveType() == SaveType.EXEC_ACCOUNT.getValue()) {
            headModel.setJzrId(10000000041L);
            headModel.setJzrCode("1");
            headModel.setJzrName("超级户");
            headModel.setJzDate(DateUtil.getCurrentTime());
        }
        //封装明细
        List<OrdThBodyModel> bodyModels = new LinkedList<OrdThBodyModel>();
        long serialNo = 1;
        for (OrdThBodyReq bodyReq : bodyList) {
            if (bodyReq.getThCount() == 0) {
                continue;
            }
            OrdThBodyModel bodyModel= new OrdThBodyModel();
            bodyModel.setBillNo(headModel.getBillNo());
            bodyModel.setSerialNo(serialNo++);
            bodyModel.setPluId(bodyReq.getPluId());
            bodyModel.setPluCode(bodyReq.getPluCode());
            bodyModel.setPluName(bodyReq.getPluName());
            bodyModel.setExPluCode(bodyReq.getExPluCode());
            bodyModel.setBarCode(bodyReq.getBarCode());
            bodyModel.setSpec(bodyReq.getSpec());
            bodyModel.setUnit(bodyReq.getUnit());
            bodyModel.setCarGoNo(bodyReq.getCarGoNo());
            bodyModel.setPluType(bodyReq.getPluType());
            bodyModel.setDepId(bodyReq.getDepId());
            bodyModel.setDepCode(bodyReq.getDepCode());
            bodyModel.setDepName(bodyReq.getDepName());
            bodyModel.setHjPrice(bodyReq.getHjPrice());
            bodyModel.setWjPrice(bodyReq.getWjPrice());
            bodyModel.setPrice(bodyReq.getPrice());
            bodyModel.setPsPrice(bodyReq.getPsPrice());
            bodyModel.setPackUnit(bodyReq.getPackUnit());
            bodyModel.setPackQty(bodyReq.getPackQty());
            bodyModel.setPackCount(bodyReq.getPackCount());
            bodyModel.setSglCount(bodyReq.getSglCount());
            bodyModel.setThCount(bodyReq.getThCount());
            bodyModel.sethCost(bodyReq.gethCost());
            bodyModel.setwCost(bodyReq.getwCost());
            bodyModel.setjTaxTotal(bodyReq.getjTaxTotal());
            bodyModel.setsTotal(bodyReq.getsTotal());
            bodyModel.setCjTotal(bodyReq.getCjTotal());
            bodyModel.setCjRate(bodyReq.getCjRate());
            bodyModel.setPsCost(bodyReq.getPsCost());
            bodyModel.setRemark(bodyReq.getRemark());
            bodyModel.setHjsTotal(bodyReq.getHjsTotal());
            bodyModel.setWjsTotal(bodyReq.getWjsTotal());
            bodyModel.setThReason(bodyReq.getThReason());
            bodyModel.setwPsPrice(bodyReq.getwPsPrice());
            bodyModel.setwPsTotal(bodyReq.getwPsTotal());
            bodyModel.setxTaxRate(bodyReq.getxTaxRate());
            bodyModel.setxTaxTotal(bodyReq.getxTaxTotal());
            bodyModel.setSupCode(bodyReq.getSupCode());
            bodyModel.setSupName(bodyReq.getSupName());
            bodyModels.add(bodyModel);
        }
        headModel.setBodyList(bodyModels);
        return headModel;
    }
}
