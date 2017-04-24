package com.suctan.huigangdemo.activity.myself;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.androidbase.mvp.MvpActivity;
import com.suctan.huigangdemo.R;
import com.suctan.huigangdemo.acache.TokenManager;
import com.suctan.huigangdemo.mvp.login.my_wallet.MymoneyPresenter;
import com.suctan.huigangdemo.mvp.login.my_wallet.MymoneyView;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by B-305 on 2017/4/13.
 */

public class MymoneyActivity  extends MvpActivity<MymoneyPresenter> implements MymoneyView{

     private TextView Mymoney;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_wallet);
        Map map = new HashMap();
        map.put("user_token", TokenManager.getToken());
        mvpPresenter.MymoneyAction(map);
        Mymoney = (TextView) findViewById(R.id.Mymoney);    //显示金额的text
        Button  incase = (Button) findViewById(R.id.incase);   //充值点击按钮
        Button  withdarwas = (Button) findViewById(R.id.withdarwas);   //提取点击按钮
        ImageView money_back= (ImageView) findViewById(R.id.money_back); //返回按钮


        //提现页面点击事件
        withdarwas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotowithdarwas = new Intent(MymoneyActivity.this , WithdarwasActivity.class);
//                gotowithdarwas.putExtras("Mmoney",);
                gotowithdarwas.putExtra("money", Mymoney.getText().toString().trim());
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
        //我的钱包中的返回 点击事件
        money_back.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    @Override
    protected MymoneyPresenter createPresenter() {
        return new MymoneyPresenter(this);
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
    public void getmoney(String money) {
      Mymoney.setText(money);

    }
}
