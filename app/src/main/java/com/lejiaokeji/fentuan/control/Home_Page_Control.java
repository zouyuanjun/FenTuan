package com.lejiaokeji.fentuan.control;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.TypeReference;
import com.google.gson.Gson;
import com.lejiaokeji.fentuan.databean.Shop_Data;
import com.lejiaokeji.fentuan.utils.Network;
import com.lejiaokeji.fentuan.wxapi.Constants;

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

    Handler handler=new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int what=msg.what;
            String result=msg.obj.toString();
            Log.d("5555","SIGN返回数据"+result);
            String retCode="999";
            try {
                retCode=JSON.parseObject(result).getString("retCode");
            }catch (JSONException e){
                home_page_listener.loadfail(retCode);
            }

            if (retCode.equals("-1")){
                home_page_listener.connectfail();
            }else if (retCode.equals("-2")){
               home_page_listener.connecttimeout();
            }else if (retCode.equals("0")){
                if (what==1){
                    Shop_Data shop_data = JSON.parseObject(result, new TypeReference<Shop_Data>() {});
                    home_page_listener.loadsuccefful(shop_data);
                }else  if (what==2){
                    Shop_Data shop_data = JSON.parseObject(result, new TypeReference<Shop_Data>() {});
                    home_page_listener.searchresult(shop_data);
                }
            }else {
                home_page_listener.loadfail(retCode);
            }
        }
    };
    public interface Home_Page_Listener{
        public void loadsuccefful(Shop_Data shop_data);
        public void loadfail(String t);
        public void searchresult(Shop_Data shop_data);
        public void severerr();
        public void connecttimeout();
        public void connectfail();
    }
    private Home_Page_Listener home_page_listener;
    public void setlistener( Home_Page_Listener home_page_listener){
        this.home_page_listener=home_page_listener;
    }

    public void loadData(String url,String data){
        network.connectnet(data,url,handler,1);
    }
    public void searchgoods(String keyword ){

        String url="";
        String data;
        if (Constants.SELECT_JD){
            data ="{\"goodsName\":\"%name\"}";
            data=data.replace("%name",keyword);
            url=Constants.URL+"shopList/selectGoods";
        }else {
            url=Constants.URL+"goodsSearch/selectGoodsName";
            data="{\"goodsName\":\"%name\",\"pddPid\":\"%pddPid\"}";
            data=data.replace("%name",keyword);
            data=data.replace("%pddPid",Constants.USERINFO.getPddpid());
        }
        network.connectnet(data,url,handler,2);
    }
}