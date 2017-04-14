package com.suctan.huigangdemo.activity.myself;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.suctan.huigangdemo.R;

/**
 * Created by B-305 on 2017/4/13.
 */

public class buyActivity  extends Activity implements View.OnClickListener{
    private ImageView buy_back; //定义一个图片名字
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_buy);
        initView();
    }

    private void initView() {
        //初始化，图片返回按钮点击事件
        buy_back = (ImageView) findViewById(R.id.buy_back);
        buy_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buy_back:
                finish();   //实现返回事件，回退前一个页面
        }
    }
}

