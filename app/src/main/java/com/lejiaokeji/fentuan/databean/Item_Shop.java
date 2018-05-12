package com.lejiaokeji.fentuan.databean;

import com.facebook.drawee.view.SimpleDraweeView;

public class Item_Shop {
    String photourl;
    String title;
    String discounturl;
    String price;
    String yongjin;
    String salecount;

    public String getPhotourl() {
        return photourl;
    }

    public void setPhotourl(String photourl) {
        this.photourl = photourl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDiscounturl() {
        return discounturl;
    }

    public void setDiscounturl(String discounturl) {
        this.discounturl = discounturl;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getYongjin() {
        return yongjin;
    }

    public void setYongjin(String yongjin) {
        this.yongjin = yongjin;
    }

    public String getSalecount() {
        return salecount;
    }

    public void setSalecount(String salecount) {
        this.salecount = salecount;
    }

    public Item_Shop(String photourl, String title, String discounturl, String price, String yongjin, String salecount) {
        this.photourl = photourl;
        this.title = title;
        this.discounturl = discounturl;
        this.price = price;
        this.yongjin = yongjin;
        this.salecount = salecount;
    }
}
