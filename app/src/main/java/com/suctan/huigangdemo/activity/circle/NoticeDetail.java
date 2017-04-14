package com.suctan.huigangdemo.activity.circle;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.suctan.huigangdemo.R;

/**
 * Created by B-305 on 2017/4/13.
 */

public class NoticeDetail extends AppCompatActivity{

    ImageButton noticeBack;
    Button noticeMore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.circle_notice_details);

        initButton();
    }

    private void initButton(){
        noticeBack = (ImageButton) findViewById(R.id.notice_back);
        noticeMore = (Button) findViewById(R.id.notice_more);

        noticeBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        noticeMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goMoreNotice = new Intent(NoticeDetail.this,MoreNotice.class);
                startActivity(goMoreNotice);
            }
        });


    }

}
