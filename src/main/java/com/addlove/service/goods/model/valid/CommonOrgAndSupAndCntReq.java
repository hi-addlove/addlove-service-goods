package com.addlove.service.goods.model.valid;

public class CommonOrgAndSupAndCntReq {
    /** 组织编码 */
    private String orgCode;
    
    /** 供应商编码 */
    private String etpCode;
    
    /** 合同ID */
    private Long cntId;
    
    /** 部门ID */
    private Long depId;
    
    /** 仓库编码 */
    private String ckCode;
    
    /**分类类型 */
    private String flCode;

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
     * @return the etpCode
     */
    public String getEtpCode() {
        return etpCode;
    }

    /**
     * @param etpCode the etpCode to set
     */
    public void setEtpCode(String etpCode) {
        this.etpCode = etpCode;
    }

    /**
     * @return the cntId
     */
    public Long getCntId() {
        return cntId;
    }

    /**
     * @param cntId the cntId to set
     */
    public void setCntId(Long cntId) {
        this.cntId = cntId;
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
     * @return the flCode
     */
    public String getFlCode() {
        return flCode;
    }

    /**
     * @param flCode the flCode to set
     */
    public void setFlCode(String flCode) {
        this.flCode = flCode;
    }
}
