package com.lejiaokeji.fentuan.activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;
import com.lejiaokeji.fentuan.R;
import com.lejiaokeji.fentuan.control.Shop_Details;

public class Pdd_Shop_Details_Activity extends AppCompatActivity{
String shopid;
Shop_Details shop_details;
ImageView imageView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdd_shop_details);
        shopid =getIntent().getStringExtra("shopid");

        shop_details=Shop_Details.getInstance();
        shop_details.getdata(shopid);
    //    shop_details.getUnionData("10004_15554651",shopid);
        createBitmap(shopid);
      //  imageView.setImageBitmap(createBitmap(shopid));
    }
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

}
