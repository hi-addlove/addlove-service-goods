package com.addlove.service.goods.model;

import java.util.List;

/**
 * 门店配送退货申请单主表(质量退货)
 * @author lw
 *
 */
public class DstRtnApplyHeadModel {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORDTHAPPLYHEAD.BILLNO
     * 单据号
     * @mbggenerated Thu Jun 06 13:47:09 CST 2019
     */
    private String billNo;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORDTHAPPLYHEAD.LRDATE
     * 录入日期
     * @mbggenerated Thu Jun 06 13:47:09 CST 2019
     */
    private String lrDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORDTHAPPLYHEAD.USERID
     * 录入人ID
     * @mbggenerated Thu Jun 06 13:47:09 CST 2019
     */
    private Long userId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORDTHAPPLYHEAD.USERCODE
     * 录入人编码
     * @mbggenerated Thu Jun 06 13:47:09 CST 2019
     */
    private String userCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORDTHAPPLYHEAD.USERNAME
     * 录入人姓名
     * @mbggenerated Thu Jun 06 13:47:09 CST 2019
     */
    private String userName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TDSTRTNAPPLYHEAD.GENTYPE
     * 生成类型：0-人工；1-系统
     * @mbggenerated Mon Sep 16 11:30:01 CST 2019
     */
    private String genType;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORDTHAPPLYHEAD.ORGCODE
     * 组织编码(用于记录退货的组织)
     * @mbggenerated Thu Jun 06 13:47:09 CST 2019
     */
    private String orgCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORDTHAPPLYHEAD.ORGNAME
     * 组织名称
     * @mbggenerated Thu Jun 06 13:47:09 CST 2019
     */
    private String orgName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORDTHAPPLYHEAD.INORGCODE
     * 内部组织编码
     * @mbggenerated Thu Jun 06 13:47:09 CST 2019
     */
    private String inOrgCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORDTHAPPLYHEAD.DEPID
     * 部门Id
     * @mbggenerated Thu Jun 06 13:47:09 CST 2019
     */
    private Long depId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORDTHAPPLYHEAD.DEPCODE
     * 部门编码
     * @mbggenerated Thu Jun 06 13:47:09 CST 2019
     */
    private String depCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORDTHAPPLYHEAD.DEPNAME
     * 部门名称
     * @mbggenerated Thu Jun 06 13:47:09 CST 2019
     */
    private String depName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TDSTRTNAPPLYHEAD.ORGTYPE
     * 组织类型：0：门店1：客户
     * @mbggenerated Mon Sep 16 11:30:01 CST 2019
     */
    private String orgType;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TDSTRTNAPPLYHEAD.THORGCODE
     * 客户编码
     * @mbggenerated Mon Sep 16 11:30:01 CST 2019
     */
    private String thOrgCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TDSTRTNAPPLYHEAD.THORGNAME
     * 客户名称
     * @mbggenerated Mon Sep 16 11:30:01 CST 2019
     */
    private String thOrgnNme;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TDSTRTNAPPLYHEAD.INTHORGCODE
     * 退货内部组织编码
     * @mbggenerated Mon Sep 16 11:30:01 CST 2019
     */
    private String inThOrgCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORDTHAPPLYHEAD.CNTID
     * 合同ID
     * @mbggenerated Thu Jun 06 13:47:09 CST 2019
     */
    private Long cntId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORDTHAPPLYHEAD.HTCODE
     * 合同编码
     * @mbggenerated Thu Jun 06 13:47:09 CST 2019
     */
    private String htCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORDTHAPPLYHEAD.HTNAME
     * 合同名称
     * @mbggenerated Thu Jun 06 13:47:09 CST 2019
     */
    private String htName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORDTHAPPLYHEAD.THCOUNT
     * 退货数量
     * @mbggenerated Thu Jun 06 13:47:09 CST 2019
     */
    private Double thCount;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TDSTRTNAPPLYHEAD.THTOTAL
     * 本币退货金额
     * @mbggenerated Mon Sep 16 11:30:01 CST 2019
     */
    private Double thTotal;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORDTHAPPLYHEAD.JYMODE
     * 经营方式:0-经销；1-代销；2-联销
     * @mbggenerated Thu Jun 06 13:47:09 CST 2019
     */
    private String jyMode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORDTHAPPLYHEAD.JSCODE
     * 结算方式编码
     * @mbggenerated Thu Jun 06 13:47:09 CST 2019
     */
    private String jsCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORDTHAPPLYHEAD.JZDATE
     * 记账日期
     * @mbggenerated Thu Jun 06 13:47:09 CST 2019
     */
    private String jzDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORDTHAPPLYHEAD.JZRID
     * 记账人ID
     * @mbggenerated Thu Jun 06 13:47:09 CST 2019
     */
    private Long jzrId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORDTHAPPLYHEAD.JZRCODE
     * 记账人编码
     * @mbggenerated Thu Jun 06 13:47:09 CST 2019
     */
    private String jzrCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORDTHAPPLYHEAD.JZRNAME
     * 记账人姓名
     * @mbggenerated Thu Jun 06 13:47:09 CST 2019
     */
    private String jzrName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORDTHAPPLYHEAD.RPTDATE
     * 报表日期
     * @mbggenerated Thu Jun 06 13:47:09 CST 2019
     */
    private String rptDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORDTHAPPLYHEAD.PRNTIMES
     * 打印次数
     * @mbggenerated Thu Jun 06 13:47:09 CST 2019
     */
    private Long prnTimes;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORDTHAPPLYHEAD.PRNDATE
     * 打印时间
     * @mbggenerated Thu Jun 06 13:47:09 CST 2019
     */
    private String prnDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORDTHAPPLYHEAD.TIMEMARK
     * 时间戳
     * @mbggenerated Thu Jun 06 13:47:09 CST 2019
     */
    private Long timeMark;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORDTHAPPLYHEAD.YWTYPE
     * 业务类型:0911-门店采购退货 0912-门店退货给总部  0913-门店直送退货给供应商 0914-总部采购退货
     * @mbggenerated Thu Jun 06 13:47:09 CST 2019
     */
    private String ywType;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORDTHAPPLYHEAD.YWIOTYPE
     * 内外部业务类型
     * @mbggenerated Thu Jun 06 13:47:09 CST 2019
     */
    private String ywIoType;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TDSTRTNAPPLYHEAD.REMARK
     *
     * @mbggenerated Mon Sep 16 11:30:01 CST 2019
     */
    private String remark;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORDTHAPPLYHEAD.JZTYPE
     * 记账类型:0-手工;1-系统
     * @mbggenerated Thu Jun 06 13:47:09 CST 2019
     */
    private String jzType;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORDTHAPPLYHEAD.DATASTATUS
     * 数据状态:0－录入；1－审批；2－转审；3－提交；4-可记账；9-关闭
     * @mbggenerated Thu Jun 06 13:47:09 CST 2019
     */
    private String dataStatus;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORDTHAPPLYHEAD.CKCODE
     * 仓库编码:连锁仓库或者物流逻辑仓库（连锁使用）
     * @mbggenerated Thu Jun 06 13:47:09 CST 2019
     */
    private String ckCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORDTHAPPLYHEAD.CKNAME
     * 仓库名称
     * @mbggenerated Thu Jun 06 13:47:09 CST 2019
     */
    private String ckName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORDTHAPPLYHEAD.THREASON
     * 退货原因
     * @mbggenerated Thu Jun 06 13:47:09 CST 2019
     */
    private String thReason;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TDSTRTNAPPLYHEAD.WTHTOTAL
     * 本币无税退货金额
     * @mbggenerated Mon Sep 16 11:30:01 CST 2019
     */
    private Double wThTotal;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TDSTRTNAPPLYHEAD.XTAXTOTAL
     * 销项税额
     * @mbggenerated Mon Sep 16 11:30:01 CST 2019
     */
    private Double xTaxTotal;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TDSTRTNAPPLYHEAD.PLNTHDATE
     * 计划退货日期
     * @mbggenerated Mon Sep 16 11:30:01 CST 2019
     */
    private String plnThDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORDTHHEAD.WLAREACODE
     * 功能区域编码：用来满足指定功能区域出库的情况
     * @mbggenerated Thu Jul 11 16:42:43 CST 2019
     */
    private String wlAreaCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORDTHHEAD.WLAREANAME
     * 功能区域名称
     * @mbggenerated Thu Jul 11 16:42:43 CST 2019
     */
    private String wlAreaName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TDSTRTNAPPLYHEAD.STOTAL
     * 售价金额
     * @mbggenerated Mon Sep 16 11:30:01 CST 2019
     */
    private Double sTotal;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TDSTRTNAPPLYHEAD.SHTYPE
     * 送货方式
     * @mbggenerated Mon Sep 16 11:30:01 CST 2019
     */
    private String shType;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TDSTRTNAPPLYHEAD.CURRCODE
     * 结算币种
     * @mbggenerated Mon Sep 16 11:30:01 CST 2019
     */
    private String currCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TDSTRTNAPPLYHEAD.EXCHANGERATE
     * 汇率
     * @mbggenerated Mon Sep 16 11:30:01 CST 2019
     */
    private Double exchangeRate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TDSTRTNAPPLYHEAD.STLCURRTHTOTAL
     * 原币配送金额
     * @mbggenerated Mon Sep 16 11:30:01 CST 2019
     */
    private Double stlCurrThTotal;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TDSTRTNAPPLYHEAD.ISSTDCURR
     *
     * @mbggenerated Mon Sep 16 11:30:01 CST 2019
     */
    private String isstdcurr;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TDSTRTNAPPLYHEAD.FHBILLNO
     *
     * @mbggenerated Mon Sep 16 11:30:01 CST 2019
     */
    private String fhbillno;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TDSTRTNAPPLYHEAD.DATASRC
     *
     * @mbggenerated Mon Sep 16 11:30:01 CST 2019
     */
    private String datasrc;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TDSTRTNAPPLYHEAD.BILLSTATUS
     * 单据状态:0－已驳回；1－待确认；3－已确认
     * @mbggenerated Mon Sep 16 11:30:01 CST 2019
     */
    private String billStatus;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TDSTRTNAPPLYHEAD.ZBCHECKDATE
     *
     * @mbggenerated Mon Sep 16 11:30:01 CST 2019
     */
    private String zbcheckdate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TDSTRTNAPPLYHEAD.ZBCHECKRID
     *
     * @mbggenerated Mon Sep 16 11:30:01 CST 2019
     */
    private Double zbcheckrid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TDSTRTNAPPLYHEAD.ZBCHECKRCODE
     *
     * @mbggenerated Mon Sep 16 11:30:01 CST 2019
     */
    private String zbcheckrcode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TDSTRTNAPPLYHEAD.ZBCHECKRNAME
     *
     * @mbggenerated Mon Sep 16 11:30:01 CST 2019
     */
    private String zbcheckrname;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TDSTRTNAPPLYHEAD.WLCHECKDATE
     *
     * @mbggenerated Mon Sep 16 11:30:01 CST 2019
     */
    private String wlcheckdate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TDSTRTNAPPLYHEAD.WLCHECKRID
     *
     * @mbggenerated Mon Sep 16 11:30:01 CST 2019
     */
    private Double wlcheckrid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TDSTRTNAPPLYHEAD.WLCHECKRCODE
     *
     * @mbggenerated Mon Sep 16 11:30:01 CST 2019
     */
    private String wlcheckrcode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TDSTRTNAPPLYHEAD.WLCHECKRNAME
     *
     * @mbggenerated Mon Sep 16 11:30:01 CST 2019
     */
    private String wlcheckrname;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TDSTRTNAPPLYHEAD.ZBCHECKCOUNT
     * 总部审核数量
     * @mbggenerated Mon Sep 16 11:30:01 CST 2019
     */
    private Double zbCheckCount;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TDSTRTNAPPLYHEAD.WLCHECKCOUNT
     * 物流审核数量
     * @mbggenerated Mon Sep 16 11:30:01 CST 2019
     */
    private Double wlCheckCount;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TDSTRTNAPPLYHEAD.REMARK1
     *
     * @mbggenerated Mon Sep 16 11:30:01 CST 2019
     */
    private String remark1;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TDSTRTNAPPLYHEAD.REMARK2
     *
     * @mbggenerated Mon Sep 16 11:30:01 CST 2019
     */
    private String remark2;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TDSTRTNAPPLYHEAD.REMARK3
     *
     * @mbggenerated Mon Sep 16 11:30:01 CST 2019
     */
    private String remark3;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TDSTRTNAPPLYHEAD.ADLPSTHSQTYPE
     * 退货申请类型:0-非质量退货；1-质量退货
     * @mbggenerated Mon Sep 16 11:30:01 CST 2019
     */
    private String adlPsThSqType;
    
