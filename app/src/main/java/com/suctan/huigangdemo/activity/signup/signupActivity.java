package com.suctan.huigangdemo.activity.signup;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.androidbase.mvp.MvpActivity;
import com.example.androidbase.utils.NetConnectUtils;
import com.example.androidbase.utils.ToastTool;
import com.suctan.huigangdemo.R;
import com.suctan.huigangdemo.activity.login.LoginActivity;
import com.suctan.huigangdemo.mvp.login.singup.SingUpPresenter;
import com.suctan.huigangdemo.mvp.login.singup.SingUpView;

import java.util.HashMap;
import java.util.Map;

import cn.bmob.v3.BmobSMS;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.RequestSMSCodeListener;
import cn.bmob.v3.listener.VerifySMSCodeListener;

/**
 * Created by B-305 on 2017/4/19.
 */

public class signupActivity extends MvpActivity<SingUpPresenter> implements SingUpView, View.OnClickListener {
    private EditText user_phone, user_pass, identityCode;
    private Button btnRegister, btnIdentityCode;
    private TimeCount time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        user_phone = (EditText) findViewById(R.id.user_phone);
        user_pass = (EditText) findViewById(R.id.user_pass);
        identityCode = (EditText) findViewById(R.id.identityCode);
        btnRegister = (Button) findViewById(R.id.btnRegister);
        btnIdentityCode = (Button) findViewById(R.id.btnIdentityCode);
        btnRegister.setOnClickListener(this);
        btnIdentityCode.setOnClickListener(this);
        time = new TimeCount(60000, 1000);

    }


    /**
     * created at 2017/3/23 17:22
     *
     * @param canClick true 可以点击 false 不可点击
     * @explain 用来控制按钮是否可以点击, 避免多次点击请求
     */
    private void toogleBtnClickStyle(boolean canClick) {
        if (canClick) {
            btnRegister.setClickable(true);
        } else {
            btnRegister.setClickable(false);
        }
    }




    /**
     * created at 2017/3/23 15:57
     *
     * @param userName 用户名
     * @param pwd      密码
     * @explain 用户注册账号
     */
    private void LoginVariety(String userName, String pwd) {
        Map<String, Object> mapLogin = new HashMap();
        mapLogin.put("user_phone", userName);
        mapLogin.put("user_pass", pwd);
        mvpPresenter.registerAction(mapLogin);
    }



    /**
     * 判断用户名与密码是否为空，网路是否正常
     */
    private void registerVariety() {
         final String userName = user_phone.getText().toString().trim();
        final String pwd = user_pass.getText().toString().trim();
        if (TextUtils.isEmpty(userName)) {
            Toast.makeText(this, getResources().getString(R.string.inputActTip), Toast.LENGTH_SHORT).show();
            toogleBtnClickStyle(true);

            return;

        } else if (TextUtils.isEmpty(pwd)) {
            Toast.makeText(this, getResources().getString(R.string.inputPwdTip), Toast.LENGTH_SHORT).show();
            toogleBtnClickStyle(true);
            return;
        } else {
            if (NetConnectUtils.isNetConnected(signupActivity.this)) {
                BmobSMS.verifySmsCode(this, user_phone.getText().toString(), identityCode.getText().toString(), new VerifySMSCodeListener() {
                    @Override
                    public void done(BmobException ex) {
                        // TODO Auto-generated method stub
                        if (ex == null) {//短信验证码已验证成功
                            Log.i("smile", "验证通过"+userName+pwd);

                            LoginVariety(userName,pwd);

                        } else {
                            Log.i("smile", "验证失败：code =" + ex.getErrorCode() + ",msg = " + ex.getLocalizedMessage());
                        }
                    }
                });

            } else {
                ToastTool.showToast(getResources().getString(R.string.checkNetTip), 0);
                toogleBtnClickStyle(true);
            }
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnIdentityCode:

                getIdentityCode();

                break;

            case R.id.btnRegister:
                registerVariety();


                break;
        }

    }

    private void getIdentityCode() {
        String phone = user_phone.getText().toString().trim();
        if (TextUtils.isEmpty(phone)) { //验证手机号是否为空
            Toast.makeText(this, getResources().getString(R.string.inputActTip), Toast.LENGTH_SHORT).show();
            toogleBtnClickStyle(true);

            return;

        } else {

            time.start();
            BmobSMS.requestSMSCode(this, user_phone.getText().toString(), "register", new RequestSMSCodeListener() {

                @Override
                public void done(Integer integer, BmobException e) {
                    if (e == null) {
                        ToastTool.showToast("短信验证码发送成功" + "短信id：" + integer, 1);

                    } else {

                        ToastTool.showToast("短信验证码发送失败" + "错误信息" +e.toString(),0);
                    }


                }
            });


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
    protected SingUpPresenter createPresenter() {
        return new SingUpPresenter(this);
    }

    @Override
    public void registerDone() {
        //注册成功后跳转到登陆页面
        startActivity(new Intent(signupActivity.this,LoginActivity.class));
    }

     class TimeCount extends CountDownTimer {

         public TimeCount(long millisInFuture, long countDownInterval) {
             super(millisInFuture, countDownInterval);
         }

         @Override
         public void onTick(long millisUntilFinished) {
             btnIdentityCode.setBackgroundColor(Color.parseColor("#B6B6D8"));
             btnIdentityCode.setClickable(false);
             btnIdentityCode.setText("("+millisUntilFinished / 1000 +") 秒");
         }

         @Override
         public void onFinish() {
             btnIdentityCode.setText("验证码");
             btnIdentityCode.setClickable(true);
             btnIdentityCode.setBackgroundColor(Color.parseColor("#4EB84A"));

         }
    }
}
