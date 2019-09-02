package com.addlove.service.goods.model;

/**
 * 加工工艺表
 * @author lw
 *
 */
public class FrsGyModel {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TFRSGY.GYCODE
     * 加工工艺编码
     * @mbggenerated Mon Aug 26 16:32:21 CST 2019
     */
    private String gyCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TFRSGY.GYNAME
     * 加工工艺名称
     * @mbggenerated Mon Aug 26 16:32:21 CST 2019
     */
    private String gyName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TFRSGY.ORGCODE
     * 组织编码
     * @mbggenerated Mon Aug 26 16:32:21 CST 2019
     */
    private String orgCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TFRSGY.ORGCNAME
     * 组织名称
     * @mbggenerated Mon Aug 26 16:32:21 CST 2019
     */
    private String orgcName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORDJHHEAD.DEPID
     * 部门Id
     * @mbggenerated Tue Jun 04 15:32:25 CST 2019
     */
    private String deptId;

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
     * This field corresponds to the database column TFRSJGHEAD.FLCOST
     * 辅料成本
     * @mbggenerated Mon Aug 26 10:46:06 CST 2019
     */
    private Double flCost;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TFRSGY.REMARK
     * 备注
     * @mbggenerated Mon Aug 26 16:32:21 CST 2019
     */
    private String remark;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TFRSGY.USESTATUS
     * 使用状态:0-未生效；1-调整中；2-已启用；3-已作废
     * @mbggenerated Mon Aug 26 16:32:21 CST 2019
     */
    private String useStatus;
    
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TSTKDBBODY.PLUID
     * 商品Id
     * @mbggenerated Tue Jun 25 13:39:20 CST 2019
     */
    private Long pluId;

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
     * @return the orgcName
     */
    public String getOrgcName() {
        return orgcName;
    }

    /**
     * @param orgcName the orgcName to set
     */
    public void setOrgcName(String orgcName) {
        this.orgcName = orgcName;
    }

    /**
     * @return the deptId
     */
    public String getDeptId() {
        return deptId;
    }

    /**
     * @param deptId the deptId to set
     */
    public void setDeptId(String deptId) {
        this.deptId = deptId;
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
     * @return the useStatus
     */
    public String getUseStatus() {
        return useStatus;
    }

    /**
     * @param useStatus the useStatus to set
     */
    public void setUseStatus(String useStatus) {
        this.useStatus = useStatus;
    }

    /**
     * @return the pluId
     */
    public Long getPluId() {
        return pluId;
    }

    /**
     * @param pluId the pluId to set
     */
    public void setPluId(Long pluId) {
        this.pluId = pluId;
    }
}