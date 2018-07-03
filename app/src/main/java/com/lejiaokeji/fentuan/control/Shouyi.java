package com.lejiaokeji.fentuan.control;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.lejiaokeji.fentuan.databean.Add_Alipay_Bean;
import com.lejiaokeji.fentuan.utils.Network;
import com.lejiaokeji.fentuan.wxapi.Constants;

/**
 * 收益模块
 */
public class Shouyi {
    Network network;

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
                    dataCall.getcode();
                }
            }else if (what==2){
                if (retCode.equals("0")){
                    dataCall.commit();
                }
            }
        }
    };
    /**
     * 网络请求状态回调接口
     */
    public interface NetWorkerr {
        public void timeout();
        public void connectfail();
        public void severerr();
    }
    public interface DataCall{
        public void getcode();
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

    public Shouyi(){
        network=Network.getnetwork();
    }

    /**
     * 获取验证码
     * @param phone
     * @param paymentAccount
     */
    public void getcode(String phone,String paymentAccount){
        String data="{\"phone\":\"%phone\",\"paymentAccount\":\"%paymentAccount\"}";
        data=data.replace("%phone",phone);
        data=data.replace("%paymentAccount",paymentAccount);
        network.connectnet(data, Constants.URL+"account/sendCode",handler,1);
    }
    public void commit(Add_Alipay_Bean add_alipay_bean){
        String data=new Gson().toJson(add_alipay_bean);
        network.connectnet(data,Constants.URL+"account/complete",handler,2);
    }
}
