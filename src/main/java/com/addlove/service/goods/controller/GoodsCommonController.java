package com.addlove.service.goods.controller;

import java.util.HashMap;
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
import com.addlove.service.goods.constants.GoodsResponseCode;
import com.addlove.service.goods.exception.ServiceException;
import com.addlove.service.goods.message.ResponseMessage;
import com.addlove.service.goods.model.BasBrandModel;
import com.addlove.service.goods.model.CatCategoryModel;
import com.addlove.service.goods.model.CntContractModel;
import com.addlove.service.goods.model.EtpSupplierModel;
import com.addlove.service.goods.model.OrgDeptModel;
import com.addlove.service.goods.model.OrgManageModel;
import com.addlove.service.goods.model.SkuPluExtendModel;
import com.addlove.service.goods.model.SkuPluModel;
import com.addlove.service.goods.model.StkStoreModel;
import com.addlove.service.goods.model.UserEmployeeModel;
import com.addlove.service.goods.model.UsrUserModel;
import com.addlove.service.goods.model.valid.CommonOrgAndDeptReq;
import com.addlove.service.goods.model.valid.CommonOrgAndSupAndCntReq;
import com.addlove.service.goods.model.valid.CommonSearchReq;
import com.addlove.service.goods.service.GoodsCommonService;

/**
 * 公共controller层：如供应商、合同、商品等
 * @author lw
 *
 */
@Controller
@RequestMapping(value = "/common")
public class GoodsCommonController extends BaseController{
    @Autowired
    private GoodsCommonService commonService;
    
