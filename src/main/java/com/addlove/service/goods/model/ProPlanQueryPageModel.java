package com.addlove.service.goods.model;


/**
 * 生产计划列表
 * @author lw
 *
 */
public class ProPlanQueryPageModel {
    /**默认页数 */
    private int pageNo;
    
    /**每页默认记录数 */
    private int pageSize;
    
    /**组织编码 */
    private String orgCode;
    
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
     * 完工状态:0-未完工；1-部分完工；2-已完工
     */
    private String doneStatus;
    
    /**
     * 是否完工:0-否；1-是
     */
    private String isDone;
    
    /**
     * 完工部门Id
     */
    private Long depId;

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
     * @return the doneStatus
     */
    public String getDoneStatus() {
        return doneStatus;
    }

    /**
     * @param doneStatus the doneStatus to set
     */
    public void setDoneStatus(String doneStatus) {
        this.doneStatus = doneStatus;
    }

    /**
     * @return the isDone
     */
    public String getIsDone() {
        return isDone;
    }

    /**
     * @param isDone the isDone to set
     */
    public void setIsDone(String isDone) {
        this.isDone = isDone;
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
}