    private List<DstRtnApplyBodyModel> bodyList;

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
     * @return the lrDate
     */
    public String getLrDate() {
        return lrDate;
    }

    /**
     * @param lrDate the lrDate to set
     */
    public void setLrDate(String lrDate) {
        this.lrDate = lrDate;
    }

    /**
     * @return the userId
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * @param userId the userId to set
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * @return the userCode
     */
    public String getUserCode() {
        return userCode;
    }

    /**
     * @param userCode the userCode to set
     */
    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    /**
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName the userName to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * @return the genType
     */
    public String getGenType() {
        return genType;
    }

    /**
     * @param genType the genType to set
     */
    public void setGenType(String genType) {
        this.genType = genType;
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
     * @return the orgType
     */
    public String getOrgType() {
        return orgType;
    }

    /**
     * @param orgType the orgType to set
     */
    public void setOrgType(String orgType) {
        this.orgType = orgType;
    }

    /**
     * @return the thOrgCode
     */
    public String getThOrgCode() {
        return thOrgCode;
    }

    /**
     * @param thOrgCode the thOrgCode to set
     */
    public void setThOrgCode(String thOrgCode) {
        this.thOrgCode = thOrgCode;
    }

    /**
     * @return the thOrgnNme
     */
    public String getThOrgnNme() {
        return thOrgnNme;
    }

    /**
     * @param thOrgnNme the thOrgnNme to set
     */
    public void setThOrgnNme(String thOrgnNme) {
        this.thOrgnNme = thOrgnNme;
    }

    /**
     * @return the inThOrgCode
     */
    public String getInThOrgCode() {
        return inThOrgCode;
    }

    /**
     * @param inThOrgCode the inThOrgCode to set
     */
    public void setInThOrgCode(String inThOrgCode) {
        this.inThOrgCode = inThOrgCode;
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
     * @return the jzDate
     */
    public String getJzDate() {
        return jzDate;
    }

    /**
     * @param jzDate the jzDate to set
     */
    public void setJzDate(String jzDate) {
        this.jzDate = jzDate;
    }

    /**
     * @return the jzrId
     */
    public Long getJzrId() {
        return jzrId;
    }

    /**
     * @param jzrId the jzrId to set
     */
    public void setJzrId(Long jzrId) {
        this.jzrId = jzrId;
    }

    /**
     * @return the jzrCode
     */
    public String getJzrCode() {
        return jzrCode;
    }

    /**
     * @param jzrCode the jzrCode to set
     */
    public void setJzrCode(String jzrCode) {
        this.jzrCode = jzrCode;
    }

    /**
     * @return the jzrName
     */
    public String getJzrName() {
        return jzrName;
    }

    /**
     * @param jzrName the jzrName to set
     */
    public void setJzrName(String jzrName) {
        this.jzrName = jzrName;
    }

    /**
     * @return the rptDate
     */
    public String getRptDate() {
        return rptDate;
    }

    /**
     * @param rptDate the rptDate to set
     */
    public void setRptDate(String rptDate) {
        this.rptDate = rptDate;
    }

    /**
     * @return the prnTimes
     */
    public Long getPrnTimes() {
        return prnTimes;
    }

    /**
     * @param prnTimes the prnTimes to set
     */
    public void setPrnTimes(Long prnTimes) {
        this.prnTimes = prnTimes;
    }

    /**
     * @return the prnDate
     */
    public String getPrnDate() {
        return prnDate;
    }

    /**
     * @param prnDate the prnDate to set
     */
    public void setPrnDate(String prnDate) {
        this.prnDate = prnDate;
    }

    /**
     * @return the timeMark
     */
    public Long getTimeMark() {
        return timeMark;
    }

    /**
     * @param timeMark the timeMark to set
     */
    public void setTimeMark(Long timeMark) {
        this.timeMark = timeMark;
    }

    /**
     * @return the ywType
     */
    public String getYwType() {
        return ywType;
    }

    /**
     * @param ywType the ywType to set
     */
    public void setYwType(String ywType) {
        this.ywType = ywType;
    }

    /**
     * @return the ywIoType
     */
    public String getYwIoType() {
        return ywIoType;
    }

    /**
     * @param ywIoType the ywIoType to set
     */
    public void setYwIoType(String ywIoType) {
        this.ywIoType = ywIoType;
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
     * @return the jzType
     */
    public String getJzType() {
        return jzType;
    }

    /**
     * @param jzType the jzType to set
     */
    public void setJzType(String jzType) {
        this.jzType = jzType;
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
     * @return the wlAreaCode
     */
    public String getWlAreaCode() {
        return wlAreaCode;
    }

    /**
     * @param wlAreaCode the wlAreaCode to set
     */
    public void setWlAreaCode(String wlAreaCode) {
        this.wlAreaCode = wlAreaCode;
    }

    /**
     * @return the wlAreaName
     */
    public String getWlAreaName() {
        return wlAreaName;
    }

    /**
     * @param wlAreaName the wlAreaName to set
     */
    public void setWlAreaName(String wlAreaName) {
        this.wlAreaName = wlAreaName;
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
     * @return the shType
     */
    public String getShType() {
        return shType;
    }

    /**
     * @param shType the shType to set
     */
    public void setShType(String shType) {
        this.shType = shType;
    }

    /**
     * @return the currCode
     */
    public String getCurrCode() {
        return currCode;
    }

    /**
     * @param currCode the currCode to set
     */
    public void setCurrCode(String currCode) {
        this.currCode = currCode;
    }

    /**
     * @return the exchangeRate
     */
    public Double getExchangeRate() {
        return exchangeRate;
    }

    /**
     * @param exchangeRate the exchangeRate to set
     */
    public void setExchangeRate(Double exchangeRate) {
        this.exchangeRate = exchangeRate;
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
     * @return the isstdcurr
     */
    public String getIsstdcurr() {
        return isstdcurr;
    }

    /**
     * @param isstdcurr the isstdcurr to set
     */
    public void setIsstdcurr(String isstdcurr) {
        this.isstdcurr = isstdcurr;
    }

    /**
     * @return the fhbillno
     */
    public String getFhbillno() {
        return fhbillno;
    }

    /**
     * @param fhbillno the fhbillno to set
     */
    public void setFhbillno(String fhbillno) {
        this.fhbillno = fhbillno;
    }

    /**
     * @return the datasrc
     */
    public String getDatasrc() {
        return datasrc;
    }

    /**
     * @param datasrc the datasrc to set
     */
    public void setDatasrc(String datasrc) {
        this.datasrc = datasrc;
    }

    /**
     * @return the billStatus
     */
    public String getBillStatus() {
        return billStatus;
    }

    /**
     * @param billStatus the billStatus to set
     */
    public void setBillStatus(String billStatus) {
        this.billStatus = billStatus;
    }

    /**
     * @return the zbcheckdate
     */
    public String getZbcheckdate() {
        return zbcheckdate;
    }

    /**
     * @param zbcheckdate the zbcheckdate to set
     */
    public void setZbcheckdate(String zbcheckdate) {
        this.zbcheckdate = zbcheckdate;
    }

    /**
     * @return the zbcheckrid
     */
    public Double getZbcheckrid() {
        return zbcheckrid;
    }

    /**
     * @param zbcheckrid the zbcheckrid to set
     */
    public void setZbcheckrid(Double zbcheckrid) {
        this.zbcheckrid = zbcheckrid;
    }

    /**
     * @return the zbcheckrcode
     */
    public String getZbcheckrcode() {
        return zbcheckrcode;
    }

    /**
     * @param zbcheckrcode the zbcheckrcode to set
     */
    public void setZbcheckrcode(String zbcheckrcode) {
        this.zbcheckrcode = zbcheckrcode;
    }

    /**
     * @return the zbcheckrname
     */
    public String getZbcheckrname() {
        return zbcheckrname;
    }

    /**
     * @param zbcheckrname the zbcheckrname to set
     */
    public void setZbcheckrname(String zbcheckrname) {
        this.zbcheckrname = zbcheckrname;
    }

    /**
     * @return the wlcheckdate
     */
    public String getWlcheckdate() {
        return wlcheckdate;
    }

    /**
     * @param wlcheckdate the wlcheckdate to set
     */
    public void setWlcheckdate(String wlcheckdate) {
        this.wlcheckdate = wlcheckdate;
    }

    /**
     * @return the wlcheckrid
     */
    public Double getWlcheckrid() {
        return wlcheckrid;
    }

    /**
     * @param wlcheckrid the wlcheckrid to set
     */
    public void setWlcheckrid(Double wlcheckrid) {
        this.wlcheckrid = wlcheckrid;
    }

    /**
     * @return the wlcheckrcode
     */
    public String getWlcheckrcode() {
        return wlcheckrcode;
    }

    /**
     * @param wlcheckrcode the wlcheckrcode to set
     */
    public void setWlcheckrcode(String wlcheckrcode) {
        this.wlcheckrcode = wlcheckrcode;
    }

    /**
     * @return the wlcheckrname
     */
    public String getWlcheckrname() {
        return wlcheckrname;
    }

    /**
     * @param wlcheckrname the wlcheckrname to set
     */
    public void setWlcheckrname(String wlcheckrname) {
        this.wlcheckrname = wlcheckrname;
    }

    /**
     * @return the zbCheckCount
     */
    public Double getZbCheckCount() {
        return zbCheckCount;
    }

    /**
     * @param zbCheckCount the zbCheckCount to set
     */
    public void setZbCheckCount(Double zbCheckCount) {
        this.zbCheckCount = zbCheckCount;
    }

    /**
     * @return the wlCheckCount
     */
    public Double getWlCheckCount() {
        return wlCheckCount;
    }

    /**
     * @param wlCheckCount the wlCheckCount to set
     */
    public void setWlCheckCount(Double wlCheckCount) {
        this.wlCheckCount = wlCheckCount;
    }

    /**
     * @return the remark1
     */
    public String getRemark1() {
        return remark1;
    }

    /**
     * @param remark1 the remark1 to set
     */
    public void setRemark1(String remark1) {
        this.remark1 = remark1;
    }

    /**
     * @return the remark2
     */
    public String getRemark2() {
        return remark2;
    }

    /**
     * @param remark2 the remark2 to set
     */
    public void setRemark2(String remark2) {
        this.remark2 = remark2;
    }

    /**
     * @return the remark3
     */
    public String getRemark3() {
        return remark3;
    }

    /**
     * @param remark3 the remark3 to set
     */
    public void setRemark3(String remark3) {
        this.remark3 = remark3;
    }

    /**
     * @return the adlPsThSqType
     */
    public String getAdlPsThSqType() {
        return adlPsThSqType;
    }

    /**
     * @param adlPsThSqType the adlPsThSqType to set
     */
    public void setAdlPsThSqType(String adlPsThSqType) {
        this.adlPsThSqType = adlPsThSqType;
    }

    /**
     * @return the bodyList
     */
    public List<DstRtnApplyBodyModel> getBodyList() {
        return bodyList;
    }

    /**
     * @param bodyList the bodyList to set
     */
    public void setBodyList(List<DstRtnApplyBodyModel> bodyList) {
        this.bodyList = bodyList;
    }
}
