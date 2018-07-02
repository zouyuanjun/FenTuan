package com.lejiaokeji.fentuan.activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.allenliu.versionchecklib.callback.OnCancelListener;
import com.allenliu.versionchecklib.core.http.HttpRequestMethod;
import com.allenliu.versionchecklib.v2.AllenVersionChecker;
import com.allenliu.versionchecklib.v2.builder.DownloadBuilder;
import com.allenliu.versionchecklib.v2.builder.UIData;
import com.allenliu.versionchecklib.v2.callback.CustomVersionDialogListener;
import com.allenliu.versionchecklib.v2.callback.RequestVersionListener;
import com.lejiaokeji.fentuan.MainActivity;
import com.lejiaokeji.fentuan.R;
import com.lejiaokeji.fentuan.control.Sign_In;
import com.lejiaokeji.fentuan.wxapi.Constants;

import java.util.Timer;
import java.util.TimerTask;
public class StartActivity extends BaseActivity {
    Activity activity;
    Timer timer;
    TimerTask task;
    PopupWindow window;
    Context context;
    String phone;
    String password;
    private DownloadBuilder builder;
    Sign_In sign_in;
    String isup;


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
        setContentView(R.layout.activity_start);
        Log.d("ddd","开始页面启动成功");
        activity=this;
        context=this;
    //    "1".substring(5);
        sign_in=Sign_In.getInstance();
        //  "1".substring(5);
        //获取保存的登陆密码
        SharedPreferences pref = getSharedPreferences("account",MODE_PRIVATE);
        //获取保存的已登陆历史账号记录
        SharedPreferences pref2 = getSharedPreferences("ACCOUNT",MODE_PRIVATE);
        final String accountlist=pref2.getString("account","{\"ee\":{\"account\":\"ee\"}}");
        phone=pref.getString("phone","1");
        password=pref.getString("password","1");
        cheakversion();
        timer=new Timer();
        task=new TimerTask(){
            public void run(){
                //未登录则跳转到登陆页面
                if (phone.equals("1")){
                    Intent intent=new Intent(activity,Sign_in_Activity.class);
                    startActivity(intent);
                    finish();
                }else {
                    sign_in.sign_in(activity,phone,password);
                }

            }
        };
    }
    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            timer.schedule(task, 1000);

        }
    };
    @Override
    protected void onResume() {
        super.onResume();
        sign_in.setPhone_Sign_Listener(new Sign_In.PhoneSignin() {
            @Override
            public void phonesignin_successful() {
                Intent intent = new Intent(activity, MainActivity.class);
                activity.startActivity(intent);
                finish();
            }
            @Override
            public void phonesignin_fail() {
                Intent intent=new Intent(activity,Sign_in_Activity.class);
                startActivity(intent);
                finish();
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
        });
    }
    @Override
    public boolean dispatchTouchEvent(MotionEvent event){
        if(window!=null&&window.isShowing()){
            return false;
        }
        return super.dispatchTouchEvent(event);
    }
    public static String getVersionCode(Context mContext) {
        String versionCode = "";
        try {
            //获取软件版本号，对应AndroidManifest.xml下android:versionCode
            versionCode = mContext.getPackageManager().getPackageInfo(mContext.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        Log.d("5555","本地版本"+versionCode);
        return versionCode;
    }
    public  void cheakversion(){
      builder= AllenVersionChecker
                .getInstance()
                .requestVersion()
                .setRequestUrl(Constants.URL+"version/getVersion")
                .request(new RequestVersionListener() {
                    @Nullable
                    @Override
                    public UIData onRequestVersionSuccess(String result) {
                        Log.d("55",result);
                        //拿到服务器返回的数据，解析，拿到downloadUrl和一些其他的UI数据
                        String code= JSON.parseObject(result).getString("retCode");
                        if (code.equals("0")){
                            String data=JSON.parseObject(result).getString("data");
                            JSONObject jsonObject=JSON.parseObject(data);
                            String versionnumber=jsonObject.getString("versionnumber");
                            String downloadurl=jsonObject.getString("downloadurl");
                            String instructions=jsonObject.getString("instructions");
                            isup=jsonObject.getString("isupdate");
                            String cruversion=getVersionCode(activity);
                            if (versionnumber.equals(cruversion)){
                                handler.sendEmptyMessageDelayed(0, 500);
                                return null;
                            }else {
                                UIData uiData = UIData
                                        .create()
                                        .setDownloadUrl(downloadurl)
                                        .setTitle("发现新版本："+versionnumber)
                                        .setContent("当前版本："+cruversion+"\n"+instructions);
                                //放一些其他的UI参数，拿到后面自定义界面使用
                                uiData.getVersionBundle().putString("key", "your value");
                                return uiData;
                            }

                        }else {
                            Toast.makeText(activity,"版本校验失败",Toast.LENGTH_LONG).show();
                            return null;
                        }

                    }
                    @Override
                    public void onRequestVersionFailure(String message) {
                        cheakversion();
                        Toast.makeText(activity,"连接网络失败，重试中。。。",Toast.LENGTH_LONG).show();
                    }
                });
       builder.excuteMission(context);
        builder.setShowDownloadFailDialog(true);

      builder.setOnCancelListener(new OnCancelListener() {
          @Override
          public void onCancel() {
              if (isup.equals("1")){
                  //1不是强制更新
                  handler.sendEmptyMessageDelayed(0, 500);
              }else {
                  Toast.makeText(activity,"必须要更新才能正常使用哦",Toast.LENGTH_LONG).show();
                  cheakversion();
              }
          }
      });
    }
}
