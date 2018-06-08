package com.lejiaokeji.fentuan.control;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.google.gson.Gson;
import com.lejiaokeji.fentuan.databean.Shop_Data;
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
    public void loadData(String url,String data){
        network.connectnet(data,url,handler,1);
    }
    Handler handler=new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int what=msg.what;
            String result=msg.obj.toString();
            Log.d("5555","SIGN返回数据"+result);
            String retCode=JSON.parseObject(result).getString("retCode");
            if (retCode.equals("-1")){
                home_page_listener.connectfail();
            }else if (retCode.equals("-2")){
               home_page_listener.connecttimeout();
            }else if (retCode.equals("0")){

                if (what==1){
                    Shop_Data shop_data = JSON.parseObject(result, new TypeReference<Shop_Data>() {});
                    home_page_listener.loadsuccefful(shop_data);
                }
            }else {
                home_page_listener.loadfail(retCode);
            }
        }
    };
    public interface Home_Page_Listener{
        public void loadsuccefful(Shop_Data shop_data);
        public void loadfail(String t);
        public void fistlogin();
        public void severerr();
        public void connecttimeout();
        public void connectfail();
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