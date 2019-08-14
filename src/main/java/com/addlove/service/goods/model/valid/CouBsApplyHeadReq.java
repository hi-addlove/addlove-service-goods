package com.addlove.service.goods.model.valid;

import java.util.List;

import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 门店报损单主表参数
 * @author lw
 *
 */
public class CouBsApplyHeadReq {
    /** 单据号*/
    private String billNo;

    /** 组织编码*/
    @NotBlank(message = "组织编码不能为空")
    private String orgCode;

    /** 组织名称*/
    private String orgName;

    /** 部门Id*/
    @NotNull(message = "部门Id不能为空")
    private Long depId;

    /** 部门编码*/
    @NotBlank(message = "部门编码不能为空")
    private String depCode;

    /** 部门名称*/
    private String depName;

    /** 报损数量*/
    @NotNull(message = "报损数量不能为空")
    private Double bsCount;
    
    /** 备注*/
    private String remark;
    
    /** 报损原因*/
    @NotBlank(message = "报损原因不能为空")
    private String bsReason;
    
    /**
     * 保存类型：1-保存;2-提交
     */
    @NotNull(message = "保存类型不能为空")
    private Integer saveType;
    
    private List<CouBsApplyBodyReq> bodyList;

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
     * @return the orgName
     */
    public String getOrgName() {
        return orgName;
    }

    /**
     * @param orgName the orgName to set
     */
    public void setOrgName(String orgName) {
        this.orgName = orgName;
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
     * @return the depName
     */
    public String getDepName() {
        return depName;
    }

    /**
     * @param depName the depName to set
     */
    public void setDepName(String depName) {
        this.depName = depName;
    }

    /**
     * @return the bsCount
     */
    public Double getBsCount() {
        return bsCount;
    }

    /**
     * @param bsCount the bsCount to set
     */
    public void setBsCount(Double bsCount) {
        this.bsCount = bsCount;
    }

    /**
     * @return the remark
     */
    public String getRemark() {
        return remark;
    }

    /**
     * @param remark the remark to set
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * @return the bsReason
     */
    public String getBsReason() {
        return bsReason;
    }

    /**
     * @param bsReason the bsReason to set
     */
    public void setBsReason(String bsReason) {
        this.bsReason = bsReason;
    }

    /**
     * @return the saveType
     */
    public Integer getSaveType() {
        return saveType;
    }

    /**
     * @param saveType the saveType to set
     */
    public void setSaveType(Integer saveType) {
        this.saveType = saveType;
    }

    /**
     * @return the bodyList
     */
    public List<CouBsApplyBodyReq> getBodyList() {
        return bodyList;
    }

    /**
     * @param bodyList the bodyList to set
     */
    public void setBodyList(List<CouBsApplyBodyReq> bodyList) {
        this.bodyList = bodyList;
    }
}
