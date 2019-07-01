package com.addlove.service.goods.model;

/**
 * 商品扩展字段
 * @author lw
 *
 */
public class SkuPluExtendModel extends SkuPluModel{
    /**特征码编码 */
    private String eXPluCode;
    
    /**现存数量 */
    private Double kcCount;
    
    /**含税进价金额 */
    private Double hCost;
    
    /**无税进价金额 */
    private Double wCost;
    
    /**商品类型 */
    private String pluType;
    
    /**包装细数 */
    private Double packQty;
    
    /**
     * @return the eXPluCode
     */
    public String geteXPluCode() {
        return eXPluCode;
    }
    /**
     * @param eXPluCode the eXPluCode to set
     */
    public void seteXPluCode(String eXPluCode) {
        this.eXPluCode = eXPluCode;
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
     * @return the pluType
     */
    public String getPluType() {
        return pluType;
    }
    /**
     * @param pluType the pluType to set
     */
    public void setPluType(String pluType) {
        this.pluType = pluType;
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
}
