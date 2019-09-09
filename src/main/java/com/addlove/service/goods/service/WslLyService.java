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

import com.addlove.service.goods.dao.GoodsCommonDao;
import com.addlove.service.goods.dao.SkuPdCSDao;
import com.addlove.service.goods.dao.WslLyDao;
import com.addlove.service.goods.model.BasFlContentModel;
import com.addlove.service.goods.model.SkuPluModel;
import com.addlove.service.goods.model.WslLyBodyModel;
import com.addlove.service.goods.model.WslLyHeadModel;
import com.addlove.service.goods.model.WslLyPageModel;
import com.addlove.service.goods.util.LoggerEnhance;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;

/**
 * 领用单逻辑层
 * @author lw
 *
 */
@Service
public class WslLyService {
    /**WslLyService类日志 */
    private static final Logger LOGGER = LoggerFactory.getLogger(WslLyService.class);
    /**
     * 默认单次批量查询plu数量
     */
    public static final int DEFAULT_BATCH_QUERY_SIZE = 500;
    
    @Autowired
    private WslLyDao wslLyDao;
    
    @Autowired
    private GoodsCommonDao commonDao;
    
    @Autowired
    private SkuPdCSDao skuPdCSDao;
    
    /**
     * 查询领用单列表
     * @param queryModel
     * @return List<WslLyHeadModel>
     */
    public List<WslLyHeadModel> queryLyPage(WslLyPageModel queryModel) {
        PageHelper.startPage(queryModel.getPageNo(), queryModel.getPageSize(), true);
        List<WslLyHeadModel> lyList = this.wslLyDao.queryLyPage(queryModel);
        if (null != lyList && !lyList.isEmpty()) {
            for (WslLyHeadModel model : lyList) {
                if (StringUtils.isNotBlank(model.getLrDate()) && model.getLrDate().length() > 19) {
                    model.setLrDate(model.getLrDate().substring(0, 19));
                }
                if (StringUtils.isNotBlank(model.getJzDate()) && model.getJzDate().length() > 19) {
                    model.setJzDate(model.getJzDate().substring(0, 19));
                }
            }
        }
        return lyList;
    }
    
    /**
     * 新增领用单
     * @param headModel
     */
    @Transactional
    public void addLy(WslLyHeadModel headModel) {
        this.wslLyDao.insertLyHead(headModel);
        this.wslLyDao.insertLyBodys(headModel.getBodyList());
    }
    
    /**
     * 编辑领用单
     * @param headModel
     */
    @Transactional
    public void editLy(WslLyHeadModel headModel) {
        this.wslLyDao.delLyHead(headModel.getBillNo());
        this.wslLyDao.delLyBodys(headModel.getBillNo());
        this.wslLyDao.insertLyHead(headModel);
        this.wslLyDao.insertLyBodys(headModel.getBodyList());
    }
    
    /**
     * 删除领用数据
     * @param billNo
     */
    @Transactional
    public void delLy(String billNo) {
        this.wslLyDao.delLyHead(billNo);
        this.wslLyDao.delLyBodys(billNo);
    }
    
    /**
     * 查询领用单详情
     * @param billNo
     * @return WslLyHeadModel
     */
    public WslLyHeadModel queryLyDetails(String billNo) {
        WslLyHeadModel headModel = this.wslLyDao.getLyHead(billNo);
        List<WslLyBodyModel> bodyList = this.wslLyDao.getLyBodys(billNo);
        headModel.setBodyList(bodyList);
        if (StringUtils.isNotBlank(headModel.getLrDate()) && headModel.getLrDate().length() > 19) {
            headModel.setLrDate(headModel.getLrDate().substring(0, 19));
        }
        if (StringUtils.isNotBlank(headModel.getJzDate()) && headModel.getJzDate().length() > 19) {
            headModel.setJzDate(headModel.getJzDate().substring(0, 19));
        }
        return headModel;
    }
    
