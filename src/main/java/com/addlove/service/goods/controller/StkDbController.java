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
import com.addlove.service.goods.model.PageModel;
import com.addlove.service.goods.model.StkDbBodyModel;
import com.addlove.service.goods.model.StkDbHeadModel;
import com.addlove.service.goods.model.StkDbQueryPageModel;
import com.addlove.service.goods.model.StkStoreModel;
import com.addlove.service.goods.model.valid.CommonQueryDetailReq;
import com.addlove.service.goods.model.valid.StkDbHeadReq;
import com.addlove.service.goods.model.valid.StkDbQueryPageReq;
import com.addlove.service.goods.service.GoodsCommonService;
import com.addlove.service.goods.service.StkDbService;
import com.addlove.service.goods.util.DateUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;

/**
 * 调拨控制层
 * @author lw
 *
 */
@Controller
@RequestMapping(value = "/stkDb")
public class StkDbController extends BaseController {
    @Autowired
    private StkDbService stkDbService;
    
    @Autowired
    private GoodsCommonService commonService;
    
    /**
     * 调拨分页查询
     * @param req
     * @return
     */
    @RequestMapping(value = "/queryDbPage", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage queryDbPage(@RequestBody @Valid StkDbQueryPageReq req) {
        StkDbQueryPageModel queryModel = new StkDbQueryPageModel();
        queryModel.setPageNo(req.getPageNo());
        queryModel.setPageSize(req.getPageSize());
        queryModel.setBillNo(req.getBillNo());
        if (StringUtils.isNotBlank(req.getStartDate())) {
            queryModel.setStartDate(req.getStartDate() + " 00:00:00");
        }
        if (StringUtils.isNotBlank(req.getEndDate())) {
            queryModel.setEndDate(req.getEndDate() + " 23:59:59");
        }
        queryModel.setDataStatus(req.getDataStatus());
        queryModel.setDbType(req.getDbType());
        queryModel.setOrgCode(req.getOrgCode());
        queryModel.setShOrgCode(req.getShOrgCode());
        List<StkDbHeadModel> dbList = this.stkDbService.queryDbPage(queryModel);
        PageModel pageModel = new PageModel();
        Page<StkDbHeadModel> page = (Page<StkDbHeadModel>) dbList;
        pageModel.setPageNo(page.getPageNum());
        pageModel.setPageSize(page.getPageSize());
        pageModel.setResult(page.getResult());
        pageModel.setTotal(page.getTotal());
        return ResponseMessage.ok(pageModel);
    }
    
    /**
     * 新增调拨单
     * @param req
     * @return ResponseMessage
     */
    @RequestMapping(value = "/addStkDb", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage addStkDb(@RequestBody @Valid StkDbHeadReq req) {
        if (StringUtils.isBlank(req.getBillNo()) && req.getSaveType() == SaveType.EXEC_ACCOUNT.getValue()) {
            throw new ServiceException(GoodsResponseCode.SAVE_BEFORE_ACCOUNT.getCode(), 
                    GoodsResponseCode.SAVE_BEFORE_ACCOUNT.getMsg());
        }
        StkDbHeadModel headModel = this.getStkDbHeadModel(req);
        if (req.getSaveType() == SaveType.SAVE.getValue()) {
            this.stkDbService.insertDbInfo(headModel);
        }else if (req.getSaveType() == SaveType.EXEC_ACCOUNT.getValue()) {
            //调用存储过程进行记账
            Map<String, Object> accountMap = new HashMap<String, Object>();
            accountMap.put("ps_BillNo", headModel.getBillNo());
            accountMap.put("ps_YwType", headModel.getYwType());
            accountMap.put("pi_UserId", headModel.getJzrId());
            accountMap.put("ps_UserCode", headModel.getJzrCode());
            accountMap.put("ps_UserName", headModel.getJzrName());
            accountMap.put("pd_JzDate", headModel.getJzDate());
            //调用存储过程进行记账
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
     * 编辑调拨单
     * @param req
     * @return ResponseMessage
     */
    @RequestMapping(value = "/editStkDb", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage editStkDb(@RequestBody @Valid StkDbHeadReq req) {
        if (StringUtils.isBlank(req.getBillNo())) {
            throw new ServiceException(GoodsResponseCode.BILLNO_NOT_BLANK.getCode(), 
                    GoodsResponseCode.BILLNO_NOT_BLANK.getMsg());
        }
        //编辑时先删除再插入
        StkDbHeadModel headModel = this.getStkDbHeadModel(req);
        if (req.getSaveType() == SaveType.SAVE.getValue()) {
            this.stkDbService.updateAllDbInfo(headModel);
        }else if (req.getSaveType() == SaveType.EXEC_ACCOUNT.getValue()) {
            Map<String, Object> accountMap = new HashMap<String, Object>();
            accountMap.put("ps_BillNo", headModel.getBillNo());
            accountMap.put("ps_YwType", headModel.getYwType());
            accountMap.put("pi_UserId", headModel.getJzrId());
            accountMap.put("ps_UserCode", headModel.getJzrCode());
            accountMap.put("ps_UserName", headModel.getJzrName());
            accountMap.put("pd_JzDate", headModel.getJzDate());
            this.stkDbService.updateAllDbInfo(headModel);
            //调用存储过程进行记账
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
    @RequestMapping(value = "/delStkDb", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage delStkDb(@RequestBody @Valid CommonQueryDetailReq req) {
        this.stkDbService.deleteDbInfo(req.getBillNo());
        return ResponseMessage.ok();
    }
    
    /**
     * 查询调拨详情
     * @param req
     * @return ResponseMessage
     */
    @RequestMapping(value = "/queryDbDetail", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage queryDbDetail(@RequestBody @Valid CommonQueryDetailReq req) {
        List<Map<String, Object>> resultList = this.stkDbService.queryDbDetailByBillNo(req.getBillNo());
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
            headJson.put("empId", headMap.get("EMPID"));
            headJson.put("empCode", headMap.get("EMPCODE"));
            headJson.put("empName", headMap.get("EMPNAME"));
            headJson.put("orgCode", headMap.get("ORGCODE"));
            headJson.put("orgName", headMap.get("ORGNAME"));
            headJson.put("depId", headMap.get("DEPID"));
            headJson.put("depCode", headMap.get("DEPCODE"));
            headJson.put("depName", headMap.get("DEPNAME"));
            headJson.put("shOrgCode", headMap.get("SHORGCODE"));
            headJson.put("shOrgName", headMap.get("SHORGNAME"));
            headJson.put("shDepId", headMap.get("SHDEPID"));
            headJson.put("shDepCode", headMap.get("SHDEPCODE"));
            headJson.put("shDepName", headMap.get("SHDEPNAME"));
            headJson.put("ckCode", headMap.get("CKCODE"));
            headJson.put("ckName", headMap.get("CKNAME"));
            headJson.put("shCkCode", headMap.get("SHCKCODE"));
            headJson.put("shCkName", headMap.get("SHCKNAME"));
            headJson.put("isPsTask", headMap.get("ISPSTASK"));
            headJson.put("dbCount", headMap.get("DBCOUNT"));
            headJson.put("wCost", headMap.get("WCOST"));
            headJson.put("hCost", headMap.get("HCOST"));
            headJson.put("remark", headMap.get("REMARK"));
            headJson.put("inOrgCode", headMap.get("INORGCODE"));
            headJson.put("inShOrgCode", headMap.get("INSHORGCODE"));
            headJson.put("dataStatus", headMap.get("DATASTATUS"));
            headJson.put("ywType", headMap.get("YWTYPE"));
            headJson.put("ywStatus", headMap.get("YWSTATUS"));
            headJson.put("rhCost", headMap.get("RHCOST"));
            headJson.put("rwCost", headMap.get("RWCOST"));
            headJson.put("wlAreaCode", headMap.get("WLAREACODE"));
            headJson.put("wlAreaName", headMap.get("WLAREANAME"));
            headJson.put("shWlAreaCode", headMap.get("SHWLAREACODE"));
            headJson.put("shWlAreaName", headMap.get("SHWLAREANAME"));
            headJson.put("sTotal", headMap.get("STOTAL"));
            headJson.put("isChecked", headMap.get("ISCHECKED"));
            headJson.put("chkUserId", headMap.get("CHKUSERID"));
            headJson.put("chkUserCode", headMap.get("CHKUSERCODE"));
            headJson.put("chkUserName", headMap.get("CHKUSERNAME"));
            headJson.put("chkDate", headMap.get("CHKDATE"));
            backJson.put("headInfo", headJson);
            JSONArray bodyArray = new JSONArray();
            for (Map<String, Object> map : resultList) {
                JSONObject bodyJson = new JSONObject();
                bodyJson.put("serialNo", map.get("SERIALNO"));
                bodyJson.put("pluId", map.get("PLUID"));
                bodyJson.put("exPluCode", map.get("EXPLUCODE"));
                bodyJson.put("exPluName", map.get("EXPLUNAME"));
                bodyJson.put("pluCode", map.get("PLUCODE"));
                bodyJson.put("pluName", map.get("PLUNAME"));
                bodyJson.put("barCode", map.get("BARCODE"));
                bodyJson.put("spec", map.get("SPEC"));
                bodyJson.put("unit", map.get("UNIT"));
                bodyJson.put("packUnit", map.get("PACKUNIT"));
                bodyJson.put("packQty", map.get("PACKQTY"));
                bodyJson.put("packCount", map.get("PACKCOUNT"));
                bodyJson.put("sglCount", map.get("SGLCOUNT"));
                bodyJson.put("dbCount", map.get("DBCOUNT2"));
                bodyJson.put("pluType", map.get("PLUTYPE"));
                bodyJson.put("hjPrice", map.get("HJPRICE"));
                bodyJson.put("wjPrice", map.get("WJPRICE"));
                bodyJson.put("price", map.get("PRICE"));
                bodyJson.put("jTaxRate", map.get("JTAXRATE"));
                bodyJson.put("hCost", map.get("HCOST2"));
                bodyJson.put("wCost", map.get("WCOST2"));
                bodyJson.put("remark", map.get("REMARK2"));
                bodyJson.put("shDepId", map.get("SHDEPID2"));
                bodyJson.put("shDepCode", map.get("SHDEPCODE2"));
                bodyJson.put("shDepName", map.get("SHDEPNAME2"));
                bodyJson.put("rhCost", map.get("RHCOST2"));
                bodyJson.put("rwCost", map.get("RWCOST2"));
                bodyJson.put("outKwCode", map.get("OUTKWCODE"));
                bodyJson.put("inKwCode", map.get("INKWCODE"));
                bodyJson.put("sTotal", map.get("STOTAL2"));
                bodyJson.put("rhJPrice", map.get("RHJPRICE"));
                bodyJson.put("rwJPrice", map.get("RWJPRICE"));
                bodyJson.put("carGoNo", map.get("CARGONO"));
                bodyJson.put("toSerialNo", map.get("TOSERIALNO"));
                bodyArray.add(bodyJson);
            }
            backJson.put("bodyInfo", bodyArray);
        }
        return ResponseMessage.ok(backJson);
    }
    
    /**
     * 组织调拨单数据
     * @param req
     * @return StkDbHeadModel
     */
    private StkDbHeadModel getStkDbHeadModel(StkDbHeadReq req) {
        //店内调拨
        if (req.getOrgCode().equals(req.getShOrgCode())) {
            if (null == req.getDepId() || null == req.getShDepId()) {
                throw new ServiceException(GoodsResponseCode.DEP_ID_NOT_BLANK.getCode(), 
                        GoodsResponseCode.DEP_ID_NOT_BLANK.getMsg());
            }
        }
        StkDbHeadModel headModel = new StkDbHeadModel();
        headModel.setLrDate(DateUtil.getCurrentTime());
        headModel.setUserId(10000000041L);
        headModel.setUserCode("1");
        headModel.setUserName("超级户");
        headModel.setEmpId(req.getEmpId());
        headModel.setEmpCode(req.getEmpCode());
        headModel.setEmpName(req.getEmpName());
        headModel.setOrgCode(req.getOrgCode());
        headModel.setOrgName(req.getOrgName());
        headModel.setDepId(req.getDepId());
        headModel.setDepCode(req.getDepCode());
        headModel.setDepName(req.getDepName());
        headModel.setShOrgCode(req.getShOrgCode());
        headModel.setShOrgName(req.getShOrgName());
        headModel.setShDepId(req.getShDepId());
        headModel.setShDepCode(req.getShDepCode());
        headModel.setShDepName(req.getShDepName());
        List<StkStoreModel> storeList = this.commonService.getStoreList(req.getOrgCode());
        if (null == storeList || storeList.isEmpty()) {
            throw new ServiceException(GoodsResponseCode.CK_NOT_BLANK.getCode(), 
                    GoodsResponseCode.CK_NOT_BLANK.getMsg());
        }
        headModel.setCkCode(storeList.get(0).getCkCode());
        headModel.setCkName(storeList.get(0).getCkName());
        List<StkStoreModel> shStoreList = this.commonService.getStoreList(req.getOrgCode());
        if (null == shStoreList || shStoreList.isEmpty()) {
            throw new ServiceException(GoodsResponseCode.CK_NOT_BLANK.getCode(), 
                    GoodsResponseCode.CK_NOT_BLANK.getMsg());
        }
        headModel.setShCkCode(shStoreList.get(0).getCkCode());
        headModel.setShCkName(shStoreList.get(0).getCkName());
        headModel.setDbCount(req.getDbCount());
        headModel.setwCost(req.getwCost());
        headModel.sethCost(req.gethCost());
        headModel.setRemark(req.getRemark());
        headModel.setInOrgCode(req.getInOrgCode());
        headModel.setInShOrgCode(req.getInShOrgCode());
        headModel.setDataStatus(DataStatus.ENTRY.getValue());
        headModel.setYwType(YwType.DB_ORDER.getValue());
        headModel.setRhCost(req.getRhCost());
        headModel.setRwCost(req.getRwCost());
        headModel.setsTotal(req.getsTotal());
        //调用存储过程生成无采购验收单号
        if (StringUtils.isBlank(req.getBillNo())) {
            Map<String, Object> map = new HashMap<>();
            map.put("ps_BillType", BillType.DB_ORDER.getValue());
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
        //组织明细
        List<StkDbBodyModel> bodyModels = new LinkedList<StkDbBodyModel>();
        long serialNo = 1;
        for (StkDbBodyModel bodyReq : bodyModels) {
            StkDbBodyModel bodyModel = new StkDbBodyModel();
            bodyModel.setBillNo(headModel.getBillNo());
            serialNo++;
            bodyModel.setSerialNo(serialNo);
            bodyModel.setPluId(bodyReq.getPluId());
            bodyModel.setExPluCode(bodyReq.getExPluCode());
            bodyModel.setExPluName(bodyReq.getExPluName());
            bodyModel.setPluCode(bodyReq.getPluCode());
            bodyModel.setPluName(bodyReq.getPluName());
            bodyModel.setBarCode(bodyReq.getBarCode());
            bodyModel.setSpec(bodyReq.getSpec());
            bodyModel.setUnit(bodyReq.getUnit());
            bodyModel.setPackUnit(bodyReq.getPackUnit());
            bodyModel.setPackQty(bodyReq.getPackQty());
            bodyModel.setPackCount(bodyReq.getPackCount());
            bodyModel.setSglCount(bodyReq.getSglCount());
            bodyModel.setDbCount(bodyReq.getDbCount());
            bodyModel.setHjPrice(bodyReq.getHjPrice());
            bodyModel.setWjPrice(bodyReq.getWjPrice());
            bodyModel.setPrice(bodyReq.getPrice());
            bodyModel.setjTaxRate(bodyReq.getjTaxRate());
            bodyModel.sethCost(bodyReq.gethCost());
            bodyModel.setwCost(bodyReq.getwCost());
            bodyModel.setRemark(bodyReq.getRemark());
            bodyModel.setShDepId(bodyReq.getShDepId());
            bodyModel.setShDepCode(bodyReq.getShDepCode());
            bodyModel.setShDepName(bodyReq.getShDepName());
            bodyModel.setRhCost(bodyReq.getRhCost());
            bodyModel.setRwCost(bodyReq.getRwCost());
            bodyModel.setsTotal(bodyReq.getsTotal());
            bodyModel.setRhJPrice(bodyReq.getRhJPrice());
            bodyModel.setRwJPrice(bodyReq.getRwJPrice());
            bodyModel.setCarGoNo(bodyReq.getCarGoNo());
            bodyModel.setToSerialNo(serialNo);
            bodyModels.add(bodyModel);
        }
        headModel.setBodyList(bodyModels);
        return headModel;
    }
}
