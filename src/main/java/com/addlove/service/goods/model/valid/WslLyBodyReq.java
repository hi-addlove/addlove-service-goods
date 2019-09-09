package com.addlove.service.goods.model.valid;

import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 领用单明细参数
 * @author lw
 *
 */
public class WslLyBodyReq {
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

    
    /** 领用数量*/
    @NotNull(message = "领用数量不能为空")
    private Double lyCount;
    
    /** 售价*/
    @NotNull(message = "售价不能为空")
    private Double price;

    /** 领用金额*/
    @NotNull(message = "领用金额不能为空")
    private Double sTotal;
    
    /** 实收金额*/
    @NotNull(message = "实收金额不能为空")
    private Double ssTotal;

    /** 备注*/
    private String remark;
    
    /** 参考售价 */
    @NotNull(message = "参考售价不能为空")
    private Double ckPrice;

    /** 参考售价金额*/
    @NotNull(message = "参考售价金额不能为空")
    private Double ckSsTotal;

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
     * @return the lyCount
     */
    public Double getLyCount() {
        return lyCount;
    }

    /**
     * @param lyCount the lyCount to set
     */
    public void setLyCount(Double lyCount) {
        this.lyCount = lyCount;
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
     * @return the sTotal
     */
    public Double getsTotal() {
        return sTotal;
    }

    /**
     * @param sTotal the sTotal to set
     */
    public void setsTotal(Double sTotal) {
        this.sTotal = sTotal;
    }

    /**
     * @return the ssTotal
     */
    public Double getSsTotal() {
        return ssTotal;
    }

    /**
     * @param ssTotal the ssTotal to set
     */
    public void setSsTotal(Double ssTotal) {
        this.ssTotal = ssTotal;
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
     * @return the ckPrice
     */
    public Double getCkPrice() {
        return ckPrice;
    }

    /**
     * @param ckPrice the ckPrice to set
     */
    public void setCkPrice(Double ckPrice) {
        this.ckPrice = ckPrice;
    }

    /**
     * @return the ckSsTotal
     */
    public Double getCkSsTotal() {
        return ckSsTotal;
    }

    /**
     * @param ckSsTotal the ckSsTotal to set
     */
    public void setCkSsTotal(Double ckSsTotal) {
        this.ckSsTotal = ckSsTotal;
    }
}
