package com.suctan.huigangdemo.widget;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.androidbase.BaseApplication;
import com.suctan.huigangdemo.R;

/**
 * Create by kushanmao on 2017/2/28 0028 10:29
 */

public class TipDialog extends Dialog implements View.OnClickListener {

    private Context context;
    private TextView tipdialog_title;
    private TextView tipdialog_content;
    private Button tipdialog_comfirm;
    private Button tipdialog_cancel;

    public TipDialog(Context context) {
        super(context);
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);
        tipdialog_title = (TextView) findViewById(R.id.tipdialog_titles);
        tipdialog_content = (TextView) findViewById(R.id.tipdialog_contents);
        tipdialog_comfirm = (Button) findViewById(R.id.tipdialog_comfirm);
        tipdialog_cancel = (Button) findViewById(R.id.tipdialog_cancel);
        String tip = "\"当前操作需要的权限\"" + "，请去\"设置\"修改";
        tipdialog_content.setText(tip);
        tipdialog_comfirm.setText("打开设置");
        tipdialog_cancel.setText("取消");
        //监听点击事件
        tipdialog_comfirm.setOnClickListener(this);
        tipdialog_cancel.setOnClickListener(this);
        Window win = getWindow();
        win.getDecorView().setPadding(20, 0, 20, 0);

    }

    //设置标题
    public TipDialog setTipTitle(String str) {
        if (str != null) {
            tipdialog_title.setText(str);
        }
        return this;
    }

    //设置内容
    public TipDialog setTipContent(String str) {
        if (str != null) {
            tipdialog_content.setText(str);
        }
        return this;
    }

    //设置确定按钮
    public TipDialog setComfrirm(String str) {
        if (str != null && tipdialog_comfirm != null) {
            tipdialog_comfirm.setText(str);
        }
        return this;
    }

    //设置取消按钮
    public TipDialog setCancel(String str) {
        if (str != null && tipdialog_cancel != null) {
            tipdialog_cancel.setText(str);
        }
        return this;
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tipdialog_comfirm:
                Lisetner.comfirm();
                break;
            case R.id.tipdialog_cancel:
                Lisetner.cancel();
                break;

        }
    }

    private OnTipLisetner Lisetner;

    public void setTipClickLisener(OnTipLisetner Lisetner) {
        this.Lisetner = Lisetner;

    }

    public interface OnTipLisetner {
        void comfirm();

        void cancel();
    }
}
