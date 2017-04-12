package com.suctan.huigangdemo.activity.recommend;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.suctan.huigangdemo.R;
import com.suctan.huigangdemo.activity.Home_Page.HomeActivity;

/**
 * Created by Tom on 2017/4/10.
 */

public class RecommendActivity extends HomeActivity implements View.OnClickListener {
    private ImageView login_recommend_back;
    private TextView login_recommend_title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recommend);
        init();
    }

    private void init() {
        login_recommend_back = (ImageView) findViewById(R.id.login_recommend_back);
        login_recommend_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        login_recommend_title = (TextView) findViewById(R.id.login_recommend_title);
        login_recommend_title.setText("享享我好吗");
    }
}
