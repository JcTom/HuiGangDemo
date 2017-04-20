package com.suctan.huigangdemo.mvp.login.postRelease;

import com.example.androidbase.mvp.BaseMvpView;
import com.suctan.huigangdemo.bean.user.CourseBean;

/**
 * Created by B-305 on 2017/4/19.
 */

public interface postReleaseView extends BaseMvpView{
    void  loadCourseDone(CourseBean courseBean);
}
