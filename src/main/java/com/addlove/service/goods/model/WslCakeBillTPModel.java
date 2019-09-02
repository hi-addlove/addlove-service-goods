package com.addlove.service.goods.model;

import java.util.List;

/**
 * 生日蛋糕加工单主表
 * @author lw
 *
 */
public class WslCakeBillTPModel {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORDJHHEAD.BILLNO
     * 单据号
     * @mbggenerated Tue Jun 04 15:32:25 CST 2019
     */
    private String billNo;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TWSLCAKEBILL_TOPOS.SAPBILLNO
     * SAP单据号
     * @mbggenerated Mon Sep 02 11:23:47 CST 2019
     */
    private String sapBillNo;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TWSLCAKEBILL_TOPOS.POSNO
     * 收款机号
     * @mbggenerated Mon Sep 02 11:23:47 CST 2019
     */
    private String posNo;

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
     * This field corresponds to the database column TWSLCAKEBILL_TOPOS.ORDTYPE
     * 订单类型:D01散客生日蛋糕;D02门店备货订单;D03大客户生日蛋糕
     * @mbggenerated Mon Sep 02 11:23:47 CST 2019
     */
    private String ordType;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TWSLCAKEBILL_TOPOS.TRADEDATE
     * 交易日期
     * @mbggenerated Mon Sep 02 11:23:47 CST 2019
     */
    private String tradeDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TWSLCAKEBILL_TOPOS.ORDDATE
     * 订购日期
     * @mbggenerated Mon Sep 02 11:23:47 CST 2019
     */
    private String ordDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TWSLCAKEBILL_TOPOS.ORDTIME
     * 订购时间
     * @mbggenerated Mon Sep 02 11:23:47 CST 2019
     */
    private String ordTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TWSLCAKEBILL_TOPOS.CREATDATE
     * 创建日期
     * @mbggenerated Mon Sep 02 11:23:47 CST 2019
     */
    private String creatDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TWSLCAKEBILL_TOPOS.CREATTIME
     * 创建时间
     * @mbggenerated Mon Sep 02 11:23:47 CST 2019
     */
    private String creatTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TWSLCAKEBILL_TOPOS.MODIFYDATE
     * 修改日期
     * @mbggenerated Mon Sep 02 11:23:47 CST 2019
     */
    private String modifyDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TWSLCAKEBILL_TOPOS.MODIFYTIME
     * 修改时间
     * @mbggenerated Mon Sep 02 11:23:47 CST 2019
     */
    private String modifyTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TWSLCAKEBILL_TOPOS.CUSTOMNAME
     * 顾客姓名
     * @mbggenerated Mon Sep 02 11:23:47 CST 2019
     */
    private String customName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TWSLCAKEBILL_TOPOS.CUSTOMMOBILE
     * 顾客手机
     * @mbggenerated Mon Sep 02 11:23:47 CST 2019
     */
    private String customMobile;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TWSLCAKEBILL_TOPOS.CUSTOMTEL
     * 顾客电话
     * @mbggenerated Mon Sep 02 11:23:47 CST 2019
     */
    private String customTel;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TWSLCAKEBILL_TOPOS.ORDSTATUS
     * 10下单；11受理（SAP受理）；12已分配生产地点；13生产（开始生产）；14配送（生产入库并发货）；15已提货；20作废
     * @mbggenerated Mon Sep 02 11:23:47 CST 2019
     */
    private String ordStatus;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TWSLCAKEBILL_TOPOS.JGORGCODE
     * 生产门店
     * @mbggenerated Mon Sep 02 11:23:47 CST 2019
     */
    private String jgOrgCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TWSLCAKEBILL_TOPOS.JGORGNAME
     * 生产门店名称
     * @mbggenerated Mon Sep 02 11:23:47 CST 2019
     */
    private String jgOrgName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TWSLCAKEBILL_TOPOS.SAPJGORGCODE
     * SAP生产门店
     * @mbggenerated Mon Sep 02 11:23:47 CST 2019
     */
    private String sapJgOrgCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TWSLCAKEBILL_TOPOS.SAPJGORGNAME
     * SAP生产门店名称
     * @mbggenerated Mon Sep 02 11:23:47 CST 2019
     */
    private String sapJgOrgName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TWSLCAKEBILL_TOPOS.ISMULTI
     * 是否多层：0-否；1-是
     * @mbggenerated Mon Sep 02 11:23:47 CST 2019
     */
    private String isMulti;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TWSLCAKEBILL_TOPOS.YJTOTAL
     * 押金金额
     * @mbggenerated Mon Sep 02 11:23:47 CST 2019
     */
    private Double yjTotal;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TWSLCAKEBILL_TOPOS.DJDATE
     * 定价日期
     * @mbggenerated Mon Sep 02 11:23:47 CST 2019
     */
    private String djDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TWSLCAKEBILL_TOPOS.THDATE
     * 提货日期
     * @mbggenerated Mon Sep 02 11:23:47 CST 2019
     */
    private String thDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TWSLCAKEBILL_TOPOS.THTIME
     * 提货时间
     * @mbggenerated Mon Sep 02 11:23:47 CST 2019
     */
    private String thTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TWSLCAKEBILL_TOPOS.THMODE
     * 提货方式：C01门店提货；C02送货到府
     * @mbggenerated Mon Sep 02 11:23:47 CST 2019
     */
    private String thMode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TWSLCAKEBILL_TOPOS.THORGCODE
     * 提货门店
     * @mbggenerated Mon Sep 02 11:23:47 CST 2019
     */
    private String thOrgCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TWSLCAKEBILL_TOPOS.ADDRESS
     * 送货地址
     * @mbggenerated Mon Sep 02 11:23:47 CST 2019
     */
    private String address;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TWSLCAKEBILL_TOPOS.REMARK
     * 备注
     * @mbggenerated Mon Sep 02 11:23:47 CST 2019
     */
    private String remark;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TWSLCAKEBILL_TOPOS.POSUSERCODE
     * 操作人编码
     * @mbggenerated Mon Sep 02 11:23:47 CST 2019
     */
    private String posUserCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TWSLCAKEBILL_TOPOS.POSUSERNAME
     * 操作人名称
     * @mbggenerated Mon Sep 02 11:23:47 CST 2019
     */
    private String posUserName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TWSLCAKEBILL_TOPOS.ORDTOTAL
     * 订单金额
     * @mbggenerated Mon Sep 02 11:23:47 CST 2019
     */
    private Double ordTotal;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TWSLCAKEBILL_TOPOS.TOTALDSC
     * 折扣金额
     * @mbggenerated Mon Sep 02 11:23:47 CST 2019
     */
    private Double totalDsc;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TWSLCAKEBILL_TOPOS.FKTOTAL
     * 应付金额
     * @mbggenerated Mon Sep 02 11:23:47 CST 2019
     */
    private Double fkTotal;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TWSLCAKEBILL_TOPOS.YFKTOTAL
     * 已付金额
     * @mbggenerated Mon Sep 02 11:23:47 CST 2019
     */
    private Double yfkTotal;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TWSLCAKEBILL_TOPOS.WFKTOTAL
     * 未付款金额
     * @mbggenerated Mon Sep 02 11:23:47 CST 2019
     */
    private Double wfkTotal;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TWSLCAKEBILL_TOPOS.TOTALPAY
     * 实付合计
     * @mbggenerated Mon Sep 02 11:23:47 CST 2019
     */
    private Double totalpay;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TWSLCAKEBILL_TOPOS.CHANGE
     * 找零
     * @mbggenerated Mon Sep 02 11:23:47 CST 2019
     */
    private Double change;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TWSLCAKEBILL_TOPOS.COMSERIALNO
     *
     * @mbggenerated Mon Sep 02 11:23:47 CST 2019
     */
    private Double comserialNo;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TWSLCAKEBILL_TOPOS.ISRTNED
     * 退货标记
     * @mbggenerated Mon Sep 02 11:23:47 CST 2019
     */
    private String isRtned;

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
     * This field corresponds to the database column TWSLCAKEBILL_TOPOS.POSTDDATE
     * 门店退货日期
     * @mbggenerated Mon Sep 02 11:23:47 CST 2019
     */
    private String postdDate;
    
