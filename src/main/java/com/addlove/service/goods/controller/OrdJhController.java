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
import com.addlove.service.goods.model.OrdJhQueryPageModel;
import com.addlove.service.goods.model.PageModel;
import com.addlove.service.goods.model.valid.OrdJhQueryDetailReq;
import com.addlove.service.goods.model.valid.OrdJhQueryPageReq;
import com.addlove.service.goods.service.OrdJhService;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;

/**
 * 配送验收控制层
 * @author lw
 *
 */
@Controller
@RequestMapping(value = "/ordJh")
public class OrdJhController extends BaseController{
    @Autowired
    private OrdJhService ordJhService;
    
    /**
     * 分页查询配送验收单
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
        queryModel.setOrgCode("999999");//组织编码暂时写死
        queryModel.setBillNo(req.getBillNo());
        if (StringUtils.isNotBlank(req.getStartDate())) {
            queryModel.setStartDate(req.getStartDate() + " 00:00:00");
        }
        if (StringUtils.isNotBlank(req.getEndDate())) {
            queryModel.setEndDate(req.getEndDate() + " 23:59:59");
        }
        queryModel.setTag(req.getTag());
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
     * 查询配送验收单详情
     * @param req
     * @return queryOrderJhDetail
     */
    @RequestMapping(value = "/queryOrderJhDetail", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage queryOrderJhDetail(@RequestBody @Valid OrdJhQueryDetailReq req) {
        List<Map<String, Object>> resultList = this.ordJhService.queryBodysByBillNo(req.getBillNo());
        JSONObject backJson = new JSONObject();
        if (null != resultList && !resultList.isEmpty()) {
            JSONObject headJson = new JSONObject();
            Map<String, Object> headMap = resultList.get(0);
            headJson.put("billNo", headMap.get("BILLNO"));
            headJson.put("refBillNo", headMap.get("REFBILLNO"));
            headJson.put("orgCode", headMap.get("ORGCODE"));
            headJson.put("orgName", headMap.get("ORGNAME"));
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
            headJson.put("ckCode", headMap.get("CKCODE"));
            headJson.put("ckName", headMap.get("CKNAME"));
            headJson.put("hCost", headMap.get("HCOST"));
            headJson.put("wCost", headMap.get("WCOST"));
            headJson.put("jtaxTotal", headMap.get("JTAXTOTAL"));
            headJson.put("cjTotal", headMap.get("CJTOTAL"));
            headJson.put("psCost", headMap.get("PSCOST"));
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
                bodyJson.put("depId", map.get("DEPID2"));
                bodyJson.put("depCode", map.get("DEPCODE2"));
                bodyJson.put("depName", map.get("DEPNAME2"));
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
                bodyJson.put("jTaxTotal", map.get("JTAXTOTAL2"));
                bodyJson.put("sTotal", map.get("STOTAL2"));
                bodyJson.put("cjTotal", map.get("CJTOTAL2"));
                bodyJson.put("cjRate", map.get("CJRATE2"));
                bodyJson.put("psCost", map.get("PSCOST2"));
                bodyJson.put("hjsTotal", map.get("HJSTOTAL2"));
                bodyJson.put("wjsTotal", map.get("WJSTOTAL2"));
                bodyArray.add(bodyJson);
            }
            backJson.put("bodyInfo", bodyArray);
        }
        return ResponseMessage.ok(backJson);
    }
}
