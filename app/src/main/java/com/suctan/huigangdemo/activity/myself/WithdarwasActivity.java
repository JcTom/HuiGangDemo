package com.suctan.huigangdemo.activity.myself;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.androidbase.mvp.MvpActivity;
import com.example.androidbase.utils.ToastTool;
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
    double s1;
     private ImageView withdarwas_back;
    @BindView(R.id.btnwitharwals)
    Button btnwitharwals;
    @BindView(R.id.witharwals)
    EditText witharwals;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.withdarwas);
        ButterKnife.bind(this);
        initView();
    }


    private void initView() {
        //提现页面的返回按钮
        withdarwas_back = (ImageView) findViewById(R.id.withdarwas_back);
        withdarwas_back.setOnClickListener(this);
        //提现页面的确认按钮
        btnwitharwals.setOnClickListener(this);
        //得到从我的钱包的页面调回来的参数 多少钱
        Intent intent=getIntent();
        String money = intent.getStringExtra("money");
        ToastTool.showToast("我给了你钱"+money , 1);
        s1 = Double.parseDouble(money);

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
          double s;
          String outmoney = witharwals.getText().toString().trim();
          s=Double.parseDouble(outmoney);
          String token = TokenManager.getToken();
          if (TextUtils.isEmpty(outmoney)){
              Toast.makeText(this, "你还没有输入提现的数字,谢谢", Toast.LENGTH_SHORT).show();
              return;
          }else{
                    if(s1>s) {
                        Map<String, Object> map = new HashMap<>();
                        map.put("user_token", token);
                        map.put("money", s);
                        mvpPresenter.withrawalsAction(map);
                        return;
                    }else{
                        Toast.makeText(this, "你并没有这么多钱!", Toast.LENGTH_SHORT).show();
                    }

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
    public void loadCourseDone(CourseBean courseBean) {

    }
    @Override
    protected tx_walletPresenter createPresenter() {
        return new tx_walletPresenter(this);
    }
}
