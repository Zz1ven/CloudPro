package com.avatarcn.tourists.json.response;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * Created by WKL on 2017-3-3.
 */
public class AllResponse<T> {
    @ApiModelProperty("总数")
    private Integer total;

    @ApiModelProperty("内容列表")
    private List<T> item;

    public AllResponse(){}

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }


    public List<T> getItem() {
        return item;
    }

    public void setItem(List<T> item) {
        this.item = item;
    }
}
