package com.lejiaokeji.fentuan.view;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.lejiaokeji.fentuan.R;
import com.lejiaokeji.fentuan.activity.Find_Password_Activity;
import com.lejiaokeji.fentuan.activity.Invite_Activity;
import com.lejiaokeji.fentuan.activity.Order_Activity;
import com.lejiaokeji.fentuan.activity.Setting_Activity;
import com.lejiaokeji.fentuan.activity.Shouyi_Activity;
import com.lejiaokeji.fentuan.view.helpview.LazyLoadFragment;
import com.lejiaokeji.fentuan.wxapi.Constants;

public class UserCentreFragment extends LazyLoadFragment implements View.OnClickListener {
    ImageView im_user_centent_shouyi;
    ImageView im_user_centent_renshu;
    ImageView im_user_centent_order;
    SimpleDraweeView sdv_photo;
    TextView tv_nickname;
    TextView tv_yaoqingcode;

    LinearLayout rl_service;
    LinearLayout rl_help;
    LinearLayout rl_uppassword;
    LinearLayout rl_myfriend;
    LinearLayout rl_mycode;
    LinearLayout ll_apply;
    LinearLayout ll_pataer;
    Button bt_setting;
    TextView tv_lever;
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
        ll_apply=findViewById(R.id.ll_apply);
        ll_pataer=findViewById(R.id.linearLayout4);
        bt_setting=findViewById(R.id.bt_setting);
        tv_yaoqingcode=findViewById(R.id.tv_myyaoqingcaode);
        im_user_centent_order=findViewById(R.id.im_user_centent_order);
        im_user_centent_shouyi=findViewById(R.id.im_user_centent_shouyi);
        im_user_centent_renshu=findViewById(R.id.im_user_centent_renshu);
        sdv_photo=findViewById(R.id.sdv_user_centent);
        tv_nickname=findViewById(R.id.tv_user_centent_name);
        sdv_photo.setImageURI(Constants.USERINFO.getHeadportrait());
        tv_nickname.setText(Constants.USERINFO.getNickname());
        tv_yaoqingcode.setText(Constants.USERINFO.getPhone());
        rl_help.setOnClickListener(this);
        rl_service.setOnClickListener(this);
        rl_uppassword.setOnClickListener(this);
        rl_myfriend.setOnClickListener(this);
        rl_mycode.setOnClickListener(this);
        bt_setting.setOnClickListener(this);
        im_user_centent_order.setOnClickListener(this);
        im_user_centent_shouyi.setOnClickListener(this);
        im_user_centent_renshu.setOnClickListener(this);
        ll_apply.setOnClickListener(this);
        if (Constants.USERINFO.getLevel()>1){
            ll_apply.setVisibility(View.GONE);
        }else {
            ll_pataer.setVisibility(View.GONE);
        }

        tv_lever=findViewById(R.id.tv_lever);
        if (Constants.USERINFO.getLevel()>1){
            tv_lever.setText("合伙人");
        }else {
            tv_lever.setText("普通会员");
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        tv_nickname.setText(Constants.USERINFO.getNickname());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_help:{
                Toast.makeText(getActivity(),"该功能正在开发，敬请期待",Toast.LENGTH_SHORT).show();
                break;
            }
            case R.id.rl_service:{
                Toast.makeText(getActivity(),"该功能正在开发，敬请期待",Toast.LENGTH_SHORT).show();
                break;
            }
            case R.id.rl_uppassword:{
                Intent intent=new Intent(getActivity(), Find_Password_Activity.class);
                getActivity().startActivity(intent);
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
                Intent intent=new Intent(getActivity(), Shouyi_Activity.class);
                getActivity().startActivity(intent);
                break;
            }case R.id.im_user_centent_renshu:{
                Toast.makeText(getActivity(),"该功能正在开发，敬请期待",Toast.LENGTH_SHORT).show();
                break;
            }case R.id.bt_setting:{
                Intent intent=new Intent(getActivity(), Setting_Activity.class);
                getActivity().startActivity(intent);
                break;
            }
        }
    }
}
