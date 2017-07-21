package com.ms.model;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

/**
 * Created by huanbenwang on 2017/7/21.
 */
@MappedSuperclass
public class BaseModel implements Serializable {
    private String id = "";

    @Column(columnDefinition="datetime default current_timestamp", insertable = false, updatable = false)
    private Date createTime;

    @Column(columnDefinition="datetime default current_timestamp", insertable = false, updatable = false)
    private Date updateTime;

    public BaseModel(){
        id = UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
