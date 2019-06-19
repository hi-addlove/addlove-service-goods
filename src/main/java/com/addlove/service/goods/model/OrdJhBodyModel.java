package com.addlove.service.goods.model;

/**
 * 验收单明细表
 * @author lw
 *
 */
public class OrdJhBodyModel {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORDJHBODY.BILLNO
     * 单据号
     * @mbggenerated Tue Jun 04 15:32:25 CST 2019
     */
    private String billNo;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORDJHBODY.SERIALNO
     * 序号
     * @mbggenerated Tue Jun 04 15:32:25 CST 2019
     */
    private Long serialNo;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORDJHBODY.TOSERIALNO
     * 序号参照
     * @mbggenerated Tue Jun 04 15:32:25 CST 2019
     */
    private Long toSerialNo;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORDJHBODY.PLUID
     * 商品ID
     * @mbggenerated Tue Jun 04 15:32:25 CST 2019
     */
    private Long pluId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORDJHBODY.PLUCODE
     * 商品编码
     * @mbggenerated Tue Jun 04 15:32:25 CST 2019
     */
    private String pluCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORDJHBODY.PLUNAME
     * 商品名称
     * @mbggenerated Tue Jun 04 15:32:25 CST 2019
     */
    private String pluName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORDJHBODY.EXPLUCODE
     * 特征码编码
     * @mbggenerated Tue Jun 04 15:32:25 CST 2019
     */
    private String exPluCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORDJHBODY.EXPLUNAME
     * 商品名称
     * @mbggenerated Tue Jun 04 15:32:25 CST 2019
     */
    private String exPluName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORDJHBODY.BARCODE
     * 商品条码
     * @mbggenerated Tue Jun 04 15:32:25 CST 2019
     */
    private String barCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORDJHBODY.SPEC
     * 规格
     * @mbggenerated Tue Jun 04 15:32:25 CST 2019
     */
    private String spec;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORDJHBODY.UNIT
     * 单位
     * @mbggenerated Tue Jun 04 15:32:25 CST 2019
     */
    private String unit;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORDJHBODY.CARGONO
     * 货号
     * @mbggenerated Tue Jun 04 15:32:25 CST 2019
     */
    private String carGoNo;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORDJHBODY.PLUTYPE
     * 商品类型
     * @mbggenerated Tue Jun 04 15:32:25 CST 2019
     */
    private String pluType;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORDJHBODY.DEPID
     * 部门Id
     * @mbggenerated Tue Jun 04 15:32:25 CST 2019
     */
    private Long depId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORDJHBODY.DEPCODE
     * 部门编码
     * @mbggenerated Tue Jun 04 15:32:25 CST 2019
     */
    private String depCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORDJHBODY.DEPNAME
     * 部门名称
     * @mbggenerated Tue Jun 04 15:32:25 CST 2019
     */
    private String depName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORDJHBODY.HJPRICE
     * 含税进价
     * @mbggenerated Tue Jun 04 15:32:25 CST 2019
     */
    private Double hjPrice;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORDJHBODY.WJPRICE
     * 无税进价
     * @mbggenerated Tue Jun 04 15:32:25 CST 2019
     */
    private Double wjPrice;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORDJHBODY.PSPRICE
     * 配送价
     * @mbggenerated Tue Jun 04 15:32:25 CST 2019
     */
    private Double psPrice;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORDJHBODY.PRICE
     * 售价
     * @mbggenerated Tue Jun 04 15:32:25 CST 2019
     */
    private Double price;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORDJHBODY.JTAXRATE
     * 进项税率
     * @mbggenerated Tue Jun 04 15:32:25 CST 2019
     */
    private Integer jTaxRate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORDJHBODY.CKCODE
     * 仓库编码
     * @mbggenerated Tue Jun 04 15:32:25 CST 2019
     */
    private String ckCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORDJHBODY.CKNAME
     * 仓库名称
     * @mbggenerated Tue Jun 04 15:32:25 CST 2019
     */
    private String ckName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORDJHBODY.PACKUNIT
     * 包装单位
     * @mbggenerated Tue Jun 04 15:32:25 CST 2019
     */
    private String packUnit;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORDJHBODY.PACKQTY
     * 包装细数
     * @mbggenerated Tue Jun 04 15:32:25 CST 2019
     */
    private Double packQty;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORDJHBODY.PACKCOUNT
     * 包装数量
     * @mbggenerated Tue Jun 04 15:32:25 CST 2019
     */
    private Double packCount;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORDJHBODY.SGLCOUNT
     * 单件件数
     * @mbggenerated Tue Jun 04 15:32:25 CST 2019
     */
    private Double sglCount;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORDJHBODY.JHCOUNT
     * 进货数量
     * @mbggenerated Tue Jun 04 15:32:25 CST 2019
     */
    private Double jhCount;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORDJHBODY.PSSHCOUNT
     * 配送收货数量（实际数量）默认等于进货数量
     * @mbggenerated Tue Jun 04 15:32:25 CST 2019
     */
    private Double psShCount;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORDJHBODY.HCOST
     * 含税进价金额
     * @mbggenerated Tue Jun 04 15:32:25 CST 2019
     */
    private Double hCost;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORDJHBODY.WCOST
     * 无税进价金额
     * @mbggenerated Tue Jun 04 15:32:25 CST 2019
     */
    private Double wCost;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORDJHBODY.JTAXTOTAL
     * 进项税额
     * @mbggenerated Tue Jun 04 15:32:25 CST 2019
     */
    private Double jTaxTotal;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORDJHBODY.PSCOST
     * 配送金额
     * @mbggenerated Tue Jun 04 15:32:25 CST 2019
     */
    private Double psCost;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORDJHBODY.STOTAL
     * 售价金额
     * @mbggenerated Tue Jun 04 15:32:25 CST 2019
     */
    private Double sTotal;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORDJHBODY.CJTOTAL
     * 进销差价
     * @mbggenerated Tue Jun 04 15:32:25 CST 2019
     */
    private Double cjTotal;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORDJHBODY.CJRATE
     * 进销差率
     * @mbggenerated Tue Jun 04 15:32:25 CST 2019
     */
    private Double cjRate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORDJHBODY.SCDATE
     * 生产日期
     * @mbggenerated Tue Jun 04 15:32:25 CST 2019
     */
    private String scDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORDJHBODY.DQDATE
     * 到期日期
     * @mbggenerated Tue Jun 04 15:32:25 CST 2019
     */
    private String dqDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORDJHBODY.BZDAYS
     * 保质期
     * @mbggenerated Tue Jun 04 15:32:25 CST 2019
     */
    private Integer bzDays;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORDJHBODY.ZPTYPE
     * 赠送类型:0-非赠品 1-赠送给顾客 2-赠送给商场 当商品类型设为0，2时，此字段可设为0或2；
     * 当商品类型设为1时，此字段设为1。对于赠送，入库作为正品处理
     * @mbggenerated Tue Jun 04 15:32:25 CST 2019
     */
    private String zpType;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORDJHBODY.CXINFO
     * 赠品信息
     * @mbggenerated Tue Jun 04 15:32:25 CST 2019
     */
    private String cxInfo;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORDJHBODY.REMARK
     * 备注
     * @mbggenerated Tue Jun 04 15:32:25 CST 2019
     */
    private String remark;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORDJHBODY.HJSTOTAL
     * 含税结算金额
     * @mbggenerated Tue Jun 04 15:32:25 CST 2019
     */
    private Double hjsTotal;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORDJHBODY.WJSTOTAL
     * 无税结算金额
     * @mbggenerated Tue Jun 04 15:32:25 CST 2019
     */
    private Double wjsTotal;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORDJHBODY.JTAXCALTYPE
     * 进项税计算类型:0-普通商品；进项税=无税进价金额*进项税率；1-农副产品；进项税=含税进价金额*进项税率
     * @mbggenerated Tue Jun 04 15:32:25 CST 2019
     */
    private String jTaxCalType;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORDJHBODY.PRICETYPE
     * 价格类型:直送验收时有效，1-成本配送价；2-加点配送价；3-固定价配送价；4-自由价配送价；
     * 当4是允许修改配送价，否则不允许修改
     * @mbggenerated Tue Jun 04 15:32:25 CST 2019
     */
    private String priceType;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORDJHBODY.JDRATE
     * 加点率:直送验收时有效，当PriceType为2时有效，依据加点率来计算配送价
     * @mbggenerated Tue Jun 04 15:32:25 CST 2019
     */
    private Long jdRate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORDJHBODY.KWCODE
     * 库位编码
     * @mbggenerated Tue Jun 04 15:32:25 CST 2019
     */
    private String kwCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORDJHBODY.WPSPRICE
     * 无税配送价:当进价为0时不回填供应商商品分析参数表中的最新进价，
     * 不为0时需要回填供应商商品分析参数表中的最新进价信息
     * @mbggenerated Tue Jun 04 15:32:25 CST 2019
     */
    private Double wPsPrice;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORDJHBODY.WPSCOST
     * 无税配送金额:直送验收时使用
     * @mbggenerated Tue Jun 04 15:32:25 CST 2019
     */
    private Double wPsCost;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORDJHBODY.XTAXRATE
     * 销项税率
     * @mbggenerated Tue Jun 04 15:32:25 CST 2019
     */
    private Integer xTaxRate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORDJHBODY.XTAXTOTAL
     * 销项税额
     * @mbggenerated Tue Jun 04 15:32:25 CST 2019
     */
    private Double xTaxTotal;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORDJHBODY.BARCODE_P
     * 
     * @mbggenerated Tue Jun 04 15:32:25 CST 2019
     */
    private String barCodeP;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORDJHBODY.MATERIALCODE
     * 配送结算价
     * @mbggenerated Tue Jun 04 15:32:25 CST 2019
     */
    private String materialCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORDJHBODY.STLCURRPSPRICE
     *
     * @mbggenerated Tue Jun 04 15:32:25 CST 2019
     */
    private Double stlCurrPsPrice;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORDJHBODY.STLCURRPSCOST
     *
     * @mbggenerated Tue Jun 04 15:32:25 CST 2019
     */
    private Double stlCurrPsCost;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORDJHBODY.ASKTYPE
     *
     * @mbggenerated Tue Jun 04 15:32:25 CST 2019
     */
    private String askType;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORDJHBODY.GRPCODE
     *
     * @mbggenerated Tue Jun 04 15:32:25 CST 2019
     */
    private String grpCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORDJHBODY.SHOPBILLNO
     *
     * @mbggenerated Tue Jun 04 15:32:25 CST 2019
     */
    private String shopBillNo;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORDJHBODY.SHOPSERIALNO
     *
     * @mbggenerated Tue Jun 04 15:32:25 CST 2019
     */
    private Long shopSerialNo;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORDJHBODY.YJHCOUNT
     *
     * @mbggenerated Tue Jun 04 15:32:25 CST 2019
     */
    private Double yjhCount;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORDJHBODY.LHJPRICE
     *
     * @mbggenerated Tue Jun 04 15:32:25 CST 2019
     */
    private Double lhjPrice;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORDJHBODY.LWJPRICE
     *
     * @mbggenerated Tue Jun 04 15:32:25 CST 2019
     */
    private Double lwjPrice;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORDJHBODY.TRACEREMARK
     *
     * @mbggenerated Tue Jun 04 15:32:25 CST 2019
     */
    private String traceRemark;

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
     * @return the toSerialNo
     */
    public Long getToSerialNo() {
        return toSerialNo;
    }

