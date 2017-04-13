package com.suctan.huigangdemo.activity.myself;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.suctan.huigangdemo.R;

/**
 * Created by B-305 on 2017/4/13.
 */

public class MydiscountAcitity extends Activity {


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.discount_money);
        ImageView image1 = (ImageView) findViewById(R.id.image1);
        ImageView image2 = (ImageView) findViewById(R.id.image2);
        ImageView image3 = (ImageView) findViewById(R.id.image3);


        image1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"恭喜你，得到了此优惠券",Toast.LENGTH_SHORT).show();
            }
        });

        image2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"恭喜你，得到了此优惠券",Toast.LENGTH_SHORT).show();
            }
        });

        image3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"恭喜你，得到了此优惠券",Toast.LENGTH_SHORT).show();
            }
        });

    }





}
