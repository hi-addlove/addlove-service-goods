package com.addlove.service.goods.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import com.addlove.service.goods.model.SkuPluModel;

/**
 * 商品盘点周期方案dao层
 * @author lw
 *
 */
@Repository
public interface SkuPdCSDao {
    /**
     * 通过组织编码和部门ID获取可用商品
     * @param orgCode
     * @param depId
     * @return List<SkuPluModel>
     */
    List<SkuPluModel> getPdSkuListByDept(@Param("orgCode")String orgCode, @Param("depId")Long depId);
    
    /**
     * 获取多部门可用商品
     * @param orgCode
     * @param depId
     * @return List<SkuPluModel>
     */
    List<SkuPluModel> getOtherDeptSkus(@Param("orgCode")String orgCode, @Param("depId")Long depId);
    
    /**
     * 通过组织编码和盘点类型获取盘点商品
     * @param orgCode
     * @param cycleType：0-日盘；1-周盘；2-月盘
     * @return List<SkuPluModel>
     */
    List<SkuPluModel> getPdSkuListByCycleType(@Param("orgCode")String orgCode, @Param("cycleType")String cycleType);
}
