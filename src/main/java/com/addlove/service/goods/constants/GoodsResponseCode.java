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
     * 商品不能为空
     */
    SKU_NOT_BLANK(20003, "商品不能为空"),
    
    /**
     * 单据记账前请先保存
     */
    SAVE_BEFORE_ACCOUNT(20004, "单据记账前请先保存"),
    
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
    DEP_ID_NOT_BLANK(20009, "部门ID不能为空"),
    
    /**
     * 执行完工存储过程错误
     */
    EXEC_DONE_PROCEDURE_ERROR(20010, "执行完工存储过程错误"),
    
    /**
     * 执行启动盘点存储过程错误
     */
    EXEC_START_PD_PROCEDURE_ERROR(20011, "执行启动盘点存储过程"),
    
    /**
     * 执行盘点记账存储过程错误
     */
    EXEC_PD_ACCOUNT_PROCEDURE_ERROR(20012, "执行盘点记账存储过程"),
    
    /**
     * 模板编码不能为空
     */
    MODEL_CODE_NOT_BLANK(20013, "模板编码不能为空"),
    
    /**
     * 要货波次不能为空
     */
    YH_BC_NOT_BLANK(20014, "要货波次不能为空"),
    
    /**
     * 系统加工工艺设置不能为空
     */
    JG_GY_NOT_BLANK(20015, "系统加工工艺设置不能为空");
    
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
