package com.lejiaokeji.fentuan.databean;

import com.facebook.drawee.view.SimpleDraweeView;

public class Item_Shop_RecommentBean {
    String headerurl;    //头像url
    String name;  //昵称
    String time;  //时间
    String sharecount;    //已分享次数
    String simpleDraweeView1;
    String simpleDraweeView2;
    String simpleDraweeView3;
    String yongjin;  //佣金
    String origin;  //商品来源
    String shop_title; //商品标题

    public Item_Shop_RecommentBean(String headerurl, String name, String time, String sharecount, String simpleDraweeView1, String simpleDraweeView2, String simpleDraweeView3, String yongjin, String origin, String shop_title) {
        this.headerurl = headerurl;
        this.name = name;
        this.time = time;
        this.sharecount = sharecount;
        this.simpleDraweeView1 = simpleDraweeView1;
        this.simpleDraweeView2 = simpleDraweeView2;
        this.simpleDraweeView3 = simpleDraweeView3;
        this.yongjin = yongjin;
        this.origin = origin;
        this.shop_title = shop_title;
    }

    public String getShop_title() {
        return shop_title;
    }

    public void setShop_title(String shop_title) {
        this.shop_title = shop_title;
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

    public String getSimpleDraweeView1() {
        return simpleDraweeView1;
    }

    public void setSimpleDraweeView1(String simpleDraweeView1) {
        this.simpleDraweeView1 = simpleDraweeView1;
    }

    public String getSimpleDraweeView2() {
        return simpleDraweeView2;
    }

    public void setSimpleDraweeView2(String simpleDraweeView2) {
        this.simpleDraweeView2 = simpleDraweeView2;
    }

    public String getSimpleDraweeView3() {
        return simpleDraweeView3;
    }

    public void setSimpleDraweeView3(String simpleDraweeView3) {
        this.simpleDraweeView3 = simpleDraweeView3;
    }

    public String getYongjin() {
        return yongjin;
    }

    public void setYongjin(String yongjin) {
        this.yongjin = yongjin;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }
}
