package com.lejiaokeji.fentuan.control;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.lejiaokeji.fentuan.databean.Item_notificationBean;
import com.lejiaokeji.fentuan.utils.Network;
import com.lejiaokeji.fentuan.wxapi.Constants;

import java.util.ArrayList;
import java.util.List;

public class Notification {
    Network network;
    public Notification(){
        network=Network.getnetwork();
    }
    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int what = msg.what;
            String result = msg.obj.toString();
            Log.d("notificationresult",result);
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
            if (what==1){
                if (retCode.equals("0")){
                    String data= JSON.parseObject(result).getString("data");
                    List<Item_notificationBean> list=new ArrayList<>();
                     list=JSON.parseArray(data,Item_notificationBean.class);
                    dataCall.notificationData(list);
                }
            }else if (what==2){
                if (retCode.equals("0")){
                    dataCall.commit();
                }
            }
        }
    };

    public void getdata(){
        network.connectnet("", Constants.URL+"notice/sendNotice",handler,1);
    }

    /**
     * 网络请求状态回调接口
     */
    public interface NetWorkerr {
        public void timeout();
        public void connectfail();
        public void severerr();
    }
    public interface DataCall{
        public void notificationData( List<Item_notificationBean> list);
        public void commit();
    }
    private NetWorkerr netWorkerr;
    private DataCall dataCall;
    public void setDataCallListener(DataCall dataCallListener){
        this.dataCall=dataCallListener;
    }
    public void setNetWorkListener(NetWorkerr netWorkerr){
        this.netWorkerr=netWorkerr;
    }
}
