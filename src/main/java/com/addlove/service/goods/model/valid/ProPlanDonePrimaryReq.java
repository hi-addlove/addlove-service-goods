package com.addlove.service.goods.model.valid;

import java.util.List;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * 完工参数
 * @author lw
 *
 */
public class ProPlanDonePrimaryReq {
    @NotEmpty(message = "生产完工数据不能为空")
    private List<ProPlanDoneDetailReq> doneList;

    /**
     * @return the doneList
     */
    public List<ProPlanDoneDetailReq> getDoneList() {
        return doneList;
    }

    /**
     * @param doneList the doneList to set
     */
    public void setDoneList(List<ProPlanDoneDetailReq> doneList) {
        this.doneList = doneList;
    }
}
