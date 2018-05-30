package com.lejiaokeji.fentuan.control;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import com.lejiaokeji.fentuan.utils.Network;

public class Home_Page_Control {
    Network network;
    String username;
    String password;
    Activity activity;

    private static Home_Page_Control instance=new Home_Page_Control();
    public static Home_Page_Control getInstance(){
        return instance;
    }
    private Home_Page_Control() {
        network= Network.getnetwork();
    }
    public void loadData(){
//        Gson gson=new Gson();
//        String requestbody=gson.toJson(signbean);  //将json对象转换为字符
   //     network.connectnet(requestbody,"login",StaticValue.url,handler,1);
    }
    Handler handler=new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int what=msg.what;

            String result=msg.obj.toString();
            Log.d("5555","SIGN返回数据"+result);
            if (what==1){
                home_page_listener.loadsuccefful();
            }
        }
    };
    public interface Home_Page_Listener{
        public void loadsuccefful();
        public void signfail(String t);
        public void fistlogin();
        public void severerr();
    }
    private Home_Page_Listener home_page_listener;
    public void setlistener( Home_Page_Listener home_page_listener){
        this.home_page_listener=home_page_listener;
    }
    public void keepdata(){

        SharedPreferences sp = activity.getSharedPreferences("SPuser", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor=sp.edit();
        editor.putString("name",username);
        editor.putString("password",password);
        editor.commit();
    }

}