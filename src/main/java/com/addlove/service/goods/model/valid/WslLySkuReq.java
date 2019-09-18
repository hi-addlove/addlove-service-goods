package com.addlove.service.goods.model.valid;

public class WslLySkuReq {
    /**组织编码 */
    private String orgCode;
    
    /**部门ID */
    private Long depId;
    
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
