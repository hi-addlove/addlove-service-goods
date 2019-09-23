package com.addlove.service.goods.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.addlove.service.goods.constants.GoodsResponseCode;
import com.addlove.service.goods.exception.ServiceException;
import com.addlove.service.goods.message.ResponseMessage;
import com.addlove.service.goods.model.StkStoreModel;
import com.addlove.service.goods.model.valid.QueryStockReportReq;
import com.addlove.service.goods.service.GoodsCommonService;
import com.addlove.service.goods.service.ReportService;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * 报表控制层
 * @author lw
 *
 */
@Controller
@RequestMapping(value = "/addlove/report")
public class ReportController extends BaseController{
    @Autowired
    private ReportService reportService;
    
    @Autowired
    private GoodsCommonService commonService;
    
    /**
     * 库存查询报表
     * @param req
     * @return ResponseMessage
     */
    @RequestMapping(value = "/queryStockReport", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage queryStockReport(@RequestBody @Valid QueryStockReportReq req) {
        Map<String, Object> queryMap = new HashMap<String, Object>();
        queryMap.put("orgCode", req.getOrgCode());
        List<StkStoreModel> storeList = this.commonService.getStoreList(req.getOrgCode());
        if (null == storeList || storeList.isEmpty()) {
            throw new ServiceException(GoodsResponseCode.CK_NOT_BLANK.getCode(), 
                    GoodsResponseCode.CK_NOT_BLANK.getMsg());
        }
        queryMap.put("ckCode", storeList.get(0).getCkCode());
        queryMap.put("pluInfo", req.getPluInfo());
        queryMap.put("clsCode", req.getClsCode());
        queryMap.put("brandCode", req.getBrandCode());
        queryMap.put("queryModel", req.getQueryModel());
        queryMap.put("showModel", req.getShowModel());
        queryMap.put("empCode", req.getEmpCode());
        queryMap.put("depCode", req.getDepCode());
        List<Map<String, Object>> stockList = this.reportService.queryStockReport(queryMap);
        JSONArray backArray = new JSONArray();
        if (null != stockList && !stockList.isEmpty()) {
            for (Map<String, Object> map : stockList) {
                JSONObject backJson = new JSONObject();
                backJson.put("orgInfo", map.get("ORGINFO"));//组织信息
                backJson.put("depInfo", map.get("DEPINFO"));//部门信息
                backJson.put("ckInfo", map.get("CKINFO"));//仓库信息
                backJson.put("clsInfo", map.get("CLSINFO"));//分类信息
                backJson.put("brandInfo", map.get("BRANDINFO"));//品牌信息
                backJson.put("pluCode", map.get("PLUCODE"));//商品编码
                backJson.put("pluName", map.get("PLUNAME"));//商品名称
                backJson.put("barCode", map.get("BARCODE"));//商品条形码
                backJson.put("unit", map.get("UNIT"));//商品单位
                backJson.put("spec", map.get("SPEC"));//规格
                backJson.put("season", map.get("SEASON"));//季节
                backJson.put("kcCount", map.get("KCCOUNT"));//现存数量
                backJson.put("lockCount", map.get("LOCKCOUNT"));//锁定数量
                backJson.put("kyCount", map.get("KYCOUNT"));
                backJson.put("kchCost", map.get("KCHCOST"));
                backJson.put("kcwCost", map.get("KCWCOST"));
                backJson.put("salCount", map.get("SALCOUNT"));//实时销售
                backJson.put("timekCount", map.get("TIMEKCOUNT"));//实时库存
                backArray.add(backJson);
            }
        }
        return ResponseMessage.ok(backArray);
    }
}
