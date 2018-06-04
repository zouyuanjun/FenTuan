package com.lejiaokeji.fentuan.view;

import android.os.Bundle;
import android.support.v7.view.ActionMode;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;

import com.lejiaokeji.fentuan.R;
import com.lejiaokeji.fentuan.adapter.Home_Re_Adapter;
import com.lejiaokeji.fentuan.adapter.Recommend_Rv_Adapter;
import com.lejiaokeji.fentuan.control.Home_Page_Control;
import com.lejiaokeji.fentuan.databean.Item_Shop;
import com.lejiaokeji.fentuan.databean.Item_Shop_RecommentBean;
import com.lejiaokeji.fentuan.databean.Shop_Data;
import com.lejiaokeji.fentuan.view.helpview.LazyLoadFragment;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RecommendTabFragment extends LazyLoadFragment {
    public static final String TITLE = "title";
    private String mTitle = "Defaut Value";
    private PullLoadMoreRecyclerView mRecyclerView;
    // private TextView mTextView;
    List<Item_Shop_RecommentBean> myListData = new ArrayList<Item_Shop_RecommentBean>();
    Recommend_Rv_Adapter adapter;
    Home_Page_Control home_page_control;
    private ActionMode actionMode;
    public Set<Integer> positionSet = new HashSet<>();

    public static RecommendTabFragment newInstance(String title) {
        RecommendTabFragment tabFragment = new RecommendTabFragment();
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
        return R.layout.fragment_home_tab;
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
            public void loadsuccefful(Shop_Data shop_data) {

            }

            @Override
            public void loadfail(String t) {

            }

            @Override
            public void fistlogin() {

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
        adapter=new Recommend_Rv_Adapter(getContext(),myListData);
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
        adapter.setOnItemClickListener(new Recommend_Rv_Adapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

            }
            @Override
            public void onItemLongClick(View view, int position) {

            }
        });
    }
    public void getdata(){
        for (int i = 0; i < 10; i++) {
            String url="http://img.zcool.cn/community/01635d571ed29832f875a3994c7836.png@900w_1l_2o_100sh.jpg";
            myListData.add(new Item_Shop_RecommentBean(url,"不才","2小时前"+i,"已分享52次",url,url,url,"25",url,"按实际学生考试地点"));
        }
    }


}
