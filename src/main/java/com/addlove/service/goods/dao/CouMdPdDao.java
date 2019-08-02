package com.addlove.service.goods.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.addlove.service.goods.model.CouMdPdHeadModel;
import com.addlove.service.goods.model.MdPdPageModel;

/**
 * 盘点dao层
 * @author lw
 *
 */
@Repository
public interface CouMdPdDao {
    /**
     * 查询盘点列表
     * @param queryModel
     * @return List<CouMdPdHeadModel>
     */
    List<CouMdPdHeadModel> queryMdPdPage(MdPdPageModel queryModel);
    
    /**
     * 查询盘点详情
     * @param billNo
     * @return List<Map<String, Object>>
     */
    List<Map<String, Object>> queryMdPdDetails(String billNo);
}
