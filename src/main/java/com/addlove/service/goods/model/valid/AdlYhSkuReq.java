package com.addlove.service.goods.model.valid;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 获取盘点商品请求参数
 * @author lw
 *
 */
public class AdlYhSkuReq {
    /**组织编码 */
    @NotBlank(message = "组织编码不能为空")
    private String orgCode;
    
    /**部门ID */
    @NotNull(message = "部门ID不能为空")
    private Long depId;
    
    /** 模板编码*/
    @NotBlank(message = "模板编码不能为空")
    private String modelCode;
    
    /** 要货波次*/
    @NotBlank(message = "要货波次不能为空")
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
