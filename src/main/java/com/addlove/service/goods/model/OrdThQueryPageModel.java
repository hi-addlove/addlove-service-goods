package com.addlove.service.goods.model;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 退货列表参数
 * @author lw
 *
 */
public class OrdThQueryPageModel {
    /**默认页数 */
    private int pageNo;
    
    /**每页默认记录数 */
    private int pageSize;
    
    /**组织编码 */
    @NotBlank(message = "组织编码不能为空")
    private String orgCode;
    
    /**业务类型：0911-门店采购退货 0912-门店退货给总部  0913-门店直送退货给供应商 0914-总部采购退货 */
    @NotBlank(message = "业务类型不能为空")
    private String ywType;
    
    /**单据号 */
    private String billNo;
    
    /**开始日期 */
    private String startDate;
    
    /**结束日期 */
    private String endDate;
    
    /** 数据状态：0－录入；1－审批；2－转审；3－提交；4-可记账；9-关闭 */
    private String dataStatus;
    
    /**部门编码 */
    private String depCode;
    
    /**供应商编码 */
    private String supCode;

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
     * @return the depCode
     */
    public String getDepCode() {
        return depCode;
    }

    /**
     * @param depCode the depCode to set
     */
    public void setDepCode(String depCode) {
        this.depCode = depCode;
    }

    /**
     * @return the supCode
     */
    public String getSupCode() {
        return supCode;
    }

    /**
     * @param supCode the supCode to set
     */
    public void setSupCode(String supCode) {
        this.supCode = supCode;
    }
}
