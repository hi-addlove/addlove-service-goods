package com.addlove.service.goods.model.valid;

import java.util.List;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 领用单主表参数
 * @author lw
 *
 */
public class WslLyHeadReq {
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
    
    /** 仓库编码*/
    @NotBlank(message = "仓库编码不能为空")
    private String ckCode;

    /** 仓库名称*/
    private String ckName;
    
    /** 用途*/
    @NotBlank(message = "用途不能为空")
    private String purpose;
    
    /** 领用数量*/
    @NotNull(message = "领用数量不能为空")
    private Double lyCount;

    /** 领用金额*/
    @NotNull(message = "领用金额不能为空")
    private Double sTotal;
    
    /** 实收金额*/
    @NotNull(message = "实收金额不能为空")
    private Double ssTotal;

    /** 备注*/
    private String remark;
    
    /**
     * 保存类型：1-保存;2-记账
     */
    @NotNull(message = "保存类型不能为空")
    private Integer saveType;
    
    private List<WslLyBodyReq> bodyList;

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
     * @return the ckName
     */
    public String getCkName() {
        return ckName;
    }

    /**
     * @param ckName the ckName to set
     */
    public void setCkName(String ckName) {
        this.ckName = ckName;
    }

    /**
     * @return the purpose
     */
    public String getPurpose() {
        return purpose;
    }

    /**
     * @param purpose the purpose to set
     */
    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    /**
     * @return the lyCount
     */
    public Double getLyCount() {
        return lyCount;
    }

    /**
     * @param lyCount the lyCount to set
     */
    public void setLyCount(Double lyCount) {
        this.lyCount = lyCount;
    }

    /**
     * @return the sTotal
     */
    public Double getsTotal() {
        return sTotal;
    }

    /**
     * @param sTotal the sTotal to set
     */
    public void setsTotal(Double sTotal) {
        this.sTotal = sTotal;
    }

    /**
     * @return the ssTotal
     */
    public Double getSsTotal() {
        return ssTotal;
    }

    /**
     * @param ssTotal the ssTotal to set
     */
    public void setSsTotal(Double ssTotal) {
        this.ssTotal = ssTotal;
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
    public List<WslLyBodyReq> getBodyList() {
        return bodyList;
    }

    /**
     * @param bodyList the bodyList to set
     */
    public void setBodyList(List<WslLyBodyReq> bodyList) {
        this.bodyList = bodyList;
    }
}
