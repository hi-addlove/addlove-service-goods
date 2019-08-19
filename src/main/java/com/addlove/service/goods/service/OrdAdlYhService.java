package com.addlove.service.goods.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.addlove.service.goods.dao.OrdAdlYhDao;
import com.addlove.service.goods.dao.SkuPdCSDao;
import com.addlove.service.goods.model.OrdYhCycleModel;
import com.addlove.service.goods.model.OrdYhTempletBodyModel;
import com.addlove.service.goods.model.OrdYhTempletHeadModel;
import com.addlove.service.goods.model.SkuPluExtendModel;
import com.addlove.service.goods.model.SkuPluModel;
import com.addlove.service.goods.model.SkuYhPSBodyModel;

/**
 * ADL要货逻辑层
 * @author lw
 *
 */
@Service
public class OrdAdlYhService {
    @Autowired
    private OrdAdlYhDao ordAdlYhDao;
    
    @Autowired
    private SkuPdCSDao skuPdCSDao;
    
    @Autowired
    private GoodsCommonService commonService;
    
    /**
     * 获取要货商品
     * @param orgCode
     * @param depId
     * @param modelCode
     * @return List<SkuPluExtendModel>
     */
    public List<SkuPluExtendModel> getYhSkuList(String orgCode, Long depId, String modelCode) {
        //获取部门商品
        List<SkuPluModel> deptSkus = this.skuPdCSDao.getPdSkuListByDept(orgCode, depId);
        //获取模板商品
        List<OrdYhTempletBodyModel> templetSkus = this.ordAdlYhDao.getTempletSkus(orgCode, modelCode);
        //获取要货周期的商品
        List<OrdYhCycleModel> cycleSkus = this.ordAdlYhDao.getYhCycleSkus(orgCode);
        //获取要货参数商品（包括：最小、最大要货量及倍数）
        List<SkuYhPSBodyModel> pSSkus = this.ordAdlYhDao.getYhPSSkus(orgCode);
        //获取商品可用库存数量
        List<SkuPluExtendModel> canUseSkuCounts = this.commonService.getCanUseSkuCounts(orgCode);
        return null;
    }
    
    /**
     * 获取组织下的模板
     * @param orgCode
     * @return OrdYhTempletHeadModel
     */
    public OrdYhTempletHeadModel getTempletsByOrgCode(String orgCode) {
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
}
