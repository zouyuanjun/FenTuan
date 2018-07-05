package com.lejiaokeji.fentuan.control;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;

import com.lejiaokeji.fentuan.utils.Network;

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

        }
    };

//    public void upnickname(String name){
//        String data="{\"\"}"
//    }
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
}
