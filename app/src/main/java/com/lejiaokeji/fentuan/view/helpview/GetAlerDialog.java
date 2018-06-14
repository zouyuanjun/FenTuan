package com.lejiaokeji.fentuan.view.helpview;

import android.app.Activity;
import android.app.AlertDialog;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.lejiaokeji.fentuan.R;

public class GetAlerDialog {

    public static AlertDialog getdialog(Activity activity,String title, String msg){
        TextView tv_title = new TextView(activity);
        tv_title.setText(title);
        tv_title.setPadding(10, 34, 10, 16);
        tv_title.setGravity(Gravity.CENTER);
        tv_title.setTextSize(17);
        tv_title.setTextColor(Color.parseColor("#333333"));

        View view = View.inflate(activity, R.layout.custom_dialog, null);

        TextView tv_msg = view.findViewById(R.id.tv_custom_dialog_msg);
        tv_msg.setText(msg);
        tv_msg.setGravity(Gravity.CENTER);
        tv_msg.setTextSize(13);

        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(activity);
        builder.setCustomTitle(tv_title);
        builder.setView(view);
        //点击对话框以外的区域是否让对话框消失
        builder.setCancelable(true);

        final android.app.AlertDialog dialog = builder.create();
        Button button=view.findViewById(R.id.bt_custom_dialog_queding);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        return dialog;
    }
}
