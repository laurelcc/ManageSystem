package com.model.vo;

import com.model.vo.consts.NotificationConst;

import java.io.Serializable;

/**
 * Created by soong on 2017/7/23.
 */
public class Notification implements Serializable {
    private String title;
    private String message;
    private Integer close;
    protected Integer bar;
    protected String type;
    protected String position;

    public Notification(String title, String message, int canClose, int bar, String type, String position){
        this.title = title;
        this.message = message;
        this.close = canClose;
        this.bar = bar;
        this.type = type;
        this.position = position;
    }

    public static Notification success(String title, String message){
        return new Notification(title, message, 1, 1, NotificationConst.TOAST_SUCCESS, NotificationConst.POSITION_TOP_RIGHT);
    }

    public static Notification info(String title, String message){
        return new Notification(title, message, 1, 1, NotificationConst.TOAST_INFO, NotificationConst.POSITION_TOP_RIGHT);
    }

    public static Notification error(String title, String message){
        return new Notification(title, message, 1, 1, NotificationConst.TOAST_ERROR, NotificationConst.POSITION_TOP_RIGHT);
    }

    public static Notification warning(String title, String message){
        return new Notification(title, message, 1, 1, NotificationConst.TOAST_WARNING, NotificationConst.POSITION_TOP_RIGHT);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getClose() {
        return close;
    }

    public void setClose(Integer close) {
        this.close = close;
    }

    public Integer getBar() {
        return bar;
    }

    public void setBar(Integer bar) {
        this.bar = bar;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
