package com.lejiaokeji.fentuan.activity;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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

import com.bumptech.glide.util.Util;
import com.lejiaokeji.fentuan.R;
import com.lejiaokeji.fentuan.utils.BitmapUtil;
import com.lejiaokeji.fentuan.wxapi.Constants;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.modelmsg.WXWebpageObject;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

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
                invite();
            }
        });
    }

    public void invite(){
        WXWebpageObject webpage=new WXWebpageObject();
        webpage.webpageUrl="https://www.baidu.com";

        WXMediaMessage msg=new WXMediaMessage(webpage);
        msg.title="这是一个APP";
        msg.description="APP的描述在这里，点击可以打开一个链接";
        Bitmap thumb= BitmapFactory.decodeResource(getResources(),R.drawable.logo);
        msg.thumbData= BitmapUtil.bmpToByteArray(thumb,true);
        SendMessageToWX.Req req=new SendMessageToWX.Req();

        req.transaction="sdfsdfsdf";
        req.message=msg;
        req.scene=SendMessageToWX.Req.WXSceneSession;
        Constants.api = WXAPIFactory.createWXAPI(activity, Constants.APP_ID, false);
        Constants.api.registerApp(Constants.APP_ID);
        Constants.api.sendReq(req);
    }

}
