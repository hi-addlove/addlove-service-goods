package com.addlove.service.goods.model.valid;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 加工单原料表参数
 * @author lw
 *
 */
public class FrsJgYlReq {
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

    /** 原料比例(%)*/
    @NotNull(message = "原料比例(%)不能为空")
    private Double ylPercent;

    /** 原料数量*/
    @NotNull(message = "原料数量不能为空")
    private Double ylCount;

    /** 原料含税进价金额*/
    @NotNull(message = "原料含税进价金额不能为空")
    private Double ylhCost;

    /** 原料无税进价金额*/
    @NotNull(message = "原料无税进价金额不能为空")
    private Double ylwCost;

    /** 原料售价金额*/
    @NotNull(message = "原料售价金额不能为空")
    private Double ylTotal;

    /** 备注*/
    private String remark;

    /** 进项税计算类型*/
    @NotBlank(message = "进项税计算类型不能为空")
    private String jTaxcalType;

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
     * @return the ylPercent
     */
    public Double getYlPercent() {
        return ylPercent;
    }

    /**
     * @param ylPercent the ylPercent to set
     */
    public void setYlPercent(Double ylPercent) {
        this.ylPercent = ylPercent;
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
}
