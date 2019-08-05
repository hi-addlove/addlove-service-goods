package com.addlove.service.goods.model.valid;

import java.util.List;

import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 门店盘点单主表参数
 * @author lw
 *
 */
public class CouMdPdHeadReq {
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
    private String depCode;

    /** 部门名称*/
    private String depName;
    
    /** 单据类型:0-日盘；1-周盘；2-月盘*/
    @NotBlank(message = "单据类型不能为空")
    private String billType;
    
    /** 账面数量*/
    @NotNull(message = "账面数量不能为空")
    private Double zmCount;

    /** 账面含税进价金额*/
    @NotNull(message = "账面含税进价金额不能为空")
    private Double zmHCost;

    /** 账面无税进价金额*/
    @NotNull(message = "账面无税进价金额不能为空")
    private Double zmWCost;

    /** 账面售价金额*/
    @NotNull(message = "账面售价金额不能为空")
    private Double zmSCost;

    /** 实盘数量*/
    @NotNull(message = "实盘数量不能为空")
    private Double sJCount;

    /** 实盘含税进价金额*/
    @NotNull(message = "实盘含税进价金额不能为空")
    private Double sJhCost;

    /** 实盘无税进价金额*/
    @NotNull(message = "实盘无税进价金额不能为空")
    private Double sJwCost;

    /** 实盘售价金额*/
    @NotNull(message = "实盘售价金额不能为空")
    private Double sJsCost;

    /** 盈亏数量*/
    @NotNull(message = "盈亏数量不能为空")
    private Double ykCount;

    /** 盈亏含税进价金额*/
    @NotNull(message = "盈亏含税进价金额不能为空")
    private Double ykHCost;

    /** 盈亏无税进价金额*/
    @NotNull(message = "盈亏无税进价金额不能为空")
    private Double ykWCost;

    /** 盈亏售价金额*/
    @NotNull(message = "盈亏售价金额不能为空")
    private Double ykSCost;
    
    /** 备注*/
    private String remark;
    
    /**
     * 保存类型：1-新增保存；2-编辑保存
     */
    @NotNull(message = "保存类型不能为空")
    private Integer saveType;
    
    private List<CouMdPdBodyReq> bodyList;

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
     * @return the billType
     */
    public String getBillType() {
        return billType;
    }

    /**
     * @param billType the billType to set
     */
    public void setBillType(String billType) {
        this.billType = billType;
    }

    /**
     * @return the zmCount
     */
    public Double getZmCount() {
        return zmCount;
    }

    /**
     * @param zmCount the zmCount to set
     */
    public void setZmCount(Double zmCount) {
        this.zmCount = zmCount;
    }

    /**
     * @return the zmHCost
     */
    public Double getZmHCost() {
        return zmHCost;
    }

    /**
     * @param zmHCost the zmHCost to set
     */
    public void setZmHCost(Double zmHCost) {
        this.zmHCost = zmHCost;
    }

    /**
     * @return the zmWCost
     */
    public Double getZmWCost() {
        return zmWCost;
    }

    /**
     * @param zmWCost the zmWCost to set
     */
    public void setZmWCost(Double zmWCost) {
        this.zmWCost = zmWCost;
    }

    /**
     * @return the zmSCost
     */
    public Double getZmSCost() {
        return zmSCost;
    }

    /**
     * @param zmSCost the zmSCost to set
     */
    public void setZmSCost(Double zmSCost) {
        this.zmSCost = zmSCost;
    }

    /**
     * @return the sJCount
     */
    public Double getsJCount() {
        return sJCount;
    }

    /**
     * @param sJCount the sJCount to set
     */
    public void setsJCount(Double sJCount) {
        this.sJCount = sJCount;
    }

    /**
     * @return the sJhCost
     */
    public Double getsJhCost() {
        return sJhCost;
    }

    /**
     * @param sJhCost the sJhCost to set
     */
    public void setsJhCost(Double sJhCost) {
        this.sJhCost = sJhCost;
    }

    /**
     * @return the sJwCost
     */
    public Double getsJwCost() {
        return sJwCost;
    }

    /**
     * @param sJwCost the sJwCost to set
     */
    public void setsJwCost(Double sJwCost) {
        this.sJwCost = sJwCost;
    }

    /**
     * @return the sJsCost
     */
    public Double getsJsCost() {
        return sJsCost;
    }

    /**
     * @param sJsCost the sJsCost to set
     */
    public void setsJsCost(Double sJsCost) {
        this.sJsCost = sJsCost;
    }

    /**
     * @return the ykCount
     */
    public Double getYkCount() {
        return ykCount;
    }

    /**
     * @param ykCount the ykCount to set
     */
    public void setYkCount(Double ykCount) {
        this.ykCount = ykCount;
    }

    /**
     * @return the ykHCost
     */
    public Double getYkHCost() {
        return ykHCost;
    }

    /**
     * @param ykHCost the ykHCost to set
     */
    public void setYkHCost(Double ykHCost) {
        this.ykHCost = ykHCost;
    }

    /**
     * @return the ykWCost
     */
    public Double getYkWCost() {
        return ykWCost;
    }

    /**
     * @param ykWCost the ykWCost to set
     */
    public void setYkWCost(Double ykWCost) {
        this.ykWCost = ykWCost;
    }

    /**
     * @return the ykSCost
     */
    public Double getYkSCost() {
        return ykSCost;
    }

    /**
     * @param ykSCost the ykSCost to set
     */
    public void setYkSCost(Double ykSCost) {
        this.ykSCost = ykSCost;
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
    public List<CouMdPdBodyReq> getBodyList() {
        return bodyList;
    }

    /**
     * @param bodyList the bodyList to set
     */
    public void setBodyList(List<CouMdPdBodyReq> bodyList) {
        this.bodyList = bodyList;
    }
}
