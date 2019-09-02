package com.addlove.service.goods.model;

/**
 * 要货模板主表
 * @author lw
 *
 */
public class OrdYhTempletOrgModel {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORDYHMODELHEAD.MODELCODE
     * 模板编码
     * @mbggenerated Mon Aug 19 15:14:02 CST 2019
     */
    private String modelCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TSTKDBHEAD.ORGCODE
     * 组织编码
     * @mbggenerated Fri Aug 02 10:50:01 CST 2019
     */
    private String orgCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORDYHMODELHEAD.REMARK
     * 备注
     * @mbggenerated Mon Aug 19 15:14:02 CST 2019
     */
    private String remark;

    /**
     * @return the modelCode
     */
    public String getModelCode() {
        return modelCode;
    }

    /**
     * @param modelCode the modelCode to set
     */
    public void setModelCode(String modelCode) {
        this.modelCode = modelCode;
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