package com.addlove.service.goods.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.addlove.service.goods.constants.GoodsResponseCode;
import com.addlove.service.goods.constants.GoodsAdlYhConstants.isUse;
import com.addlove.service.goods.constants.GoodsAdlYhConstants.yhType;
import com.addlove.service.goods.constants.GoodsCommonConstants.ProcedureResult;
import com.addlove.service.goods.dao.OrdAdlYhDao;
import com.addlove.service.goods.dao.SkuPdCSDao;
import com.addlove.service.goods.exception.ServiceException;
import com.addlove.service.goods.model.OrdAdlYhBodyModel;
import com.addlove.service.goods.model.OrdAdlYhHeadModel;
import com.addlove.service.goods.model.OrdAdlYhPageModel;
import com.addlove.service.goods.model.OrdAdlYhPluCursorModel;
import com.addlove.service.goods.model.OrdYhCycleModel;
import com.addlove.service.goods.model.OrdYhTempletBodyModel;
import com.addlove.service.goods.model.OrdYhTempletHeadModel;
import com.addlove.service.goods.model.SkuPluModel;
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
    
    @Autowired
    private OrdAdlYhDao ordAdlYhDao;
    
    @Autowired
    private SkuPdCSDao skuPdCSDao;
    
    @Autowired
    private GoodsCommonService commonService;
    
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
        List<OrdAdlYhBodyModel> bodyList = this.ordAdlYhDao.getYhBodys(billNo);
        yhHead.setBodyList(bodyList);
        return yhHead;
    }
    
    /**
     * 获取要货商品
     * @param orgCode
     * @param depId
     * @param modelCode
     * @return List<SkuPluExtendModel>
     */
    public JSONArray getYhSkuList(String orgCode, Long depId, String modelCode) {
        JSONArray backArray = new JSONArray();
        //获取部门商品
        List<SkuPluModel> deptSkus = this.skuPdCSDao.getPdSkuListByDept(orgCode, depId);
        //获取模板商品
        List<OrdYhTempletBodyModel> templetSkus = this.ordAdlYhDao.getTempletSkus(orgCode, modelCode);
        if (null == deptSkus || deptSkus.isEmpty() ) {
            return backArray;
        }
        if (null == templetSkus || templetSkus.isEmpty() ) {
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
        //获取要货参数商品（包括：最小、最大要货量及倍数）
        List<SkuYhPSBodyModel> pSSkus = this.ordAdlYhDao.getYhPSSkus(orgCode);
        Map<String, SkuYhPSBodyModel> psMap = new HashMap<String, SkuYhPSBodyModel>();
        if (null != pSSkus && !pSSkus.isEmpty()) {
            for (SkuYhPSBodyModel psModel : pSSkus) {
                psMap.put(psModel.getPluCode(), psModel);
            }
        }
        //获取商品可用库存数量
        List<Map<String, Object>> kcList = this.commonService.getKcSum(pluList);
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
            backJson.put("minCount", psModel.getMinCount());
            backJson.put("maxCount", psModel.getMaxCount());
            backJson.put("times", psModel.getTimes());
            backJson.put("kcCount", Double.valueOf(kcMap.get(pluModel.getPluId()).toString()));
            OrdAdlYhPluCursorModel cursorModel = mrMap.get(key);
            backJson.put("mRJhCount", cursorModel.getmRJhCount());
            backArray.add(backJson);
        }
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
     * 获取模板商品
     * @param orgCode
     * @param modelCode
     * @return List<OrdYhTempletBodyModel>
     */
    List<OrdYhTempletBodyModel> getTempletSkus(String orgCode, String modelCode) {
        return this.ordAdlYhDao.getTempletSkus(orgCode, modelCode);
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
    public List<OrdAdlYhPluCursorModel> getMrCounts(String orgCode, Long depId, String modelCode) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("ps_OrgCode", orgCode);
        map.put("ps_ModelCode", modelCode);
        map.put("ps_DepId", depId.toString());
        map.put("ps_Type", yhType.ORDINARY_YH.getValue());
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
