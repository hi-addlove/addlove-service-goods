package com.addlove.service.goods.model.valid;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 库存查询报表参数
 * @author lw
 *
 */
public class QueryStockReportReq {
    /**组织编码 */
    @NotBlank(message = "组织编码不能为空")
    private String orgCode;
    /**商品信息：商品编码、商品名称、商品条形码 */
    private String pluInfo;
    /**品类编码 */
    private String clsCode;
    /**品牌编码 */
    private String brandCode;
    /**查询模式：1-大于0；2-小于0；3-全部；4-等于0 */
    private String queryModel;
    /**展现模式：1-品类；2-单品 */
    private String showModel;
    /**采购员 */
    private String empCode;
    /**部门编码 */
    private String depCode;
    /**
     * @return the orgCode
     */
    public String getOrgCode() {
        return orgCode;
    }
    /**
     * @param orgCode the orgCode to set
     */
    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }
    /**
     * @return the pluInfo
     */
    public String getPluInfo() {
        return pluInfo;
    }
    /**
     * @param pluInfo the pluInfo to set
     */
    public void setPluInfo(String pluInfo) {
        this.pluInfo = pluInfo;
    }
    /**
     * @return the clsCode
     */
    public String getClsCode() {
        return clsCode;
    }
    /**
     * @param clsCode the clsCode to set
     */
    public void setClsCode(String clsCode) {
        this.clsCode = clsCode;
    }
    /**
     * @return the brandCode
     */
    public String getBrandCode() {
        return brandCode;
    }
    /**
     * @param brandCode the brandCode to set
     */
    public void setBrandCode(String brandCode) {
        this.brandCode = brandCode;
    }
    /**
     * @return the queryModel
     */
    public String getQueryModel() {
        return queryModel;
    }
    /**
     * @param queryModel the queryModel to set
     */
    public void setQueryModel(String queryModel) {
        this.queryModel = queryModel;
    }
    /**
     * @return the showModel
     */
    public String getShowModel() {
        return showModel;
    }
    /**
     * @param showModel the showModel to set
     */
    public void setShowModel(String showModel) {
        this.showModel = showModel;
    }
    /**
     * @return the empCode
     */
    public String getEmpCode() {
        return empCode;
    }
    /**
     * @param empCode the empCode to set
     */
    public void setEmpCode(String empCode) {
        this.empCode = empCode;
    }
    /**
     * @return the depCode
     */
    public String getDepCode() {
        return depCode;
    }
    /**
     * @param depCode the depCode to set
     */
    public void setDepCode(String depCode) {
        this.depCode = depCode;
    }
}
