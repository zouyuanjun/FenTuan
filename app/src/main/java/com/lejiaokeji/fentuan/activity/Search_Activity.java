package com.lejiaokeji.fentuan.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.lejiaokeji.fentuan.R;
import com.lejiaokeji.fentuan.adapter.Home_Re_Adapter;
import com.lejiaokeji.fentuan.control.Home_Page_Control;
import com.lejiaokeji.fentuan.databean.Item_Shop;
import com.lejiaokeji.fentuan.databean.Shop_Data;
import com.lejiaokeji.fentuan.wxapi.Constants;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Search_Activity extends AppCompatActivity{
    Activity activity;
    ImageView im_back;
    Button bt_searchbutton;
    ProgressBar progressBar;
    EditText ed_search;
    PullLoadMoreRecyclerView pullLoadMoreRecyclerView;
    Home_Re_Adapter adapter;
    Context context;
    List<Item_Shop> myListData= new ArrayList<Item_Shop>();
    Home_Page_Control home_page_control;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.parseColor("#ff475d"));
        }
        setContentView(R.layout.activity_search);
        im_back=findViewById(R.id.im_back_to_home);
        im_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        progressBar=findViewById(R.id.pb_search);
        ed_search=findViewById(R.id.ed_search);
        pullLoadMoreRecyclerView=findViewById(R.id.rv_search);
        bt_searchbutton =findViewById(R.id.im_searchbutton);
        context=this;
        home_page_control=Home_Page_Control.getInstance();
        ed_search.setFocusable(true);
        ed_search.setFocusableInTouchMode(true);
        ed_search.requestFocus();
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        initdata();
    }
    public void initdata(){
        home_page_control.setlistener(new Home_Page_Control.Home_Page_Listener() {
            @Override
            public void loadsuccefful(Shop_Data shop_data) {

            }
            @Override
            public void loadfail(String t) {
                Toast.makeText(context,"您搜索的商品没有结果哦，请更换关键词搜索",Toast.LENGTH_LONG).show();
            }

            @Override
            public void searchresult(Shop_Data shop_data) {
                myListData.clear();
                for (Item_Shop item_shop:shop_data.getData()) {
                    myListData.add(item_shop);
                }
                pullLoadMoreRecyclerView.setPullLoadMoreCompleted();
                progressBar.setVisibility(View.GONE);
                adapter.notifyDataSetChanged();


            }
            @Override
            public void severerr() {

            }

            @Override
            public void connecttimeout() {

            }

            @Override
            public void connectfail() {

            }
        });
        bt_searchbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String keyword=ed_search.getText().toString();
                if (!keyword.isEmpty()){
                    home_page_control.searchgoods(keyword);
                    progressBar.setVisibility(View.VISIBLE);
                }
            }
        });
        adapter=new Home_Re_Adapter(context,myListData);
        pullLoadMoreRecyclerView.setLinearLayout();
        pullLoadMoreRecyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        pullLoadMoreRecyclerView.setPullRefreshEnable(false);
        pullLoadMoreRecyclerView.setPushRefreshEnable(false);
        pullLoadMoreRecyclerView.setOnPullLoadMoreListener(new PullLoadMoreRecyclerView.PullLoadMoreListener() {
            @Override
            public void onRefresh() {

            }

            @Override
            public void onLoadMore() {

            }
        });
        adapter.setOnItemClickListener(new Home_Re_Adapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                if (Constants.SELECT_JD){
                    Intent intent=new Intent(activity, JD_Shop_Details_Activity.class);
                    intent.putExtra("shopid",myListData.get(position).getGoods_id());
                    intent.putExtra("link",myListData.get(position).getDiscount_link());
                    intent.putExtra("discount",myListData.get(position).getDiscount_price());
                    intent.putExtra("yongjin",myListData.get(position).getCommission());
                    activity.startActivity(intent);
                }else {
                    Intent  intent=new Intent(activity, Pdd_Shop_Details_Activity.class);
                    intent.putExtra("shopid",myListData.get(position).getGoods_id());
                    intent.putExtra("yongjin",myListData.get(position).getCommission());
                    intent.putExtra("quan_price",myListData.get(position).getCoupon_price());
                    activity.startActivity(intent);

                }

            }
            @Override
            public void onItemLongClick(View view, int position) {
            }
        });
    }
    public void getdata(){

    }
}