    private List<WslCakeBillPluTPModel> bodyList;

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
     * @return the sapBillNo
     */
    public String getSapBillNo() {
        return sapBillNo;
    }

    /**
     * @param sapBillNo the sapBillNo to set
     */
    public void setSapBillNo(String sapBillNo) {
        this.sapBillNo = sapBillNo;
    }

    /**
     * @return the posNo
     */
    public String getPosNo() {
        return posNo;
    }

    /**
     * @param posNo the posNo to set
     */
    public void setPosNo(String posNo) {
        this.posNo = posNo;
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
     * @return the ordType
     */
    public String getOrdType() {
        return ordType;
    }

    /**
     * @param ordType the ordType to set
     */
    public void setOrdType(String ordType) {
        this.ordType = ordType;
    }

    /**
     * @return the tradeDate
     */
    public String getTradeDate() {
        return tradeDate;
    }

    /**
     * @param tradeDate the tradeDate to set
     */
    public void setTradeDate(String tradeDate) {
        this.tradeDate = tradeDate;
    }

    /**
     * @return the ordDate
     */
    public String getOrdDate() {
        return ordDate;
    }

    /**
     * @param ordDate the ordDate to set
     */
    public void setOrdDate(String ordDate) {
        this.ordDate = ordDate;
    }

    /**
     * @return the ordTime
     */
    public String getOrdTime() {
        return ordTime;
    }

    /**
     * @param ordTime the ordTime to set
     */
    public void setOrdTime(String ordTime) {
        this.ordTime = ordTime;
    }

    /**
     * @return the creatDate
     */
    public String getCreatDate() {
        return creatDate;
    }

    /**
     * @param creatDate the creatDate to set
     */
    public void setCreatDate(String creatDate) {
        this.creatDate = creatDate;
    }

    /**
     * @return the creatTime
     */
    public String getCreatTime() {
        return creatTime;
    }

    /**
     * @param creatTime the creatTime to set
     */
    public void setCreatTime(String creatTime) {
        this.creatTime = creatTime;
    }

    /**
     * @return the modifyDate
     */
    public String getModifyDate() {
        return modifyDate;
    }

    /**
     * @param modifyDate the modifyDate to set
     */
    public void setModifyDate(String modifyDate) {
        this.modifyDate = modifyDate;
    }

    /**
     * @return the modifyTime
     */
    public String getModifyTime() {
        return modifyTime;
    }

    /**
     * @param modifyTime the modifyTime to set
     */
    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime;
    }

