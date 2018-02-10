package com.avatarcn.tourists.json.response;

import java.util.List;

/**
 * Created by MDF on 2018-1-23.
 */
public class PageFoodAssessResponse {
    private List<FoodAssessResponse> assesses;
    private int offset;
    private int pageSize;
    private int assessCount;


    public List<FoodAssessResponse> getAssesses() {
        return assesses;
    }

    public void setAssesses(List<FoodAssessResponse> assesses) {
        this.assesses = assesses;
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

    public int getAssessCount() {
        return assessCount;
    }

    public void setAssessCount(int assessCount) {
        this.assessCount = assessCount;
    }


}
