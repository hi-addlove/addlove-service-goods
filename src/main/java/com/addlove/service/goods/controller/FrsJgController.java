package com.addlove.service.goods.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.validation.Valid;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.addlove.service.goods.constants.GoodsJgConstants.DataStatus;
import com.addlove.service.goods.constants.GoodsJgConstants.SaveType;
import com.addlove.service.goods.constants.GoodsJgConstants.YwType;
import com.addlove.service.goods.constants.GoodsResponseCode;
import com.addlove.service.goods.exception.ServiceException;
import com.addlove.service.goods.message.ResponseMessage;
import com.addlove.service.goods.model.FrsGyModel;
import com.addlove.service.goods.model.FrsJgHeadModel;
import com.addlove.service.goods.model.FrsJgPageModel;
import com.addlove.service.goods.model.PageModel;
import com.addlove.service.goods.model.SkuPluExtendModel;
import com.addlove.service.goods.model.valid.CommonQueryDetailReq;
import com.addlove.service.goods.model.valid.FrsJgCpReq;
import com.addlove.service.goods.model.valid.FrsJgHeadReq;
import com.addlove.service.goods.model.valid.FrsJgPageReq;
import com.addlove.service.goods.model.valid.FrsJgSkuReq;
import com.addlove.service.goods.model.valid.FrsJgYlReq;
import com.addlove.service.goods.service.FrsJgService;
import com.addlove.service.goods.service.GoodsCommonService;
import com.addlove.service.goods.util.DateUtil;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;

/**
 * 加工单控制层
 * @author lw
 *
 */
@Controller
@RequestMapping(value = "/jg")
public class FrsJgController extends BaseController{
    @Autowired
    private FrsJgService frsJgService;
    
    @Autowired
    private GoodsCommonService commonService;
    
