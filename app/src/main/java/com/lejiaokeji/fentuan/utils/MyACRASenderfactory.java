package com.lejiaokeji.fentuan.utils;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import org.acra.config.CoreConfiguration;
import org.acra.sender.ReportSender;
import org.acra.sender.ReportSenderFactory;


public class MyACRASenderfactory implements ReportSenderFactory {

    // NB requires a no arg constructor.
//
    @NonNull
    @Override
    public ReportSender create(@NonNull Context context, @NonNull CoreConfiguration config) {
        Log.d("MyACRASenderfactory","到这里了");
        return new MyACRASender();
    }

    @Override
    public boolean enabled(@NonNull CoreConfiguration config) {
        return false;
    }
//
//    @NonNull
//    @Override
//    public ReportSender create(@NonNull Context context, @NonNull ACRAConfiguration config) {
//        Log.d("MyACRASenderfactory","到这里了");
//       return new MyACRASender();
//    }
//    public MyACRASenderfactory(){
//
//    }
}