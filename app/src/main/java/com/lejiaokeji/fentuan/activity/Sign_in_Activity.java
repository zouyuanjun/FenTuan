package com.lejiaokeji.fentuan.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.lejiaokeji.fentuan.MainActivity;
import com.lejiaokeji.fentuan.R;
import com.lejiaokeji.fentuan.control.Sign_In;
import com.lejiaokeji.fentuan.utils.GetAlerDialog;
import com.lejiaokeji.fentuan.wxapi.Constants;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

public class Sign_in_Activity extends BaseActivity implements View.OnClickListener{

    AutoCompleteTextView autoCompleteTextView;
    EditText editText;
    Button bt_signin;
    TextView tv_phone_sign_up;
    TextView tv_forget_password;
    ImageButton bt_weixin_sign;
    Sign_In sign_in;
    Activity activity;
    ProgressBar progressBar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                    | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
            window.setNavigationBarColor(Color.TRANSPARENT);
        }
        setContentView(R.layout.activity_sign_in);
        activity=this;
        autoCompleteTextView=findViewById(R.id.tv_input_phonenum);
        progressBar=findViewById(R.id.pb_sign);
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

    }
    @Override
    protected void onResume() {
        super.onResume();
        listtener();
    }

    @Override
    public void onClick(View v) {
        switch ( v.getId()){
            //登陆部分
            case R.id.bt_sign:{
                String phone=autoCompleteTextView.getText().toString();
                String password=editText.getText().toString();
                if (phone.length()==11&&!(password.isEmpty())){
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
                    sign_in.sign_in(activity,phone,password);
                    progressBar.setVisibility(View.VISIBLE);
                }else{
                    AlertDialog alertDialog= GetAlerDialog.getdialog(activity,"登陆失败","请填写正确的账号密码再登陆");
                    alertDialog.show();
                }
                break;
            }
            case R.id.tv_phone_sign_up:{
                Intent intent=new Intent(activity,Phone_SignUp_Activity.class);
                activity.startActivity(intent);
                break;
            }
            case R.id.tv_forget_password:{
                Intent intent=new Intent(activity,Find_Password_Activity.class);
                activity.startActivity(intent);

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
                sign_in.weichatsign(activity);
                break;
            }
        }
    }
    private void listtener(){
        sign_in.setPhone_Sign_Listener(new Sign_In.PhoneSignin() {
            @Override
            public void phonesignin_successful() {
                Intent intent = new Intent(activity, MainActivity.class);
                activity.startActivity(intent);
                finish();
            }
            @Override
            public void phonesignin_fail() {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(activity,"账号或密码有误",Toast.LENGTH_LONG).show();
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
    boolean isexit=false;
    @Override
    public void onBackPressed() {
        if (isexit){
            AtyContainer.getInstance().finishAllActivity();
        }else {
            isexit=true;
            Toast.makeText(activity,"再按一次返回退出",Toast.LENGTH_LONG).show();
        }

    }
}
