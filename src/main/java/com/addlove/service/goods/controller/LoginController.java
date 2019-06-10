package com.addlove.service.goods.controller;

import java.util.Map;
import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.addlove.service.goods.config.UserInit;
import com.addlove.service.goods.constants.LoginMsgCode.LoginCode;
import com.addlove.service.goods.message.ResponseMessage;
import com.addlove.service.goods.model.UserModel;
import com.addlove.service.goods.model.valid.UserReq;
import com.addlove.service.goods.service.UserService;

@Controller
@RequestMapping(value = "/goods/gateway")
public class LoginController extends BaseController{
    /*@Autowired
    private UserService userService;*/
    
    /**
     * 登录
     * @param req
     * @param request
     * @param response
     * @return ResponseMessage
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage login(@RequestBody @Valid UserReq req, HttpServletRequest request, HttpServletResponse response) {
        //UserModel userModel = this.userService.getUserByUserName(req.getUserName());
        UserModel userModel = new UserModel();
        userModel.setUserName(req.getUserName());
        userModel.setPassword(req.getPassword());
        if (null == userModel) {
            return ResponseMessage.fail(LoginCode.ACCOUNT.getMsg(), LoginCode.ACCOUNT.getCode());
        }
        if (!userModel.getPassword().equals(req.getPassword())) {
            return ResponseMessage.fail(LoginCode.PASSWORD.getMsg(), LoginCode.PASSWORD.getCode());
        }
        String uuid = UUID.randomUUID().toString();
        Map<String, Long> session = UserInit.getSession();
        session.put(uuid, System.currentTimeMillis());
        //给session保存数据
        request.getSession().setAttribute(UserInit.SESSIONKEY, uuid);
        Cookie cookie = new Cookie(UserInit.SESSIONKEY, uuid);
        cookie.setHttpOnly(true);
        response.addCookie(cookie);
        ResponseMessage resMsg = new ResponseMessage();
        resMsg.setCode(LoginCode.SUCCESS.getCode());
        resMsg.setMsg(LoginCode.SUCCESS.getMsg());
        resMsg.setData(userModel);
        return resMsg;
    }
}
