package com.addlove.service.goods.model;

import java.util.List;

/**
 * 加工单主表
 * @author lw
 *
 */
public class FrsJgHeadModel {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORDJHHEAD.BILLNO
     * 单据号
     * @mbggenerated Tue Jun 04 15:32:25 CST 2019
     */
    private String billNo;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORDJHHEAD.LRDATE
     * 录入日期
     * @mbggenerated Tue Jun 04 15:32:25 CST 2019
     */
    private String lrDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORDJHHEAD.USERID
     * 录入人ID
     * @mbggenerated Tue Jun 04 15:32:25 CST 2019
     */
    private Long userId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORDJHHEAD.USERCODE
     * 录入人编码
     * @mbggenerated Tue Jun 04 15:32:25 CST 2019
     */
    private String userCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORDJHHEAD.USERNAME
     * 录入人姓名
     * @mbggenerated Tue Jun 04 15:32:25 CST 2019
     */
    private String userName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORDJHHEAD.JZDATE
     * 记账日期
     * @mbggenerated Tue Jun 04 15:32:25 CST 2019
     */
    private String jzDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORDJHHEAD.JZRID
     * 记账人ID
     * @mbggenerated Tue Jun 04 15:32:25 CST 2019
     */
    private Long jzrId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORDJHHEAD.JZRCODE
     * 记账人编码
     * @mbggenerated Tue Jun 04 15:32:25 CST 2019
     */
    private String jzrCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORDJHHEAD.JZRNAME
     * 记账人姓名
     * @mbggenerated Tue Jun 04 15:32:25 CST 2019
     */
    private String jzrName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORDJHHEAD.JZTYPE
     * 记账类型：0-手工 1-系统。默认为0
     * @mbggenerated Tue Jun 04 15:32:25 CST 2019
     */
    private String jzType;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORDJHHEAD.RPTDATE
     * 报表日期 
     * @mbggenerated Tue Jun 04 15:32:25 CST 2019
     */
    private String rptDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORDJHHEAD.PRNTIMES
     * 打印次数
     * @mbggenerated Tue Jun 04 15:32:25 CST 2019
     */
    private Integer prnTimes;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORDJHHEAD.PRNDATE
     * 打印时间
     * @mbggenerated Tue Jun 04 15:32:25 CST 2019
     */
    private String prnDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORDJHHEAD.TIMEMARK
     * 时间戳
     * @mbggenerated Tue Jun 04 15:32:25 CST 2019
     */
    private Long timeMark;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORDCDADLYHHEAD.YWTYPE
     * 业务类型:1401
     * @mbggenerated Mon Aug 19 13:44:21 CST 2019
     */
    private String ywType;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORDCDADLYHHEAD.YWIOTYPE
     * 内外部业务类型:0-内部业务；1-外部业务
     * @mbggenerated Mon Aug 19 13:44:21 CST 2019
     */
    private String ywIoType;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORDTHHEAD.ZDRID
     * 制单人Id
     * @mbggenerated Thu Jul 11 16:42:43 CST 2019
     */
    private Long zdrId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORDTHHEAD.ZDRCODE
     * 制单人编码
     * @mbggenerated Thu Jul 11 16:42:43 CST 2019
     */
    private String zdrCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORDTHHEAD.ZDRNAME
     * 制单人姓名
     * @mbggenerated Thu Jul 11 16:42:43 CST 2019
     */
    private String zdrName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORDJHHEAD.ORGCODE
     * 组织编码
     * @mbggenerated Tue Jun 04 15:32:25 CST 2019
     */
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
     * This field corresponds to the database column TFRSJGHEAD.GYCODE
     * 加工工艺编码
     * @mbggenerated Mon Aug 26 10:46:06 CST 2019
     */
    private String gyCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TFRSJGHEAD.GYNAME
     * 加工工艺名称
     * @mbggenerated Mon Aug 26 10:46:06 CST 2019
     */
    private String gyName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TFRSJGHEAD.YLCOUNT
     * 原料数量
     * @mbggenerated Mon Aug 26 10:46:06 CST 2019
     */
    private Double ylCount;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TFRSJGHEAD.YLHCOST
     * 原料含税进价金额
     * @mbggenerated Mon Aug 26 10:46:06 CST 2019
     */
    private Double ylhCost;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TFRSJGHEAD.YLWCOST
     * 原料无税进价金额
     * @mbggenerated Mon Aug 26 10:46:06 CST 2019
     */
    private Double ylwCost;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TFRSJGHEAD.YLTOTAL
     * 原料售价金额
     * @mbggenerated Mon Aug 26 10:46:06 CST 2019
     */
    private Double ylTotal;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TFRSJGHEAD.CPCOUNT
     * 成品数量
     * @mbggenerated Mon Aug 26 10:46:06 CST 2019
     */
    private Double cpCount;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TFRSJGHEAD.CPHCOST
     * 成品含税进价金额
     * @mbggenerated Mon Aug 26 10:46:06 CST 2019
     */
    private Double cphCost;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TFRSJGHEAD.CPWCOST
     * 成品无税进价金额
     * @mbggenerated Mon Aug 26 10:46:06 CST 2019
     */
    private Double cpwCost;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TFRSJGHEAD.CPTOTAL
     * 成品售价金额
     * @mbggenerated Mon Aug 26 10:46:06 CST 2019
     */
    private Double cpTotal;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TFRSJGHEAD.FLCOST
     * 辅料成本
     * @mbggenerated Mon Aug 26 10:46:06 CST 2019
     */
    private Double flCost;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TFRSJGHEAD.REMARK
     * 备注
     * @mbggenerated Mon Aug 26 10:46:06 CST 2019
     */
    private String remark;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORDJHHEAD.DATASTATUS
     * 数据状态：0－录入；1－审批；2－转审；3－提交；4-可记账；9-关闭
     * @mbggenerated Tue Jun 04 15:32:25 CST 2019
     */
    private String dataStatus;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORDJHHEAD.REFBILLTYPE
     * 相关单据类型
     * @mbggenerated Tue Jun 04 15:32:25 CST 2019
     */
    private String refBillType;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORDJHHEAD.REFBILLNO
     * 相关单据号
     * @mbggenerated Tue Jun 04 15:32:25 CST 2019
     */
    private String refBillNo;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORDJHHEAD.CKCODE
     * 仓库编码
     * @mbggenerated Tue Jun 04 15:32:25 CST 2019
     */
    private String ckCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORDJHHEAD.CKNAME
     * 仓库名称
     * @mbggenerated Tue Jun 04 15:32:25 CST 2019
     */
    private String ckName;
    
