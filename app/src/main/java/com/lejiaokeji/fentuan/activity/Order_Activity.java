package com.lejiaokeji.fentuan.activity;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.lejiaokeji.fentuan.R;

import java.util.ArrayList;
import java.util.List;

public class Order_Activity extends AppCompatActivity implements View.OnClickListener{
    ImageView im_order_back;
    TextView tv_order_all;
    TextView tv_order_pay;
    TextView tv_order_shou;
    TextView tv_order_jiesuan;
    TextView tv_order_shixiao;
TextView textView;
ImageView imageView;
    ImageView im_order_all;
    ImageView im_order_pay;
    ImageView im_order_shou;
    ImageView im_order_jiesuan;
    ImageView im_order_shixiao;

    List<TextView> textViewList=new ArrayList<>();
    List<ImageView> imageViewList=new ArrayList<>();




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.parseColor("#ff286e"));
        }
        setContentView(R.layout.activity_order);
        im_order_back=findViewById(R.id.im_order_back);
        im_order_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

         tv_order_all=findViewById(R.id.tv_order_all);
         tv_order_pay=findViewById(R.id.tv_order_pay);
         tv_order_shou=findViewById(R.id.tv_order_shou);
         tv_order_jiesuan=findViewById(R.id.tv_order_jiesuan);
         tv_order_shixiao=findViewById(R.id.tv_order_shixiao);
         im_order_all=findViewById(R.id.im_order_all);
         im_order_pay=findViewById(R.id.im_order_pay);
         im_order_shou=findViewById(R.id.im_order_shouhuo);
         im_order_jiesuan=findViewById(R.id.im_order_jiesuan);
         im_order_shixiao=findViewById(R.id.im_order_shixiao);
         textViewList.add(tv_order_all);
         imageViewList.add(im_order_all);

         tv_order_all.setOnClickListener(this);
        tv_order_pay.setOnClickListener(this);
        tv_order_shou.setOnClickListener(this);
        tv_order_jiesuan.setOnClickListener(this);
        tv_order_shixiao.setOnClickListener(this);



    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case  R.id.tv_order_all:{
                textView=textViewList.get(0);
                imageView=imageViewList.get(0);
                textView.setTextColor(Color.parseColor("#333333"));
                imageView.setVisibility(View.GONE);
                textViewList.clear();
                imageViewList.clear();
                textViewList.add(tv_order_all);
                imageViewList.add(im_order_all);
                tv_order_all.setTextColor(Color.parseColor("#ff2d55"));
                im_order_all.setVisibility(View.VISIBLE);

                break;
            }
            case  R.id.tv_order_pay:{
                textView=textViewList.get(0);
                imageView=imageViewList.get(0);
                textView.setTextColor(Color.parseColor("#333333"));
                imageView.setVisibility(View.GONE);
                textViewList.clear();
                imageViewList.clear();
                textViewList.add(tv_order_pay);
                imageViewList.add(im_order_pay);
                tv_order_pay.setTextColor(Color.parseColor("#ff2d55"));
                im_order_pay.setVisibility(View.VISIBLE);
                break;
            }
            case  R.id.tv_order_shou:{
                textView=textViewList.get(0);
                imageView=imageViewList.get(0);
                textView.setTextColor(Color.parseColor("#333333"));
                imageView.setVisibility(View.GONE);
                textViewList.clear();
                imageViewList.clear();
                textViewList.add(tv_order_shou);
                imageViewList.add(im_order_shou);
                tv_order_shou.setTextColor(Color.parseColor("#ff2d55"));
                im_order_shou.setVisibility(View.VISIBLE);
                break;
            }
            case  R.id.tv_order_jiesuan:{
                textView=textViewList.get(0);
                imageView=imageViewList.get(0);
                textView.setTextColor(Color.parseColor("#333333"));
                imageView.setVisibility(View.GONE);
                textViewList.clear();
                imageViewList.clear();
                textViewList.add(tv_order_jiesuan);
                imageViewList.add(im_order_jiesuan);
                tv_order_jiesuan.setTextColor(Color.parseColor("#ff2d55"));
                im_order_jiesuan.setVisibility(View.VISIBLE);
                break;
            }
            case  R.id.tv_order_shixiao:{
                textView=textViewList.get(0);
                imageView=imageViewList.get(0);
                textView.setTextColor(Color.parseColor("#333333"));
                imageView.setVisibility(View.GONE);
                textViewList.clear();
                imageViewList.clear();
                textViewList.add(tv_order_shixiao);
                imageViewList.add(im_order_shixiao);
                tv_order_shixiao.setTextColor(Color.parseColor("#ff2d55"));
                im_order_shixiao.setVisibility(View.VISIBLE);
                break;
            }
        }
    }
}
