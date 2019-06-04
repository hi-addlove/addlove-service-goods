package com.addlove.service.goods.constants;

/**
 * 
 * @author lw
 *
 */
public interface LoginMsgCode {
    /**
     * 登录的code
     */
    public enum LoginCode {
        /**
         * 账户不存在
         */
        ACCOUNT(1000, "账户不存在"),
        /**
         * 密码错误
         */
        PASSWORD(1001, "密码错误"),
        /**
         * 请先登录
         */
        SESSION(-1, "请先登录"),
        /**
         * 登录成功
         */
        SUCCESS(0, "登录成功");
        private int code;
        private String msg;

        LoginCode(int code, String msg) {
            this.code = code;
            this.msg = msg;
        }

        /**
         * @return code
         */
        public int getCode() {
            return code;
        }

        /**
         * @param code code
         */
        public void setCode(int code) {
            this.code = code;
        }

        /**
         * @return msg
         */
        public String getMsg() {
            return msg;
        }

        /**
         * @param msg msg
         */
        public void setMsg(String msg) {
            this.msg = msg;
        }
    }
}
