package com.suctan.huigangdemo.activity.myself;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.suctan.huigangdemo.R;

/**
 * Created by B-305 on 2017/4/13.
 */

public class WithdarwasActivity extends Activity implements View.OnClickListener{
     private ImageView withdarwas_back;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.withdarwas);
        initView();
    }

    private void initView() {
        withdarwas_back = (ImageView) findViewById(R.id.withdarwas_back);
        withdarwas_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.withdarwas_back:
                 finish();
        }

    }
}
