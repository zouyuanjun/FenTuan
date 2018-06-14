package com.lejiaokeji.fentuan.view;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Color;
import android.media.Image;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.lejiaokeji.fentuan.R;
import com.lejiaokeji.fentuan.activity.Search_Activity;
import com.lejiaokeji.fentuan.view.helpview.ColorTrackTabLayout;
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


public class HomeFragment extends LazyLoadFragment implements View.OnClickListener{
    private String[] mTitles = new String[]{"推   荐", "女装", "男装","内衣配饰","母婴玩具", "美妆个护", "食品保健","居家生活", "鞋品箱包", "运动户外", "文体车品", "数码家电"};
    private SimpleViewPagerIndicator mIndicator;
    private ViewPager mViewPager;
    private FragmentPagerAdapter mAdapter;
    private HomeTabFragment[] mFragments = new HomeTabFragment[mTitles.length];
    RelativeLayout rl_select_jd;
    List<String> images=new ArrayList<>();
    List<String> titles=new ArrayList<>();
    ColorTrackTabLayout tabLayout;
    ImageView select_jd;
    ImageView select_pdd;
    TextView tv_select_jd;
    TextView tv_select_pdd;
    TextView tv_recommend;
    ImageView im_hide;
    LinearLayout relativeLayout;
    EditText ed_search;
    boolean idshow=false;
    int page=1;
    TextView tv_type_3;
    TextView tv_type_4;
    TextView tv_type_5;
    TextView tv_type_6;
    TextView tv_type_7;
    TextView tv_type_8;
    TextView tv_type_9;
    TextView tv_type_10;
    TextView tv_type_11;
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            "android.permission.READ_EXTERNAL_STORAGE",
            "android.permission.WRITE_EXTERNAL_STORAGE" };

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
            @Override
            public int getItemPosition(Object object) {
                return POSITION_NONE;
            }
        };

        mViewPager.setAdapter(mAdapter);
        mViewPager.setCurrentItem(0);
    }
    private void initViews() {
        verifyStoragePermissions(getActivity());
        tv_type_3=findViewById(R.id.tv_type_3);
        tv_type_3.setOnClickListener(this);
        tv_type_4=findViewById(R.id.tv_type_4);
        tv_type_4.setOnClickListener(this);
        tv_type_5=findViewById(R.id.tv_type_5);
        tv_type_5.setOnClickListener(this);
        tv_type_6=findViewById(R.id.tv_type_6);
        tv_type_6.setOnClickListener(this);
        tv_type_7=findViewById(R.id.tv_type_7);
        tv_type_7.setOnClickListener(this);
        tv_type_8=findViewById(R.id.tv_type_8);
        tv_type_8.setOnClickListener(this);
        tv_type_9=findViewById(R.id.tv_type_9);
        tv_type_9.setOnClickListener(this);
        tv_type_10=findViewById(R.id.tv_type_10);
        tv_type_10.setOnClickListener(this);
        tv_type_11=findViewById(R.id.tv_type_11);
        tv_type_11.setOnClickListener(this);
        select_jd=findViewById(R.id.img_select_jd);
        select_pdd=findViewById(R.id.img_select_pdd);
        tv_select_jd=findViewById(R.id.tv_select_jd);
        tv_select_jd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                select_pdd.setVisibility(View.GONE);
                select_jd.setVisibility(View.VISIBLE);
                Constants.SELECT_JD=true;
                rl_select_jd.setBackground(ContextCompat.getDrawable(getActivity(),R.drawable.home_bg_jd));
                int pageindex=mViewPager.getCurrentItem();
                mViewPager.setCurrentItem(pageindex);
                mViewPager.getAdapter().notifyDataSetChanged();
            }
        });
        tv_select_pdd=findViewById(R.id.tv_select_pingduoduo);
        tv_select_pdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Constants.SELECT_JD=false;
                rl_select_jd.setBackground(ContextCompat.getDrawable(getActivity(),R.drawable.home_bg_pdd));
                select_pdd.setVisibility(View.VISIBLE);
                select_jd.setVisibility(View.GONE);
                int pageindex=mViewPager.getCurrentItem();
             mViewPager.setCurrentItem(pageindex);
                mViewPager.getAdapter().notifyDataSetChanged();
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
        rl_select_jd=findViewById(R.id.rl_jd_selcet);
        tabLayout=findViewById(R.id.mytablayout);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        tabLayout.setTabPaddingLeftAndRight(15,15);
        mViewPager = findViewById(R.id.id_stickynavlayout_viewpager);

        mIndicator =  findViewById(R.id.id_stickynavlayout_indicator);
        tv_recommend=findViewById(R.id.tv_recomment);
        tv_recommend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("sd","选中推荐");
                mViewPager.setCurrentItem(0);
                tv_recommend.setTextColor(Color.parseColor("#ff2d55"));
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
                if (tab.getPosition() > 0) {
                    tv_recommend.setTextColor(Color.parseColor("#000000"));
                } else {
                    tv_recommend.setTextColor(Color.parseColor("#ff2d55"));
                }
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

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        ed_search=findViewById(R.id.ed_home_search);
        ed_search.setFocusable(false);
        ed_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), Search_Activity.class);
                getActivity().startActivity(intent);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.tv_type_3:{
                mViewPager.setCurrentItem(3);
                idshow=false;
                relativeLayout.setVisibility(View.GONE);
                break;
            }
            case R.id.tv_type_4:{
                mViewPager.setCurrentItem(4);
                idshow=false;
                relativeLayout.setVisibility(View.GONE);
                break;
            }
            case R.id.tv_type_5:{
                mViewPager.setCurrentItem(5);
                idshow=false;
                relativeLayout.setVisibility(View.GONE);
                break;
            }
            case R.id.tv_type_6:{
                mViewPager.setCurrentItem(6);
                idshow=false;
                relativeLayout.setVisibility(View.GONE);
                break;
            }
            case R.id.tv_type_7:{
                mViewPager.setCurrentItem(7);
                idshow=false;
                relativeLayout.setVisibility(View.GONE);
                break;
            }
            case R.id.tv_type_8:{
                mViewPager.setCurrentItem(8);
                idshow=false;
                relativeLayout.setVisibility(View.GONE);
                break;
            }
            case R.id.tv_type_9:{
                mViewPager.setCurrentItem(9);
                idshow=false;
                relativeLayout.setVisibility(View.GONE);
                break;
            }
            case R.id.tv_type_10:{
                mViewPager.setCurrentItem(10);
                idshow=false;
                relativeLayout.setVisibility(View.GONE);
                break;
            }
            case R.id.tv_type_11:{
                mViewPager.setCurrentItem(11);
                idshow=false;
                relativeLayout.setVisibility(View.GONE);
                break;

            }
        }

    }

    public static void verifyStoragePermissions(Activity activity) {

        try {
            //检测是否有写的权限
            int permission = ActivityCompat.checkSelfPermission(activity,
                    "android.permission.WRITE_EXTERNAL_STORAGE");
            if (permission != PackageManager.PERMISSION_GRANTED) {
                // 没有写的权限，去申请写的权限，会弹出对话框
                ActivityCompat.requestPermissions(activity, PERMISSIONS_STORAGE,REQUEST_EXTERNAL_STORAGE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}