package com.addlove.service.goods.model;

public class OrdJhQueryPageModel {
    /**默认页数 */
    private Integer pageNo;
    /**每页默认记录数 */
    private Integer pageSize;
    /**组织编码 */
    private String orgCode;
    /**单据号 */
    private String billNo;
    /**开始日期 */
    private String startDate;
    /**结束日期 */
    private String endDate;
    /**收货状态：0-未验收；1-正常验收；2-异常验收 */
    private String tag;
    /**数据状态：0－录入；1－审批；2－转审；3－提交；4-可记账；9-关闭 */
    private String dataStatus;
    /**0904-物流采购验收 0905-门店采购验收 0906-无采购验收 0907-直送验收 0908-配送验收 */
    private String ywType;
    
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
     * @return the tag
     */
    public String getTag() {
        return tag;
    }
    /**
     * @param tag the tag to set
     */
    public void setTag(String tag) {
        this.tag = tag;
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
     * @return the ywType
     */
    public String getYwType() {
        return ywType;
    }
    /**
     * @param ywType the ywType to set
     */
    public void setYwType(String ywType) {
        this.ywType = ywType;
    }
}
