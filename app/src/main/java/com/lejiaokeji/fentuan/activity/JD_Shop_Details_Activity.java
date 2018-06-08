package com.lejiaokeji.fentuan.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.lejiaokeji.fentuan.control.Shop_Details;

public class JD_Shop_Details_Activity extends AppCompatActivity{
    String shopid="";
    Shop_Details shop_details;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        shopid=getIntent().getStringExtra("shopid");
        shop_details=Shop_Details.getInstance();
        shop_details.getUnionData("1331837784",shopid);
    }
}
