package com.suctan.huigangdemo.activity.share;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.suctan.huigangdemo.R;

/**
 * Created by B-305 on 2017/4/5.
 */

public class Service_list extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.service_list);
        getSupportActionBar().hide();
    }
}
