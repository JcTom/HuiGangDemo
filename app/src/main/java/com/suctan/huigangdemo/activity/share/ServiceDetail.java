package com.suctan.huigangdemo.activity.share;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.suctan.huigangdemo.R;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.suctan.huigangdemo.R.id.login_title;

public class ServiceDetail extends AppCompatActivity {

    @BindView(R.id.login_back)
    ImageView loginBack;
    @BindView(login_title)
    TextView loginTitle;
    @BindView(R.id.search)
    ImageView search;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.service_details);
        ButterKnife.bind(this);
        initView();
    }

    /*
 * 初始化标题栏
 * */
    private void initView() {
        search.setVisibility(View.GONE);
        loginTitle.setText("详情");
        loginBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

}
