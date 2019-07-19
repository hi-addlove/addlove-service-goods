package com.addlove.service.goods.model;

/**
 * 分页查询的对象封装
 * @author lw
 *
 */
public class PageQuery {

    private Integer pageNo;

    private Integer pageSize;

    private String orderBy;

    private String orderType;

    private Long total;

    private Object result;

    /**
     * Creates a new instance of PageQuery.
     *
     * @param pageNo
     *            页码
     * @param pageSize
     *            每页数量
     */
    public PageQuery(Integer pageNo, Integer pageSize) {
        this.pageNo = pageNo;
        this.pageSize = pageSize;
    }

    /**
     * Creates a new instance of PageQuery.
     *
     * @param pageNo
     *            页码
     * @param pageSize
     *            每页数量
     * @param data
     *            分页数据
     * @param total
     *            总数
     */
    public PageQuery(Integer pageNo, Integer pageSize, Object data, Long total) {
        this.pageNo = pageNo;
        this.pageSize = pageSize;
        this.result = data;
        this.total = total;
    }

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
     * 返回 orderBy
     * @return orderBy
     */
    public String getOrderBy() {
        return orderBy;
    }

    /**
     * 设置  orderBy
     * @param orderBy value
     */
    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    /**
     * 返回 orderType
     * @return orderType
     */
    public String getOrderType() {
        return orderType;
    }

    /**
     * 设置  orderType
     * @param orderType value
     */
    public void setOrderType(String orderType) {
        this.orderType = orderType;
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
