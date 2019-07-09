package com.addlove.service.goods.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

/**
 * 报表dao层
 * @author lw
 *
 */
@Repository
public interface ReportDao {
    /**
     * 库存查询报表
     * @param queryMap
     * @return List<Map<String, Object>>
     */
    List<Map<String, Object>> queryStockReport(Map<String, Object> queryMap);
}
