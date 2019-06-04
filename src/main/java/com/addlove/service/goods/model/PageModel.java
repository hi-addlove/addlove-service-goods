package com.addlove.service.goods.model;

/**
 * 分页数据返回的DATA封装类
 *
 * @author haow 2018-03-18 下午5:19:45
 */
public class PageModel {
    private Integer pageNo;

    private Integer pageSize;

    private Long total;

    private Object result;

    /**
     * 返回 pageNo
     * @return pageNo
     */
    public Integer getPageNo() {
        return pageNo;
    }

    /**
     * 设置  pageNo
     * @param pageNo value
     */
    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    /**
     * 返回 pageSize
     * @return pageSize
     */
    public Integer getPageSize() {
        return pageSize;
    }

    /**
     * 设置  pageSize
     * @param pageSize value
     */
    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    /**
     * 返回 total
     * @return total
     */
    public Long getTotal() {
        return total;
    }

    /**
     * 设置  total
     * @param total value
     */
    public void setTotal(Long total) {
        this.total = total;
    }

    /**
     * 返回 result
     * @return result
     */
    public Object getResult() {
        return result;
    }

    /**
     * 设置  result
     * @param result value
     */
    public void setResult(Object result) {
        this.result = result;
    }
}
