package com.addlove.service.goods.controller;

import java.util.List;
import javax.validation.Valid;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.addlove.service.goods.constants.GoodsAdlYhConstants.FlCode;
import com.addlove.service.goods.constants.GoodsResponseCode;
import com.addlove.service.goods.exception.ServiceException;
import com.addlove.service.goods.message.ResponseMessage;
import com.addlove.service.goods.model.BasFlContentModel;
import com.addlove.service.goods.model.OrdAdlYhHeadModel;
import com.addlove.service.goods.model.OrdAdlYhPageModel;
import com.addlove.service.goods.model.OrdYhTempletHeadModel;
import com.addlove.service.goods.model.PageModel;
import com.addlove.service.goods.model.valid.AdlYhSkuReq;
import com.addlove.service.goods.model.valid.CommonQueryDetailReq;
import com.addlove.service.goods.model.valid.OrdAdlYhPageReq;
import com.addlove.service.goods.service.CouBsApplyService;
import com.addlove.service.goods.service.OrdAdlYhService;
import com.alibaba.fastjson.JSONArray;
import com.github.pagehelper.Page;

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
    
    @Autowired
    private CouBsApplyService couBsApplyService;
    
    /**
     * 查询要货列表
     * @param req
     * @return ResponseMessage
     */
    @RequestMapping(value = "/queryYhPage", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage queryYhPage(@RequestBody @Valid OrdAdlYhPageReq req) {
        OrdAdlYhPageModel queryModel = new OrdAdlYhPageModel();
        queryModel.setPageNo(req.getPageNo());
        queryModel.setPageSize(req.getPageSize());
        queryModel.setOrgCode(req.getOrgCode());
        queryModel.setBillNo(req.getBillNo());
        if (StringUtils.isNotBlank(req.getStartDate())) {
            queryModel.setStartDate(req.getStartDate() + " 00:00:00");
        }
        if (StringUtils.isNotBlank(req.getEndDate())) {
            queryModel.setEndDate(req.getEndDate() + " 23:59:59");
        }
        queryModel.setYwStatus(req.getYwStatus());
        queryModel.setDepId(req.getDepId());
        List<OrdAdlYhHeadModel> yhList = this.ordAdlYhService.queryYhPage(queryModel);
        Page<OrdAdlYhHeadModel> page = (Page<OrdAdlYhHeadModel>) yhList;
        PageModel pageModel = new PageModel();
        pageModel.setPageNo(page.getPageNum());
        pageModel.setPageSize(page.getPageSize());
        pageModel.setResult(page.getResult());
        pageModel.setTotal(page.getTotal());
        return ResponseMessage.ok(pageModel);
    }
    
    /**
     * 查询要货详情
     * @param req
     * @return ResponseMessage
     */
    @RequestMapping(value = "/queryBsDetails", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage queryMdBsDetails(@RequestBody @Valid CommonQueryDetailReq req) {
        OrdAdlYhHeadModel model = this.ordAdlYhService.queryYhDetails(req.getBillNo());
        return ResponseMessage.ok(model);
    }
    
    /**
     * 获取模板编码
     * @param req
     * @return ResponseMessage
     */
    @RequestMapping(value = "/getYhModelCode", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage getYhModelCode(@RequestBody @Valid AdlYhSkuReq req) {
        if (StringUtils.isBlank(req.getOrgCode())) {
            throw new ServiceException(GoodsResponseCode.ORGCODE_NOT_BLANK.getCode(), 
                    GoodsResponseCode.ORGCODE_NOT_BLANK.getMsg());
        }
        List<OrdYhTempletHeadModel> templets = this.ordAdlYhService.getTempletsByOrgCode(req.getOrgCode());
        return ResponseMessage.ok(templets);
    }
    
    /**
     * 获取要货波次
     * @return ResponseMessage
     */
    @RequestMapping(value = "/getYhBc", method = RequestMethod.GET)
    @ResponseBody
    public ResponseMessage getYhBc() {
        List<BasFlContentModel> models = this.couBsApplyService.getFls(FlCode.YH_BC.getValue());
        return ResponseMessage.ok(models);
    }
    
    /**
     * 获取要货商品
     * @param req
     * @return ResponseMessage
     */
    @RequestMapping(value = "/getYhSkuList", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage getYhSkuList(@RequestBody @Valid AdlYhSkuReq req) {
        if (StringUtils.isBlank(req.getOrgCode())) {
            throw new ServiceException(GoodsResponseCode.ORGCODE_NOT_BLANK.getCode(), 
                    GoodsResponseCode.ORGCODE_NOT_BLANK.getMsg());
        }
        if (null == req.getDepId()) {
            throw new ServiceException(GoodsResponseCode.DEP_ID_NOT_BLANK.getCode(), 
                    GoodsResponseCode.DEP_ID_NOT_BLANK.getMsg());
        }
        if (StringUtils.isBlank(req.getModelCode())) {
            throw new ServiceException(GoodsResponseCode.MODEL_CODE_NOT_BLANK.getCode(), 
                    GoodsResponseCode.MODEL_CODE_NOT_BLANK.getMsg());
        }
        JSONArray backArray = this.ordAdlYhService.getYhSkuList(req.getOrgCode(), req.getDepId(), req.getModelCode());
        return ResponseMessage.ok(backArray);
    }
}
