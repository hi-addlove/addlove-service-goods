package com.addlove.service.goods.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.addlove.service.goods.model.CouMdPdBodyModel;
import com.addlove.service.goods.model.CouMdPdHeadModel;
import com.addlove.service.goods.model.MdPdAccountModel;
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
     * 插入盘点主表
     * @param headModel
     */
    void insertPdHead(CouMdPdHeadModel headModel);
    
    /**
     * 批量插入盘点明细表
     * @param bodyList
     */
    void insertPdBody(@Param("bodys") List<CouMdPdBodyModel> bodyList);
    
    /**
     * 查询盘点详情
     * @param billNo
     * @return List<Map<String, Object>>
     */
    List<Map<String, Object>> queryMdPdDetails(String billNo);
    
    /**
     * 删除盘点主表
     * @param billNo
     */
    void deleteMdPdHead(String billNo);
    
    /**
     * 删除盘点明细表
     * @param billNo
     */
    void deleteMdPdBody(String billNo);
    
    /**
     * 获取盘点账面商品数据
     * @param billNo
     * @param orgCode
     * @return List<MdPdAccountModel>
     */
    List<MdPdAccountModel> getMdPdAccountPlus(@Param("billNo")String billNo, @Param("orgCode")String orgCode);
    
    /**
     * 通过单据号获取盘点主表信息
     * @param billNo
     * @return CouMdPdHeadModel
     */
    CouMdPdHeadModel getPdHeadByBillNo(String billNo);
    
    /**
     * 查询漏盘商品处理方式
     * @param orgCode
     * @return Map<String, Object>
     */
    Map<String, Object> getPdType(String orgCode);
    
    /**
     * 执行启动盘点存储过程
     * @param map
     */
    void execStartPdProcedure(Map<String, Object> map);
    
    /**
     * 执行盘点记账存储过程
     * @param map
     */
    void execPdAccountProcedure(Map<String, Object> map);
}
