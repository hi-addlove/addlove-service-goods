package com.addlove.service.goods.controller;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.addlove.service.goods.constants.GoodsOrdJhConstants.ModelTags;
import com.addlove.service.goods.constants.GoodsOrdThConstants.BillType;
import com.addlove.service.goods.constants.GoodsOrdThConstants.YwType;
import com.addlove.service.goods.message.ResponseMessage;
import com.addlove.service.goods.model.OrdJhHeadModel;
import com.addlove.service.goods.model.OrdThApplyBodyModel;
import com.addlove.service.goods.model.OrdThApplyHeadModel;
import com.addlove.service.goods.model.OrdThQueryPageModel;
import com.addlove.service.goods.model.PageModel;
import com.addlove.service.goods.model.valid.OrdJhQueryDetailReq;
import com.addlove.service.goods.model.valid.OrdThApplyBodyDiffReq;
import com.addlove.service.goods.model.valid.OrdThApplyHeadDiffReq;
import com.addlove.service.goods.model.valid.OrdThQueryPageReq;
import com.addlove.service.goods.service.OrdJhService;
import com.addlove.service.goods.service.OrdThApplyService;
import com.addlove.service.goods.util.DateUtil;
import com.github.pagehelper.Page;

/**
 * 退货申请单控制层
 * @author lw
 *
 */
@Controller
@RequestMapping(value = "/ordThApply")
public class OrdThApplyController extends BaseController{
    @Autowired
    private OrdJhService ordJhService;
    
    @Autowired
    private OrdThApplyService ordThApplyService;
    
