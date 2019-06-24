package com.addlove.service.goods.model.valid;

import java.util.List;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 验收主表参数
 * @author lw
 *
 */
public class OrdJhHeadReq {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORDJHHEAD.BILLNO
     * 单据号
     * @mbggenerated Tue Jun 04 15:32:25 CST 2019
     */
    private String billNo;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORDJHHEAD.ORGCODE
     * 组织编码
     * @mbggenerated Tue Jun 04 15:32:25 CST 2019
     */
    @NotBlank(message = "组织编码不能为空")
    private String orgCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORDJHHEAD.ORGNAME
     * 组织名称
     * @mbggenerated Tue Jun 04 15:32:25 CST 2019
     */
    private String orgName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORDJHHEAD.DEPID
     * 部门Id
     * @mbggenerated Tue Jun 04 15:32:25 CST 2019
     */
    @NotNull(message = "部门Id不能为空")
    private Long depId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORDJHHEAD.DEPCODE
     * 部门编码
     * @mbggenerated Tue Jun 04 15:32:25 CST 2019
     */
    private String depCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORDJHHEAD.DEPNAME
     * 部门名称
     * @mbggenerated Tue Jun 04 15:32:25 CST 2019
     */
    private String depName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORDJHHEAD.SUPCODE
     * 供应商编码
     * @mbggenerated Tue Jun 04 15:32:25 CST 2019
     */
    @NotBlank(message = "供应商编码不能为空")
    private String supCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORDJHHEAD.SUPNAME
     * 供应商名称
     * @mbggenerated Tue Jun 04 15:32:25 CST 2019
     */
    private String supName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORDJHHEAD.CNTID
     * 合同ID
     * @mbggenerated Tue Jun 04 15:32:25 CST 2019
     */
    @NotNull(message = "合同ID不能为空")
    private Long cntId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORDJHHEAD.HTCODE
     * 合同编码
     * @mbggenerated Tue Jun 04 15:32:25 CST 2019
     */
    private String htCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORDJHHEAD.HTNAME
     * 合同名称
     * @mbggenerated Tue Jun 04 15:32:25 CST 2019
     */
    private String htName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORDJHHEAD.JHCOUNT
     * 进货数量
     * @mbggenerated Tue Jun 04 15:32:25 CST 2019
     */
    @NotNull(message = "进货数量不能为空")
    private Double jhCount;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORDJHHEAD.HCOST
     * 含税进价金额
     * @mbggenerated Tue Jun 04 15:32:25 CST 2019
     */
    @NotNull(message = "含税进价金额不能为空")
    private Double hCost;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORDJHHEAD.WCOST
     * 无税进价金额
     * @mbggenerated Tue Jun 04 15:32:25 CST 2019
     */
    @NotNull(message = "无税进价金额不能为空")
    private Double wCost;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORDJHHEAD.JTAXTOTAL
     * 税额
     * @mbggenerated Tue Jun 04 15:32:25 CST 2019
     */
    @NotNull(message = "税额不能为空")
    private Double jtaxTotal;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORDJHHEAD.PSCOST
     * 配送金额
     * @mbggenerated Tue Jun 04 15:32:25 CST 2019
     */
    @NotNull(message = "配送金额不能为空")
    private Double psCost;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORDJHHEAD.STOTAL
     * 售价金额
     * @mbggenerated Tue Jun 04 15:32:25 CST 2019
     */
    @NotNull(message = "售价金额不能为空")
    private Double sTotal;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORDJHHEAD.CJTOTAL
     * 进销差价
     * @mbggenerated Tue Jun 04 15:32:25 CST 2019
     */
    @NotNull(message = "进销差价不能为空")
    private Double cjTotal;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORDJHHEAD.REMARK
     * 备注
     * @mbggenerated Tue Jun 04 15:32:25 CST 2019
     */
    private String remark;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORDJHHEAD.TELEPHONE
     * 电话
     * @mbggenerated Tue Jun 04 15:32:25 CST 2019
     */
    private String telePhone;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORDJHHEAD.FAX
     * 传真
     * @mbggenerated Tue Jun 04 15:32:25 CST 2019
     */
    private String fax;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORDJHHEAD.LINKMAN
     * 联系人
     * @mbggenerated Tue Jun 04 15:32:25 CST 2019
     */
    private String linkMan;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORDJHHEAD.LKMTEL
     * 联系人电话
     * @mbggenerated Tue Jun 04 15:32:25 CST 2019
     */
    private String lkmTel;
    
    /**
     * 保存类型：1-保存；2-记账
     */
    @NotNull(message = "保存类型不能为空")
    private Integer saveType;
    
    /**
     * 经营方式:0-经销；1-代销；2-联销；3-租赁
     */
    @NotBlank(message = "经营方式不能为空")
    private String jyMode;
    
    /**
     * 结算方式:0-经销结算；1-代销结算；2-联销结算；3-租金结算
     */
    @NotBlank(message = "结算方式不能为空")
    private String jsCode;
    
    /**
     * 商品明细
     */
    private List<OrdJhBodyReq> bodyList;

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
     * @return the jhCount
     */
    public Double getJhCount() {
        return jhCount;
    }

    /**
     * @param jhCount the jhCount to set
     */
    public void setJhCount(Double jhCount) {
        this.jhCount = jhCount;
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
     * @return the telePhone
     */
    public String getTelePhone() {
        return telePhone;
    }

    /**
     * @param telePhone the telePhone to set
     */
    public void setTelePhone(String telePhone) {
        this.telePhone = telePhone;
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
     * @return the bodyList
     */
    public List<OrdJhBodyReq> getBodyList() {
        return bodyList;
    }

    /**
     * @param bodyList the bodyList to set
     */
    public void setBodyList(List<OrdJhBodyReq> bodyList) {
        this.bodyList = bodyList;
    }
}
