package com.lejiaokeji.fentuan.view;

import android.app.Activity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.lejiaokeji.fentuan.R;
import com.lejiaokeji.fentuan.adapter.Notification_Adapter;
import com.lejiaokeji.fentuan.control.Notification;
import com.lejiaokeji.fentuan.databean.Item_notificationBean;
import com.lejiaokeji.fentuan.view.helpview.LazyLoadFragment;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

import java.util.ArrayList;
import java.util.List;

public class NotificationFragment extends LazyLoadFragment {
    PullLoadMoreRecyclerView recyclerView;
    Notification_Adapter adapter;
    List<Item_notificationBean> list=new ArrayList<>();
    Notification notification;
    Activity activity;
    ProgressBar progressBar;

    @Override
    protected int setContentView() {

        return R.layout.fragment_notification;
    }

    @Override
    protected void lazyLoad() {
        progressBar=findViewById(R.id.pb_notifition);
        activity=getActivity();
        notification=new Notification();
        notification.getdata();
        recyclerView =findViewById(R.id.notification_rv);
        recyclerView.setLinearLayout();
        recyclerView.setPushRefreshEnable(false);
        recyclerView.setPullRefreshEnable(false);
        adapter=new Notification_Adapter(getContext(),list);
        recyclerView.setAdapter(adapter);

        notification.setNetWorkListener(new Notification.NetWorkerr() {
            @Override
            public void timeout() {
                Toast.makeText(activity, "连接超时，请检查网络", Toast.LENGTH_LONG).show();
            }

            @Override
            public void connectfail() {
                Toast.makeText(activity, "与服务器连接失败，请检查网络", Toast.LENGTH_LONG).show();
            }

            @Override
            public void severerr() {
                Toast.makeText(activity, "服务器内部错误", Toast.LENGTH_LONG).show();
            }
        });
        notification.setDataCallListener(new Notification.DataCall() {
            @Override
            public void notificationData( List<Item_notificationBean> listdata) {
                progressBar.setVisibility(View.GONE);
                list.clear();
                list.addAll(listdata);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void commit() {

            }
        });




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
