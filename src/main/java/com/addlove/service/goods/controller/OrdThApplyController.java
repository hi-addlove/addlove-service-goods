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
import com.addlove.service.goods.constants.GoodsOrdJhConstants.ModelTags;
import com.addlove.service.goods.constants.GoodsOrdThConstants.ApplyStatus;
import com.addlove.service.goods.constants.GoodsOrdThConstants.YwType;
import com.addlove.service.goods.message.ResponseMessage;
import com.addlove.service.goods.model.OrdJhBodyModel;
import com.addlove.service.goods.model.OrdJhHeadModel;
import com.addlove.service.goods.model.OrdThApplyBodyModel;
import com.addlove.service.goods.model.OrdThApplyHeadModel;
import com.addlove.service.goods.model.OrdThQueryPageModel;
import com.addlove.service.goods.model.PageModel;
import com.addlove.service.goods.model.valid.CommonQueryDetailReq;
import com.addlove.service.goods.model.valid.OrdThApplyBodyDiffReq;
import com.addlove.service.goods.model.valid.OrdThApplyHeadDiffReq;
import com.addlove.service.goods.model.valid.OrdThQueryPageReq;
import com.addlove.service.goods.service.GoodsCommonService;
import com.addlove.service.goods.service.OrdJhService;
import com.addlove.service.goods.service.OrdThApplyService;
import com.addlove.service.goods.util.DateUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;

/**
 * 差异单控制层
 * @author lw
 *
 */
@Controller
@RequestMapping(value = "/ordThApply")
public class OrdThApplyController extends BaseController{
    @Autowired
    private OrdJhService ordJhService;
    
    @Autowired
    private GoodsCommonService commonService;
    
    @Autowired
    private OrdThApplyService ordThApplyService;
    
