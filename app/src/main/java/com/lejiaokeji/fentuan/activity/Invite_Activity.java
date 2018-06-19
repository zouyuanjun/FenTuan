package com.lejiaokeji.fentuan.activity;

import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.lejiaokeji.fentuan.R;

public class Invite_Activity extends AppCompatActivity{
    TextView friendnum;
    ImageView im_back;
    ConstraintLayout constraintLayout;
    Activity activity;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.parseColor("#ff286e"));
        }
        setContentView(R.layout.activity_invite);
        activity=this;
        im_back=findViewById(R.id.im_invite_back);
        im_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        constraintLayout=findViewById(R.id.constraintLayout3);
        constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(activity,"该功能正在开发，敬请期待",Toast.LENGTH_LONG).show();
            }
        });
    }
}
