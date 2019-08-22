package com.addlove.service.goods;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import com.addlove.service.goods.constants.GoodsAdlYhConstants.yhType;
import com.addlove.service.goods.constants.GoodsOrdJhConstants.SaveType;
import com.addlove.service.goods.controller.GoodsCommonController;
import com.addlove.service.goods.controller.StkDbController;
import com.addlove.service.goods.model.OrdAdlYhPluCursorModel;
import com.addlove.service.goods.model.valid.CommonOrgAndDeptReq;
import com.addlove.service.goods.model.valid.CommonOrgAndSupAndCntReq;
import com.addlove.service.goods.model.valid.CommonQueryDetailReq;
import com.addlove.service.goods.model.valid.StkDbBodyReq;
import com.addlove.service.goods.model.valid.StkDbHeadReq;
import com.addlove.service.goods.service.OrdAdlYhService;
import oracle.jdbc.OracleCallableStatement;
import oracle.jdbc.OracleTypes;

public class StkDbTest extends AddloveServiceGoodsApplicationTests{
    @Autowired
    private StkDbController stkDbController;
    @Autowired
    private GoodsCommonController commonController;
    @Autowired
    private OrdAdlYhService ordAdlYhService;
    
    @Test
    public void testQueryDbDetail() {
        CommonQueryDetailReq req = new CommonQueryDetailReq();
        req.setBillNo("1DBYW201906240001");
        this.stkDbController.queryDbDetail(req);
    }
    
    @Test
    public void testAddStkDb() {
      StkDbHeadReq req = this.getStkDbHeadReq();
      req.setBillNo("1DBYW201906280008");
      req.setSaveType(SaveType.EXEC_ACCOUNT.getValue());
      this.stkDbController.addStkDb(req);
    }
    
    @Test
    public void testGetSkuListByDept() {
        CommonOrgAndDeptReq req = new CommonOrgAndDeptReq();
        req.setOrgCode("999999");
        req.setShOrgCode("6321");
        req.setDeptId(10000000024L);
        this.commonController.getSkuListByDept(req);
    }
    
    @Test
    public void testGetPurchaseReturnSkus() {
        CommonOrgAndSupAndCntReq req = new CommonOrgAndSupAndCntReq();
        req.setOrgCode("999999");
        req.setCntId(10000000421L);
        req.setCkCode("01");
        this.commonController.getPurchaseReturnSkus(req);
    }
    
    @SuppressWarnings("unchecked")
    @Test
    public void testExecMrCountsProcedure() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("ps_OrgCode", "999999");
        map.put("ps_ModelCode", "001");
        map.put("ps_DepId", "10000000021");
        map.put("ps_Type", yhType.ORDINARY_YH.getValue());
        map.put("pc_Data", new ArrayList<OrdAdlYhPluCursorModel>());
        this.ordAdlYhService.test(map);
        List<OrdAdlYhPluCursorModel> models = (List<OrdAdlYhPluCursorModel>) map.get("pc_Data");
        if (!models.isEmpty()) {
            System.out.println("---------------有数据--------------------");
        }
        System.out.println(map);
        
