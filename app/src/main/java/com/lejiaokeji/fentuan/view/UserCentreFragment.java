package com.lejiaokeji.fentuan.view;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.lejiaokeji.fentuan.R;
import com.lejiaokeji.fentuan.activity.Invite_Activity;
import com.lejiaokeji.fentuan.activity.Order_Activity;
import com.lejiaokeji.fentuan.activity.Setting_Activity;
import com.lejiaokeji.fentuan.view.helpview.LazyLoadFragment;

public class UserCentreFragment extends LazyLoadFragment implements View.OnClickListener {
    ImageView im_user_centent_shouyi;
    ImageView im_user_centent_renshu;
    ImageView im_user_centent_order;


    LinearLayout rl_service;
    LinearLayout rl_help;
    LinearLayout rl_uppassword;
    LinearLayout rl_myfriend;
    LinearLayout rl_mycode;
    Button bt_setting;
    @Override
    protected int setContentView() {
        return R.layout.fragment_usercentre;
    }

    @Override
    protected void lazyLoad() {
        rl_help=findViewById(R.id.rl_help);
        rl_service=findViewById(R.id.rl_service);
        rl_uppassword=findViewById(R.id.rl_uppassword);
        rl_myfriend=findViewById(R.id.rl_myfriend);
        rl_mycode=findViewById(R.id.rl_mycode);
        bt_setting=findViewById(R.id.bt_setting);
        im_user_centent_order=findViewById(R.id.im_user_centent_order);
        im_user_centent_shouyi=findViewById(R.id.im_user_centent_shouyi);
        im_user_centent_renshu=findViewById(R.id.im_user_centent_renshu);

        rl_help.setOnClickListener(this);
        rl_service.setOnClickListener(this);
        rl_uppassword.setOnClickListener(this);
        rl_myfriend.setOnClickListener(this);
        rl_mycode.setOnClickListener(this);
        bt_setting.setOnClickListener(this);
        im_user_centent_order.setOnClickListener(this);
        im_user_centent_shouyi.setOnClickListener(this);
        im_user_centent_renshu.setOnClickListener(this);



    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_help:{
                break;
            }
            case R.id.rl_service:{
                break;
            }
            case R.id.rl_uppassword:{
                break;
            }
            case R.id.rl_myfriend:{
                Intent intent=new Intent(getActivity(), Invite_Activity.class);
                getActivity().startActivity(intent);
                break;
            }
            case R.id.rl_mycode:{
                break;
            }case R.id.im_user_centent_order:{
                Intent intent=new Intent(getActivity(), Order_Activity.class);
                getActivity().startActivity(intent);
                break;
            }case R.id.im_user_centent_shouyi:{
                Intent intent=new Intent(getActivity(), Setting_Activity.class);
                getActivity().startActivity(intent);
                break;
            }case R.id.im_user_centent_renshu:{
                Intent intent=new Intent(getActivity(), Setting_Activity.class);
                getActivity().startActivity(intent);
                break;
            }case R.id.bt_setting:{
                Intent intent=new Intent(getActivity(), Setting_Activity.class);
                getActivity().startActivity(intent);
                break;
            }
        }
    }
}
