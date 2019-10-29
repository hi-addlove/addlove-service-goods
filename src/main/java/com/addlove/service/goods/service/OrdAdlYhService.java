package com.addlove.service.goods.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.addlove.service.goods.constants.GoodsResponseCode;
import com.addlove.service.goods.constants.GoodsAdlYhConstants.isUse;
import com.addlove.service.goods.constants.GoodsAdlYhConstants.YhType;
import com.addlove.service.goods.constants.GoodsCommonConstants.ProcedureResult;
import com.addlove.service.goods.dao.OrdAdlYhDao;
import com.addlove.service.goods.dao.SkuPdCSDao;
import com.addlove.service.goods.exception.ServiceException;
import com.addlove.service.goods.model.EtpAddressModel;
import com.addlove.service.goods.model.OrdAdlYhBodyModel;
import com.addlove.service.goods.model.OrdAdlYhHeadModel;
import com.addlove.service.goods.model.OrdAdlYhPageModel;
import com.addlove.service.goods.model.OrdAdlYhPluCursorModel;
import com.addlove.service.goods.model.OrdYhCycleModel;
import com.addlove.service.goods.model.OrdYhTempletBodyModel;
import com.addlove.service.goods.model.OrdYhTempletHeadModel;
import com.addlove.service.goods.model.SkuPluModel;
import com.addlove.service.goods.model.SkuPluPacketModel;
import com.addlove.service.goods.model.SkuYhPSBodyModel;
import com.addlove.service.goods.util.DateUtil;
import com.addlove.service.goods.util.LoggerEnhance;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;

/**
 * ADL要货逻辑层
 * @author lw
 *
 */
@Service
public class OrdAdlYhService {
    /**OrdAdlYhService类日志 */
    private static final Logger LOGGER = LoggerFactory.getLogger(OrdAdlYhService.class);
    
    /**
     * 默认单次批量查询plu数量
     */
    public static final int DEFAULT_BATCH_QUERY_SIZE = 500;
    
    @Autowired
    private OrdAdlYhDao ordAdlYhDao;
    
    @Autowired
    private SkuPdCSDao skuPdCSDao;
    
    /**
     * 要货列表
     * @param queryModel
     * @return List<OrdAdlYhHeadModel> 
     */
    public List<OrdAdlYhHeadModel> queryYhPage(OrdAdlYhPageModel queryModel) {
        PageHelper.startPage(queryModel.getPageNo(), queryModel.getPageSize(), true);
        List<OrdAdlYhHeadModel> yhList = this.ordAdlYhDao.queryYhPage(queryModel);
        if (null != yhList && !yhList.isEmpty()) {
            for (OrdAdlYhHeadModel model : yhList) {
                if (StringUtils.isNotBlank(model.getLrDate()) && model.getLrDate().length() > 19) {
                    model.setLrDate(model.getLrDate().substring(0, 19));
                }
                if (StringUtils.isNotBlank(model.getJzDate()) && model.getJzDate().length() > 19) {
                    model.setJzDate(model.getJzDate().substring(0, 19));
                }
            }
        }
        return yhList;
    }
    
    /**
     * 新增要货
     * @param headModel
     */
    @Transactional
    public void addYh(OrdAdlYhHeadModel headModel) {
        this.ordAdlYhDao.insertYhHead(headModel);
        this.ordAdlYhDao.insertYhBodys(headModel.getBodyList());
    }
    
    /**
     * 编辑要货
     * @param headModel
     */
    @Transactional
    public void editYh(OrdAdlYhHeadModel headModel) {
        this.ordAdlYhDao.delYhHead(headModel.getBillNo());
        this.ordAdlYhDao.delYhBodys(headModel.getBillNo());
        this.ordAdlYhDao.insertYhHead(headModel);
        this.ordAdlYhDao.insertYhBodys(headModel.getBodyList());
    }
    
    /**
     * 删除要货数据
     * @param billNo
     */
    @Transactional
    public void delYh(String billNo) {
        this.ordAdlYhDao.delYhHead(billNo);
        this.ordAdlYhDao.delYhBodys(billNo);
    }
    
