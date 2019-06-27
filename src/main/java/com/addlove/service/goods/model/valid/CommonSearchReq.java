package com.addlove.service.goods.model.valid;

/**
 * 搜索内容
 * @author lw
 *
 */
public class CommonSearchReq {
    /**
     * 搜索内容:(暂包含：用户ID、用户编码、用户名)
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
