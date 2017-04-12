package com.suctan.huigangdemo.mvp.login.index.home;

import com.example.androidbase.mvp.BaseMvpView;
import com.suctan.huigangdemo.bean.user.HomeBean;

/**
 * Created by Administrator on 2017/3/4.
 */

public interface HomeView extends BaseMvpView {
    void homeInfoDone(HomeBean homeBean);
}
