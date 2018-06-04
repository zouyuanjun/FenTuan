package com.lejiaokeji.fentuan.databean;

public class Item_notificationBean {
    String title;
    String picture;
    String content;
    String time;

    public Item_notificationBean(String title, String picture, String content, String time) {
        this.title = title;
        this.picture = picture;
        this.content = content;
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
