package com.addlove.service.goods.service;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import com.addlove.service.goods.dao.ReportDao;

/**
 * 报表业务层
 * @author lw
 *
 */
@Service
public class ReportService {
    @Autowired
    private ReportDao reportDao;
    
    /**
     * 库存查询报表
     * @param queryMap
     * @return List<Map<String, Object>>
     */
    @Cacheable(value = "stockReportCache")
    public List<Map<String, Object>> queryStockReport(Map<String, Object> queryMap) {
        return this.reportDao.queryStockReport(queryMap);
    }
}
