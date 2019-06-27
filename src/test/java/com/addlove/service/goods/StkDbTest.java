package com.addlove.service.goods;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.addlove.service.goods.controller.StkDbController;
import com.addlove.service.goods.model.valid.CommonQueryDetailReq;

public class StkDbTest extends AddloveServiceGoodsApplicationTests{
    @Autowired
    private StkDbController stkDbController;
    
    @Test
    public void testQueryDbDetail() {
        CommonQueryDetailReq req = new CommonQueryDetailReq();
        req.setBillNo("1DBYW201906240001");
        this.stkDbController.queryDbDetail(req);
    }
}
