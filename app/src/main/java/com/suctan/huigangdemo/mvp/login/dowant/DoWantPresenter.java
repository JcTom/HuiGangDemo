package com.suctan.huigangdemo.mvp.login.dowant;

import com.example.androidbase.rxjava.ApiCallback;
import com.example.androidbase.rxjava.SubscriberCallBack;
import com.suctan.huigangdemo.bean.dowant.DoWantOrderReturn;
import com.suctan.huigangdemo.bean.user.ModifyReturn;
import com.suctan.huigangdemo.mvp.login.DemoBasePresenter;
import com.suctan.huigangdemo.mvp.login.wanteat.WantEatView;
import com.suctan.huigangdemo.util.JSONParstObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Tom on 2017/4/11.
 */

public class DoWantPresenter extends DemoBasePresenter<DoWantView> {
    public DoWantPresenter(DoWantView mvpView) {
        attachView(mvpView);
    }

    //获取我要做的列表
    public void getDoWantList() {
        addSubscription(apiStores.getDoWantListReturn(new HashMap<String, Object>()),
                new SubscriberCallBack<>(new ApiCallback<String>() {
                    @Override
                    public void onStart() {

                    }

                    @Override
                    public void onCompleted() {
                        mvpView.hideLoading();
                    }

                    @Override
                    public void onSuccess(String model) {
                        if (model != null) {

                            DoWantOrderReturn doWantOrderReturn = JSONParstObject.getDoWantListObject(model);
                            if (doWantOrderReturn != null && doWantOrderReturn.getDoWantOrderBeenList() != null) {
                                mvpView.getDoWantOrderSuc(doWantOrderReturn.getDoWantOrderBeenList());
                            } else {
                                mvpView.getDoWantOrderFail();
                            }
                        }
                    }

                    @Override
                    public void onFailed(String msg) {
                        mvpView.getDataFail(msg);
                    }
                }));
    }
}