    /**
     * @return the customName
     */
    public String getCustomName() {
        return customName;
    }

    /**
     * @param customName the customName to set
     */
    public void setCustomName(String customName) {
        this.customName = customName;
    }

    /**
     * @return the customMobile
     */
    public String getCustomMobile() {
        return customMobile;
    }

    /**
     * @param customMobile the customMobile to set
     */
    public void setCustomMobile(String customMobile) {
        this.customMobile = customMobile;
    }

    /**
     * @return the customTel
     */
    public String getCustomTel() {
        return customTel;
    }

    /**
     * @param customTel the customTel to set
     */
    public void setCustomTel(String customTel) {
        this.customTel = customTel;
    }

    /**
     * @return the ordStatus
     */
    public String getOrdStatus() {
        return ordStatus;
    }

    /**
     * @param ordStatus the ordStatus to set
     */
    public void setOrdStatus(String ordStatus) {
        this.ordStatus = ordStatus;
    }

    /**
     * @return the jgOrgCode
     */
    public String getJgOrgCode() {
        return jgOrgCode;
    }

    /**
     * @param jgOrgCode the jgOrgCode to set
     */
    public void setJgOrgCode(String jgOrgCode) {
        this.jgOrgCode = jgOrgCode;
    }

    /**
     * @return the jgOrgName
     */
    public String getJgOrgName() {
        return jgOrgName;
    }

    /**
     * @param jgOrgName the jgOrgName to set
     */
    public void setJgOrgName(String jgOrgName) {
        this.jgOrgName = jgOrgName;
    }

    /**
     * @return the sapJgOrgCode
     */
    public String getSapJgOrgCode() {
        return sapJgOrgCode;
    }

    /**
     * @param sapJgOrgCode the sapJgOrgCode to set
     */
    public void setSapJgOrgCode(String sapJgOrgCode) {
        this.sapJgOrgCode = sapJgOrgCode;
    }

    /**
     * @return the sapJgOrgName
     */
    public String getSapJgOrgName() {
        return sapJgOrgName;
    }

    /**
     * @param sapJgOrgName the sapJgOrgName to set
     */
    public void setSapJgOrgName(String sapJgOrgName) {
        this.sapJgOrgName = sapJgOrgName;
    }

    /**
     * @return the isMulti
     */
    public String getIsMulti() {
        return isMulti;
    }

    /**
     * @param isMulti the isMulti to set
     */
    public void setIsMulti(String isMulti) {
        this.isMulti = isMulti;
    }

    /**
     * @return the yjTotal
     */
    public Double getYjTotal() {
        return yjTotal;
    }

