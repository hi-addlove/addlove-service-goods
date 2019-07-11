package com.addlove.service.goods.model;

/**
 * 退货单明细表
 * @author lw
 *
 */
public class OrdThBodyModel {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORDTHBODY.BILLNO
     * 单据号
     * @mbggenerated Thu Jul 11 16:42:43 CST 2019
     */
    private String billNo;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORDTHBODY.SERIALNO
     * 序号
     * @mbggenerated Thu Jul 11 16:42:43 CST 2019
     */
    private Long serialNo;
    
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORDTHBODY.PLUID
     * 商品ID
     * @mbggenerated Thu Jul 11 16:42:43 CST 2019
     */
    private Long pluId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORDTHBODY.PLUCODE
     * 商品编码
     * @mbggenerated Thu Jul 11 16:42:43 CST 2019
     */
    private String pluCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORDTHBODY.PLUNAME
     * 商品名称
     * @mbggenerated Thu Jul 11 16:42:43 CST 2019
     */
    private String pluName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORDTHBODY.EXPLUCODE
     * 特征码编码
     * @mbggenerated Thu Jul 11 16:42:43 CST 2019
     */
    private String exPluCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORDTHBODY.EXPLUNAME
     * 特征码名称
     * @mbggenerated Thu Jul 11 16:42:43 CST 2019
     */
    private String exPluName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORDTHBODY.BARCODE
     * 商品条码
     * @mbggenerated Thu Jul 11 16:42:43 CST 2019
     */
    private String barCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORDTHBODY.SPEC
     * 规格
     * @mbggenerated Thu Jul 11 16:42:43 CST 2019
     */
    private String spec;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORDTHBODY.UNIT
     * 单位
     * @mbggenerated Thu Jul 11 16:42:43 CST 2019
     */
    private String unit;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORDTHBODY.CARGONO
     * 货号
     * @mbggenerated Thu Jul 11 16:42:43 CST 2019
     */
    private String carGoNo;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORDTHBODY.PLUTYPE
     * 商品类型:0-正品  1-赠品  2-促销品  3-不良品
     * @mbggenerated Thu Jul 11 16:42:43 CST 2019
     */
    private String pluType;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORDTHBODY.DEPID
     * 部门Id
     * @mbggenerated Thu Jul 11 16:42:43 CST 2019
     */
    private Long depId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORDTHBODY.DEPCODE
     * 部门编码
     * @mbggenerated Thu Jul 11 16:42:43 CST 2019
     */
    private String depCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORDTHBODY.DEPNAME
     * 部门名称
     * @mbggenerated Thu Jul 11 16:42:43 CST 2019
     */
    private String depName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORDTHBODY.HJPRICE
     * 含税进价
     * @mbggenerated Thu Jul 11 16:42:43 CST 2019
     */
    private Double hjPrice;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORDTHBODY.WJPRICE
     * 无税进价
     * @mbggenerated Thu Jul 11 16:42:43 CST 2019
     */
    private Double wjPrice;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORDTHBODY.JTAXRATE
     * 进项税率
     * @mbggenerated Thu Jul 11 16:42:43 CST 2019
     */
    private Double jTaxRate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORDTHBODY.PRICE
     * 售价
     * @mbggenerated Thu Jul 11 16:42:43 CST 2019
     */
    private Double price;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORDTHBODY.PSPRICE
     * 配送价:直送退货有效，指门店对总部的配送价格，也是门店自己的进货价格
     * @mbggenerated Thu Jul 11 16:42:43 CST 2019
     */
    private Double psPrice;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORDTHBODY.PACKUNIT
     * 包装单位
     * @mbggenerated Thu Jul 11 16:42:43 CST 2019
     */
    private String packUnit;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORDTHBODY.PACKQTY
     * 包装细数
     * @mbggenerated Thu Jul 11 16:42:43 CST 2019
     */
    private Double packQty;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORDTHBODY.PACKCOUNT
     * 包装数量
     * @mbggenerated Thu Jul 11 16:42:43 CST 2019
     */
    private Double packCount;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORDTHBODY.SGLCOUNT
     * 单件件数
     * @mbggenerated Thu Jul 11 16:42:43 CST 2019
     */
    private Double sglCount;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORDTHBODY.THCOUNT
     * 退货数量
     * @mbggenerated Thu Jul 11 16:42:43 CST 2019
     */
    private Double thCount;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORDTHBODY.HCOST
     * 含税进价金额
     * @mbggenerated Thu Jul 11 16:42:43 CST 2019
     */
    private Double hCost;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORDTHBODY.WCOST
     * 无税进价金额
     * @mbggenerated Thu Jul 11 16:42:43 CST 2019
     */
    private Double wCost;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORDTHBODY.JTAXTOTAL
     * 进项税额
     * @mbggenerated Thu Jul 11 16:42:43 CST 2019
     */
    private Double jTaxTotal;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORDTHBODY.STOTAL
     * 售价金额
     * @mbggenerated Thu Jul 11 16:42:43 CST 2019
     */
    private Double sTotal;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORDTHBODY.CJTOTAL
     * 进销差价
     * @mbggenerated Thu Jul 11 16:42:43 CST 2019
     */
    private Double cjTotal;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORDTHBODY.CJRATE
     * 进销差率
     * @mbggenerated Thu Jul 11 16:42:43 CST 2019
     */
    private Double cjRate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORDTHBODY.PSCOST
     * 配送金额:直送退货有效
     * @mbggenerated Thu Jul 11 16:42:43 CST 2019
     */
    private Double psCost;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORDTHBODY.REMARK
     * 备注
     * @mbggenerated Thu Jul 11 16:42:43 CST 2019
     */
    private String remark;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORDTHBODY.HJSTOTAL
     * 含税结算金额
     * @mbggenerated Thu Jul 11 16:42:43 CST 2019
     */
    private Double hjsTotal;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORDTHBODY.WJSTOTAL
     * 无税结算金额
     * @mbggenerated Thu Jul 11 16:42:43 CST 2019
     */
    private Double wjsTotal;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORDTHBODY.THREASON
     * 退货原因
     * @mbggenerated Thu Jul 11 16:42:43 CST 2019
     */
    private String thReason;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORDTHBODY.WPSPRICE
     * 无税配送价
     * @mbggenerated Thu Jul 11 16:42:43 CST 2019
     */
    private Double wpsPrice;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORDTHBODY.WPSTOTAL
     * 无税配送金额
     * @mbggenerated Thu Jul 11 16:42:43 CST 2019
     */
    private Double wpsTotal;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORDTHBODY.XTAXRATE
     * 销项税率
     * @mbggenerated Thu Jul 11 16:42:43 CST 2019
     */
    private Double xTaxRate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORDTHBODY.XTAXTOTAL
     * 销项税额
     * @mbggenerated Thu Jul 11 16:42:43 CST 2019
     */
    private Double xTaxTotal;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORDTHBODY.KWCODE
     * 库位编码
     * @mbggenerated Thu Jul 11 16:42:43 CST 2019
     */
    private String kwCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORDTHBODY.STLCURRPSPRICE
     *
     * @mbggenerated Thu Jul 11 16:42:43 CST 2019
     */
    private Double stlCurrPsPrice;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORDTHBODY.STLCURRPSCOST
     *
     * @mbggenerated Thu Jul 11 16:42:43 CST 2019
     */
    private Double stlCurrPsCost;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORDTHBODY.MATERIALCODE
     *
     * @mbggenerated Thu Jul 11 16:42:43 CST 2019
     */
    private String materialCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORDTHBODY.SUPCODE
     * 供应商编码:记录总部的主供应商
     * @mbggenerated Thu Jul 11 16:42:43 CST 2019
     */
    private String supCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORDTHBODY.SUPNAME
     * 供应商名称
     * @mbggenerated Thu Jul 11 16:42:43 CST 2019
     */
    private String supName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORDTHBODY.UDP1
     *
     * @mbggenerated Thu Jul 11 16:42:43 CST 2019
     */
    private String udp1;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORDTHBODY.UDP2
     *
     * @mbggenerated Thu Jul 11 16:42:43 CST 2019
     */
    private String udp2;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORDTHBODY.UDP3
     *
     * @mbggenerated Thu Jul 11 16:42:43 CST 2019
     */
    private String udp3;

