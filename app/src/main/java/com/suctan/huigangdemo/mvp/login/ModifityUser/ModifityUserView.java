package com.suctan.huigangdemo.mvp.login.ModifityUser;

import com.example.androidbase.mvp.BaseMvpView;
import com.suctan.huigangdemo.bean.user.CourseBean;

/**
 * Created by Tom on 2017/4/11.
 */

public interface ModifityUserView extends BaseMvpView {
    void loadCourseDone(CourseBean courseBean);

    void LoginQuitSuc();

}