    /**
     * @param yjTotal the yjTotal to set
     */
    public void setYjTotal(Double yjTotal) {
        this.yjTotal = yjTotal;
    }

    /**
     * @return the djDate
     */
    public String getDjDate() {
        return djDate;
    }

    /**
     * @param djDate the djDate to set
     */
    public void setDjDate(String djDate) {
        this.djDate = djDate;
    }

    /**
     * @return the thDate
     */
    public String getThDate() {
        return thDate;
    }

    /**
     * @param thDate the thDate to set
     */
    public void setThDate(String thDate) {
        this.thDate = thDate;
    }

    /**
     * @return the thTime
     */
    public String getThTime() {
        return thTime;
    }

    /**
     * @param thTime the thTime to set
     */
    public void setThTime(String thTime) {
        this.thTime = thTime;
    }

    /**
     * @return the thMode
     */
    public String getThMode() {
        return thMode;
    }

    /**
     * @param thMode the thMode to set
     */
    public void setThMode(String thMode) {
        this.thMode = thMode;
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
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
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
     * @return the posUserCode
     */
    public String getPosUserCode() {
        return posUserCode;
    }

    /**
     * @param posUserCode the posUserCode to set
     */
    public void setPosUserCode(String posUserCode) {
        this.posUserCode = posUserCode;
    }

    /**
     * @return the posUserName
     */
    public String getPosUserName() {
        return posUserName;
    }

    /**
     * @param posUserName the posUserName to set
     */
    public void setPosUserName(String posUserName) {
        this.posUserName = posUserName;
    }

    /**
     * @return the ordTotal
     */
    public Double getOrdTotal() {
        return ordTotal;
    }

    /**
     * @param ordTotal the ordTotal to set
     */
    public void setOrdTotal(Double ordTotal) {
        this.ordTotal = ordTotal;
    }

    /**
     * @return the totalDsc
     */
    public Double getTotalDsc() {
        return totalDsc;
    }

    /**
     * @param totalDsc the totalDsc to set
     */
    public void setTotalDsc(Double totalDsc) {
        this.totalDsc = totalDsc;
    }

    /**
     * @return the fkTotal
     */
    public Double getFkTotal() {
        return fkTotal;
    }

    /**
     * @param fkTotal the fkTotal to set
     */
    public void setFkTotal(Double fkTotal) {
        this.fkTotal = fkTotal;
    }

    /**
     * @return the yfkTotal
     */
    public Double getYfkTotal() {
        return yfkTotal;
    }

    /**
     * @param yfkTotal the yfkTotal to set
     */
    public void setYfkTotal(Double yfkTotal) {
        this.yfkTotal = yfkTotal;
    }

    /**
     * @return the wfkTotal
     */
    public Double getWfkTotal() {
        return wfkTotal;
    }

    /**
     * @param wfkTotal the wfkTotal to set
     */
    public void setWfkTotal(Double wfkTotal) {
        this.wfkTotal = wfkTotal;
    }

    /**
     * @return the totalpay
     */
    public Double getTotalpay() {
        return totalpay;
    }

    /**
     * @param totalpay the totalpay to set
     */
    public void setTotalpay(Double totalpay) {
        this.totalpay = totalpay;
    }

    /**
     * @return the change
     */
    public Double getChange() {
        return change;
    }

    /**
     * @param change the change to set
     */
    public void setChange(Double change) {
        this.change = change;
    }

    /**
     * @return the comserialNo
     */
    public Double getComserialNo() {
        return comserialNo;
    }

    /**
     * @param comserialNo the comserialNo to set
     */
    public void setComserialNo(Double comserialNo) {
        this.comserialNo = comserialNo;
    }

    /**
     * @return the isRtned
     */
    public String getIsRtned() {
        return isRtned;
    }

    /**
     * @param isRtned the isRtned to set
     */
    public void setIsRtned(String isRtned) {
        this.isRtned = isRtned;
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
     * @return the postdDate
     */
    public String getPostdDate() {
        return postdDate;
    }

    /**
     * @param postdDate the postdDate to set
     */
    public void setPostdDate(String postdDate) {
        this.postdDate = postdDate;
    }

    /**
     * @return the bodyList
     */
    public List<WslCakeBillPluTPModel> getBodyList() {
        return bodyList;
    }

    /**
     * @param bodyList the bodyList to set
     */
    public void setBodyList(List<WslCakeBillPluTPModel> bodyList) {
        this.bodyList = bodyList;
    }
}