    /**
     * 分页查询退货差异单
     * @param req
     * @return ResponseMessage
     */
    @RequestMapping(value = "/queryOrderThDiff", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage queryOrderThDiff(@RequestBody @Valid OrdThQueryPageReq req) {
        OrdThQueryPageModel queryModel = new OrdThQueryPageModel();
        queryModel.setPageNo(req.getPageNo());
        queryModel.setPageSize(req.getPageSize());
        queryModel.setOrgCode(req.getOrgCode());
        queryModel.setOrgCode("999999");//组织编码暂时写死
        queryModel.setBillNo(req.getBillNo());
        if (StringUtils.isNotBlank(req.getStartDate())) {
            queryModel.setStartDate(req.getStartDate() + " 00:00:00");
        }
        if (StringUtils.isNotBlank(req.getEndDate())) {
            queryModel.setEndDate(req.getEndDate() + " 23:59:59");
        }
        queryModel.setApplyStatus(req.getApplyStatus());
        queryModel.setTjrName(req.getTjrName());
        List<OrdThApplyHeadModel> ordThHeadList = this.ordThApplyService.queryOrdThHeadModelByPage(queryModel);
        PageModel pageModel = new PageModel();
        Page<OrdThApplyHeadModel> page = (Page<OrdThApplyHeadModel>) ordThHeadList;
        pageModel.setPageNo(page.getPageNum());
        pageModel.setPageSize(page.getPageSize());
        pageModel.setResult(page.getResult());
        pageModel.setTotal(page.getTotal());
        return ResponseMessage.ok(pageModel);
    }
    
    /**
     * 产生配送验收差异单
     * @param req
     * @return ResponseMessage
     */
    @RequestMapping(value = "/generateDifferentBill", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage generateDifferentBill(@RequestBody @Valid OrdThApplyHeadDiffReq req) {
        List<OrdThApplyBodyDiffReq> bodyReqList = req.getBodyList();
        //没有差异商品，直接更新配送验收主表
        if (null == bodyReqList || bodyReqList.isEmpty()) {
            OrdJhHeadModel model = new OrdJhHeadModel();
            model.setBillNo(req.getBillNo());
            model.setYsrId(10000000041L);
            model.setYsrCode("1");
            model.setYsrName("超级户");
            //送货确认时间
            model.setShrDate(DateUtil.getCurrentTime());
            model.setTag(ModelTags.NORMAL_ACCEPTANCE.getValue());
            this.ordJhService.updateJhHeadYsrCodeAndStatus(model);
        }else {
            OrdThApplyHeadModel headModel = new OrdThApplyHeadModel();
            //调用存储过程生成差异单号
            Map<String, Object> map = new HashMap<>();
            map.put("ps_BillType", BillType.RETURN_APPLY.getValue());
            String billNo = this.commonService.getBillNoByCallProcedure(map);
            headModel.setBillNo(billNo);
            headModel.setRefBillNo(req.getBillNo());
            headModel.setOrgCode(req.getOrgCode());
            headModel.setOrgName(req.getOrgName());
            headModel.setInOrgCode(req.getInOrgCode());
            headModel.setZbOrgCode(req.getZbOrgCode());
            headModel.setZbOrgName(req.getZbOrgName());
            headModel.setSupCode(req.getSupCode());
            headModel.setSupName(req.getSupName());
            headModel.setCntId(req.getCntId());
            headModel.setCkCode(req.getCkCode());
            headModel.setCkName(req.getCkName());
            headModel.setThCount(req.getThCount());
            headModel.sethCost(req.gethCost());
            headModel.setwCost(req.getwCost());
            headModel.setjTaxTotal(req.getJtaxTotal());
            headModel.setsTotal(req.getsTotal());
            headModel.setCjTotal(req.getCjTotal());
            headModel.setPsCost(req.getPsCost());
            headModel.setYwType(YwType.SHOP_TO_ZB.getValue());
            headModel.setApplyStatus(ApplyStatus.HAVE_APPLYED.getValue());
            headModel.setTjDate(DateUtil.getCurrentTime());
            headModel.setTjrId(10000000041L);
            headModel.setTjrCode("l");
            headModel.setTjrName("超级户");
            headModel.setDepId(req.getDepId());
            headModel.setDepCode(req.getDepCode());
            headModel.setDepName(req.getDepName());
            //差异单商品明细集合
            List<OrdThApplyBodyModel> bodyModelList = new LinkedList<OrdThApplyBodyModel>();
            //验收配送单明细集合
            List<OrdJhBodyModel> jhModelList = new LinkedList<OrdJhBodyModel>();
            for (OrdThApplyBodyDiffReq bodyReq : bodyReqList) {
                //验收配送单明细
                OrdJhBodyModel jhModel = new OrdJhBodyModel();
                jhModel.setBillNo(req.getBillNo());
                jhModel.setSerialNo(bodyReq.getSerialNo());
                jhModel.setPsShCount(bodyReq.getPsShCount());
                jhModelList.add(jhModel);
                //退货差异单
                OrdThApplyBodyModel bodyModel = new OrdThApplyBodyModel();
                bodyModel.setBillNo(billNo);
                bodyModel.setSerialNo(bodyReq.getSerialNo());
                bodyModel.setPluId(bodyReq.getPluId());
                bodyModel.setPluCode(bodyReq.getPluCode());
                bodyModel.setPluName(bodyReq.getPluName());
                bodyModel.setExPluCode(bodyReq.getExPluCode());
                bodyModel.setBarCode(bodyReq.getBarCode());
                bodyModel.setSpec(bodyReq.getSpec());
                bodyModel.setUnit(bodyReq.getUnit());
                bodyModel.setCarGoNo(bodyReq.getCarGoNo());
                bodyModel.setDepId(bodyReq.getDepId());
                bodyModel.setDepCode(bodyReq.getDepCode());
                bodyModel.setDepName(bodyReq.getDepName());
                bodyModel.setPsPrice(bodyReq.getPsPrice());
                bodyModel.setPackUnit(bodyReq.getPackUnit());
                bodyModel.setPackQty(bodyReq.getPackQty());
                bodyModel.setPackCount(bodyReq.getPackCount());
                bodyModel.setSglCount(bodyReq.getSglCount());
                bodyModel.setThCount(bodyReq.getThCount());
                bodyModel.sethCost(bodyReq.gethCost());
                bodyModel.setwCost(bodyReq.getwCost());
                bodyModel.setjTaxTotal(bodyReq.getjTaxTotal());
                bodyModel.setPsCost(bodyReq.getPsCost());
                bodyModel.setsTotal(bodyReq.getsTotal());
                bodyModel.setCjTotal(bodyReq.getCjTotal());
                bodyModel.setCjRate(bodyReq.getCjRate());
                bodyModel.setHjsTotal(bodyReq.getHjsTotal());
                bodyModel.setWjsTotal(bodyReq.getWjsTotal());
                bodyModel.setSqThCount(bodyReq.getSqThCount());
                bodyModelList.add(bodyModel);
            }
            //页面执行“保存”操作：不生成差异单，只改变验收单确认数量
            if (ApplyStatus.NOT_APPLY.getValue().equals(req.getApplyStatus())) {
                this.ordJhService.updateJhBodyPsShCount(jhModelList);
            }else {
                //页面执行“提交”操作：生成差异单
                OrdJhHeadModel model = new OrdJhHeadModel();
                model.setBillNo(req.getBillNo());
                model.setYsrId(10000000041L);
                model.setYsrCode("l");
                model.setYsrName("超级户");
                //送货确认时间
                model.setShrDate(DateUtil.getCurrentTime());
                model.setTag(ModelTags.ABNORMAL_ACCEPTANCE.getValue());
                this.ordJhService.updateJhHeadYsrCodeAndStatus(model);
                this.ordThApplyService.insertOrdThApply(headModel, bodyModelList);
            }
        }
        return ResponseMessage.ok();
    }
    
    /**
     * 查询退货差异单详情
     * @param req
     * @return ResponseMessage
     */
    @RequestMapping(value = "/queryOrderThDetail", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage queryOrderThDetail(@RequestBody @Valid CommonQueryDetailReq req) {
        List<Map<String, Object>> resultList = this.ordThApplyService.queryThBodysByBillNo(req.getBillNo());
        JSONObject backJson = new JSONObject();
        if (null != resultList && !resultList.isEmpty()) {
            JSONObject headJson = new JSONObject();
            Map<String, Object> headMap = resultList.get(0);
            headJson.put("billNo", headMap.get("BILLNO"));
            headJson.put("orgCode", headMap.get("ORGCODE"));
            headJson.put("orgName", headMap.get("ORGNAME"));
            headJson.put("tjDate", headMap.get("TJDATE"));
            headJson.put("tjrCode", headMap.get("TJRCODE"));
            headJson.put("tjrName", headMap.get("TJRNAME"));
            headJson.put("applyStatus", headMap.get("APPLYSTATUS"));
            headJson.put("thCount", headMap.get("THCOUNT"));
            headJson.put("checkUserId", headMap.get("CHECKUSERID"));
            headJson.put("checkUserCode", headMap.get("CHECKUSERCODE"));
            headJson.put("checkUserName", headMap.get("CHECKUSERNAME"));
            headJson.put("supCkCode", headMap.get("SUPCKCODE"));
            headJson.put("supCkName", headMap.get("SUPCKNAME"));
            headJson.put("supDepId", headMap.get("SUPDEPID"));
            headJson.put("supDepCode", headMap.get("SUPDEPCODE"));
            headJson.put("supdepname", headMap.get("SUPDEPNAME"));
            headJson.put("createBillNo", headMap.get("CREATEBILLNO"));
            headJson.put("refBillNo", headMap.get("REFBILLNO"));
            backJson.put("headInfo", headJson);
            JSONArray bodyArray = new JSONArray();
            for (Map<String, Object> map : resultList) {
                JSONObject bodyJson = new JSONObject();
                for (Map.Entry<String, Object> entry : map.entrySet()) {
                    if ("SQTHCOUNT2".equals(entry.getKey().toString().trim())) {
                        bodyJson.put("sqThCount", entry.getValue());
                    }
                }
                bodyJson.put("serialNo", map.get("SERIALNO"));
                bodyJson.put("pluCode", map.get("PLUCODE"));
                bodyJson.put("pluName", map.get("PLUNAME"));
                bodyJson.put("checkInfo", map.get("CHECKINFO2"));
                bodyJson.put("checkUserId", map.get("CHECKUSERID2"));
                bodyJson.put("checkUserCode", map.get("CHECKUSERCODE2"));
                bodyJson.put("checkUserName", map.get("CHECKUSERNAME2"));
                bodyJson.put("checkDate", map.get("CHECKDATE2"));
                bodyJson.put("thCount", map.get("THCOUNT2"));
                bodyJson.put("supCode", map.get("SUPCODE"));
                bodyJson.put("supName", map.get("SUPNAME"));
                bodyJson.put("supCkCode", map.get("SUPCKCODE"));
                bodyJson.put("supCkName", map.get("SUPCKNAME"));
                bodyArray.add(bodyJson);
            }
            backJson.put("bodyInfo", bodyArray);
        }
        return ResponseMessage.ok(backJson);
    }
}
