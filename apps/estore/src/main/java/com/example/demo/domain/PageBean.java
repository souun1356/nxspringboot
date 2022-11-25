package com.example.demo.domain;

import java.util.List;

public class PageBean {
    private int pageNum; // 頁碼
    private int currentPage; // 每頁條數
    private int totalPage; // 總頁數
    private int totalCount; // 總條數
    private List<Product> pro; // 每頁數據

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public List<Product> getPro() {
        return pro;
    }

    public void setPro(List<Product> pro) {
        this.pro = pro;
    }

}
