package com.suctan.huigangdemo.mvp.login.ModifityPsw;

import com.example.androidbase.mvp.BaseMvpView;
import com.suctan.huigangdemo.bean.user.CourseBean;

/**
 * Created by B-305 on 2017/4/19.
 */

    //修改密码,定义一个接口,去继承baseMvpview 这个类,并且底下返回这个courseBean的对象
    public interface ModifityPswView extends BaseMvpView {
        void  loadCourseDone(CourseBean courseBean);

    }
