package com.lejiaokeji.fentuan.databean;

import java.util.List;

public class Pdd_Shop_Details_Bean {
    String goods_name;
    String coupon_discount;
    String goods_desc;
    String goods_id;
    String goods_image_url;
    String min_group_price;
    List<String> goods_gallery_urls;
    String has_coupon;

    public String getHas_coupon() {
        return has_coupon;
    }

    public void setHas_coupon(String has_coupon) {
        this.has_coupon = has_coupon;
    }

    public Pdd_Shop_Details_Bean() {
    }

    public String getGoods_name() {
        return goods_name;
    }

    public void setGoods_name(String goods_name) {
        this.goods_name = goods_name;
    }

    public String getCoupon_discount() {
        return coupon_discount;
    }

    public void setCoupon_discount(String coupon_discount) {
        this.coupon_discount = coupon_discount;
    }

    public String getGoods_desc() {
        return goods_desc;
    }

    public void setGoods_desc(String goods_desc) {
        this.goods_desc = goods_desc;
    }

    public String getGoods_id() {
        return goods_id;
    }

    public void setGoods_id(String goods_id) {
        this.goods_id = goods_id;
    }

    public String getGoods_image_url() {
        return goods_image_url;
    }

    public void setGoods_image_url(String goods_image_url) {
        this.goods_image_url = goods_image_url;
    }

    public String getMin_group_price() {
        return min_group_price;
    }

    public void setMin_group_price(String min_group_price) {
        this.min_group_price = min_group_price;
    }

    public List<String> getGoods_gallery_urls() {
        return goods_gallery_urls;
    }

    public void setGoods_gallery_urls(List<String> goods_gallery_urls) {
        this.goods_gallery_urls = goods_gallery_urls;
    }
}
