package com.lejiaokeji.fentuan.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.lejiaokeji.fentuan.R;
import com.lejiaokeji.fentuan.control.Sign_In;
import com.lejiaokeji.fentuan.utils.GetAlerDialog;
import com.lejiaokeji.fentuan.wxapi.Constants;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

public class Sign_in_Activity extends AppCompatActivity implements View.OnClickListener{

    AutoCompleteTextView autoCompleteTextView;
    EditText editText;
    Button bt_signin;
    TextView tv_phone_sign_up;
    TextView tv_forget_password;
    ImageButton bt_weixin_sign;
    Sign_In sign_in;
    String strphonenum;
    String strpassword;
    Activity activity;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        activity=this;
        autoCompleteTextView=findViewById(R.id.tv_input_phonenum);
        editText=findViewById(R.id.tv_input_password);
        bt_signin=findViewById(R.id.bt_sign);
        tv_phone_sign_up=findViewById(R.id.tv_phone_sign_up);
        tv_forget_password=findViewById(R.id.tv_forget_password);
        bt_weixin_sign =findViewById(R.id.imb_weixin_sign);
        sign_in=Sign_In.getInstance();
        bt_signin.setOnClickListener(this);
        bt_weixin_sign.setOnClickListener(this);
        tv_forget_password.setOnClickListener(this);
        tv_phone_sign_up.setOnClickListener(this);
//sadfasdf;
    }

    @Override
    public void onClick(View v) {
        switch ( v.getId()){
            case R.id.bt_sign:{
                String phone=autoCompleteTextView.getText().toString();
                String password=editText.getText().toString();
                if (!(phone.isEmpty()||password.isEmpty())){
                    sign_in.sign_in(phone,password);
                }else{
                    android.app.AlertDialog alertDialog= GetAlerDialog.getdialog(activity,"提交失败","请填写账号密码再登陆");
                }

                break;
            }
            case R.id.tv_phone_sign_up:{

                break;
            }
            case R.id.tv_forget_password:{
                break;
            }
            //微信登陆
            case R.id.imb_weixin_sign:{
                Constants.api= WXAPIFactory.createWXAPI(this, Constants.APP_ID, false);
                // 将该app注册到微信
                Constants.api.registerApp(Constants.APP_ID);
                if (!Constants.api.isWXAppInstalled()) {
                    Toast.makeText(this,"您还未安装微信客户端",Toast.LENGTH_SHORT).show();
                    return;
                }

                Log.d("555","微信注册");
                sign_in.weichatsign(activity);




                break;
            }
        }
    }
}
