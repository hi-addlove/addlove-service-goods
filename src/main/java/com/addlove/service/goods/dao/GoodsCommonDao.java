package com.addlove.service.goods.dao;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.addlove.service.goods.model.BasBrandModel;
import com.addlove.service.goods.model.CatCategoryModel;
import com.addlove.service.goods.model.CntContractModel;
import com.addlove.service.goods.model.EtpSupplierModel;
import com.addlove.service.goods.model.OrgDeptModel;
import com.addlove.service.goods.model.OrgManageModel;
import com.addlove.service.goods.model.SkuPluExtendModel;
import com.addlove.service.goods.model.SkuPluModel;
import com.addlove.service.goods.model.StkStoreModel;
import com.addlove.service.goods.model.UserEmployeeModel;
import com.addlove.service.goods.model.UsrUserModel;

/**
 * 公共dao层：如供应商、合同、商品等
 * @author lw
 *
 */
@Repository
public interface GoodsCommonDao {
    /**
     * 获取供应商
     * @param orgCode
     * @return List<EtpSupplierModel>
     */
    List<EtpSupplierModel> getSupplierList(String orgCode);
    
    /**
     * 通过组织、供应商获取合同
     * @param orgCode
     * @param etpCode
     * @return List<CntContractModel>
     */
    List<CntContractModel> getCntList(@Param("orgCode")String orgCode, @Param("etpCode")String etpCode);
    
    /**
     * 通过组织、合同获取商品
     * @param orgCode
     * @param cntId
     * @return List<SkuPluModel>
     */
    List<SkuPluModel> getSkuList(@Param("orgCode")String orgCode, @Param("cntId")Long cntId);
    
    /**
     * 获取部门
     * @param orgCode
     * @return List<OrgDeptModel>
     */
    List<OrgDeptModel> getDeptList(String orgCode);
    
    /**
     * 获取组织
     * @param orgCode
     * @return OrgManageModel
     */
    OrgManageModel getOrgModel(String orgCode);
    
    /**
     * 获取所有组织
     * @return
     */
    List<OrgManageModel> getAllOrgModel();
    
    /**
     * 获取仓库
     * @param orgCode
     * @return List<StkStoreModel>
     */
    List<StkStoreModel> getStoreList(String orgCode);
    
    /**
     * 通过组织、部门、仓库查询商品
     * @param orgCode
     * @param depId
     * @param ckCode
     * @return List<SkuPluModel>
     */
    List<SkuPluExtendModel> getSkuListByDept(@Param("orgCode")String orgCode, @Param("shOrgCode")String shOrgCode,
            @Param("depId")Long depId, @Param("ckCode")String ckCode);
    
    /**
     * 通过组织、部门查询生产计划商品
     * @param orgCode
     * @param depId
     * @return List<SkuPluModel>
     */
    List<SkuPluModel> getProPlanSkuListByDept(@Param("orgCode")String orgCode, @Param("depId")Long depId);
    
    /**
     * 搜索用户
     * @param searchContent：用户ID、用户编码、用户名
     * @return List<UsrUserModel>
     */
    List<UsrUserModel> getAllUsers(Map<String, Object> map);
    
    /**
     * 获取采购员
     * @param map
     * @return List<UserEmployeeModel>
     */
    List<UserEmployeeModel> getPurchaseEmp(Map<String, Object> map);
    
    /**
     * 获取商品品类
     * @param map
     * @return List<CatCategoryModel>
     */
    List<CatCategoryModel> getCatCategorys(Map<String, Object> map);
    
    /**
     * 获取商品品牌
     * @param map
     * @return List<BasBrandModel>
     */
    List<BasBrandModel> getBasBrands(Map<String, Object> map);
    
    /**
     * 获取采购退货商品
     * @param orgCode
     * @param cntId
     * @param ckCode
     * @return List<SkuPluExtendModel>
     */
    List<SkuPluExtendModel> getPurchaseReturnSkus(@Param("orgCode")String orgCode, @Param("cntId")Long cntId, 
            @Param("ckCode")String ckCode);
    
    /**
     * 获取门店报损商品
     * @param orgCode
     * @param depId
     * @return List<SkuPluExtendModel>
     */
    List<SkuPluExtendModel> getMdBsSkus(@Param("orgCode")String orgCode, @Param("depId")Long depId);
    
    /**
     * 调用存储过程生成单据号
     * @param map
     */
    void getBillNoByCallProcedure(Map<String, Object> map);
    
    /**
     * 调用存储过程进行单据记账
     * @param map
     */
    void execAccountByCallProcedure(Map<String, Object> map);
    
    /**
     * 获取每个商品的库存总数
     * @param pluSet
     * @return List<Map<String, Object>>
     */
    List<Map<String, Object>> getKcSum(@Param("list") List<Map<String, Object>> pluIds);
}