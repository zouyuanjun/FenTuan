package com.lejiaokeji.fentuan.view;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.lejiaokeji.fentuan.R;
import com.lejiaokeji.fentuan.adapter.Home_Re_Adapter;
import com.lejiaokeji.fentuan.control.Home_Page_Control;
import com.lejiaokeji.fentuan.databean.Item_Shop;
import com.lejiaokeji.fentuan.view.helpview.LazyLoadFragment;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

import java.util.ArrayList;
import java.util.List;

public class HomeTabFragment extends LazyLoadFragment {
    public static final String TITLE = "title";
    private String mTitle = "Defaut Value";
    private PullLoadMoreRecyclerView mRecyclerView;
    // private TextView mTextView;
    List<Item_Shop> myListData= new ArrayList<Item_Shop>();
    Home_Re_Adapter adapter;
    Home_Page_Control home_page_control;

    public static HomeTabFragment newInstance(String title) {
        HomeTabFragment tabFragment = new HomeTabFragment();
        Bundle bundle = new Bundle();
        bundle.putString(TITLE, title);
        tabFragment.setArguments(bundle);
        return tabFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mTitle = getArguments().getString(TITLE);
            Log.d("当前标签","haha "+mTitle);
        }
    }

    @Override
    protected int setContentView() {
        return R.layout.fragment_tab;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

    }

    @Override
    protected void lazyLoad() {
        home_page_control=Home_Page_Control.getInstance();
        home_page_control.setlistener(new Home_Page_Control.Home_Page_Listener() {
            @Override
            public void loadsuccefful() {

            }

            @Override
            public void signfail(String t) {

            }

            @Override
            public void fistlogin() {

            }

            @Override
            public void severerr() {

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
        getdata();
        mRecyclerView.setOnPullLoadMoreListener(new PullLoadMoreRecyclerView.PullLoadMoreListener() {
            @Override
            public void onRefresh() {
                getdata();
                mRecyclerView.setPullLoadMoreCompleted();
                adapter.notifyDataSetChanged();
                Log.d("555","刷新更多");
            }

            @Override
            public void onLoadMore() {
             //   getdata();
                mRecyclerView.setPullLoadMoreCompleted();
                adapter.notifyDataSetChanged();
                Log.d("555","加载更多");
            }
        });
    }
 public void getdata(){
     for (int i = 0; i < 10; i++) {
         String url="http://img.zcool.cn/community/01635d571ed29832f875a3994c7836.png@900w_1l_2o_100sh.jpg";
         myListData.add(new Item_Shop(i,url,"哇哈哈哈"+i,url,"52","10",url,25));
     }
 }

}
