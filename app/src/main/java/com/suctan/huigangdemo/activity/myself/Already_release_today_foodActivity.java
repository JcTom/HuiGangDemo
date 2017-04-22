package com.suctan.huigangdemo.activity.myself;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.suctan.huigangdemo.R;

/**
 * Created by B-305 on 2017/4/22.
 */

public class Already_release_today_foodActivity extends Activity  implements View.OnClickListener{
    private ImageView Alreadyback;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.already_release_today_food);
        initView();

    }

    private void initView() {
        Alreadyback = (ImageView) findViewById(R.id.AlreadyBack);
        Alreadyback.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
     switch (v.getId()){
         case R.id.AlreadyBack:
         finish();
         break;
     }

    }
}
