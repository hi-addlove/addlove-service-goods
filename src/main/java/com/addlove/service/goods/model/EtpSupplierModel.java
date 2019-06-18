package com.addlove.service.goods.model;

/**
 * 供应商表
 * @author lw
 *
 */
public class EtpSupplierModel {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TETPSUPPLIER.ORGCODE
     * 组织编码
     * @mbggenerated Tue Jun 18 11:01:37 CST 2019
     */
    private String orgCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TETPSUPPLIER.ETPCODE
     * 供应商编码
     * @mbggenerated Tue Jun 18 11:01:37 CST 2019
     */
    private String etpCode;
    
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TETPSUPPLIER.ETPNAME
     * 供应商名称
     * @mbggenerated Tue Jun 18 11:01:37 CST 2019
     */
    private String etpName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TETPSUPPLIER.ETPORGCODE
     * 供应商组织编码
     * @mbggenerated Tue Jun 18 11:01:37 CST 2019
     */
    private String etpOrgCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TETPSUPPLIER.DATASTATUS
     * 数据状态:0－录入；1－审批；2－转审；3－提交；4-可记账；9-关闭
     * @mbggenerated Tue Jun 18 11:01:37 CST 2019
     */
    private String dataStatus;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TETPSUPPLIER.YWSTATUS
     * 供应商状态:0－注册；1－启用；2－淘汰；3－注销；
     * @mbggenerated Tue Jun 18 11:01:37 CST 2019
     */
    private String ywStatus;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TETPSUPPLIER.ETPATTRIBUTE
     * 供应商属性:0－潜在供应商；1－内部供应商；2－外部供应商；
     * @mbggenerated Tue Jun 18 11:01:37 CST 2019
     */
    private String etpAttribute;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TETPSUPPLIER.ETPTYPE
     * 供应商类型:0－普通供应商；1－重点供应商；2－散供应商；
     * @mbggenerated Tue Jun 18 11:01:37 CST 2019
     */
    private String etpType;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TETPSUPPLIER.ETPLEVEL
     * 供应商级别
     * @mbggenerated Tue Jun 18 11:01:37 CST 2019
     */
    private String etpLevel;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TETPSUPPLIER.LOGINDATE
     * 注册日期
     * @mbggenerated Tue Jun 18 11:01:37 CST 2019
     */
    private String loginDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TETPSUPPLIER.LOGOUTDATE
     * 注销日期
     * @mbggenerated Tue Jun 18 11:01:37 CST 2019
     */
    private String logoutDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TETPSUPPLIER.JSETPCODE
     * 结算企业:默认自身（企业编码）
     * @mbggenerated Tue Jun 18 11:01:37 CST 2019
     */
    private String jsEtpCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TETPSUPPLIER.ISQK
     * 是否允许欠款:0-否；1-是
     * @mbggenerated Tue Jun 18 11:01:37 CST 2019
     */
    private String isQk;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TETPSUPPLIER.QKTOTAL
     * 允许欠款总额度:当IsQk=1是有效
     * @mbggenerated Tue Jun 18 11:01:37 CST 2019
     */
    private Double qkTotal;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TETPSUPPLIER.PDTOTAL
     * 铺底金额限制
     * @mbggenerated Tue Jun 18 11:01:37 CST 2019
     */
    private Double pdTotal;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TETPSUPPLIER.JHTOTAL
     * 累计进货金额
     * @mbggenerated Tue Jun 18 11:01:37 CST 2019
     */
    private Double jhTotal;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TETPSUPPLIER.XSTOTAL
     * 累计销售金额
     * @mbggenerated Tue Jun 18 11:01:37 CST 2019
     */
    private Double xsTotal;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TETPSUPPLIER.MAXCOUNT
     * 最大往来数量
     * @mbggenerated Tue Jun 18 11:01:37 CST 2019
     */
    private Double maxCount;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TETPSUPPLIER.MAXTOTAL
     * 最大往来金额
     * @mbggenerated Tue Jun 18 11:01:37 CST 2019
     */
    private Double maxTotal;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TETPSUPPLIER.MINCOUNT
     * 最小往来数量
     * @mbggenerated Tue Jun 18 11:01:37 CST 2019
     */
    private Double minCount;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TETPSUPPLIER.MINTOTAL
     * 最小往来金额
     * @mbggenerated Tue Jun 18 11:01:37 CST 2019
     */
    private Double minTotal;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TETPSUPPLIER.BGNDATE
     * 第一次发生业务时间
     * @mbggenerated Tue Jun 18 11:01:37 CST 2019
     */
    private String bgnDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TETPSUPPLIER.ENDDATE
     * 最近一次发生业务时间
     * @mbggenerated Tue Jun 18 11:01:37 CST 2019
     */
    private String endDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TETPSUPPLIER.ISACTIVE
     * 是否可用:0-否；1-是
     * @mbggenerated Tue Jun 18 11:01:37 CST 2019
     */
    private String isActive;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TETPSUPPLIER.REMARK
     * 备注
     * @mbggenerated Tue Jun 18 11:01:37 CST 2019
     */
    private String remark;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TETPSUPPLIER.ETPCLASS
     * 供应商性质:0－生产商；1－经销商；2－贸易商；
     * @mbggenerated Tue Jun 18 11:01:37 CST 2019
     */
    private String etpClass;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TETPSUPPLIER.JSINFO
     * 结算信息描述
     * @mbggenerated Tue Jun 18 11:01:37 CST 2019
     */
    private String jsInfo;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TETPSUPPLIER.RTNRATE
     * 返点比例
     * @mbggenerated Tue Jun 18 11:01:37 CST 2019
     */
    private Double rtnRate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TETPSUPPLIER.MAINSHCKCODE
     * 主收货仓库编码
     * @mbggenerated Tue Jun 18 11:01:37 CST 2019
     */
    private String mainShCkCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TETPSUPPLIER.DEPID
     * 部门Id
     * @mbggenerated Tue Jun 18 11:01:37 CST 2019
     */
    private Long depId;

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
     * @return the etpCode
     */
    public String getEtpCode() {
        return etpCode;
    }

