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
import com.addlove.service.goods.constants.GoodsMdBsConstants.CouStatus;
import com.addlove.service.goods.constants.GoodsMdBsConstants.DataStatus;
import com.addlove.service.goods.constants.GoodsMdBsConstants.FlCode;
import com.addlove.service.goods.constants.GoodsMdBsConstants.SaveType;
import com.addlove.service.goods.constants.GoodsMdBsConstants.YwType;
import com.addlove.service.goods.constants.GoodsResponseCode;
import com.addlove.service.goods.exception.ServiceException;
import com.addlove.service.goods.message.ResponseMessage;
import com.addlove.service.goods.model.BasFlContentModel;
import com.addlove.service.goods.model.CouBsApplyBodyModel;
import com.addlove.service.goods.model.CouBsApplyHeadModel;
import com.addlove.service.goods.model.CouBsApplyPageModel;
import com.addlove.service.goods.model.OrgManageModel;
import com.addlove.service.goods.model.PageModel;
import com.addlove.service.goods.model.SkuPluExtendModel;
import com.addlove.service.goods.model.StkStoreModel;
import com.addlove.service.goods.model.valid.CommonOrgAndSupAndCntReq;
import com.addlove.service.goods.model.valid.CommonQueryDetailReq;
import com.addlove.service.goods.model.valid.CouBsApplyBodyReq;
import com.addlove.service.goods.model.valid.CouBsApplyHeadReq;
import com.addlove.service.goods.model.valid.CouBsApplyPageReq;
import com.addlove.service.goods.service.CouBsApplyService;
import com.addlove.service.goods.service.GoodsCommonService;
import com.addlove.service.goods.util.DateUtil;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;

/**
 * 门店报损控制层
 * @author lw
 *
 */
@Controller
@RequestMapping(value = "/mdBs")
public class CouBsApplyController extends BaseController{
    @Autowired
    private CouBsApplyService couBsApplyService;
    
    @Autowired
    private GoodsCommonService commonService;
    
