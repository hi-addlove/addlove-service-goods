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
import com.addlove.service.goods.constants.GoodsCommonConstants.BillType;
import com.addlove.service.goods.constants.GoodsOrdJhConstants.DataStatus;
import com.addlove.service.goods.constants.GoodsOrdJhConstants.ModelTags;
import com.addlove.service.goods.constants.GoodsOrdJhConstants.SaveType;
import com.addlove.service.goods.constants.GoodsOrdJhConstants.YwType;
import com.addlove.service.goods.constants.GoodsResponseCode;
import com.addlove.service.goods.exception.ServiceException;
import com.addlove.service.goods.message.ResponseMessage;
import com.addlove.service.goods.model.OrdJhBodyModel;
import com.addlove.service.goods.model.OrdJhHeadModel;
import com.addlove.service.goods.model.OrdJhQueryPageModel;
import com.addlove.service.goods.model.OrgManageModel;
import com.addlove.service.goods.model.PageModel;
import com.addlove.service.goods.model.StkStoreModel;
import com.addlove.service.goods.model.valid.OrdJhBodyReq;
import com.addlove.service.goods.model.valid.OrdJhHeadReq;
import com.addlove.service.goods.model.valid.CommonQueryDetailReq;
import com.addlove.service.goods.model.valid.CommonSearchReq;
import com.addlove.service.goods.model.valid.OrdJhQueryPageReq;
import com.addlove.service.goods.service.GoodsCommonService;
import com.addlove.service.goods.service.OrdJhService;
import com.addlove.service.goods.util.DateUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;

/**
 * 配送验收/无采购验收控制层
 * @author lw
 *
 */
@Controller
@RequestMapping(value = "/ordJh")
public class OrdJhController extends BaseController{
    @Autowired
    private OrdJhService ordJhService;
    
    @Autowired
    private GoodsCommonService commonService;
    
