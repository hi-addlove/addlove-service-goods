package com.addlove.service.goods;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import com.addlove.service.goods.controller.ReportController;
import com.addlove.service.goods.model.valid.QueryStockReportReq;
import com.addlove.service.goods.service.ReportService;

public class RepostTest extends AddloveServiceGoodsApplicationTests{
    @Autowired
    private ReportController reportController;
    
    @Autowired
    private ReportService reportService;
    
    @Test
    public void testQueryStockReport() {
        QueryStockReportReq req = new QueryStockReportReq();
        req.setOrgCode("999999");
        req.setPluInfo("1010");
        this.reportController.queryStockReport(req);
    }
    
    @Test
    public void testStockReport() {
        Map<String, Object> queryMap = new HashMap<String, Object>();
        queryMap.put("orgCode", "999999");
        queryMap.put("pluInfo", "1010");
        long startTime = System.currentTimeMillis();
        List<Map<String,Object>> list = this.reportService.queryStockReport(queryMap );
        long endTime = System.currentTimeMillis();
        System.out.println("---------------第一次执行时间-------------" + (endTime - startTime));
        if (null != list && !list.isEmpty()) {
            for (Map<String, Object> map : list) {
                System.out.println("---------------map.get(\"ORGINFO\")-------------" + map.get("ORGINFO"));
            }
        }
        
        long startTime2 = System.currentTimeMillis();
        List<Map<String,Object>> list2 = this.reportService.queryStockReport(queryMap );
        long endTime2 = System.currentTimeMillis();
        System.out.println("---------------第二次执行时间-------------" + (endTime2 - startTime2));
        if (null != list2 && !list2.isEmpty()) {
            for (Map<String, Object> map : list2) {
                System.out.println("---------------DEPINFO-------------" + map.get("DEPINFO"));
            }
        }
    }
}
