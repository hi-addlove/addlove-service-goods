package com.addlove.service.goods.model.valid;

/**
 * 获取盘点商品请求参数
 * @author lw
 *
 */
public class AdlYhSkuReq {
    /**组织编码 */
    private String orgCode;
    
    /**部门ID */
    private Long depId;
    
    /** 模板编码*/
    private String modelCode;
    
    /** 要货波次*/
    private String yhBc;

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
     * @return the modelCode
     */
    public String getModelCode() {
        return modelCode;
    }

    /**
     * @param modelCode the modelCode to set
     */
    public void setModelCode(String modelCode) {
        this.modelCode = modelCode;
    }

    /**
     * @return the yhBc
     */
    public String getYhBc() {
        return yhBc;
    }

    /**
     * @param yhBc the yhBc to set
     */
    public void setYhBc(String yhBc) {
        this.yhBc = yhBc;
    }
}
