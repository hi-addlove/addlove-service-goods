package com.addlove.service.goods.model.valid;

import java.util.List;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotBlank;

/**
 * ADL要货单主表参数
 * @author lw
 *
 */
public class OrdAdlYhHeadReq {
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
    
    /** 要货送达日期*/
    @NotBlank(message = "要货送达日期不能为空")
    private String sdDate;
    
    /** 要货数量*/
    @NotNull(message = "要货数量不能为空")
    private Double yhCount;
    
    /** 要货金额*/
    @NotNull(message = "要货金额不能为空")
    private Double yhTotal;
    
    /** 备注*/
    private String remark;
    
    /** 模板编码*/
    @NotBlank(message = "模板编码不能为空")
    private String modelCode;

    /** 要货波次*/
    @NotBlank(message = "要货波次不能为空")
    private String yhBc;
    
    /**
     * 保存类型：1-保存;2-记账
     */
    @NotNull(message = "保存类型不能为空")
    private Integer saveType;
    
    private List<OrdAdlYhBodyReq> bodyList;

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
     * @return the sdDate
     */
    public String getSdDate() {
        return sdDate;
    }

    /**
     * @param sdDate the sdDate to set
     */
    public void setSdDate(String sdDate) {
        this.sdDate = sdDate;
    }

    /**
     * @return the yhCount
     */
    public Double getYhCount() {
        return yhCount;
    }

    /**
     * @param yhCount the yhCount to set
     */
    public void setYhCount(Double yhCount) {
        this.yhCount = yhCount;
    }

    /**
     * @return the yhTotal
     */
    public Double getYhTotal() {
        return yhTotal;
    }

    /**
     * @param yhTotal the yhTotal to set
     */
    public void setYhTotal(Double yhTotal) {
        this.yhTotal = yhTotal;
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
     * @return the modelCode
     */
    public String getModelCode() {
        return modelCode;
    }

    /**
     * @param modelCode the modelCode to set
     */
    public void setModelCode(String modelCode) {
        this.modelCode = modelCode;
    }

    /**
     * @return the yhBc
     */
    public String getYhBc() {
        return yhBc;
    }

    /**
     * @param yhBc the yhBc to set
     */
    public void setYhBc(String yhBc) {
        this.yhBc = yhBc;
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
    public List<OrdAdlYhBodyReq> getBodyList() {
        return bodyList;
    }

    /**
     * @param bodyList the bodyList to set
     */
    public void setBodyList(List<OrdAdlYhBodyReq> bodyList) {
        this.bodyList = bodyList;
    }
}
