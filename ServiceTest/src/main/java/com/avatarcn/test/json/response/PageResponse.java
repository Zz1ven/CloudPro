package com.avatarcn.test.json.response;

import java.util.List;

/**
 * Created by z1ven on 2017/12/20 15:28
 */
public class PageResponse<T> {

    private int total;
    private int offset;
    private int pageSize;
    private List<T> items;

    public PageResponse() {
    }

    public PageResponse(int total, int offset, int pageSize, List<T> items) {
        this.total = total;
        this.offset = offset;
        this.pageSize = pageSize;
        this.items = items;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }
}
