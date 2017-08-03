package com.model.VO;

import com.model.XUser;

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

    public VUser(XUser XUser){
        this.id = XUser.getId();
        this.username = XUser.getUsername();
        this.alias = XUser.getAlias();
        this.province = XUser.getProvince();
        this.city = XUser.getCity();
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
