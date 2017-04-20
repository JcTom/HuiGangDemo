package com.suctan.huigangdemo.activity.signup;

import android.os.Bundle;
import android.view.View;
import com.example.androidbase.mvp.MvpActivity;
import com.suctan.huigangdemo.R;
import com.suctan.huigangdemo.bean.user.CourseBean;
import com.suctan.huigangdemo.mvp.login.singup.SingUpPresenter;
import com.suctan.huigangdemo.mvp.login.singup.SingUpView;

/**
 * Created by B-305 on 2017/4/19.
 */

public class signupActivity extends MvpActivity<SingUpPresenter> implements SingUpView,View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

    }

    @Override
    public void onClick(View v) {

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
    protected SingUpPresenter createPresenter() {
        return null;
    }
}
