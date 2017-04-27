package com.suctan.huigangdemo.mvp.login.ModifityUser;

import android.widget.Toast;

import com.example.androidbase.BaseApplication;
import com.example.androidbase.rxjava.ApiCallback;
import com.example.androidbase.rxjava.SubscriberCallBack;
import com.example.androidbase.utils.ACache;
import com.google.gson.Gson;
import com.suctan.huigangdemo.acache.CurrentUser;
import com.suctan.huigangdemo.acache.HuiGangApplication;
import com.suctan.huigangdemo.bean.user.ModifyReturn;
import com.suctan.huigangdemo.bean.user.Users;
import com.suctan.huigangdemo.mvp.login.DemoBasePresenter;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Tom on 2017/4/11.
 */
//这个presenter 写的是,设置里面的退出登录
public class ModifityUserPresenter extends DemoBasePresenter<ModifityUserView> {
    Users ccurrentUser;

    public ModifityUserPresenter(ModifityUserView mvpView) {
        attachView(mvpView);
    }

    public void MoidifytyUser(Map map, final int resultQuest, final String data) {

        ccurrentUser = CurrentUser.getInstance().getUserBean();
        addSubscription(apiStores.ModifyUserReturn(map),
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
                        if (model.getStatus() == 1) {
                            getCacheDeal(resultQuest, data);
                        }
                        Toast.makeText(BaseApplication.getContext(), "修改用户信息" + model.getStatus() + model.getMsg(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailed(String msg) {
                        mvpView.getDataFail(msg);
                    }
                }));
    }

    public void LoginQuit(Map map) {
        addSubscription(apiStores.ModifyUserReturn(map),
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

    //处理需要缓存的信息
    public void getCacheDeal(int resultCode, String message) {
        switch (resultCode) {
            case 1003://获取用户名
                ccurrentUser.setUser_name(message);
                break;
            case 1004://验证身份

                break;
            case 1005://获取性别
                if (message.equals("男")) {
                    ccurrentUser.setUser_sex(0);
                } else {
                    ccurrentUser.setUser_sex(1);
                }
                break;
            case 1006://获取年龄段
                ccurrentUser.setUser_age(message);
                break;
            case 1007://获取学历
                ccurrentUser.setUser_education(message);
                break;
            case 1008://获取熟悉领域
                ccurrentUser.setUser_skill(message);
                break;
            case 1009://获取业余爱好
                ccurrentUser.setUser_hobby(message);
                break;
        }
        ACache aCache = ACache.get(BaseApplication.getContext());
        String oldUserStr = aCache.getAsString("User");
        //删除缓存中的数据
        if (oldUserStr != null) {
            aCache.remove("User");
        }
        //添加缓存数据
        aCache.put("User", new Gson().toJson(ccurrentUser));
        //获取数据缓存
        CurrentUser.getInstance().setUserBean(ccurrentUser);
    }
}

