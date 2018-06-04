package com.lejiaokeji.fentuan.databean;

import java.util.List;

public class Shop_Data {
    String message;
    String retCode;
    List<Item_Shop> data;

    public Shop_Data() {
    }

    public Shop_Data(String message, String retCode, List<Item_Shop> data) {
        this.message = message;
        this.retCode = retCode;
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getRetCode() {
        return retCode;
    }

    public void setRetCode(String retCode) {
        this.retCode = retCode;
    }

    public List<Item_Shop> getData() {
        return data;
    }

    public void setData(List<Item_Shop> data) {
        this.data = data;
    }
}
