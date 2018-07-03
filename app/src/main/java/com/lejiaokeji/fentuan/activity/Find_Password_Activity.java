package com.lejiaokeji.fentuan.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.lejiaokeji.fentuan.R;
import com.lejiaokeji.fentuan.control.Sign_In;
import com.lejiaokeji.fentuan.view.helpview.GetAlerDialog;

public class Find_Password_Activity extends AppCompatActivity {
    EditText et_phone;
    EditText et_password;
    EditText et_code;
    Button bt_uppassword;
    Button bt_getcode;
    Toolbar mToolbar;
    String phone;
    String code;
    String password;
    boolean cansend = true;
    Activity activity;
    CountDownTimer timer;
    Sign_In sign_in;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.parseColor("#ff475d"));
        }
        setContentView(R.layout.activity_forgetpassword);
        activity = this;
        mToolbar = findViewById(R.id.toolbar_findpassword);
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        sign_in = Sign_In.getInstance();
        et_phone = findViewById(R.id.et_phone);
        et_phone.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    et_phone.setBackground(ContextCompat.getDrawable(activity, R.drawable.shape_corner444miao143));
                } else {
                    et_phone.setBackground(ContextCompat.getDrawable(activity, R.drawable.shape_corner444));
                }
            }
        });
        et_password = findViewById(R.id.et_password);
        et_password.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    et_password.setBackground(ContextCompat.getDrawable(activity, R.drawable.shape_corner444miao143));
                } else {
                    et_password.setBackground(ContextCompat.getDrawable(activity, R.drawable.shape_corner444));
                }
            }
        });
        et_code = findViewById(R.id.et_code);
        et_code.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    et_code.setBackground(ContextCompat.getDrawable(activity, R.drawable.shape_corner444miao143));
                } else {
                    et_code.setBackground(ContextCompat.getDrawable(activity, R.drawable.shape_corner444));
                }
            }
        });
        bt_uppassword = findViewById(R.id.bt_updatpassword);
        bt_getcode = findViewById(R.id.bt_getcode);
        bt_getcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                phone = et_phone.getText().toString();
                sendcode(phone);
            }
        });

        bt_uppassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                code = et_code.getText().toString();
                password = et_password.getText().toString();
                sign_in.findpassword(phone, code, password);
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
                bt_getcode.setText("重新发送(" + millisUntilFinished / 1000 + ")秒");
            }

            /**
             * 倒计时完成时被调用
             */
            @Override
            public void onFinish() {
                bt_getcode.setText("重新发送");
                bt_getcode.setBackground(ContextCompat.getDrawable(activity, R.drawable.signup_bt_verif_def));
                cansend = true;
            }
        };
    }

    @Override
    protected void onResume() {
        super.onResume();
        sign_in.setUpdataPassWordListener(new Sign_In.UpdataPassWord() {
            @Override
            public void fail() {
                Toast.makeText(activity,"验证码错误",Toast.LENGTH_LONG).show();
            }

            @Override
            public void successful() {
                AlertDialog alertDialog = GetAlerDialog.getdialog(activity, "修改密码", "密码重置成功，点击确定返回");
                alertDialog.show();
                alertDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        activity.finish();
                    }
                });
            }
        });
        sign_in.setNetWorkListener(new Sign_In.NetWorkerr() {
            @Override
            public void timeout() {
                Toast.makeText(activity,"连接超时，请检查网络",Toast.LENGTH_LONG).show();
            }
            @Override
            public void connectfail() {
                Toast.makeText(activity,"与服务器连接失败，请检查网络",Toast.LENGTH_LONG).show();
            }

            @Override
            public void severerr() {
                Toast.makeText(activity,"服务器内部错误",Toast.LENGTH_LONG).show();
            }
        });
    }

    //请求验证码
    public void sendcode(String phone) {
        Log.d("5555", phone + phone.length());
        if (phone.length() == 11) {
            if (cansend) {
                timer.start();
                cansend = false;
                bt_getcode.setBackground(ContextCompat.getDrawable(activity, R.drawable.signup_bt_verif_dis));
                sign_in.findpassword_code(phone);
            }

        } else {
            Toast.makeText(activity, "请填写正确的手机号", Toast.LENGTH_SHORT).show();
        }
    }

}