    /**
     * @param toSerialNo the toSerialNo to set
     */
    public void setToSerialNo(Long toSerialNo) {
        this.toSerialNo = toSerialNo;
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
     * @return the ckCode
     */
    public String getCkCode() {
        return ckCode;
    }

    /**
     * @param ckCode the ckCode to set
     */
    public void setCkCode(String ckCode) {
        this.ckCode = ckCode;
    }

    /**
     * @return the ckName
     */
    public String getCkName() {
        return ckName;
    }

    /**
     * @param ckName the ckName to set
     */
    public void setCkName(String ckName) {
        this.ckName = ckName;
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
     * @return the jhCount
     */
    public Double getJhCount() {
        return jhCount;
    }

    /**
     * @param jhCount the jhCount to set
     */
    public void setJhCount(Double jhCount) {
        this.jhCount = jhCount;
    }

    /**
     * @return the psShCount
     */
    public Double getPsShCount() {
        return psShCount;
    }

    /**
     * @param psShCount the psShCount to set
     */
    public void setPsShCount(Double psShCount) {
        this.psShCount = psShCount;
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
     * @return the scDate
     */
    public String getScDate() {
        return scDate;
    }

    /**
     * @param scDate the scDate to set
     */
    public void setScDate(String scDate) {
        this.scDate = scDate;
    }

    /**
     * @return the dqDate
     */
    public String getDqDate() {
        return dqDate;
    }

    /**
     * @param dqDate the dqDate to set
     */
    public void setDqDate(String dqDate) {
        this.dqDate = dqDate;
    }

    /**
     * @return the bzDays
     */
    public Integer getBzDays() {
        return bzDays;
    }

    /**
     * @param bzDays the bzDays to set
     */
    public void setBzDays(Integer bzDays) {
        this.bzDays = bzDays;
    }

    /**
     * @return the zpType
     */
    public String getZpType() {
        return zpType;
    }

    /**
     * @param zpType the zpType to set
     */
    public void setZpType(String zpType) {
        this.zpType = zpType;
    }

    /**
     * @return the cxInfo
     */
    public String getCxInfo() {
        return cxInfo;
    }

    /**
     * @param cxInfo the cxInfo to set
     */
    public void setCxInfo(String cxInfo) {
        this.cxInfo = cxInfo;
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
     * @return the jTaxCalType
     */
    public String getjTaxCalType() {
        return jTaxCalType;
    }

    /**
     * @param jTaxCalType the jTaxCalType to set
     */
    public void setjTaxCalType(String jTaxCalType) {
        this.jTaxCalType = jTaxCalType;
    }

    /**
     * @return the priceType
     */
    public String getPriceType() {
        return priceType;
    }

    /**
     * @param priceType the priceType to set
     */
    public void setPriceType(String priceType) {
        this.priceType = priceType;
    }

    /**
     * @return the jdRate
     */
    public Long getJdRate() {
        return jdRate;
    }

    /**
     * @param jdRate the jdRate to set
     */
    public void setJdRate(Long jdRate) {
        this.jdRate = jdRate;
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
     * @return the wPsPrice
     */
    public Double getwPsPrice() {
        return wPsPrice;
    }

    /**
     * @param wPsPrice the wPsPrice to set
     */
    public void setwPsPrice(Double wPsPrice) {
        this.wPsPrice = wPsPrice;
    }

    /**
     * @return the wPsCost
     */
    public Double getwPsCost() {
        return wPsCost;
    }

    /**
     * @param wPsCost the wPsCost to set
     */
    public void setwPsCost(Double wPsCost) {
        this.wPsCost = wPsCost;
    }

    /**
     * @return the xTaxRate
     */
    public Integer getxTaxRate() {
        return xTaxRate;
    }

    /**
     * @param xTaxRate the xTaxRate to set
     */
    public void setxTaxRate(Integer xTaxRate) {
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
     * @return the barCodeP
     */
    public String getBarCodeP() {
        return barCodeP;
    }

    /**
     * @param barCodeP the barCodeP to set
     */
    public void setBarCodeP(String barCodeP) {
        this.barCodeP = barCodeP;
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
     * @return the askType
     */
    public String getAskType() {
        return askType;
    }

    /**
     * @param askType the askType to set
     */
    public void setAskType(String askType) {
        this.askType = askType;
    }

    /**
     * @return the grpCode
     */
    public String getGrpCode() {
        return grpCode;
    }

    /**
     * @param grpCode the grpCode to set
     */
    public void setGrpCode(String grpCode) {
        this.grpCode = grpCode;
    }

    /**
     * @return the shopBillNo
     */
    public String getShopBillNo() {
        return shopBillNo;
    }

    /**
     * @param shopBillNo the shopBillNo to set
     */
    public void setShopBillNo(String shopBillNo) {
        this.shopBillNo = shopBillNo;
    }

    /**
     * @return the shopSerialNo
     */
    public Long getShopSerialNo() {
        return shopSerialNo;
    }

    /**
     * @param shopSerialNo the shopSerialNo to set
     */
    public void setShopSerialNo(Long shopSerialNo) {
        this.shopSerialNo = shopSerialNo;
    }

    /**
     * @return the yjhCount
     */
    public Double getYjhCount() {
        return yjhCount;
    }

    /**
     * @param yjhCount the yjhCount to set
     */
    public void setYjhCount(Double yjhCount) {
        this.yjhCount = yjhCount;
    }

    /**
     * @return the lhjPrice
     */
    public Double getLhjPrice() {
        return lhjPrice;
    }

    /**
     * @param lhjPrice the lhjPrice to set
     */
    public void setLhjPrice(Double lhjPrice) {
        this.lhjPrice = lhjPrice;
    }

    /**
     * @return the lwjPrice
     */
    public Double getLwjPrice() {
        return lwjPrice;
    }

    /**
     * @param lwjPrice the lwjPrice to set
     */
    public void setLwjPrice(Double lwjPrice) {
        this.lwjPrice = lwjPrice;
    }

    /**
     * @return the traceRemark
     */
    public String getTraceRemark() {
        return traceRemark;
    }

    /**
     * @param traceRemark the traceRemark to set
     */
    public void setTraceRemark(String traceRemark) {
        this.traceRemark = traceRemark;
    }
}
