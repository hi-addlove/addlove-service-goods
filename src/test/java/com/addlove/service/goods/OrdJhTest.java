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

import com.addlove.service.goods.constants.GoodsResponseCode;
import com.addlove.service.goods.constants.GoodsCommonConstants.ProcedureResult;
import com.addlove.service.goods.controller.OrdJhController;
import com.addlove.service.goods.exception.ServiceException;
import com.addlove.service.goods.model.OrdJhHeadModel;
import com.addlove.service.goods.model.SkuPluExtendModel;
import com.addlove.service.goods.model.UsrUserModel;
import com.addlove.service.goods.model.valid.OrdJhBodyReq;
import com.addlove.service.goods.model.valid.OrdJhHeadReq;
import com.addlove.service.goods.service.GoodsCommonService;
import com.addlove.service.goods.util.DateUtil;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrdJhTest {
    @Autowired
    private OrdJhController ordJhController;
    
    @Autowired
    private GoodsCommonService commonService;
    
    @Test
    public void testAddNoPurchaseJh() {
        OrdJhHeadReq req = this.zuZhuangReq();
        //req.setBillNo("1JHYW2019062000004");
        req.setSaveType(1);
        this.ordJhController.addNoPurchaseJh(req);
    }
    
    @Test
    public void testEditNoPurchaseJh() {
        OrdJhHeadReq req = this.zuZhuangReq();
        req.setBillNo("");
        this.ordJhController.addNoPurchaseJh(req);
    }
    
    @Test
    public void testAccount() {
        OrdJhHeadModel headModel = new OrdJhHeadModel();
        headModel.setBillNo("1JHYW2019062000004");
        headModel.setYwType("0906");
        headModel.setJzrId(91L);
        headModel.setJzrCode("0000");
        headModel.setJzrName("Admin");
        headModel.setJzDate(DateUtil.getCurrentTime());
        Map<String, Object> accountMap = new HashMap<String, Object>();
        accountMap.put("ps_BillNo", headModel.getBillNo());
        accountMap.put("ps_YwType", headModel.getYwType());
        accountMap.put("pi_UserId", headModel.getJzrId());
        accountMap.put("ps_UserCode", headModel.getJzrCode());
        accountMap.put("ps_UserName", headModel.getJzrName());
        accountMap.put("pd_JzDate", headModel.getJzDate());
        Map<String, Object> resultMap = this.commonService.execAccountByCallProcedure(accountMap);
        if (null == resultMap) {
            throw new ServiceException(GoodsResponseCode.EXEC_PROCEDURE_ERROR.getCode(), 
                    GoodsResponseCode.EXEC_PROCEDURE_ERROR.getMsg());
        }
        int resultCode = null != resultMap.get("pi_Result") ? Integer.valueOf(resultMap.get("pi_Result").toString()) : -1;
        if (ProcedureResult.EXEC_ERROR_RECORD.getValue() == resultCode 
                || ProcedureResult.EXEC_ERROR_EXIT.getValue() == resultCode) {
            throw new ServiceException(GoodsResponseCode.EXEC_PROCEDURE_ERROR.getCode(), 
                    null != resultMap.get("ps_message") ? resultMap.get("ps_message").toString() : GoodsResponseCode.EXEC_PROCEDURE_ERROR.getMsg());
        }
    }
    
    @Test
    public void testGetSkuListByDept() {
        List<SkuPluExtendModel> skuList = 
                this.commonService.getSkuListByDept("999999", "637012", 10000000021L, "01");
        if (null != skuList) {
            for (SkuPluExtendModel model : skuList) {
                System.out.println(model.getPluId() + "---" + model.getPluCode() + "---" + model.getExPluCode());
            }
        }
    }
    
    @Test
    public void testGetAllUsers() {
        Map<String, Object> map = new HashMap<>();
        map.put("searchContent", "02");
        List<UsrUserModel> allUsers = this.commonService.getAllUsers(map);
        if (null != allUsers) {
            for (UsrUserModel user : allUsers) {
                System.out.println(user.getUserId() + "---" + user.getUserCode() + "---" + user.getUserName());
            }
        }
    }
    
    private OrdJhHeadReq zuZhuangReq() {
        OrdJhHeadReq req = new OrdJhHeadReq();
        req.setOrgCode("999999");
        req.setOrgName("四川爱达乐公司");
        req.setDepId(10000000021L);
        req.setDepCode("01");
        req.setDepName("前场");
        req.setSupCode("ADL");
        req.setSupName("四川爱达乐食品有限责任公司");
        req.setCntId(10000000361L);
        req.setHtCode("0001");
        req.setHtName("标准配送合同");
        req.setJhCount(2D);
        req.sethCost(2D);
        req.setwCost(2D);
        req.setJtaxTotal(2D);
        req.setPsCost(2D);
        req.setsTotal(2D);
        req.setCjTotal(2D);
        req.setRemark("无采购验收单-1");
        req.setTelePhone("13566778899");
        req.setFax("028-1111");
        req.setLinkMan("tom");
        req.setLkmTel("13566778899");
        //明细
        List<OrdJhBodyReq> bodyList = new LinkedList<OrdJhBodyReq>();
        OrdJhBodyReq bodyReq = new OrdJhBodyReq();
        bodyReq.setPluId(10000000001L);
        bodyReq.setPluCode("10101001");
        bodyReq.setPluName("椰蓉面包");
        bodyReq.setBarCode("6944956610163");
        bodyReq.setSpec("");
        bodyReq.setUnit("个");
        bodyReq.setCarGoNo("");
        bodyReq.setDepId(10000000021L);
        bodyReq.setDepCode("01");
        bodyReq.setDepName("前场");
        bodyReq.setHjPrice(0.0);
        bodyReq.setWjPrice(0.0);
        bodyReq.setPsPrice(0.0);
        bodyReq.setPrice(2.0);
        bodyReq.setjTaxRate(1);
        bodyReq.setPackUnit("个");
        bodyReq.setPackQty(10.0);
        bodyReq.setPackCount(10.0);
        bodyReq.setSglCount(1.0);
        bodyReq.setJhCount(1.0);
        bodyReq.setPsShCount(1.0);
        bodyReq.sethCost(1.0);
        bodyReq.setwCost(1.0);
        bodyReq.setjTaxTotal(1.0);
        bodyReq.setPsCost(1.0);
        bodyReq.setsTotal(1.0);
        bodyReq.setCjTotal(1.0);
        bodyReq.setCjRate(1.0);
        bodyReq.setScDate("2019-12-06");
        bodyReq.setDqDate("2019-12-06");
        bodyReq.setBzDays(10);
        bodyReq.setCxInfo("赠品");
        bodyReq.setRemark("商品-1");
        bodyReq.setHjsTotal(10.0);
        bodyReq.setWjsTotal(10.0);
        bodyReq.setwPsPrice(1.0);
        bodyReq.setwPsCost(1.0);
        bodyReq.setxTaxRate(1);
        bodyReq.setxTaxTotal(10.0);
        bodyList.add(bodyReq);
        OrdJhBodyReq bodyReq2 = new OrdJhBodyReq();
        bodyReq2.setPluId(10000000001L);
        bodyReq2.setPluCode("10101001");
        bodyReq2.setPluName("椰蓉面包");
        bodyReq2.setBarCode("6944956610163");
        bodyReq2.setSpec("");
        bodyReq2.setUnit("个");
        bodyReq2.setCarGoNo("");
        bodyReq2.setDepId(10000000021L);
        bodyReq2.setDepCode("01");
        bodyReq2.setDepName("前场");
        bodyReq2.setHjPrice(0.0);
        bodyReq2.setWjPrice(0.0);
        bodyReq2.setPsPrice(0.0);
        bodyReq2.setPrice(2.0);
        bodyReq2.setjTaxRate(1);
        bodyReq2.setPackUnit("个");
        bodyReq2.setPackQty(10.0);
        bodyReq2.setPackCount(10.0);
        bodyReq2.setSglCount(1.0);
        bodyReq2.setJhCount(1.0);
        bodyReq2.setPsShCount(1.0);
        bodyReq2.sethCost(1.0);
        bodyReq2.setwCost(1.0);
        bodyReq2.setjTaxTotal(1.0);
        bodyReq2.setPsCost(1.0);
        bodyReq2.setsTotal(1.0);
        bodyReq2.setCjTotal(1.0);
        bodyReq2.setCjRate(1.0);
        bodyReq2.setScDate("2019-12-06");
        bodyReq2.setDqDate("2019-12-06");
        bodyReq2.setBzDays(10);
        bodyReq2.setCxInfo("赠品");
        bodyReq2.setRemark("商品-1");
        bodyReq2.setHjsTotal(10.0);
        bodyReq2.setWjsTotal(10.0);
        bodyReq2.setwPsPrice(1.0);
        bodyReq2.setwPsCost(1.0);
        bodyReq2.setxTaxRate(1);
        bodyReq2.setxTaxTotal(10.0);
        bodyList.add(bodyReq2);
        //设置明细
        req.setBodyList(bodyList);
        return req;
    }
}
