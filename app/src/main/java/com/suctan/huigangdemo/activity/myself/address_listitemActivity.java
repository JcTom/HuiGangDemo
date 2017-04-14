package com.suctan.huigangdemo.activity.myself;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.suctan.huigangdemo.R;

/**
 * Created by B-305 on 2017/4/14.
 */

public class address_listitemActivity extends Activity  implements View.OnClickListener{

   private ImageView add_address_back;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_address_list_item);
        initView();
    }

    private void initView() {
        add_address_back = (ImageView) findViewById(R.id.add_address_back);
        add_address_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.add_address_back:
                finish();
        }
    }
}
