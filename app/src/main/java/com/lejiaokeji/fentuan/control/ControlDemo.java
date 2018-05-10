package com.lejiaokeji.fentuan.control;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import com.lejiaokeji.fentuan.utils.Network;

public class ControlDemo {
    Network network;
    String username;
    String password;
    Activity activity;
    public void sign_in(String username, String password) {
        network= Network.getnetwork();
        this.username = username;
        this.password = password;
    }
    private static ControlDemo instance=new ControlDemo();
    public static ControlDemo getInstance(){
        return instance;
    }
    private ControlDemo() {
        network= Network.getnetwork();
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

}

