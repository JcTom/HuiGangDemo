package com.suctan.huigangdemo.activity.myself;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.suctan.huigangdemo.R;

/**
 * Created by B-305 on 2017/4/13.
 */

public class release_todayfood_Activiity extends Activity implements View.OnClickListener{
   private ImageView today_food_back;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.release_today_food);
        initView();
    }

    private void initView() {
        today_food_back = (ImageView) findViewById(R.id.today_food_back);
        today_food_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.today_food_back:
                finish();
        }

    }
}
