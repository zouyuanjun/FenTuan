package com.lejiaokeji.fentuan.view;

import com.lejiaokeji.fentuan.R;
import com.lejiaokeji.fentuan.view.helpview.LazyLoadFragment;

public class BlankFragment extends LazyLoadFragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    public BlankFragment() {
        // Required empty public constructor
    }

    @Override
    protected int setContentView() {
        return R.layout.fragment_recommend;
    }

    @Override
    protected void lazyLoad() {

    }

}
