package com.suctan.huigangdemo.activity.myself;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.androidbase.mvp.MvpActivity;
import com.suctan.huigangdemo.R;
import com.suctan.huigangdemo.acache.TokenManager;
import com.suctan.huigangdemo.mvp.login.Addaddress.addressPresenter;
import com.suctan.huigangdemo.mvp.login.Addaddress.addressView;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by B-305 on 2017/4/13.
 */

public class addressActivity extends MvpActivity<addressPresenter> implements View.OnClickListener, addressView {

    private ImageView address_back;   //地址管理中的返回按钮
    private TextView address_add;     //地址管理中的添加按钮
    private ListView lv_address;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.address_manager);
        initView();
        getAddressList();

    }

    private void getAddressList() {
        Map<String, Object> map = new HashMap<>();
        map.put("user_token", TokenManager.getToken());
        mvpPresenter.getAddressList(map);
    }

    @Override
    protected addressPresenter createPresenter() {
        return new addressPresenter(this);
    }

    private void initView() {
        //地址管理中的添加按钮 初始化
        address_add = (TextView) findViewById(R.id.address_add);
        address_add.setOnClickListener(this);
        //地址管理中的返回按钮 初始化
        address_back = (ImageView) findViewById(R.id.address_back);
        address_back.setOnClickListener(this);
        lv_address = (ListView) findViewById(R.id.lv_address);


    }

    private void initListViewAdatper() {


    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.address_back:
                finish();
                break;
            case R.id.address_add:
                Intent add_address = new Intent(addressActivity.this, address_listitemActivity.class);
                startActivity(add_address);
        }

    }

    @Override
    public void addAdressSuc() {
        finish();
    }

    @Override
    public void addAdressFail() {

    }

    @Override
    public void getAddressListSuc() {



    }

    @Override
    public void getAddressListFail() {

    }

    @Override
    public void getDataFail(String msg) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }
}
