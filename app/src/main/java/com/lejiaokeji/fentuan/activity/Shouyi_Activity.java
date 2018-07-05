package com.lejiaokeji.fentuan.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lejiaokeji.fentuan.R;
import com.lejiaokeji.fentuan.wxapi.Constants;

public class Shouyi_Activity  extends AppCompatActivity implements View.OnClickListener{
    ImageView im_shouyi_back;
    Button bt_tixian;
    Activity activity;
    TextView tv_select_jd;
    TextView tv_select_pdd;
    ImageView select_jd;
    ImageView select_pdd;
    RelativeLayout rl_select_jd;
    TextView tv_yugushouyi;
    TextView tv_shouyicount;
    TextView tv_order;
    TextView tv_order_count;

    TextView tv_team_shouyi;
    TextView tv_team_shouyi_count;
    TextView tv_team_order;
    TextView tv_team_order_count;




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.parseColor("#ff286e"));
        }
        setContentView(R.layout.activity_shouyi);
        activity=this;
        im_shouyi_back=findViewById(R.id.im_shouyi_back);
        select_jd=findViewById(R.id.img_select_jd);
        select_pdd=findViewById(R.id.img_select_pdd);
        rl_select_jd=findViewById(R.id.rl_jd_selcet);
        tv_select_jd=findViewById(R.id.tv_select_jd);
        tv_select_jd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("选择","京东");
                select_pdd.setVisibility(View.GONE);
                select_jd.setVisibility(View.VISIBLE);
                rl_select_jd.setBackground(ContextCompat.getDrawable(activity,R.drawable.home_bg_jd));
                setdata(true);

            }
        });
        tv_select_pdd=findViewById(R.id.tv_select_pingduoduo);
        tv_select_pdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("选择","平多多" );
                rl_select_jd.setBackground(ContextCompat.getDrawable(activity,R.drawable.home_bg_pdd));
                select_pdd.setVisibility(View.VISIBLE);
                select_jd.setVisibility(View.GONE);
                setdata(false);
            }
        });

        bt_tixian=findViewById(R.id.bt_tixian);
        bt_tixian.setOnClickListener(this);
        im_shouyi_back.setOnClickListener(this);

         tv_yugushouyi=findViewById(R.id.tv_yugushouyi);
         tv_shouyicount=findViewById(R.id.tv_shouyicount);
         tv_order=findViewById(R.id.tv_order);
         tv_order_count=findViewById(R.id.tv_order_count);

         tv_team_shouyi=findViewById(R.id.tv_team_shouyi);
         tv_team_shouyi_count=findViewById(R.id.tv_team_shouyi_count);
         tv_team_order=findViewById(R.id.tv_team_order);
         tv_team_order_count=findViewById(R.id.tv_team_order_count);

        setdata(false);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.im_shouyi_back:{
                finish();
                break;
            }
            case R.id.bt_tixian:{
                Log.d("sdfa","sdfasdf");
                Intent intent=new Intent(activity,Activity_tixian.class);
                activity.startActivity(intent);
                break;
            }
        }
    }

    public void setdata(boolean isjd){
        if (isjd){
            tv_yugushouyi.setText("预估京东收益");
            tv_shouyicount.setText("0.00元");
            tv_order.setText("京东订单数");
            tv_order_count.setText("0笔");

            tv_team_shouyi.setText("预估京东收益");
            tv_team_shouyi_count.setText("0.00元");
            tv_team_order.setText("京东订单数");
            tv_team_order_count.setText("0笔");
        }else {
            tv_yugushouyi.setText("预估拼多多收益");
            tv_shouyicount.setText("0.00元");
            tv_order.setText("拼多多订单数");
            tv_order_count.setText("0笔");

            tv_team_shouyi.setText("预估拼多多收益");
            tv_team_shouyi_count.setText("0.00元");
            tv_team_order.setText("拼多多订单数");
            tv_team_order_count.setText("0笔");
        }


    }

}
