package com.suctan.huigangdemo.activity.myself;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.suctan.huigangdemo.R;

/**
 * Created by B-305 on 2017/4/13.
 */

public class WaitrealActivity extends Activity  implements View.OnClickListener{
    private ImageView real_back;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.waiting_real);
        initView();
    }

    private void initView() {
        real_back = (ImageView) findViewById(R.id.real_back);
        real_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.real_back:
                finish();

            }
        }
}
