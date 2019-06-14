package com.addlove.service.goods;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.addlove.service.goods.constants.GoodsOrdThConstants.BillType;
import com.addlove.service.goods.controller.OrdJhController;
import com.addlove.service.goods.controller.OrdThApplyController;
import com.addlove.service.goods.message.ResponseMessage;
import com.addlove.service.goods.model.PageModel;
import com.addlove.service.goods.model.valid.OrdJhQueryDetailReq;
import com.addlove.service.goods.model.valid.OrdJhQueryPageReq;
import com.addlove.service.goods.model.valid.OrdThApplyBodyDiffReq;
import com.addlove.service.goods.model.valid.OrdThApplyHeadDiffReq;
import com.addlove.service.goods.service.OrdThApplyService;
import com.alibaba.fastjson.JSONObject;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AddloveServiceGoodsApplicationTests {
    @Autowired
    private OrdJhController ordJhController;
    
    @Autowired
    private OrdThApplyService ordThApplyService;
    
    @Autowired
    private OrdThApplyController ordThApplyController;
    
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
    
    @Test
    public void testQueryOrderJhDetail() {
        OrdJhQueryDetailReq req = new OrdJhQueryDetailReq();
        req.setBillNo("1JHYW2019060400002");
        ResponseMessage res = this.ordJhController.queryOrderJhDetail(req);
        JSONObject json = (JSONObject) res.getData();
        System.out.println(json);
    }
    
    @Test
    public void testQueryOrderThDetail() {
        OrdJhQueryDetailReq req = new OrdJhQueryDetailReq();
        req.setBillNo("1THSQ201906130002");
        ResponseMessage res = this.ordThApplyController.queryOrderThDetail(req);
        JSONObject json = (JSONObject) res.getData();
        System.out.println(json);
    }
    
    @Test
    public void testGetBillNo() {
        Map<String, Object> map = new HashMap<>();
        map.put("ps_BillType", BillType.RETURN_APPLY.getValue());
        //map.put("ps_BillNo", "");
        String billNo = this.ordThApplyService.getBillNoByCallProcedure(map);
        System.out.println("billNo=================" + billNo);
    }
    
    @Test
    public void testGenerateDifferentBill() {
        OrdThApplyHeadDiffReq req = this.zuzhuang();
        this.ordThApplyController.generateDifferentBill(req);
    }
    
    private OrdThApplyHeadDiffReq zuzhuang() {
        OrdThApplyHeadDiffReq req = new OrdThApplyHeadDiffReq();
        req.setBillNo("123");
        req.setOrgCode("999999");
        req.setOrgName("测试");
        req.setInOrgCode("999999");
        req.setZbOrgCode("111");
        req.setZbOrgName("总部编码");
        req.setSupCode("sup123");
        req.setSupName("供应商名称");
        req.setCntId(123L);
        req.setCkCode("ck123");
        req.setCkName("cangku");
        req.setThCount(1.0);
        req.sethCost(1.0);
        req.setwCost(1.0);
        req.setJtaxTotal(1.0);
        req.setsTotal(1.0);
        req.setCjTotal(1.0);
        req.setPsCost(1.0);
        req.setApplyStatus("1");
        List<OrdThApplyBodyDiffReq> bodyList = new LinkedList<>();
        OrdThApplyBodyDiffReq bodyReq = new OrdThApplyBodyDiffReq();
        bodyReq.setSerialNo(1L);
        bodyReq.setPluId(123L);
        bodyReq.setPluCode("123code");
        bodyReq.setPluName("商品名称");
        bodyReq.setExPluCode("123code");
        bodyReq.setBarCode("1");
        bodyReq.setSpec("1");
        bodyReq.setUnit("1");
        bodyReq.setCarGoNo("1");
        bodyReq.setDepId(123L);
        bodyReq.setDepCode("depcode");
        bodyReq.setDepName("dep name");
        bodyReq.setPsPrice(1.0);
        bodyReq.setPackUnit("1");
        bodyReq.setPackQty(1.0);
        bodyReq.setPackCount(1.0);
        bodyReq.setSglCount(1.0);
        bodyReq.setThCount(1.0);
        bodyReq.sethCost(1.0);
        bodyReq.setwCost(1.0);
        bodyReq.setjTaxTotal(1.0);
        bodyReq.setPsCost(1.0);
        bodyReq.setsTotal(1.0);
        bodyReq.setCjTotal(1.0);
        bodyReq.setCjRate(1.0);
        bodyReq.setHjsTotal(1.0);
        bodyReq.setWjsTotal(1.0);
        //bodyReq.setSqThCount(1.0);
        bodyList.add(bodyReq);
        OrdThApplyBodyDiffReq bodyReq2 = new OrdThApplyBodyDiffReq();
        bodyReq2.setSerialNo(2L);
        bodyReq2.setPluId(123L);
        bodyReq2.setPluCode("123code");
        bodyReq2.setPluName("商品名称");
        bodyReq2.setExPluCode("123code");
        bodyReq2.setBarCode("1");
        bodyReq2.setSpec("1");
        bodyReq2.setUnit("1");
        bodyReq2.setCarGoNo("1");
        bodyReq2.setDepId(123L);
        bodyReq2.setDepCode("depcode");
        bodyReq2.setDepName("dep name");
        bodyReq2.setPsPrice(1.0);
        bodyReq2.setPackUnit("1");
        bodyReq2.setPackQty(1.0);
        bodyReq2.setPackCount(1.0);
        bodyReq2.setSglCount(1.0);
        bodyReq2.setThCount(1.0);
        bodyReq2.sethCost(1.0);
        bodyReq2.setwCost(1.0);
        bodyReq2.setjTaxTotal(1.0);
        bodyReq2.setPsCost(1.0);
        bodyReq2.setsTotal(1.0);
        bodyReq2.setCjTotal(1.0);
        bodyReq2.setCjRate(1.0);
        bodyReq2.setHjsTotal(1.0);
        bodyReq2.setWjsTotal(1.0);
        //bodyReq2.setSqThCount(1.0);
        bodyList.add(bodyReq2);
        req.setBodyList(bodyList);
        return req;
    }
}
