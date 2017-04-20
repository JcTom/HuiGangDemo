package com.suctan.huigangdemo.mvp.login.resetpass;

import android.util.Log;

import com.example.androidbase.rxjava.ApiCallback;
import com.example.androidbase.rxjava.SubscriberCallBack;
import com.example.androidbase.utils.ToastTool;
import com.suctan.huigangdemo.bean.user.LoginReturn;
import com.suctan.huigangdemo.bean.user.ModifyReturn;
import com.suctan.huigangdemo.mvp.login.DemoBasePresenter;

import java.util.Map;

/**
 * Created by Tom on 2017/4/11.
 */
public class ResetPassPresenter extends DemoBasePresenter<ResetPassView> {
    public final static String TAG = "SingUpPresenter";
    public ResetPassPresenter(ResetPassView resetPassView) {
        attachView(resetPassView);
    }

    /**
     * @param map Map
     * @explain 请求重置密码
     */
    //请求登录
    public void resetPassAction(Map map) {
        addSubscription(apiStores.resetPass(map),   //发送网络请求 实现回调方法
                new SubscriberCallBack<>(new ApiCallback<ModifyReturn>() {

                    @Override
                    public void onStart() {
                        Log.i(TAG, "onStart: ");
                    }

                    @Override
                    public void onSuccess(ModifyReturn model) {
                        mvpView.resetPassDone();
                        ToastTool.showToast("onSuccess: 返回状态是:"+model.getStatus()+"msg："+model.getMsg(),1);
                        Log.i(TAG, "onSuccess: 返回状态是:"+model.getStatus()+"msg："+model.getMsg());
                    }


                    @Override
                    public void onFailed(String msg) {
                    }

                    @Override
                    public void onCompleted() {

                    }
                }));


    }


}
