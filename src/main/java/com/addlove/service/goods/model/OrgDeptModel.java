package com.addlove.service.goods.model;

/**
 * 部门表
 * @author lw
 *
 */
public class OrgDeptModel {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORGDEPT.DEPID
     * 部门ID
     * @mbggenerated Wed Jun 19 10:48:06 CST 2019
     */
    private Long depId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORGDEPT.ORGCODE
     * 组织编码
     * @mbggenerated Wed Jun 19 10:48:06 CST 2019
     */
    private String orgCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORGDEPT.ORGCLASS
     * 组织类别
     * @mbggenerated Wed Jun 19 10:48:06 CST 2019
     */
    private String orgClass;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORGDEPT.DEPCODE
     * 部门编码
     * @mbggenerated Wed Jun 19 10:48:06 CST 2019
     */
    private String depCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORGDEPT.DEPNAME
     * 部门名称
     * @mbggenerated Wed Jun 19 10:48:06 CST 2019
     */
    private String depName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORGDEPT.DEPENAME
     * 部门英文名称
     * @mbggenerated Wed Jun 19 10:48:06 CST 2019
     */
    private String depEName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORGDEPT.AREA
     * 营业面积（平方米）
     * @mbggenerated Wed Jun 19 10:48:06 CST 2019
     */
    private Double area;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORGDEPT.ISLAST
     * 末级部门
     * @mbggenerated Wed Jun 19 10:48:06 CST 2019
     */
    private String isLast;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORGDEPT.ISYW
     * 业务部门
     * @mbggenerated Wed Jun 19 10:48:06 CST 2019
     */
    private String isYw;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORGDEPT.ISACTIVE
     * 在用
     * @mbggenerated Wed Jun 19 10:48:06 CST 2019
     */
    private String isActive;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORGDEPT.REMARK
     * 备注
     * @mbggenerated Wed Jun 19 10:48:06 CST 2019
     */
    private String remark;

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
     * @return the orgClass
     */
    public String getOrgClass() {
        return orgClass;
    }

    /**
     * @param orgClass the orgClass to set
     */
    public void setOrgClass(String orgClass) {
        this.orgClass = orgClass;
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
     * @return the depEName
     */
    public String getDepEName() {
        return depEName;
    }

    /**
     * @param depEName the depEName to set
     */
    public void setDepEName(String depEName) {
        this.depEName = depEName;
    }

    /**
     * @return the area
     */
    public Double getArea() {
        return area;
    }

    /**
     * @param area the area to set
     */
    public void setArea(Double area) {
        this.area = area;
    }

    /**
     * @return the isLast
     */
    public String getIsLast() {
        return isLast;
    }

    /**
     * @param isLast the isLast to set
     */
    public void setIsLast(String isLast) {
        this.isLast = isLast;
    }

    /**
     * @return the isYw
     */
    public String getIsYw() {
        return isYw;
    }

    /**
     * @param isYw the isYw to set
     */
    public void setIsYw(String isYw) {
        this.isYw = isYw;
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
}
