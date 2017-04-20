package com.suctan.huigangdemo.mvp.login;

import com.suctan.huigangdemo.bean.user.GetUserReturn;
import com.suctan.huigangdemo.bean.user.LoginReturn;
import com.suctan.huigangdemo.bean.user.ModifyReturn;

import java.util.Map;

import retrofit2.http.POST;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * Created by LZH on 16/11/6.
 */

public interface ApiStores {
    //    String ServerUrl = "http://112.74.195.131:8666/api/";
//String ServerUrl = "http://10.0.2.2/tp/index.php/home/index/login_test/username/合明/password/94682431/";
//    String ServerUrl = "http://10.5.12.125/tp/index.php/home/index/";
    String ServerUrl = "http://119.29.137.109/tp/index.php/home/index/";
//        String ServerUrl = "http://119.29.137.109/hello/";
/**********************************************************************************************************************/
    /**
     * 用户登录请求
     */
    @POST("login")
    Observable<LoginReturn> getLoginReturnMessage(@QueryMap Map<String, Object> loginMap);

    //    @POST("login.php")
//    Observable<LoginReturn> getLoginReturnMessage(@QueryMap Map<String, Object> loginMap);
//    @POST("login")
//    Observable<String> getHello(@QueryMap Map<String, Object> helloReturn);
    /**
     * 获取用户信息
     */
    @POST("get_userInfo")
    Observable<String> getUserReturnMessage(@QueryMap Map<String, Object> userReturn);  /**

     * 发布共享需求
     */
    @POST("pub_need")
    Observable<ModifyReturn> PubNeedReturn(@QueryMap Map<String, Object> needReturn);

    /* 发布共享需求
     */
    @POST("pub_need")
    Observable<ModifyReturn> PubNoticeReturn(@QueryMap Map<String, Object> needReturn);

    /**
     * 修改用户信息
     */
    @POST("update_userInfo")
    Observable<ModifyReturn> ModifyUserReturn(@QueryMap Map<String, Object> userReturn);

    /**
     * 修改用户密码
     */
    @POST("update_pass")
    Observable<ModifyReturn> ModifyPswReturn(@QueryMap Map<String, Object> userReturn);

    /**
     * 发布帖子或者话题
     */
    @POST("pub_ topic")
    Observable<ModifyReturn> postReleaseReturn(@QueryMap Map<String, Object> userReturn);

}
