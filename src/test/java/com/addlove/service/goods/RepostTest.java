package com.addlove.service.goods;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.addlove.service.goods.controller.ReportController;
import com.addlove.service.goods.model.valid.QueryStockReportReq;

public class RepostTest extends AddloveServiceGoodsApplicationTests{
    @Autowired
    private ReportController reportController;
    
    @Test
    public void testQueryStockReport() {
        QueryStockReportReq req = new QueryStockReportReq();
        req.setOrgCode("999999");
        req.setPluInfo("1010");
        this.reportController.queryStockReport(req);
    }
}
