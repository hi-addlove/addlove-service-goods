package com.addlove.service.goods.model.valid;

import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 配送退货明细表参数
 * @author lw
 *
 */
public class DstRtnApplyBodyReq {
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
    
    /** 本币退货价*/
    @NotNull(message = "本币退货价不能为空")
    private Double thPrice;
    
    /** 退货数量*/
    @NotNull(message = "退货数量不能为空")
    private Double thCount;

    /** 退货金额*/
    @NotNull(message = "退货金额不能为空")
    private Double thTotal;
    
    /** 备注*/
    private String remark;
    
    /** 部门Id*/
    @NotNull(message = "部门Id不能为空")
    private Long depId;
    
    /** 退货原因*/
    private String thReason;
    
    /** 本币无税退货价*/
    @NotNull(message = "无税退货价不能为空")
    private Double wThPrice;
    
    /** 本币无税退货金额*/
    @NotNull(message = "无税退货金额不能为空")
    private Double wThTotal;
    
    /** 售价 */
    @NotNull(message = "售价 不能为空")
    private Double price;

    /** 售价金额*/
    @NotNull(message = "售价 金额不能为空")
    private Double sTotal;

    /** 原币退货价格*/
    @NotNull(message = "原币退货价格不能为空")
    private Double stlCurrThPrice;

    /** 原币退货金额*/
    @NotNull(message = "原币退货金额不能为空")
    private Double stlCurrThTotal;

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
     * @return the thPrice
     */
    public Double getThPrice() {
        return thPrice;
    }

    /**
     * @param thPrice the thPrice to set
     */
    public void setThPrice(Double thPrice) {
        this.thPrice = thPrice;
    }

    /**
     * @return the thCount
     */
    public Double getThCount() {
        return thCount;
    }

    /**
     * @param thCount the thCount to set
     */
    public void setThCount(Double thCount) {
        this.thCount = thCount;
    }

    /**
     * @return the thTotal
     */
    public Double getThTotal() {
        return thTotal;
    }

    /**
     * @param thTotal the thTotal to set
     */
    public void setThTotal(Double thTotal) {
        this.thTotal = thTotal;
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
     * @return the thReason
     */
    public String getThReason() {
        return thReason;
    }

    /**
     * @param thReason the thReason to set
     */
    public void setThReason(String thReason) {
        this.thReason = thReason;
    }

    /**
     * @return the wThPrice
     */
    public Double getwThPrice() {
        return wThPrice;
    }

    /**
     * @param wThPrice the wThPrice to set
     */
    public void setwThPrice(Double wThPrice) {
        this.wThPrice = wThPrice;
    }

    /**
     * @return the wThTotal
     */
    public Double getwThTotal() {
        return wThTotal;
    }

    /**
     * @param wThTotal the wThTotal to set
     */
    public void setwThTotal(Double wThTotal) {
        this.wThTotal = wThTotal;
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
     * @return the stlCurrThPrice
     */
    public Double getStlCurrThPrice() {
        return stlCurrThPrice;
    }

    /**
     * @param stlCurrThPrice the stlCurrThPrice to set
     */
    public void setStlCurrThPrice(Double stlCurrThPrice) {
        this.stlCurrThPrice = stlCurrThPrice;
    }

    /**
     * @return the stlCurrThTotal
     */
    public Double getStlCurrThTotal() {
        return stlCurrThTotal;
    }

    /**
     * @param stlCurrThTotal the stlCurrThTotal to set
     */
    public void setStlCurrThTotal(Double stlCurrThTotal) {
        this.stlCurrThTotal = stlCurrThTotal;
    }
}
