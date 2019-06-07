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

import com.addlove.service.goods.constants.GoodsOrdJhConstants.ModelTags;
import com.addlove.service.goods.message.ResponseMessage;
import com.addlove.service.goods.model.OrdJhBodyModel;
import com.addlove.service.goods.model.OrdJhHeadModel;
import com.addlove.service.goods.model.OrdJhQueryPageModel;
import com.addlove.service.goods.model.PageModel;
import com.addlove.service.goods.model.valid.OrdJhQueryDetailReq;
import com.addlove.service.goods.model.valid.OrdJhQueryPageReq;
import com.addlove.service.goods.model.valid.OrdThApplyBodyDiffReq;
import com.addlove.service.goods.model.valid.OrdThApplyHeadDiffReq;
import com.addlove.service.goods.service.OrdJhService;
import com.addlove.service.goods.util.DateUtil;
import com.github.pagehelper.Page;

/**
 * 配送验收控制层
 * @author lw
 *
 */
@Controller
@RequestMapping(value = "/ordJh")
public class OrdJhController extends BaseController{
    @Autowired
    private OrdJhService ordJhService;
    
    /**
     * 分页查询配送验收单
     * @param req
     * @return ResponseMessage
     */
    @RequestMapping(value = "/queryOrderJh", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage queryOrderJh(@RequestBody @Valid OrdJhQueryPageReq req) {
        OrdJhQueryPageModel queryModel = new OrdJhQueryPageModel();
        queryModel.setPageNo(req.getPageNo());
        queryModel.setPageSize(req.getPageSize());
        queryModel.setOrgCode(req.getOrgCode());
        queryModel.setOrgCode("690246");//组织编码暂时写死：德阳一中新店
        queryModel.setBillNo(req.getBillNo());
        if (StringUtils.isNotBlank(req.getStartDate())) {
            queryModel.setStartDate(req.getStartDate() + " 00:00:00");
        }
        if (StringUtils.isNotBlank(req.getEndDate())) {
            queryModel.setEndDate(req.getEndDate() + " 23:59:59");
        }
        queryModel.setTag(req.getTag());
        List<OrdJhHeadModel> ordJhHeadList = this.ordJhService.queryOrdJhHeadModelByPage(queryModel);
        PageModel pageModel = new PageModel();
        Page<OrdJhHeadModel> page = (Page<OrdJhHeadModel>) ordJhHeadList;
        pageModel.setPageNo(page.getPageNum());
        pageModel.setPageSize(page.getPageSize());
        pageModel.setResult(page.getResult());
        pageModel.setTotal(page.getTotal());
        return ResponseMessage.ok(pageModel);
    }
    
    /**
     * 查询配送验收单详情
     * @param req
     * @return queryOrderJhDetail
     */
    @RequestMapping(value = "/queryOrderJhDetail", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage queryOrderJhDetail(@RequestBody @Valid OrdJhQueryDetailReq req) {
        List<OrdJhBodyModel> ordJhSkus = this.ordJhService.queryBodysByBillNo(req.getBillNo());
        return ResponseMessage.ok(ordJhSkus);
    }
    
    /**
     * 产生配送验收差异单
     * @param req
     * @return ResponseMessage
     */
    @RequestMapping(value = "/generateDifferentBill", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage generateDifferentBill(@RequestBody @Valid OrdThApplyHeadDiffReq req) {
        List<OrdThApplyBodyDiffReq> bodyList = req.getBodyList();
        //没有差异商品，直接更新配送验收主表
        if (null == bodyList || bodyList.isEmpty()) {
            OrdJhHeadModel model = new OrdJhHeadModel();
            model.setBillNo(req.getBillNo());
            model.setYsrId(123L);
            model.setYsrCode("");
            model.setYsrName("");
            //送货确认时间
            model.setShrDate(DateUtil.getCurrentTime());
            model.setTag(ModelTags.NORMAL_ACCEPTANCE.getValue());
            this.ordJhService.updateJhHeadYsrCodeAndStatus(model);
        }else {
            
        }
        return ResponseMessage.ok();
    }
}
