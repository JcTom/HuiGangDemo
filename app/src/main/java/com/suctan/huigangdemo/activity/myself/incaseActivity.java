package com.suctan.huigangdemo.activity.myself;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.suctan.huigangdemo.R;

/**
 * Created by B-305 on 2017/4/13.
 */

public class incaseActivity extends Activity implements View.OnClickListener{
    private ImageView incase_back;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.incase);
        initView();
    }

    private void initView() {
        incase_back = (ImageView) findViewById(R.id.incase_back);
        incase_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.incase_back:
                  finish();
        }
    }
}
