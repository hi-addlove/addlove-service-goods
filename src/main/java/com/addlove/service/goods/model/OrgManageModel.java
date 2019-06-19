package com.addlove.service.goods.model;

/**
 * 组织表
 * @author lw
 *
 */
public class OrgManageModel {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORGMANAGE.ORGCODE
     * 组织编码
     * @mbggenerated Wed Jun 19 10:48:06 CST 2019
     */
    private String orgCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORGMANAGE.INORGCODE
     * 内部组织编码
     * @mbggenerated Wed Jun 19 10:48:06 CST 2019
     */
    private String inOrgCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORGMANAGE.ORGNAME
     * 组织名称
     * @mbggenerated Wed Jun 19 10:48:06 CST 2019
     */
    private String orgName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORGMANAGE.AREA
     * 营业面积（平方米）
     * @mbggenerated Wed Jun 19 10:48:06 CST 2019
     */
    private Double area;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORGMANAGE.ORGTYPE
     * 组织类型
     * @mbggenerated Wed Jun 19 10:48:06 CST 2019
     */
    private String orgType;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORGMANAGE.PREORGCODE
     * 上级组织
     * @mbggenerated Wed Jun 19 10:48:06 CST 2019
     */
    private String preOrgCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORGMANAGE.ORGCLASS
     * 组织分类
     * @mbggenerated Wed Jun 19 10:48:06 CST 2019
     */
    private String orgClass;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORGMANAGE.ISMANASTOCK
     * 管理库存
     * @mbggenerated Wed Jun 19 10:48:06 CST 2019
     */
    private String isManaStock;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORGMANAGE.FORGCODE
     * 财务组织
     * @mbggenerated Wed Jun 19 10:48:06 CST 2019
     */
    private String fOrgCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORGMANAGE.ZBORGCODE
     * 业务控制组织
     * @mbggenerated Wed Jun 19 10:48:06 CST 2019
     */
    private String zbOrgCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORGMANAGE.WLAREACODE
     * 物流区域
     * @mbggenerated Wed Jun 19 10:48:06 CST 2019
     */
    private String wlAreaCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORGMANAGE.ACCLEVEL
     * 核算级别
     * @mbggenerated Wed Jun 19 10:48:06 CST 2019
     */
    private String accLevel;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORGMANAGE.DBSVRNAME
     * 数据库服务器名称
     * @mbggenerated Wed Jun 19 10:48:06 CST 2019
     */
    private String dbSvrName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORGMANAGE.DBNAME
     * 数据库名称
     * @mbggenerated Wed Jun 19 10:48:06 CST 2019
     */
    private String dbName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORGMANAGE.COMMMODE
     * 通讯方式
     * @mbggenerated Wed Jun 19 10:48:06 CST 2019
     */
    private String commMode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORGMANAGE.BUSINESSMODE
     * 商圈类型
     * @mbggenerated Wed Jun 19 10:48:06 CST 2019
     */
    private String businessMode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORGMANAGE.FACECODE
     * 通信接口
     * @mbggenerated Wed Jun 19 10:48:06 CST 2019
     */
    private String faceCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORGMANAGE.COMMPATHOUT
     * 通讯导出路径
     * @mbggenerated Wed Jun 19 10:48:06 CST 2019
     */
    private String commPathOut;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORGMANAGE.COMMPATHIN
     * 通讯导入路径
     * @mbggenerated Wed Jun 19 10:48:06 CST 2019
     */
    private String commPathIn;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORGMANAGE.YHPRI
     * 要货优先级
     * @mbggenerated Wed Jun 19 10:48:06 CST 2019
     */
    private Integer yhPri;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORGMANAGE.ISMBZQ
     * 管理保质期
     * @mbggenerated Wed Jun 19 10:48:06 CST 2019
     */
    private String isMbzq;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORGMANAGE.RKBJRATE
     * 入库提前报警天数比例
     * @mbggenerated Wed Jun 19 10:48:06 CST 2019
     */
    private Double rkBjRate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORGMANAGE.CKBJRATE
     * 出库提前报警天数比例
     * @mbggenerated Wed Jun 19 10:48:06 CST 2019
     */
    private Double ckBjRate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORGMANAGE.GQBJRATE
     * 过期提前报警天数比例
     * @mbggenerated Wed Jun 19 10:48:06 CST 2019
     */
    private Double gqBjRate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORGMANAGE.REMARK
     * 备注
     * @mbggenerated Wed Jun 19 10:48:06 CST 2019
     */
    private String remark;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORGMANAGE.DBID
     * 数据库ID
     * @mbggenerated Wed Jun 19 10:48:06 CST 2019
     */
    private String dbId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORGMANAGE.ISENABLE
     * 是否生效
     * @mbggenerated Wed Jun 19 10:48:06 CST 2019
     */
    private String isEnable;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORGMANAGE.PRICEMODE
     * 商品价格控制方式
     * @mbggenerated Wed Jun 19 10:48:06 CST 2019
     */
    private String priceMode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORGMANAGE.POSCOUNT
     * Pos点数
     * @mbggenerated Wed Jun 19 10:48:06 CST 2019
     */
    private Integer posCount;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORGMANAGE.POSCOUNTVERIFY
     * 校验信息
     * @mbggenerated Wed Jun 19 10:48:06 CST 2019
     */
    private String posCountVerify;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORGMANAGE.SHORPGRADE
     * 门店等级
     * @mbggenerated Wed Jun 19 10:48:06 CST 2019
     */
    private String shorpGrade;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORGMANAGE.MODALSHOP
     * 样板门店
     * @mbggenerated Wed Jun 19 10:48:06 CST 2019
     */
    private String modalShop;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORGMANAGE.QUOTEAREA
     * 牌价所属地
     * @mbggenerated Wed Jun 19 10:48:06 CST 2019
     */
    private String quoteArea;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TORGMANAGE.MACHINECONFIG
     * 门店机器配置
     * @mbggenerated Wed Jun 19 10:48:06 CST 2019
     */
    private String machineConfig;

