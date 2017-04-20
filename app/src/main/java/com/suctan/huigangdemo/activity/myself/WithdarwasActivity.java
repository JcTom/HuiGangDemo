package com.suctan.huigangdemo.activity.myself;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.androidbase.mvp.MvpActivity;
import com.suctan.huigangdemo.R;
import com.suctan.huigangdemo.acache.TokenManager;
import com.suctan.huigangdemo.bean.user.CourseBean;
import com.suctan.huigangdemo.mvp.login.my_wallet_tx.tx_walletPresenter;
import com.suctan.huigangdemo.mvp.login.my_wallet_tx.tx_walletView;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by B-305 on 2017/4/13.
 */

public class WithdarwasActivity extends MvpActivity<tx_walletPresenter> implements View.OnClickListener,tx_walletView {
     private ImageView withdarwas_back;
    @BindView(R.id.btnwitharwals)
    Button btnwitharwals;
    @BindView(R.id.witharwals)
    EditText witharwals;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.withdarwas);
        initView();
        ButterKnife.bind(this);
    }



    private void initView() {
        //提现页面的返回按钮
        withdarwas_back = (ImageView) findViewById(R.id.withdarwas_back);
        withdarwas_back.setOnClickListener(this);

        //提现页面的确认按钮
        btnwitharwals.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.withdarwas_back:
                 finish();
            case R.id.btnwitharwals:
                witharwalsVariety();
        }

    }

    private void witharwalsVariety() {
          String outmoney = witharwals.getText().toString().trim();
          String token = TokenManager.getToken();
          if (TextUtils.isEmpty(outmoney)){
              Toast.makeText(this, "你还没有输入提现的数字,谢谢", Toast.LENGTH_SHORT).show();
              return;
          }
        Map<String,Object> map= new HashMap();
        map.put("user_token",token);
        map.put("money",outmoney);
        mvpPresenter.withrawalsAction(map);

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
    public void loadCourseDone(CourseBean courseBean) {

    }
    @Override
    protected tx_walletPresenter createPresenter() {
        return new tx_walletPresenter(this);
    }
}
