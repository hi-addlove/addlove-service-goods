package com.addlove.service.goods.model.valid;

import javax.validation.constraints.NotNull;

/**
 * 门店退货申请明细表参数
 * @author lw
 *
 */
public class OrdThApplyBodyDiffReq {
    /**序号 */
    @NotNull(message = "序号不能为空")
    private Long serialNo;
    
    /**商品ID */
    @NotNull(message = "商品ID不能为空")
    private Long pluId;

    /**商品编码 */
    @NotNull(message = "商品编码不能为空")
    private String pluCode;

    /**商品名称 */
    private String pluName;
    
    /**特征码编码 */
    @NotNull(message = "特征码编码不能为空")
    private String exPluCode;
    
    /**商品条码 */
    private String barCode;

    /**规格 */
    private String spec;

    /**单位 */
    private String unit;

    /**货号 */
    private String carGoNo;
    
    /**部门Id */
    @NotNull(message = "部门Id不能为空")
    private Long depId;

    /**部门编码 */
    @NotNull(message = "部门编码不能为空")
    private String depCode;

    /**部门名称 */
    private String depName;
    
    /**配送价 */
    @NotNull(message = "配送价不能为空")
    private Double psPrice;
    
    /**包装单位 */
    @NotNull(message = "包装单位不能为空")
    private String packUnit;

    /**包装细数 */
    @NotNull(message = "包装细数不能为空")
    private Double packQty;

    /**包装数量 */
    @NotNull(message = "包装数量不能为空")
    private Double packCount;

    /**单件件数 */
    @NotNull(message = "单件件数不能为空")
    private Double sglCount;
    
    /**退货数量 */
    @NotNull(message = "退货数量不能为空")
    private Double thCount;

    /**含税进价金额 */
    @NotNull(message = "含税进价金额不能为空")
    private Double hCost;

    /**无税进价金额 */
    @NotNull(message = "无税进价金额不能为空")
    private Double wCost;

    /**进项税额 */
    @NotNull(message = "进项税额不能为空")
    private Double jTaxTotal;

    /**配送金额 */
    @NotNull(message = "配送金额不能为空")
    private Double psCost;

    /**售价金额 */
    @NotNull(message = "售价金额不能为空")
    private Double sTotal;

    /**进销差价 */
    @NotNull(message = "进销差价不能为空")
    private Double cjTotal;

    /**进销差率 */
    @NotNull(message = "进销差率不能为空")
    private Double cjRate;
    
    /**含税结算金额 */
    @NotNull(message = "含税结算金额不能为空")
    private Double hjsTotal;

    /**无税结算金额 */
    @NotNull(message = "无税结算金额不能为空")
    private Double wjsTotal;

    /**
     * @return the serialNo
     */
    public Long getSerialNo() {
        return serialNo;
    }

    /**
     * @param serialNo the serialNo to set
     */
    public void setSerialNo(Long serialNo) {
        this.serialNo = serialNo;
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
     * @return the exPluCode
     */
    public String getExPluCode() {
        return exPluCode;
    }

    /**
     * @param exPluCode the exPluCode to set
     */
    public void setExPluCode(String exPluCode) {
        this.exPluCode = exPluCode;
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
     * @return the carGoNo
     */
    public String getCarGoNo() {
        return carGoNo;
    }

    /**
     * @param carGoNo the carGoNo to set
     */
    public void setCarGoNo(String carGoNo) {
        this.carGoNo = carGoNo;
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
     * @return the psPrice
     */
    public Double getPsPrice() {
        return psPrice;
    }

    /**
     * @param psPrice the psPrice to set
     */
    public void setPsPrice(Double psPrice) {
        this.psPrice = psPrice;
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
     * @return the packCount
     */
    public Double getPackCount() {
        return packCount;
    }

    /**
     * @param packCount the packCount to set
     */
    public void setPackCount(Double packCount) {
        this.packCount = packCount;
    }

    /**
     * @return the sglCount
     */
    public Double getSglCount() {
        return sglCount;
    }

    /**
     * @param sglCount the sglCount to set
     */
    public void setSglCount(Double sglCount) {
        this.sglCount = sglCount;
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
     * @return the hCost
     */
    public Double gethCost() {
        return hCost;
    }

    /**
     * @param hCost the hCost to set
     */
    public void sethCost(Double hCost) {
        this.hCost = hCost;
    }

    /**
     * @return the wCost
     */
    public Double getwCost() {
        return wCost;
    }

    /**
     * @param wCost the wCost to set
     */
    public void setwCost(Double wCost) {
        this.wCost = wCost;
    }

    /**
     * @return the jTaxTotal
     */
    public Double getjTaxTotal() {
        return jTaxTotal;
    }

    /**
     * @param jTaxTotal the jTaxTotal to set
     */
    public void setjTaxTotal(Double jTaxTotal) {
        this.jTaxTotal = jTaxTotal;
    }

    /**
     * @return the psCost
     */
    public Double getPsCost() {
        return psCost;
    }

    /**
     * @param psCost the psCost to set
     */
    public void setPsCost(Double psCost) {
        this.psCost = psCost;
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
     * @return the cjTotal
     */
    public Double getCjTotal() {
        return cjTotal;
    }

    /**
     * @param cjTotal the cjTotal to set
     */
    public void setCjTotal(Double cjTotal) {
        this.cjTotal = cjTotal;
    }

    /**
     * @return the cjRate
     */
    public Double getCjRate() {
        return cjRate;
    }

    /**
     * @param cjRate the cjRate to set
     */
    public void setCjRate(Double cjRate) {
        this.cjRate = cjRate;
    }

    /**
     * @return the hjsTotal
     */
    public Double getHjsTotal() {
        return hjsTotal;
    }

    /**
     * @param hjsTotal the hjsTotal to set
     */
    public void setHjsTotal(Double hjsTotal) {
        this.hjsTotal = hjsTotal;
    }

    /**
     * @return the wjsTotal
     */
    public Double getWjsTotal() {
        return wjsTotal;
    }

    /**
     * @param wjsTotal the wjsTotal to set
     */
    public void setWjsTotal(Double wjsTotal) {
        this.wjsTotal = wjsTotal;
    }
}