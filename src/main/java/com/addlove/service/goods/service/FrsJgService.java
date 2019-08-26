package com.addlove.service.goods.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.addlove.service.goods.dao.FrsJgDao;
import com.addlove.service.goods.model.FrsGyModel;
import com.addlove.service.goods.model.FrsJgCpModel;
import com.addlove.service.goods.model.FrsJgHeadModel;
import com.addlove.service.goods.model.FrsJgPageModel;
import com.addlove.service.goods.model.FrsJgYlModel;
import com.addlove.service.goods.model.SkuPluExtendModel;
import com.github.pagehelper.PageHelper;

/**
 * 加工单逻辑层
 * @author lw
 *
 */
@Service
public class FrsJgService {
    @Autowired
    private FrsJgDao frsJgDao;
    
    
    public List<FrsJgHeadModel> queryJgPage(FrsJgPageModel queryModel) {
        PageHelper.startPage(queryModel.getPageNo(), queryModel.getPageSize(), true);
        List<FrsJgHeadModel> jgList = this.frsJgDao.queryJgPage(queryModel);
        if (null != jgList && !jgList.isEmpty()) {
            for (FrsJgHeadModel model : jgList) {
                if (StringUtils.isNotBlank(model.getLrDate()) && model.getLrDate().length() > 19) {
                    model.setLrDate(model.getLrDate().substring(0, 19));
                }
                if (StringUtils.isNotBlank(model.getJzDate()) && model.getJzDate().length() > 19) {
                    model.setJzDate(model.getJzDate().substring(0, 19));
                }
            }
        }
        return jgList;
    }
    
    /**
     * 删除加工数据
     * @param billNo
     */
    @Transactional
    public void delJg(String billNo) {
        this.frsJgDao.delJgHead(billNo);
        this.frsJgDao.delJgYls(billNo);
        this.frsJgDao.delJgCps(billNo);
    }
    
    /**
     * 查询加工单详情
     * @param billNo
     * @return FrsJgHeadModel
     */
    public FrsJgHeadModel queryJgDetails(String billNo) {
        FrsJgHeadModel headModel = this.frsJgDao.getJgHead(billNo);
        List<FrsJgYlModel> ylList = this.frsJgDao.getJgYls(billNo);
        List<FrsJgCpModel> cpList = this.frsJgDao.getJgCps(billNo);
        headModel.setYlList(ylList);
        headModel.setCpList(cpList);
        return headModel;
    }
    
    /**
     * 获取加工商品
     * @param orgCode
     * @param depId
     * @param ckCode
     * @return Set<SkuPluExtendModel>
     */
    public Set<SkuPluExtendModel> getJgSkuList(String orgCode, Long depId, String ckCode) {
        //获取部门商品
        List<SkuPluExtendModel> multiSkus = this.frsJgDao.getMultiSkus(orgCode, depId, ckCode);
        List<SkuPluExtendModel> lyAndBsSkus = this.frsJgDao.getLyAndBsSkus(orgCode, depId, ckCode);
        //去重商品
        Set<SkuPluExtendModel> skuSet = new HashSet<>();
        if (null != multiSkus && !multiSkus.isEmpty()) {
            multiSkus.addAll(lyAndBsSkus);
            skuSet.addAll(multiSkus);
            return skuSet;
        }else {
            skuSet.addAll(lyAndBsSkus);
            return skuSet;
        }
    }
    
    /**
     * 获取加工工艺数据
     * @param orgCode
     * @param depCode
     * @return List<FrsGyModel>
     */
    public List<FrsGyModel> getJgGys(String orgCode, String depCode) {
        return this.frsJgDao.getJgGys(orgCode, depCode);
    }
}
