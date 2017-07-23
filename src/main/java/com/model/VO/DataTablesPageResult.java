package com.model.VO;

import java.io.Serializable;
import java.util.List;

/**
 * Created by soong on 2017/7/23.
 */
public class DataTablesPageResult<T extends Serializable> {
    private List<T> data;
    private Integer draw;
    private Long recordsTotal;
    private Integer recordsFiltered;

    public DataTablesPageResult(int draw, long total, int filtered, List<T> data){
        this.data = data;
        this.recordsTotal = total;
        this.recordsFiltered = filtered;
        this.draw = draw;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public Integer getDraw() {
        return draw;
    }

    public void setDraw(Integer draw) {
        this.draw = draw;
    }

    public Long getRecordsTotal() {
        return recordsTotal;
    }

    public void setRecordsTotal(Long recordsTotal) {
        this.recordsTotal = recordsTotal;
    }

    public Integer getRecordsFiltered() {
        return recordsFiltered;
    }

    public void setRecordsFiltered(Integer recordsFiltered) {
        this.recordsFiltered = recordsFiltered;
    }
}
