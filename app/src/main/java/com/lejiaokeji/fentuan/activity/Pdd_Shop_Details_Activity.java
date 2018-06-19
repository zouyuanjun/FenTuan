package com.lejiaokeji.fentuan.activity;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
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
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
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
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;
import com.lejiaokeji.fentuan.R;
import com.lejiaokeji.fentuan.control.Shop_Details;
import com.lejiaokeji.fentuan.databean.JD_Shop_Details_Bean;
import com.lejiaokeji.fentuan.databean.Pdd_Shop_Details_Bean;
import com.lejiaokeji.fentuan.utils.BitmapUtil;
import com.lejiaokeji.fentuan.utils.WX_Share;
import com.lejiaokeji.fentuan.view.helpview.GlideImageLoader;
import com.lejiaokeji.fentuan.wxapi.Constants;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class Pdd_Shop_Details_Activity extends AppCompatActivity{
    String shopid;
    Shop_Details shop_details;
    Banner banner;
    String yongjin;
    String quan_price;
    TextView tv_yongjin;
    TextView tv_tuiguang;
    TextView lingquan;
    TextView tv_price;
    TextView tv_quan_price;
    TextView tv_title;
    TextView tv_goods_desc;
    TextView tv_quan;
    Button bt_detail_add_mall;
    Activity activity;
    String imgurl="";
    ImageView im_detail_back;
    RelativeLayout rl_lingquan;
    RelativeLayout rl_tuiguang;
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
        setContentView(R.layout.activity_pdd_shop_details);
        shopid =getIntent().getStringExtra("shopid");
        yongjin =getIntent().getStringExtra("yongjin");
        quan_price =getIntent().getStringExtra("quan_price");
        activity=this;
        banner =  findViewById(R.id.pdd_banner);
        tv_yongjin=findViewById(R.id.tv_detail_yongjin);
        tv_tuiguang=findViewById(R.id.tv_tuiguang_count);
        lingquan=findViewById(R.id.tv_lingquan_count);
        tv_price=findViewById(R.id.tv_begin_price);
        tv_title=findViewById(R.id.tv_detail_title);
        tv_quan_price=findViewById(R.id.tv_sale_price);
        tv_goods_desc=findViewById(R.id.tv_goods_desc);
        tv_quan=findViewById(R.id.tv_quan);
        im_detail_back=findViewById(R.id.im_detail_back);
        im_detail_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        shop_details=Shop_Details.getInstance();
        shop_details.getdata(shopid);
        rl_lingquan=findViewById(R.id.rv_lingquan);
        rl_tuiguang=findViewById(R.id.rv_tuiguang);
    bt_detail_add_mall=findViewById(R.id.bt_detail_add_mall);
    bt_detail_add_mall.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Toast.makeText(activity,"该功能正在加紧开发，请在给我们点时间吧~~",Toast.LENGTH_LONG).show();
        }
    });
        createBitmap(shopid);
        initdata();
    }


    public void initdata(){
        shop_details.setslistener(new Shop_Details.DetailsListener() {
            @Override
            public void getdatasuccessful(JD_Shop_Details_Bean shop_data) {

            }

            @Override
            public void openjdurl(String t) {

            }

            @Override
            public void getpdddata(Pdd_Shop_Details_Bean pdd_shop_details_bean) {
                banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
                //设置图片加载器
                banner.setImageLoader(new GlideImageLoader());
                //设置图片集合
                banner.setImages(pdd_shop_details_bean.getGoods_gallery_urls());
                //设置banner动画效果
      //          banner.setBannerAnimation(Transformer.DepthPage);
                //设置自动轮播，默认为true
                banner.isAutoPlay(true);
                //设置轮播时间
                banner.setDelayTime(2500);
                //设置指示器位置（当banner模式中有指示器时）
                banner.setIndicatorGravity(BannerConfig.CENTER);
                //banner设置方法全部调用完毕时最后调用
                banner.start();
                imgurl=pdd_shop_details_bean.getGoods_image_url();
                tv_tuiguang.setText("￥"+yongjin);
                tv_yongjin.setText("佣金：￥"+yongjin);
                lingquan.setText(Float.parseFloat(pdd_shop_details_bean.getCoupon_discount())/100+"");
                tv_price.setText("原价：￥"+Float.parseFloat(pdd_shop_details_bean.getMin_group_price())/100);
                tv_title.setText(pdd_shop_details_bean.getGoods_name());
                tv_goods_desc.setMovementMethod(ScrollingMovementMethod.getInstance());
                tv_goods_desc.setText("                 "+pdd_shop_details_bean.getGoods_desc());
          if (pdd_shop_details_bean.getHas_coupon().equals("false")){
              tv_quan.setText("优惠券已过期");
              tv_quan_price.setText("￥"+Float.parseFloat(pdd_shop_details_bean.getMin_group_price())/100);
          }else {
              String quan=Float.parseFloat(pdd_shop_details_bean.getCoupon_discount())/100+"";
              tv_quan.setText(quan.substring(0,quan.length()-2)+"元优惠券");

              double sale_price=Double.parseDouble(pdd_shop_details_bean.getMin_group_price())/100-Double.parseDouble(pdd_shop_details_bean.getCoupon_discount())/100;
              DecimalFormat df = new DecimalFormat("0.00");
              String CNY = df.format(sale_price);

              tv_quan_price.setText("￥"+CNY);
          }
            }
            @Override
            public void getjdsharurl(String url) throws IOException {

            }

            @Override
            public void openpddurl(String url) {
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
            public void sharepdd(String url) {
                try {
                    String shareText=tv_title.getText().toString()+"\n"+tv_price.getText().toString()+"\n"+tv_quan.getText().toString()+"\n"+"券后价："+tv_quan_price.getText().toString()
                            +"\n"+"购买链接："+url+"\n"+"~~~~~~~~~~~~~~~~~"+"\n"+"点击链接打开或长按识别二维码领券即可购买";
                    ClipboardManager cm = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                    ClipData mClipData = ClipData.newPlainText("Label", shareText);
                    Toast.makeText(activity,"文案已复制，粘贴即可发圈",Toast.LENGTH_LONG).show();
                    cm.setPrimaryClip(mClipData);
                    getbitmap(url);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        rl_lingquan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shop_details.getpddUnion(Constants.USERINFO.getPddpid(),shopid);
            }
        });
        rl_tuiguang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shop_details.sharepdd(Constants.USERINFO.getPddpid(),shopid);

            }
        });

    }
    //创建链接二维码
    public static Bitmap createBitmap(String str){
        Bitmap bitmap = null;
        BitMatrix result = null;
        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
        try {
            result = multiFormatWriter.encode(str, BarcodeFormat.QR_CODE, 400, 400);
            BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
            bitmap = barcodeEncoder.createBitmap(result);
        } catch (WriterException e){
            e.printStackTrace();
        } catch (IllegalArgumentException iae){ // ?
            return null;
        }
        return bitmap;
    }
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

    public void  getbitmap(String url) throws IOException, ExecutionException, InterruptedException {
       RelativeLayout relativeLayout=findViewById(R.id.relative);
        relativeLayout.setDrawingCacheEnabled(true);
      View  view=activity.getWindow().getDecorView();
        //获取标题的bitmap
        view.setDrawingCacheEnabled(true);//生成View的副本，是一个Bitmap
        Bitmap titlebmp = relativeLayout.getDrawingCache();//获取View的副本，就是这个View上面所显示的任何内容
        //获取商品图片的bitmap
        banner.setDrawingCacheEnabled(true);
        Bitmap goodsbitmap = banner.getDrawingCache(true);

        BitmapUtil bitmapUtil= BitmapUtil.getinstance();
        //缩放标题bitmap
        titlebmp=bitmapUtil.resizeImage(titlebmp,Float.parseFloat(goodsbitmap.getWidth()+"")/titlebmp.getWidth());
        //合成
        Bitmap addbitmap= bitmapUtil.addBitmap(titlebmp,goodsbitmap);
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
        imageUris.add(path+"5645.png");
        imageUris.add(path+filename);
        //   WX_Share.sharePhotosToWX(activity,"hahahah",imageUris);
        WX_Share.sharePhotoToWX(activity,"hahahah",path+filename);
        Log.d("55","截图完成");
        banner.setDrawingCacheEnabled(false);
    }
}
