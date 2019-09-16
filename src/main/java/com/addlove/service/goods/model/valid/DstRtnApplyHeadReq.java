package com.addlove.service.goods.model.valid;

import java.util.List;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 配送退货主表参数
 * @author lw
 *
 */
public class DstRtnApplyHeadReq {
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
    
    /** 合同ID*/
    private Long cntId;

    /** 合同编码*/
    private String htCode;

    /** 合同名称*/
    private String htName;
    
    /** 退货数量*/
    @NotNull(message = "退货数量不能为空")
    private Double thCount;

    /** 退货金额*/
    @NotNull(message = "退货金额不能为空")
    private Double thTotal;
    
    /** 备注*/
    private String remark;
    
    /** 仓库编码*/
    @NotBlank(message = "仓库编码不能为空")
    private String ckCode;

    /** 仓库名称*/
    private String ckName;
    
    /** 退货原因*/
    private String thReason;
    
    /** 本币无税退货金额*/
    @NotNull(message = "无税退货金额不能为空")
    private Double wThTotal;

    /** 计划退货日期*/
    @NotBlank(message = "计划退货日期不能为空")
    private String plnThDate;
    
    /** 售价金额*/
    @NotNull(message = "售价金额不能为空")
    private Double sTotal;
    
    /** 原币配送金额*/
    @NotNull(message = "原币配送金额不能为空")
    private Double stlCurrThTotal;
    
    /**
     * 保存类型：1-保存;2-记账
     */
    @NotNull(message = "保存类型不能为空")
    private Integer saveType;
    
    private List<DstRtnApplyBodyReq> bodyList;

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
     * @return the cntId
     */
    public Long getCntId() {
        return cntId;
    }

    /**
     * @param cntId the cntId to set
     */
    public void setCntId(Long cntId) {
        this.cntId = cntId;
    }

    /**
     * @return the htCode
     */
    public String getHtCode() {
        return htCode;
    }

    /**
     * @param htCode the htCode to set
     */
    public void setHtCode(String htCode) {
        this.htCode = htCode;
    }

    /**
     * @return the htName
     */
    public String getHtName() {
        return htName;
    }

    /**
     * @param htName the htName to set
     */
    public void setHtName(String htName) {
        this.htName = htName;
    }

    /**
     * @return the thCount
     */
    public Double getThCount() {
        return thCount;
    }

    /**
     * @param thCount the thCount to set
     */
    public void setThCount(Double thCount) {
        this.thCount = thCount;
    }

    /**
     * @return the thTotal
     */
    public Double getThTotal() {
        return thTotal;
    }

    /**
     * @param thTotal the thTotal to set
     */
    public void setThTotal(Double thTotal) {
        this.thTotal = thTotal;
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
     * @return the thReason
     */
    public String getThReason() {
        return thReason;
    }

    /**
     * @param thReason the thReason to set
     */
    public void setThReason(String thReason) {
        this.thReason = thReason;
    }

    /**
     * @return the wThTotal
     */
    public Double getwThTotal() {
        return wThTotal;
    }

    /**
     * @param wThTotal the wThTotal to set
     */
    public void setwThTotal(Double wThTotal) {
        this.wThTotal = wThTotal;
    }

    /**
     * @return the plnThDate
     */
    public String getPlnThDate() {
        return plnThDate;
    }

    /**
     * @param plnThDate the plnThDate to set
     */
    public void setPlnThDate(String plnThDate) {
        this.plnThDate = plnThDate;
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
     * @return the stlCurrThTotal
     */
    public Double getStlCurrThTotal() {
        return stlCurrThTotal;
    }

    /**
     * @param stlCurrThTotal the stlCurrThTotal to set
     */
    public void setStlCurrThTotal(Double stlCurrThTotal) {
        this.stlCurrThTotal = stlCurrThTotal;
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
    public List<DstRtnApplyBodyReq> getBodyList() {
        return bodyList;
    }

    /**
     * @param bodyList the bodyList to set
     */
    public void setBodyList(List<DstRtnApplyBodyReq> bodyList) {
        this.bodyList = bodyList;
    }
}
