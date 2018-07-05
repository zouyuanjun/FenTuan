package com.lejiaokeji.fentuan.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.lejiaokeji.fentuan.R;
import com.lejiaokeji.fentuan.wxapi.Constants;

public class Activity_tixian extends AppCompatActivity {
    ImageView im_tixian_back;
    ConstraintLayout add_taobao;
    Activity activity;
    TextView tv_alipayaccount;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.parseColor("#ff286e"));
        }
        setContentView(R.layout.activity_tixian);
        activity=this;
        im_tixian_back=findViewById(R.id.im_tixian_back);
        tv_alipayaccount=findViewById(R.id.textView30);
        im_tixian_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        add_taobao=findViewById(R.id.constraintLayout9);
        add_taobao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null!=Constants.AlipayAccount){
                    Toast.makeText(activity,"暂不提供修改功能哦",Toast.LENGTH_SHORT).show();
                }else {
               //     Toast.makeText(activity,"要跳转",Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(activity,Add_Alipay_Activity.class);
                    activity.startActivity(intent);
                }

            }
        });
//        Log.d("adssd",Constants.AlipayAccount.length()+Constants.AlipayAccount);
        if (null!=Constants.AlipayAccount){
            tv_alipayaccount.setText(Constants.AlipayAccount);
        }
    }
}
