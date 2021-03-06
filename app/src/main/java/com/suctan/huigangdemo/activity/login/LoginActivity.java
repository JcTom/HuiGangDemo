package com.suctan.huigangdemo.activity.login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
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
import com.suctan.huigangdemo.acache.TokenManager;
import com.suctan.huigangdemo.activity.MainActivity;
import com.suctan.huigangdemo.activity.rememberPsw.SmoothCheckBox;
import com.suctan.huigangdemo.activity.setting.SeetingFogetPwd;
import com.suctan.huigangdemo.activity.signup.signupActivity;
import com.suctan.huigangdemo.bean.user.Users;
import com.suctan.huigangdemo.mvp.login.LoginPresener;
import com.suctan.huigangdemo.mvp.login.LoginView;

import java.util.HashMap;
import java.util.Map;

import cn.bmob.v3.Bmob;

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
    private String TAG ="LoginActivity";
    private boolean xuanzhong;

    //记住密码
    private SmoothCheckBox scb;
    private SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //第一：默认初始化
        Bmob.initialize(this, "1fe47f6bb8ec6a3eb640c3617952b5a6");
        setContentView(R.layout.activity_login);
        StatusBarUtil.setColor(this, getResources().getColor(R.color.colorPrimary), 0);
        String token = TokenManager.getToken();
         ToastTool.showToast(token,2);
//        if (!TextUtils.isEmpty(token)) {
//            goActivity();
////            startActivity(new Intent(this , MainActivity.class));
//        }
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

        //记住密码
        sp = getSharedPreferences("config", MODE_PRIVATE);
        scb = (SmoothCheckBox) findViewById(R.id.remember_pass);
        //获取sp里面存储的数据
        String savedphone= sp.getString("phone", "");
        String savedPassword = sp.getString("password", "");
        boolean saveremenber = sp.getBoolean("xuanzhong",false);
        edtUserName.setText(savedphone);
        edtPwd.setText(savedPassword);
        scb.setChecked(saveremenber);
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
                Intent intentLogPwd = new Intent(this, SeetingFogetPwd.class);
                startActivity(intentLogPwd);
                break;
            case R.id.btn_signup:
                Intent intentsignup= new Intent(this, signupActivity.class);
                startActivity(intentsignup);
                break;
        }
    }

    /**
     * 判断用户名与密码是否为空，网路是否正常
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
        //获取正确的数据储存起来
        String account1 = edtUserName.getText().toString();
        String password1 = edtPwd.getText().toString();
        if (scb.isChecked()) {
            // 说明勾选框被选中了。把用户名和密码给记录下来
            // 获取到一个参数文件的编辑器。
            SharedPreferences.Editor editor = sp.edit();
            editor.putString("phone", account1);
            editor.putString("password", password1);
            editor.putBoolean("xuanzhong",true);
            // 把数据给保存到sp里面
            editor.commit();
            Toast.makeText(getApplicationContext(), "记住密码已经生效", Toast.LENGTH_SHORT)
                    .show();
        }

        goActivity();
    }

    @Override
    public void loginMessageReturn(Users users) {
        Log.i(TAG, "loginMessageReturn: 电话号码是 " +users.getUser_phone() );

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