    /**
     * 预料集合
     */
    private List<FrsJgYlModel> ylList;
    
    /**
     * 成品集合
     */
    private List<FrsJgCpModel> cpList;

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
    public Integer getPrnTimes() {
        return prnTimes;
    }

    /**
     * @param prnTimes the prnTimes to set
     */
    public void setPrnTimes(Integer prnTimes) {
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
     * @return the zdrId
     */
    public Long getZdrId() {
        return zdrId;
    }

    /**
     * @param zdrId the zdrId to set
     */
    public void setZdrId(Long zdrId) {
        this.zdrId = zdrId;
    }

    /**
     * @return the zdrCode
     */
    public String getZdrCode() {
        return zdrCode;
    }

    /**
     * @param zdrCode the zdrCode to set
     */
    public void setZdrCode(String zdrCode) {
        this.zdrCode = zdrCode;
    }

    /**
     * @return the zdrName
     */
    public String getZdrName() {
        return zdrName;
    }

    /**
     * @param zdrName the zdrName to set
     */
    public void setZdrName(String zdrName) {
        this.zdrName = zdrName;
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
     * @return the gyCode
     */
    public String getGyCode() {
        return gyCode;
    }

    /**
     * @param gyCode the gyCode to set
     */
    public void setGyCode(String gyCode) {
        this.gyCode = gyCode;
    }

    /**
     * @return the gyName
     */
    public String getGyName() {
        return gyName;
    }

    /**
     * @param gyName the gyName to set
     */
    public void setGyName(String gyName) {
        this.gyName = gyName;
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
     * @return the flCost
     */
    public Double getFlCost() {
        return flCost;
    }

    /**
     * @param flCost the flCost to set
     */
    public void setFlCost(Double flCost) {
        this.flCost = flCost;
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
     * @return the refBillType
     */
    public String getRefBillType() {
        return refBillType;
    }

    /**
     * @param refBillType the refBillType to set
     */
    public void setRefBillType(String refBillType) {
        this.refBillType = refBillType;
    }

    /**
     * @return the refBillNo
     */
    public String getRefBillNo() {
        return refBillNo;
    }

    /**
     * @param refBillNo the refBillNo to set
     */
    public void setRefBillNo(String refBillNo) {
        this.refBillNo = refBillNo;
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
     * @return the ylList
     */
    public List<FrsJgYlModel> getYlList() {
        return ylList;
    }

    /**
     * @param ylList the ylList to set
     */
    public void setYlList(List<FrsJgYlModel> ylList) {
        this.ylList = ylList;
    }

    /**
     * @return the cpList
     */
    public List<FrsJgCpModel> getCpList() {
        return cpList;
    }

    /**
     * @param cpList the cpList to set
     */
    public void setCpList(List<FrsJgCpModel> cpList) {
        this.cpList = cpList;
    }
}