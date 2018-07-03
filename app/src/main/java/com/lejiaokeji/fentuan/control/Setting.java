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

    public void upnickname(String name){

    }

}
