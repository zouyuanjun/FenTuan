package com.lejiaokeji.fentuan.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.lejiaokeji.fentuan.R;

public class Shouyi_Activity  extends AppCompatActivity implements View.OnClickListener{
    ImageView im_shouyi_back;
    Button bt_tixian;
    Activity activity;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shouyi);
        activity=this;
        im_shouyi_back=findViewById(R.id.im_shouyi_back);
        bt_tixian=findViewById(R.id.bt_tixian);

        bt_tixian.setOnClickListener(this);
        im_shouyi_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.im_shouyi_back:{
                finish();
                break;
            }
            case R.id.bt_tixian:{
                Intent intent=new Intent(activity,Activity_tixian.class);
                activity.startActivity(intent);
                break;
            }
        }
    }
}
