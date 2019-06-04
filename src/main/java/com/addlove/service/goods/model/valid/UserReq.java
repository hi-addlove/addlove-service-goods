package com.addlove.service.goods.model.valid;

import org.hibernate.validator.constraints.NotBlank;

public class UserReq {
    /**用户名 */
    @NotBlank(message = "用户名不能为空")
    private String userName;
    
    /**密码 */
    @NotBlank(message = "密码不能为空")
    private String password;
    /**
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }
    /**
     * @param userName the userName to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }
    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }
    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
