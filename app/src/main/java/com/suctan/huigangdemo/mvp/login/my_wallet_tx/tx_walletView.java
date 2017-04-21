package com.suctan.huigangdemo.mvp.login.my_wallet_tx;

import com.example.androidbase.mvp.BaseMvpView;
import com.suctan.huigangdemo.bean.user.CourseBean;

/**
 * Created by B-305 on 2017/4/20.
 */

public interface tx_walletView extends BaseMvpView{
    void  loadCourseDone(CourseBean courseBean);
}
