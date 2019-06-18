package com.addlove.service.goods.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.addlove.service.goods.dao.GoodsCommonDao;
import com.addlove.service.goods.model.CntContractModel;
import com.addlove.service.goods.model.EtpSupplierModel;
import com.addlove.service.goods.model.SkuPluModel;

/**
 * 公共service层：如供应商、合同、商品等
 * @author lw
 *
 */
@Service
public class GoodsCommonService {
    @Autowired
    private GoodsCommonDao commonDao;
    
    /**
     * 获取供应商
     * @param orgCode
     * @return List<EtpSupplierModel>
     */
    public List<EtpSupplierModel> getSupplierList(String orgCode) {
        return this.commonDao.getSupplierList(orgCode);
    }
    
    /**
     * 通过组织、供应商获取合同
     * @param orgCode
     * @param etpCode
     * @return List<CntContractModel>
     */
    public List<CntContractModel> getCntList(String orgCode, String etpCode){
        return this.commonDao.getCntList(orgCode, etpCode);
    }
    
    /**
     * 通过组织、合同获取商品
     * @param orgCode
     * @param cntId
     * @return List<SkuPluModel>
     */
    public List<SkuPluModel> getSkuList(String orgCode, Long cntId) {
        return this.getSkuList(orgCode, cntId);
    }
}