    /**
     * 分页查询退货差异单
     * @param req
     * @return ResponseMessage
     */
    @RequestMapping(value = "/queryOrderThDiff", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage queryOrderThDiff(@RequestBody @Valid OrdThQueryPageReq req) {
        OrdThQueryPageModel queryModel = new OrdThQueryPageModel();
        queryModel.setPageNo(req.getPageNo());
        queryModel.setPageSize(req.getPageSize());
        queryModel.setOrgCode(req.getOrgCode());
        queryModel.setOrgCode("999999");//组织编码暂时写死
        queryModel.setBillNo(req.getBillNo());
        if (StringUtils.isNotBlank(req.getStartDate())) {
            queryModel.setStartDate(req.getStartDate() + " 00:00:00");
        }
        if (StringUtils.isNotBlank(req.getEndDate())) {
            queryModel.setEndDate(req.getEndDate() + " 23:59:59");
        }
        queryModel.setCheckStatus(req.getCheckStatus());
        queryModel.setTjrName(req.getTjrName());
        List<OrdThApplyHeadModel> ordThHeadList = this.ordThApplyService.queryOrdThHeadModelByPage(queryModel);
        PageModel pageModel = new PageModel();
        Page<OrdThApplyHeadModel> page = (Page<OrdThApplyHeadModel>) ordThHeadList;
        pageModel.setPageNo(page.getPageNum());
        pageModel.setPageSize(page.getPageSize());
        pageModel.setResult(page.getResult());
        pageModel.setTotal(page.getTotal());
        return ResponseMessage.ok(pageModel);
    }
    
    /**
     * 产生配送验收差异单
     * @param req
     * @return ResponseMessage
     */
    @RequestMapping(value = "/generateDifferentBill", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage generateDifferentBill(@RequestBody @Valid OrdThApplyHeadDiffReq req) {
        List<OrdThApplyBodyDiffReq> bodyReqList = req.getBodyList();
        //没有差异商品，直接更新配送验收主表
        if (null == bodyReqList || bodyReqList.isEmpty()) {
            OrdJhHeadModel model = new OrdJhHeadModel();
            model.setBillNo(req.getBillNo());
            model.setYsrId(123L);
            model.setYsrCode("lf0913");
            model.setYsrName("李飞");
            //送货确认时间
            model.setShrDate(DateUtil.getCurrentTime());
            model.setTag(ModelTags.NORMAL_ACCEPTANCE.getValue());
            this.ordJhService.updateJhHeadYsrCodeAndStatus(model);
        }else {
            OrdThApplyHeadModel headModel = new OrdThApplyHeadModel();
            //调用存储过程生成差异单号
            Map<String, Object> map = new HashMap<>();
            map.put("ps_BillType", BillType.RETURN_APPLY.getValue());
            String billNo = this.ordThApplyService.getBillNoByCallProcedure(map);
            headModel.setBillNo(billNo);
            headModel.setRefBillNo(req.getBillNo());
            headModel.setOrgCode(req.getOrgCode());
            headModel.setOrgName(req.getOrgName());
            headModel.setInOrgCode(req.getInOrgCode());
            headModel.setZbOrgCode(req.getZbOrgCode());
            headModel.setZbOrgName(req.getZbOrgName());
            headModel.setSupCode(req.getSupCode());
            headModel.setSupName(req.getSupName());
            headModel.setCntId(req.getCntId());
            headModel.setCkCode(req.getCkCode());
            headModel.setCkName(req.getCkName());
            headModel.setThCount(req.getThCount());
            headModel.sethCost(req.gethCost());
            headModel.setwCost(req.getwCost());
            headModel.setjTaxTotal(req.getJtaxTotal());
            headModel.setsTotal(req.getsTotal());
            headModel.setCjTotal(req.getCjTotal());
            headModel.setPsCost(req.getPsCost());
            headModel.setYwType(YwType.SHOP_TO_ZB.getValue());
            //组装商品明细
            List<OrdThApplyBodyModel> bodyModelList = new LinkedList<OrdThApplyBodyModel>();
            for (OrdThApplyBodyDiffReq bodyReq : bodyReqList) {
                OrdThApplyBodyModel bodyModel = new OrdThApplyBodyModel();
                bodyModel.setBillNo(billNo);
                bodyModel.setSerialNo(bodyReq.getSerialNo());
                bodyModel.setPluId(bodyReq.getPluId());
                bodyModel.setPluCode(bodyReq.getPluCode());
                bodyModel.setPluName(bodyReq.getPluName());
                bodyModel.setExPluCode(bodyReq.getExPluCode());
                bodyModel.setBarCode(bodyReq.getBarCode());
                bodyModel.setSpec(bodyReq.getSpec());
                bodyModel.setUnit(bodyReq.getUnit());
                bodyModel.setCarGoNo(bodyReq.getCarGoNo());
                bodyModel.setDepId(bodyReq.getDepId());
                bodyModel.setDepCode(bodyReq.getDepCode());
                bodyModel.setDepName(bodyReq.getDepName());
                bodyModel.setPsPrice(bodyReq.getPsPrice());
                bodyModel.setPackUnit(bodyReq.getPackUnit());
                bodyModel.setPackQty(bodyReq.getPackQty());
                bodyModel.setPackCount(bodyReq.getPackCount());
                bodyModel.setSglCount(bodyReq.getSglCount());
                bodyModel.setThCount(bodyReq.getThCount());
                bodyModel.sethCost(bodyReq.gethCost());
                bodyModel.setwCost(bodyReq.getwCost());
                bodyModel.setjTaxTotal(bodyReq.getjTaxTotal());
                bodyModel.setPsCost(bodyReq.getPsCost());
                bodyModel.setsTotal(bodyReq.getsTotal());
                bodyModel.setCjTotal(bodyReq.getCjTotal());
                bodyModel.setCjRate(bodyReq.getCjRate());
                bodyModel.setHjsTotal(bodyReq.getHjsTotal());
                bodyModel.setWjsTotal(bodyReq.getWjsTotal());
                bodyModel.setSqThCount(bodyReq.getSqThCount());
                bodyModelList.add(bodyModel);
            }
            this.ordThApplyService.insertOrdThApply(headModel, bodyModelList);
        }
        return ResponseMessage.ok();
    }
    
    /**
     * 查询退货差异单详情
     * @param req
     * @return ResponseMessage
     */
    @RequestMapping(value = "/queryOrderThDetail", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage queryOrderThDetail(@RequestBody @Valid OrdJhQueryDetailReq req) {
        List<OrdThApplyBodyModel> ordJhBodys = this.ordThApplyService.queryThBodysByBillNo(req.getBillNo());
        return ResponseMessage.ok(ordJhBodys);
    }
}
