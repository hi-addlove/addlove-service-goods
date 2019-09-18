package com.addlove.service.goods.model.valid;

import javax.validation.constraints.NotNull;

public class CouBsApplyBillReq {
    /**部门ID */
    @NotNull(message = "部门Id不能为空")
    private Long depId;

    /** 单据号（用于模糊搜索）*/
    private String billNo;

    /**
     * @return the depId
     */
    public Long getDepId() {
        return depId;
    }

    /**
     * @param depId the depId to set
     */
    public void setDepId(Long depId) {
        this.depId = depId;
    }

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
}
