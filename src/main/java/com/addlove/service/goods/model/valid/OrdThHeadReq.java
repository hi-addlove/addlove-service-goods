package com.addlove.service.goods.model.valid;

import java.util.List;

import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 退货单主表参数
 * @author lw
 *
 */
public class OrdThHeadReq {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORDTHHEAD.BILLNO
     * 单据号
     * @mbggenerated Thu Jul 11 16:42:43 CST 2019
     */
    private String billNo;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORDTHHEAD.BILLTYPE
     * 单据类型：0-退货返厂；1-验收冲红；2-整单冲红
     * @mbggenerated Thu Jul 11 16:42:43 CST 2019
     */
    @NotBlank(message = "单据类型不能为空")
    private String billType;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORDTHHEAD.ORGCODE
     * 组织编码
     * @mbggenerated Thu Jul 11 16:42:43 CST 2019
     */
    @NotBlank(message = "组织编码不能为空")
    private String orgCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORDTHHEAD.ORGNAME
     * 组织名称
     * @mbggenerated Thu Jul 11 16:42:43 CST 2019
     */
    private String orgName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORDTHHEAD.DEPID
     * 部门Id：记录退货的部门表头上的部门级别必须与组织的核算级别一致或者比核算级别低，如果核算级别到组织一级则部门不必用户录入由系统自动处理（最好是隐藏），
     * 如果核算到部门，则一张单据只能包含该核算部门的数据。
     * @mbggenerated Thu Jul 11 16:42:43 CST 2019
     */
    @NotNull(message = "部门Id不能为空")
    private Long depId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORDTHHEAD.DEPCODE
     * 部门编码
     * @mbggenerated Thu Jul 11 16:42:43 CST 2019
     */
    @NotBlank(message = "部门编码不能为空")
    private String depCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORDTHHEAD.DEPNAME
     * 部门名称
     * @mbggenerated Thu Jul 11 16:42:43 CST 2019
     */
    private String depName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORDTHHEAD.SUPCODE
     * 供应商编码：当退货类型为1、3、4时，记录货退给的供应商
     * @mbggenerated Thu Jul 11 16:42:43 CST 2019
     */
    @NotBlank(message = "供应商编码不能为空")
    private String supCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORDTHHEAD.SUPNAME
     * 供应商名称
     * @mbggenerated Thu Jul 11 16:42:43 CST 2019
     */
    private String supName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORDTHHEAD.CNTID
     * 合同ID：非直送退货时指退货组织的进货合同；直送退货时指总部组织的进货合同
     * @mbggenerated Thu Jul 11 16:42:43 CST 2019
     */
    @NotNull(message = "合同ID不能为空")
    private Long cntId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORDTHHEAD.HTCODE
     * 合同编码
     * @mbggenerated Thu Jul 11 16:42:43 CST 2019
     */
    @NotBlank(message = "合同编码不能为空")
    private String htCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORDTHHEAD.HTNAME
     * 合同名称
     * @mbggenerated Thu Jul 11 16:42:43 CST 2019
     */
    private String htName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORDTHHEAD.JYMODE
     * 经营方式：0-经销；1-代销；2-联销
     * @mbggenerated Thu Jul 11 16:42:43 CST 2019
     */
    @NotBlank(message = "经营方式不能为空")
    private String jyMode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORDTHHEAD.JSCODE
     * 结算方式编码
     * @mbggenerated Thu Jul 11 16:42:43 CST 2019
     */
    @NotBlank(message = "结算方式不能为空")
    private String jsCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORDTHHEAD.THCOUNT
     * 退货数量
     * @mbggenerated Thu Jul 11 16:42:43 CST 2019
     */
    @NotNull(message = "退货数量不能为空")
    private Double thCount;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORDTHHEAD.HCOST
     * 含税进价金额
     * @mbggenerated Thu Jul 11 16:42:43 CST 2019
     */
    @NotNull(message = "含税进价金额不能为空")
    private Double hCost;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORDTHHEAD.WCOST
     * 无税进价金额
     * @mbggenerated Thu Jul 11 16:42:43 CST 2019
     */
    @NotNull(message = "无税进价金额不能为空")
    private Double wCost;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORDTHHEAD.JTAXTOTAL
     * 进项税额
     * @mbggenerated Thu Jul 11 16:42:43 CST 2019
     */
    @NotNull(message = "进项税额不能为空")
    private Double jTaxTotal;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORDTHHEAD.STOTAL
     * 售价金额
     * @mbggenerated Thu Jul 11 16:42:43 CST 2019
     */
    @NotNull(message = "售价金额不能为空")
    private Double sTotal;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORDTHHEAD.CJTOTAL
     * 进销差价
     * @mbggenerated Thu Jul 11 16:42:43 CST 2019
     */
    @NotNull(message = "进销差价不能为空")
    private Double cjTotal;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORDTHHEAD.PSCOST
     * 配送金额：直送验收时使用
     * @mbggenerated Thu Jul 11 16:42:43 CST 2019
     */
    @NotNull(message = "配送金额不能为空")
    private Double psCost;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORDTHHEAD.THREASON
     * 退货原因
     * @mbggenerated Thu Jul 11 16:42:43 CST 2019
     */
    private String thReason;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORDTHHEAD.WPSTOTAL
     * 无税配送金额：直送验收时使用
     * @mbggenerated Thu Jul 11 16:42:43 CST 2019
     */
    @NotNull(message = "无税配送金额不能为空")
    private Double wpsTotal;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORDTHHEAD.XTAXTOTAL
     * 销项税额
     * @mbggenerated Thu Jul 11 16:42:43 CST 2019
     */
    @NotNull(message = "销项税额不能为空")
    private Double xTaxTotal;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORDTHHEAD.TELEPHONE
     * 电话
     * @mbggenerated Thu Jul 11 16:42:43 CST 2019
     */
    private String telephone;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORDTHHEAD.FAX
     * 传真
     * @mbggenerated Thu Jul 11 16:42:43 CST 2019
     */
    private String fax;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORDTHHEAD.LINKMAN
     * 联系人
     * @mbggenerated Thu Jul 11 16:42:43 CST 2019
     */
    private String linkMan;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORDTHHEAD.LKMTEL
     * 联系人电话
     * @mbggenerated Thu Jul 11 16:42:43 CST 2019
     */
    private String lkmTel;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORDTHHEAD.REMARK
     *
     * @mbggenerated Thu Jul 11 16:42:43 CST 2019
     */
    private String remark;
    