    /**
     * 通过组织编码获取供应商
     * @param req
     * @return ResponseMessage
     */
    @RequestMapping(value = "/getSupplierList", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage getSupplierList(@RequestBody CommonOrgAndSupAndCntReq req) {
        String orgCode = req.getOrgCode();
        if (StringUtils.isBlank(orgCode)) {
            throw new ServiceException(GoodsResponseCode.ORGCODE_NOT_BLANK.getCode(), 
                    GoodsResponseCode.ORGCODE_NOT_BLANK.getMsg());
        }
        List<EtpSupplierModel> supplierList = this.commonService.getSupplierList(orgCode);
        return ResponseMessage.ok(supplierList);
    }
    
    /**
     * 通过组织获取合同
     * @param req
     * @return ResponseMessage
     */
    @RequestMapping(value = "/getCntList", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage getCntList(@RequestBody CommonOrgAndSupAndCntReq req) {
        String orgCode = req.getOrgCode();
        if (StringUtils.isBlank(orgCode)) {
            throw new ServiceException(GoodsResponseCode.ORGCODE_NOT_BLANK.getCode(), 
                    GoodsResponseCode.ORGCODE_NOT_BLANK.getMsg());
        }
       List<CntContractModel> cntList = this.commonService.getCntList(orgCode, null);
        return ResponseMessage.ok(cntList);
    }
    
    /**
     * 通过组织及供应商获取合同
     * @param req
     * @return ResponseMessage
     */
    @RequestMapping(value = "/getCntListByEtp", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage getCntListByEtp(@RequestBody CommonOrgAndSupAndCntReq req) {
        String orgCode = req.getOrgCode();
        String etpCode = req.getEtpCode();
        if (StringUtils.isBlank(orgCode)) {
            throw new ServiceException(GoodsResponseCode.ORGCODE_NOT_BLANK.getCode(), 
                    GoodsResponseCode.ORGCODE_NOT_BLANK.getMsg());
        }
        if (StringUtils.isBlank(etpCode)) {
            throw new ServiceException(GoodsResponseCode.ETPCODE_NOT_BLANK.getCode(), 
                    GoodsResponseCode.ETPCODE_NOT_BLANK.getMsg());
        }
       List<CntContractModel> cntList = this.commonService.getCntList(orgCode, etpCode);
        return ResponseMessage.ok(cntList);
    }
    
    /**
     * 通过组织、合同获取商品
     * @param req
     * @return ResponseMessage
     */
    @RequestMapping(value = "/getSkuList", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage getSkuList(@RequestBody CommonOrgAndSupAndCntReq req) {
        String orgCode = req.getOrgCode();
        Long cntId = req.getCntId();
        if (StringUtils.isBlank(orgCode)) {
            throw new ServiceException(GoodsResponseCode.ORGCODE_NOT_BLANK.getCode(), 
                    GoodsResponseCode.ORGCODE_NOT_BLANK.getMsg());
        }
        if (null == cntId) {
            throw new ServiceException(GoodsResponseCode.CNTID_NOT_BLANK.getCode(), 
                    GoodsResponseCode.CNTID_NOT_BLANK.getMsg());
        }
       List<SkuPluModel> skuList = this.commonService.getSkuList(orgCode, cntId);
       return ResponseMessage.ok(skuList);
    }
    
    /**
     * 通过组织编码获取部门
     * @param req
     * @return ResponseMessage
     */
    @RequestMapping(value = "/getDeptList", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage getDeptList(@RequestBody CommonOrgAndSupAndCntReq req) {
        String orgCode = req.getOrgCode();
        if (StringUtils.isBlank(orgCode)) {
            throw new ServiceException(GoodsResponseCode.ORGCODE_NOT_BLANK.getCode(), 
                    GoodsResponseCode.ORGCODE_NOT_BLANK.getMsg());
        }
        List<OrgDeptModel> deptList = this.commonService.getDeptList(orgCode);
        return ResponseMessage.ok(deptList);
    }
    
    /**
     * 通过组织编码获取仓库
     * @param req
     * @return ResponseMessage
     */
    @RequestMapping(value = "/getStoreList", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage getStoreList(@RequestBody CommonOrgAndSupAndCntReq req) {
        String orgCode = req.getOrgCode();
        if (StringUtils.isBlank(orgCode)) {
            throw new ServiceException(GoodsResponseCode.ORGCODE_NOT_BLANK.getCode(), 
                    GoodsResponseCode.ORGCODE_NOT_BLANK.getMsg());
        }
        List<StkStoreModel> storeList = this.commonService.getStoreList(orgCode);
        return ResponseMessage.ok(storeList);
    }
    
    /**
     * 获取所有组织
     * @return ResponseMessage
     */
    @RequestMapping(value = "/getAllOrgModel", method = RequestMethod.GET)
    @ResponseBody
    public ResponseMessage getAllOrgModel() {
        List<OrgManageModel> allOrgModel = this.commonService.getAllOrgModel();
        return ResponseMessage.ok(allOrgModel);
    }
    
    /**
     * 通过组织、部门、仓库查询商品
     * @param req
     * @return ResponseMessage
     */
    @RequestMapping(value = "/getSkuListByDept", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage getSkuListByDept(@RequestBody @Valid CommonOrgAndDeptReq req) {
        List<StkStoreModel> storeList = this.commonService.getStoreList(req.getOrgCode());
        if (null == storeList || storeList.isEmpty()) {
            throw new ServiceException(GoodsResponseCode.CK_NOT_BLANK.getCode(), 
                    GoodsResponseCode.CK_NOT_BLANK.getMsg());
        }
       List<SkuPluExtendModel> skuList = this.commonService.getSkuListByDept(req.getOrgCode(), req.getShOrgCode(), 
               req.getDeptId(), storeList.get(0).getCkCode());
       return ResponseMessage.ok(skuList);
    }
    
    /**
     * 通过组织、部门查询生产计划商品
     * @param req
     * @return ResponseMessage
     */
    @RequestMapping(value = "/getProPlanSkuListByDept", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage getProPlanSkuListByDept(@RequestBody CommonOrgAndSupAndCntReq req) {
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
       List<SkuPluModel> skuList = this.commonService.getProPlanSkuListByDept(orgCode, depId);
       return ResponseMessage.ok(skuList);
    }
    
    /**
     * 搜索用户:用户ID、用户编码、用户名
     * @param req
     * @return ResponseMessage
     */
    @RequestMapping(value = "/getAllUsers", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage getAllUsers(@RequestBody CommonSearchReq req) {
        Map<String, Object> map = new HashMap<>();
        map.put("searchContent", req.getSearchContent());
        List<UsrUserModel> allUsers = this.commonService.getAllUsers(map);
        return ResponseMessage.ok(allUsers);
    }
    
    /**
     * 搜索员工:员工ID、员工编码、员工姓名
     * @param req
     * @return ResponseMessage
     */
    @RequestMapping(value = "/getPurchaseEmp", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage getPurchaseEmp(@RequestBody CommonSearchReq req) {
        Map<String, Object> map = new HashMap<>();
        map.put("searchContent", req.getSearchContent());
        List<UserEmployeeModel> emps = this.commonService.getPurchaseEmp(map);
        return ResponseMessage.ok(emps);
    }
    
    /**
     * 搜索商品品类:品类ID、品类编码、品类名称
     * @param req
     * @return ResponseMessage
     */
    @RequestMapping(value = "/getCatCategorys", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage getCatCategorys(@RequestBody CommonSearchReq req) {
        Map<String, Object> map = new HashMap<>();
        map.put("searchContent", req.getSearchContent());
        List<CatCategoryModel> catCategorys = this.commonService.getCatCategorys(map);
        return ResponseMessage.ok(catCategorys);
    }
    
    /**
     * 搜索商品品牌：品牌编码、品牌名称
     * @param req
     * @return ResponseMessage
     */
    @RequestMapping(value = "/getBasBrands", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage getBasBrands(@RequestBody CommonSearchReq req) {
        Map<String, Object> map = new HashMap<>();
        map.put("searchContent", req.getSearchContent());
        List<BasBrandModel> basBrands = this.commonService.getBasBrands(map);
        return ResponseMessage.ok(basBrands);
    }
    
    /**
     * 获取采购退货商品
     * @param req
     * @return ResponseMessage
     */
    @RequestMapping(value = "/getPurchaseReturnSkus", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage getPurchaseReturnSkus(@RequestBody @Valid CommonOrgAndSupAndCntReq req) {
        String orgCode = req.getOrgCode();
        Long cntId = req.getCntId();
        if (StringUtils.isBlank(orgCode)) {
            throw new ServiceException(GoodsResponseCode.ORGCODE_NOT_BLANK.getCode(), 
                    GoodsResponseCode.ORGCODE_NOT_BLANK.getMsg());
        }
        if (null == cntId) {
            throw new ServiceException(GoodsResponseCode.CNTID_NOT_BLANK.getCode(), 
                    GoodsResponseCode.CNTID_NOT_BLANK.getMsg());
        }
        List<StkStoreModel> storeList = this.commonService.getStoreList(orgCode);
        if (null == storeList || storeList.isEmpty()) {
            throw new ServiceException(GoodsResponseCode.CK_NOT_BLANK.getCode(), 
                    GoodsResponseCode.CK_NOT_BLANK.getMsg());
        }
       List<SkuPluExtendModel> skuList = this.commonService.getPurchaseReturnSkus(orgCode, cntId, storeList.get(0).getCkCode());
       return ResponseMessage.ok(skuList);
    }
}
