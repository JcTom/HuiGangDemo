package com.example.androidbase.mvp;

/**
 * Created by chenxiaozhou on 16/6/20.
 */
public interface BaseMvpView {

    void getDataFail(String msg);

    void showLoading();

    void hideLoading();

}