    /**
     * @param etpCode the etpCode to set
     */
    public void setEtpCode(String etpCode) {
        this.etpCode = etpCode;
    }

    /**
     * @return the etpName
     */
    public String getEtpName() {
        return etpName;
    }

    /**
     * @param etpName the etpName to set
     */
    public void setEtpName(String etpName) {
        this.etpName = etpName;
    }

    /**
     * @return the etpOrgCode
     */
    public String getEtpOrgCode() {
        return etpOrgCode;
    }

    /**
     * @param etpOrgCode the etpOrgCode to set
     */
    public void setEtpOrgCode(String etpOrgCode) {
        this.etpOrgCode = etpOrgCode;
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
     * @return the ywStatus
     */
    public String getYwStatus() {
        return ywStatus;
    }

    /**
     * @param ywStatus the ywStatus to set
     */
    public void setYwStatus(String ywStatus) {
        this.ywStatus = ywStatus;
    }

    /**
     * @return the etpAttribute
     */
    public String getEtpAttribute() {
        return etpAttribute;
    }

    /**
     * @param etpAttribute the etpAttribute to set
     */
    public void setEtpAttribute(String etpAttribute) {
        this.etpAttribute = etpAttribute;
    }

    /**
     * @return the etpType
     */
    public String getEtpType() {
        return etpType;
    }

    /**
     * @param etpType the etpType to set
     */
    public void setEtpType(String etpType) {
        this.etpType = etpType;
    }

    /**
     * @return the etpLevel
     */
    public String getEtpLevel() {
        return etpLevel;
    }

    /**
     * @param etpLevel the etpLevel to set
     */
    public void setEtpLevel(String etpLevel) {
        this.etpLevel = etpLevel;
    }

    /**
     * @return the loginDate
     */
    public String getLoginDate() {
        return loginDate;
    }

    /**
     * @param loginDate the loginDate to set
     */
    public void setLoginDate(String loginDate) {
        this.loginDate = loginDate;
    }

    /**
     * @return the logoutDate
     */
    public String getLogoutDate() {
        return logoutDate;
    }

    /**
     * @param logoutDate the logoutDate to set
     */
    public void setLogoutDate(String logoutDate) {
        this.logoutDate = logoutDate;
    }

    /**
     * @return the jsEtpCode
     */
    public String getJsEtpCode() {
        return jsEtpCode;
    }

    /**
     * @param jsEtpCode the jsEtpCode to set
     */
    public void setJsEtpCode(String jsEtpCode) {
        this.jsEtpCode = jsEtpCode;
    }

    /**
     * @return the isQk
     */
    public String getIsQk() {
        return isQk;
    }

    /**
     * @param isQk the isQk to set
     */
    public void setIsQk(String isQk) {
        this.isQk = isQk;
    }

    /**
     * @return the qkTotal
     */
    public Double getQkTotal() {
        return qkTotal;
    }

    /**
     * @param qkTotal the qkTotal to set
     */
    public void setQkTotal(Double qkTotal) {
        this.qkTotal = qkTotal;
    }

    /**
     * @return the pdTotal
     */
    public Double getPdTotal() {
        return pdTotal;
    }

    /**
     * @param pdTotal the pdTotal to set
     */
    public void setPdTotal(Double pdTotal) {
        this.pdTotal = pdTotal;
    }

    /**
     * @return the jhTotal
     */
    public Double getJhTotal() {
        return jhTotal;
    }

    /**
     * @param jhTotal the jhTotal to set
     */
    public void setJhTotal(Double jhTotal) {
        this.jhTotal = jhTotal;
    }

    /**
     * @return the xsTotal
     */
    public Double getXsTotal() {
        return xsTotal;
    }

    /**
     * @param xsTotal the xsTotal to set
     */
    public void setXsTotal(Double xsTotal) {
        this.xsTotal = xsTotal;
    }

    /**
     * @return the maxCount
     */
    public Double getMaxCount() {
        return maxCount;
    }

    /**
     * @param maxCount the maxCount to set
     */
    public void setMaxCount(Double maxCount) {
        this.maxCount = maxCount;
    }

    /**
     * @return the maxTotal
     */
    public Double getMaxTotal() {
        return maxTotal;
    }

    /**
     * @param maxTotal the maxTotal to set
     */
    public void setMaxTotal(Double maxTotal) {
        this.maxTotal = maxTotal;
    }

    /**
     * @return the minCount
     */
    public Double getMinCount() {
        return minCount;
    }

    /**
     * @param minCount the minCount to set
     */
    public void setMinCount(Double minCount) {
        this.minCount = minCount;
    }

    /**
     * @return the minTotal
     */
    public Double getMinTotal() {
        return minTotal;
    }

    /**
     * @param minTotal the minTotal to set
     */
    public void setMinTotal(Double minTotal) {
        this.minTotal = minTotal;
    }

    /**
     * @return the bgnDate
     */
    public String getBgnDate() {
        return bgnDate;
    }

    /**
     * @param bgnDate the bgnDate to set
     */
    public void setBgnDate(String bgnDate) {
        this.bgnDate = bgnDate;
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
     * @return the isActive
     */
    public String getIsActive() {
        return isActive;
    }

    /**
     * @param isActive the isActive to set
     */
    public void setIsActive(String isActive) {
        this.isActive = isActive;
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
     * @return the etpClass
     */
    public String getEtpClass() {
        return etpClass;
    }

    /**
     * @param etpClass the etpClass to set
     */
    public void setEtpClass(String etpClass) {
        this.etpClass = etpClass;
    }

    /**
     * @return the jsInfo
     */
    public String getJsInfo() {
        return jsInfo;
    }

    /**
     * @param jsInfo the jsInfo to set
     */
    public void setJsInfo(String jsInfo) {
        this.jsInfo = jsInfo;
    }

    /**
     * @return the rtnRate
     */
    public Double getRtnRate() {
        return rtnRate;
    }

    /**
     * @param rtnRate the rtnRate to set
     */
    public void setRtnRate(Double rtnRate) {
        this.rtnRate = rtnRate;
    }

    /**
     * @return the mainShCkCode
     */
    public String getMainShCkCode() {
        return mainShCkCode;
    }

    /**
     * @param mainShCkCode the mainShCkCode to set
     */
    public void setMainShCkCode(String mainShCkCode) {
        this.mainShCkCode = mainShCkCode;
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
}