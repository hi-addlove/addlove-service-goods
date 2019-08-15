package com.addlove.service.goods.model;

/**
 * 分类类型内容表
 * @author lw
 *
 */
public class BasFlContentModel {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TBASFLCONTENT.FLCODE
     * 分类类型
     * @mbggenerated Wed Aug 14 17:24:14 CST 2019
     */
    private String flCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TBASFLCONTENT.LXCODE
     * 类型编码
     * @mbggenerated Wed Aug 14 17:24:14 CST 2019
     */
    private String lxCode;
    
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TBASFLCONTENT.LXNAME
     * 类型名称
     * @mbggenerated Wed Aug 14 17:24:14 CST 2019
     */
    private String lxName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TBASFLCONTENT.LXTYPE
     * 类型分类
     * @mbggenerated Wed Aug 14 17:24:14 CST 2019
     */
    private String lxType;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TBASFLCONTENT.REMARK
     * 备注
     * @mbggenerated Wed Aug 14 17:24:14 CST 2019
     */
    private String remark;

    /**
     * @return the flCode
     */
    public String getFlCode() {
        return flCode;
    }

    /**
     * @param flCode the flCode to set
     */
    public void setFlCode(String flCode) {
        this.flCode = flCode;
    }

    /**
     * @return the lxCode
     */
    public String getLxCode() {
        return lxCode;
    }

    /**
     * @param lxCode the lxCode to set
     */
    public void setLxCode(String lxCode) {
        this.lxCode = lxCode;
    }

    /**
     * @return the lxName
     */
    public String getLxName() {
        return lxName;
    }

    /**
     * @param lxName the lxName to set
     */
    public void setLxName(String lxName) {
        this.lxName = lxName;
    }

    /**
     * @return the lxType
     */
    public String getLxType() {
        return lxType;
    }

    /**
     * @param lxType the lxType to set
     */
    public void setLxType(String lxType) {
        this.lxType = lxType;
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
}