package com.addlove.service.goods;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.addlove.service.goods.constants.GoodsOrdJhConstants.BillType;
import com.addlove.service.goods.controller.OrdJhController;
import com.addlove.service.goods.message.ResponseMessage;
import com.addlove.service.goods.model.OrdJhBodyModel;
import com.addlove.service.goods.model.PageModel;
import com.addlove.service.goods.model.valid.OrdJhQueryDetailReq;
import com.addlove.service.goods.model.valid.OrdJhQueryPageReq;
import com.addlove.service.goods.service.OrdThApplyService;
import com.alibaba.fastjson.JSONObject;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AddloveServiceGoodsApplicationTests {
    @Autowired
    private OrdJhController ordJhController;
    
    @Autowired
    private OrdThApplyService ordThApplyService;
    
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
    
    @Test
    public void testGetBillNo() {
        Map<String, Object> map = new HashMap<>();
        map.put("ps_BillType", BillType.RETURN_APPLY.getValue());
        //map.put("ps_BillNo", "");
        String billNo = this.ordThApplyService.getBillNoByCallProcedure(map);
        System.out.println("billNo=================" + billNo);
    }
}
