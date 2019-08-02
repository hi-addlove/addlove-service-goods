package com.addlove.service.goods.service;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.addlove.service.goods.dao.CouMdPdDao;
import com.addlove.service.goods.dao.SkuPdCSDao;
import com.addlove.service.goods.model.CouMdPdHeadModel;
import com.addlove.service.goods.model.MdPdPageModel;
import com.addlove.service.goods.model.SkuPluModel;
import com.github.pagehelper.PageHelper;

/**
 * 门店盘点逻辑层
 * @author lw
 *
 */
@Service
public class CouMdPdService {
    @Autowired
    private CouMdPdDao couMdPdDao;
    
    @Autowired
    private SkuPdCSDao skuPdCSDao;
    
    /**
     * 查询盘点列表
     * @param queryModel
     * @return List<CouMdPdHeadModel>
     */
    public List<CouMdPdHeadModel> queryMdPdPage(MdPdPageModel queryModel) {
        PageHelper.startPage(queryModel.getPageNo(), queryModel.getPageSize(), true);
        List<CouMdPdHeadModel> pdList = this.couMdPdDao.queryMdPdPage(queryModel);
        if (null != pdList && !pdList.isEmpty()) {
            for (CouMdPdHeadModel model : pdList) {
                if (StringUtils.isNotBlank(model.getLrDate()) && model.getLrDate().length() > 19) {
                    model.setLrDate(model.getLrDate().substring(0, 19));
                }
                if (StringUtils.isNotBlank(model.getJzDate()) && model.getJzDate().length() > 19) {
                    model.setJzDate(model.getJzDate().substring(0, 19));
                }
            }
        }
        return pdList;
    }
    
    /**
     * 查询盘点详情
     * @param billNo
     * @return List<Map<String, Object>>
     */
    public List<Map<String, Object>> queryMdPdDetails(String billNo) {
        return this.couMdPdDao.queryMdPdDetails(billNo);
    }
    
    /**
     * 获取盘点商品
     * @param orgCode
     * @param depId
     * @param cycleType
     * @return List<SkuPluModel>
     */
    public List<SkuPluModel> getPdSkuList(String orgCode, Long depId, String cycleType) {
        List<SkuPluModel> deptSkus = this.skuPdCSDao.getPdSkuListByDept(orgCode, depId);
        List<SkuPluModel> cycleSkus = this.skuPdCSDao.getPdSkuListByCycleType(orgCode, cycleType);
        deptSkus.addAll(cycleSkus);
        return deptSkus;
    }
}