    /**
     * 查询要货详情
     * @param billNo
     * @return OrdAdlYhHeadModel
     */
    public OrdAdlYhHeadModel queryYhDetails(String billNo) {
        OrdAdlYhHeadModel yhHead = this.ordAdlYhDao.getYhHead(billNo);
        if (StringUtils.isNotBlank(yhHead.getLrDate()) && yhHead.getLrDate().length() > 19) {
            yhHead.setLrDate(yhHead.getLrDate().substring(0, 19));
        }
        List<OrdAdlYhBodyModel> bodyList = this.ordAdlYhDao.getYhBodys(billNo);
        yhHead.setBodyList(bodyList);
        return yhHead;
    }
    
    /**
     * 获取要货商品
     * @param orgCode
     * @param depId
     * @param modelCode
     * @return JSONArray
     */
    @Transactional
    public JSONArray getYhSkuList(String orgCode, Long depId, String modelCode) {
        long startTime = System.currentTimeMillis();
        JSONArray backArray = new JSONArray();
        //获取部门商品
        Set<SkuPluModel> deptSkus = new HashSet<>();
        List<SkuPluModel> pdDeptSkus = this.skuPdCSDao.getPdSkuListByDept(orgCode, depId);
        if (null != pdDeptSkus && !pdDeptSkus.isEmpty()) {
            deptSkus.addAll(pdDeptSkus);
        }
        List<SkuPluModel> otherDeptSkus = this.skuPdCSDao.getOtherDeptSkus(orgCode, depId);
        if (null != otherDeptSkus && !otherDeptSkus.isEmpty()) {
            deptSkus.addAll(otherDeptSkus);
        }
        //获取模板商品
        List<OrdYhTempletBodyModel> templetSkus = this.ordAdlYhDao.getTempletSkus(orgCode, depId, modelCode);
        if (null == templetSkus || templetSkus.isEmpty() ) {
            return backArray;
        }
        //获取要货参数商品（包括：最小、最大要货量及倍数）
        List<SkuYhPSBodyModel> pSSkus = this.ordAdlYhDao.getYhPSSkus(orgCode);
        if (null == pSSkus || pSSkus.isEmpty() ) {
            return backArray;
        }
        //将模板商品合并到部门商品
        for (OrdYhTempletBodyModel templetModel : templetSkus) {
            SkuPluModel pluModel = new SkuPluModel();
            pluModel.setPluId(templetModel.getPluId());
            pluModel.setPluCode(templetModel.getPluCode());
            pluModel.setPluName(templetModel.getPluName());
            pluModel.setBarCode(templetModel.getBarCode());
            pluModel.setUnit(templetModel.getUnit());
            pluModel.setSpec(templetModel.getSpec());
            pluModel.setPrice(templetModel.getPrice());
            deptSkus.add(pluModel);
        }
        //获取要货周期的商品
        List<OrdYhCycleModel> cycleSkus = this.ordAdlYhDao.getYhCycleSkus(orgCode);
        Map<String, OrdYhCycleModel> cycleMap = new HashMap<String, OrdYhCycleModel>();
        if (null != cycleSkus && !cycleSkus.isEmpty()) {
            for (OrdYhCycleModel cycleModel : cycleSkus) {
                cycleMap.put(cycleModel.getPluCode(), cycleModel);
            }
        }
        //总商品中剔除不在当天要货周期的商品
        Iterator<SkuPluModel> iterator = deptSkus.iterator();
        List<Map<String, Object>> pluList = new LinkedList<Map<String, Object>>();
        while (iterator.hasNext()) {
            SkuPluModel pluModel = iterator.next();
            String key = pluModel.getPluCode();
            OrdYhCycleModel cycleModel = cycleMap.get(key);
            boolean flag = this.isRemoveCycle(cycleModel);
            if (flag) {
                iterator.remove();
            }else {
                Map<String, Object> queryMap = new HashMap<String, Object>();
                queryMap.put("pluId", pluModel.getPluId());
                pluList.add(queryMap);
            }
        }
        //获取包装数据
        List<SkuPluPacketModel> packets = this.ordAdlYhDao.getPackets(orgCode, depId);
        Map<Long, SkuPluPacketModel> packetMap = new HashMap<Long, SkuPluPacketModel>();
        if (null != packets && !packets.isEmpty()) {
            for (SkuPluPacketModel packetModel : packets) {
                packetMap.put(packetModel.getPluId(), packetModel);
            }
        }
        //获取要货参数商品（包括：最小、最大要货量及倍数）
        Map<String, SkuYhPSBodyModel> psMap = new HashMap<String, SkuYhPSBodyModel>();
        if (null != pSSkus && !pSSkus.isEmpty()) {
            for (SkuYhPSBodyModel psModel : pSSkus) {
                psMap.put(psModel.getPluCode(), psModel);
            }
        }
        //获取商品可用库存数量
        List<Map<String, Object>> kcList = new ArrayList<>();
        int size = pluList.size();
        if (size > 0) {
            if (DEFAULT_BATCH_QUERY_SIZE < size) {
                int part = size / DEFAULT_BATCH_QUERY_SIZE; // 分批数
                for (int i = 0; i < part; i++) {
                    List<Map<String, Object>> partPluList = new LinkedList<Map<String, Object>>();
                    partPluList.addAll(pluList.subList(0, DEFAULT_BATCH_QUERY_SIZE));
                    List<Map<String,Object>> kcPartList = this.ordAdlYhDao.getYhKcSum(partPluList, orgCode);
                    kcList.addAll(kcPartList);
                    pluList.subList(0, DEFAULT_BATCH_QUERY_SIZE).clear();
                }
                if (!pluList.isEmpty()) {
                    List<Map<String,Object>> kcPartList = this.ordAdlYhDao.getYhKcSum(pluList, orgCode);
                    kcList.addAll(kcPartList);
                }
            }else {
                kcList = this.ordAdlYhDao.getYhKcSum(pluList, orgCode);
            }
        }
        Map<Long, Object> kcMap = new HashMap<Long, Object>();
        for (Map<String, Object> map : kcList) {
            long pluId = Long.parseLong(null != map.get("PLUID") ? map.get("PLUID").toString() : "0") ;
            kcMap.put(pluId, null != map.get("KCCOUNT") ? map.get("KCCOUNT").toString() : "0");
        }
        //获取明日到货数量
        List<OrdAdlYhPluCursorModel> mrModels = this.getMrCounts(orgCode, depId, modelCode);
        Map<String, OrdAdlYhPluCursorModel> mrMap = new HashMap<String, OrdAdlYhPluCursorModel>();
        if (!mrModels.isEmpty()) {
            for (OrdAdlYhPluCursorModel cursorModel : mrModels) {
                mrMap.put(cursorModel.getPluCode(), cursorModel);
            }
        }
        //整合商品
        for (SkuPluModel pluModel : deptSkus) {
            String key = pluModel.getPluCode();
            JSONObject backJson = new JSONObject();
            backJson.put("pluId", pluModel.getPluId());
            backJson.put("pluCode", pluModel.getPluCode());
            backJson.put("pluName", pluModel.getPluName());
            backJson.put("barCode", pluModel.getBarCode());
            backJson.put("unit", pluModel.getUnit());
            backJson.put("spec", pluModel.getSpec());
            backJson.put("price", pluModel.getPrice());
            SkuYhPSBodyModel psModel = psMap.get(key);
            if (null != psModel) {
                backJson.put("minCount", psModel.getMinCount());
                backJson.put("maxCount", psModel.getMaxCount());
                backJson.put("times", psModel.getTimes());
            }else {
                backJson.put("minCount", 0);
                backJson.put("maxCount", 99999);
                backJson.put("times", 1);
            }
            if (null != kcMap.get(pluModel.getPluId())) {
                backJson.put("kcCount", Double.valueOf(kcMap.get(pluModel.getPluId()).toString()));
            }else {
                backJson.put("kcCount", 0);
            }
            OrdAdlYhPluCursorModel cursorModel = mrMap.get(key);
            if (null != cursorModel) {
                backJson.put("mRJhCount", cursorModel.getmRJhCount());
            }else {
                backJson.put("mRJhCount", 0.0);
            }
            SkuPluPacketModel packetModel = packetMap.get(pluModel.getPluId());
            if (null != packetModel) {
                backJson.put("packUnit", packetModel.getPackUnit());
                backJson.put("packQty", packetModel.getPackQty());
            }else {
                backJson.put("packUnit", "");
                backJson.put("packQty", 0);
            }
            backArray.add(backJson);
        }
        long endTime = System.currentTimeMillis();
        LoggerEnhance.info(LOGGER, "查询要货商品消耗时间:{}", (endTime - startTime));
        return backArray;
    }
    
