package com.suctan.huigangdemo.activity.myself;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.suctan.huigangdemo.R;


/**
 * Created by B-305 on 2017/4/13.
 */

public class WaitOrdersActivity extends Activity  implements View.OnClickListener{

    private ImageView orders_back;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.waiting_orders);
        initView();


    }

    private void initView() {

        orders_back = (ImageView) findViewById(R.id.orders_back);
        orders_back.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.orders_back:
                finish();
        }

    }
}