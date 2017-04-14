package com.suctan.huigangdemo.activity.share;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.suctan.huigangdemo.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by B-305 on 2017/4/13.
 */

public class ReleaseService extends AppCompatActivity{

    @BindView(R.id.login_back)
    ImageView loginBack;
    @BindView(R.id.login_title)
    TextView loginTitle;
    @BindView(R.id.search)
    ImageView search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.service_release);
        ButterKnife.bind(this);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        initView();

    }

    private void initView() {
        final Intent it=ReleaseService.this.getIntent();
        final String serviceType=it.getStringExtra("serviceType");
        loginTitle.setText("发布"+serviceType+"服务");
        search.setVisibility(View.GONE);
        loginBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
