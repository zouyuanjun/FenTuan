package com.lejiaokeji.fentuan;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.lejiaokeji.fentuan.activity.BaseActivity;
import com.lejiaokeji.fentuan.adapter.ViewPagerAdapter;
import com.lejiaokeji.fentuan.view.HomeFragment;
import com.lejiaokeji.fentuan.view.RecommendFragment;
import com.lejiaokeji.fentuan.view.helpview.OnlyIconItemView;
import com.lejiaokeji.fentuan.view.NotificationFragment;
import com.lejiaokeji.fentuan.view.UserCentreFragment;

import java.util.ArrayList;
import java.util.List;

import me.majiajie.pagerbottomtabstrip.NavigationController;
import me.majiajie.pagerbottomtabstrip.PageNavigationView;
import me.majiajie.pagerbottomtabstrip.item.BaseTabItem;
import me.majiajie.pagerbottomtabstrip.listener.OnTabItemSelectedListener;

public class MainActivity extends BaseActivity {
    ViewPager viewPager;
    Context context;
    Activity activity;
    private List<Fragment> fragmentList=new ArrayList<>();
    NavigationController mNavigationController;
    PageNavigationView pageBottomTabLayout;
    Window window;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity=this;
        Fresco.initialize(this);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window = activity.getWindow();
//设置透明状态栏,这样才能让 ContentView 向上
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//需要设置这个 flag 才能调用 setStatusBarColor 来设置状态栏颜色
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//设置状态栏颜色
            ViewGroup mContentView = (ViewGroup) activity.findViewById(Window.ID_ANDROID_CONTENT);
            View mChildView = mContentView.getChildAt(0);
            if (mChildView != null) {
                //注意不是设置 ContentView 的 FitsSystemWindows, 而是设置 ContentView 的第一个子 View . 使其不为系统 View 预留空间.
                ViewCompat.setFitsSystemWindows(mChildView, false);
            }
        }
        setContentView(R.layout.activity_main);
        viewPager=findViewById(R.id.myviewpage);

        context=this;
        pageBottomTabLayout = (PageNavigationView) findViewById(R.id.tab);
        mNavigationController = pageBottomTabLayout.custom()
                .addItem(newItem(R.drawable.tab_ic_home_def,R.drawable.tab_ic_home_sel))
                .addItem(newItem(R.drawable.tab_ic_recom_def,R.drawable.tab_ic_recom_sel))
//  a              .addItem(newItem(R.drawable.tab_ic_mall_def,R.drawable.tab_ic_mall_sel))
                .addItem(newItem(R.drawable.tab_ic_inform_def,R.drawable.tab_ic_inform_sel))
                .addItem(newItem(R.drawable.tab_ic_my_def,R.drawable.tab_ic_my_sel))
                .build();
        initview();
    }
    private  void initview() {

        fragmentList.add(new HomeFragment());
        fragmentList.add(new RecommendFragment());
//        fragmentList.add(new Store_Fragment());
        fragmentList.add(new NotificationFragment());
        fragmentList.add(new UserCentreFragment());
        viewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager(), fragmentList));
        //自动适配ViewPager页面切换
        mNavigationController.setupWithViewPager(viewPager);

        //也可以设置Item选中事件的监听
        mNavigationController.addTabItemSelectedListener(new OnTabItemSelectedListener() {
            @Override
            public void onSelected(int index, int old) {

               if (index>0){
                   if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                       window.setStatusBarColor(Color.parseColor("#ff286e"));
                   }

               }else {
                   if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                       window.setStatusBarColor(Color.parseColor("#b2333333"));
                   }
               }
            }

            @Override
            public void onRepeat(int index) {
                Log.i("asd", "onRepeat selected: " + index);
            }
        });
    }
    private BaseTabItem newItem(int drawable, int checkedDrawable){
        OnlyIconItemView onlyIconItemView = new OnlyIconItemView(this);
        onlyIconItemView.initialize(drawable,checkedDrawable);
        return onlyIconItemView;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("mainactivity","sa阀手动阀a");
    }
}
