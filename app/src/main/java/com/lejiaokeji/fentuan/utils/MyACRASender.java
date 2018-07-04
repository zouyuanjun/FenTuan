package com.lejiaokeji.fentuan.utils;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.lejiaokeji.fentuan.databean.ACRA_Bean;

import org.acra.ReportField;
import org.acra.data.CrashReportData;
import org.acra.sender.ReportSender;
import org.acra.sender.ReportSenderException;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.BufferedSink;

public class MyACRASender implements ReportSender {


    @Override
    public void send(Context context, CrashReportData report) throws ReportSenderException {
        // 自定义需要发送的内容到后台

        ACRA_Bean acra_bean=new ACRA_Bean();
        acra_bean.setANDROID_VERSION(report.getString(ReportField.ANDROID_VERSION));
        acra_bean.setAPP_VERSION_CODE( report.getString(ReportField.APP_VERSION_CODE));
        acra_bean.setAPP_VERSION_NAME( report.getString(ReportField.APP_VERSION_NAME));
        acra_bean.setPACKAGE_NAME(report.getString(ReportField.PACKAGE_NAME));
        acra_bean.setPHONE_MODEL(report.getString(ReportField.PHONE_MODEL));
        acra_bean.setSTACK_TRACE(report.getString(ReportField.STACK_TRACE));
        acra_bean.setUSER_CRASH_DATE(report.getString(ReportField.USER_CRASH_DATE));
        acra_bean.setUSER_COMMENT("");
        acra_bean.setUSER_APP_START_DATE(report.getString(ReportField.USER_APP_START_DATE));

        OkHttpClient client = new OkHttpClient();
        String json=new Gson().toJson(acra_bean);
        RequestBody requestBody = FormBody.create(MediaType.parse("application/json; charset=utf-8"), json);


        Request request = new Request.Builder()
                .url("http://47.98.155.149:8080/huiwanxueyuan/ACRAServlet")
                .post(requestBody)//传递请求体
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
