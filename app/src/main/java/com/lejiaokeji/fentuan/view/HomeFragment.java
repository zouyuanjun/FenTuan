package com.lejiaokeji.fentuan.view;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.lejiaokeji.fentuan.R;
import com.lejiaokeji.fentuan.view.helpview.GlideImageLoader;
import com.lejiaokeji.fentuan.view.helpview.LazyLoadFragment;
import com.lejiaokeji.fentuan.view.helpview.OnlyTextTab;
import com.lejiaokeji.fentuan.view.helpview.SimpleViewPagerIndicator;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends LazyLoadFragment {
    private String[] mTitles = new String[]{"简介", "评价", "相关","简介","简介", "评价", "相关","简介", "评价", "相关"};
    private SimpleViewPagerIndicator mIndicator;
    private ViewPager mViewPager;
    private FragmentPagerAdapter mAdapter;
    private HomeTabFragment[] mFragments = new HomeTabFragment[mTitles.length];
    List<String> images=new ArrayList<>();
    List<String> titles=new ArrayList<>();
    TabLayout tabLayout;
    @Override
    protected int setContentView() {
        return R.layout.fragment_home;
    }

    @Override
    protected void lazyLoad() {
        images.clear();
        images.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1525760827184&di=7e2ab5aae471045c19b44966e1e6327b&imgtype=0&src=http%3A%2F%2Fpic2.ooopic.com%2F11%2F44%2F96%2F87b3OOOPICd4.jpg");
        images.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1525760827184&di=4ab59126e04b1afcacf93ff942c9c4f4&imgtype=0&src=http%3A%2F%2Fimgsrc.baidu.com%2Fimage%2Fc0%253Dpixel_huitu%252C0%252C0%252C294%252C40%2Fsign%3D550123260c0828387c00d454d1e1cc6d%2F42166d224f4a20a4345db4fe9b529822720ed04c.jpg");
        images.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1525760827180&di=e13fd305b2ae85660894c6f4fc65ce2e&imgtype=0&src=http%3A%2F%2Fimgsrc.baidu.com%2Fimage%2Fc0%253Dshijue1%252C0%252C0%252C294%252C40%2Fsign%3D9318f631b3389b502cf2e811ed5c8fa8%2F4ec2d5628535e5dd42d849f07cc6a7efce1b622d.jpg");
        images.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1525760827178&di=6fbd340ce8951b896105949980c46e7f&imgtype=0&src=http%3A%2F%2Fimgsrc.baidu.com%2Fimage%2Fc0%253Dshijue1%252C0%252C0%252C294%252C40%2Fsign%3D852d136481d4b31ce4319cf8efbf4d0a%2F8601a18b87d6277f51ef3a1f22381f30e924fc1c.jpg");
        images.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1525760827177&di=6ce6d7f037e62c9af27b0c9003cf9d92&imgtype=0&src=http%3A%2F%2Fimg.zcool.cn%2Fcommunity%2F0135c656fb290d6ac7257948dd18af.jpg%402o.jpg");
        titles.clear();
        titles.add("哇哈哈");
        titles.add("AD钙");
        titles.add("噜啦啦");
        titles.add("啊啊啊");
        titles.add("阿西吧");
        Banner banner = (Banner) findViewById(R.id.banner);
        //设置banner样式
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE);
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        banner.setImages(images);
        //设置banner动画效果
        banner.setBannerAnimation(Transformer.DepthPage);
        //设置标题集合（当banner样式有显示title时）
        banner.setBannerTitles(titles);
        //设置自动轮播，默认为true
        banner.isAutoPlay(true);
        //设置轮播时间
        banner.setDelayTime(2500);
        //设置指示器位置（当banner模式中有指示器时）
        banner.setIndicatorGravity(BannerConfig.CENTER);
        //banner设置方法全部调用完毕时最后调用
        banner.start();
        initViews();
        initDatas();
    }
    //设置viewpage adapter
    private void initDatas() {
        mIndicator.setTitles(mTitles);

        for (int i = 0; i < mTitles.length; i++) {
            mFragments[i] = (HomeTabFragment) HomeTabFragment.newInstance(mTitles[i]);
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
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);

        mViewPager = findViewById(R.id.id_stickynavlayout_viewpager);

        mIndicator =  findViewById(R.id.id_stickynavlayout_indicator);
       // tabLayout.setupWithViewPager(mViewPager);
        tabLayout.addTab(tabLayout.newTab().setText(mTitles[0]));
        tabLayout.addTab(tabLayout.newTab().setText(mTitles[1]));
        tabLayout.addTab(tabLayout.newTab().setText(mTitles[2]));
        tabLayout.addTab(tabLayout.newTab().setText(mTitles[3]));
        tabLayout.addTab(tabLayout.newTab().setText(mTitles[4]));
        tabLayout.addTab(tabLayout.newTab().setText(mTitles[5]));
        tabLayout.addTab(tabLayout.newTab().setText(mTitles[6]));
        tabLayout.addTab(tabLayout.newTab().setText(mTitles[8]));
        tabLayout.addTab(tabLayout.newTab().setText(mTitles[9]));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mViewPager.setCurrentItem(tab.getPosition()+1);
                Log.d("555","第一次选择");
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                Log.d("555","第二次选择");
            }
        });
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                Log.d("555","viewpage position"+position+"positionOffset"+positionOffset);
                if (position>0){
                    tabLayout.setScrollPosition(position-1,positionOffset,true);
                }

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }
}