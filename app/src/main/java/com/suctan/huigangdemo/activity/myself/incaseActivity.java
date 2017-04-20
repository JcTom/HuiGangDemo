package com.suctan.huigangdemo.activity.myself;

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
import com.suctan.huigangdemo.mvp.login.my_wallet_cz.cz_walletPresenter;
import com.suctan.huigangdemo.mvp.login.my_wallet_cz.cz_walletView;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by B-305 on 2017/4/13.
 */

public class incaseActivity extends MvpActivity<cz_walletPresenter> implements View.OnClickListener,cz_walletView {
    private ImageView incase_back;
    private Button btncash;
    private EditText incashmoney;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.incase);
        initView();
    }


    private void initView() {
        //返回按钮点击事件
        incase_back = (ImageView) findViewById(R.id.incase_back);
        incase_back.setOnClickListener(this);
        //确认充值按钮点击事件
        btncash = (Button) findViewById(R.id.btncash);
        btncash.setOnClickListener(this);
        //输入今日的输入框初始化
        incashmoney= (EditText) findViewById(R.id.incashmoney);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.incase_back:
                  finish();
            case R.id.btncash:
                incashVariety();
        }
    }

    private void incashVariety() {
        String token = TokenManager.getToken();
        String money =  incashmoney.getText() .toString().trim();
        if (TextUtils.isEmpty(money)){
            Toast.makeText(this, "你还没有输入充值的数字,谢谢", Toast.LENGTH_SHORT).show();
            return;
        }
        Map<String,Object> map= new HashMap();
        map.put("user_token",token);
        map.put("money",money);
        mvpPresenter.Addmoney(map);
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
        ToastTool.showToast("充值成功",1);
    }
    @Override
    protected cz_walletPresenter createPresenter() {
        return new cz_walletPresenter(this);
    }

}
