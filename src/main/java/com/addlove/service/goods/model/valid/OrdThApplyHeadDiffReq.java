package com.addlove.service.goods.model.valid;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 门店退货申请主表参数
 * @author lw
 *
 */
public class OrdThApplyHeadDiffReq {
    /**配送验收单号 */
    @NotBlank(message = "配送验收单号不能为空")
    private String billNo;
    
    /**组织编码 */
    @NotBlank(message = "组织编码 不能为空")
    private String orgCode;
    
    /**组织名称 */
    private String orgName;
    
    /**内部组织编码 */
    @NotBlank(message = "内部组织编码不能为空")
    private String inOrgCode;
    
    /**总部组织编码 */
    @NotBlank(message = "配送验收单号不能为空")
    private String zbOrgCode;
    
    /**总部组织名称 */
    private String zbOrgName;
    
    /**供应商编码 */
    @NotBlank(message = "供应商编码不能为空")
    private String supCode;
    
    /**供应商名称 */
    private String supName;
    
    /**合同ID */
    @NotNull(message = "合同ID不能为空")
    private Long cntId;
    
    /**仓库编码 */
    @NotBlank(message = "仓库编码不能为空")
    private String ckCode;
    
    /**仓库名称 */
    private String ckName;
    
    /**退货数量 */
    @NotNull(message = "退货数量不能为空")
    private Double thCount;
    
    /**含税进价金额 */
    @NotNull(message = "含税进价金额不能为空")
    private Double hCost;
    
    /**无税进价金额  */
    @NotNull(message = "无税进价金额不能为空")
    private Double wCost;
    
    /**税额*/
    @NotNull(message = "税额不能为空")
    private Double jtaxTotal;
    
    /**售价金额 */
    @NotNull(message = "售价金额不能为空")
    private Double sTotal;
    
    /**进销差价 */
    @NotNull(message = "进销差价不能为空")
    private Double cjTotal;
    
    /**配送金额 */
    @NotNull(message = "配送金额不能为空")
    private Double psCost;
    
    /**申请状态:0-未申请(对应前端的“保存操作”),1-已申请(对应前端的“提交操作”),2-退货中,3-退货完毕,9-驳回 */
    @NotBlank(message = "申请状态不能为空")
    private String applyStatus;
    
    /**部门Id */
    @NotNull(message = "部门Id不能为空")
    private Long depId;
    
    /**部门编码 */
    @NotBlank(message = "部门编码不能为空")
    private String depCode;
    
    /**部门名称 */
    private String depName;
    
    /**差异明细*/
    private List<OrdThApplyBodyDiffReq> bodyList;
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
     * @return the inOrgCode
     */
    public String getInOrgCode() {
        return inOrgCode;
    }
    /**
     * @param inOrgCode the inOrgCode to set
     */
    public void setInOrgCode(String inOrgCode) {
        this.inOrgCode = inOrgCode;
    }
    /**
     * @return the zbOrgCode
     */
    public String getZbOrgCode() {
        return zbOrgCode;
    }
    /**
     * @param zbOrgCode the zbOrgCode to set
     */
    public void setZbOrgCode(String zbOrgCode) {
        this.zbOrgCode = zbOrgCode;
    }
    /**
     * @return the zbOrgName
     */
    public String getZbOrgName() {
        return zbOrgName;
    }
    /**
     * @param zbOrgName the zbOrgName to set
     */
    public void setZbOrgName(String zbOrgName) {
        this.zbOrgName = zbOrgName;
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
    /**
     * @return the supName
     */
    public String getSupName() {
        return supName;
    }
    /**
     * @param supName the supName to set
     */
    public void setSupName(String supName) {
        this.supName = supName;
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
     * @return the hCost
     */
    public Double gethCost() {
        return hCost;
    }
    /**
     * @param hCost the hCost to set
     */
    public void sethCost(Double hCost) {
        this.hCost = hCost;
    }
    /**
     * @return the wCost
     */
    public Double getwCost() {
        return wCost;
    }
    /**
     * @param wCost the wCost to set
     */
    public void setwCost(Double wCost) {
        this.wCost = wCost;
    }
    /**
     * @return the jtaxTotal
     */
    public Double getJtaxTotal() {
        return jtaxTotal;
    }
    /**
     * @param jtaxTotal the jtaxTotal to set
     */
    public void setJtaxTotal(Double jtaxTotal) {
        this.jtaxTotal = jtaxTotal;
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
     * @return the cjTotal
     */
    public Double getCjTotal() {
        return cjTotal;
    }
    /**
     * @param cjTotal the cjTotal to set
     */
    public void setCjTotal(Double cjTotal) {
        this.cjTotal = cjTotal;
    }
    /**
     * @return the psCost
     */
    public Double getPsCost() {
        return psCost;
    }
    /**
     * @param psCost the psCost to set
     */
    public void setPsCost(Double psCost) {
        this.psCost = psCost;
    }
    /**
     * @return the applyStatus
     */
    public String getApplyStatus() {
        return applyStatus;
    }
    /**
     * @param applyStatus the applyStatus to set
     */
    public void setApplyStatus(String applyStatus) {
        this.applyStatus = applyStatus;
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
     * @return the bodyList
     */
    public List<OrdThApplyBodyDiffReq> getBodyList() {
        return bodyList;
    }
    /**
     * @param bodyList the bodyList to set
     */
    public void setBodyList(List<OrdThApplyBodyDiffReq> bodyList) {
        this.bodyList = bodyList;
    }
}