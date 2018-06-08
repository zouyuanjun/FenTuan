package com.lejiaokeji.fentuan.activity;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.facebook.drawee.view.SimpleDraweeView;
import com.lejiaokeji.fentuan.R;
import com.lejiaokeji.fentuan.control.Shop_Details;
import com.lejiaokeji.fentuan.databean.JD_Shop_Details_Bean;
import com.lejiaokeji.fentuan.utils.BitmapUtil;
import com.lejiaokeji.fentuan.utils.WX_Share;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JD_Shop_Details_Activity extends AppCompatActivity{
    String shopid="";
    String discount_link="";
    String discount="";
    String yongjin="";
    Shop_Details shop_details;
    ImageView simpleDraweeView;
    ImageView im_detail_back;
    TextView tv_yongjing;
    TextView tv_title;
    TextView tv_sale_price;
    TextView tv_price;
    Button bt_add_mall;
    TextView tv_lingquan;
    TextView tv_tuiguang;
    Activity activity;
    RelativeLayout relativeLayout;
    RelativeLayout lingquan;
    RelativeLayout tuiguang;

    BitmapUtil bitmapUtil;
    View view;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jd_shop_details);
        activity=this;
        shopid=getIntent().getStringExtra("shopid");
        discount_link=getIntent().getStringExtra("link");
        discount=getIntent().getStringExtra("discount");
        yongjin=getIntent().getStringExtra("yongjin");
        simpleDraweeView=findViewById(R.id.simpleDraweeView);
        im_detail_back=findViewById(R.id.im_detail_back);
        tv_yongjing=findViewById(R.id.tv_detail_yongjin);
        tv_title=findViewById(R.id.tv_detail_title);
        tv_sale_price=findViewById(R.id.tv_sale_price);
        tv_price=findViewById(R.id.tv_begin_price);
        bt_add_mall=findViewById(R.id.bt_detail_add_mall);
        tv_lingquan=findViewById(R.id.tv_lingquan_count);
        tv_tuiguang=findViewById(R.id.tv_tuiguang_count);
        lingquan=findViewById(R.id.rv_lingquan);
        tuiguang=findViewById(R.id.rv_tuiguang);
        shop_details=Shop_Details.getInstance();
        setlistener();
        shop_details.getUnionData("1331837784",shopid);
        shop_details.getdata(shopid);

    }

    public void setlistener(){
        shop_details.setslistener(new Shop_Details.DetailsListener() {
            @Override
            public void getdatasuccessful(JD_Shop_Details_Bean shop_data) {
                Glide.with(activity)
                        .load(shop_data.getImgUrl())
                        .into(simpleDraweeView);
                tv_lingquan.setText(discount);
                tv_price.setText("原价：￥"+shop_data.getUnitPrice());
                float sale_price=Float.parseFloat(shop_data.getUnitPrice())-Float.parseFloat(discount);
                tv_sale_price.setText("￥"+sale_price);
                tv_tuiguang.setText("￥"+yongjin);
                tv_yongjing.setText("佣金：￥"+yongjin);
                tv_title.setText(shop_data.getGoodsName());
            }

            @Override
            public void signfail(String t) {

            }

            @Override
            public void fistlogin() {

            }

            @Override
            public void severerr() {

            }
        });
        lingquan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        tuiguang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    getbitmap();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
    public void setdata(){



    }

    public void  getbitmap() throws IOException {
        relativeLayout=findViewById(R.id.relative);
        relativeLayout.setDrawingCacheEnabled(true);
        view=activity.getWindow().getDecorView();
        view.setDrawingCacheEnabled(true);//生成View的副本，是一个Bitmap
        Bitmap bmp = relativeLayout.getDrawingCache();//获取View的副本，就是这个View上面所显示的任何内容
        simpleDraweeView.setDrawingCacheEnabled(true);
        Bitmap bitmap =simpleDraweeView.getDrawingCache();
        bitmapUtil=BitmapUtil.getinstance();
        Bitmap addbitmap= bitmapUtil.addBitmap(bmp,bitmap);
        String path="/data/user/0/com.lejiaokeji.fentuan/cache/";
        String filename="5645.png";
        bitmapUtil.saveFile(addbitmap,path,filename);
        List<String> imageUris=new ArrayList<>();
        imageUris.add(path+filename);
        WX_Share.sharePhotosToWX(activity,"hahahah",imageUris);
     //   WX_Share.sharePhotoToWX(activity,"hahahah",path+filename);
        Log.d("55","截图完成");

    }

}
