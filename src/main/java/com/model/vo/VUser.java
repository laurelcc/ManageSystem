package com.model.vo;

import com.model.User;

import java.io.Serializable;

/**
 * Created by soong on 2017/7/23.
 */
public class VUser implements Serializable {
    private String id;
    private String username;
    private String alias;
    private String province;
    private String city;

    public VUser(String id, String name, String alias, String province, String city){
        this.id = id;
        this.username = name;
        this.alias = alias;
        this.province = province;
        this.city = city;
    }

    public VUser(User user){
        this.id = user.getId();
        this.username = user.getUsername();
        this.alias = user.getAlias();
        this.province = user.getProvince();
        this.city = user.getCity();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
