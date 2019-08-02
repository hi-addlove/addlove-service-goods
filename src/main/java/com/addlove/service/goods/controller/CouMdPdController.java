package com.addlove.service.goods.controller;

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

import com.addlove.service.goods.message.ResponseMessage;
import com.addlove.service.goods.model.CouMdPdHeadModel;
import com.addlove.service.goods.model.MdPdPageModel;
import com.addlove.service.goods.model.PageModel;
import com.addlove.service.goods.model.SkuPluModel;
import com.addlove.service.goods.model.valid.CommonQueryDetailReq;
import com.addlove.service.goods.model.valid.MdPdPageReq;
import com.addlove.service.goods.model.valid.MdPdSkuReq;
import com.addlove.service.goods.service.CouMdPdService;
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
}
