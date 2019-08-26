package com.addlove.service.goods.model.valid;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 加工单主表参数
 * @author lw
 *
 */
public class FrsJgHeadReq {
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
    
    /** 原料数量*/
    @NotNull(message = "原料数量不能为空")
    private Double ylCount;

    /** 原料含税进价金额*/
    @NotNull(message = "原料含税进价金额不能为空")
    private Double ylhCost;

    /** 原料无税进价金额*/
    @NotNull(message = "原料无税进价金额不能为空")
    private Double ylwCost;

    /** 原料售价金额*/
    @NotNull(message = "原料售价金额不能为空")
    private Double ylTotal;
    
    /** 成品数量*/
    @NotNull(message = "成品数量不能为空")
    private Double cpCount;

    /** 成品含税进价金额*/
    @NotNull(message = "成品含税进价金额不能为空")
    private Double cphCost;

    /** 成品无税进价金额*/
    @NotNull(message = "成品无税进价金额不能为空")
    private Double cpwCost;

    /** 成品售价金额*/
    @NotNull(message = "成品售价金额不能为空")
    private Double cpTotal;
    
    /** 备注*/
    private String remark;
    
    /** 仓库编码*/
    @NotBlank(message = "仓库编码不能为空")
    private String ckCode;

    /** 仓库名称*/
    private String ckName;
    
    /**
     * 保存类型：1-保存;2-记账
     */
    @NotNull(message = "保存类型不能为空")
    private Integer saveType;
    
    private List<FrsJgYlReq> ylList;
    
    private List<FrsJgCpReq> cpList;

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
     * @return the ylCount
     */
    public Double getYlCount() {
        return ylCount;
    }

    /**
     * @param ylCount the ylCount to set
     */
    public void setYlCount(Double ylCount) {
        this.ylCount = ylCount;
    }

    /**
     * @return the ylhCost
     */
    public Double getYlhCost() {
        return ylhCost;
    }

    /**
     * @param ylhCost the ylhCost to set
     */
    public void setYlhCost(Double ylhCost) {
        this.ylhCost = ylhCost;
    }

    /**
     * @return the ylwCost
     */
    public Double getYlwCost() {
        return ylwCost;
    }

    /**
     * @param ylwCost the ylwCost to set
     */
    public void setYlwCost(Double ylwCost) {
        this.ylwCost = ylwCost;
    }

    /**
     * @return the ylTotal
     */
    public Double getYlTotal() {
        return ylTotal;
    }

    /**
     * @param ylTotal the ylTotal to set
     */
    public void setYlTotal(Double ylTotal) {
        this.ylTotal = ylTotal;
    }

    /**
     * @return the cpCount
     */
    public Double getCpCount() {
        return cpCount;
    }

    /**
     * @param cpCount the cpCount to set
     */
    public void setCpCount(Double cpCount) {
        this.cpCount = cpCount;
    }

    /**
     * @return the cphCost
     */
    public Double getCphCost() {
        return cphCost;
    }

    /**
     * @param cphCost the cphCost to set
     */
    public void setCphCost(Double cphCost) {
        this.cphCost = cphCost;
    }

    /**
     * @return the cpwCost
     */
    public Double getCpwCost() {
        return cpwCost;
    }

    /**
     * @param cpwCost the cpwCost to set
     */
    public void setCpwCost(Double cpwCost) {
        this.cpwCost = cpwCost;
    }

    /**
     * @return the cpTotal
     */
    public Double getCpTotal() {
        return cpTotal;
    }

    /**
     * @param cpTotal the cpTotal to set
     */
    public void setCpTotal(Double cpTotal) {
        this.cpTotal = cpTotal;
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
     * @return the ylList
     */
    public List<FrsJgYlReq> getYlList() {
        return ylList;
    }

    /**
     * @param ylList the ylList to set
     */
    public void setYlList(List<FrsJgYlReq> ylList) {
        this.ylList = ylList;
    }

    /**
     * @return the cpList
     */
    public List<FrsJgCpReq> getCpList() {
        return cpList;
    }

    /**
     * @param cpList the cpList to set
     */
    public void setCpList(List<FrsJgCpReq> cpList) {
        this.cpList = cpList;
    }
}
