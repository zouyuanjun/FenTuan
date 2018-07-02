package com.lejiaokeji.fentuan.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.lejiaokeji.fentuan.MainActivity;
import com.lejiaokeji.fentuan.R;
import com.lejiaokeji.fentuan.control.Sign_In;
import com.lejiaokeji.fentuan.utils.Network;

public class WX_Signin_Activity extends BaseActivity {
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
    RadioButton radioButton;
    boolean isaccpet=false;
    Toolbar toolbar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.parseColor("#ff475d"));
        }
        setContentView(R.layout.activity_weixin_signin);
        toolbar=findViewById(R.id.toolbar_bindphone);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setTitle("");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        activity = this;
        Intent intent = getIntent();
        token = intent.getStringExtra("token");
        openid = intent.getStringExtra("openid");
        network = Network.getnetwork();
        sign_in = Sign_In.getInstance();
        et_phone = findViewById(R.id.et_phone);
        et_phone.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus){
                    et_phone.setBackground(ContextCompat.getDrawable(activity,R.drawable.shape_corner444miao143));
                }else {
                    et_phone.setBackground(ContextCompat.getDrawable(activity,R.drawable.shape_corner444));
                }
            }
        });
        et_password = findViewById(R.id.et_password);
        et_password.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus){
                    et_password.setBackground(ContextCompat.getDrawable(activity,R.drawable.shape_corner444miao143));
                }else {
                    et_password.setBackground(ContextCompat.getDrawable(activity,R.drawable.shape_corner444));
                }
            }
        });
        et_code = findViewById(R.id.et_code);
        et_code.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus){
                    et_code.setBackground(ContextCompat.getDrawable(activity,R.drawable.shape_corner444miao143));
                }else {
                    et_code.setBackground(ContextCompat.getDrawable(activity,R.drawable.shape_corner444));
                }
            }
        });
        et_yaoqingcode = findViewById(R.id.et_yaoqingcode);
        et_yaoqingcode.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus){
                    et_yaoqingcode.setBackground(ContextCompat.getDrawable(activity,R.drawable.shape_corner444miao143));
                }else {
                    et_yaoqingcode.setBackground(ContextCompat.getDrawable(activity,R.drawable.shape_corner444));
                }
            }
        });
        bt_getcode = findViewById(R.id.bt_getcode);
        bt_bind = findViewById(R.id.bt_bind);
        radioButton =findViewById(R.id.radioButton);

        getinputdata();
    }

    @Override
    protected void onResume() {
        super.onResume();
        init();
    }

    public void init() {
        Log.d("555",token+openid);
        if (null!=token&&null!=openid){
            sign_in.getwxinfo(token,openid);
        }
        sign_in.setsignlistener(new Sign_In.Signresult() {
            @Override
            public void signsuccessful() {
                Intent intent = new Intent(activity, MainActivity.class);
                activity.startActivity(intent);
                finish();
            }

            @Override
            public void yaoqing_err(String t) {
                Toast.makeText(activity,"邀请码好像错了哦",Toast.LENGTH_LONG).show();
            }

            @Override
            public void code_err() {
                Toast.makeText(activity,"验证码好像错了哦",Toast.LENGTH_LONG).show();
            }

            @Override
            public void uppasswordsuccessful() {
            }

            @Override
            public void othererr(String errcode) {
                Toast.makeText(activity,"抱歉，发生看意料之外的错误，错误码："+errcode,Toast.LENGTH_LONG).show();
            }

            @Override
            public void get_code_err(String code) {

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

                if (isaccpet){
                    phone = et_phone.getText().toString();
                    String code = et_code.getText().toString();
                    String password = et_password.getText().toString();
                    String yaoqingcode = et_yaoqingcode.getText().toString();
                    if (!phone.isEmpty()&&!code.isEmpty()&&!password.isEmpty()){
                        sign_in.bindphone(activity,phone,password,code,yaoqingcode);
                    }else {
                        Toast.makeText(activity,"请填写所有信息",Toast.LENGTH_LONG).show();
                    }

                }else {
                    Toast.makeText(activity,"必须接受粉团用户协议",Toast.LENGTH_LONG).show();
                }

            }
        });
        radioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 isaccpet=true;
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
    //请求验证码
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
