package com.addlove.service.goods.model.valid;

import org.hibernate.validator.constraints.NotBlank;

public class OrdThQueryPageReq {
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
    
    /**审核状态:0-保存，1-审批1，2-审批2，3-审批3，4-审批4 */
    private String checkStatus;
   
    /**提交人（操作人） */
    private String tjrName;

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
     * @return the checkStatus
     */
    public String getCheckStatus() {
        return checkStatus;
    }
    
    /**
     * @param checkStatus the checkStatus to set
     */
    public void setCheckStatus(String checkStatus) {
        this.checkStatus = checkStatus;
    }

    /**
     * @return the tjrName
     */
    public String getTjrName() {
        return tjrName;
    }

    /**
     * @param tjrName the tjrName to set
     */
    public void setTjrName(String tjrName) {
        this.tjrName = tjrName;
    }
}
