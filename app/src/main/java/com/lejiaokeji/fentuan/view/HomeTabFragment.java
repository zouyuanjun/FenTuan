package com.lejiaokeji.fentuan.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lejiaokeji.fentuan.R;
import com.lejiaokeji.fentuan.activity.JD_Shop_Details_Activity;
import com.lejiaokeji.fentuan.activity.Pdd_Shop_Details_Activity;
import com.lejiaokeji.fentuan.adapter.Home_Re_Adapter;
import com.lejiaokeji.fentuan.control.Home_Page_Control;
import com.lejiaokeji.fentuan.databean.Item_Shop;
import com.lejiaokeji.fentuan.databean.PDD_Shop_Bean;
import com.lejiaokeji.fentuan.databean.Shop_Data;
import com.lejiaokeji.fentuan.view.helpview.LazyLoadFragment;
import com.lejiaokeji.fentuan.wxapi.Constants;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

import java.util.ArrayList;
import java.util.List;
public class HomeTabFragment extends LazyLoadFragment {
    public static final String TITLE = "shoptype";
    private int mTitle = 0;
    private PullLoadMoreRecyclerView mRecyclerView;
    List<Item_Shop> myListData= new ArrayList<Item_Shop>();
    Home_Re_Adapter adapter;
    Home_Page_Control home_page_control;
    ProgressBar progressBar;
     int  shoptype=0;
     int page=2;
    String url=Constants.URL+"shopList/recommended";
    ConstraintLayout cl_try;
    Activity activity;
    boolean isFirstload=true;
    public static HomeTabFragment newInstance(int type) {
        HomeTabFragment tabFragment = new HomeTabFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(TITLE, type);
        tabFragment.setArguments(bundle);
        return tabFragment;
    }
    @Override
    public void onStart() {
        super.onStart();
        isFirstload=true;
         page=2;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity=getActivity();
        home_page_control=Home_Page_Control.getInstance();
        if (getArguments() != null) {
            mTitle = getArguments().getInt(TITLE);
            shoptype=mTitle;
        }
    }
    @Override
    protected int setContentView() {
        return R.layout.fragment_home_tab;
    }
    @Override
    public void onResume() {
        super.onResume();
    }
    @Override
    public void onStop() {
        super.onStop();
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        myListData.clear();
    }
    @Override
    protected void lazyLoad() {
        progressBar=findViewById(R.id.pb_shop_list);
        if (isFirstload){
            initdata();
            isFirstload=false;
        }
        home_page_control.setlistener(new Home_Page_Control.Home_Page_Listener() {


            @Override
            public void loadsuccefful(Shop_Data shop_data) {
                for (Item_Shop item_shop:shop_data.getData()){
                    myListData.add(item_shop);
                }
                progressBar.setVisibility(View.GONE);
                mRecyclerView.setPullLoadMoreCompleted();
                adapter.notifyDataSetChanged();
                page++;
            }

            @Override
            public void loadfail(String t) {
                mRecyclerView.setPullLoadMoreCompleted();
                Toast.makeText(activity,"暂无数据，返回码："+t,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void searchresult(Shop_Data shop_data) {

            }

            @Override
            public void severerr() {

            }
            @Override
            public void connecttimeout() {
                cl_try.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.GONE);
         //       Toast.makeText(activity,"连接超时，请检查您的网络",Toast.LENGTH_LONG).show();
                mRecyclerView.setPullLoadMoreCompleted();
            }

            @Override
            public void connectfail() {
                cl_try.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.GONE);
                mRecyclerView.setPullLoadMoreCompleted();
         //       Toast.makeText(activity,"连接失败，请检查您的网络",Toast.LENGTH_LONG);
            }
        });
        cl_try=findViewById(R.id.cl_try_again);
        cl_try.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getdata();
                cl_try.setVisibility(View.GONE);
            }
        });

        adapter=new Home_Re_Adapter(getContext(),myListData);
        mRecyclerView = findViewById(R.id.id_stickynavlayout_innerscrollview);
        mRecyclerView.setLinearLayout();
        mRecyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        mRecyclerView.setPullRefreshEnable(false);
        mRecyclerView.setPushRefreshEnable(true);
        mRecyclerView.setOnPullLoadMoreListener(new PullLoadMoreRecyclerView.PullLoadMoreListener() {
            @Override
            public void onRefresh() {
                Log.d("555","刷新更多");
            }

            @Override
            public void onLoadMore() {
                getdata();
                Log.d("555","加载更多");
            }
        });
        adapter.setOnItemClickListener(new Home_Re_Adapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                if (Constants.SELECT_JD){
                    Intent  intent=new Intent(activity, JD_Shop_Details_Activity.class);
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
    /**
     * 加载更多数据
     */
 public void getdata(){

        if (Constants.SELECT_JD){
            if (shoptype==0){
                url= Constants.URL+"shopList/sendJdData";
                String data="{\"pageStart\":\"%pageStart\"}";
                data=data.replace("%pageStart",String.valueOf(page));
                home_page_control.loadData(url,data);
            }else {
                url= Constants.URL+"shopList/findByTypePage";
                String data="{\"goodsType\":\"%type\",\"pageStart\":\"%pageStart\"}";
                data=data.replace("%pageStart",String.valueOf(page));
                data=data.replace("%type",String.valueOf(shoptype));
                home_page_control.loadData(url,data);
            }
        }else {
            String data="";
            if (shoptype==0){
                url= Constants.URL+"goodsSearch/recommend";
                data="{\"pageStart\":\"%pageStart\",\"pddPid\":\"10004_15554651\"}";
                data=data.replace("%pageStart",String.valueOf(page));
            }else {
                PDD_Shop_Bean pdd_shop_bean=new PDD_Shop_Bean(page,Constants.USERINFO.getPddpid());
                switch (shoptype){
                    case 1:{
                        pdd_shop_bean.setCatId(210);
                        break;
                    }
                    case 2:{
                        pdd_shop_bean.setCatId(239);
                        break;
                    }
                    case 3:{
                        pdd_shop_bean.setCatId(3817);
                        break;
                    }
                    case 4:{
                        pdd_shop_bean.setCatId(77);
                        break;
                    }
                    case 5:{
                        pdd_shop_bean.setCatId(1464);
                        break;
                    }
                    case 6:{
                        pdd_shop_bean.setCatId(2);
                        break;
                    }
                    case 7:{
                        pdd_shop_bean.setCatId(489);
                        break;
                    }
                    case 8:{
                        pdd_shop_bean.setCatId(277);
                        break;
                    }
                    case 9:{
                        pdd_shop_bean.setCatId(2351);
                        break;
                    }
                    case 10:{
                        pdd_shop_bean.setCatId(7639);
                        break;
                    }
                    case 11:{
                        pdd_shop_bean.setCatId(6076);
                        break;
                    }
                }
                data=new Gson().toJson(pdd_shop_bean);
                url= Constants.URL+"goodsSearch/selectCategory";
            }
            home_page_control.loadData(url,data);
        }
 }
    /**
     * 第一次创建页面时初始化数据
     */
 public void initdata(){

     String data="";
     if (Constants.SELECT_JD){
         Log.d("当前类型","京东"+shoptype);
         if (shoptype==0){
             url= Constants.URL+"shopList/sendJdData";
             data="{\"pageStart\":\"1\"}";
         }else {
             url= Constants.URL+"shopList/findByTypePage";
             data="{\"goodsType\":\"type\",\"pageStart\":\"1\"}";
             data=data.replace("type",String.valueOf(shoptype));
         }
     }else {
         Log.d("当前类型","拼多多"+shoptype);
         if (shoptype==0){
             url= Constants.URL+"goodsSearch/recommend";
             data="{\"pageStart\":\"1\",\"pddPid\":\"10004_15554651\"}";
         }else {
             PDD_Shop_Bean pdd_shop_bean=new PDD_Shop_Bean(1,Constants.USERINFO.getPddpid());
             switch (shoptype){
                 case 1:{
                     pdd_shop_bean.setCatId(210);
                     break;
                 }
                 case 2:{
                     pdd_shop_bean.setCatId(239);
                     break;
                 }
                 case 3:{
                     pdd_shop_bean.setCatId(3817);
                     break;
                 }
                 case 4:{
                     pdd_shop_bean.setCatId(77);
                     break;
                 }
                 case 5:{
                     pdd_shop_bean.setCatId(1464);
                     break;
                 }
                 case 6:{
                     pdd_shop_bean.setCatId(2);
                     break;
                 }
                 case 7:{
                     pdd_shop_bean.setCatId(489);
                     break;
                 }
                 case 8:{
                     pdd_shop_bean.setCatId(277);
                     break;
                 }
                 case 9:{
                     pdd_shop_bean.setCatId(2351);
                     break;
                 }
                 case 10:{
                     pdd_shop_bean.setCatId(7639);
                     break;
                 }
                 case 11:{
                     pdd_shop_bean.setCatId(6076);
                     break;
                 }
             }
             url= Constants.URL+"goodsSearch/selectCategory";
             data=new Gson().toJson(pdd_shop_bean);
         }
     }
     home_page_control.loadData(url,data);
 }
}