    /**
     * 获取紧急要货商品
     * @param orgCode
     * @param depId
     * @return JSONArray
     */
    @Transactional
    public JSONArray getUrgentYhSkuList(String orgCode, Long depId) {
        long startTime = System.currentTimeMillis();
        JSONArray backArray = new JSONArray();
        //获取部门商品
        Set<SkuPluModel> deptSkus = new HashSet<>();
        List<SkuPluModel> pdDeptSkus =  this.skuPdCSDao.getPdSkuListByDept(orgCode, depId);
        if (null != pdDeptSkus && !pdDeptSkus.isEmpty()) {
            deptSkus.addAll(pdDeptSkus);
        }
        List<SkuPluModel> otherDeptSkus = this.skuPdCSDao.getOtherDeptSkus(orgCode, depId);
        if (null != otherDeptSkus && !otherDeptSkus.isEmpty()) {
            deptSkus.addAll(otherDeptSkus);
        }
        //获取要货参数商品（包括：最小、最大要货量及倍数）
        List<SkuYhPSBodyModel> pSSkus = this.ordAdlYhDao.getYhPSSkus(orgCode);
        if (null == pSSkus || pSSkus.isEmpty() ) {
            return backArray;
        }
        //获取要货参数商品（包括：最小、最大要货量及倍数）
        Map<String, SkuYhPSBodyModel> psMap = new HashMap<String, SkuYhPSBodyModel>();
        if (null != pSSkus && !pSSkus.isEmpty()) {
            for (SkuYhPSBodyModel psModel : pSSkus) {
                psMap.put(psModel.getPluCode(), psModel);
            }
        }
        Iterator<SkuPluModel> iterator = deptSkus.iterator();
        List<Map<String, Object>> pluList = new LinkedList<Map<String, Object>>();
        while (iterator.hasNext()) {
            SkuPluModel pluModel = iterator.next();
            Map<String, Object> queryMap = new HashMap<String, Object>();
            queryMap.put("pluId", pluModel.getPluId());
            pluList.add(queryMap);
        }
        //获取商品可用库存数量
        List<Map<String, Object>> kcList = new ArrayList<>();
        int size = pluList.size();
        if (size > 0) {
            if (DEFAULT_BATCH_QUERY_SIZE < size) {
                int part = size / DEFAULT_BATCH_QUERY_SIZE; // 分批数
                for (int i = 0; i < part; i++) {
                    List<Map<String, Object>> partPluList = new LinkedList<Map<String, Object>>();
                    partPluList.addAll(pluList.subList(0, DEFAULT_BATCH_QUERY_SIZE));
                    List<Map<String,Object>> kcPartList = this.ordAdlYhDao.getYhKcSum(partPluList, orgCode);
                    kcList.addAll(kcPartList);
                    pluList.subList(0, DEFAULT_BATCH_QUERY_SIZE).clear();
                }
                if (!pluList.isEmpty()) {
                    List<Map<String,Object>> kcPartList = this.ordAdlYhDao.getYhKcSum(pluList, orgCode);
                    kcList.addAll(kcPartList);
                }
            }else {
                kcList = this.ordAdlYhDao.getYhKcSum(pluList, orgCode);
            }
        }
        Map<Long, Object> kcMap = new HashMap<Long, Object>();
        for (Map<String, Object> map : kcList) {
            long pluId = Long.parseLong(null != map.get("PLUID") ? map.get("PLUID").toString() : "0") ;
            kcMap.put(pluId, null != map.get("KCCOUNT") ? map.get("KCCOUNT").toString() : "0");
        }
        //获取包装数据
        List<SkuPluPacketModel> packets = this.ordAdlYhDao.getPackets(orgCode, depId);
        Map<Long, SkuPluPacketModel> packetMap = new HashMap<Long, SkuPluPacketModel>();
        if (null != packets && !packets.isEmpty()) {
            for (SkuPluPacketModel packetModel : packets) {
                packetMap.put(packetModel.getPluId(), packetModel);
            }
        }
        //整合商品
        for (SkuPluModel pluModel : deptSkus) {
            String key = pluModel.getPluCode();
            JSONObject backJson = new JSONObject();
            backJson.put("pluId", pluModel.getPluId());
            backJson.put("pluCode", pluModel.getPluCode());
            backJson.put("pluName", pluModel.getPluName());
            backJson.put("barCode", pluModel.getBarCode());
            backJson.put("unit", pluModel.getUnit());
            backJson.put("spec", pluModel.getSpec());
            backJson.put("price", pluModel.getPrice());
            SkuYhPSBodyModel psModel = psMap.get(key);
            if (null != psModel) {
                backJson.put("minCount", psModel.getMinCount());
                backJson.put("times", psModel.getTimes());
            }else {
                backJson.put("minCount", 0);
                backJson.put("times", 1);
            }
            if (null != kcMap.get(pluModel.getPluId())) {
                backJson.put("kcCount", Double.valueOf(kcMap.get(pluModel.getPluId()).toString()));
            }else {
                backJson.put("kcCount", 0);
            }
            SkuPluPacketModel packetModel = packetMap.get(pluModel.getPluId());
            if (null != packetModel) {
                backJson.put("packUnit", packetModel.getPackUnit());
                backJson.put("packQty", packetModel.getPackQty());
            }else {
                backJson.put("packUnit", "");
                backJson.put("packQty", 0);
            }
            backArray.add(backJson);
        }
        long endTime = System.currentTimeMillis();
        LoggerEnhance.info(LOGGER, "查询紧急要货商品消耗时间:{}", (endTime - startTime));
        return backArray;
    }
    
