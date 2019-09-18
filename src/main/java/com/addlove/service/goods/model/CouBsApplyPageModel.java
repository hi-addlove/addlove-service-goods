package com.addlove.service.goods.model;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 门店报损列表请求参数
 * @author lw
 *
 */
public class CouBsApplyPageModel {
    /**默认页数 */
    private int pageNo = 1;
    
    /**每页默认记录数 */
    private int pageSize = 10;
    
    /**组织编码 */
    @NotBlank(message = "组织编码不能为空")
    private String orgCode;
    
    /**单据号 */
    private String billNo;
    
    /**开始日期 */
    private String startDate;
    
    /**结束日期 */
    private String endDate;
    
    /** 数据状态:0－录入；1－审批；2－转审；3－提交；4-可记账；9-关闭*/
    private String dataStatus;
    
    /** 审核状态:0-未申请；1-已申请；2-审核通过；3-审核驳回*/
    private String couStatus;
    
    /** 申请人编码*/
    private String userCode;
    
    /** 部门ID*/
    private Long depId;
    
    /** 仓库编码*/
    private String ckCode;
    
    /** 查询类型：1-报损；2-冲红*/
    @NotNull(message = "查询类型不能为空")
    private Integer queryType;

    /**
     * @return the pageNo
     */
    public int getPageNo() {
        return pageNo;
    }

    /**
     * @param pageNo the pageNo to set
     */
    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    /**
     * @return the pageSize
     */
    public int getPageSize() {
        return pageSize;
    }

    /**
     * @param pageSize the pageSize to set
     */
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    /**
     * @return the orgCode
     */
    public String getOrgCode() {
        return orgCode;
    }

    /**
     * @param orgCode the orgCode to set
     */
    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    /**
     * @return the billNo
     */
    public String getBillNo() {
        return billNo;
    }

    /**
     * @param billNo the billNo to set
     */
    public void setBillNo(String billNo) {
        this.billNo = billNo;
    }

    /**
     * @return the startDate
     */
    public String getStartDate() {
        return startDate;
    }

    /**
     * @param startDate the startDate to set
     */
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    /**
     * @return the endDate
     */
    public String getEndDate() {
        return endDate;
    }

    /**
     * @param endDate the endDate to set
     */
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    /**
     * @return the dataStatus
     */
    public String getDataStatus() {
        return dataStatus;
    }

    /**
     * @param dataStatus the dataStatus to set
     */
    public void setDataStatus(String dataStatus) {
        this.dataStatus = dataStatus;
    }

    /**
     * @return the couStatus
     */
    public String getCouStatus() {
        return couStatus;
    }

    /**
     * @param couStatus the couStatus to set
     */
    public void setCouStatus(String couStatus) {
        this.couStatus = couStatus;
    }

    /**
     * @return the userCode
     */
    public String getUserCode() {
        return userCode;
    }

    /**
     * @param userCode the userCode to set
     */
    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    /**
     * @return the depId
     */
    public Long getDepId() {
        return depId;
    }

    /**
     * @param depId the depId to set
     */
    public void setDepId(Long depId) {
        this.depId = depId;
    }

    /**
     * @return the ckCode
     */
    public String getCkCode() {
        return ckCode;
    }

    /**
     * @param ckCode the ckCode to set
     */
    public void setCkCode(String ckCode) {
        this.ckCode = ckCode;
    }

    /**
     * @return the queryType
     */
    public Integer getQueryType() {
        return queryType;
    }

    /**
     * @param queryType the queryType to set
     */
    public void setQueryType(Integer queryType) {
        this.queryType = queryType;
    }
}
