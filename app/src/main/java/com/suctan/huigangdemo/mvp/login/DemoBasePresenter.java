package com.suctan.huigangdemo.mvp.login;


import com.example.androidbase.mvp.BasePresenter;
import com.example.androidbase.retrofit.ApiClient;

/**
 * Created by chenxiaozhou on 16/7/7.
 */
public class DemoBasePresenter<V> extends BasePresenter<V> {

    public ApiStores apiStores;

    @Override
    protected void initApiStores() {
        apiStores = ApiClient.retrofit(ApiStores.ServerUrl).create(ApiStores.class);
    }
}
