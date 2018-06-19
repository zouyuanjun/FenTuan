package com.lejiaokeji.fentuan.view;

import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.lejiaokeji.fentuan.R;
import com.lejiaokeji.fentuan.view.helpview.GlideImageLoader;
import com.lejiaokeji.fentuan.view.helpview.LazyLoadFragment;
import com.lejiaokeji.fentuan.view.helpview.SimpleViewPagerIndicator;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import java.util.ArrayList;
import java.util.List;


    public class RecommendFragment extends LazyLoadFragment {
        private String[] mTitles = new String[]{"商品推荐", "营销素材", "新手必发"};
        private SimpleViewPagerIndicator mIndicator;
        LinearLayout linearLayout;
        private ViewPager mViewPager;
        private FragmentPagerAdapter mAdapter;
        private RecommendTabFragment[] mFragments = new RecommendTabFragment[mTitles.length];
        TabLayout tabLayout;
        @Override
        protected int setContentView() {
            return R.layout.fragment_recommend;
        }

        @Override
        protected void lazyLoad() {
            initViews();
            initDatas();
        }
        //设置viewpage adapter
        private void initDatas() {
            mIndicator.setTitles(mTitles);

            for (int i = 0; i < mTitles.length; i++) {
                mFragments[i] = (RecommendTabFragment) RecommendTabFragment.newInstance(mTitles[i]);
            }
            mAdapter = new FragmentPagerAdapter(this.getChildFragmentManager()) {
                @Override
                public int getCount() {
                    return mTitles.length;
                }

                @Override
                public Fragment getItem(int position) {
                    return mFragments[position];
                }

                @Nullable
                @Override
                public CharSequence getPageTitle(int position) {
                    return mTitles[position];
                }

                @Override
                public void destroyItem(ViewGroup container, int position, Object object) {
                    super.destroyItem(container, position, object);

                }
            };

            mViewPager.setAdapter(mAdapter);
            mViewPager.setCurrentItem(0);
        }

        private void initViews() {
            tabLayout=findViewById(R.id.mytablayout);
            mViewPager = findViewById(R.id.id_stickynavlayout_viewpager);
            linearLayout=findViewById(R.id.ly_tab);
            mIndicator =  findViewById(R.id.id_stickynavlayout_indicator);
            tabLayout.addTab(tabLayout.newTab().setText(mTitles[0]));
            tabLayout.addTab(tabLayout.newTab().setText(mTitles[1]));
            tabLayout.addTab(tabLayout.newTab().setText(mTitles[2]));
            tabLayout.setupWithViewPager(mViewPager);
            tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                @Override
                public void onTabSelected(TabLayout.Tab tab) {
                    if (tab.getPosition()==1){
                        linearLayout.setBackground(ContextCompat.getDrawable(getActivity(),R.drawable.recom_bg_01));
                    }else if (tab.getPosition()==2){
                        linearLayout.setBackground(ContextCompat.getDrawable(getActivity(),R.drawable.recom_bg_02));
                    }else if (tab.getPosition()==3){
                        linearLayout.setBackground(ContextCompat.getDrawable(getActivity(),R.drawable.recom_bg_03));
                    }
                }

                @Override
                public void onTabUnselected(TabLayout.Tab tab) {

                }

                @Override
                public void onTabReselected(TabLayout.Tab tab) {

                }
            });

        }
    }
