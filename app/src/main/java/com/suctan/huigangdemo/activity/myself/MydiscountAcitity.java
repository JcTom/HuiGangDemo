package com.suctan.huigangdemo.activity.myself;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.androidbase.mvp.MvpActivity;
import com.suctan.huigangdemo.R;
import com.suctan.huigangdemo.bean.Mydiscount.MydiscountBean;
import com.suctan.huigangdemo.mvp.login.myMydiscount.MydiscountPresenter;
import com.suctan.huigangdemo.mvp.login.myMydiscount.MydiscountView;

/**
 * Created by B-305 on 2017/4/13.
 */

public class MydiscountAcitity extends MvpActivity<MydiscountPresenter> implements MydiscountView {


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.discount_money);
        ImageView My_discount_back = (ImageView) findViewById(R.id.My_discount_back);

        //点击返回事件
        My_discount_back.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    @Override
    protected MydiscountPresenter createPresenter() {
        return null;
    }


    @Override
    public void getMydiscountS(MydiscountBean mydiscountBean) {

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
