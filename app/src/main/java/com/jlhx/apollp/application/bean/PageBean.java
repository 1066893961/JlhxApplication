package com.jlhx.apollp.application.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Author:lwz
 * Time:2019/3/8
 * Description:
 */

public class PageBean<T> implements Serializable{
    /**
     * currentPage : 1
     * total : 5
     * pageSize : 10
     * pageSizes : 1
     */

    private int currentPage;
    private int total;
    private int pageSize;
    private int pageSizes;
    private List<T> data;

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageSizes() {
        return pageSizes;
    }

    public void setPageSizes(int pageSizes) {
        this.pageSizes = pageSizes;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public boolean needLoadMore(){
        return pageSizes>currentPage;
    }
}
