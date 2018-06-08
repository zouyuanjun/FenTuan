package com.lejiaokeji.fentuan.control;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.lejiaokeji.fentuan.activity.JD_Shop_Details_Activity;
import com.lejiaokeji.fentuan.databean.JD_Shop_Details_Bean;
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
            String code=JSON.parseObject(result).getString("retCode");
            if (code.equals("0")){
                if (what==1){
                    String data=JSON.parseObject(result).getString("data");
                    JSONObject jsonObject=JSON.parseObject(data);
                    JD_Shop_Details_Bean jd_shop_details_bean=new JD_Shop_Details_Bean();
                    jd_shop_details_bean.setGoodsName(jsonObject.getString("goodsName"));
                    jd_shop_details_bean.setImgUrl(jsonObject.getString("imgUrl"));
                    jd_shop_details_bean.setMaterialUrl(jsonObject.getString("materialUrl"));
                    jd_shop_details_bean.setUnitPrice(jsonObject.getString("unitPrice"));
                    jd_shop_details_bean.setShopId(jsonObject.getString("shopId"));
                    detailsListener.getdatasuccessful(jd_shop_details_bean);
                }
            }

        }
    };
    public interface DetailsListener {
        public void getdatasuccessful(JD_Shop_Details_Bean shop_data);
        public void signfail(String t);
        public void fistlogin();
        public void severerr();
    }
    private DetailsListener detailsListener;
    public void setslistener( DetailsListener detailsListener){
        this.detailsListener=detailsListener;
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
