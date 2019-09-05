package com.addlove.service.goods.model.valid;

import org.hibernate.validator.constraints.NotBlank;

/**
 * ADL要货列表请求参数
 * @author lw
 *
 */
public class OrdAdlYhPageReq {
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
    
    /** 要货状态:0-未确认1-已确认；2-已作废*/
    private String ywStatus;
    
    /** 部门ID*/
    private Long depId;
    
    /** 是否紧急要货：0-否；1-是*/
    @NotBlank(message = "是否紧急要货不能为空")
    private String isUrgent;

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
     * @return the ywStatus
     */
    public String getYwStatus() {
        return ywStatus;
    }

    /**
     * @param ywStatus the ywStatus to set
     */
    public void setYwStatus(String ywStatus) {
        this.ywStatus = ywStatus;
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
     * @return the isUrgent
     */
    public String getIsUrgent() {
        return isUrgent;
    }

    /**
     * @param isUrgent the isUrgent to set
     */
    public void setIsUrgent(String isUrgent) {
        this.isUrgent = isUrgent;
    }
}
