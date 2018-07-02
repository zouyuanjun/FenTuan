package com.lejiaokeji.fentuan.view.notification;

import android.graphics.Color;
import android.os.Build;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.lejiaokeji.fentuan.R;
import com.lejiaokeji.fentuan.adapter.Notification_Adapter;
import com.lejiaokeji.fentuan.databean.Item_notificationBean;
import com.lejiaokeji.fentuan.view.helpview.LazyLoadFragment;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

import java.util.ArrayList;
import java.util.List;

public class NotificationFragment extends LazyLoadFragment {
    PullLoadMoreRecyclerView recyclerView;
    Notification_Adapter adapter;
    List<Item_notificationBean> list=new ArrayList<>();

    @Override
    protected int setContentView() {

        return R.layout.fragment_notification;
    }

    @Override
    protected void lazyLoad() {
        setdata();
        recyclerView =findViewById(R.id.notification_rv);
        recyclerView.setLinearLayout();
        recyclerView.setPushRefreshEnable(false);
        recyclerView.setPullRefreshEnable(false);
        adapter=new Notification_Adapter(getContext(),list);
        recyclerView.setAdapter(adapter);

    }
    public void setdata(){
        list.clear();
        list.add(new Item_notificationBean("关于注册问题的通知","","比较宽松的和诺夫卡是男女萨卡加诺夫卡按实际肯定会诺夫卡山","2小时前"));
        list.add(new Item_notificationBean("关于注册问题的通知","http://img2.imgtn.bdimg.com/it/u=633577398,100638800&fm=27&gp=0.jpg","比较宽松的和诺夫卡是男女萨卡加诺夫卡按实际肯定会诺夫卡山","3小时前"));
        list.add(new Item_notificationBean("关于注册问题的通知","http://img4.imgtn.bdimg.com/it/u=2503058379,1253215927&fm=27&gp=0.jpg","比较宽松的和诺夫卡是男女萨卡加诺夫卡按实际肯定会诺夫卡山","2小时前"));
        list.add(new Item_notificationBean("关于注册问题的通知","http://img2.imgtn.bdimg.com/it/u=633577398,100638800&fm=27&gp=0.jpg","比较宽松的和诺夫卡是男女萨卡加诺夫卡按实际肯定会诺夫卡山","3小时前"));
        list.add(new Item_notificationBean("关于注册问题的通知","http://img4.imgtn.bdimg.com/it/u=2503058379,1253215927&fm=27&gp=0.jpg","比较宽松的和诺夫卡是男女萨卡加诺夫卡按实际肯定会诺夫卡山","2小时前"));
    }
}
