package com.lejiaokeji.fentuan.databean;

public class Item_Shop {
    String goods_id;    //商品ID
    String goods_image;  //商品图片URL
    String goods_name;  //标题
    String discount_link;  //领券链接
    String coupon_price; //最终价格
    String commission;  //佣金
    String discount_price; //折扣券面额
    int sale_num; //已卖出数量

    public Item_Shop() {
    }

    public Item_Shop(String id, String goods_image, String goods_name, String discount_link, String coupon_price, String commission, String discount_price, int sale_num) {
        this.goods_id = id;
        this.goods_image = goods_image;
        this.goods_name = goods_name;
        this.discount_link = discount_link;
        this.coupon_price = coupon_price;
        this.commission = commission;
        this.discount_price = discount_price;
        this.sale_num = sale_num;
    }


    public String getGoods_image() {
        return goods_image;
    }

    public void setGoods_image(String goods_image) {
        this.goods_image = goods_image;
    }

    public String getGoods_id() {
        return goods_id;
    }

    public void setGoods_id(String goods_id) {
        this.goods_id = goods_id;
    }

    public String getGoods_name() {
        return goods_name;
    }

    public void setGoods_name(String goods_name) {
        this.goods_name = goods_name;
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
    public String getCommission() {
        return commission;
    }

    public void setCommission(String commission) {
        this.commission = commission;
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
