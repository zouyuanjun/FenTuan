package com.lejiaokeji.fentuan.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class GetSystemdate {

    static Date day=new Date();
    SimpleDateFormat df;

    private static GetSystemdate getSysdata=new GetSystemdate();
     private GetSystemdate() {
        df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss：SSS");
    }
    public static GetSystemdate getsysdata(){
        return getSysdata;
    }
    public String datastring(){
         String s=df.format(day);
         return s;
    }
}