    /**
     * 查询加工单列表
     * @param req
     * @return ResponseMessage
     */
    @RequestMapping(value = "/queryJgPage", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage queryJgPage(@RequestBody @Valid FrsJgPageReq req) {
        FrsJgPageModel queryModel = new FrsJgPageModel();
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
        queryModel.setDepId(req.getDepId());
        queryModel.setDataStatus(req.getDataStatus());
        List<FrsJgHeadModel> jgList = this.frsJgService.queryJgPage(queryModel);
        Page<FrsJgHeadModel> page = (Page<FrsJgHeadModel>) jgList;
        PageModel pageModel = new PageModel();
        pageModel.setPageNo(page.getPageNum());
        pageModel.setPageSize(page.getPageSize());
        pageModel.setResult(page.getResult());
        pageModel.setTotal(page.getTotal());
        return ResponseMessage.ok(pageModel);
    }
    
    /**
     * 新增加工单
     * @param req
     * @return ResponseMessage
     */
    @RequestMapping(value = "/addJg", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage addJg(@RequestBody @Valid FrsJgHeadReq req) {
        if (StringUtils.isBlank(req.getBillNo()) && req.getSaveType() == SaveType.EXEC_ACCOUNT.getValue()) {
            throw new ServiceException(GoodsResponseCode.SAVE_BEFORE_ACCOUNT.getCode(), 
                    GoodsResponseCode.SAVE_BEFORE_ACCOUNT.getMsg());
        }
        FrsJgHeadModel headModel = this.getJgHeadModel(req);
        JSONObject json = new JSONObject();
        json.put("billNo", headModel.getBillNo());
        return ResponseMessage.ok(json);
    }
    
    /**
     * 编辑加工单
     * @param req
     * @return ResponseMessage
     */
    @RequestMapping(value = "/editJg", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage editJg(@RequestBody @Valid FrsJgHeadReq req) {
        if (StringUtils.isBlank(req.getBillNo())) {
            throw new ServiceException(GoodsResponseCode.BILLNO_NOT_BLANK.getCode(), 
                    GoodsResponseCode.BILLNO_NOT_BLANK.getMsg());
        }
        FrsJgHeadModel headModel = this.getJgHeadModel(req);
        JSONObject json = new JSONObject();
        json.put("billNo", headModel.getBillNo());
        return ResponseMessage.ok(json);
    }
    
    /**
     * 删除加工单
     * @param req
     * @return ResponseMessage
     */
    @RequestMapping(value = "/delJg", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage delJg(@RequestBody @Valid CommonQueryDetailReq req) {
        this.frsJgService.delJg(req.getBillNo());
        return ResponseMessage.ok();
    }
    
    /**
     * 查询加工单详情
     * @param req
     * @return ResponseMessage
     */
    @RequestMapping(value = "/queryJgDetails", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage queryJgDetails(@RequestBody @Valid CommonQueryDetailReq req) {
        FrsJgHeadModel model = this.frsJgService.queryJgDetails(req.getBillNo());
        return ResponseMessage.ok(model);
    }
    
    /**
     * 获取加工商品
     * @param req
     * @return ResponseMessage
     */
    @RequestMapping(value = "/getJgSkuList", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage getJgSkuList(@RequestBody @Valid FrsJgSkuReq req) {
        if (StringUtils.isBlank(req.getOrgCode())) {
            throw new ServiceException(GoodsResponseCode.ORGCODE_NOT_BLANK.getCode(), 
                    GoodsResponseCode.ORGCODE_NOT_BLANK.getMsg());
        }
        if (null == req.getDepId()) {
            throw new ServiceException(GoodsResponseCode.DEP_ID_NOT_BLANK.getCode(), 
                    GoodsResponseCode.DEP_ID_NOT_BLANK.getMsg());
        }
        if (StringUtils.isBlank(req.getCkCode())) {
            throw new ServiceException(GoodsResponseCode.CK_NOT_BLANK.getCode(), 
                    GoodsResponseCode.CK_NOT_BLANK.getMsg());
        }
       Set<SkuPluExtendModel> skuList = this.frsJgService.getJgSkuList(req.getOrgCode(), req.getDepId(), req.getCkCode());
        return ResponseMessage.ok(skuList);
    }
    
    /**
     * 组装加工单数据
     * @param req
     * @return FrsJgHeadModel
     */
    private FrsJgHeadModel getJgHeadModel(FrsJgHeadReq req) {
        FrsJgHeadModel headModel = new FrsJgHeadModel();
        List<FrsJgYlReq> ylList = req.getYlList();
        List<FrsJgCpReq> cpList = req.getCpList();
        if (null == ylList || ylList.isEmpty() || req.getYlCount() == 0) {
            throw new ServiceException(GoodsResponseCode.SKU_NOT_BLANK.getCode(), 
                    GoodsResponseCode.SKU_NOT_BLANK.getMsg());
        }
        if (null == cpList || cpList.isEmpty() || req.getCpCount() == 0) {
            throw new ServiceException(GoodsResponseCode.SKU_NOT_BLANK.getCode(), 
                    GoodsResponseCode.SKU_NOT_BLANK.getMsg());
        }
        headModel.setLrDate(DateUtil.getCurrentTime());
        headModel.setUserId(10000000041L);
        headModel.setUserCode("1");
        headModel.setUserName("超级户");
        headModel.setYwType(YwType.MD_JG.getValue());
        headModel.setOrgCode(req.getOrgCode());
        headModel.setOrgName(req.getOrgName());
        headModel.setDepId(req.getDepId());
        headModel.setDepCode(req.getDepCode());
        headModel.setDepName(req.getDepName());
        List<FrsGyModel> jgGys = this.frsJgService.getJgGys(req.getOrgCode(), req.getDepCode());
        headModel.setGyCode(jgGys.get(0).getGyCode());
        headModel.setGyName(jgGys.get(0).getGyName());
        headModel.setYlCount(req.getYlCount());
        headModel.setYlhCost(req.getYlhCost());
        headModel.setYlwCost(req.getYlwCost());
        headModel.setYlTotal(req.getYlTotal());
        headModel.setCpCount(req.getCpCount());
        headModel.setCphCost(req.getCphCost());
        headModel.setCpwCost(req.getCpwCost());
        headModel.setCpTotal(req.getCpTotal());
        headModel.setFlCost(jgGys.get(0).getFlCost());
        headModel.setRemark(req.getRemark());
        headModel.setDataStatus(DataStatus.ENTRY.getValue());
        headModel.setCkCode(req.getCkCode());
        headModel.setCkName(req.getCkName());
        if (req.getSaveType() == SaveType.EXEC_ACCOUNT.getValue()) {
            headModel.setJzDate(DateUtil.getCurrentTime());
            headModel.setJzrId(10000000041L);
            headModel.setJzrCode("1");
            headModel.setJzrName("超级户");
        }
        if (StringUtils.isBlank(req.getBillNo())) {
            Map<String, Object> map = new HashMap<>();
            map.put("ps_BillType", YwType.MD_JG.getValue());
            String billNo = this.commonService.getBillNoByCallProcedure(map);
            headModel.setBillNo(billNo);
        }else {
            headModel.setBillNo(req.getBillNo());
        }
        return headModel;
    }
}
