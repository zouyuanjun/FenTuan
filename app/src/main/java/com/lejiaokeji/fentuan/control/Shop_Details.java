package com.lejiaokeji.fentuan.control;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import com.lejiaokeji.fentuan.utils.Network;
import com.lejiaokeji.fentuan.wxapi.Constants;

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
        String data="{\"goodsId\":\"%id\"}";
        data=data.replace("%id",id);
        String url= Constants.URL+"/goodsInfo/getShopinfo";
        network.connectnet(data,url,handler,1);
    }

}