        Map<String, Object> map2 = new HashMap<String, Object>();
        map2.put("ps_OrgCode", "999999");
        map2.put("ps_ModelCode", "001");
        map2.put("ps_DepId", "10000000021");
        map2.put("ps_Type", yhType.ORDINARY_YH.getValue());
        List<?> result = new ArrayList<Map<String, Object>>();
//        map2.put("pc_Data", result);
        this.ordAdlYhService.getPcDatas(map2);
        result = (List<?>) map2.get("result");
        if (!result.isEmpty()) {
            System.out.println("---------------来一波大数据--------------------");
        }
        System.out.println(result);
    }
    
    @Test
    public void testCursor() {
        try {
            Class.forName("oracle.jdbc.OracleDriver");
            Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@121.196.214.2:1521:orcl", 
                    "adlhscmp", "adlhscmp");
            CallableStatement pc = conn.prepareCall("{CALL sOrd_ModelYh_GetPlu(?,?,?,?,?,?,?)}");
            pc.setString(1, "999999");
            pc.setString(2, "001");
            pc.setString(3, "10000000021");
            pc.setString(4, yhType.ORDINARY_YH.getValue());
            pc.registerOutParameter(5, OracleTypes.NUMERIC);
            pc.registerOutParameter(6, OracleTypes.VARCHAR);
            pc.registerOutParameter(7, OracleTypes.CURSOR);
            pc.execute();
            String ms = pc.getString(6);
            System.out.println(ms);
            ResultSet cursor = ((OracleCallableStatement)pc).getCursor(7);
            while(cursor.next()) {
                System.out.println(cursor.getString(1));
            }
            ResultSet rs = (ResultSet) pc.getObject(7);
            while(rs.next()) {
                System.out.println(rs.getString("Plucode"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Test
    public void testOracleCursor() {
        try {
            Class.forName("oracle.jdbc.OracleDriver");
            Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@121.196.214.2:1521:orcl", 
                    "adlhscmp", "adlhscmp");
            OracleCallableStatement ocs = (OracleCallableStatement) conn.prepareCall("{CALL sOrd_ModelYh_GetPlu(?,?,?,?,?,?,?)}");
            ocs.setString(1, "999999");
            ocs.setString(2, "001");
            ocs.setString(3, "10000000021");
            ocs.setString(4, yhType.ORDINARY_YH.getValue());
            ocs.registerOutParameter(5, OracleTypes.NUMERIC);
            ocs.registerOutParameter(6, OracleTypes.VARCHAR);
            ocs.registerOutParameter(7, OracleTypes.CURSOR);
            ocs.execute();
            String ms = ocs.getString(6);
            System.out.println(ms);
            ResultSet cursor = ocs.getCursor(7);
            while(cursor.next()) {
                System.out.println(cursor.getString(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private StkDbHeadReq getStkDbHeadReq() {
        StkDbHeadReq req = new StkDbHeadReq();
        req.setEmpId(100L);
        req.setEmpCode("100");
        req.setEmpName("lw");
        req.setOrgCode("999999");
        req.setOrgName("样板门店");
        req.setDepId(100L);
        req.setDepCode("01");
        req.setDepName("现场");
        req.setShOrgCode("690002");
        req.setShOrgName("德阳店");
        req.setShDepId(100L);
        req.setShDepCode("02");
        req.setShDepName("烤花");
        req.setDbCount(100D);
        req.setwCost(10D);
        req.sethCost(10D);
        req.setRemark("");
        req.setInOrgCode("999999");
        req.setInShOrgCode("690002");
        req.setRhCost(10D);
        req.setRwCost(10D);
        req.setsTotal(100D);
        List<StkDbBodyReq> bodyList = new LinkedList<StkDbBodyReq>();
        StkDbBodyReq bodyReq = new StkDbBodyReq();
        bodyReq.setPluId(100L);
        bodyReq.setExPluCode("001");
        bodyReq.setExPluName("管我");
        bodyReq.setPluCode("001");
        bodyReq.setPluName("管不着");
        bodyReq.setBarCode("条形码");
        bodyReq.setSpec("123");
        bodyReq.setUnit("bao");
        bodyReq.setPackUnit("10");
        bodyReq.setPackQty(10D);
        bodyReq.setPackCount(10D);
        bodyReq.setSglCount(10D);
        bodyReq.setDbCount(10D);
        bodyReq.setHjPrice(10D);
        bodyReq.setWjPrice(10D);
        bodyReq.setPrice(10D);
        bodyReq.setjTaxRate(0D);
        bodyReq.sethCost(10D);
        bodyReq.setwCost(10D);
        bodyReq.setRemark("");
        bodyReq.setShDepId(100L);
        bodyReq.setShDepCode("01");
        bodyReq.setShDepName("现场");
        bodyReq.setRhCost(10D);
        bodyReq.setRwCost(10D);
        bodyReq.setsTotal(10D);
        bodyReq.setRhJPrice(10D);
        bodyReq.setRwJPrice(10D);
        bodyReq.setCarGoNo("货号");
        bodyList.add(bodyReq);
        StkDbBodyReq bodyReq2 = new StkDbBodyReq();
        bodyReq2.setPluId(100L);
        bodyReq2.setExPluCode("001");
        bodyReq2.setExPluName("管我");
        bodyReq2.setPluCode("001");
        bodyReq2.setPluName("管不着");
        bodyReq2.setBarCode("条形码");
        bodyReq2.setSpec("123");
        bodyReq2.setUnit("bao");
        bodyReq2.setPackUnit("10");
        bodyReq2.setPackQty(10D);
        bodyReq2.setPackCount(10D);
        bodyReq2.setSglCount(10D);
        bodyReq2.setDbCount(10D);
        bodyReq2.setHjPrice(10D);
        bodyReq2.setWjPrice(10D);
        bodyReq2.setPrice(10D);
        bodyReq2.setjTaxRate(0D);
        bodyReq2.sethCost(10D);
        bodyReq2.setwCost(10D);
        bodyReq2.setRemark("");
        bodyReq2.setShDepId(100L);
        bodyReq2.setShDepCode("01");
        bodyReq2.setShDepName("现场");
        bodyReq2.setRhCost(10D);
        bodyReq2.setRwCost(10D);
        bodyReq2.setsTotal(10D);
        bodyReq2.setRhJPrice(10D);
        bodyReq2.setRwJPrice(10D);
        bodyReq2.setCarGoNo("货号");
        bodyList.add(bodyReq2);
        req.setBodyList(bodyList);
        return req;
    }
}
