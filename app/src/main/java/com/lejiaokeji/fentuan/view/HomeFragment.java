package com.lejiaokeji.fentuan.view;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lejiaokeji.fentuan.R;
import com.lejiaokeji.fentuan.adapter.Home_Re_Adapter;
import com.lejiaokeji.fentuan.view.dummy.DummyContent;
import com.lejiaokeji.fentuan.view.dummy.DummyContent.DummyItem;

import java.util.ArrayList;
import java.util.List;

import me.majiajie.pagerbottomtabstrip.MaterialMode;
import me.majiajie.pagerbottomtabstrip.NavigationController;
import me.majiajie.pagerbottomtabstrip.PageNavigationView;

public class HomeFragment extends Fragment {
    private String[] mTitles = new String[]{"简介", "评价", "相关"};
    private SimpleViewPagerIndicator mIndicator;
    private ViewPager mViewPager;
    private FragmentPagerAdapter mAdapter;
    private TabFragment[] mFragments = new TabFragment[mTitles.length];
    View view;
    NavigationController mNavigationController;
    PageNavigationView pageBottomTabLayout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);
        initViews();
        initDatas();
        initEvents();
        return view;
    }

    private void initEvents() {
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
            }

            @Override
            public void onPageScrolled(int position, float positionOffset,
                                       int positionOffsetPixels) {
                mIndicator.scroll(position, positionOffset);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }
    private void initDatas() {
        mIndicator.setTitles(mTitles);

        for (int i = 0; i < mTitles.length; i++) {
            mFragments[i] = (TabFragment) TabFragment.newInstance(mTitles[i]);
        }
        mAdapter = new FragmentPagerAdapter(getActivity().getSupportFragmentManager()) {
            @Override
            public int getCount() {
                return mTitles.length;
            }

            @Override
            public Fragment getItem(int position) {
                return mFragments[position];
            }

        };

        mViewPager.setAdapter(mAdapter);
        mViewPager.setCurrentItem(0);
    }

    private void initViews() {
        mIndicator = (SimpleViewPagerIndicator) view.findViewById(R.id.id_stickynavlayout_indicator);
        mViewPager = (ViewPager) view.findViewById(R.id.id_stickynavlayout_viewpager);
        pageBottomTabLayout = (PageNavigationView)view.findViewById(R.id.intab);
        mNavigationController=pageBottomTabLayout.material().addItem(R.drawable.pfile_ic_portrait,"首页")
                .addItem(R.drawable.pfile_ic_portrait,"推荐")
                .addItem(R.drawable.pfile_ic_portrait,"商城")
                .setDefaultColor(0x89000000)
                .setMode(MaterialMode.CHANGE_BACKGROUND_COLOR)
                .build();
        mNavigationController.setupWithViewPager(mViewPager);
		/*
		RelativeLayout ll = (RelativeLayout) findViewById(R.id.id_stickynavlayout_topview);
		TextView tv = new TextView(this);
		tv.setText("我的动态添加的");
		tv.setBackgroundColor(0x77ff0000);
		ll.addView(tv, new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.MATCH_PARENT, 600));
		*/
    }


}