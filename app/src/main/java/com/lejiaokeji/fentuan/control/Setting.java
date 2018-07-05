package com.lejiaokeji.fentuan.control;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.lejiaokeji.fentuan.utils.Network;
import com.lejiaokeji.fentuan.wxapi.Constants;

/**
 * 设置相关控制类
 */
public class Setting {
    Network network;
    public Setting(){
        network=Network.getnetwork();
    }
    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int what = msg.what;
            String result = msg.obj.toString();
            Log.d("shouyiresult",result);
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
                  dataCall.upnamesuccessful();
                }
            }
        }
    };

    public void upnickname(String name){
        String data="{\"nickname\":\"%nickname\",\"phone\":\"%phone\"}";
        data=data.replace("%nickname",name);
        data=data.replace("%phone", Constants.USERINFO.getPhone());
        network.connectnet(data,Constants.URL+"UserBase/updateSet",handler,1);
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
        public void upnamesuccessful();
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
