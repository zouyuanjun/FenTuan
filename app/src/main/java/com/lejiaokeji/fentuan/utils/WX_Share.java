package com.lejiaokeji.fentuan.utils;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class WX_Share {

    private static WX_Share instance=new WX_Share();
    public static WX_Share getinstance(){
        return instance;
    }
    private WX_Share() {
    }


    //分享多张照片到朋友圈
    public static void sharePhotosToWX(Context context, String text, List<String> imageUris) {
        ArrayList<Uri> uriArrayList = new ArrayList<>();
        uriArrayList.clear();
        if (!uninstallSoftware(context, "com.tencent.mm")) {
            Toast.makeText(context, "微信没有安装！", Toast.LENGTH_SHORT).show();
            return;
        }
        for (int i=0;i<imageUris.size();i++){
            File file = new File(imageUris.get(i));
            if (!file.exists()) {
                return;
            }
         //   uriArrayList.add(Uri.fromFile(file));
           uriArrayList.add(FileProvider.getUriForFile(context, "com.lejiaokeji.fentuan.fileprovider", file));
          Log.d("sdf",FileProvider.getUriForFile(context, "com.lejiaokeji.fentuan.fileprovider", file).getPath());
            Log.d("string",FileProvider.getUriForFile(context, "com.lejiaokeji.fentuan.fileprovider", file).toString());
        }
        Intent intent = new Intent();
        ComponentName comp = new ComponentName("com.tencent.mm", "com.tencent.mm.ui.tools.ShareToTimeLineUI");
        intent.setComponent(comp);
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_SEND_MULTIPLE);
        intent.putParcelableArrayListExtra(Intent.EXTRA_STREAM, uriArrayList);
        intent.putExtra("Kdescription", text);
        context.startActivity(intent);
    }
    //分享单张照片到朋友圈
    public static void sharePhotoToWX(Context context, String text, String imagepath) {
        if (!uninstallSoftware(context, "com.tencent.mm")) {
            Toast.makeText(context, "微信没有安装！", Toast.LENGTH_SHORT).show();
            return;
        }
        File file = new File(imagepath);
        Uri uri=FileProvider.getUriForFile(context, "com.lejiaokeji.fentuan.fileprovider", file);

        Intent intent = new Intent();
//        ComponentName comp = new ComponentName("com.tencent.mm", "com.tencent.mm.ui.tools.ShareToTimeLineUI");
//        intent.setComponent(comp);
        intent.setAction(Intent.ACTION_SEND);
        intent.setType("image/*");
        intent.putExtra(Intent.EXTRA_STREAM, uri);
        Log.d("555",uri.getPath());
        Log.d("string",uri.toString());
        intent.putExtra("Kdescription", "这个也不显示");
        context.startActivity(intent);
    }
    //判断是否安装微信客户端
    private static boolean uninstallSoftware(Context context, String packageName) {
        PackageManager packageManager = context.getPackageManager();
        try {
            PackageInfo packageInfo = packageManager.getPackageInfo(packageName, PackageManager.COMPONENT_ENABLED_STATE_DEFAULT);
            if (packageInfo != null) {
                return true;
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }
}