    /**
     * 保存类型：1-保存；2-记账
     */
    @NotNull(message = "保存类型不能为空")
    private Integer saveType;
    
    private List<OrdThBodyReq> bodyList;

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
     * @return the jyMode
     */
    public String getJyMode() {
        return jyMode;
    }

    /**
     * @param jyMode the jyMode to set
     */
    public void setJyMode(String jyMode) {
        this.jyMode = jyMode;
    }

    /**
     * @return the jsCode
     */
    public String getJsCode() {
        return jsCode;
    }

    /**
     * @param jsCode the jsCode to set
     */
    public void setJsCode(String jsCode) {
        this.jsCode = jsCode;
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
     * @return the jTaxTotal
     */
    public Double getjTaxTotal() {
        return jTaxTotal;
    }

    /**
     * @param jTaxTotal the jTaxTotal to set
     */
    public void setjTaxTotal(Double jTaxTotal) {
        this.jTaxTotal = jTaxTotal;
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
     * @return the wpsTotal
     */
    public Double getWpsTotal() {
        return wpsTotal;
    }

    /**
     * @param wpsTotal the wpsTotal to set
     */
    public void setWpsTotal(Double wpsTotal) {
        this.wpsTotal = wpsTotal;
    }

    /**
     * @return the xTaxTotal
     */
    public Double getxTaxTotal() {
        return xTaxTotal;
    }

    /**
     * @param xTaxTotal the xTaxTotal to set
     */
    public void setxTaxTotal(Double xTaxTotal) {
        this.xTaxTotal = xTaxTotal;
    }

    /**
     * @return the telephone
     */
    public String getTelephone() {
        return telephone;
    }

    /**
     * @param telephone the telephone to set
     */
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    /**
     * @return the fax
     */
    public String getFax() {
        return fax;
    }

    /**
     * @param fax the fax to set
     */
    public void setFax(String fax) {
        this.fax = fax;
    }

    /**
     * @return the linkMan
     */
    public String getLinkMan() {
        return linkMan;
    }

    /**
     * @param linkMan the linkMan to set
     */
    public void setLinkMan(String linkMan) {
        this.linkMan = linkMan;
    }

    /**
     * @return the lkmTel
     */
    public String getLkmTel() {
        return lkmTel;
    }

    /**
     * @param lkmTel the lkmTel to set
     */
    public void setLkmTel(String lkmTel) {
        this.lkmTel = lkmTel;
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
    public List<OrdThBodyReq> getBodyList() {
        return bodyList;
    }

    /**
     * @param bodyList the bodyList to set
     */
    public void setBodyList(List<OrdThBodyReq> bodyList) {
        this.bodyList = bodyList;
    }
}