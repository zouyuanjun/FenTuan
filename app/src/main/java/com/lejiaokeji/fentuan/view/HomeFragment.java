package com.lejiaokeji.fentuan.view;
import android.content.res.Resources;
import android.media.Image;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lejiaokeji.fentuan.R;
import com.lejiaokeji.fentuan.view.helpview.GlideImageLoader;
import com.lejiaokeji.fentuan.view.helpview.LazyLoadFragment;
import com.lejiaokeji.fentuan.view.helpview.OnlyTextTab;
import com.lejiaokeji.fentuan.view.helpview.SimpleViewPagerIndicator;
import com.lejiaokeji.fentuan.wxapi.Constants;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends LazyLoadFragment {
    private String[] mTitles = new String[]{"推荐", "女装", "男装","内衣配饰","母婴玩具", "美妆个护", "食品保健","居家生活", "鞋品箱包", "运动户外", "文体车品", "数码家电"};
    private SimpleViewPagerIndicator mIndicator;
    private ViewPager mViewPager;
    private FragmentPagerAdapter mAdapter;
    private HomeTabFragment[] mFragments = new HomeTabFragment[mTitles.length];
    List<String> images=new ArrayList<>();
    List<String> titles=new ArrayList<>();
    TabLayout tabLayout;
    ImageView select_jd;
    ImageView select_pdd;
    TextView tv_select_jd;
    TextView tv_select_pdd;
    TextView tv_recommend;
    ImageView im_hide;
    RelativeLayout relativeLayout;
    boolean idshow=false;
    int page=1;
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
            mFragments[i] = (HomeTabFragment) HomeTabFragment.newInstance(i);
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
        select_jd=findViewById(R.id.img_select_jd);
        select_pdd=findViewById(R.id.img_select_pdd);
        tv_select_jd=findViewById(R.id.tv_select_jd);
        tv_select_jd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                select_pdd.setVisibility(View.GONE);
                select_jd.setVisibility(View.VISIBLE);
                Constants.SELECT_JD=true;
            }
        });
        tv_select_pdd=findViewById(R.id.tv_select_pingduoduo);
        tv_select_pdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Constants.SELECT_JD=false;
                select_pdd.setVisibility(View.VISIBLE);
                select_jd.setVisibility(View.GONE);
            }
        });
        im_hide=findViewById(R.id.im_hide);
        relativeLayout=findViewById(R.id.relative);

        im_hide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (idshow){
                    relativeLayout.setVisibility(View.GONE);
                    idshow=false;
                    Log.d("55","隐藏");
                }else {
                    relativeLayout.setVisibility(View.VISIBLE);
                    idshow=true;
                }
            }
        });
        tabLayout=findViewById(R.id.mytablayout);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);

        mViewPager = findViewById(R.id.id_stickynavlayout_viewpager);

        mIndicator =  findViewById(R.id.id_stickynavlayout_indicator);
        tv_recommend=findViewById(R.id.tv_recomment);
        tv_recommend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("sd","选中推荐");
                mViewPager.setCurrentItem(0);
            }
        });
        tabLayout.setupWithViewPager(mViewPager);
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
//                mViewPager.setCurrentItem(tab.getPosition()+1);
//                Log.d("555","第一次选择");
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
//                Log.d("555","viewpage position"+position+"positionOffset"+positionOffset);
//                if (position>0){
//                    tabLayout.setScrollPosition(position-1,positionOffset,true);
//                }

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        try {
            //拿到tabLayout的mTabStrip属性
            Field mTabStripField = tabLayout.getClass().getDeclaredField("mTabStrip");
            mTabStripField.setAccessible(true);

            LinearLayout mTabStrip = (LinearLayout) mTabStripField.get(tabLayout);

            int dp10 = (int) dip2px(10);

            for (int i = 0; i < mTabStrip.getChildCount(); i++) {
                View tabView = mTabStrip.getChildAt(i);

                //拿到tabView的mTextView属性
                Field mTextViewField = tabView.getClass().getDeclaredField("mTextView");
                mTextViewField.setAccessible(true);

                TextView mTextView = (TextView) mTextViewField.get(tabView);

                tabView.setPadding(0, 0, 0, 0);

                //因为我想要的效果是   字多宽线就多宽，所以测量mTextView的宽度
                int width = 0;
                width = mTextView.getWidth();
                if (width == 0) {
                    mTextView.measure(0, 0);
                    width = mTextView.getMeasuredWidth();
                }
                //设置tab左右间距为10dp  注意这里不能使用Padding 因为源码中线的宽度是根据 tabView的宽度来设置的
                LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) tabView.getLayoutParams();
                params.width = width ;
                params.leftMargin = dp10;
                params.rightMargin = dp10;
                tabView.setLayoutParams(params);

                tabView.invalidate();
            }

        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }
    public static float dip2px(float dipValue)
    {
        final float scale = Resources.getSystem().getDisplayMetrics().density;
        return  (dipValue * scale + 0.5f);
    }

}