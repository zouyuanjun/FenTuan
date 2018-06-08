package com.lejiaokeji.fentuan.activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.Window;
import android.view.WindowManager;
import android.widget.PopupWindow;

import com.allenliu.versionchecklib.callback.OnCancelListener;
import com.allenliu.versionchecklib.core.http.HttpRequestMethod;
import com.allenliu.versionchecklib.v2.AllenVersionChecker;
import com.allenliu.versionchecklib.v2.builder.DownloadBuilder;
import com.allenliu.versionchecklib.v2.builder.UIData;
import com.allenliu.versionchecklib.v2.callback.CustomVersionDialogListener;
import com.allenliu.versionchecklib.v2.callback.RequestVersionListener;
import com.lejiaokeji.fentuan.R;
import java.util.Timer;
import java.util.TimerTask;
public class StartActivity extends AppCompatActivity {
    Activity activity;
    Timer timer;
    TimerTask task;
    PopupWindow window;
    Context context;
    private DownloadBuilder builder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);// 隐藏标题
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);// 设置全屏
        setContentView(R.layout.activity_start);
        Log.d("ddd","开始页面启动成功");
        activity=this;
        context=this;
        //  "1".substring(5);
        //获取保存的登陆密码
        SharedPreferences pref = getSharedPreferences("SPuser",MODE_PRIVATE);
        //获取保存的已登陆历史账号记录
        SharedPreferences pref2 = getSharedPreferences("ACCOUNT",MODE_PRIVATE);
        final String accountlist=pref2.getString("account","{\"ee\":{\"account\":\"ee\"}}");
        final String name=pref.getString("name","1");
        final String password=pref.getString("password","1");
        cheakversion();
        timer=new Timer();
        task=new TimerTask(){
            public void run(){

                Intent intent=new Intent(activity,Sign_in_Activity.class);
                startActivity(intent);
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
    public boolean dispatchTouchEvent(MotionEvent event){
        if(window!=null&&window.isShowing()){
            return false;
        }
        return super.dispatchTouchEvent(event);
    }
    public static int getVersionCode(Context mContext) {
        int versionCode = 0;
        try {
            //获取软件版本号，对应AndroidManifest.xml下android:versionCode
            versionCode = mContext.getPackageManager().getPackageInfo(mContext.getPackageName(), 0).versionCode;
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
                .setRequestUrl("https://www.baidu.com/")
                .request(new RequestVersionListener() {
                    @Nullable
                    @Override
                    public UIData onRequestVersionSuccess(String result) {
                        //拿到服务器返回的数据，解析，拿到downloadUrl和一些其他的UI数据
                        UIData uiData = UIData
                                .create()
                                .setDownloadUrl("http://p83ixm9mm.bkt.clouddn.com/%E6%83%A0%E7%8E%A9%E5%AD%A6%E9%99%A21.2.5.apk")
                                .setTitle("有新的版本更新")
                                .setContent("修复已知bug");
                        //放一些其他的UI参数，拿到后面自定义界面使用
                        uiData.getVersionBundle().putString("key", "your value");
                        return uiData;
                    }
                    @Override
                    public void onRequestVersionFailure(String message) {

                    }
                });
       builder.excuteMission(context);
      builder.setOnCancelListener(new OnCancelListener() {
          @Override
          public void onCancel() {
              handler.sendEmptyMessageDelayed(0, 500);
          }
      });
    }

}
