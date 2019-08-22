package com.addlove.service.goods.model;

/**
 * 客户多地址维护表
 * @author lw
 *
 */
public class EtpAddressModel {
    /**
     * 企业编码
     */
    private String etpCode;
    
    /**
     * 地址
     */
    private String address;

    /**
     * @return the etpCode
     */
    public String getEtpCode() {
        return etpCode;
    }

    /**
     * @param etpCode the etpCode to set
     */
    public void setEtpCode(String etpCode) {
        this.etpCode = etpCode;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }
}
