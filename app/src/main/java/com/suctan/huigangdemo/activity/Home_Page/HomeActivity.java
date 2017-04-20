package com.suctan.huigangdemo.activity.Home_Page;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.androidbase.mvp.BasePresenter;
import com.example.androidbase.mvp.MvpActivity;
import com.jaeger.library.StatusBarUtil;
import com.suctan.huigangdemo.R;
import com.suctan.huigangdemo.activity.MainActivity;
import com.suctan.huigangdemo.activity.login.LoginActivity;
import com.suctan.huigangdemo.bean.index.EatFoodReturn;
import com.suctan.huigangdemo.bean.user.HomeBean;
import com.suctan.huigangdemo.mvp.login.index.home.HomePresenter;
import com.suctan.huigangdemo.mvp.login.index.home.HomeView;

/**
 * Created by Tom on 2017/4/6.
 */

public class HomeActivity extends MvpActivity<HomePresenter> implements HomeView,View.OnClickListener {

    private ImageView imgBack;
    private ImageView imgSearch;
    private TextView txtTitle;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StatusBarUtil.setColor(this, getResources().getColor(R.color.colorPrimary), 0);
        initView();
    }

    private void initView() {
        imgBack = (ImageView) findViewById(R.id.login_back);
        imgBack.setOnClickListener(this);
        imgSearch = (ImageView) findViewById(R.id.search);
        imgSearch.setOnClickListener(this);
        txtTitle = (TextView) findViewById(R.id.login_title);
        txtTitle.setText("享享我好吗");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_back:
                finish();
                break;

            case R.id.search:
                Intent goMainIntent = new Intent(HomeActivity.this, MainActivity.class);
                startActivity(goMainIntent);
                break;

        }
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

    @Override
    protected HomePresenter createPresenter() {
        return null;
    }


    @Override
    public void getRollViewListSuc(EatFoodReturn mEatFoodReturn) {

    }

    @Override
    public void getRollViewListFail() {

    }

    @Override
    public void getEatfoodListSuc(EatFoodReturn mEatFoodReturn) {

    }


    @Override
    public void getEatfoodListFail() {

    }
}
