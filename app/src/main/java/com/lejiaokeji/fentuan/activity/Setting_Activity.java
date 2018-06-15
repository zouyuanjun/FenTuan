package com.lejiaokeji.fentuan.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.lejiaokeji.fentuan.R;
import com.lejiaokeji.fentuan.wxapi.Constants;

public class Setting_Activity extends AppCompatActivity {
    SimpleDraweeView sdv_photo;
    ImageView im_setting_back;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        im_setting_back=findViewById(R.id.im_setting_back);
        im_setting_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        sdv_photo=findViewById(R.id.sdv_setting_photo);
        sdv_photo.setImageURI(Constants.USERINFO.getHeadportrait());
    }
}
