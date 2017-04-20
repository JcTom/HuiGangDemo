package com.suctan.huigangdemo.mvp.login;

import android.util.Log;

import com.example.androidbase.BaseApplication;
import com.example.androidbase.rxjava.ApiCallback;
import com.example.androidbase.rxjava.SubscriberCallBack;
import com.example.androidbase.utils.ACache;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.suctan.huigangdemo.acache.CurrentUser;
import com.suctan.huigangdemo.bean.user.LoginReturn;
import com.suctan.huigangdemo.bean.user.Result;
import com.suctan.huigangdemo.bean.user.Users;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

/**
 * create at 2017/3/23 16:31
 *
 * @author：LZH
 * @explain 登录Presener
 */

public class LoginPresener extends DemoBasePresenter<LoginView> {
    private final static String TAG = "LoginPresener";

    public LoginPresener(LoginView mvpView) {
        attachView(mvpView);
    }

    /**
     * @param map Map
     * @explain 请求登录
     */
    //请求登录
    public void getLoginAction(Map map) {
        addSubscription(apiStores.getLoginReturnMessage(map),   //发送网络请求 实现回调方法
                new SubscriberCallBack<>(new ApiCallback<LoginReturn>() {

                    @Override
                    public void onStart() {
                        mvpView.showLoading();
                    }

                    @Override
                    public void onCompleted() {
                        Log.i(TAG, "onCompleted: ");
                    }

                    @Override
                    public void onSuccess(LoginReturn model) {
                        if (model != null) {
                            if (model.getStatus() == 1) {

                                Log.i(TAG, "token是：" + model.getToken() + model.getStatus() + "msg是" + model.getMsg());
                                InsertTokenToCace(model.getToken());//缓存token
                                getCurrentUser(model.getToken());   //获取登陆的用户信息
                                mvpView.loginGoMain();
                            } else {
                                mvpView.getDataFail("用户名或者密码错误");
                            }
                        }
                    }

                    @Override
                    public void onFailed(String msg) {
                        mvpView.getDataFail(msg);
                    }
                }));
    }

    /**
     * @param userToken String
     * @explain 获取用户信息
     */

    public void getCurrentUser(final String userToken) {
        Map mapToken = new HashMap();
        mapToken.put("user_token", userToken);
        Log.i(TAG, "参数token是：" + userToken);
        addSubscription(apiStores.getUserReturnMessage(mapToken),
                new SubscriberCallBack<>(new ApiCallback<String>() {
                    @Override
                    public void onStart() {
                        Log.i(TAG, "onStart: ");
                    }

                    @Override
                    public void onCompleted() {
                        Log.i(TAG, "onCompleted: ");
                        mvpView.hideLoading();
                    }

                    @Override
                    public void onSuccess(String model) {
                        Log.i(TAG, "onSuccess: " + model);
                        if (model != null && !model.isEmpty()) {
                            Gson gson = new Gson();
                            Type userType = new TypeToken<Result<Users>>() {
                            }.getType();
                            Result<Users> Result = gson.fromJson(model, userType);
                            if (Result.getStatus() == 1) {
                                Users user = Result.getUsers();
                                Log.i(TAG, "onSuccess: 电话号码是" + user.getUser_phone());
                                InsertToCace(user);
                                Log.i(TAG, "onSuccess: 执行缓存数据");
                                mvpView.loginMessageReturn(user);
                            } else {
                            }
                        } else {
                            mvpView.getDataFail("用户获取失败");
                        }
                    }

                    @Override
                    public void onFailed(String msg) {
                        Log.i(TAG, "onFailed: ");
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
                Log.i(TAG, "InsertToCace:  对象" + CurrentUser.getInstance().getUserBean());
                Log.i(TAG, "InsertToCace: setUserBean 成功");
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
        aCacheToken.put("nowToken", UserToken);//暂时去掉时间
    }
}
package com.suctan.huigangdemo.mvp.login;

import android.util.Log;

import com.example.androidbase.BaseApplication;
import com.example.androidbase.rxjava.ApiCallback;
import com.example.androidbase.rxjava.SubscriberCallBack;
import com.example.androidbase.utils.ACache;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.suctan.huigangdemo.acache.CurrentUser;
import com.suctan.huigangdemo.bean.user.LoginReturn;
import com.suctan.huigangdemo.bean.user.Result;
import com.suctan.huigangdemo.bean.user.Users;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

/**
 * create at 2017/3/23 16:31
 *
 * @author：LZH
 * @explain 登录Presener
 */

public class LoginPresener extends DemoBasePresenter<LoginView> {
    private final static String TAG = "LoginPresener";

    public LoginPresener(LoginView mvpView) {
        attachView(mvpView);
    }

    /**
     * @param map Map
     * @explain 请求登录
     */
    //请求登录
    public void getLoginAction(Map map) {
        addSubscription(apiStores.getLoginReturnMessage(map),   //发送网络请求 实现回调方法
                new SubscriberCallBack<>(new ApiCallback<LoginReturn>() {

                    @Override
                    public void onStart() {
                        mvpView.showLoading();
                    }

                    @Override
                    public void onCompleted() {
                        Log.i(TAG, "onCompleted: ");
                    }

                    @Override
                    public void onSuccess(LoginReturn model) {
                        if (model != null) {
                            if (model.getStatus() == 1) {

                                Log.i(TAG, "token是：" + model.getToken() + model.getStatus() + "msg是" + model.getMsg());
                                InsertTokenToCace(model.getToken());//缓存token
                                getCurrentUser(model.getToken());   //获取登陆的用户信息
                                mvpView.loginGoMain();
                            } else {
                                mvpView.getDataFail("用户名或者密码错误");
                            }
                        }
                    }

                    @Override
                    public void onFailed(String msg) {
                        mvpView.getDataFail(msg);
                    }
                }));
    }

    /**
     * @param userToken String
     * @explain 获取用户信息
     */

    public void getCurrentUser(final String userToken) {
        Map mapToken = new HashMap();
        mapToken.put("user_token", userToken);
        Log.i(TAG, "参数token是：" + userToken);
        addSubscription(apiStores.getUserReturnMessage(mapToken),
                new SubscriberCallBack<>(new ApiCallback<String>() {
                    @Override
                    public void onStart() {
                        Log.i(TAG, "onStart: ");
                    }

                    @Override
                    public void onCompleted() {
                        Log.i(TAG, "onCompleted: ");
                        mvpView.hideLoading();
                    }

                    @Override
                    public void onSuccess(String model) {
                        Log.i(TAG, "onSuccess: " + model);
                        if (model != null && !model.isEmpty()) {
                            Gson gson = new Gson();
                            Type userType = new TypeToken<Result<Users>>() {
                            }.getType();
                            Result<Users> Result = gson.fromJson(model, userType);
                            if (Result.getStatus() == 1) {
                                Users user = Result.getUsers();
                                Log.i(TAG, "onSuccess: 电话号码是" + user.getUser_phone());
                                InsertToCace(user);
                                Log.i(TAG, "onSuccess: 执行缓存数据");
                                mvpView.loginMessageReturn(user);
                            } else {
                            }
                        } else {
                            mvpView.getDataFail("用户获取失败");
                        }
                    }

                    @Override
                    public void onFailed(String msg) {
                        Log.i(TAG, "onFailed: ");
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
                Log.i(TAG, "InsertToCace:  对象" + CurrentUser.getInstance().getUserBean());
                Log.i(TAG, "InsertToCace: setUserBean 成功");
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
        aCacheToken.put("nowToken", UserToken);//暂时去掉时间
    }
}
