package com.lejiaokeji.fentuan.view.notification;

import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;

import com.lejiaokeji.fentuan.R;
import com.lejiaokeji.fentuan.view.helpview.LazyLoadFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import zlc.season.practicalrecyclerview.PracticalRecyclerView;

public class NotificationFragment extends LazyLoadFragment {
    @Override
    protected int setContentView() {
        return R.layout.fragment_notification;
    }

    @Override
    protected void lazyLoad() {

    }

}
