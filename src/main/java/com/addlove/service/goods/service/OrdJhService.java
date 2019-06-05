package com.addlove.service.goods.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.addlove.service.goods.dao.OrdJhDao;
import com.addlove.service.goods.model.OrdJhBodyModel;
import com.addlove.service.goods.model.OrdJhHeadModel;
import com.addlove.service.goods.model.OrdJhQueryPageModel;
import com.github.pagehelper.PageHelper;

/**
 * 配送验收逻辑层
 * @author lw
 *
 */
@Service
public class OrdJhService {
    @Autowired
    private OrdJhDao ordJhDao;
    
    /**
     * 分页查询配送验收数据
     * @param queryModel
     * @return List<OrdJhHeadModel>
     */
    public List<OrdJhHeadModel> queryOrdJhHeadModelByPage(OrdJhQueryPageModel queryModel) {
        PageHelper.startPage(queryModel.getPageNo(), queryModel.getPageSize(), true);
        List<OrdJhHeadModel> ordJhHeadList = this.ordJhDao.queryOrdJhHeadModelByPage(queryModel);
        return ordJhHeadList;
    }
    
    /**
     * 根据配送验收单号查询商品信息
     * @param billNo
     * @return List<OrdJhBodyModel>
     */
    public List<OrdJhBodyModel> querySkusByBillNo(String billNo) {
        return this.ordJhDao.querySkusByBillNo(billNo);
    }
}
