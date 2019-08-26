package com.addlove.service.goods.model.valid;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 加工单成品表参数
 * @author lw
 *
 */
public class FrsJgCpReq {
    /** 商品ID*/
    @NotNull(message = "商品ID不能为空")
    private Long pluId;

    /** 商品编码*/
    @NotBlank(message = "商品编码不能为空")
    private String pluCode;

    /** 商品名称*/
    private String pluName;

    /** 商品条码*/
    private String barCode;
    
    /** 规格*/
    private String spec;
    
    /** 单位*/
    private String unit;
    
    /** 含税进价*/
    @NotNull(message = "含税进价不能为空")
    private Double hjPrice;

    /** 无税进价*/
    @NotNull(message = "无税进价不能为空")
    private Double wjPrice;

    /** 售价*/
    @NotNull(message = "售价不能为空")
    private Double price;

    /** 进项税率*/
    @NotNull(message = "进项税率不能为空")
    private Integer jTaxRate;
    
    /** 成品率(%)*/
    @NotNull(message = "成品率(%)不能为空")
    private Double cpPercent;

    /** 成品数量*/
    @NotNull(message = "成品数量不能为空")
    private Double cpCount;

    /** 成品含税进价金额*/
    @NotNull(message = "成品含税进价金额不能为空")
    private Double cphCost;

    /** 成品无税进价金额*/
    @NotNull(message = "成品无税进价金额不能为空")
    private Double cpwCost;

    /** 成品售价金额*/
    @NotNull(message = "成品售价金额不能为空")
    private Double cpTotal;

    /** 备注*/
    private String remark;

    /** 进项税计算类型*/
    @NotNull(message = "进项税计算类型不能为空")
    private String jTaxcalType;

    /** 分配比率(%)*/
    @NotNull(message = "分配比率(%)不能为空")
    private Double fpPercent;

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
     * @return the barCode
     */
    public String getBarCode() {
        return barCode;
    }

    /**
     * @param barCode the barCode to set
     */
    public void setBarCode(String barCode) {
        this.barCode = barCode;
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
     * @return the hjPrice
     */
    public Double getHjPrice() {
        return hjPrice;
    }

    /**
     * @param hjPrice the hjPrice to set
     */
    public void setHjPrice(Double hjPrice) {
        this.hjPrice = hjPrice;
    }

    /**
     * @return the wjPrice
     */
    public Double getWjPrice() {
        return wjPrice;
    }

    /**
     * @param wjPrice the wjPrice to set
     */
    public void setWjPrice(Double wjPrice) {
        this.wjPrice = wjPrice;
    }

    /**
     * @return the price
     */
    public Double getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(Double price) {
        this.price = price;
    }

    /**
     * @return the jTaxRate
     */
    public Integer getjTaxRate() {
        return jTaxRate;
    }

    /**
     * @param jTaxRate the jTaxRate to set
     */
    public void setjTaxRate(Integer jTaxRate) {
        this.jTaxRate = jTaxRate;
    }

    /**
     * @return the cpPercent
     */
    public Double getCpPercent() {
        return cpPercent;
    }

    /**
     * @param cpPercent the cpPercent to set
     */
    public void setCpPercent(Double cpPercent) {
        this.cpPercent = cpPercent;
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
     * @return the jTaxcalType
     */
    public String getjTaxcalType() {
        return jTaxcalType;
    }

    /**
     * @param jTaxcalType the jTaxcalType to set
     */
    public void setjTaxcalType(String jTaxcalType) {
        this.jTaxcalType = jTaxcalType;
    }

    /**
     * @return the fpPercent
     */
    public Double getFpPercent() {
        return fpPercent;
    }

    /**
     * @param fpPercent the fpPercent to set
     */
    public void setFpPercent(Double fpPercent) {
        this.fpPercent = fpPercent;
    }
}
