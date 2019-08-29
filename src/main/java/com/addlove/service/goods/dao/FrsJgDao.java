package com.addlove.service.goods.dao;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import com.addlove.service.goods.model.FrsGyModel;
import com.addlove.service.goods.model.FrsJgCpModel;
import com.addlove.service.goods.model.FrsJgHeadModel;
import com.addlove.service.goods.model.FrsJgPageModel;
import com.addlove.service.goods.model.FrsJgYlModel;
import com.addlove.service.goods.model.SkuPluExtendModel;

/**
 * 加工单dao层
 * @author lw
 *
 */
@Repository
public interface FrsJgDao {
    /**
     * 查询加工单列表
     * @param queryModel
     * @return List<FrsJgHeadModel>
     */
    List<FrsJgHeadModel> queryJgPage(@Param("queryModel") FrsJgPageModel queryModel);
    
    /**
     * 插入加工单主表
     * @param headModel
     */
    void insertJgHead(FrsJgHeadModel headModel);
    
    /**
     * 插入原料数据
     * @param ylList
     */
    void insertJgYls(@Param("bodys") List<FrsJgYlModel> ylList);
    
    /**
     * 插入成品数据
     * @param cpModels
     */
    void insertJgCps(@Param("bodys") List<FrsJgCpModel> cpList);
    
    /**
     * 通过单号获取加工单主表
     * @param billNo
     * @return FrsJgHeadModel
     */
    FrsJgHeadModel getJgHead(String billNo);
    
    /**
     * 通过单号获取加工单原料数据
     * @param billNo
     * @return List<FrsJgYlModel>
     */
    List<FrsJgYlModel> getJgYls(String billNo);
    
    /**
     * 通过单号获取加工单成品数据
     * @param billNo
     * @return List<FrsJgCpModel>
     */
    List<FrsJgCpModel> getJgCps(String billNo);
    
    /**
     * 删除加工主表
     * @param billNo
     */
    void delJgHead(String billNo);
    
    /**
     * 删除加工原料
     * @param billNo
     */
    void delJgYls(String billNo);
    
    /**
     * 删除加工成品
     * @param billNo
     */
    void delJgCps(String billNo);
    
    /**
     * 获取多经营部门商品数据
     * @param orgCode
     * @param shOrgCode
     * @param depId
     * @param ckCode
     * @return List<SkuPluExtendModel>
     */
    List<SkuPluExtendModel> getMultiSkus(@Param("orgCode")String orgCode, @Param("depId")Long depId, 
            @Param("ckCode")String ckCode);
    
    /**
     * 获取商品领用及报损商品
     * @param orgCode
     * @param shOrgCode
     * @param depId
     * @param ckCode
     * @return List<SkuPluExtendModel>
     */
    List<SkuPluExtendModel> getLyAndBsSkus(@Param("orgCode")String orgCode, @Param("depId")Long depId, 
            @Param("ckCode")String ckCode);
    
    /**
     * 获取加工工艺数据
     * @param orgCode
     * @param depCode
     * @return List<FrsJgYlModel>
     */
    List<FrsGyModel> getJgGys(@Param("orgCode")String orgCode, @Param("depCode")String depCode);
    
    /**
     * 调用存储过程进行加工单据记账
     * @param map
     */
    void execJgAccountProcedure(Map<String, Object> map);
}
