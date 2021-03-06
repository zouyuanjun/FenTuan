package com.lejiaokeji.fentuan.control;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.lejiaokeji.fentuan.databean.JD_Shop_Details_Bean;
import com.lejiaokeji.fentuan.databean.Pdd_Shop_Details_Bean;
import com.lejiaokeji.fentuan.utils.Network;
import com.lejiaokeji.fentuan.wxapi.Constants;

import java.io.IOException;

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
                    //京东商品详情
                    String data=JSON.parseObject(result).getString("data");
                    JSONObject jsonObject=JSON.parseObject(data);
                    JD_Shop_Details_Bean jd_shop_details_bean=new JD_Shop_Details_Bean();
                    jd_shop_details_bean.setGoodsName(jsonObject.getString("goodsName"));
                    jd_shop_details_bean.setImgUrl(jsonObject.getString("imgUrl"));
                    jd_shop_details_bean.setMaterialUrl(jsonObject.getString("materialUrl"));
                    jd_shop_details_bean.setUnitPrice(jsonObject.getString("unitPrice"));
                    jd_shop_details_bean.setShopId(jsonObject.getString("shopId"));
                    detailsListener.getdatasuccessful(jd_shop_details_bean);
                }else if (what==2){
                    //自买获取京东三合一转链
                    String url=JSON.parseObject(result).getString("data");
                    detailsListener.openjdurl(url);

                }else if (what==3){
                    //平多多商品相亲
                    String data=JSON.parseObject(result).getString("data");
                    JSONArray jsonObject=JSON.parseArray(data);
                    try {
                        JSONObject jsonObject1=jsonObject.getJSONObject(0);
                        Pdd_Shop_Details_Bean pdd_shop_details_bean=JSON.parseObject(jsonObject1.toString(), new TypeReference<Pdd_Shop_Details_Bean>() {});
                        detailsListener.getpdddata(pdd_shop_details_bean);
                    }catch (IndexOutOfBoundsException e){
                        detailsListener.getdatafail();
                    }

                }else if (what==4){
                    //分享京东商品到朋友圈
                    String url=JSON.parseObject(result).getString("data");
                    try {
                        detailsListener.getjdsharurl(url);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }else if (what==5){
                    //打开平多多购买链接
                    String url=JSON.parseObject(result).getString("data");
                    detailsListener.openpddurl(url);
                }else if (what==6){
                    //分享平多多商品到朋友圈
                    String url=JSON.parseObject(result).getString("data");
                    detailsListener.sharepdd(url);

                }
            }
        }
    };
    public interface DetailsListener {
        public void getdatasuccessful(JD_Shop_Details_Bean shop_data);
        public void openjdurl(String t);
        public void getpdddata(Pdd_Shop_Details_Bean pdd_shop_details_bean);
        public void getjdsharurl(String url) throws IOException;
        public void openpddurl(String url);
        public void sharepdd(String url);
        public void getdatafail();
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
            network.connectnet(data,url,handler,3);
        }

    }
    public void getUnionData(String pid,String shopid,String couponUrl){
        if (Constants.SELECT_JD){
            String data="{\"goodsId\":\"%goodsid\",\"positionId\":\"%jdPid\",\"couponUrl\":\"%couponUrl\"}";
            data=data.replace("%goodsid",shopid);
            data=data.replace("%jdPid",pid);
            data=data.replace("%couponUrl",couponUrl);
            String url=Constants.URL+"shopList/chain";
            network.connectnet(data,url,handler,2);
        }
    }

    public void shareJD(String pid,String shopid,String couponUrl){
        if (Constants.SELECT_JD){
            String data="{\"goodsId\":\"%goodsid\",\"positionId\":\"%jdPid\",\"couponUrl\":\"%couponUrl\"}";
            data=data.replace("%goodsid",shopid);
            data=data.replace("%jdPid",pid);
            data=data.replace("%couponUrl",couponUrl);
            String url=Constants.URL+"shopList/chain";
            network.connectnet(data,url,handler,4);
        }
    }
    public void getpddUnion(String pid,String shopid){
        if (!Constants.SELECT_JD){
            String data="{\"goodsId\":\"%goodsid\",\"pddPid\":\"%pddPid\"}";
            data=data.replace("%goodsid",shopid);
            data=data.replace("%pddPid",pid);
            String url=Constants.URL+"generate/getGenerate";
            network.connectnet(data,url,handler,5);
        }
    }
    public void sharepdd(String pid,String shopid){
        if (!Constants.SELECT_JD){
            String data="{\"goodsId\":\"%goodsid\",\"pddPid\":\"%pddPid\"}";
            data=data.replace("%goodsid",shopid);
            data=data.replace("%pddPid",pid);
            String url=Constants.URL+"generate/getGenerate";
            network.connectnet(data,url,handler,6);
        }
    }
}
