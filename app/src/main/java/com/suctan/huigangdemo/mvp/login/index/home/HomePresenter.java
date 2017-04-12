package com.suctan.huigangdemo.mvp.login.index.home;


import com.suctan.huigangdemo.mvp.login.DemoBasePresenter;

/**
 * Created by Administrator on 2017/3/4.
 */

public class HomePresenter extends DemoBasePresenter<HomeView> {
    public HomePresenter(HomeView mvpView) {
        attachView(mvpView);
    }
}