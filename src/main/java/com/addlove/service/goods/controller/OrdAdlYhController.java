package com.addlove.service.goods.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.addlove.service.goods.message.ResponseMessage;
import com.addlove.service.goods.model.valid.AdlYhSkuReq;
import com.addlove.service.goods.service.OrdAdlYhService;

/**
 * ADL要货控制层
 * @author lw
 *
 */
@Controller
@RequestMapping(value = "/adlYh")
public class OrdAdlYhController extends BaseController{
    @Autowired
    private OrdAdlYhService ordAdlYhService;
    
    /**
     * 获取要货商品
     * @param req
     * @return ResponseMessage
     */
    @RequestMapping(value = "/getYhSkuList", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage getYhSkuList(@RequestBody @Valid AdlYhSkuReq req) {
        return ResponseMessage.ok();
    }
}
