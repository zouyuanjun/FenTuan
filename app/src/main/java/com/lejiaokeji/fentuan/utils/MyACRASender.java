package com.lejiaokeji.fentuan.utils;

import android.content.Context;
import android.util.Log;

import org.acra.ReportField;
import org.acra.data.CrashReportData;
import org.acra.sender.ReportSender;
import org.acra.sender.ReportSenderException;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MyACRASender implements ReportSender {


    @Override
    public void send(Context context, CrashReportData report) throws ReportSenderException {
        // Iterate over the CrashReportData instance and do whatever
        // you need with each pair of ReportField key / String value
        // 自定义需要发送的内容到后台
        Log.i("45454", "发送结果: " + report.toString());
        OkHttpClient client = new OkHttpClient();
        FormBody.Builder formBody = new FormBody.Builder();//创建表单请求体
        formBody.add("APP_VERSION_CODE", report.getString(ReportField.APP_VERSION_CODE));//传递键值对参数
        formBody.add("APP_VERSION_NAME", report.getString(ReportField.APP_VERSION_NAME));
        formBody.add("PACKAGE_NAME", report.getString(ReportField.PACKAGE_NAME));
        formBody.add("FILE_PATH", report.getString(ReportField.FILE_PATH));
        formBody.add("PHONE_MODEL", report.getString(ReportField.PHONE_MODEL));
        formBody.add("PHONE_MODEL", report.getString(ReportField.PHONE_MODEL));
        formBody.add("ANDROID_VERSION", report.getString(ReportField.ANDROID_VERSION));
        formBody.add("BUILD", report.getString(ReportField.BUILD));
        formBody.add("BRAND", report.getString(ReportField.BRAND));
        formBody.add("STACK_TRACE", report.getString(ReportField.STACK_TRACE));
        formBody.add("STACK_TRACE_HASH", report.getString(ReportField.STACK_TRACE_HASH));
        formBody.add("USER_CRASH_DATE", report.getString(ReportField.USER_CRASH_DATE));
        formBody.add("DUMPSYS_MEMINFO", report.getString(ReportField.DUMPSYS_MEMINFO));
        formBody.add("DEVICE_ID", report.getString(ReportField.DEVICE_ID));
        Request request = new Request.Builder()
                .url("http://192.168.2.111:8080/HttpControl//ACRAServlet")
                .post(formBody.build())//传递请求体
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {

            }
        });





    }
}
