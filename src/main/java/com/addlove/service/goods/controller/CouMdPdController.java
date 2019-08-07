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
import com.addlove.service.goods.constants.GoodsMdPdConstants.CouStatus;
import com.addlove.service.goods.constants.GoodsMdPdConstants.SaveType;
import com.addlove.service.goods.constants.GoodsMdPdConstants.YwType;
import com.addlove.service.goods.constants.GoodsOrdJhConstants.DataStatus;
import com.addlove.service.goods.exception.ServiceException;
import com.addlove.service.goods.message.ResponseMessage;
import com.addlove.service.goods.model.CouMdPdBodyModel;
import com.addlove.service.goods.model.CouMdPdHeadModel;
import com.addlove.service.goods.model.MdPdAccountModel;
import com.addlove.service.goods.model.MdPdPageModel;
import com.addlove.service.goods.model.OrgManageModel;
import com.addlove.service.goods.model.PageModel;
import com.addlove.service.goods.model.SkuPluModel;
import com.addlove.service.goods.model.StkStoreModel;
import com.addlove.service.goods.model.valid.CommonQueryDetailReq;
import com.addlove.service.goods.model.valid.CouMdPdBodyReq;
import com.addlove.service.goods.model.valid.CouMdPdHeadReq;
import com.addlove.service.goods.model.valid.MdPdPageReq;
import com.addlove.service.goods.model.valid.MdPdSkuReq;
import com.addlove.service.goods.service.CouMdPdService;
import com.addlove.service.goods.service.GoodsCommonService;
import com.addlove.service.goods.util.DateUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;

/**
 * 门店盘点控制层
 * @author lw
 *
 */
@Controller
@RequestMapping(value = "/mdPd")
public class CouMdPdController extends BaseController{
    @Autowired
    private CouMdPdService couMdPdService;
    
    @Autowired
    private GoodsCommonService commonService;
    
