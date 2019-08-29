package com.addlove.service.goods.model.valid;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 用户请求参数
 * @author lw
 *
 */
public class UserReq {
    /**用户编码 */
    @NotBlank(message = "用户编码不能为空")
    private String userCode;
    
    /**组织编码 */
    @NotBlank(message = "组织编码不能为空")
    private String orgCode;

    /**
     * @return the userCode
     */
    public String getUserCode() {
        return userCode;
    }

    /**
     * @param userCode the userCode to set
     */
    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

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
}
