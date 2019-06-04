package com.addlove.service.goods.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.addlove.service.goods.dao.UserDao;
import com.addlove.service.goods.model.UserModel;

@Service
public class UserService {
    @Autowired
    private UserDao userDao;
    
    public UserModel getUserByUserName(String userName) {
        return userDao.getUserByUserName(userName);
    }
}
