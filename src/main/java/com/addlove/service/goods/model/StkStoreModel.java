package com.addlove.service.goods.model;

/**
 * 连锁仓库表
 * @author lw
 *
 */
public class StkStoreModel {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TSTKSTORE.ORGCODE
     * 所属组织编码
     * @mbggenerated Fri Jun 21 16:04:19 CST 2019
     */
    private String orgCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TSTKSTORE.ATORGCODE
     * 所在组织编码
     * @mbggenerated Fri Jun 21 16:04:19 CST 2019
     */
    private String atOrgCode;
    
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TSTKSTORE.CKCODE
     * 仓库编码
     * @mbggenerated Fri Jun 21 16:04:19 CST 2019
     */
    private String ckCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TSTKSTORE.CKNAME
     * 仓库名称
     * @mbggenerated Fri Jun 21 16:04:19 CST 2019
     */
    private String ckName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TSTKSTORE.CKENAME
     * 仓库英文名称
     * @mbggenerated Fri Jun 21 16:04:19 CST 2019
     */
    private String ckEName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TSTKSTORE.CKAREA
     * 仓库面积
     * @mbggenerated Fri Jun 21 16:04:19 CST 2019
     */
    private Double ckArea;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TSTKSTORE.CKADDRESS
     * 详细地址
     * @mbggenerated Fri Jun 21 16:04:19 CST 2019
     */
    private String ckAddress;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TSTKSTORE.CKTYPE
     * 仓库性质：0-卖场；1-门店仓库；2-物流逻辑库
     * @mbggenerated Fri Jun 21 16:04:19 CST 2019
     */
    private String ckType;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TSTKSTORE.ISMBZQ
     * 是否管理保质期：0-否；1-是
     * @mbggenerated Fri Jun 21 16:04:19 CST 2019
     */
    private String isMbzq;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TSTKSTORE.EMPNAME
     * 负责人
     * @mbggenerated Fri Jun 21 16:04:19 CST 2019
     */
    private String empName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TSTKSTORE.TELEPHONE
     * 电话
     * @mbggenerated Fri Jun 21 16:04:19 CST 2019
     */
    private String telephone;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TSTKSTORE.REMARK
     * 备注
     * @mbggenerated Fri Jun 21 16:04:19 CST 2019
     */
    private String remark;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TSTKSTORE.KCTYPE
     * 库存类型：0-正品库存;3-不良品库存；采购平台使用，类型为3的不良品库存不在采购平台库存统计范围之内
     * @mbggenerated Fri Jun 21 16:04:19 CST 2019
     */
    private String kcType;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TSTKSTORE.AREACTRL
     * 控制存储范围：0-否；1-是；
     * @mbggenerated Fri Jun 21 16:04:19 CST 2019
     */
    private String areaCtrl;

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
     * @return the atOrgCode
     */
    public String getAtOrgCode() {
        return atOrgCode;
    }

    /**
     * @param atOrgCode the atOrgCode to set
     */
    public void setAtOrgCode(String atOrgCode) {
        this.atOrgCode = atOrgCode;
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
     * @return the ckEName
     */
    public String getCkEName() {
        return ckEName;
    }

    /**
     * @param ckEName the ckEName to set
     */
    public void setCkEName(String ckEName) {
        this.ckEName = ckEName;
    }

    /**
     * @return the ckArea
     */
    public Double getCkArea() {
        return ckArea;
    }

    /**
     * @param ckArea the ckArea to set
     */
    public void setCkArea(Double ckArea) {
        this.ckArea = ckArea;
    }

    /**
     * @return the ckAddress
     */
    public String getCkAddress() {
        return ckAddress;
    }

    /**
     * @param ckAddress the ckAddress to set
     */
    public void setCkAddress(String ckAddress) {
        this.ckAddress = ckAddress;
    }

    /**
     * @return the ckType
     */
    public String getCkType() {
        return ckType;
    }

    /**
     * @param ckType the ckType to set
     */
    public void setCkType(String ckType) {
        this.ckType = ckType;
    }

    /**
     * @return the isMbzq
     */
    public String getIsMbzq() {
        return isMbzq;
    }

    /**
     * @param isMbzq the isMbzq to set
     */
    public void setIsMbzq(String isMbzq) {
        this.isMbzq = isMbzq;
    }

    /**
     * @return the empName
     */
    public String getEmpName() {
        return empName;
    }

    /**
     * @param empName the empName to set
     */
    public void setEmpName(String empName) {
        this.empName = empName;
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
     * @return the kcType
     */
    public String getKcType() {
        return kcType;
    }

    /**
     * @param kcType the kcType to set
     */
    public void setKcType(String kcType) {
        this.kcType = kcType;
    }

    /**
     * @return the areaCtrl
     */
    public String getAreaCtrl() {
        return areaCtrl;
    }

    /**
     * @param areaCtrl the areaCtrl to set
     */
    public void setAreaCtrl(String areaCtrl) {
        this.areaCtrl = areaCtrl;
    }
}
