package com.addlove.service.goods;

import java.util.LinkedList;
import java.util.List;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import com.addlove.service.goods.controller.ProPlanController;
import com.addlove.service.goods.model.valid.ProPlanDoneDetailReq;
import com.addlove.service.goods.model.valid.ProPlanDonePrimaryReq;

public class ProPlanTest extends AddloveServiceGoodsApplicationTests{
    @Autowired
    private ProPlanController proPlanController;
    
    @Test
    public void testExecProPlanDone() {
        ProPlanDonePrimaryReq req = new ProPlanDonePrimaryReq();
        List<ProPlanDoneDetailReq> doneList = new LinkedList<>();
        ProPlanDoneDetailReq detailReq = new ProPlanDoneDetailReq();
        detailReq.setBillNo("1SCJH201907010001");
        detailReq.setSerialNo(1L);
        detailReq.setPluId(10000000009L);
        detailReq.setRequestdate("2019-07-01");
        detailReq.setPlantime("17");
        detailReq.setProduceCount(10D);
        doneList.add(detailReq);
        ProPlanDoneDetailReq detailReq2 = new ProPlanDoneDetailReq();
        detailReq2.setBillNo("1SCJH201907010001");
        detailReq2.setSerialNo(2L);
        detailReq2.setPluId(10000000015L);
        detailReq2.setRequestdate("2019-07-01");
        detailReq2.setPlantime("17");
        detailReq2.setProduceCount(10D);
        doneList.add(detailReq2);
        req.setDoneList(doneList);
        this.proPlanController.execProPlanDone(req);
    }
}
