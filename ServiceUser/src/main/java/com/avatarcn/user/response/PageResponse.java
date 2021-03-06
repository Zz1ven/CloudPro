package com.avatarcn.user.response;



import java.util.List;


/**
 * Created by AutoCode on 2018-1-17
 */
public class PageResponse<T> {

    private Integer total;
    private Integer offset;
    private Integer pageSize;
    private List<T> item;

    public PageResponse(){}

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public List<T> getItem() {
        return item;
    }

    public void setItem(List<T> item) {
        this.item = item;
    }
}
