package com.suctan.huigangdemo.mvp.login;


import com.example.androidbase.BaseApplication;

import com.example.androidbase.rxjava.ApiCallback;
import com.example.androidbase.rxjava.SubscriberCallBack;
import com.example.androidbase.utils.ACache;
import com.google.gson.Gson;
import com.suctan.huigangdemo.bean.user.CurrentUser;
import com.suctan.huigangdemo.bean.user.GetUserReturn;
import com.suctan.huigangdemo.bean.user.LoginReturn;
import com.suctan.huigangdemo.bean.user.Users;
import com.suctan.huigangdemo.util.JSONParstObject;


import java.util.HashMap;
import java.util.Map;

/**
 * create at 2017/3/23 16:31
 *
 * @author：LZH
 * @explain 登录Presener
 */

public class LoginPresener extends DemoBasePresenter<LoginView> {

    public LoginPresener(LoginView mvpView) {
        attachView(mvpView);
    }

    //请求登录
    public void getLoginAction(Map map) {
        addSubscription(apiStores.getLoginReturnMessage(map),
                new SubscriberCallBack<>(new ApiCallback<LoginReturn>() {
                    @Override
                    public void onStart() {
                        mvpView.showLoading();
                    }

                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onSuccess(LoginReturn model) {
                        if (model != null) {
                            if (model.getToken() != null) {
                                getCurrentUser(model.getToken());
                            } else {
                                mvpView.getDataFail("用户名或者密码错误");
                            }
                        }
                    }

                    @Override
                    public void onFaild(String msg) {
                        mvpView.getDataFail(msg);
                        mvpView.hideLoading();
                    }
                }));
    }


    public void getCurrentUser(final String userToken) {
        Map mapUser = new HashMap();
        mapUser.put("action", "get_user_info");
        mapUser.put("token", userToken);
        addSubscription(apiStores.getUserReturnMessage(mapUser),
                new SubscriberCallBack<>(new ApiCallback<GetUserReturn>() {
                    @Override
                    public void onStart() {
                    }

                    @Override
                    public void onCompleted() {
                        mvpView.hideLoading();
                    }

                    @Override
                    public void onSuccess(GetUserReturn model) {
                        System.out.println("用户信息" + model.getDatas());
                        if (model != null) {
                            if (model.getStatus().equals("success")) {
                                if (model.getDatas() != null && !model.getDatas().isEmpty()) {
                                    Users users = JSONParstObject.GetUserJSonObject(model.getDatas());
                                    if (users != null) {
                                        InsertTokenToCace(userToken);
                                        users.setToken(userToken);
                                        InsertToCace(users);
                                        mvpView.loginMessageReturn(users);
                                    }
                                }
                            }
                        } else {
                            mvpView.getDataFail("用户获取失败");
                        }
                    }

                    @Override
                    public void onFaild(String msg) {
                        mvpView.getDataFail(msg);
                    }
                }));
    }

    /**
     * created at 2017/3/23 16:45
     *
     * @param mUser 当前登录的用户对象
     * @explain 缓存用户信息
     */
    public void InsertToCace(Users mUser) {
        //保存于缓存中
        ACache aCache = ACache.get(BaseApplication.getContext());
        aCache.put("User", new Gson().toJson(mUser));
        //从缓存中获取对象
        String userStr = aCache.getAsString("User");
        if (userStr != null) {
            Users userBean = new Gson().fromJson(userStr, Users.class);
            if (userBean != null) {
                CurrentUser.getInstance().setUserBean(userBean);
            }
        }
    }

    /**
     * created at 2017/3/23 16:52
     *
     * @param UserToken 用户请求服务器的key
     * @explain 缓存用户的token 实现token的时间验证
     */
    public void InsertTokenToCace(String UserToken) {
        ACache aCacheToken = ACache.get(BaseApplication.getContext());
//        aCacheToken.put("nowToken", UserToken, 10800);
        aCacheToken.put("nowToken", UserToken);//暂时去掉时间
    }
}