    /**
     * @return the orgCode
     */
    public String getOrgCode() {
        return orgCode;
    }

    /**
     * @param orgCode the orgCode to set
     */
    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    /**
     * @return the inOrgCode
     */
    public String getInOrgCode() {
        return inOrgCode;
    }

    /**
     * @param inOrgCode the inOrgCode to set
     */
    public void setInOrgCode(String inOrgCode) {
        this.inOrgCode = inOrgCode;
    }

    /**
     * @return the orgName
     */
    public String getOrgName() {
        return orgName;
    }

    /**
     * @param orgName the orgName to set
     */
    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    /**
     * @return the area
     */
    public Double getArea() {
        return area;
    }

    /**
     * @param area the area to set
     */
    public void setArea(Double area) {
        this.area = area;
    }

    /**
     * @return the orgType
     */
    public String getOrgType() {
        return orgType;
    }

    /**
     * @param orgType the orgType to set
     */
    public void setOrgType(String orgType) {
        this.orgType = orgType;
    }

    /**
     * @return the preOrgCode
     */
    public String getPreOrgCode() {
        return preOrgCode;
    }

    /**
     * @param preOrgCode the preOrgCode to set
     */
    public void setPreOrgCode(String preOrgCode) {
        this.preOrgCode = preOrgCode;
    }

    /**
     * @return the orgClass
     */
    public String getOrgClass() {
        return orgClass;
    }

    /**
     * @param orgClass the orgClass to set
     */
    public void setOrgClass(String orgClass) {
        this.orgClass = orgClass;
    }

    /**
     * @return the isManaStock
     */
    public String getIsManaStock() {
        return isManaStock;
    }

    /**
     * @param isManaStock the isManaStock to set
     */
    public void setIsManaStock(String isManaStock) {
        this.isManaStock = isManaStock;
    }

    /**
     * @return the fOrgCode
     */
    public String getfOrgCode() {
        return fOrgCode;
    }

    /**
     * @param fOrgCode the fOrgCode to set
     */
    public void setfOrgCode(String fOrgCode) {
        this.fOrgCode = fOrgCode;
    }

    /**
     * @return the zbOrgCode
     */
    public String getZbOrgCode() {
        return zbOrgCode;
    }

    /**
     * @param zbOrgCode the zbOrgCode to set
     */
    public void setZbOrgCode(String zbOrgCode) {
        this.zbOrgCode = zbOrgCode;
    }

    /**
     * @return the wlAreaCode
     */
    public String getWlAreaCode() {
        return wlAreaCode;
    }

    /**
     * @param wlAreaCode the wlAreaCode to set
     */
    public void setWlAreaCode(String wlAreaCode) {
        this.wlAreaCode = wlAreaCode;
    }

    /**
     * @return the accLevel
     */
    public String getAccLevel() {
        return accLevel;
    }

    /**
     * @param accLevel the accLevel to set
     */
    public void setAccLevel(String accLevel) {
        this.accLevel = accLevel;
    }

    /**
     * @return the dbSvrName
     */
    public String getDbSvrName() {
        return dbSvrName;
    }

    /**
     * @param dbSvrName the dbSvrName to set
     */
    public void setDbSvrName(String dbSvrName) {
        this.dbSvrName = dbSvrName;
    }

    /**
     * @return the dbName
     */
    public String getDbName() {
        return dbName;
    }

    /**
     * @param dbName the dbName to set
     */
    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    /**
     * @return the commMode
     */
    public String getCommMode() {
        return commMode;
    }

