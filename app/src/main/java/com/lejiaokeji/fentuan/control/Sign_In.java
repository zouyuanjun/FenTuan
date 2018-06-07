package com.lejiaokeji.fentuan.control;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import com.google.gson.*;
import com.google.gson.Gson;
import com.google.gson.JsonElement;

import com.lejiaokeji.fentuan.MainActivity;
import com.lejiaokeji.fentuan.databean.WX_info_Bean;
import com.lejiaokeji.fentuan.utils.Network;
import com.lejiaokeji.fentuan.wxapi.Constants;
import com.lejiaokeji.fentuan.wxapi.WXEntryActivity;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;


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
     WX_info_Bean wx_info_bean=new WX_info_Bean();;
    public void sign_in(String username, String password) {
        network= Network.getnetwork();
        this.username = username;
        this.password = password;
    }
    private static Sign_In instance=new Sign_In();
    public static Sign_In getInstance(){

        return instance;
    }
    private Sign_In() {
        network= Network.getnetwork();
    }


    public void sign(){
//        Gson gson=new Gson();
//        String requestbody=gson.toJson(signbean);  //将json对象转换为字符
//        network.connectnet(requestbody,"login",StaticValue.url,handler,1);
    }
    String openid="";
    String nickname="";
    String sex="";
    String province="";
    String city="";
    String headimgurl="";
    Handler handler=new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                int what=msg.what;
                String result=msg.obj.toString();
                Log.d("5555","SIGN返回数据"+result);
                if (what==1){
                    JsonElement je = new JsonParser().parse(result);
                     openid = je.getAsJsonObject().get("openid").getAsString();
                     nickname = je.getAsJsonObject().get("nickname").getAsString();
                     sex = je.getAsJsonObject().get("sex").getAsString();
                     province = je.getAsJsonObject().get("province").getAsString();
                     city = je.getAsJsonObject().get("city").getAsString();
                     headimgurl=je.getAsJsonObject().get("headimgurl").getAsString();

                }if (what==3){
                    //登陆结果
                    JsonElement je = new JsonParser().parse(result);
                    String retCode = je.getAsJsonObject().get("retCode").getAsString();
                    if (retCode.equals("0")){
                        Intent intent=new Intent(activity, MainActivity.class);
                        intent.putExtra("phone",wx_info_bean.getPhone());
                        activity.startActivity(intent);
                        signresult.signsuccessful();
                    }
                }if (what==4){
                    //修改密码结果
                    JsonElement je = new JsonParser().parse(result);
                    String retCode = je.getAsJsonObject().get("retCode").getAsString();
                    if (retCode.equals("0")){

                        signresult.uppasswordsuccessful();
                    }
                }
                    isture(result);
            }
        };
    public interface Signresult{
        public void signsuccessful();
        public void signfail(String t);
        public void fistlogin();
        public void uppasswordsuccessful();
        public void wxinfo();
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
    //微信登陆部分
    public void weichatsign(Context context){
        Constants.api = WXAPIFactory.createWXAPI(context, Constants.APP_ID, false);
        Constants.api.registerApp(Constants.APP_ID);
        final SendAuth.Req req = new SendAuth.Req();
        req.scope = "snsapi_userinfo";
        req.state = "fentuanyizhuang";
        Constants.api.sendReq(req);
    }
    //获取微信用户基本信息
    public void getwxinfo(String token,String openid){
        String url="https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID";
        url=url.replace("ACCESS_TOKEN",token);
        url=url.replace("OPENID",openid);
        network.connectnet("",url,handler,1);
    }
    //获取注册验证码
    public void getcode(String phone){
        String url=Constants.URL+"/user/sendCode";
        String data="{\"phone\":\"%phone\"}";
        data=data.replace("%phone",phone);
        network.connectnet(data,url,handler,2);
    }
    //绑定手机号
    public void bindphone(Activity activity,String phone ,String password,String code,String yaoqingcode){
        this.activity=activity;
        wx_info_bean.setAddress(province+"·"+city);
        wx_info_bean.setNickname(nickname);
        wx_info_bean.setSex(sex);
        wx_info_bean.setWeixinid(openid);
        wx_info_bean.setHeadportrait(headimgurl);
        wx_info_bean.setPassword(password);
        wx_info_bean.setCode(code);
        wx_info_bean.setInvitecode(yaoqingcode);
        wx_info_bean.setPhone(phone);
        String data=new Gson().toJson(wx_info_bean);
        Log.d("555",data);
        String url=Constants.URL+"/user/perfectInfo";
        network.connectnet(data,url,handler,3);
    }
    //找回密码
    public void findpassword(String phone,String code,String password){
        String data="{\"phone\":\"%phone\",\"code\":\"%code\",\"password\":\"%password\"}";
        data=data.replace("%phone",phone);
        data=data.replace("%code",code);
        data=data.replace("%password",password);
        network.connectnet(data,Constants.URL+"/findPassWord ",handler,4);
    }
}
