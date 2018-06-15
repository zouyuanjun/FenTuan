package com.lejiaokeji.fentuan.wxapi;

import com.lejiaokeji.fentuan.databean.Userinfo_Bean;
import com.tencent.mm.opensdk.openapi.IWXAPI;

public class Constants {
    public static final String APP_ID="wx46b14ff64afefa78";
    public static IWXAPI api;   //第三方和微信通信的接口
    public static String URL="http://192.168.1.112:8080/";
  //  public static String URL="http://47.98.155.149:8088/";
    public static  boolean SELECT_JD=true;   //首页是否选中京东还是拼多多
    public static Userinfo_Bean USERINFO;
}