    /**
     * @param commMode the commMode to set
     */
    public void setCommMode(String commMode) {
        this.commMode = commMode;
    }

    /**
     * @return the businessMode
     */
    public String getBusinessMode() {
        return businessMode;
    }

    /**
     * @param businessMode the businessMode to set
     */
    public void setBusinessMode(String businessMode) {
        this.businessMode = businessMode;
    }

    /**
     * @return the faceCode
     */
    public String getFaceCode() {
        return faceCode;
    }

    /**
     * @param faceCode the faceCode to set
     */
    public void setFaceCode(String faceCode) {
        this.faceCode = faceCode;
    }

    /**
     * @return the commPathOut
     */
    public String getCommPathOut() {
        return commPathOut;
    }

    /**
     * @param commPathOut the commPathOut to set
     */
    public void setCommPathOut(String commPathOut) {
        this.commPathOut = commPathOut;
    }

    /**
     * @return the commPathIn
     */
    public String getCommPathIn() {
        return commPathIn;
    }

    /**
     * @param commPathIn the commPathIn to set
     */
    public void setCommPathIn(String commPathIn) {
        this.commPathIn = commPathIn;
    }

    /**
     * @return the yhPri
     */
    public Integer getYhPri() {
        return yhPri;
    }

    /**
     * @param yhPri the yhPri to set
     */
    public void setYhPri(Integer yhPri) {
        this.yhPri = yhPri;
    }

    /**
     * @return the isMbzq
     */
    public String getIsMbzq() {
        return isMbzq;
    }

    /**
     * @param isMbzq the isMbzq to set
     */
    public void setIsMbzq(String isMbzq) {
        this.isMbzq = isMbzq;
    }

    /**
     * @return the rkBjRate
     */
    public Double getRkBjRate() {
        return rkBjRate;
    }

    /**
     * @param rkBjRate the rkBjRate to set
     */
    public void setRkBjRate(Double rkBjRate) {
        this.rkBjRate = rkBjRate;
    }

    /**
     * @return the ckBjRate
     */
    public Double getCkBjRate() {
        return ckBjRate;
    }

    /**
     * @param ckBjRate the ckBjRate to set
     */
    public void setCkBjRate(Double ckBjRate) {
        this.ckBjRate = ckBjRate;
    }

    /**
     * @return the gqBjRate
     */
    public Double getGqBjRate() {
        return gqBjRate;
    }

    /**
     * @param gqBjRate the gqBjRate to set
     */
    public void setGqBjRate(Double gqBjRate) {
        this.gqBjRate = gqBjRate;
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
     * @return the dbId
     */
    public String getDbId() {
        return dbId;
    }

    /**
     * @param dbId the dbId to set
     */
    public void setDbId(String dbId) {
        this.dbId = dbId;
    }

    /**
     * @return the isEnable
     */
    public String getIsEnable() {
        return isEnable;
    }

    /**
     * @param isEnable the isEnable to set
     */
    public void setIsEnable(String isEnable) {
        this.isEnable = isEnable;
    }

    /**
     * @return the priceMode
     */
    public String getPriceMode() {
        return priceMode;
    }

    /**
     * @param priceMode the priceMode to set
     */
    public void setPriceMode(String priceMode) {
        this.priceMode = priceMode;
    }

    /**
     * @return the posCount
     */
    public Integer getPosCount() {
        return posCount;
    }

    /**
     * @param posCount the posCount to set
     */
    public void setPosCount(Integer posCount) {
        this.posCount = posCount;
    }

    /**
     * @return the posCountVerify
     */
    public String getPosCountVerify() {
        return posCountVerify;
    }

    /**
     * @param posCountVerify the posCountVerify to set
     */
    public void setPosCountVerify(String posCountVerify) {
        this.posCountVerify = posCountVerify;
    }

    /**
     * @return the shorpGrade
     */
    public String getShorpGrade() {
        return shorpGrade;
    }

    /**
     * @param shorpGrade the shorpGrade to set
     */
    public void setShorpGrade(String shorpGrade) {
        this.shorpGrade = shorpGrade;
    }

    /**
     * @return the modalShop
     */
    public String getModalShop() {
        return modalShop;
    }

    /**
     * @param modalShop the modalShop to set
     */
    public void setModalShop(String modalShop) {
        this.modalShop = modalShop;
    }

    /**
     * @return the quoteArea
     */
    public String getQuoteArea() {
        return quoteArea;
    }

    /**
     * @param quoteArea the quoteArea to set
     */
    public void setQuoteArea(String quoteArea) {
        this.quoteArea = quoteArea;
    }

    /**
     * @return the machineConfig
     */
    public String getMachineConfig() {
        return machineConfig;
    }

    /**
     * @param machineConfig the machineConfig to set
     */
    public void setMachineConfig(String machineConfig) {
        this.machineConfig = machineConfig;
    }
}
