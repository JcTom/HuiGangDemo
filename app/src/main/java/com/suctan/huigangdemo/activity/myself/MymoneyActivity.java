package com.suctan.huigangdemo.activity.myself;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.suctan.huigangdemo.R;

/**
 * Created by B-305 on 2017/4/13.
 */

public class MymoneyActivity  extends Activity{


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_wallet);

        Button  incase = (Button) findViewById(R.id.incase);   //充值点击按钮
        Button  withdarwas = (Button) findViewById(R.id.withdarwas);   //提取点击按钮

        //提现页面点击事件
        withdarwas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotowithdarwas = new Intent(MymoneyActivity.this , WithdarwasActivity.class);
                startActivity(gotowithdarwas);
            }
        });

        //充值页面点击事件
        incase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotoincase = new Intent(MymoneyActivity.this , incaseActivity.class);
                startActivity(gotoincase);
            }
        });






    }
}
