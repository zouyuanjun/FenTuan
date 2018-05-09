package com.lejiaokeji.fentuan.utils;

import android.os.Message;
import android.util.Log;
import java.io.IOException;
import okhttp3.Call;
import okhttp3.Callback;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


/**
 * Created by zou on 2018/3/28.
 */

public class Network {
    private static Network instance=new Network();
    public static Network getnetwork(){
        return instance;
    }
    private Network() {
    }
    public void connectnet(String date ,String header, String url,final android.os.Handler handler, final int i){
        OkHttpClient client = new OkHttpClient();//创建OkHttpClient对象。
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");//数据类型为json格式，
        String jsonStr = date;//json数据.
        Log.d("5555","发送请求头"+header+"请求体"+jsonStr);
        RequestBody body = RequestBody.create(JSON, jsonStr);
        Request request = new Request.Builder()
                .addHeader("header",header)
                .url(url)
                .post(body)
                .build();
        client.newCall(request).enqueue(new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {

            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
               Message message=new Message();
               String s=response.body().string();
               message.what=i;
               message.obj=s;
               handler.sendMessage(message);
            }
        });
    }

}
