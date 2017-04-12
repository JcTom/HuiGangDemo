package com.suctan.huigangdemo.activity.login;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.androidbase.mvp.MvpActivity;
import com.example.androidbase.utils.NetConnectUtils;
import com.example.androidbase.utils.ToastTool;
import com.jaeger.library.StatusBarUtil;
import com.suctan.huigangdemo.activity.MainActivity;
import com.suctan.huigangdemo.bean.user.Users;
import com.suctan.huigangdemo.mvp.login.LoginPresener;
import com.suctan.huigangdemo.mvp.login.LoginView;


import java.util.HashMap;
import java.util.Map;

import com.suctan.huigangdemo.R;

/**
 * create at 2017/3/23 17:23
 *
 * @author：LZH
 * @explain 用户登录
 */
public class LoginActivity extends MvpActivity<LoginPresener> implements LoginView, View.OnClickListener {

    //title
    private ImageView imgBack;
    private ImageView search;
    private TextView txtTitle;
    private Button btnLogin;
    //edt
    private EditText edtUserName;
    private EditText edtPwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        StatusBarUtil.setColor(this, getResources().getColor(R.color.colorPrimary), 0);
        initView();
    }

    @Override
    protected LoginPresener createPresenter() {
        return new LoginPresener(this);
    }

    private void initView() {
        imgBack = (ImageView) findViewById(R.id.login_back);
        imgBack.setOnClickListener(this);

        search = (ImageView) findViewById(R.id.search);
        search.setOnClickListener(this);
        search.setVisibility(View.GONE);
        txtTitle = (TextView) findViewById(R.id.login_title);
        txtTitle.setText("登录");

        btnLogin = (Button) findViewById(R.id.btn_login);
        btnLogin.setOnClickListener(this);

        //EditText
        edtUserName = (EditText) findViewById(R.id.edt_login_user_name);
        edtPwd = (EditText) findViewById(R.id.edt_login_user_pwd);

    }

    /**
     * created at 2017/3/23 17:22
     *
     * @param canClick true 可以点击 false 不可点击
     * @explain 用来控制按钮是否可以点击, 避免多次点击请求
     */
    private void toogleBtnClickStyle(boolean canClick) {
        if (canClick) {
        btnLogin.setClickable(true);
    } else {
        btnLogin.setClickable(false);
    }
}


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_back:
                finish();
                break;

            case R.id.search:
                finish();
                break;

            case R.id.btn_login:
                toogleBtnClickStyle(false);
                Variety();
                Pass();
                break;
        }
    }
    //测试
    private void Pass() {
                goActivity();
    }

    /**
     * 判断用户名与密码是否为空
     */
    private void Variety() {
        String userName = edtUserName.getText().toString().trim();
        String pwd = edtPwd.getText().toString().trim();
        if (TextUtils.isEmpty(userName)) {
            Toast.makeText(this, getResources().getString(R.string.inputActTip), Toast.LENGTH_SHORT).show();
            toogleBtnClickStyle(true);
            return;
        } else if (TextUtils.isEmpty(pwd)) {
            Toast.makeText(this, getResources().getString(R.string.inputPwdTip), Toast.LENGTH_SHORT).show();
            toogleBtnClickStyle(true);
            return;
        } else {
            if (NetConnectUtils.isNetConnected(LoginActivity.this)) {
                LoginVariety(userName, pwd);
            } else {
                ToastTool.showToast(getResources().getString(R.string.checkNetTip), 0);
                toogleBtnClickStyle(true);
            }
        }
    }

    /**
     * created at 2017/3/23 15:57
     *
     * @param userName 用户名
     * @param pwd      密码
     * @explain 用户登录验证
     */
    private void LoginVariety(String userName, String pwd) {
        Map mapLogin = new HashMap();
        mapLogin.put("action", "login");
        mapLogin.put("userName", userName);
        mapLogin.put("password", pwd);
        mvpPresenter.getLoginAction(mapLogin);
    }

    /**
     * 跳到主页面
     */
    private void goActivity() {
        ToastTool.showToast(getResources().getString(R.string.loginSucTip), 1);
        Intent goMainIntent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(goMainIntent);
        finish();
    }


    @Override
    public void loginMessageReturn(Users users) {
        goActivity();
    }

    @Override
    public void getDataFail(String msg) {
        toogleBtnClickStyle(true);
        ToastTool.showToast(msg, 0);
    }

    @Override
    public void showLoading() {

    }
    @Override
    public void hideLoading() {

    }
}
