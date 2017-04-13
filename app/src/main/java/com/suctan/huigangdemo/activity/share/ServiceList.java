package com.suctan.huigangdemo.activity.share;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.suctan.huigangdemo.R;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.suctan.huigangdemo.R.id.login_title;

/**
 * Created by B-305 on 2017/4/13.
 */

public class ServiceList extends AppCompatActivity {

    @BindView(R.id.login_back)
    ImageView loginBack;
    @BindView(login_title)
    TextView loginTitle;
    @BindView(R.id.search)
    ImageView search;
    @BindView(R.id.add_post)
    Button serviceFb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.service_list);
        ButterKnife.bind(this);

        initView();

    }

    private void initView() {
        search.setVisibility(View.GONE);
        serviceFb.setVisibility(View.VISIBLE);

        final Intent it=ServiceList.this.getIntent();
        final String serviceType=it.getStringExtra("serviceType");
        loginTitle.setText(serviceType);
        loginBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        serviceFb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goReleaseService = new Intent(ServiceList.this, ReleaseService.class);
                goReleaseService.putExtra("serviceType",serviceType);
                startActivity(goReleaseService);
            }
        });
    }

}