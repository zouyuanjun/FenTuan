package com.lejiaokeji.fentuan.databean;

public class Item_notificationBean {
    String title;
    String imgurl;
    String ms;
    String startTime;

    public Item_notificationBean() {
    }

    public Item_notificationBean(String title, String imgurl, String ms, String startTime) {
        this.title = title;
        this.imgurl = imgurl;
        this.ms = ms;
        this.startTime = startTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    public String getContent() {
        return ms;
    }

    public void setContent(String content) {
        this.ms = content;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }
}