    /**
     * 查询报损列表
     * @param req
     * @return ResponseMessage
     */
    @RequestMapping(value = "/queryBsPage", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage queryMdBsPage(@RequestBody @Valid CouBsApplyPageReq req) {
        CouBsApplyPageModel queryModel = new CouBsApplyPageModel();
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
        queryModel.setDataStatus(req.getDataStatus());
        queryModel.setCouStatus(req.getCouStatus());
        queryModel.setUserCode(req.getUserCode());
        queryModel.setDepId(req.getDepId());
        queryModel.setCkCode(req.getCkCode());
        List<CouBsApplyHeadModel> bsList = this.couBsApplyService.queryMdBsPage(queryModel);
        Page<CouBsApplyHeadModel> page = (Page<CouBsApplyHeadModel>) bsList;
        PageModel pageModel = new PageModel();
        pageModel.setPageNo(page.getPageNum());
        pageModel.setPageSize(page.getPageSize());
        pageModel.setResult(page.getResult());
        pageModel.setTotal(page.getTotal());
        return ResponseMessage.ok(pageModel);
    }
    
    /**
     * 新增报损
     * @param req
     * @return ResponseMessage
     */
    @RequestMapping(value = "/addBs", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage addBs(@RequestBody @Valid CouBsApplyHeadReq req) {
        CouBsApplyHeadModel headModel = this.getBsHeadModel(req);
        if (req.getSaveType() == SaveType.COMMIT.getValue()) {
            headModel.setCouStatus(CouStatus.HAS_APPLY.getValue());
        }
        this.couBsApplyService.saveBs(headModel);
        JSONObject json = new JSONObject();
        json.put("billNo", headModel.getBillNo());
        return ResponseMessage.ok(json);
    }
    
    /**
     * 编辑报损
     * @param req
     * @return ResponseMessage
     */
    @RequestMapping(value = "/editBs", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage editBs(@RequestBody @Valid CouBsApplyHeadReq req) {
        if (StringUtils.isBlank(req.getBillNo())) {
            throw new ServiceException(GoodsResponseCode.BILLNO_NOT_BLANK.getCode(), 
                    GoodsResponseCode.BILLNO_NOT_BLANK.getMsg());
        }
        CouBsApplyHeadModel headModel = this.getBsHeadModel(req);
        if (req.getSaveType() == SaveType.COMMIT.getValue()) {
            headModel.setCouStatus(CouStatus.HAS_APPLY.getValue());
        }
        this.couBsApplyService.saveBs(headModel);
        JSONObject json = new JSONObject();
        json.put("billNo", headModel.getBillNo());
        return ResponseMessage.ok(json);
    }
    
    /**
     * 删除报损
     * @param req
     * @return ResponseMessage
     */
    @RequestMapping(value = "/deleteBs", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage deleteBs(@RequestBody @Valid CommonQueryDetailReq req) {
        this.couBsApplyService.deleteBs(req.getBillNo());
        return ResponseMessage.ok();
    }
    
    /**
     * 查询报损详情
     * @param req
     * @return ResponseMessage
     */
    @RequestMapping(value = "/queryBsDetails", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage queryMdBsDetails(@RequestBody @Valid CommonQueryDetailReq req) {
        CouBsApplyHeadModel model = this.couBsApplyService.queryMdBsDetails(req.getBillNo());
        return ResponseMessage.ok(model);
    }
    
    /**
     * 获取门店报损商品
     * @param req
     * @return ResponseMessage
     */
    @RequestMapping(value = "/getBsSkus", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage getMdBsSkus(@RequestBody CommonOrgAndSupAndCntReq req) {
        String orgCode = req.getOrgCode();
        Long depId = req.getDepId();
        if (StringUtils.isBlank(orgCode)) {
            throw new ServiceException(GoodsResponseCode.ORGCODE_NOT_BLANK.getCode(), 
                    GoodsResponseCode.ORGCODE_NOT_BLANK.getMsg());
        }
        if (null == depId) {
            throw new ServiceException(GoodsResponseCode.DEP_ID_NOT_BLANK.getCode(), 
                    GoodsResponseCode.DEP_ID_NOT_BLANK.getMsg());
        }
        List<SkuPluExtendModel> bsSkus = this.commonService.getMdBsSkus(orgCode, depId);
        return ResponseMessage.ok(bsSkus);
    }
    
    /**
     * 获取报损原因
     * @return ResponseMessage
     */
    @RequestMapping(value = "/getBsReason", method = RequestMethod.GET)
    @ResponseBody
    public ResponseMessage getBsReason() {
        List<BasFlContentModel> models = this.couBsApplyService.getFls(FlCode.BS_REASON.getValue());
        return ResponseMessage.ok(models);
    }
    
    /**
     * 封装报损单数据
     * @param req
     * @return CouBsApplyHeadModel
     */
    private CouBsApplyHeadModel getBsHeadModel(CouBsApplyHeadReq req) {
        CouBsApplyHeadModel headModel = new CouBsApplyHeadModel();
        List<CouBsApplyBodyReq> bodyList = req.getBodyList();
        if (null == bodyList || bodyList.isEmpty()) {
            throw new ServiceException(GoodsResponseCode.SKU_NOT_BLANK.getCode(), 
                    GoodsResponseCode.SKU_NOT_BLANK.getMsg());
        }
        headModel.setLrDate(DateUtil.getCurrentTime());
        headModel.setUserId(10000000041L);
        headModel.setUserCode("1");
        headModel.setUserName("超级户");
        headModel.setCouStatus(CouStatus.NOT_APPLY.getValue());
        headModel.setDataStatus(DataStatus.ENTRY.getValue());
        headModel.setOrgCode(req.getOrgCode());
        headModel.setOrgName(req.getOrgName());
        OrgManageModel orgModel = this.commonService.getOrgModel(req.getOrgCode());
        headModel.setInOrgCode(orgModel.getInOrgCode());
        headModel.setDepId(req.getDepId());
        headModel.setDepCode(req.getDepCode());
        headModel.setDepName(req.getDepName());
        List<StkStoreModel> storeList = this.commonService.getStoreList(req.getOrgCode());
        if (null == storeList || storeList.isEmpty()) {
            throw new ServiceException(GoodsResponseCode.CK_NOT_BLANK.getCode(), 
                    GoodsResponseCode.CK_NOT_BLANK.getMsg());
        }
        headModel.setCkCode(storeList.get(0).getCkCode());
        headModel.setCkName(storeList.get(0).getCkName());
        headModel.setBsCount(req.getBsCount());
        headModel.setRemark(req.getRemark());
        if (StringUtils.isBlank(req.getBillNo())) {
            Map<String, Object> map = new HashMap<>();
            map.put("ps_BillType", YwType.MD_BS.getValue());
            String billNo = this.commonService.getBillNoByCallProcedure(map);
            headModel.setBillNo(billNo);
        }else {
            headModel.setBillNo(req.getBillNo());
        }
        List<CouBsApplyBodyModel> bodyModels = new LinkedList<CouBsApplyBodyModel>();
        long serialNo = 1;
        for (CouBsApplyBodyReq bodyReq : bodyList) {
            CouBsApplyBodyModel bodyModel = new CouBsApplyBodyModel();
            bodyModel.setBillNo(headModel.getBillNo());
            bodyModel.setSerialNo(serialNo++);
            bodyModel.setDepId(headModel.getDepId());
            bodyModel.setDepCode(headModel.getDepCode());
            bodyModel.setDepName(headModel.getDepName());
            bodyModel.setPluId(bodyReq.getPluId());
            bodyModel.setPluCode(bodyReq.getPluCode());
            bodyModel.setPluName(bodyReq.getPluName());
            bodyModel.setExPluCode("*");
            bodyModel.setBarCode(bodyReq.getBarCode());
            bodyModel.setUnit(bodyReq.getUnit());
            bodyModel.setSpec(bodyReq.getSpec());
            bodyModel.setBsCount(bodyReq.getBsCount());
            bodyModel.setCarGoNo(bodyReq.getCarGoNo());
            bodyModel.setYkReason(req.getBsReason());
            bodyModel.setRemark(bodyReq.getRemark());
            bodyModels.add(bodyModel);
        }
        headModel.setBodyList(bodyModels);
        return headModel;
    }
}
