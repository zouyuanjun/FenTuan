package com.lejiaokeji.fentuan.control;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.google.gson.*;
import com.google.gson.Gson;
import com.google.gson.JsonElement;

import com.lejiaokeji.fentuan.databean.Phone_Sign_Bean;
import com.lejiaokeji.fentuan.databean.Userinfo_Bean;
import com.lejiaokeji.fentuan.databean.WX_info_Bean;
import com.lejiaokeji.fentuan.utils.Network;
import com.lejiaokeji.fentuan.wxapi.Constants;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

/**
 * Created by zou on 2018/3/28.
 */

public class Sign_In {

    Network network;
    String phone;
    String password;
    Activity activity;
    WX_info_Bean wx_info_bean = new WX_info_Bean();
    ;



    private static Sign_In instance = new Sign_In();

    public static Sign_In getInstance() {
        return instance;
    }

    private Sign_In() {
        network = Network.getnetwork();
    }


    String openid = "";
    String nickname = "";
    String sex = "";
    String province = "";
    String city = "";
    String headimgurl = "";
    Handler handler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int what = msg.what;
            String result = msg.obj.toString();
            Log.d("5555", "SIGN返回数据" + result+what);
            if (what == 1) {
                JsonElement je = new JsonParser().parse(result);
                openid = je.getAsJsonObject().get("openid").getAsString();
                nickname = je.getAsJsonObject().get("nickname").getAsString();
                sex = je.getAsJsonObject().get("sex").getAsString();
                province = je.getAsJsonObject().get("province").getAsString();
                city = je.getAsJsonObject().get("city").getAsString();
                headimgurl = je.getAsJsonObject().get("headimgurl").getAsString();

            }else{
                String retCode ="500";
                try {
                    JsonElement je = new JsonParser().parse(result);
                    retCode = je.getAsJsonObject().get("retCode").getAsString();
                }catch (JsonSyntaxException e){
                 netWorkerr.severerr();
                }
                if (retCode.equals("-2")){
                    netWorkerr.timeout();
                    return;
                }else if (retCode.equals("-1")){
                    netWorkerr.connectfail();
                    return;
                }
                if (what==2){
                    //请求注册验证码接口
                    if (retCode.equals("5")){
                        signresult.get_code_err(retCode);
                    }
                }else if (what == 3) {
                    //微信绑定绑定手机号的登陆结果
                    if (retCode.equals("0")) {
                        String data = JSON.parseObject(result).getString("data");;
                        Constants.USERINFO = JSON.parseObject(data, new TypeReference<Userinfo_Bean>() {});
                        signresult.signsuccessful();
                    } else {
                        signresult.yaoqing_err(retCode);
                    }
                }else if (what == 4) {
                    //修改密码结果
                    if (retCode.equals("0")) {
                        updataPassWord.successful();
                    }else if (retCode.equals("16")){
                       updataPassWord.fail();
                    }
                }else if (what==5){
                    //手机号登陆返回的结果
                    if (retCode.equals("0")) {
                        String data = JSON.parseObject(result).getString("data");;
                        Constants.USERINFO = JSON.parseObject(data, new TypeReference<Userinfo_Bean>() {});
                        keepdata();  //保存账户信息
                        phoneSignin.phonesignin_successful();
                    } else {
                        phoneSignin.phonesignin_fail();
                    }
                }else if (what==6){
                    //手机号注册返回的结果
                    if (retCode.equals("0")) {
                        String data = JSON.parseObject(result).getString("data");;
                        Constants.USERINFO = JSON.parseObject(data, new TypeReference<Userinfo_Bean>() {});
                        signresult.signsuccessful();
                    } else if (retCode.equals("6")){
                        signresult.yaoqing_err(retCode);
                    }else if (retCode.equals("16")){
                        signresult.code_err();
                    }else {
                        signresult.othererr(retCode);
                    }
                }
            }

        }
    };

    public interface Signresult {
        public void signsuccessful();

        public void yaoqing_err(String t);

        public void code_err();

        public void uppasswordsuccessful();

        public void othererr(String errcode);
        public void get_code_err(String code);
    }
    public interface PhoneSignin{
        public void phonesignin_successful();
        public void phonesignin_fail();
    }
    public interface Faillcallback {
      public void servererr();
    }
    public interface UpdataPassWord{
        public void fail();
        public void successful();
    }
    /**
     * 网络请求状态回调接口
     */
    public interface NetWorkerr {
        public void timeout();
        public void connectfail();
        public void severerr();
    }

    private NetWorkerr netWorkerr;
    public void setNetWorkListener(NetWorkerr netWorkerr){
        this.netWorkerr=netWorkerr;
    }
    private UpdataPassWord updataPassWord;
    private Signresult signresult;
    private PhoneSignin phoneSignin;

    /**
     * 手机号登陆回调
     * @param phoneSignin
     */
    public void setPhone_Sign_Listener(PhoneSignin phoneSignin){
        this.phoneSignin=phoneSignin;
    }

    public void setsignlistener(Signresult signresult1) {
        this.signresult = signresult1;
    }
    public void setUpdataPassWordListener( UpdataPassWord updataPassWordListener) {
        this.updataPassWord=updataPassWordListener;
    }
    public void keepdata() {
        SharedPreferences sp = activity.getSharedPreferences("account", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("phone", phone);
        editor.putString("password", password);
        editor.commit();
    }

    //微信登陆部分
    public void weichatsign(Context context) {
        Constants.api = WXAPIFactory.createWXAPI(context, Constants.APP_ID, false);
        Constants.api.registerApp(Constants.APP_ID);
        final SendAuth.Req req = new SendAuth.Req();
        req.scope = "snsapi_userinfo";
        req.state = "fentuanyizhuang";
        Constants.api.sendReq(req);
    }
    /**
     * 获取微信用户基本信息
     * @param token
     * @param openid
     */
    public void getwxinfo(String token, String openid) {
        String url = "https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID";
        url = url.replace("ACCESS_TOKEN", token);
        url = url.replace("OPENID", openid);
        network.connectnet("", url, handler, 1);
    }

    //获取注册验证码
    public void getcode(String phone) {
        String url = Constants.URL + "/user/sendCode";
        String data = "{\"phone\":\"%phone\"}";
        data = data.replace("%phone", phone);
        network.connectnet(data, url, handler, 2);
    }

    /**
     * 微信登陆绑定手机号
     * @param activity
     * @param phone
     * @param password
     * @param code
     * @param yaoqingcode
     */
    public void bindphone(Activity activity, String phone, String password, String code, String yaoqingcode) {
        this.activity = activity;
        wx_info_bean.setAddress(province + "·" + city);
        wx_info_bean.setNickname(nickname);
        wx_info_bean.setSex(sex);
        wx_info_bean.setWeixinid(openid);
        wx_info_bean.setHeadportrait(headimgurl);
        wx_info_bean.setPassword(password);
        wx_info_bean.setCode(code);
        wx_info_bean.setInvitecode(yaoqingcode);
        wx_info_bean.setPhone(phone);
        String data = new Gson().toJson(wx_info_bean);
        Log.d("555", data);
        String url = Constants.URL + "/user/perfectInfo";
        network.connectnet(data, url, handler, 3);
    }
    /**
     * 修改手机密码
     * @param phone
     * @param code
     * @param password
     */
    public void findpassword(String phone, String code, String password) {
        String data = "{\"phone\":\"%phone\",\"code\":\"%code\",\"password\":\"%password\"}";
        data = data.replace("%phone", phone);
        data = data.replace("%code", code);
        data = data.replace("%password", password);
        network.connectnet(data, Constants.URL + "user/findPassWord ", handler, 4);
    }
    /**
     * 手机号登陆
     * @param activity
     * @param phone
     * @param password
     */
    public void sign_in(Activity activity,String phone, String password) {
        this.activity=activity;
        this.phone = phone;
        this.password = password;
        String data = "{\"phone\":\"%phone\",\"password\":\"%password\"}";
        data = data.replace("%password", password);
        data = data.replace("%phone", phone);
        network.connectnet(data, Constants.URL + "user/login", handler, 5);
    }

    /**
     * 通过手机号注册
     * @param phone
     * @param password
     * @param code
     * @param yaoqingcode
     */
    public void phonesignin(String phone, String password, String code, String yaoqingcode){
        Phone_Sign_Bean phone_sign_bean=new Phone_Sign_Bean(phone,password,code,yaoqingcode);
        String data=new Gson().toJson(phone_sign_bean);
        network.connectnet(data,Constants.URL+"user/registAccount",handler,6);
    }

    /**
     * 找回密码时获取验证码
     * @param phone
     */
    public void findpassword_code(String phone){
        String url = Constants.URL + "/user/sendCodeXg";
        String data = "{\"phone\":\"%phone\"}";
        data = data.replace("%phone", phone);
        network.connectnet(data, url, handler, 7);
    }
}
