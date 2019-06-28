package com.addlove.service.goods.model.valid;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 组织、部门查询商品参数
 * @author lw
 *
 */
public class CommonOrgAndDeptReq {
    /** 组织编码 */
    @NotBlank(message = "组织编码不能为空")
    private String orgCode;
    
    /** 调入组织编码 */
    @NotBlank(message = "调入组织编码不能为空")
    private String shOrgCode;
    
    /** 部门Id */
    @NotNull(message = "部门Id不能为空")
    private Long deptId;

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
     * @return the shOrgCode
     */
    public String getShOrgCode() {
        return shOrgCode;
    }

    /**
     * @param shOrgCode the shOrgCode to set
     */
    public void setShOrgCode(String shOrgCode) {
        this.shOrgCode = shOrgCode;
    }

    /**
     * @return the deptId
     */
    public Long getDeptId() {
        return deptId;
    }

    /**
     * @param deptId the deptId to set
     */
    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }
}
