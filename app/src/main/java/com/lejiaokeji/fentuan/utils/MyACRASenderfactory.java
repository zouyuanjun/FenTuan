package com.lejiaokeji.fentuan.utils;

import android.content.Context;
import android.support.annotation.NonNull;

import org.acra.config.CoreConfiguration;
import org.acra.sender.ReportSender;
import org.acra.sender.ReportSenderFactory;

public class MyACRASenderfactory implements ReportSenderFactory {

    // NB requires a no arg constructor.

    @NonNull
    @Override
    public ReportSender create(@NonNull Context context, @NonNull CoreConfiguration config) {
        return new MyACRASender();
    }

    @Override
    public boolean enabled(@NonNull CoreConfiguration config) {
        return false;
    }
}