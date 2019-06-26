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
    private String inOrgCode;
    
    /** 调入内部组织编码 */
    @NotBlank(message = "调入内部组织编码不能为空")
    private String inShOrgCode;
    
    /** 部门Id */
    @NotNull(message = "部门Id不能为空")
    private Long deptId;

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
     * @return the inShOrgCode
     */
    public String getInShOrgCode() {
        return inShOrgCode;
    }

    /**
     * @param inShOrgCode the inShOrgCode to set
     */
    public void setInShOrgCode(String inShOrgCode) {
        this.inShOrgCode = inShOrgCode;
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
