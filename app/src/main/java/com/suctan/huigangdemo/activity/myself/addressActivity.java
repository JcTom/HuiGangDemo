package com.suctan.huigangdemo.activity.myself;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.suctan.huigangdemo.R;

/**
 * Created by B-305 on 2017/4/13.
 */

public class addressActivity extends Activity implements View.OnClickListener{

    private ImageView address_back;   //地址管理中的返回按钮
    private TextView address_add;     //地址管理中的添加按钮
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.address_manager);
        initView();

    }

    private void initView() {
        //地址管理中的添加按钮 初始化
        address_add = (TextView) findViewById(R.id.address_add);
        address_add.setOnClickListener(this);
        //地址管理中的返回按钮 初始化
        address_back = (ImageView) findViewById(R.id.address_back);
        address_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.address_back:
                finish();
                break;
            case R.id.address_add:
                Intent add_address = new Intent(addressActivity.this, address_listitemActivity.class);
                startActivity(add_address);
        }

    }
}
