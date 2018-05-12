package com.lejiaokeji.fentuan.databean;

import com.facebook.drawee.view.SimpleDraweeView;

public class Item_Shop_RecommentBean {
    String headerurl;
    String name;
    String time;
    String sharecount;
    SimpleDraweeView simpleDraweeView1;
    SimpleDraweeView simpleDraweeView2;
    SimpleDraweeView simpleDraweeView3;
    String yongjin;

    public Item_Shop_RecommentBean(String headerurl, String name, String time, String sharecount, SimpleDraweeView simpleDraweeView1, SimpleDraweeView simpleDraweeView2, SimpleDraweeView simpleDraweeView3, String yongjin) {
        this.headerurl = headerurl;
        this.name = name;
        this.time = time;
        this.sharecount = sharecount;
        this.simpleDraweeView1 = simpleDraweeView1;
        this.simpleDraweeView2 = simpleDraweeView2;
        this.simpleDraweeView3 = simpleDraweeView3;
        this.yongjin = yongjin;
    }

    public String getHeaderurl() {
        return headerurl;
    }

    public void setHeaderurl(String headerurl) {
        this.headerurl = headerurl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getSharecount() {
        return sharecount;
    }

    public void setSharecount(String sharecount) {
        this.sharecount = sharecount;
    }

    public SimpleDraweeView getSimpleDraweeView1() {
        return simpleDraweeView1;
    }

    public void setSimpleDraweeView1(SimpleDraweeView simpleDraweeView1) {
        this.simpleDraweeView1 = simpleDraweeView1;
    }

    public SimpleDraweeView getSimpleDraweeView2() {
        return simpleDraweeView2;
    }

    public void setSimpleDraweeView2(SimpleDraweeView simpleDraweeView2) {
        this.simpleDraweeView2 = simpleDraweeView2;
    }

    public SimpleDraweeView getSimpleDraweeView3() {
        return simpleDraweeView3;
    }

    public void setSimpleDraweeView3(SimpleDraweeView simpleDraweeView3) {
        this.simpleDraweeView3 = simpleDraweeView3;
    }

    public String getYongjin() {
        return yongjin;
    }

    public void setYongjin(String yongjin) {
        this.yongjin = yongjin;
    }
}
