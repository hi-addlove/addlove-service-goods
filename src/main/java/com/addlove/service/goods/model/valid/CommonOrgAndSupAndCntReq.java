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
}
