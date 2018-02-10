package com.avatarcn.test.json.response;

import java.util.List;

/**
 * Created by z1ven on 2017/12/20 15:27
 */
public class AllResponse<T> {

    private int total;
    private List<T> items;

    public AllResponse() {
    }

    public AllResponse(List<T> items) {
        this.items = items;
        this.total = items.size();
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }
}
