package com.lejiaokeji.fentuan.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.lejiaokeji.fentuan.R;
import com.lejiaokeji.fentuan.wxapi.Constants;

import java.util.Timer;
import java.util.TimerTask;

public class Setting_Activity extends BaseActivity {
    SimpleDraweeView sdv_photo;
    ImageView im_setting_back;
    TextView tv_chang_hear;
    Button bt_exit;
    Activity activity;
    LinearLayout ll_clean_cache;
    TextView tv_nickname;
    TextView tv_versioncode;
    ImageButton imb_upnickname;
    TextView tv_phone;
    ImageButton imb_upphone;
    TextView tv_lever;
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
        imb_upnickname=findViewById(R.id.imb_upnickname);
        /**
         * 修改昵称
         */
        imb_upnickname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View view = View.inflate(activity, R.layout.pop_upnickname, null);
               AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                builder.setView(view);
                //点击对话框以外的区域是否让对话框消失
                builder.setCancelable(true);
                 final AlertDialog dialog = builder.create();
                Button button=view.findViewById(R.id.bt_upnickname);
                final EditText editText=view.findViewById(R.id.editText);

                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String nickname=editText.getText().toString();
                        Toast.makeText(activity,nickname,Toast.LENGTH_LONG).show();
                        dialog.dismiss();
                    }
                });
                dialog.show();
                editText.setFocusable(true);
                editText.setFocusableInTouchMode(true);
                editText.requestFocus();
                Timer timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
                    }
                }, 100);

            }
        });
        tv_phone=findViewById(R.id.tv_setting_phone);
        tv_phone.setText(Constants.USERINFO.getPhone());
        imb_upphone=findViewById(R.id.imb_upphone);
        imb_upphone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View view = View.inflate(activity, R.layout.pop_upnickname, null);
                AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                builder.setView(view);
                //点击对话框以外的区域是否让对话框消失
                builder.setCancelable(true);
                final AlertDialog dialog = builder.create();
                Button button=view.findViewById(R.id.bt_upnickname);
                final EditText editText=view.findViewById(R.id.editText);
                editText.setHint("请输入新手机号码");
                editText.setInputType(InputType.TYPE_CLASS_NUMBER);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String nickname=editText.getText().toString();
                        Toast.makeText(activity,nickname,Toast.LENGTH_LONG).show();
                        dialog.dismiss();
                    }
                });
                dialog.show();
                editText.setFocusable(true);
                editText.setFocusableInTouchMode(true);
                editText.requestFocus();
                Timer timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
                    }
                }, 100);
            }
        });
        /**
         * 退出登陆按钮
         */
        bt_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sp = activity.getSharedPreferences("account", Activity.MODE_PRIVATE);
                SharedPreferences.Editor editor = sp.edit();
                editor.putString("phone", "1");
                editor.putString("password", "1");
                editor.commit();

                Intent intent=new Intent(activity,Sign_in_Activity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
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
        ll_clean_cache=findViewById(R.id.rv_clean_cache);
        ll_clean_cache.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(activity,"清除缓存成功",Toast.LENGTH_SHORT).show();
            }
        });
        tv_nickname=findViewById(R.id.tv_setting_nickname);
        tv_nickname.setText(Constants.USERINFO.getNickname());
        tv_versioncode=findViewById(R.id.tv_app_version);
        tv_versioncode.setText(Constants.APP_VERSION);
        tv_lever=findViewById(R.id.tv_lever);
        if (Constants.USERINFO.getLevel()>1){
            tv_lever.setText("合伙人");
        }else {
            tv_lever.setText("普通会员");
        }
    }
}
