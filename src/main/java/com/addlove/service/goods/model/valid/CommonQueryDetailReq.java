package com.addlove.service.goods.model.valid;

import org.hibernate.validator.constraints.NotBlank;

public class CommonQueryDetailReq {
    /**单据号 */
    @NotBlank(message = "单据号不能为空")
    private String billNo;

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
