package com.lejiaokeji.fentuan.activity;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.facebook.drawee.view.SimpleDraweeView;
import com.lejiaokeji.fentuan.R;
import com.lejiaokeji.fentuan.control.Shop_Details;
import com.lejiaokeji.fentuan.databean.JD_Shop_Details_Bean;
import com.lejiaokeji.fentuan.databean.Pdd_Shop_Details_Bean;
import com.lejiaokeji.fentuan.utils.BitmapUtil;
import com.lejiaokeji.fentuan.utils.WX_Share;
import com.lejiaokeji.fentuan.wxapi.Constants;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class JD_Shop_Details_Activity extends AppCompatActivity{
    String shopid="";
    String discount_link="";
    String discount="";
    String yongjin="";
    Shop_Details shop_details;
    ImageView simpleDraweeView;
    ImageView im_detail_back;
    TextView tv_yongjing;
    TextView tv_title;
    TextView tv_sale_price;
    TextView tv_price;
    Button bt_add_mall;
    TextView tv_lingquan;
    TextView tv_tuiguang;
    TextView tv_quan;

    Activity activity;
    RelativeLayout relativeLayout;
    RelativeLayout lingquan;
    RelativeLayout tuiguang;

    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            "android.permission.READ_EXTERNAL_STORAGE",
            "android.permission.WRITE_EXTERNAL_STORAGE" };

    BitmapUtil bitmapUtil;
    View view;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                    | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
            window.setNavigationBarColor(Color.TRANSPARENT);
        }
        setContentView(R.layout.activity_jd_shop_details);
        activity=this;
    //    verifyStoragePermissions(activity);
        shopid=getIntent().getStringExtra("shopid");
        discount_link=getIntent().getStringExtra("link");
        discount=getIntent().getStringExtra("discount");
        yongjin=getIntent().getStringExtra("yongjin");
        simpleDraweeView=findViewById(R.id.simpleDraweeView);
        im_detail_back=findViewById(R.id.im_detail_back);
        tv_yongjing=findViewById(R.id.tv_detail_yongjin);
        tv_title=findViewById(R.id.tv_detail_title);
        tv_sale_price=findViewById(R.id.tv_sale_price);
        tv_price=findViewById(R.id.tv_begin_price);
        bt_add_mall=findViewById(R.id.bt_detail_add_mall);
        tv_lingquan=findViewById(R.id.tv_lingquan_count);
        tv_tuiguang=findViewById(R.id.tv_tuiguang_count);
        lingquan=findViewById(R.id.rv_lingquan);
        tuiguang=findViewById(R.id.rv_tuiguang);
        tv_quan=findViewById(R.id.tv_quan);
        tv_quan.setText(discount.substring(0,discount.length()-2)+"元优惠券");
        shop_details=Shop_Details.getInstance();
        setlistener();

        shop_details.getdata(shopid);

    }

    public void setlistener(){
        shop_details.setslistener(new Shop_Details.DetailsListener() {
            @Override
            public void getdatasuccessful(JD_Shop_Details_Bean shop_data) {
                Glide.with(activity)
                        .load(shop_data.getImgUrl())
                        .into(simpleDraweeView);
                tv_lingquan.setText("￥"+discount);
                tv_price.setText("原价：￥"+shop_data.getUnitPrice());
                double sale_price=Double.parseDouble(shop_data.getUnitPrice())-Double.parseDouble(discount);
                DecimalFormat df = new DecimalFormat("0.00");
                String CNY = df.format(sale_price);
                tv_sale_price.setText("￥"+CNY);
                tv_tuiguang.setText("￥"+yongjin);
                tv_yongjing.setText("佣金：￥"+yongjin);
                tv_title.setText(shop_data.getGoodsName());
            }
            @Override
            public void openjdurl(String url) {
                if (uninstallSoftware(activity)){
                    Uri uri = Uri.parse(url);
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(intent);
                }else {
                    Uri uri = Uri.parse(url);
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(intent);
                }
            }
            @Override
            public void getpdddata(Pdd_Shop_Details_Bean pdd_shop_details_bean) {

            }
            @Override
            public void getjdsharurl(String url) throws IOException {
                String shareText=tv_title.getText().toString()+"\n"+tv_price.getText().toString()+"\n"+tv_quan.getText().toString()+"\n"+"券后价："+tv_sale_price.getText().toString()
                        +"\n"+"购买链接："+url+"\n"+"~~~~~~~~~~~~~~~~~"+"\n"+"点击链接到浏览器打开或长按识别二维码领券即可购买";
                ClipboardManager cm = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData mClipData = ClipData.newPlainText("Label", shareText);
                Toast.makeText(activity,"文案已复制，粘贴即可发圈",Toast.LENGTH_LONG).show();
                cm.setPrimaryClip(mClipData);
                getbitmap(url);
            }

            @Override
            public void openpddurl(String url) {

            }

            @Override
            public void sharepdd(String url) {

            }
        });
        lingquan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shop_details.getUnionData(Constants.USERINFO.getJdpid(),shopid,discount_link);
            }
        });
        tuiguang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shop_details.shareJD(Constants.USERINFO.getJdpid(),shopid,discount_link);
            }
        });
        im_detail_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    public void  getbitmap(String url) throws IOException {
        relativeLayout=findViewById(R.id.relative);
        relativeLayout.setDrawingCacheEnabled(true);
        view=activity.getWindow().getDecorView();
        //获取标题的bitmap
        view.setDrawingCacheEnabled(true);//生成View的副本，是一个Bitmap
        Bitmap bmp = relativeLayout.getDrawingCache();//获取View的副本，就是这个View上面所显示的任何内容
        //获取商品图片的bitmap
        simpleDraweeView.setDrawingCacheEnabled(true);
        Bitmap bitmap =simpleDraweeView.getDrawingCache();
        bitmapUtil=BitmapUtil.getinstance();
        //合成
        Bitmap addbitmap= bitmapUtil.addBitmap(bmp,bitmap);
        //获取二维码背景bitmap
        Drawable db = getResources().getDrawable(R.drawable.qr);
        BitmapDrawable drawable = (BitmapDrawable)db;
        Bitmap qrbj = drawable.getBitmap();
        addbitmap=bitmapUtil.resizeImage(addbitmap,Float.parseFloat(qrbj.getWidth()+"")/addbitmap.getWidth());
        //获取二维码
        int size= (int) (qrbj.getHeight()*0.8);
        Bitmap qrbitmap=BitmapUtil.createQRBitmap(url,size);
        //合成二维码bj
        Bitmap erbgbitmap=bitmapUtil.toConformBitmap(qrbj,qrbitmap,qrbj.getHeight()/10,qrbj.getHeight()/10+50);
        //最终合成
        Bitmap aa=bitmapUtil.addBitmap(addbitmap,erbgbitmap);
        Bitmap bitmap1=bitmapUtil.resizeImage(aa,0.50f);
        String path= Environment.getExternalStorageDirectory()+ "/FenTuan_IMG/";
        String filename=shopid+".jpg";
        bitmapUtil.saveFile(activity,bitmap1,filename);
        List<String> imageUris=new ArrayList<>();
        imageUris.add(path+filename);
        imageUris.add(path+filename);
       // WX_Share.sharePhotosToWX(activity,"hahahah",imageUris);
     WX_Share.sharePhotoToWX(activity,"hahahah",path+filename);
        Log.d("55","截图完成");

    }
    public static void verifyStoragePermissions(Activity activity) {

        try {
            //检测是否有写的权限
            int permission = ActivityCompat.checkSelfPermission(activity,
                    "android.permission.WRITE_EXTERNAL_STORAGE");
            if (permission != PackageManager.PERMISSION_GRANTED) {
                // 没有写的权限，去申请写的权限，会弹出对话框
                ActivityCompat.requestPermissions(activity, PERMISSIONS_STORAGE,REQUEST_EXTERNAL_STORAGE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //判断是否安装京东客户端
    private static boolean uninstallSoftware(Context context) {
        PackageManager packageManager = context.getPackageManager();
        try {
            PackageInfo packageInfo = packageManager.getPackageInfo("com.jingdong.app.mall", PackageManager.COMPONENT_ENABLED_STATE_DEFAULT);
            if (packageInfo != null) {
                return true;
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

}
