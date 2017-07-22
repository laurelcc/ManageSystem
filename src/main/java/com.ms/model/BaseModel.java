package com.ms.model;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

/**
 * Created by huanbenwang on 2017/7/21.
 */
@MappedSuperclass
public class BaseModel implements Serializable {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "uuid", length = 32)
    private String id = "";

//    @Column(columnDefinition="datetime default current_timestamp", insertable = false, updatable = false)
    @CreatedDate
    private Date createTime;

//    @Column(columnDefinition="datetime default current_timestamp", insertable = false, updatable = false)
    @LastModifiedDate
    private Date updateTime;

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