    /**
     * 分页查询配送验收单/无采购验收单
     * @param req
     * @return ResponseMessage
     */
    @RequestMapping(value = "/queryOrderJh", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage queryOrderJh(@RequestBody @Valid OrdJhQueryPageReq req) {
        OrdJhQueryPageModel queryModel = new OrdJhQueryPageModel();
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
        queryModel.setTag(req.getTag());
        queryModel.setDataStatus(req.getDataStatus());
        queryModel.setYwType(req.getYwType());
        List<JSONObject> backList = this.ordJhService.queryOrdJhHeadModelByPage(queryModel);
        PageModel pageModel = new PageModel();
        PageInfo<JSONObject> page = new PageInfo<>(backList);
        pageModel.setPageNo(page.getPageNum());
        pageModel.setPageSize(page.getPageSize());
        pageModel.setResult(page.getList());
        pageModel.setTotal(page.getTotal());
        return ResponseMessage.ok(pageModel);
    }
    
    /**
     * 新增无采购验收单
     * @param req
     * @return ResponseMessage
     */
    @RequestMapping(value = "/addNoPurchaseJh", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage addNoPurchaseJh(@RequestBody @Valid OrdJhHeadReq req) {
        if (StringUtils.isBlank(req.getBillNo()) && req.getSaveType() == SaveType.EXEC_ACCOUNT.getValue()) {
            throw new ServiceException(GoodsResponseCode.SAVE_BEFORE_ACCOUNT.getCode(), 
                    GoodsResponseCode.SAVE_BEFORE_ACCOUNT.getMsg());
        }
        OrdJhHeadModel headModel = this.getOrdJhHeadModel(req);
        if (req.getSaveType() == SaveType.SAVE.getValue()) {
            this.ordJhService.insertOrdJh(headModel);
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
     * 编辑无采购验收单
     * @param req
     * @return ResponseMessage
     */
    @RequestMapping(value = "/editNoPurchaseJh", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage editNoPurchaseJh(@RequestBody @Valid OrdJhHeadReq req) {
        if (StringUtils.isBlank(req.getBillNo())) {
            throw new ServiceException(GoodsResponseCode.BILLNO_NOT_BLANK.getCode(), 
                    GoodsResponseCode.BILLNO_NOT_BLANK.getMsg());
        }
        //编辑时先删除再插入
        OrdJhHeadModel headModel = this.getOrdJhHeadModel(req);
        if (req.getSaveType() == SaveType.SAVE.getValue()) {
            this.ordJhService.updateAllJhInfo(headModel);
        }else if (req.getSaveType() == SaveType.EXEC_ACCOUNT.getValue()) {
            Map<String, Object> accountMap = new HashMap<String, Object>();
            accountMap.put("ps_BillNo", headModel.getBillNo());
            accountMap.put("ps_YwType", headModel.getYwType());
            accountMap.put("pi_UserId", headModel.getJzrId());
            accountMap.put("ps_UserCode", headModel.getJzrCode());
            accountMap.put("ps_UserName", headModel.getJzrName());
            accountMap.put("pd_JzDate", headModel.getJzDate());
            this.ordJhService.updateAllJhInfo(headModel);
            //调用存储过程进行记账
            this.commonService.execAccountByCallProcedure(accountMap);
        }else {
            throw new ServiceException(GoodsResponseCode.BILL_OPRATE_ERROR.getCode(), 
                    GoodsResponseCode.BILL_OPRATE_ERROR.getMsg());
        }
        return ResponseMessage.ok();
    }
    
    /**
     * 删除验收单据
     * @param req
     * @return ResponseMessage
     */
    @RequestMapping(value = "/delOrdJh", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage delOrdJh(@RequestBody @Valid CommonQueryDetailReq req) {
        this.ordJhService.deleteJhData(req.getBillNo());
        return ResponseMessage.ok();
    }
    
    /**
     * 通过单号模糊搜索验收单据
     * @param req
     * @return ResponseMessage
     */
    @RequestMapping(value = "/searchOrdJhByBillNo", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage searchOrdJhByBillNo(@RequestBody @Valid CommonSearchReq req) {
        List<OrdJhHeadModel> billNos = this.ordJhService.searchOrdJhByBillNo(req.getSearchContent());
        return ResponseMessage.ok(billNos);
    }
    
    /**
     * 查询配送验收单/无采购验收单详情
     * @param req
     * @return queryOrderJhDetail
     */
    @RequestMapping(value = "/queryOrderJhDetail", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage queryOrderJhDetail(@RequestBody @Valid CommonQueryDetailReq req) {
        List<Map<String, Object>> resultList = this.ordJhService.queryBodysByBillNo(req.getBillNo());
        JSONObject backJson = new JSONObject();
        if (null != resultList && !resultList.isEmpty()) {
            JSONObject headJson = new JSONObject();
            Map<String, Object> headMap = resultList.get(0);
            headJson.put("billNo", headMap.get("BILLNO"));
            headJson.put("refBillNo", headMap.get("REFBILLNO"));
            headJson.put("orgCode", headMap.get("ORGCODE"));
            headJson.put("orgName", headMap.get("ORGNAME"));
            headJson.put("depId", headMap.get("DEPID"));
            headJson.put("depCode", headMap.get("DEPCODE"));
            headJson.put("depName", headMap.get("DEPNAME"));
            headJson.put("lrDate", headMap.get("LRDATE"));
            headJson.put("userCode", headMap.get("USERCODE"));
            headJson.put("userName", headMap.get("USERNAME"));
            headJson.put("tjDate", headMap.get("TJDATE"));
            headJson.put("tjrCode", headMap.get("TJRCODE"));
            headJson.put("tjrName", headMap.get("TJRNAME"));
            headJson.put("jzDate", headMap.get("JZDATE"));
            headJson.put("jzrCode", headMap.get("JZRCODE"));
            headJson.put("jzrName", headMap.get("JZRNAME"));
            headJson.put("zbOrgCode", headMap.get("ZBORGCODE"));
            headJson.put("zbOrgName", headMap.get("ZBORGNAME"));
            headJson.put("jhCount", headMap.get("JHCOUNT"));
            headJson.put("sTotal", headMap.get("STOTAL"));
            headJson.put("tag", headMap.get("TAG"));
            headJson.put("pickDate", headMap.get("PICKDATE"));
            headJson.put("ysrCode", headMap.get("YSRCODE"));
            headJson.put("ysrName", headMap.get("YSRNAME"));
            headJson.put("supCode", headMap.get("SUPCODE"));
            headJson.put("supName", headMap.get("SUPNAME"));
            headJson.put("inOrgCode", headMap.get("INORGCODE"));
            headJson.put("cntId", headMap.get("CNTID"));
            headJson.put("htCode", headMap.get("HTCODE"));
            headJson.put("htName", headMap.get("HTNAME"));
            headJson.put("ckCode", headMap.get("CKCODE"));
            headJson.put("ckName", headMap.get("CKNAME"));
            headJson.put("jhCount", headMap.get("JHCOUNT"));
            headJson.put("hCost", headMap.get("HCOST"));
            headJson.put("wCost", headMap.get("WCOST"));
            headJson.put("jtaxTotal", headMap.get("JTAXTOTAL"));
            headJson.put("cjTotal", headMap.get("CJTOTAL"));
            headJson.put("remark", headMap.get("REMARK"));
            headJson.put("psCost", headMap.get("PSCOST"));
            headJson.put("shrDate", headMap.get("SHRDATE"));
            headJson.put("telePhone", headMap.get("TELEPHONE"));
            headJson.put("fax", headMap.get("FAX"));
            headJson.put("linkMan", headMap.get("LINKMAN"));
            headJson.put("lkmTel", headMap.get("LKMTEL"));
            headJson.put("dataStatus", headMap.get("DATASTATUS"));
            backJson.put("headInfo", headJson);
            JSONArray bodyArray = new JSONArray();
            for (Map<String, Object> map : resultList) {
                JSONObject bodyJson = new JSONObject();
                bodyJson.put("serialNo", map.get("SERIALNO"));
                bodyJson.put("toSerialNo", map.get("TOSERIALNO"));
                bodyJson.put("pluId", map.get("PLUID"));
                bodyJson.put("pluCode", map.get("PLUCODE"));
                bodyJson.put("pluName", map.get("PLUNAME"));
                bodyJson.put("exPluCode", map.get("EXPLUCODE"));
                bodyJson.put("exPluName", map.get("EXPLUNAME"));
                bodyJson.put("barCode", map.get("BARCODE2"));
                bodyJson.put("spec", map.get("SPEC2"));
                bodyJson.put("unit", map.get("UNIT2"));
                bodyJson.put("carGoNo", map.get("CARGONO2"));
                bodyJson.put("pluType", map.get("PLUTYPE2"));
                bodyJson.put("depId", map.get("DEPID2"));
                bodyJson.put("depCode", map.get("DEPCODE2"));
                bodyJson.put("depName", map.get("DEPNAME2"));
                bodyJson.put("hjPrice", map.get("HJPRICE2"));
                bodyJson.put("wjPrice", map.get("WJPRICE2"));
                bodyJson.put("price", map.get("PRICE2"));
                bodyJson.put("jhCount", map.get("JHCOUNT2"));
                bodyJson.put("psShCount", map.get("PSSHCOUNT2"));
                bodyJson.put("psPrice", map.get("PSPRICE2"));
                bodyJson.put("packUnit", map.get("PACKUNIT2"));
                bodyJson.put("packQty", map.get("PACKQTY2"));
                bodyJson.put("packCount", map.get("PACKCOUNT2"));
                bodyJson.put("sglCount", map.get("SGLCOUNT2"));
                bodyJson.put("hCost", map.get("HCOST2"));
                bodyJson.put("wCost", map.get("WCOST2"));
                bodyJson.put("jTaxRate", map.get("JTAXRATE2"));
                bodyJson.put("jTaxTotal", map.get("JTAXTOTAL2"));
                bodyJson.put("sTotal", map.get("STOTAL2"));
                bodyJson.put("cjTotal", map.get("CJTOTAL2"));
                bodyJson.put("cjRate", map.get("CJRATE2"));
                bodyJson.put("psCost", map.get("PSCOST2"));
                bodyJson.put("hjsTotal", map.get("HJSTOTAL2"));
                bodyJson.put("wjsTotal", map.get("WJSTOTAL2"));
                bodyJson.put("jTaxCalType", map.get("JTAXCALTYPE2"));
                bodyJson.put("wPsPrice", map.get("WPSPRICE2"));
                bodyJson.put("wPsCost", map.get("WPSCOST2"));
                bodyJson.put("xTaxRate", map.get("XTAXRATE2"));
                bodyJson.put("xTaxTotal", map.get("XTAXTOTAL2"));
                bodyJson.put("materialCode", map.get("MATERIALCODE2"));
                bodyJson.put("scDate", map.get("SCDATE2"));
                bodyJson.put("dqDate", map.get("DQDATE2"));
                bodyJson.put("bzDays", map.get("BZDAYS2"));
                bodyJson.put("zpType", map.get("ZPTYPE2"));
                bodyJson.put("cxInfo", map.get("CXINFO2"));
                bodyJson.put("remark", map.get("REMARK2"));
                bodyArray.add(bodyJson);
            }
            backJson.put("bodyInfo", bodyArray);
        }
        return ResponseMessage.ok(backJson);
    }
    
    /**
     * 组装验收单数据
     * @param req
     * @return OrdJhHeadModel
     */
    private OrdJhHeadModel getOrdJhHeadModel(OrdJhHeadReq req) {
        List<OrdJhBodyReq> bodyList = req.getBodyList();
        if (null == bodyList || bodyList.isEmpty()) {
            throw new ServiceException(GoodsResponseCode.SKU_NOT_BLANK.getCode(), 
                    GoodsResponseCode.SKU_NOT_BLANK.getMsg());
        }
        OrdJhHeadModel headModel = new OrdJhHeadModel();
        headModel.setLrDate(DateUtil.getCurrentTime());
        headModel.setUserId(10000000041L);
        headModel.setUserCode("1");
        headModel.setUserName("超级户");
        headModel.setYwType(YwType.NO_PURCHASE_ACCEPTANCE.getValue());
        headModel.setOrgCode(req.getOrgCode());
        headModel.setOrgName(req.getOrgName());
        OrgManageModel orgModel = this.commonService.getOrgModel(req.getOrgCode());
        headModel.setInOrgCode(orgModel.getInOrgCode());
        headModel.sethOrgCode(orgModel.getInOrgCode());
        headModel.setZbOrgCode(req.getOrgCode());
        List<StkStoreModel> storeList = this.commonService.getStoreList(req.getOrgCode());
        if (null == storeList || storeList.isEmpty()) {
            throw new ServiceException(GoodsResponseCode.CK_NOT_BLANK.getCode(), 
                    GoodsResponseCode.CK_NOT_BLANK.getMsg());
        }
        headModel.setCkCode(storeList.get(0).getCkCode());
        headModel.setCkName(storeList.get(0).getCkName());
        headModel.setDepId(req.getDepId());
        headModel.setDepCode(req.getDepCode());
        headModel.setDepName(req.getDepName());
        headModel.setSupCode(req.getSupCode());
        headModel.setSupName(req.getSupName());
        headModel.setCntId(req.getCntId());
        headModel.setHtCode(req.getHtCode());
        headModel.setHtName(req.getHtName());
        headModel.setJhCount(req.getJhCount());
        headModel.sethCost(req.gethCost());
        headModel.setwCost(req.getwCost());
        headModel.setJtaxTotal(req.getJtaxTotal());
        headModel.setPsCost(req.getPsCost());
        headModel.setsTotal(req.getsTotal());
        headModel.setCjTotal(req.getCjTotal());
        headModel.setRemark(req.getRemark());
        headModel.setDataStatus(DataStatus.ENTRY.getValue());
        headModel.setTag(ModelTags.NORMAL_ACCEPTANCE.getValue());
        headModel.setTelePhone(req.getTelePhone());
        headModel.setFax(req.getFax());
        headModel.setLinkMan(req.getLinkMan());
        headModel.setLkmTel(req.getLkmTel());
        headModel.setJyMode(req.getJyMode());
        headModel.setJsCode(req.getJsCode());
        //调用存储过程生成无采购验收单号
        if (StringUtils.isBlank(req.getBillNo())) {
            Map<String, Object> map = new HashMap<>();
            map.put("ps_BillType", BillType.ACCEPTANCE_APPLY.getValue());
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
        //组织明细
        List<OrdJhBodyModel> bodyModels = new LinkedList<OrdJhBodyModel>();
        long serialNo = 1;
        for (OrdJhBodyReq bodyReq : bodyList) {
            if (bodyReq.getJhCount() == 0) {
                continue;
            }
            OrdJhBodyModel bodyModel = new OrdJhBodyModel();
            bodyModel.setBillNo(headModel.getBillNo());
            serialNo++;
            bodyModel.setSerialNo(serialNo);
            bodyModel.setToSerialNo(serialNo);
            bodyModel.setPluId(bodyReq.getPluId());
            bodyModel.setPluCode(bodyReq.getPluCode());
            bodyModel.setPluName(bodyReq.getPluName());
            bodyModel.setExPluCode("*");
            bodyModel.setBarCode(bodyReq.getBarCode());
            bodyModel.setSpec(bodyReq.getSpec());
            bodyModel.setUnit(bodyReq.getUnit());
            bodyModel.setCarGoNo(bodyReq.getCarGoNo());
            bodyModel.setDepId(bodyReq.getDepId());
            bodyModel.setDepCode(bodyReq.getDepCode());
            bodyModel.setDepName(bodyReq.getDepName());
            bodyModel.setHjPrice(bodyReq.getHjPrice());
            bodyModel.setWjPrice(bodyReq.getWjPrice());
            bodyModel.setPsPrice(bodyReq.getPsPrice());
            bodyModel.setPrice(bodyReq.getPrice());
            bodyModel.setjTaxRate(bodyReq.getjTaxRate());
            bodyModel.setPackUnit(bodyReq.getPackUnit());
            bodyModel.setPackQty(bodyReq.getPackQty());
            bodyModel.setPackCount(bodyReq.getPackCount());
            bodyModel.setSglCount(bodyReq.getSglCount());
            bodyModel.setJhCount(bodyReq.getJhCount());
            bodyModel.setPsShCount(bodyReq.getPsShCount());
            bodyModel.sethCost(bodyReq.gethCost());
            bodyModel.setwCost(bodyReq.getwCost());
            bodyModel.setjTaxTotal(bodyReq.getjTaxTotal());
            bodyModel.setPsCost(bodyReq.getPsCost());
            bodyModel.setsTotal(bodyReq.getsTotal());
            bodyModel.setCjTotal(bodyReq.getCjTotal());
            bodyModel.setCjRate(bodyReq.getCjRate());
            bodyModel.setScDate(bodyReq.getScDate());
            bodyModel.setDqDate(bodyReq.getDqDate());
            bodyModel.setBzDays(bodyReq.getBzDays());
            bodyModel.setCxInfo(bodyReq.getCxInfo());
            bodyModel.setRemark(bodyReq.getRemark());
            bodyModel.setHjsTotal(bodyReq.getHjsTotal());
            bodyModel.setWjsTotal(bodyReq.getWjsTotal());
            bodyModel.setwPsPrice(bodyReq.getwPsPrice());
            bodyModel.setwPsCost(bodyReq.getwPsCost());
            bodyModel.setxTaxRate(bodyReq.getxTaxRate());
            bodyModel.setxTaxTotal(bodyReq.getxTaxTotal());
            bodyModels.add(bodyModel);
        }
        headModel.setBodyList(bodyModels);
        return headModel;
    }
}
