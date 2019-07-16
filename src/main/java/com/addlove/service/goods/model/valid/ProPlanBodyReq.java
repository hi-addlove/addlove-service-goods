package com.addlove.service.goods.model.valid;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 生产计划明细表参数
 * @author lw
 *
 */
public class ProPlanBodyReq {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TFRSPRODUCEPLANBODY.PLUID
     * 商品ID
     * @mbggenerated Wed Jul 03 09:44:27 CST 2019
     */
    @NotNull(message = "商品Id不能为空")
    private Long pluId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TFRSPRODUCEPLANBODY.PLUCODE
     * 商品编码
     * @mbggenerated Wed Jul 03 09:44:27 CST 2019
     */
    @NotBlank(message = "商品编码不能为空")
    private String pluCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TFRSPRODUCEPLANBODY.PLUNAME
     * 商品名称
     * @mbggenerated Wed Jul 03 09:44:27 CST 2019
     */
    private String pluName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TFRSPRODUCEPLANBODY.SPEC
     * 规格
     * @mbggenerated Wed Jul 03 09:44:27 CST 2019
     */
    private String spec;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TFRSPRODUCEPLANBODY.UNIT
     * 单位
     * @mbggenerated Wed Jul 03 09:44:27 CST 2019
     */
    private String unit;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TFRSPRODUCEPLANBODY.PLANCOUNT
     * 计划数量
     * @mbggenerated Wed Jul 03 09:44:27 CST 2019
     */
    @NotNull(message = "计划数量不能为空")
    private Double planCount;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TFRSPRODUCEPLANBODY.PLANTIME
     * 计划时间
     * @mbggenerated Wed Jul 03 09:44:27 CST 2019
     */
    @NotBlank(message = "计划时间不能为空")
    private String planTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TFRSPRODUCEPLANBODY.REMARK
     * 备注
     * @mbggenerated Wed Jul 03 09:44:27 CST 2019
     */
    private String remark;

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

    /**
     * @return the pluCode
     */
    public String getPluCode() {
        return pluCode;
    }

    /**
     * @param pluCode the pluCode to set
     */
    public void setPluCode(String pluCode) {
        this.pluCode = pluCode;
    }

    /**
     * @return the pluName
     */
    public String getPluName() {
        return pluName;
    }

    /**
     * @param pluName the pluName to set
     */
    public void setPluName(String pluName) {
        this.pluName = pluName;
    }

    /**
     * @return the spec
     */
    public String getSpec() {
        return spec;
    }

    /**
     * @param spec the spec to set
     */
    public void setSpec(String spec) {
        this.spec = spec;
    }

    /**
     * @return the unit
     */
    public String getUnit() {
        return unit;
    }

    /**
     * @param unit the unit to set
     */
    public void setUnit(String unit) {
        this.unit = unit;
    }

    /**
     * @return the planCount
     */
    public Double getPlanCount() {
        return planCount;
    }

    /**
     * @param planCount the planCount to set
     */
    public void setPlanCount(Double planCount) {
        this.planCount = planCount;
    }

    /**
     * @return the planTime
     */
    public String getPlanTime() {
        return planTime;
    }

    /**
     * @param planTime the planTime to set
     */
    public void setPlanTime(String planTime) {
        this.planTime = planTime;
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