package com.suctan.huigangdemo.mvp.login.share;

import com.example.androidbase.rxjava.ApiCallback;
import com.example.androidbase.rxjava.SubscriberCallBack;
import com.suctan.huigangdemo.bean.user.ModifyReturn;
import com.suctan.huigangdemo.mvp.login.DemoBasePresenter;

import java.util.Map;

/**
 * Created by haily on 2017/4/19.
 */

public class PubNeedPresener extends DemoBasePresenter<PubNeedView> {
    public PubNeedPresener(PubNeedView mvpView) {
        attachView(mvpView);
    }

    /**
     * @param map Map
     * @explain 请求发布共享需求
     */
    public void pub_needAction(Map map) {

        addSubscription(apiStores.PubNeed(map),
                new SubscriberCallBack<>(new ApiCallback<ModifyReturn>() {
                    @Override
                    public void onStart() {

                        System.out.println("onStart");
                    }

                    @Override
                    public void onSuccess(ModifyReturn model) {
                        System.out.println("onSuccess");
                        System.out.println("status="+model.getStatus()+"msg="+model.getMsg());
                        mvpView.pubNeedGoBack();
                    }


                    @Override
                    public void onFailed(String msg) {
                        System.out.println("status="+msg);
                        System.out.println("onFailed");

                    }

                    @Override
                    public void onCompleted() {

                    }
                }));  //发送网络请求 实现回调方法

    }
}
