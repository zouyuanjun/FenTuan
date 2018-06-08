package com.lejiaokeji.fentuan.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;

import com.bumptech.glide.Glide;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

public class BitmapUtil {
    private static BitmapUtil instance=new BitmapUtil();
    public static BitmapUtil getinstance(){
        return instance;
    }
    private BitmapUtil() {
    }

    public Bitmap toConformBitmap(Bitmap background, Bitmap foreground) {
        if( background == null ) {
            return null;
        }

        int bgWidth = background.getWidth();
        int bgHeight = background.getHeight();
        //int fgWidth = foreground.getWidth();
        //int fgHeight = foreground.getHeight();
        //create the new blank bitmap 创建一个新的和SRC长度宽度一样的位图
        Bitmap newbmp = Bitmap.createBitmap(bgWidth, bgHeight, Bitmap.Config.ARGB_8888);
        Canvas cv = new Canvas(newbmp);
        //draw bg into
        cv.drawBitmap(background, 0, 0, null);//在 0，0坐标开始画入bg
        //draw fg into
        cv.drawBitmap(foreground, 0, 0, null);//在 0，0坐标开始画入fg ，可以从任意位置画入
        //save all clip
        cv.save(Canvas.ALL_SAVE_FLAG);//保存
        //store
        cv.restore();//存储
        return newbmp;
    }
    public Bitmap addBitmap(Bitmap background, Bitmap foreground) {
        if( background == null ) {
            return null;
        }

        int bgWidth = background.getWidth();
        int bgHeight = background.getHeight();
        //int fgWidth = foreground.getWidth();
        int fgHeight = foreground.getHeight();
        //创建一个新的高度相加的位图
        Bitmap newbmp = Bitmap.createBitmap(bgWidth, bgHeight+fgHeight, Bitmap.Config.ARGB_8888);
        Canvas cv = new Canvas(newbmp);
        //draw bg into
        cv.drawBitmap(background, 0, 0, null);//在 0，0坐标开始画入bg
        //draw fg into
        cv.drawBitmap(foreground, 0, bgHeight, null);//在 0，0坐标开始画入fg ，可以从任意位置画入
        //save all clip
        cv.save(Canvas.ALL_SAVE_FLAG);//保存
        //store
        cv.restore();//存储
        return newbmp;
    }

    //保存bitmap为文件
    public  void saveFile(Bitmap bmp , String path,String fileName) throws IOException {

        File dirFile = new File("/data/user/0/com.lejiaokeji.fentuan/cache/");
        if(!dirFile.exists()){
            dirFile.mkdir();
        }
        File myCaptureFile = new File("/data/user/0/com.lejiaokeji.fentuan/cache/" + fileName);
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(myCaptureFile));
        bmp.compress(Bitmap.CompressFormat.JPEG, 80, bos);
        bos.flush();
        bos.close();
    }

}
