package com.huhusky.common.utils.http.domain;

import java.util.List;

public class PageData<T> {

    public static final int DEFAULT_PAGE_SIZE = 30;

    private int page = 1;
    private int pageSize = DEFAULT_PAGE_SIZE;
    private int pageRows = -1;
    private int hasNext;
    private List<T> data;

    private int totalCount;
    private int totalPage;

    public PageData() {
    }

    public PageData(int page, int pageSize) {
        this.page = page;
        this.pageSize = pageSize;
    }

    public int getPageRow() {
        return getPageRow(page, pageSize);
    }

    public PageData(int page) {
        this.page = page;
    }

    public int getPageRow(int page) {
        return getPageRow(page, pageSize);
    }

    public int getPageRow(int page, int pageSize) {
        page -= 1;
        if (page >= 0 && pageSize >= 0) {
            return page * pageSize;
        }
        return 0;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getHasNext() {
        return hasNext;
    }

    public void setHasNext(int hasNext) {
        this.hasNext = hasNext;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public int getPageRows() {
        return pageRows;
    }

    public void setPageRows(int pageRows) {
        this.pageRows = pageRows;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getTotalPages() {
        if (getPageRows() != -1) {
            return getPageRows() / getPageSize() + 1;
        }
        return 1;
    }
}
