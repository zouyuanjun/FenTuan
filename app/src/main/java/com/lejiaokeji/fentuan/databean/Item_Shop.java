package com.lejiaokeji.fentuan.databean;

import com.facebook.drawee.view.SimpleDraweeView;

public class Item_Shop {
    String goods_id;    //商品ID
    String good_img;  //商品图片URL
    String goods_name;  //标题
    String discount_link;  //领券链接
    String coupon_price; //最终价格
    String yongjin;  //佣金
    String discount_price; //折扣券面额
    int sale_num; //已卖出数量

    public Item_Shop() {
    }

    public Item_Shop(String id, String good_img, String title, String discount_link, String coupon_price, String yongjin, String discount_price, int sale_num) {
        this.goods_id = id;
        this.good_img = good_img;
        this.goods_name = title;
        this.discount_link = discount_link;
        this.coupon_price = coupon_price;
        this.yongjin = yongjin;
        this.discount_price = discount_price;
        this.sale_num = sale_num;
    }

    public String getId() {
        return goods_id;
    }

    public void setId(String id) {
        this.goods_id = id;
    }

    public String getGood_img() {
        return good_img;
    }

    public void setGood_img(String good_img) {
        this.good_img = good_img;
    }
    public String getTitle() {
        return goods_name;
    }
    public void setTitle(String title) {
        this.goods_name = title;
    }
    public String getDiscount_link() {
        return discount_link;
    }
    public void setDiscount_link(String discount_link) {
        this.discount_link = discount_link;
    }
    public String getCoupon_price() {
        return coupon_price;
    }
    public void setCoupon_price(String coupon_price) {
        this.coupon_price = coupon_price;
    }
    public String getYongjin() {
        return yongjin;
    }

    public void setYongjin(String yongjin) {
        this.yongjin = yongjin;
    }

    public String getDiscount_price() {
        return discount_price;
    }

    public void setDiscount_price(String discount_price) {
        this.discount_price = discount_price;
    }

    public int getSale_num() {
        return sale_num;
    }

    public void setSale_num(int sale_num) {
        this.sale_num = sale_num;
    }
}
