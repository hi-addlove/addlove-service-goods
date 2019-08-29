package com.addlove.service.goods.context;

import com.addlove.service.goods.model.SysUserModel;

public class SysUserDataContextHolder {
    private static final ThreadLocal<SysUserModel> CONTEXT_HOLDER = new ThreadLocal<SysUserModel>();
    
    /**
     * 设置用户数据
     * @param sysUserModel
     */
    public static void setSysUserData(SysUserModel sysUserModel) {
        CONTEXT_HOLDER.set(sysUserModel);
    }
    
    /**
     * 获取用户数据
     * @param sysUserModel
     */
    public static SysUserModel getSysUserData() {
        return CONTEXT_HOLDER.get();
    }
    
    /**
     * 清除用户数据
     */
    public static void clearSysUserData() {
        CONTEXT_HOLDER.remove();
    }
}
