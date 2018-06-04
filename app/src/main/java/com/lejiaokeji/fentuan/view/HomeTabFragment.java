package com.lejiaokeji.fentuan.view;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.lejiaokeji.fentuan.R;
import com.lejiaokeji.fentuan.adapter.Home_Re_Adapter;
import com.lejiaokeji.fentuan.control.Home_Page_Control;
import com.lejiaokeji.fentuan.databean.Item_Shop;
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
    // private TextView mTextView;
    List<Item_Shop> myListData= new ArrayList<Item_Shop>();
    Home_Re_Adapter adapter;
    Home_Page_Control home_page_control;
     int  shoptype=0;
     int page=2;
    String url=Constants.URL+"shopList/recommended";
    Activity activity;
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
        int page=2;
        myListData.clear();
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity=getActivity();
        if (getArguments() != null) {
            mTitle = getArguments().getInt(TITLE);
            shoptype=mTitle;
            Log.d("当前标签","haha "+mTitle);
        }
    }
    @Override
    protected int setContentView() {
        return R.layout.fragment_home_tab;
    }
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

    }
    @Override
    protected void lazyLoad() {
        Log.d("当前类型",""+shoptype);
        String data="";
        if (shoptype==0){
          url= Constants.URL+"shopList/sendJdData";
            data="{\"pageStart\":\"1\"}";
        }else {
            url= Constants.URL+"shopList/findByTypePage";
            data="{\"goodsType\":\"type\",\"pageStart\":\"1\"}";
            data=data.replace("type",String.valueOf(shoptype));
        }

        home_page_control=Home_Page_Control.getInstance();
        home_page_control.loadData(url,data);
        home_page_control.setlistener(new Home_Page_Control.Home_Page_Listener() {


            @Override
            public void loadsuccefful(Shop_Data shop_data) {
                for (Item_Shop item_shop:shop_data.getData())
                myListData.add(item_shop);
                mRecyclerView.setPullLoadMoreCompleted();
                adapter.notifyDataSetChanged();
                page++;
            }

            @Override
            public void loadfail(String t) {
                mRecyclerView.setPullLoadMoreCompleted();

                Toast.makeText(activity,"请求错误，错误码："+t,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void fistlogin() {

            }

            @Override
            public void severerr() {

            }

            @Override
            public void connecttimeout() {
                Log.d("555","请求超时");
                Toast.makeText(activity,"连接超时，请检查您的网络",Toast.LENGTH_LONG).show();
            }

            @Override
            public void connectfail() {
                Toast.makeText(activity,"连接失败，请检查您的网络",Toast.LENGTH_LONG);
            }
        });
        adapter=new Home_Re_Adapter(getContext(),myListData);
        mRecyclerView = findViewById(R.id.id_stickynavlayout_innerscrollview);
        mRecyclerView.setLinearLayout();
        mRecyclerView.setAdapter(adapter);
        Log.d("55555","内标签正在渲染");

        // mTextView = (TextView) view.findViewById(R.id.id_info);
        // mTextView.setText(mTitle);
        adapter.notifyDataSetChanged();
        mRecyclerView.setPullRefreshEnable(true);
        mRecyclerView.setPushRefreshEnable(true);
   //     getdata();
        mRecyclerView.setOnPullLoadMoreListener(new PullLoadMoreRecyclerView.PullLoadMoreListener() {
            @Override
            public void onRefresh() {
                getdata();
                mRecyclerView.setPullLoadMoreCompleted();
                adapter.notifyDataSetChanged();
                page++;
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

            }
            @Override
            public void onItemLongClick(View view, int position) {
            }
        });
    }
 public void getdata(){
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
 }

}