    /**
     * 查询盘点列表
     * @param req
     * @return ResponseMessage
     */
    @RequestMapping(value = "/queryMdPdPage", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage queryMdPdPage(@RequestBody @Valid MdPdPageReq req) {
        MdPdPageModel queryModel = new MdPdPageModel();
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
        queryModel.setCouStatus(req.getCouStatus());
        queryModel.setBillType(req.getBillType());
        queryModel.setDepId(req.getDepId());
        List<CouMdPdHeadModel> pdList = this.couMdPdService.queryMdPdPage(queryModel);
        Page<CouMdPdHeadModel> page = (Page<CouMdPdHeadModel>) pdList;
        PageModel pageModel = new PageModel();
        pageModel.setPageNo(page.getPageNum());
        pageModel.setPageSize(page.getPageSize());
        pageModel.setResult(page.getResult());
        pageModel.setTotal(page.getTotal());
        return ResponseMessage.ok(pageModel);
    }
    
    /**
     * 编辑或记账
     * @param req
     * @return ResponseMessage
     */
    @RequestMapping(value = "/editOrAccount", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage editOrAccount(@RequestBody @Valid CouMdPdHeadReq req) {
        if (StringUtils.isBlank(req.getBillNo())) {
            throw new ServiceException(GoodsResponseCode.BILLNO_NOT_BLANK.getCode(), 
                    GoodsResponseCode.BILLNO_NOT_BLANK.getMsg());
        }
        CouMdPdHeadModel headModel = this.getMdPdHeadModel(req);
        if (req.getSaveType() == SaveType.EDIT_SAVE.getValue()) {
            this.couMdPdService.editPdInfo(headModel);
        }else if (req.getSaveType() == SaveType.PD_ACCOUNT.getValue()) {
            headModel.setJzrId(10000000041L);
            headModel.setJzrCode("1");
            headModel.setJzrName("超级户");
            headModel.setJzDate(DateUtil.getCurrentTime());
            this.couMdPdService.execPdAccountProcedure(headModel);
        }
        JSONObject json = new JSONObject();
        json.put("billNo", headModel.getBillNo());
        return ResponseMessage.ok(json);
    }
    
    /**
     * 启动
     * @param req
     * @return ResponseMessage
     */
    @RequestMapping(value = "/startUp", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage startUp(@RequestBody @Valid CouMdPdHeadReq req) {
        CouMdPdHeadModel headModel = this.getMdPdHeadModel(req);
        //this.couMdPdService.addPdInfoAndStartUp(headModel);
        this.couMdPdService.addPdInfo(headModel);
        this.couMdPdService.execStartPdProcedure(headModel.getBillNo());
        JSONObject backJson = new JSONObject();
        String pdType = this.couMdPdService.getPdType(headModel.getOrgCode());
        JSONObject pdTypeJson = new JSONObject();
        pdTypeJson.put("pdType", pdType);
        pdTypeJson.put("billNo", headModel.getBillNo());
        backJson.put("typeInfo", pdTypeJson);
        //返回启动盘点后的账面数量等数据
        List<MdPdAccountModel> models = this.couMdPdService.getMdPdAccountPlus(headModel.getBillNo(), headModel.getOrgCode());
        JSONArray array = new JSONArray();
        if (null != models && !models.isEmpty()) {
            for (MdPdAccountModel model : models) {
                JSONObject json = (JSONObject) JSONObject.toJSON(model);
                array.add(json);
            }
        }
        backJson.put("pluInfo", array);
        return ResponseMessage.ok(backJson);
    }
    
    /**
     * 删除盘点信息
     * @param req
     * @return ResponseMessage
     */
    @RequestMapping(value = "/deleteMdPd", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage deleteMdPd(@RequestBody @Valid CommonQueryDetailReq req) {
        this.couMdPdService.deleteMdPd(req.getBillNo());
        return ResponseMessage.ok();
    }
    
    /**
     * 查询盘点详情
     * @param req
     * @return ResponseMessage
     */
    @RequestMapping(value = "/queryMdPdDetails", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage queryMdPdDetails(@RequestBody @Valid CommonQueryDetailReq req) {
        List<Map<String,Object>> resultList = this.couMdPdService.queryMdPdDetails(req.getBillNo());
        JSONObject backJson = new JSONObject();
        if (null != resultList && !resultList.isEmpty()) {
            JSONObject headJson = new JSONObject();
            Map<String, Object> headMap = resultList.get(0);
            headJson.put("billNo", headMap.get("BILLNO"));
            headJson.put("lrDate", headMap.get("LRDATE"));
            headJson.put("userId", headMap.get("USERID"));
            headJson.put("userCode", headMap.get("USERCODE"));
            headJson.put("userName", headMap.get("USERNAME"));
            headJson.put("jzDate", headMap.get("JZDATE"));
            headJson.put("jzrId", headMap.get("JZRID"));
            headJson.put("jzrCode", headMap.get("JZRCODE"));
            headJson.put("jzrName", headMap.get("JZRNAME"));
            headJson.put("orgCode", headMap.get("ORGCODE"));
            headJson.put("orgName", headMap.get("ORGNAME"));
            headJson.put("inOrgCode", headMap.get("INORGCODE"));
            headJson.put("depId", headMap.get("DEPID"));
            headJson.put("depCode", headMap.get("DEPCODE"));
            headJson.put("depName", headMap.get("DEPNAME"));
            headJson.put("ckCode", headMap.get("CKCODE"));
            headJson.put("ckName", headMap.get("CKNAME"));
            headJson.put("pdStartDate", headMap.get("PDSTARTDATE"));
            headJson.put("ywType", headMap.get("YWTYPE"));
            headJson.put("billType", headMap.get("BILLTYPE"));
            headJson.put("couStatus", headMap.get("COUSTATUS"));
            headJson.put("zmCount", headMap.get("ZMCOUNT"));
            headJson.put("zmHCost", headMap.get("ZMHCOST"));
            headJson.put("zmWCost", headMap.get("ZMWCOST"));
            headJson.put("zmSCost", headMap.get("ZMSCOST"));
            headJson.put("sJCount", headMap.get("SJCOUNT"));
            headJson.put("sJhCost", headMap.get("SJHCOST"));
            headJson.put("sJwCost", headMap.get("SJWCOST"));
            headJson.put("sJsCost", headMap.get("SJSCOST"));
            headJson.put("ykCount", headMap.get("YKCOUNT"));
            headJson.put("ykHCost", headMap.get("YKHCOST"));
            headJson.put("ykWCost", headMap.get("YKWCOST"));
            headJson.put("ykSCost", headMap.get("YKSCOST"));
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
                bodyJson.put("exPluCode", map.get("EXPLUCODE"));
                bodyJson.put("exPluName", map.get("EXPLUNAME"));
                bodyJson.put("barCode", map.get("BARCODE"));
                bodyJson.put("unit", map.get("UNIT"));
                bodyJson.put("spec", map.get("SPEC"));
                bodyJson.put("price", map.get("PRICE2"));
                bodyJson.put("wjPrice", map.get("WJPRICE2"));
                bodyJson.put("hjPrice", map.get("HJPRICE2"));
                bodyJson.put("zmCount", headMap.get("ZMCOUNT2"));
                bodyJson.put("zmHCost", headMap.get("ZMHCOST2"));
                bodyJson.put("zmWCost", headMap.get("ZMWCOST2"));
                bodyJson.put("zmSCost", headMap.get("ZMSCOST2"));
                bodyJson.put("sJCount", headMap.get("SJCOUNT2"));
                bodyJson.put("sJhCost", headMap.get("SJHCOST2"));
                bodyJson.put("sJwCost", headMap.get("SJWCOST2"));
                bodyJson.put("sJsCost", headMap.get("SJSCOST2"));
                bodyJson.put("ykCount", headMap.get("YKCOUNT2"));
                bodyJson.put("ykHCost", headMap.get("YKHCOST2"));
                bodyJson.put("ykWCost", headMap.get("YKWCOST2"));
                bodyJson.put("ykSCost", headMap.get("YKSCOST2"));
                bodyJson.put("remark", map.get("REMARK2"));
                bodyArray.add(bodyJson);
            }
            backJson.put("bodyInfo", bodyArray);
        }
        return ResponseMessage.ok(backJson);
    }
    
    /**
     * 获取盘点商品
     * @param req
     * @return ResponseMessage
     */
    @RequestMapping(value = "/getPdSkuList", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage getPdSkuList(@RequestBody @Valid MdPdSkuReq req) {
        List<SkuPluModel> pdSkuList = this.couMdPdService.getPdSkuList(req.getOrgCode(), req.getDepId(), req.getBillType());
        return ResponseMessage.ok(pdSkuList);
    }
    
    /**
     * 组织盘点参数
     * @param req
     * @return CouMdPdHeadModel
     */
    private CouMdPdHeadModel getMdPdHeadModel(CouMdPdHeadReq req) {
        CouMdPdHeadModel headModel = new CouMdPdHeadModel();
        List<CouMdPdBodyReq> bodyList = req.getBodyList();
        if (null == bodyList || bodyList.isEmpty()) {
            throw new ServiceException(GoodsResponseCode.SKU_NOT_BLANK.getCode(), 
                    GoodsResponseCode.SKU_NOT_BLANK.getMsg());
        }
        headModel.setLrDate(DateUtil.getCurrentTime());
        headModel.setUserId(10000000041L);
        headModel.setUserCode("1");
        headModel.setUserName("超级户");
        headModel.setOrgCode(req.getOrgCode());
        headModel.setOrgName(req.getOrgName());
        OrgManageModel orgModel = this.commonService.getOrgModel(req.getOrgCode());
        headModel.setInOrgCode(orgModel.getInOrgCode());
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
        headModel.setYwType(YwType.MD_PD.getValue());
        headModel.setBillType(req.getBillType());
        if (StringUtils.isBlank(req.getBillNo())) {
            Map<String, Object> map = new HashMap<>();
            map.put("ps_BillType", YwType.MD_PD.getValue());
            String billNo = this.commonService.getBillNoByCallProcedure(map);
            headModel.setBillNo(billNo);
            headModel.setCouStatus(CouStatus.NOT_EXEC.getValue());
        }else {
            headModel.setCouStatus(CouStatus.HAS_EXEC.getValue());
        }
        headModel.setZmCount(req.getZmCount());
        headModel.setZmHCost(req.getZmHCost());
        headModel.setZmWCost(req.getZmWCost());
        headModel.setZmSCost(req.getZmSCost());
        headModel.setsJCount(req.getsJCount());
        headModel.setsJhCost(req.getsJhCost());
        headModel.setsJwCost(req.getsJwCost());
        headModel.setsJsCost(req.getsJsCost());
        headModel.setYkCount(req.getYkCount());
        headModel.setYkHCost(req.getYkHCost());
        headModel.setYkWCost(req.getYkWCost());
        headModel.setYkSCost(req.getYkSCost());
        headModel.setDataStatus(DataStatus.ENTRY.getValue());
        headModel.setRemark(req.getRemark());
        List<CouMdPdBodyModel> bodyModels = new LinkedList<CouMdPdBodyModel>();
        long serialNo = 1;
        for (CouMdPdBodyReq bodyReq : bodyList) {
            CouMdPdBodyModel bodyModel = new CouMdPdBodyModel();
            bodyModel.setBillNo(headModel.getBillNo());
            bodyModel.setSerialNo(serialNo++);
            bodyModel.setDepId(headModel.getDepId());
            bodyModel.setDepCode(headModel.getDepCode());
            bodyModel.setDepName(headModel.getDepName());
            bodyModel.setCkCode(headModel.getCkCode());
            bodyModel.setCkName(headModel.getCkName());
            bodyModel.setPluId(bodyReq.getPluId());
            bodyModel.setPluCode(bodyReq.getPluCode());
            bodyModel.setPluName(bodyReq.getPluName());
            bodyModel.setExPluCode("*");
            bodyModel.setBarCode(bodyReq.getBarCode());
            bodyModel.setUnit(bodyReq.getUnit());
            bodyModel.setSpec(bodyReq.getSpec());
            bodyModel.setPrice(bodyReq.getPrice());
            bodyModel.setWjPrice(bodyReq.getWjPrice());
            bodyModel.setHjPrice(bodyReq.getHjPrice());
            bodyModel.setZmCount(bodyReq.getZmCount());
            bodyModel.setZmHCost(bodyReq.getZmHCost());
            bodyModel.setZmWCost(bodyReq.getZmWCost());
            bodyModel.setZmSCost(bodyReq.getZmSCost());
            bodyModel.setsJCount(bodyReq.getsJCount());
            bodyModel.setsJhCost(bodyReq.getsJhCost());
            bodyModel.setsJwCost(bodyReq.getsJwCost());
            bodyModel.setsJsCost(bodyReq.getsJsCost());
            bodyModel.setYkCount(bodyReq.getYkCount());
            bodyModel.setYkHCost(bodyReq.getYkHCost());
            bodyModel.setYkWCost(bodyReq.getYkWCost());
            bodyModel.setYkSCost(bodyReq.getYkSCost());
            bodyModel.setRemark(bodyReq.getRemark());
            bodyModels.add(bodyModel);
        }
        headModel.setBodyList(bodyModels);
        return headModel;
    }
}
