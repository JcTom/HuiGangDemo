package com.suctan.huigangdemo.mvp.login;

import com.suctan.huigangdemo.bean.user.GetUserReturn;
import com.suctan.huigangdemo.bean.user.LoginReturn;

import java.util.Map;

import retrofit2.http.POST;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * Created by LZH on 16/11/6.
 */

public interface ApiStores {
    String ServerUrl = "http://112.74.195.131:8666/api/";
/**********************************************************************************************************************/
    /**
     * 用户登录请求
     */
    @POST("Login.ashx")
    Observable<LoginReturn> getLoginReturnMessage(@QueryMap Map<String, Object> loginMap);

    /**
     * 获取用户信息
     */
    @POST("User.ashx")
    Observable<GetUserReturn> getUserReturnMessage(@QueryMap Map<String, Object> userReturn);
}
