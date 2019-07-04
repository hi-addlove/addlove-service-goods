package com.addlove.service.goods.model.valid;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 完工详细参数
 * @author lw
 *
 */
public class ProPlanDoneDetailReq {
    /**
     * 单据号
     */
    @NotBlank(message = "单据号不能为空")
    private String billNo;
    /**
     * 商品ID
     */
    @NotNull(message = "商品Id不能为空")
    private Long pluId;
    /**
     * 要求完工日期
     */
    @NotBlank(message = "要求完工日期不能为空")
    private String requestdate;
    /**
     * 计划时间
     */
    @NotBlank(message = "计划时间不能为空")
    private String plantime;
    /**
     * 完工数量
     */
    @NotNull(message = "完工数量不能为空")
    private Double produceCount;
    
    /**
     * @return the billNo
     */
    public String getBillNo() {
        return billNo;
    }
    /**
     * @param billNo the billNo to set
     */
    public void setBillNo(String billNo) {
        this.billNo = billNo;
    }
    /**
     * @return the pluId
     */
    public Long getPluId() {
        return pluId;
    }
    /**
     * @param pluId the pluId to set
     */
    public void setPluId(Long pluId) {
        this.pluId = pluId;
    }
    /**
     * @return the requestdate
     */
    public String getRequestdate() {
        return requestdate;
    }
    /**
     * @param requestdate the requestdate to set
     */
    public void setRequestdate(String requestdate) {
        this.requestdate = requestdate;
    }
    /**
     * @return the plantime
     */
    public String getPlantime() {
        return plantime;
    }
    /**
     * @param plantime the plantime to set
     */
    public void setPlantime(String plantime) {
        this.plantime = plantime;
    }
    /**
     * @return the produceCount
     */
    public Double getProduceCount() {
        return produceCount;
    }
    /**
     * @param produceCount the produceCount to set
     */
    public void setProduceCount(Double produceCount) {
        this.produceCount = produceCount;
    }
}
