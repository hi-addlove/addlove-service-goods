package com.addlove.service.goods.model;

import java.io.Serializable;

/**
 * 系统登录用户类
 * @author lw
 *
 */
public class SysUserModel implements Serializable{

    private static final long serialVersionUID = -91255021209026903L;
    
    /**
     * 用户对象
     */
    private UsrUserModel userModel;
    
    /**
     * 组织对象
     */
    private OrgManageModel orgModel;

    /**
     * @return the userModel
     */
    public UsrUserModel getUserModel() {
        return userModel;
    }

    /**
     * @param userModel the userModel to set
     */
    public void setUserModel(UsrUserModel userModel) {
        this.userModel = userModel;
    }

    /**
     * @return the orgModel
     */
    public OrgManageModel getOrgModel() {
        return orgModel;
    }

    /**
     * @param orgModel the orgModel to set
     */
    public void setOrgModel(OrgManageModel orgModel) {
        this.orgModel = orgModel;
    }
}
