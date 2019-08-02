package com.addlove.service.goods.model.valid;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 获取盘点商品请求参数
 * @author lw
 *
 */
public class MdPdSkuReq {
    /**组织编码 */
    @NotBlank(message = "组织编码不能为空")
    private String orgCode;
    
    /**部门ID */
    @NotNull(message = "部门ID不能为空")
    private Long depId;
    
    /**单据类型:0-日盘；1-周盘；2-月盘 */
    @NotBlank(message = "单据类型不能为空")
    private String billType;

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
     * @return the billType
     */
    public String getBillType() {
        return billType;
    }

    /**
     * @param billType the billType to set
     */
    public void setBillType(String billType) {
        this.billType = billType;
    }
}
