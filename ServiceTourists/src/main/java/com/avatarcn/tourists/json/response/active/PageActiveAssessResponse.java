package com.avatarcn.tourists.json.response.active;

import java.util.List;

/**
 * Created by z1ven on 2018/1/19 14:35
 */
public class PageActiveAssessResponse {
    private List<ActiveAssessResponse> assesses;
    private int offset;
    private int pageSize;
    private int assessCount;
    private float averageScore;

    public List<ActiveAssessResponse> getAssesses() {
        return assesses;
    }

    public void setAssesses(List<ActiveAssessResponse> assesses) {
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

    public float getAverageScore() {
        return averageScore;
    }

    public void setAverageScore(float averageScore) {
        this.averageScore = averageScore;
    }
}
