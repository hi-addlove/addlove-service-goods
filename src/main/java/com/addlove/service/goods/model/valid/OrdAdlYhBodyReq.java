package com.addlove.service.goods.model.valid;

import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotBlank;

/**
 * ADL要货单明细参数
 * @author lw
 *
 */
public class OrdAdlYhBodyReq {
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
    
    /** 单位*/
    private String unit;

    /** 规格*/
    private String spec;
    
    /** 包装单位*/
    @NotBlank(message = "包装单位不能为空")
    private String packUnit;

    /** 包装细数*/
    @NotNull(message = "包装细数不能为空")
    private Double packQty;
    
    /** 要货数量*/
    @NotNull(message = "要货数量不能为空")
    private Double yhCount;
    
    /** 确认数量*/
    @NotNull(message = "确认数量不能为空")
    private Double qrCount;
    
    /** 售价*/
    @NotNull(message = "售价不能为空")
    private Double price;

    /** 售价金额*/
    @NotNull(message = "售价金额不能为空")
    private Double sTotal;
    
    /** 备注*/
    private String remark;
    
    /** 现有库存数量*/
    private Double kcCount;
    
    /** 明日到货数量*/
    private Double mRJhCount;

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
     * @return the packUnit
     */
    public String getPackUnit() {
        return packUnit;
    }

    /**
     * @param packUnit the packUnit to set
     */
    public void setPackUnit(String packUnit) {
        this.packUnit = packUnit;
    }

    /**
     * @return the packQty
     */
    public Double getPackQty() {
        return packQty;
    }

    /**
     * @param packQty the packQty to set
     */
    public void setPackQty(Double packQty) {
        this.packQty = packQty;
    }

    /**
     * @return the yhCount
     */
    public Double getYhCount() {
        return yhCount;
    }

    /**
     * @param yhCount the yhCount to set
     */
    public void setYhCount(Double yhCount) {
        this.yhCount = yhCount;
    }

    /**
     * @return the qrCount
     */
    public Double getQrCount() {
        return qrCount;
    }

    /**
     * @param qrCount the qrCount to set
     */
    public void setQrCount(Double qrCount) {
        this.qrCount = qrCount;
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
     * @return the kcCount
     */
    public Double getKcCount() {
        return kcCount;
    }

    /**
     * @param kcCount the kcCount to set
     */
    public void setKcCount(Double kcCount) {
        this.kcCount = kcCount;
    }

    /**
     * @return the mRJhCount
     */
    public Double getmRJhCount() {
        return mRJhCount;
    }

    /**
     * @param mRJhCount the mRJhCount to set
     */
    public void setmRJhCount(Double mRJhCount) {
        this.mRJhCount = mRJhCount;
    }
}
