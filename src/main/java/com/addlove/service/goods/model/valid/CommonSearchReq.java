package com.addlove.service.goods.model.valid;

/**
 * 搜索内容
 * @author lw
 *
 */
public class CommonSearchReq {
    /**
     * 搜索内容:(包含：用户ID、用户编码、用户名)、（包含：员工ID、员工编码、员工姓名）
     * （包含：品类ID、品类编码、品类名称）、（包含：品牌编码、品牌名称）、（包含：单据号）
     */
    private String searchContent;

    /**
     * @return the searchContent
     */
    public String getSearchContent() {
        return searchContent;
    }

    /**
     * @param searchContent the searchContent to set
     */
    public void setSearchContent(String searchContent) {
        this.searchContent = searchContent;
    }
}
