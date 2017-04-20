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
import com.suctan.huigangdemo.R;
import com.suctan.huigangdemo.activity.MainActivity;
import com.suctan.huigangdemo.activity.setting.SeetingForGetPwd;
import com.suctan.huigangdemo.activity.signup.signupActivity;
import com.suctan.huigangdemo.bean.user.Users;
import com.suctan.huigangdemo.mvp.login.LoginPresener;
import com.suctan.huigangdemo.mvp.login.LoginView;

import java.util.HashMap;
import java.util.Map;

/**
 * create at 2017/3/23 17:23
 *
 * @author：LZH
 * @explain 用户登录
 */
public class LoginActivity extends MvpActivity<LoginPresener> implements LoginView, View.OnClickListener {

    //title
    private ImageView imgBack;
    private TextView txtTitle;
    private Button btnLogin,btn_signup;
    private TextView forgetpsw;
    //edt
    private EditText edtUserName;
    private EditText edtPwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        StatusBarUtil.setColor(this, getResources().getColor(R.color.colorPrimary), 0);
//        mvpPresenter.getHelloText();
//        //
//        TelephonyManager TelephonyMgr = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
//        String szImei = TelephonyMgr.getDeviceId();
        initView();
    }

    @Override
    protected LoginPresener createPresenter() {
        return new LoginPresener(this);
    }

    private void initView() {
        imgBack = (ImageView) findViewById(R.id.logins_back);
        imgBack.setOnClickListener(this);

        //注册
        btn_signup = (Button) findViewById(R.id.btn_signup);
        btn_signup.setOnClickListener(this);

        //忘记密码
        forgetpsw = (TextView) findViewById(R.id.forgetpsw);
        forgetpsw.setOnClickListener(this);

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
            case R.id.logins_back:
                finish();
                break;
            case R.id.btn_login:
//              TODO 调试阶段，登陆任意放行
//                toogleBtnClickStyle(false);
//                LoginVariety("1", "1");
                Variety();
                break;
            case R.id.forgetpsw:
                Intent intentLogPwd = new Intent(this, SeetingForGetPwd.class);
                startActivity(intentLogPwd);
                break;
            case R.id.btn_signup:
                Intent intentsignup= new Intent(this, signupActivity.class);
                startActivity(intentsignup);
                break;
        }
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
//                System.out.println("你输入的手机号码和密码是" + userName + pwd);
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
        Map<String, Object> mapLogin = new HashMap();
        mapLogin.put("user_phone", userName);
        mapLogin.put("user_pass", pwd);
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
    public void loginGoMain() {
        goActivity();
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
