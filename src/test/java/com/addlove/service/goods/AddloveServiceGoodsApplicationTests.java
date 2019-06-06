package com.addlove.service.goods;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.addlove.service.goods.controller.OrdJhController;
import com.addlove.service.goods.message.ResponseMessage;
import com.addlove.service.goods.model.OrdJhBodyModel;
import com.addlove.service.goods.model.PageModel;
import com.addlove.service.goods.model.valid.OrdJhQueryDetailReq;
import com.addlove.service.goods.model.valid.OrdJhQueryPageReq;
import com.alibaba.fastjson.JSONObject;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AddloveServiceGoodsApplicationTests {
    @Autowired
    private OrdJhController ordJhController;
    
    @Test
    public void contextLoads() {}
    
    @Test
    public void testQueryOrderJh() {
        OrdJhQueryPageReq req = new OrdJhQueryPageReq();
        req.setOrgCode("111");
        ResponseMessage res = this.ordJhController.queryOrderJh(req);
        PageModel model = (PageModel) res.getData();
        System.out.println(model.getResult());
    }
    
    @SuppressWarnings("unchecked")
    @Test
    public void testQueryOrderJhDetail() {
        OrdJhQueryDetailReq req = new OrdJhQueryDetailReq();
        req.setBillNo("1JHYW2019060400002");
        ResponseMessage res = this.ordJhController.queryOrderJhDetail(req);
        List<OrdJhBodyModel> ordJhSkus = (List<OrdJhBodyModel>) res.getData();
        System.out.println(JSONObject.toJSONString(ordJhSkus));
        if (null != ordJhSkus) {
            for (OrdJhBodyModel sku : ordJhSkus) {
                System.out.println(sku.getSerialNo() + "_" + sku.getPluCode() + "_" + sku.getPluName());
            }
        }
    }
}
