package com.lejiaokeji.fentuan.activity;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.lejiaokeji.fentuan.R;
import com.lejiaokeji.fentuan.control.Shouyi;
import com.lejiaokeji.fentuan.databean.Add_Alipay_Bean;

public class Add_Alipay_Activity  extends BaseActivity{
    Activity activity;
    Context context;
    EditText ed_name;
    EditText ed_alipayaccount;
    EditText ed_phone;
    EditText ed_code;
    Button bt_get_code;
    Button bt_commit;
    Shouyi shouyi;
    boolean cansend = true;
    CountDownTimer timer;
    ImageView back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.parseColor("#ff286e"));
        }
        setContentView(R.layout.activity_addalipay);
        context=this;
        activity=this;
        ed_alipayaccount=findViewById(R.id.ed_alipayaccount);
        ed_name=findViewById(R.id.ed_name);
        ed_phone=findViewById(R.id.ed_alipayphone);
        ed_code=findViewById(R.id.ed_addalipay_code);
        bt_get_code=findViewById(R.id.bt_addalipay_code);
        bt_commit=findViewById(R.id.bt_commit);
        back=findViewById(R.id.im_addalipay_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        ed_phone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length()==11){
                    bt_get_code.setBackground(ContextCompat.getDrawable(context,R.drawable.signup_bt_verif_def));
                }else {
                    bt_get_code.setBackground(ContextCompat.getDrawable(context,R.drawable.signup_bt_verif_dis));
                }

            }
        });
        bt_get_code.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone=ed_phone.getText().toString();
                String account=ed_alipayaccount.getText().toString();
                if (phone.length()==11&&!account.isEmpty()){
                    shouyi.getcode(phone,account);
                    cansend=false;
                    bt_get_code.setBackground(ContextCompat.getDrawable(context,R.drawable.signup_bt_verif_dis));
                    timer.start();
                }else {
                    Toast.makeText(context,"请填写正确的手机号码和支付宝账户",Toast.LENGTH_LONG).show();
                }
            }
        });
        bt_commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=ed_name.getText().toString();
                String alipayaccount=ed_alipayaccount.getText().toString();
                String phone=ed_phone.getText().toString();
                String code=ed_code.getText().toString();
                if (!name.isEmpty()&&!alipayaccount.isEmpty()&&!phone.isEmpty()&&!code.isEmpty()){
                    Add_Alipay_Bean add_alipay_bean=new Add_Alipay_Bean(code,name,alipayaccount,phone);
                    shouyi.commit(add_alipay_bean);
                }else {
                    Toast.makeText(context,"请填写完整信息再提交",Toast.LENGTH_LONG).show();
                }
            }
        });

        /**
         * 倒数计时器
         */
        timer = new CountDownTimer(60 * 1000, 1000) {
            /**
             * 固定间隔被调用,就是每隔countDownInterval会回调一次方法onTick
             * @param millisUntilFinished
             */
            @Override
            public void onTick(long millisUntilFinished) {
                bt_get_code.setText("重新发送(" + millisUntilFinished / 1000 + ")秒");
            }

            /**
             * 倒计时完成时被调用
             */
            @Override
            public void onFinish() {
                bt_get_code.setText("重新发送");
                bt_get_code.setBackground(ContextCompat.getDrawable(activity, R.drawable.signup_bt_verif_def));
                cansend = true;
            }
        };

    }

    @Override
    protected void onResume() {
        super.onResume();
        shouyi=new Shouyi();
        shouyi.setNetWorkListener(new Shouyi.NetWorkerr() {
            @Override
            public void timeout() {
                Toast.makeText(activity,"连接超时，请检查网络",Toast.LENGTH_LONG).show();
            }
            @Override
            public void connectfail() {
                Toast.makeText(activity,"与服务器连接失败，请检查网络",Toast.LENGTH_LONG).show();
            }

            @Override
            public void severerr() {
                Toast.makeText(activity,"服务器内部错误",Toast.LENGTH_LONG).show();
            }
        });
        shouyi.setDataCallListener(new Shouyi.DataCall() {
            @Override
            public void getcode() {

            }

            @Override
            public void commit() {
                finish();
            }
        });
    }


}
