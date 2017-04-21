package com.suctan.huigangdemo.activity.setting;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
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
import com.suctan.huigangdemo.mvp.login.resetpass.ResetPassPresenter;
import com.suctan.huigangdemo.mvp.login.resetpass.ResetPassView;

import java.util.HashMap;
import java.util.Map;

import cn.bmob.v3.BmobSMS;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.RequestSMSCodeListener;
import cn.bmob.v3.listener.VerifySMSCodeListener;

/**
 * Created by 黄淑翰 on 2017/4/13.
 */
public class SeetingFogetPwd extends MvpActivity<ResetPassPresenter> implements ResetPassView, View.OnClickListener {
    private EditText user_phone, user_pass, identityCode;
    private Button btnResetPass, btnIdentityCode;
    private TimeCount time;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        user_phone = (EditText) findViewById(R.id.user_phone);
        user_pass = (EditText) findViewById(R.id.user_pass);
        identityCode = (EditText) findViewById(R.id.identityCode);
        btnResetPass = (Button) findViewById(R.id.btnReset);
        btnIdentityCode = (Button) findViewById(R.id.btnIdentityCode);
        btnResetPass.setOnClickListener(this);
        btnIdentityCode.setOnClickListener(this);
        time = new TimeCount(60000, 1000);


    }

    @Override
    protected ResetPassPresenter createPresenter() {
        return new ResetPassPresenter(this);
    }


    /**
     * created at 2017/3/23 17:22
     *
     * @param canClick true 可以点击 false 不可点击
     * @explain 用来控制按钮是否可以点击, 避免多次点击请求
     */
    private void toogleBtnClickStyle(boolean canClick) {
        if (canClick) {
            btnResetPass.setClickable(true);
        } else {
            btnResetPass.setClickable(false);
        }
    }

    /**
     * created at 2017/3/23 15:57
     *
     * @param userPhone 手机号
     * @param pwd      密码
     * @explain 用户注册账号
     */
    private void LoginVariety(String userPhone, String pwd) {
        Map<String, Object> mapLogin = new HashMap();
        mapLogin.put("user_phone", userPhone);
        mapLogin.put("user_pass", pwd);
        mvpPresenter.resetPassAction(mapLogin);
    }

    /**
     * 判断用户名与密码是否为空，网路是否正常
     */
    private void registerVariety() {
        final String pwd = user_pass.getText().toString().trim();
        final String phone = user_phone.getText().toString().trim();
        if (TextUtils.isEmpty(pwd)) {
            Toast.makeText(this, getResources().getString(R.string.inputPwdTip), Toast.LENGTH_SHORT).show();
            toogleBtnClickStyle(true);
            return;
        } else {
            if (NetConnectUtils.isNetConnected(SeetingFogetPwd.this)) {
                BmobSMS.verifySmsCode(this, user_phone.getText().toString(), identityCode.getText().toString(), new VerifySMSCodeListener() {
                    @Override
                    public void done(BmobException ex) {
                        // TODO Auto-generated method stub
                        if (ex == null) {//短信验证码已验证成功
                            Log.i("smile", "验证通过");
                            LoginVariety(phone,pwd);

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

            case R.id.btnReset:
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
            BmobSMS.requestSMSCode(this, user_phone.getText().toString(), "resetPass", new RequestSMSCodeListener() {
                @Override
                public void done(Integer integer, BmobException e) {
                    if (e == null) {
                        ToastTool.showToast("短信验证码发送成功" + "短信id：" + integer, 1);

                    } else {

                        ToastTool.showToast("短信验证码发送失败" + "错误信息" + e.toString(), 0);
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
    public void resetPassDone() {
           //重置密码成功后跳转到登陆页面
        startActivity(new Intent(SeetingFogetPwd.this, LoginActivity.class));
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
