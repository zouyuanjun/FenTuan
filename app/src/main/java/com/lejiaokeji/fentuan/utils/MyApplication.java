package com.lejiaokeji.fentuan.utils;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.lejiaokeji.fentuan.R;

import org.acra.ACRA;
import org.acra.BuildConfig;
import org.acra.annotation.AcraCore;
import org.acra.annotation.AcraHttpSender;
import org.acra.annotation.AcraToast;
import org.acra.data.StringFormat;
import org.acra.sender.HttpSender;
//@AcraHttpSender(
//        uri = "http://duanqz.gotunnel.net/acra-secure/_design/acra-storage/_update/report",
//        httpMethod = HttpSender.Method.POST
//)
@AcraCore(reportSenderFactoryClasses = MyACRASenderfactory.class)

@AcraToast(resText = R.string.app_name)
public class MyApplication extends Application {

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);

        // The following line triggers the initialization of ACRA
       // ACRA.init(this);
    }

    @Override
    public void onCreate() {
        Log.d("MyApplication","初始化");
        super.onCreate();

    }
}
