package com.lejiaokeji.fentuan.activity;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.lejiaokeji.fentuan.R;
import com.lejiaokeji.fentuan.control.OrderInfo;
import com.lejiaokeji.fentuan.databean.Pdd_Order_Bean;

import java.util.ArrayList;
import java.util.List;

public class Order_Activity extends BaseActivity implements View.OnClickListener {
    ImageView im_order_back;
    TextView tv_order_all;
    TextView tv_order_pay;
    TextView tv_order_shou;
    TextView tv_order_jiesuan;
    TextView tv_order_shixiao;
    TextView textView;
    ImageView imageView;
    ImageView im_order_all;
    ImageView im_order_pay;
    ImageView im_order_shou;
    ImageView im_order_jiesuan;
    ImageView im_order_shixiao;
    Button bt_search_order_button;
    OrderInfo orderInfo;
    EditText ed_search_order;
    List<TextView> textViewList = new ArrayList<>(); //用来保存已选择的textview
    List<ImageView> imageViewList = new ArrayList<>(); //保存已选择的image
    Activity activity;
    Context context;

    TextView tv_nodata;
    ProgressBar progressBar;


    //订单状态： -1 未支付; 0-已支付；1-已成团；2-确认收货；3-审核成功；4-审核失败（不可提现）；5-已经结算；8-非多多进宝商品（无佣金订单）;10-已处罚
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.parseColor("#ff286e"));
        }
        setContentView(R.layout.activity_order);
        activity = this;
        context = this;
        im_order_back = findViewById(R.id.im_order_back);
        im_order_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        ed_search_order = findViewById(R.id.ed_search_order);
        bt_search_order_button = findViewById(R.id.bt_search_order_button);
        tv_order_all = findViewById(R.id.tv_order_all);
        tv_order_pay = findViewById(R.id.tv_order_pay);
        tv_order_shou = findViewById(R.id.tv_order_shou);
        tv_order_jiesuan = findViewById(R.id.tv_order_jiesuan);
        tv_order_shixiao = findViewById(R.id.tv_order_shixiao);
        im_order_all = findViewById(R.id.im_order_all);
        im_order_pay = findViewById(R.id.im_order_pay);
        im_order_shou = findViewById(R.id.im_order_shouhuo);
        im_order_jiesuan = findViewById(R.id.im_order_jiesuan);
        im_order_shixiao = findViewById(R.id.im_order_shixiao);
        textViewList.add(tv_order_all);
        imageViewList.add(im_order_all);
        tv_nodata=findViewById(R.id.textView7);
        progressBar=findViewById(R.id.progressBar3);
        tv_order_all.setOnClickListener(this);
        tv_order_pay.setOnClickListener(this);
        tv_order_shou.setOnClickListener(this);
        tv_order_jiesuan.setOnClickListener(this);
        tv_order_shixiao.setOnClickListener(this);
        bt_search_order_button.setOnClickListener(this);
        orderInfo = new OrderInfo();
        orderInfo.getallareder("1");
    }

    @Override
    protected void onResume() {
        super.onResume();
        orderInfo.setNetWorkListener(new OrderInfo.NetWorkerr() {
            @Override
            public void timeout() {
                Toast.makeText(activity, "连接超时，请检查网络", Toast.LENGTH_LONG).show();
            }

            @Override
            public void connectfail() {
                Toast.makeText(activity, "与服务器连接失败，请检查网络", Toast.LENGTH_LONG).show();
            }

            @Override
            public void severerr() {
                Toast.makeText(activity, "服务器内部错误", Toast.LENGTH_LONG).show();
            }
        });
        orderInfo.setDataCallListener(new OrderInfo.DataCall() {

            @Override
            public void backallorder(List<Pdd_Order_Bean> list) {
                progressBar.setVisibility(View.GONE);
                if (list.size() == 0) {
                   tv_nodata.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void backotherorder(List<Pdd_Order_Bean> list) {
                progressBar.setVisibility(View.GONE);
                if (list.size() == 0) {
                    tv_nodata.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void seachresult(List<Pdd_Order_Bean> list) {
                if (list.size() == 0) {
                    Toast.makeText(context, "没有找到这个订单信息", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_order_all: {
                textView = textViewList.get(0);
                imageView = imageViewList.get(0);
                textView.setTextColor(Color.parseColor("#333333"));
                imageView.setVisibility(View.GONE);
                textViewList.clear();
                imageViewList.clear();
                textViewList.add(tv_order_all);
                imageViewList.add(im_order_all);
                tv_order_all.setTextColor(Color.parseColor("#ff2d55"));
                im_order_all.setVisibility(View.VISIBLE);
                orderInfo.getallareder("1");
                break;
            }
            case R.id.tv_order_pay: {
                textView = textViewList.get(0);
                imageView = imageViewList.get(0);
                textView.setTextColor(Color.parseColor("#333333"));
                imageView.setVisibility(View.GONE);
                textViewList.clear();
                imageViewList.clear();
                textViewList.add(tv_order_pay);
                imageViewList.add(im_order_pay);
                tv_order_pay.setTextColor(Color.parseColor("#ff2d55"));
                im_order_pay.setVisibility(View.VISIBLE);
                getdata("0");
                break;
            }
            case R.id.tv_order_shou: {
                textView = textViewList.get(0);
                imageView = imageViewList.get(0);
                textView.setTextColor(Color.parseColor("#333333"));
                imageView.setVisibility(View.GONE);
                textViewList.clear();
                imageViewList.clear();
                textViewList.add(tv_order_shou);
                imageViewList.add(im_order_shou);
                tv_order_shou.setTextColor(Color.parseColor("#ff2d55"));
                im_order_shou.setVisibility(View.VISIBLE);
                getdata("2");
                break;
            }
            case R.id.tv_order_jiesuan: {
                textView = textViewList.get(0);
                imageView = imageViewList.get(0);
                textView.setTextColor(Color.parseColor("#333333"));
                imageView.setVisibility(View.GONE);
                textViewList.clear();
                imageViewList.clear();
                textViewList.add(tv_order_jiesuan);
                imageViewList.add(im_order_jiesuan);
                tv_order_jiesuan.setTextColor(Color.parseColor("#ff2d55"));
                im_order_jiesuan.setVisibility(View.VISIBLE);
                getdata("5");
                break;
            }
            case R.id.tv_order_shixiao: {
                textView = textViewList.get(0);
                imageView = imageViewList.get(0);
                textView.setTextColor(Color.parseColor("#333333"));
                imageView.setVisibility(View.GONE);
                textViewList.clear();
                imageViewList.clear();
                textViewList.add(tv_order_shixiao);
                imageViewList.add(im_order_shixiao);
                tv_order_shixiao.setTextColor(Color.parseColor("#ff2d55"));
                im_order_shixiao.setVisibility(View.VISIBLE);
                getdata("4");
                break;
            }
            //搜索订单号
            case R.id.bt_search_order_button: {
                String order_num = ed_search_order.getText().toString();
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
                if (!order_num.isEmpty()) {
                    orderInfo.seachorder(order_num);
                } else {
                    Toast.makeText(context, "请填写订单号", Toast.LENGTH_SHORT).show();
                }

            }
        }
    }
    public void getdata(String status){
        tv_nodata.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);
        orderInfo.getotherorder(status);
    }

}
