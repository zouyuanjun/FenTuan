package com.lejiaokeji.fentuan;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.lejiaokeji.fentuan.adapter.ViewPagerAdapter;
import com.lejiaokeji.fentuan.view.HomeFragment;
import com.lejiaokeji.fentuan.view.dummy.DummyContent;

import java.util.ArrayList;
import java.util.List;

import me.majiajie.pagerbottomtabstrip.MaterialMode;
import me.majiajie.pagerbottomtabstrip.NavigationController;
import me.majiajie.pagerbottomtabstrip.PageNavigationView;
import me.majiajie.pagerbottomtabstrip.listener.OnTabItemSelectedListener;

public class MainActivity extends AppCompatActivity implements HomeFragment.OnListFragmentInteractionListener{
    ViewPager viewPager;
    private List<Fragment> fragmentList=new ArrayList<>();
    NavigationController mNavigationController;
    PageNavigationView pageBottomTabLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager=findViewById(R.id.myviewpage);
        pageBottomTabLayout = (PageNavigationView) findViewById(R.id.tab);
        mNavigationController=pageBottomTabLayout.material().addItem(R.drawable.pfile_ic_portrait,"首页")
                .addItem(R.drawable.pfile_ic_portrait,"推荐")
                .addItem(R.drawable.pfile_ic_portrait,"商城")
                .addItem(R.drawable.pfile_ic_portrait,"通知")
                .addItem(R.drawable.pfile_ic_portrait,"我的")
        .setDefaultColor(0x89000000)
        .setMode(MaterialMode.CHANGE_BACKGROUND_COLOR)
        .build();
        initview();
    }
    private  void initview(){

        fragmentList.add(HomeFragment.newInstance(5));
        fragmentList.add(HomeFragment.newInstance(5));
        fragmentList.add(HomeFragment.newInstance(5));
        fragmentList.add(HomeFragment.newInstance(5));
        fragmentList.add(HomeFragment.newInstance(5));
        viewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager(),fragmentList));
        //自动适配ViewPager页面切换
        mNavigationController.setupWithViewPager(viewPager);

        //也可以设置Item选中事件的监听
        mNavigationController.addTabItemSelectedListener(new OnTabItemSelectedListener() {
            @Override
            public void onSelected(int index, int old) {
                Log.i("asd","selected: " + index + " old: " + old);
            }

            @Override
            public void onRepeat(int index) {
                Log.i("asd","onRepeat selected: " + index);
            }
        });
    }

    @Override
    public void onListFragmentInteraction(DummyContent.DummyItem item) {

    }
}
