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
//        queryMap.put("pluInfo", "1010");
        for (int i = 0; i < 3; i++) {
            long startTime = System.currentTimeMillis();
            List<Map<String,Object>> list = this.reportService.queryStockReport(queryMap);
            long endTime = System.currentTimeMillis();
            System.out.println("---------------执行时间-------------" + (endTime - startTime));
            if (null != list && !list.isEmpty()) {
                Map<String, Object> map = list.get(0);
                System.out.println("---------------map.get(\"ORGINFO\")-------------" + map.get("ORGINFO"));
            }
        }
    }
}