    /**
     * 获取组织下的模板
     * @param orgCode
     * @return OrdYhTempletHeadModel
     */
    public List<OrdYhTempletHeadModel> getTempletsByOrgCode(String orgCode) {
        return this.ordAdlYhDao.getTempletsByOrgCode(orgCode);
    }
    
    /**
     * 获取要货周期的商品
     * @param orgCode
     * @return List<OrdYhCycleModel>
     */
    public List<OrdYhCycleModel> getYhCycleSkus(String orgCode) {
        return this.ordAdlYhDao.getYhCycleSkus(orgCode);
    }
    
    /**
     * 获取明日到货数量
     * @param orgCode
     * @param depId
     * @param modelCode
     * @return List<OrdAdlYhPluCursorModel>
     */
    @SuppressWarnings("unchecked")
    @Transactional
    public List<OrdAdlYhPluCursorModel> getMrCounts(String orgCode, Long depId, String modelCode) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("ps_OrgCode", orgCode);
        map.put("ps_ModelCode", modelCode);
        map.put("ps_DepId", depId.toString());
        map.put("ps_Type", YhType.ORDINARY_YH.getValue());
        this.ordAdlYhDao.execMrCountsProcedure(map);
        LoggerEnhance.info(LOGGER, "查询明日数量结果为--------------------：{}", null != map.get("ps_Message") ? map.get("ps_Message").toString() : "");
        int resultCode = null != map.get("pi_Result") ? Integer.valueOf(map.get("pi_Result").toString()) : -1;
        if (ProcedureResult.EXEC_ERROR_RECORD.getValue() == resultCode 
                || ProcedureResult.EXEC_ERROR_EXIT.getValue() == resultCode) {
            throw new ServiceException(GoodsResponseCode.EXEC_PROCEDURE_ERROR.getCode(), 
                    null != map.get("ps_Message") ? map.get("ps_Message").toString() : GoodsResponseCode.EXEC_PROCEDURE_ERROR.getMsg());
        }
        List<OrdAdlYhPluCursorModel> models = new ArrayList<OrdAdlYhPluCursorModel>();
        models.clear();
        models.addAll((List<OrdAdlYhPluCursorModel>) map.get("pc_Data"));
        return models;
    }
    
    /**
     * 获取组织地址
     * @param etpCode
     * @return EtpAddressModel
     */
    public EtpAddressModel getAddress(String etpCode) {
        return this.ordAdlYhDao.getAddress(etpCode);
    }
    
    /**
     * 调用存储过程进行单据记账
     * @param map
     * @return Map<String, Object>
     */
    @Transactional
    public Map<String, Object> execYhAccountProcedure(Map<String, Object> map) {
        long startTime = System.currentTimeMillis();
        this.ordAdlYhDao.execYhAccountProcedure(map);
        long endTime = System.currentTimeMillis();
        LoggerEnhance.info(LOGGER, "调用要货单据记账存储过程-【CALL sOrd_CDADL_YhBillAcc()】消耗时间:{}", (endTime - startTime));
        if (null == map) {
            throw new ServiceException(GoodsResponseCode.EXEC_PROCEDURE_ERROR.getCode(), 
                    GoodsResponseCode.EXEC_PROCEDURE_ERROR.getMsg());
        }
        LoggerEnhance.info(LOGGER, "要货单据记账结果为--------------------：{}", null != map.get("ps_Message") ? map.get("ps_Message").toString() : "");
        int resultCode = null != map.get("pi_Result") ? Integer.valueOf(map.get("pi_Result").toString()) : -1;
        if (ProcedureResult.EXEC_ERROR_RECORD.getValue() == resultCode 
                || ProcedureResult.EXEC_ERROR_EXIT.getValue() == resultCode) {
            throw new ServiceException(GoodsResponseCode.EXEC_PROCEDURE_ERROR.getCode(), 
                    null != map.get("ps_Message") ? map.get("ps_Message").toString() : GoodsResponseCode.EXEC_PROCEDURE_ERROR.getMsg());
        }
        return map;
    }
    
    public void test(Map<String, Object> map) {
        this.ordAdlYhDao.execMrCountsProcedure(map);
    }
    
    /**
     * 判断是否删除要货周期的商品
     * @param cycleRes
     * @return
     */
    private boolean isRemoveCycle(OrdYhCycleModel cycleModel) {
        boolean flag = false;
        if (null == cycleModel) {
            return flag;
        }
        int day = DateUtil.getDayForWeek(DateUtil.getCurrentDate());
        String cycleRes = "1";//默认不删除
        if (day == DigitalConstant.ONE) {
            cycleRes = cycleModel.getMonday();
        }
        if (day == DigitalConstant.TWO) {
            cycleRes = cycleModel.getTuesday();
        }
        if (day == DigitalConstant.THREE) {
            cycleRes = cycleModel.getWednesday();
        }
        if (day == DigitalConstant.FOUR) {
            cycleRes = cycleModel.getThursday();
        }
        if (day == DigitalConstant.FIVE) {
            cycleRes = cycleModel.getFriday();
        }
        if (day == DigitalConstant.SIX) {
            cycleRes = cycleModel.getSaturday();
        }
        if (day == DigitalConstant.SEVEN) {
            cycleRes = cycleModel.getSunday();
        }
        if (isUse.UN_USE.getValue().equals(cycleRes)) {
            flag = true;
        }
        return flag;
    }
    
    /**
     * 数字常量
     * @author lw
     */
    public interface DigitalConstant {
        /**
         * 1
         */
        int ONE = 1;
        
        /**
         * 2
         */
        int TWO = 2;
        
        /**
         * 3
         */
        int THREE = 3;
        
        /**
         * 4
         */
        int FOUR = 4;

        /**
         * 5
         */
        int FIVE = 5;

        /**
         * 6
         */
        int SIX = 6;

        /**
         * 7
         */
        int SEVEN = 7;
    }
}
