package com.suctan.huigangdemo.mvp.login.Addaddress;

import com.example.androidbase.rxjava.ApiCallback;
import com.example.androidbase.rxjava.SubscriberCallBack;
import com.suctan.huigangdemo.bean.user.ModifyReturn;
import com.suctan.huigangdemo.mvp.login.DemoBasePresenter;

import java.util.Map;

/**
 * Created by B-305 on 2017/4/20.
 */

public class addressPresenter extends DemoBasePresenter<addressView> {
    public addressPresenter(addressView mvpView) {
        attachView(mvpView);
    }

    //把在activity里面定义好的参数,把封装在map里面的数据提取过来.
    /*添加用户地址*/
    public void addAddressActoin(Map map) {
        addSubscription(apiStores.addressReturn(map),
                new SubscriberCallBack<>(new ApiCallback<String>() {
                    @Override
                    public void onStart() {
                        System.out.println("onStart");
                    }

                    @Override
                    public void onSuccess(String model) {
                        mvpView.addAdressSuc();
                        System.out.println("添加地址回调信息" + model);
                    }


                    @Override
                    public void onFailed(String msg) {
                        System.out.println("status=" + msg);
                    }

                    @Override
                    public void onCompleted() {
                        mvpView.hideLoading();
                    }
                })
        );

    }


    /*获取用户地址列表*/
    public void getAddressList(Map map) {
        addSubscription(apiStores.getAddressListReturn(map),
                new SubscriberCallBack<>(new ApiCallback<String>() {
                    @Override
                    public void onStart() {

                    }

                    @Override
                    public void onSuccess(String model) {
                        System.out.println("获取用户地址列表" + model);
                    }


                    @Override
                    public void onFailed(String msg) {
                        System.out.println("status=" + msg);
                    }

                    @Override
                    public void onCompleted() {
                        mvpView.hideLoading();
                    }
                })
        );

    }
}
