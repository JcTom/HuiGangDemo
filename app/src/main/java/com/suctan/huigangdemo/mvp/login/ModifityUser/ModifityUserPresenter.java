package com.suctan.huigangdemo.mvp.login.ModifityUser;

import android.widget.Toast;

import com.example.androidbase.BaseApplication;
import com.example.androidbase.rxjava.ApiCallback;
import com.example.androidbase.rxjava.SubscriberCallBack;
import com.suctan.huigangdemo.bean.user.ModifyReturn;
import com.suctan.huigangdemo.mvp.login.DemoBasePresenter;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Tom on 2017/4/11.
 */
//这个presenter 写的是,设置里面的退出登录
public class ModifityUserPresenter extends DemoBasePresenter<ModifityUserView> {
    public ModifityUserPresenter(ModifityUserView mvpView) {
        attachView(mvpView);
    }

    public void MoidifytyUser(Map map) {
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
                        Toast.makeText(BaseApplication.getContext(), "修改用户信息" + model.getStatus() + model.getMsg(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailed(String msg) {
                        mvpView.getDataFail(msg);
                    }
                }));
    }


    public void LoginQuit(Map map) {
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
                        mvpView.LoginQuitSuc();
                    }

                    @Override
                    public void onFailed(String msg) {
                        mvpView.getDataFail(msg);
                    }
                }));
    }
}
