package com.suctan.huigangdemo.mvp.login.singup;

import android.util.Log;

import com.example.androidbase.rxjava.ApiCallback;
import com.example.androidbase.rxjava.SubscriberCallBack;
import com.example.androidbase.utils.ToastTool;
import com.suctan.huigangdemo.bean.user.ModifyReturn;
import com.suctan.huigangdemo.mvp.login.DemoBasePresenter;

import java.util.Map;

/**
 * Created by Tom on 2017/4/11.
 */
public class SingUpPresenter extends DemoBasePresenter<SingUpView> {
    public final static String TAG = "SingUpPresenter";
    public SingUpPresenter(SingUpView singUpView) {
        attachView(singUpView);
    }

    /**
     * @param map Map
     * @explain 请求注册
     */
    public void registerAction(Map map) {
        addSubscription(apiStores.Register(map),   //发送网络请求 实现回调方法
                new SubscriberCallBack<>(new ApiCallback<ModifyReturn>() {

                    @Override
                    public void onStart() {
                        Log.i(TAG, "onStart: ");
                    }
                    @Override
                    public void onSuccess(ModifyReturn model) {
                        ToastTool.showToast("onSuccess: 返回状态是:"+model.getStatus()+"msg："+model.getMsg(),1);
                        Log.i(TAG, "onSuccess: 返回状态是:"+model.getStatus()+"msg："+model.getMsg());
                        mvpView.registerDone();
                    }

                    @Override
                    public void onFailed(String msg) {
                        Log.i(TAG, "onFailed: ");

                    }

                    @Override
                    public void onCompleted() {

                    }
                }));


    }


}
