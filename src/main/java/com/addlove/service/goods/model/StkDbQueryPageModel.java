package com.addlove.service.goods.model;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 调拨列表
 * @author lw
 *
 */
public class StkDbQueryPageModel {
    /**默认页数 */
    private Integer pageNo;
    
    /**每页默认记录数 */
    private Integer pageSize;
    
    /**单据号 */
    private String billNo;
    
    /**开始日期 */
    private String startDate;
    
    /**结束日期 */
    private String endDate;
    
    /**
     * 数据状态：0－录入；1－审批；2－转审；3－提交；4-可记账；9-关闭
     */
    private String dataStatus;
    
    /**
     * 调拨类型：1-店内调拨；2-店间调拨
     */
    private Integer dbType;
    
    /**组织编码 */
    @NotBlank(message = "组织编码不能为空")
    private String orgCode;
    
    /**
     * 调入组织编码
     */
    private String shOrgCode;

    /**
     * @return the pageNo
     */
    public Integer getPageNo() {
        return pageNo;
    }

    /**
     * @param pageNo the pageNo to set
     */
    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    /**
     * @return the pageSize
     */
    public Integer getPageSize() {
        return pageSize;
    }

    /**
     * @param pageSize the pageSize to set
     */
    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
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
     * @return the dbType
     */
    public Integer getDbType() {
        return dbType;
    }

    /**
     * @param dbType the dbType to set
     */
    public void setDbType(Integer dbType) {
        this.dbType = dbType;
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
     * @return the shOrgCode
     */
    public String getShOrgCode() {
        return shOrgCode;
    }

    /**
     * @param shOrgCode the shOrgCode to set
     */
    public void setShOrgCode(String shOrgCode) {
        this.shOrgCode = shOrgCode;
    }
}
