package com.addlove.service.goods.model.valid;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 门店盘点单明细表参数
 * @author lw
 *
 */
public class CouMdPdBodyReq {
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

    /** 售价*/
    @NotNull(message = "售价不能为空")
    private Double price;

    /** 调出无税进价*/
    @NotNull(message = "调出无税进价不能为空")
    private Double wjPrice;
    
    /** 调出含税进价*/
    @NotNull(message = "调出含税进价不能为空")
    private Double hjPrice;
    
    /** 账面数量*/
    @NotNull(message = "账面数量不能为空")
    private Double zmCount;

    /** 账面含税进价金额*/
    @NotNull(message = "账面含税进价金额不能为空")
    private Double zmHCost;

    /** 账面无税进价金额*/
    @NotNull(message = "账面无税进价金额不能为空")
    private Double zmWCost;

    /** 账面售价金额*/
    @NotNull(message = "账面售价金额不能为空")
    private Double zmSCost;

    /** 实盘数量*/
    @NotNull(message = "实盘数量不能为空")
    private Double sJCount;

    /** 实盘含税进价金额*/
    @NotNull(message = "实盘含税进价金额不能为空")
    private Double sJhCost;

    /** 实盘无税进价金额*/
    @NotNull(message = "实盘无税进价金额不能为空")
    private Double sJwCost;

    /** 实盘售价金额*/
    @NotNull(message = "实盘售价金额不能为空")
    private Double sJsCost;

    /** 盈亏数量*/
    @NotNull(message = "盈亏数量不能为空")
    private Double ykCount;

    /** 盈亏含税进价金额*/
    @NotNull(message = "盈亏含税进价金额不能为空")
    private Double ykHCost;

    /** 盈亏无税进价金额*/
    @NotNull(message = "盈亏无税进价金额不能为空")
    private Double ykWCost;

    /** 盈亏售价金额*/
    @NotNull(message = "盈亏售价金额不能为空")
    private Double ykSCost;
    
    /** 备注*/
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
     * @return the zmCount
     */
    public Double getZmCount() {
        return zmCount;
    }

    /**
     * @param zmCount the zmCount to set
     */
    public void setZmCount(Double zmCount) {
        this.zmCount = zmCount;
    }

    /**
     * @return the zmHCost
     */
    public Double getZmHCost() {
        return zmHCost;
    }

    /**
     * @param zmHCost the zmHCost to set
     */
    public void setZmHCost(Double zmHCost) {
        this.zmHCost = zmHCost;
    }

    /**
     * @return the zmWCost
     */
    public Double getZmWCost() {
        return zmWCost;
    }

    /**
     * @param zmWCost the zmWCost to set
     */
    public void setZmWCost(Double zmWCost) {
        this.zmWCost = zmWCost;
    }

    /**
     * @return the zmSCost
     */
    public Double getZmSCost() {
        return zmSCost;
    }

    /**
     * @param zmSCost the zmSCost to set
     */
    public void setZmSCost(Double zmSCost) {
        this.zmSCost = zmSCost;
    }

    /**
     * @return the sJCount
     */
    public Double getsJCount() {
        return sJCount;
    }

    /**
     * @param sJCount the sJCount to set
     */
    public void setsJCount(Double sJCount) {
        this.sJCount = sJCount;
    }

    /**
     * @return the sJhCost
     */
    public Double getsJhCost() {
        return sJhCost;
    }

    /**
     * @param sJhCost the sJhCost to set
     */
    public void setsJhCost(Double sJhCost) {
        this.sJhCost = sJhCost;
    }

    /**
     * @return the sJwCost
     */
    public Double getsJwCost() {
        return sJwCost;
    }

    /**
     * @param sJwCost the sJwCost to set
     */
    public void setsJwCost(Double sJwCost) {
        this.sJwCost = sJwCost;
    }

    /**
     * @return the sJsCost
     */
    public Double getsJsCost() {
        return sJsCost;
    }

    /**
     * @param sJsCost the sJsCost to set
     */
    public void setsJsCost(Double sJsCost) {
        this.sJsCost = sJsCost;
    }

    /**
     * @return the ykCount
     */
    public Double getYkCount() {
        return ykCount;
    }

    /**
     * @param ykCount the ykCount to set
     */
    public void setYkCount(Double ykCount) {
        this.ykCount = ykCount;
    }

    /**
     * @return the ykHCost
     */
    public Double getYkHCost() {
        return ykHCost;
    }

    /**
     * @param ykHCost the ykHCost to set
     */
    public void setYkHCost(Double ykHCost) {
        this.ykHCost = ykHCost;
    }

    /**
     * @return the ykWCost
     */
    public Double getYkWCost() {
        return ykWCost;
    }

    /**
     * @param ykWCost the ykWCost to set
     */
    public void setYkWCost(Double ykWCost) {
        this.ykWCost = ykWCost;
    }

    /**
     * @return the ykSCost
     */
    public Double getYkSCost() {
        return ykSCost;
    }

    /**
     * @param ykSCost the ykSCost to set
     */
    public void setYkSCost(Double ykSCost) {
        this.ykSCost = ykSCost;
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
