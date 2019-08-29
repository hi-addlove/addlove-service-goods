package com.addlove.service.goods.controller;

import java.util.Map;
import java.util.UUID;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.addlove.service.goods.config.UserInit;
import com.addlove.service.goods.constants.GoodsResponseCode;
import com.addlove.service.goods.constants.LoginMsgCode.LoginCode;
import com.addlove.service.goods.context.SysUserDataContextHolder;
import com.addlove.service.goods.message.ResponseMessage;
import com.addlove.service.goods.model.OrgManageModel;
import com.addlove.service.goods.model.SysUserModel;
import com.addlove.service.goods.model.UsrUserModel;
import com.addlove.service.goods.model.valid.UserReq;
import com.addlove.service.goods.service.GoodsCommonService;
import com.addlove.service.goods.util.DES3;
import com.addlove.service.goods.util.LoggerEnhance;
import com.alibaba.fastjson.JSONObject;

/**
 * 登录
 * @author lw
 *
 */
@Controller
@RequestMapping(value = "/addlove/gateway")
public class LoginController extends BaseController{
    /**LoginController类日志 */
    private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);
    
    @Autowired
    private GoodsCommonService commonService;
    
    //加解密密钥
    @Value("${addlove.user.key}")
    private String key;
    
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
        LoggerEnhance.info(LOGGER, "外部系统传输的UserCode密文为:{}", req.getUserCode());
        LoggerEnhance.info(LOGGER, "外部系统传输的OrgCode密文为:{}", req.getOrgCode());
        JSONObject backJson = new JSONObject();
        try {
            String userCode = DES3.decrypt(req.getUserCode(), key);
            UsrUserModel userModel = this.commonService.getUserByCode(userCode.trim().toString());
            if (null == userModel) {
                return ResponseMessage.fail(LoginCode.ACCOUNT.getMsg(), LoginCode.ACCOUNT.getCode());
            }
            String orgCode = DES3.decrypt(req.getOrgCode(), key);
            OrgManageModel orgModel = this.commonService.getOrgModel(orgCode.trim().toString());
            if (null == orgModel) {
                return ResponseMessage.fail(LoginCode.ORGANIZE.getMsg(), LoginCode.ORGANIZE.getCode());
            }
            //将用户和组织设置进全局
            SysUserDataContextHolder.clearSysUserData();
            SysUserModel sysUserModel = new SysUserModel();
            sysUserModel.setUserModel(userModel);
            sysUserModel.setOrgModel(orgModel);
            SysUserDataContextHolder.setSysUserData(sysUserModel);
            //返回给前端用户和组织信息
            backJson.put("userId", userModel.getUserId());
            backJson.put("userCode", userModel.getUserCode());
            backJson.put("userName", userModel.getUserName());
            backJson.put("orgCode", orgModel.getOrgCode());
            backJson.put("orgName", orgModel.getOrgName());
            String uuid = UUID.randomUUID().toString();
            Map<String, Long> session = UserInit.getSession();
            session.put(uuid, System.currentTimeMillis());
            //给session保存数据
            request.getSession().setAttribute(UserInit.SESSIONKEY, uuid);
            Cookie cookie = new Cookie(UserInit.SESSIONKEY, uuid);
            cookie.setHttpOnly(true);
            cookie.setPath("/");
            response.addCookie(cookie);
            ResponseMessage resMsg = new ResponseMessage();
            resMsg.setCode(LoginCode.SUCCESS.getCode());
            resMsg.setMsg(LoginCode.SUCCESS.getMsg());
            resMsg.setData(backJson);
            return resMsg;
        } catch (Exception e) {
            LoggerEnhance.error(LOGGER, GoodsResponseCode.DES3_ENCRYPT_DECRYPT_ERROR.getCode(), 
                    GoodsResponseCode.DES3_ENCRYPT_DECRYPT_ERROR.getMsg(), e);
            ResponseMessage resMsg = new ResponseMessage();
            resMsg.setCode(LoginCode.USER_OR_ORG_ERROR.getCode());
            resMsg.setMsg(LoginCode.USER_OR_ORG_ERROR.getMsg());
            resMsg.setData(backJson);
            return resMsg;
        }
    }
}
