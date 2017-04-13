package com.suctan.huigangdemo.mvp.login;


import android.util.Log;
import android.widget.Toast;

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

    private String TAG="LoginPresener";

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
                        System.out.println("onStart");
                    }
                    @Override
                    public void onCompleted() {
                        System.out.println("onCompleted");
                    }

                    @Override
                    public void onSuccess(LoginReturn model) {
                        if (model != null) {
                            System.out.println("onSuccess ");
                            if (model.getStatus().equals("1")) {
                                mvpView.loginGoMain();
                                Log.i(TAG, "我是状态成功status->"+model.getStatus()+"msg->"+model.getMsg()+"token->"+model.getToken());
                                getCurrentUser(model.getToken());
                            } else {
                                mvpView.loginGoMain();
                                Log.i(TAG, "我是状态失败status->"+model.getStatus()+"msg->"+model.getMsg()+"token->"+model.getToken());
                                mvpView.getDataFail("用户名或者密码错误");
                            }
                        }
                    }


                    @Override
                    public void onFailed(String msg) {
                        mvpView.loginGoMain();
                        System.out.println("onFailed msg"+msg);
                        mvpView.getDataFail(msg);
                        mvpView.hideLoading();
                    }
                }));
    }


    public void getCurrentUser(final String userToken) {
        Map mapUser = new HashMap();
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
                    public void onFailed(String msg) {
                        mvpView.getDataFail(msg);
                    }
                }));
    }




    public void getHelloText() {
        Map map=new HashMap();
        map.put("username", "18942433927");
        map.put("password", 94682431);
//       String user  = "?&user=liheming";
        addSubscription(apiStores.getHello(map),
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
                        Toast.makeText(BaseApplication.getContext(),model,Toast.LENGTH_LONG).show();

                        Log.i(TAG, "onSuccess: "+model);
                    }

                    @Override
                    public void onFailed(String msg) {
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
