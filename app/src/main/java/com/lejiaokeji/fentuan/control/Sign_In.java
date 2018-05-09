package com.lejiaokeji.fentuan.control;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import com.google.gson.*;
import com.google.gson.Gson;
import com.google.gson.JsonElement;

import com.lejiaokeji.fentuan.utils.Network;


import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * Created by zou on 2018/3/28.
 */

public class Sign_In {
    Network network;
    String username;
    String password;
    Activity activity;
    public Sign_In(String username, String password, Activity activity) {
        network= Network.getnetwork();
        this.activity=activity;
        this.username = username;
        this.password = password;
    }
    public void sign(){
//        Gson gson=new Gson();
//        String requestbody=gson.toJson(signbean);  //将json对象转换为字符
//        network.connectnet(requestbody,"login",StaticValue.url,handler,1);
    }
        Handler handler=new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                int what=msg.what;

                    String result=msg.obj.toString();
                    Log.d("5555","SIGN返回数据"+result);
                    //对返回的数据进行判断是否登陆成功
                    isture(result);
            }
        };
    public interface Signresult{
        public void signsuccessful();
        public void signfail(String t);
        public void fistlogin();
        public void severerr();
    }
    private  Signresult signresult;
    public void setsignlistener( Signresult signresult1){
        this.signresult=signresult1;
    }

    public void isture(String result){

    }

    public void keepdata(){

        SharedPreferences sp = activity.getSharedPreferences("SPuser", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor=sp.edit();
        editor.putString("name",username);
        editor.putString("password",password);
        editor.commit();
    }

}
