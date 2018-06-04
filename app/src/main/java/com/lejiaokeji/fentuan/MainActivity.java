package com.lejiaokeji.fentuan;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.lejiaokeji.fentuan.adapter.ViewPagerAdapter;
import com.lejiaokeji.fentuan.view.HomeFragment;
import com.lejiaokeji.fentuan.view.notification.NotificationFragment;
import com.lejiaokeji.fentuan.view.RecommendFragment;
import com.lejiaokeji.fentuan.view.Store_Fragment;
import com.lejiaokeji.fentuan.view.UserCentreFragment;

import java.util.ArrayList;
import java.util.List;

import me.majiajie.pagerbottomtabstrip.NavigationController;
import me.majiajie.pagerbottomtabstrip.PageNavigationView;
import me.majiajie.pagerbottomtabstrip.listener.OnTabItemSelectedListener;

public class MainActivity extends AppCompatActivity {
    ViewPager viewPager;
    private List<Fragment> fragmentList=new ArrayList<>();
    NavigationController mNavigationController;
    PageNavigationView pageBottomTabLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(this);
        setContentView(R.layout.activity_main);
        viewPager=findViewById(R.id.myviewpage);
        pageBottomTabLayout = (PageNavigationView) findViewById(R.id.tab);
        mNavigationController=pageBottomTabLayout.material().addItem(R.drawable.tool_ic_home_nor,R.drawable.tool_ic_home_sel,"",getResources().getColor(R.color.colorAccent))
                .addItem(R.drawable.tool_ic_code_nor,R.drawable.tool_ic_code_sel,"推荐",getResources().getColor(R.color.colorAccent))
                .addItem(R.drawable.pfile_ic_portrait,"商城")
                .addItem(R.drawable.pfile_ic_portrait,"通知")
                .addItem(R.drawable.pfile_ic_portrait,"我的")
        .build();
        initview();
    }
    private  void initview() {

        fragmentList.add(new HomeFragment());
        fragmentList.add(new RecommendFragment());
        fragmentList.add(new Store_Fragment());
        fragmentList.add(new NotificationFragment());
        fragmentList.add(new UserCentreFragment());
        viewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager(), fragmentList));
        //自动适配ViewPager页面切换
        mNavigationController.setupWithViewPager(viewPager);

        //也可以设置Item选中事件的监听
        mNavigationController.addTabItemSelectedListener(new OnTabItemSelectedListener() {
            @Override
            public void onSelected(int index, int old) {
                Log.i("asd", "selected: " + index + " old: " + old);
            }

            @Override
            public void onRepeat(int index) {
                Log.i("asd", "onRepeat selected: " + index);
            }
        });
    }
}
