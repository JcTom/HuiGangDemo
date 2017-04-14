package com.suctan.huigangdemo.activity.myself;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.suctan.huigangdemo.R;

/**
 * Created by B-305 on 2017/4/13.
 */

public class WaitfoodActitity  extends Activity implements View.OnClickListener{

    private ImageView room_back;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.waiting_food);
        initView();
    }

    private void initView() {
        room_back = (ImageView) findViewById(R.id.room_back);
        room_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.room_back:
                finish();

        }
    }
}
