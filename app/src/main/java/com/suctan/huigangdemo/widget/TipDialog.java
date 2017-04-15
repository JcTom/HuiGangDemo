package com.suctan.huigangdemo.widget;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.suctan.huigangdemo.R;

/**
 * Create by kushanmao on 2017/2/28 0028 10:29
 */

public class TipDialog extends AlertDialog implements View.OnClickListener {

    private Context context;
    private TextView tipdialog_title;
    private TextView tipdialog_content;
    private Button tipdialog_comfirm;
    private Button tipdialog_cancel;


    public TipDialog(Context context) {
        this(context, 0);
    }

    public TipDialog(Context context, int themeResId) {
        super(context, themeResId);
        this.context = context;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = LayoutInflater.from(context).inflate(R.layout.activity_dialog, null);
        initView(view);

        Window win = getWindow();
        win.getDecorView().setPadding(20, 0, 20, 0);
        setContentView(view);
    }

    private void initView(View view) {
        tipdialog_title = (TextView) findViewById(R.id.tipdialog_title);
        tipdialog_content = (TextView) findViewById(R.id.tipdialog_content);
        tipdialog_comfirm = (Button) findViewById(R.id.tipdialog_comfirm);
        tipdialog_cancel = (Button) findViewById(R.id.tipdialog_cancel);

        //监听点击事件
        tipdialog_comfirm.setOnClickListener(this);
        tipdialog_cancel.setOnClickListener(this);

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
        if (str != null) {
            tipdialog_comfirm.setText(str);
        }
        return this;
    }

    //设置取消按钮
    public TipDialog setCancel(String str) {
        if (str != null) {
            tipdialog_content.setText(str);
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
