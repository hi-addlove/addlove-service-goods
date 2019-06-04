package com.addlove.service.goods.dao;

import org.springframework.stereotype.Repository;

import com.addlove.service.goods.model.UserModel;

@Repository
public interface UserDao {
    /**
     * 根据用户名查询用户信息
     * @param userName
     * @return UserModel
     */
    UserModel getUserByUserName(String userName);
}
