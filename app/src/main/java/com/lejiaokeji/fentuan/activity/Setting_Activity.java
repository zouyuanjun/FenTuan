package com.lejiaokeji.fentuan.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.lejiaokeji.fentuan.R;
import com.lejiaokeji.fentuan.wxapi.Constants;

public class Setting_Activity extends AppCompatActivity {
    SimpleDraweeView sdv_photo;
    ImageView im_setting_back;
    TextView tv_chang_hear;
    Button bt_exit;
    Activity activity;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.parseColor("#ff286e"));
        }
        setContentView(R.layout.activity_setting);
        activity=this;
        im_setting_back=findViewById(R.id.im_setting_back);
        im_setting_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        sdv_photo=findViewById(R.id.sdv_setting_photo);
        sdv_photo.setImageURI(Constants.USERINFO.getHeadportrait());
        bt_exit=findViewById(R.id.bt_exit);
        bt_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sp = activity.getSharedPreferences("account", Activity.MODE_PRIVATE);
                SharedPreferences.Editor editor = sp.edit();
                editor.putString("phone", "1");
                editor.putString("password", "1");
                editor.commit();

                Intent intent=new Intent(activity,Sign_in_Activity.class);
                activity.startActivity(intent);
                finish();
            }
        });
        tv_chang_hear=findViewById(R.id.tv_chang_hear);
        tv_chang_hear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(activity,"该功能正在开发，敬请期待",Toast.LENGTH_LONG).show();
            }
        });
    }
}
