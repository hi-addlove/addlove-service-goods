package com.addlove.service.goods.constants;

/**
 * 定义该服务异常码：20000-22000
 * @author lw
 *
 */
public enum GoodsResponseCode {
    /**
     * 组织编码不能为空
     */
    ORGCODE_NOT_BLANK(20000, "组织编码不能为空"),
    
    /**
     * 供应商编码不能为空
     */
    ETPCODE_NOT_BLANK(20001, "供应商编码不能为空"),
    
    /**
     * 合同ID不能为空
     */
    CNTID_NOT_BLANK(20002, "合同ID不能为空"),
    
    /**
     * 验收单商品不能为空
     */
    JH_SKU_NOT_BLANK(20003, "验收单商品不能为空"),
    
    /**
     * 验收单记账前请先保存
     */
    SAVE_BEFORE_ACCOUNT(20004, "验收单记账前请先保存"),
    
    /**
     * 单据号不能为空
     */
    BILLNO_NOT_BLANK(20005, "单据号不能为空"),
    
    /**
     * 单据操作错误
     */
    BILL_OPRATE_ERROR(20006, "单据操作错误"),
    
    /**
     * 执行记账存储过程错误
     */
    EXEC_PROCEDURE_ERROR(20007, "执行记账存储过程错误"),
    
    /**
     * 仓库不能为空
     */
    CK_NOT_BLANK(20008, "仓库不能为空"),
    
    /**
     * 部门ID不能为空
     */
    DEP_ID_NOT_BLANK(20009, "部门ID不能为空");
    
    private int code;
    private String msg;
    
    private GoodsResponseCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    /**
     * @return the code
     */
    public int getCode() {
        return code;
    }

    /**
     * @param code the code to set
     */
    public void setCode(int code) {
        this.code = code;
    }

    /**
     * @return the msg
     */
    public String getMsg() {
        return msg;
    }

    /**
     * @param msg the msg to set
     */
    public void setMsg(String msg) {
        this.msg = msg;
    }
}
