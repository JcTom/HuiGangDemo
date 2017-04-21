package com.suctan.huigangdemo.activity.password;

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
import com.suctan.huigangdemo.mvp.login.ModifityPsw.ModifityPswPresenter;
import com.suctan.huigangdemo.mvp.login.ModifityPsw.ModifityPswView;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by B-305 on 2017/4/19.
 */

public class PasswordActivity extends MvpActivity<ModifityPswPresenter> implements ModifityPswView ,View.OnClickListener{

    @BindView(R.id.btn_psw)              //修改密码页面的确认按钮
    Button btn_psw;
    @BindView(R.id.Pswedit_phone)         //修改密码页面的手机号填写框
    EditText Pswedit_phone;
    @BindView(R.id.pswedit_old)               //修改密码页面的旧密码填写框
    EditText pswedit_old;
    @BindView(R.id.pswedit_new)               //修改密码页面的新密码填写框
    EditText pswedit_new;
    @BindView(R.id.psw_back)                   //修改密码页面的返回按钮
    ImageView psw_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modfity_password);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        //返回按钮点击事件
        psw_back.setOnClickListener(this);
        //确认修改密码点击事件
        btn_psw.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.psw_back:
                finish();
                break;
            case R.id.btn_psw:
                PswVariety();
                break;
        }

    }

    private void PswVariety() {
        String token = TokenManager.getToken();
        String psweditphone=Pswedit_phone.getText().toString().trim();
        String psweditold = pswedit_old.getText().toString().trim();
        String psweditnew = pswedit_new.getText().toString().trim();
        if (TextUtils.isEmpty(psweditphone)) {
            Toast.makeText(this, "请输入手机号", Toast.LENGTH_SHORT).show();
            return;
        } else if (TextUtils.isEmpty(psweditold)) {
            Toast.makeText(this, "请输入旧密码", Toast.LENGTH_SHORT).show();
            return;
        } else if (TextUtils.isEmpty(psweditnew)){
            Toast.makeText(this, "请输入新密码", Toast.LENGTH_SHORT).show();
            return;
            }

        Map<String, Object> map = new HashMap();
        map.put("user_token",token);
        map.put("user_pass",psweditold);
        map.put("user_newPass",psweditnew);
        mvpPresenter.ModifityPsw(map);

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
        ToastTool.showToast("修改密码成功",1);
    }

    @Override
    protected ModifityPswPresenter createPresenter() {
        return new ModifityPswPresenter(this);
    }
}
