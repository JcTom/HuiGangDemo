package com.suctan.huigangdemo.mvp.login.myMydiscount;

import com.example.androidbase.rxjava.ApiCallback;
import com.example.androidbase.rxjava.SubscriberCallBack;
import com.suctan.huigangdemo.mvp.login.DemoBasePresenter;

import java.util.HashMap;


/**
 * Created by Tom on 2017/4/25.
 */

public class MydiscountPresenter extends DemoBasePresenter<MydiscountView> {

    public MydiscountPresenter(MydiscountView mvpView){
        attachView(mvpView);
    }
    /*
    * 优惠劵
    * */
    /*public void getMydiscount() {
        addSubscription(apiStores.getMydiscount(new HashMap<String, Object>()),
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
                        System.out.println("首页获取菜的列表" + model);
                    }
                    @Override
                    public void onFailed(String msg) {
                        mvpView.getDataFail(msg);
                    }
                }));
    }*/

}
