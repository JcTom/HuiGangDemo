package com.suctan.huigangdemo.mvp.login.buyorder;

import com.example.androidbase.rxjava.ApiCallback;
import com.example.androidbase.rxjava.SubscriberCallBack;
import com.suctan.huigangdemo.bean.user.ModifyReturn;
import com.suctan.huigangdemo.mvp.login.DemoBasePresenter;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Tom on 2017/4/11.
 */

public class MyBuyOrderPresenter extends DemoBasePresenter<MyBuyOrderView> {
    public MyBuyOrderPresenter(MyBuyOrderView mvpView) {
        attachView(mvpView);
    }

    public void getMyOrderList(Map map) {
        addSubscription(apiStores.ModifyUserReturn(new HashMap<String, Object>()),
                new SubscriberCallBack<>(new ApiCallback<ModifyReturn>() {
                    @Override
                    public void onStart() {

                    }

                    @Override
                    public void onCompleted() {
                        mvpView.hideLoading();
                    }

                    @Override
                    public void onSuccess(ModifyReturn model) {

                    }

                    @Override
                    public void onFailed(String msg) {
                        mvpView.getDataFail(msg);
                    }
                }));
    }


}