    /**
     * 获取分类内容
     * @param flCode
     * @return List<BasFlContentModel>
     */
    public List<BasFlContentModel> getFls(String flCode) {
        return this.wslLyDao.getFls(flCode);
    }
    
    /**
     * 获取领用单商品
     * @param orgCode
     * @param depId
     * @return JSONArray
     */
    public JSONArray getLySkuList(String orgCode, Long depId) {
        long startTime = System.currentTimeMillis();
        JSONArray backArray = new JSONArray();
        //获取部门商品
        List<SkuPluModel> deptSkus = new ArrayList<SkuPluModel>();
        deptSkus = this.skuPdCSDao.getPdSkuListByDept(orgCode, depId);
        List<SkuPluModel> otherDeptSkus = this.skuPdCSDao.getOtherDeptSkus(orgCode, depId);
        if (null != otherDeptSkus && !otherDeptSkus.isEmpty()) {
            deptSkus.addAll(otherDeptSkus);
        }
        //获取领用单可用商品
        List<SkuPluModel> lySkus = this.wslLyDao.getLySkus(orgCode, depId);
        deptSkus.addAll(lySkus);
        //获取商品可用库存数量
        Iterator<SkuPluModel> iterator = deptSkus.iterator();
        List<Map<String, Object>> pluList = new LinkedList<Map<String, Object>>();
        while (iterator.hasNext()) {
            SkuPluModel pluModel = iterator.next();
            Map<String, Object> queryMap = new HashMap<String, Object>();
            queryMap.put("pluId", pluModel.getPluId());
            pluList.add(queryMap);
        }
        List<Map<String, Object>> kcList = new ArrayList<>();
        int size = pluList.size();
        if (size > 0) {
            if (DEFAULT_BATCH_QUERY_SIZE < size) {
                int part = size / DEFAULT_BATCH_QUERY_SIZE; // 分批数
                for (int i = 0; i < part; i++) {
                    List<Map<String, Object>> partPluList = new LinkedList<Map<String, Object>>();
                    partPluList.addAll(pluList.subList(0, DEFAULT_BATCH_QUERY_SIZE));
                    List<Map<String,Object>> kcPartList = this.commonDao.getKcSum(partPluList, orgCode);
                    kcList.addAll(kcPartList);
                    pluList.subList(0, DEFAULT_BATCH_QUERY_SIZE).clear();
                }
                if (!pluList.isEmpty()) {
                    List<Map<String,Object>> kcPartList = this.commonDao.getKcSum(pluList, orgCode);
                    kcList.addAll(kcPartList);
                }
            }else {
                kcList = this.commonDao.getKcSum(pluList, orgCode);
            }
        }
        Map<Long, Object> kcMap = new HashMap<Long, Object>();
        for (Map<String, Object> map : kcList) {
            long pluId = Long.parseLong(null != map.get("PLUID") ? map.get("PLUID").toString() : "0") ;
            kcMap.put(pluId, null != map.get("KCCOUNT") ? map.get("KCCOUNT").toString() : "0");
        }
        //整合商品
        for (SkuPluModel pluModel : deptSkus) {
            JSONObject backJson = new JSONObject();
            backJson.put("pluId", pluModel.getPluId());
            backJson.put("pluCode", pluModel.getPluCode());
            backJson.put("pluName", pluModel.getPluName());
            backJson.put("barCode", pluModel.getBarCode());
            backJson.put("unit", pluModel.getUnit());
            backJson.put("spec", pluModel.getSpec());
            backJson.put("price", pluModel.getPrice());
            if (null != kcMap.get(pluModel.getPluId())) {
                backJson.put("kcCount", Double.valueOf(kcMap.get(pluModel.getPluId()).toString()));
            }else {
                backJson.put("kcCount", 0);
            }
            backArray.add(backJson);
        }
        long endTime = System.currentTimeMillis();
        LoggerEnhance.info(LOGGER, "查询领用单商品消耗时间:{}", (endTime - startTime));
        return backArray;
    }
}