    /**
     * @return the billNo
     */
    public String getBillNo() {
        return billNo;
    }

    /**
     * @param billNo the billNo to set
     */
    public void setBillNo(String billNo) {
        this.billNo = billNo;
    }

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
     * @return the exPluName
     */
    public String getExPluName() {
        return exPluName;
    }

    /**
     * @param exPluName the exPluName to set
     */
    public void setExPluName(String exPluName) {
        this.exPluName = exPluName;
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
     * @return the jTaxRate
     */
    public Double getjTaxRate() {
        return jTaxRate;
    }

    /**
     * @param jTaxRate the jTaxRate to set
     */
    public void setjTaxRate(Double jTaxRate) {
        this.jTaxRate = jTaxRate;
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
     * @return the wpsPrice
     */
    public Double getWpsPrice() {
        return wpsPrice;
    }

    /**
     * @param wpsPrice the wpsPrice to set
     */
    public void setWpsPrice(Double wpsPrice) {
        this.wpsPrice = wpsPrice;
    }

    /**
     * @return the wpsTotal
     */
    public Double getWpsTotal() {
        return wpsTotal;
    }

    /**
     * @param wpsTotal the wpsTotal to set
     */
    public void setWpsTotal(Double wpsTotal) {
        this.wpsTotal = wpsTotal;
    }

    /**
     * @return the xTaxRate
     */
    public Double getxTaxRate() {
        return xTaxRate;
    }

    /**
     * @param xTaxRate the xTaxRate to set
     */
    public void setxTaxRate(Double xTaxRate) {
        this.xTaxRate = xTaxRate;
    }

    /**
     * @return the xTaxTotal
     */
    public Double getxTaxTotal() {
        return xTaxTotal;
    }

    /**
     * @param xTaxTotal the xTaxTotal to set
     */
    public void setxTaxTotal(Double xTaxTotal) {
        this.xTaxTotal = xTaxTotal;
    }

    /**
     * @return the kwCode
     */
    public String getKwCode() {
        return kwCode;
    }

    /**
     * @param kwCode the kwCode to set
     */
    public void setKwCode(String kwCode) {
        this.kwCode = kwCode;
    }

    /**
     * @return the stlCurrPsPrice
     */
    public Double getStlCurrPsPrice() {
        return stlCurrPsPrice;
    }

    /**
     * @param stlCurrPsPrice the stlCurrPsPrice to set
     */
    public void setStlCurrPsPrice(Double stlCurrPsPrice) {
        this.stlCurrPsPrice = stlCurrPsPrice;
    }

    /**
     * @return the stlCurrPsCost
     */
    public Double getStlCurrPsCost() {
        return stlCurrPsCost;
    }

    /**
     * @param stlCurrPsCost the stlCurrPsCost to set
     */
    public void setStlCurrPsCost(Double stlCurrPsCost) {
        this.stlCurrPsCost = stlCurrPsCost;
    }

    /**
     * @return the materialCode
     */
    public String getMaterialCode() {
        return materialCode;
    }

    /**
     * @param materialCode the materialCode to set
     */
    public void setMaterialCode(String materialCode) {
        this.materialCode = materialCode;
    }

    /**
     * @return the supCode
     */
    public String getSupCode() {
        return supCode;
    }

    /**
     * @param supCode the supCode to set
     */
    public void setSupCode(String supCode) {
        this.supCode = supCode;
    }

    /**
     * @return the supName
     */
    public String getSupName() {
        return supName;
    }

    /**
     * @param supName the supName to set
     */
    public void setSupName(String supName) {
        this.supName = supName;
    }

    /**
     * @return the udp1
     */
    public String getUdp1() {
        return udp1;
    }

    /**
     * @param udp1 the udp1 to set
     */
    public void setUdp1(String udp1) {
        this.udp1 = udp1;
    }

    /**
     * @return the udp2
     */
    public String getUdp2() {
        return udp2;
    }

    /**
     * @param udp2 the udp2 to set
     */
    public void setUdp2(String udp2) {
        this.udp2 = udp2;
    }

    /**
     * @return the udp3
     */
    public String getUdp3() {
        return udp3;
    }

    /**
     * @param udp3 the udp3 to set
     */
    public void setUdp3(String udp3) {
        this.udp3 = udp3;
    }
}