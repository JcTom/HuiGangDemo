package com.suctan.huigangdemo.mvp.login.Addaddress;

import com.example.androidbase.rxjava.ApiCallback;
import com.example.androidbase.rxjava.SubscriberCallBack;
import com.suctan.huigangdemo.bean.user.ModifyReturn;
import com.suctan.huigangdemo.mvp.login.DemoBasePresenter;

import java.util.Map;

/**
 * Created by B-305 on 2017/4/20.
 */

public class addressPresenter extends DemoBasePresenter<addressView>{
    public addressPresenter(addressView mvpView){
        attachView(mvpView);
    }
    //把在activity里面定义好的参数,把封装在map里面的数据提取过来.
    public void addressActoin(Map map){
        addSubscription(apiStores.addressReturn(map),
                new SubscriberCallBack<>(new ApiCallback<ModifyReturn>() {
                    @Override
                    public void onStart() {
                        System.out.println("onStart");
                    }

                    @Override
                    public void onSuccess(ModifyReturn model) {
                        System.out.println("onSuccess");
                        System.out.println("status="+model.getStatus()+"msg="+model.getMsg());
                    }


                    @Override
                    public void onFailed(String msg) {
                        System.out.println("status="+msg);
                        System.out.println("onFailed");
                    }

                    @Override
                    public void onCompleted() {
                        mvpView.hideLoading();
                    }
                })
        );

    }
}
