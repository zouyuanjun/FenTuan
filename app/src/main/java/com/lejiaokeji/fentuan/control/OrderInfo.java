package com.lejiaokeji.fentuan.control;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.lejiaokeji.fentuan.databean.Pdd_Order_Bean;
import com.lejiaokeji.fentuan.utils.Network;
import com.lejiaokeji.fentuan.wxapi.Constants;
import java.util.ArrayList;
import java.util.List;
public class OrderInfo {
    Network network;

    public OrderInfo(){
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
                //搜索订单
                if (retCode.equals("0")){
                    String data= JSON.parseObject(result).getString("data");
                    List<Pdd_Order_Bean> list=new ArrayList<>();
                    dataCall.seachresult(list);
                }
            }else if (what==2){
                //某个状态的订单
                if (retCode.equals("0")){
                    String data=JSON.parseObject(result).getString("data");
                    List<Pdd_Order_Bean> list=new ArrayList<>();
                    dataCall.backotherorder(list);
                }
            }else if (what==3){
                //全部订单
                if (retCode.equals("0")){
                    String data=JSON.parseObject(result).getString("data");
                    List<Pdd_Order_Bean> list=new ArrayList<>();
                    dataCall.backallorder(list);
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
        public void backallorder(List<Pdd_Order_Bean> list);
        public void backotherorder(List<Pdd_Order_Bean> list);
        public void seachresult(List<Pdd_Order_Bean> list);
    }
    private NetWorkerr netWorkerr;
    private DataCall dataCall;
    public void setDataCallListener(DataCall dataCallListener){
        this.dataCall=dataCallListener;
    }
    public void setNetWorkListener(NetWorkerr netWorkerr){
        this.netWorkerr=netWorkerr;
    }

    /**
     * 获取所有的订单
     */
    public void getallareder(String startPage){
        String data="{\"pid\":\"%pid\",\"startPage\":\"%startPage\"}";
        data=data.replace("%pid",Constants.USERINFO.getPddpid());
        data=data.replace("%startPage",startPage);
        network.connectnet(data, Constants.URL+"pddOrder/getOrderInfo",handler,3);
    }

    /**
     * 根据订单号搜索订单
     * @param order
     */
    public void seachorder(String order){
        String data="{\"orderId\":\"%order\"}";
        data=data.replace("%order",order);
        network.connectnet(data, Constants.URL+"pddOrder/selectByOrderId",handler,1);
    }

    /**
     * 获取其他状态的订单
     * @param status
     */
    public void getotherorder(String status){
        String data="{\"pddPid\":\"%pddPid\",\"status\":\"%status\"}";
        data=data.replace("%pddPid",Constants.USERINFO.getPddpid());
        data=data.replace("%status",status);
        network.connectnet(data, Constants.URL+"pddOrder/orderInfo",handler,2);
    }
}
