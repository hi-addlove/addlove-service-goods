package com.addlove.service.goods.model;

/**
 * 门店盘点账面数量model
 * @author lw
 *
 */
public class MdPdAccountModel extends CouMdPdBodyModel{
    /**含税进价 */
    private Double hJPricePd;
    
    /**无税进价 */
    private Double wJPricePd;
    
    /**含税金额 */
    private Double hCost;
    
    /**无税金额 */
    private Double wCost;
    
    /**售价金额 */
    private Double sCost;
    
    /**库存 */
    private Double kcCount;
    
    /**可用库存 */
    private Double kyKcCount;

    /**
     * @return the hJPricePd
     */
    public Double gethJPricePd() {
        return hJPricePd;
    }

    /**
     * @param hJPricePd the hJPricePd to set
     */
    public void sethJPricePd(Double hJPricePd) {
        this.hJPricePd = hJPricePd;
    }

    /**
     * @return the wJPricePd
     */
    public Double getwJPricePd() {
        return wJPricePd;
    }

    /**
     * @param wJPricePd the wJPricePd to set
     */
    public void setwJPricePd(Double wJPricePd) {
        this.wJPricePd = wJPricePd;
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
     * @return the sCost
     */
    public Double getsCost() {
        return sCost;
    }

    /**
     * @param sCost the sCost to set
     */
    public void setsCost(Double sCost) {
        this.sCost = sCost;
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
     * @return the kyKcCount
     */
    public Double getKyKcCount() {
        return kyKcCount;
    }

    /**
     * @param kyKcCount the kyKcCount to set
     */
    public void setKyKcCount(Double kyKcCount) {
        this.kyKcCount = kyKcCount;
    }
}
