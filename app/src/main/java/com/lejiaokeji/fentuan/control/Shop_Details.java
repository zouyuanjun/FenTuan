package com.lejiaokeji.fentuan.control;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import com.lejiaokeji.fentuan.utils.Network;
import com.lejiaokeji.fentuan.wxapi.Constants;

import java.util.Date;

public class Shop_Details {
    Network network;
    String username;
    String password;
    Activity activity;

    private static Shop_Details instance=new Shop_Details();
    public static Shop_Details getInstance(){
        return instance;
    }
    private Shop_Details() {
        network= Network.getnetwork();
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
    private Sign_In.Signresult signresult;
    public void setsignlistener( Sign_In.Signresult signresult1){
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
    public void getdata(String id){
        if (Constants.SELECT_JD){
            String data="{\"goodsId\":\"%id\"}";
            data=data.replace("%id",id);
            String url= Constants.URL+"/goodsInfo/getShopinfo";
            network.connectnet(data,url,handler,1);
        }else {
            String data="{\"goodsId\":\"%id\"}";
            data=data.replace("%id",id);
            String url= Constants.URL+"goodsDetail/getGoodsDetail";
            network.connectnet(data,url,handler,1);
        }

    }
    public void getUnionData(String pid,String shopid){
        if (Constants.SELECT_JD){
            String data="{\"goodsId\":\"%goodsid\",\"jdPid\":\"%jdPid\"}";
            data=data.replace("%goodsid",shopid);
            data=data.replace("%jdPid",pid);
            String url=Constants.URL+"union/getUnionData";
            network.connectnet(data,url,handler,2);
        }else {
            String data="{\"goodsId\":\"%goodsid\",\"pddPid\":\"%pddPid\"}";
            data=data.replace("%goodsid",shopid);
            data=data.replace("%pddPid",pid);
            String url=Constants.URL+"generate/getGenerate";
            network.connectnet(data,url,handler,3);
        }


    }
}
