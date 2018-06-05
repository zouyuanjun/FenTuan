package com.lejiaokeji.fentuan.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.lejiaokeji.fentuan.R;
import com.lejiaokeji.fentuan.control.Sign_In;
import com.lejiaokeji.fentuan.utils.Network;

public class WX_Signin_Activity extends AppCompatActivity {
    String token = "";
    String openid = "";
    Network network;
    Sign_In sign_in;
    EditText et_phone;
    EditText et_password;
    EditText et_code;
    EditText et_yaoqingcode;
    Button bt_getcode;
    Button bt_bind;

    String phone;
    boolean cansend = true;
    Activity activity;
    CountDownTimer timer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weixin_signin);
        activity = this;
        Intent intent = getIntent();
        token = intent.getStringExtra("token");
        openid = intent.getStringExtra("openid");
        network = Network.getnetwork();
        sign_in = Sign_In.getInstance();
        et_phone = findViewById(R.id.et_phone);
        et_password = findViewById(R.id.et_password);
        et_code = findViewById(R.id.et_code);
        et_yaoqingcode = findViewById(R.id.et_yaoqingcode);
        bt_getcode = findViewById(R.id.bt_getcode);
        bt_bind = findViewById(R.id.bt_bind);
        init();
        getinputdata();
    }

    public void init() {
        Log.d("555",token+openid);
        sign_in.getwxinfo(token,openid);
        sign_in.setsignlistener(new Sign_In.Signresult() {
            @Override
            public void signsuccessful() {
                finish();
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

            @Override
            public void wxinfo() {

            }
        });
    }

    public void getinputdata() {



        bt_getcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                phone = et_phone.getText().toString();
                sendcode(phone);
            }
        });

        bt_bind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                phone = et_phone.getText().toString();
                String code = et_code.getText().toString();
                String password = et_password.getText().toString();
                String yaoqingcode = et_yaoqingcode.getText().toString();
                sign_in.bindphone(activity,phone,password,code,yaoqingcode);
            }
        });


        /**
         * 倒数计时器
         */
        timer = new CountDownTimer(60 * 1000, 1000) {
            /**
             * 固定间隔被调用,就是每隔countDownInterval会回调一次方法onTick
             * @param millisUntilFinished
             */
            @Override
            public void onTick(long millisUntilFinished) {
                bt_getcode.setText("重新发送（" + millisUntilFinished/1000 + ")秒");
            }

            /**
             * 倒计时完成时被调用
             */
            @Override
            public void onFinish() {
                bt_getcode.setText("重新发送");
                cansend = true;
            }
        };
    }

    /**
     * 将毫秒转化为 分钟：秒 的格式
     *
     * @param millisecond 毫秒
     * @return
     */
    public String formatTime(long millisecond) {
        int minute;//分钟
        int second;//秒数
        minute = (int) ((millisecond / 1000) / 60);
        second = (int) ((millisecond / 1000) % 60);
        if (minute < 10) {
            if (second < 10) {
                return "0" + minute + ":" + "0" + second;
            } else {
                return "0" + minute + ":" + second;
            }
        } else {
            if (second < 10) {
                return minute + ":" + "0" + second;
            } else {
                return minute + ":" + second;
            }
        }
    }
    public void sendcode( String phone) {
        Log.d("5555",phone+phone.length());
        if (phone.length() == 11) {
            if (cansend) {
                timer.start();
                cansend=false;
                sign_in.getcode(phone);
            }

        } else {
            Toast.makeText(activity, "请填写正确的手机号", Toast.LENGTH_SHORT).show();
        }
    }


}