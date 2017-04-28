package com.suctan.huigangdemo.mvp.login;

import com.suctan.huigangdemo.bean.address.AddAddressResult;
import com.suctan.huigangdemo.bean.topic.AddCommentBean;
import com.suctan.huigangdemo.bean.user.ComomBeanReturn;
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
    String ServerUrl = "http://10.5.12.125/tp/index.php/home/index/";
//    String ServerUrl = "http://119.29.137.109/tp/index.php/home/index/";
//        String ServerUrl = "http://119.29.137.109/hello/";
/**********************************************************************************************************************/
    /**
     * 用户登录请求
     */
    @POST("login")
    Observable<LoginReturn> getLoginReturnMessage(@QueryMap Map<String, Object> loginMap);

    /**
     * 用户注册请求
     */
    @POST("register")
    Observable<ModifyReturn> Register(@QueryMap Map<String, Object> loginMap);

    /**
     * 用户重置密码请求
     */
    @POST("resetPass")
    Observable<ModifyReturn> resetPass(@QueryMap Map<String, Object> loginMap);

    //    @POST("login.php")
//    Observable<LoginReturn> getLoginReturnMessage(@QueryMap Map<String, Object> loginMap);
//    @POST("login")
//    Observable<String> getHello(@QueryMap Map<String, Object> helloReturn);

    /**
     * 获取用户信息
     */
    @POST("get_userInfo")
    Observable<String> getUserReturnMessage(@QueryMap Map<String, Object> userReturn);
    /**

     * 发布共享需求
     */
    @POST("pub_need")
    Observable<ModifyReturn> PubNeed(@QueryMap Map<String, Object> needReturn);


    /**
     * 发布我要吃的饭菜
     */
    @POST("pub_eatFood")
    Observable<ModifyReturn> PubEatFood(@QueryMap Map<String, Object> needReturn);

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
    Observable<ModifyReturn> postReleaseReturn(@QueryMap Map<String, Object> topicReturn);

    /**
     * 发布评论
     */
    @POST("pub_topic_comment")
    Observable<AddCommentBean> postComment(@QueryMap Map<String, Object> topicReturn);

    /**
     * 获取话题列表
     */
    @POST("get_topicS")
    Observable<String> getPostTopicList(@QueryMap Map<String, Object> topicReturn);


    /**
     * 获取话题列表
     */
    @POST("look_topic")
    Observable<String> getTopicCommentListReturn(@QueryMap Map<String, Object> topiccoment);


    /**
     * 首页获取所有轮播图
     */
    @POST("get_ad_photo")
    Observable<String> getRollPageListReturn(@QueryMap Map<String, Object> rollviewReturn);


    /**
     * 首页获取所有菜列表
     */
    @POST("get_makeFoodList")
    Observable<String> getEatFoodList(@QueryMap Map<String, Object> EatListReturn);


 

    /**
     * 我的钱包,里面的充值功能
     */
    @POST("put_money")
    Observable<ModifyReturn> addmoneyReturn(@QueryMap Map<String, Object> moneyReturn);

    /**
     * 我的钱包,里面的显示金额功能
     */
    @POST("set_money")
    Observable<ModifyReturn> MymoneyReturn( @QueryMap Map<String, Object>  moneyReturn);

    /**
     * 我的钱包,里面的提现功能
     */
    @POST("get_money")
    Observable<ModifyReturn> outmoneyReturn(@QueryMap Map<String, Object> outmoneyReturn);



    /**
     * 地址管理,里面的添加地址管理功能,目前这个功能待定
     */
    @POST("add_address")
    Observable<AddAddressResult> addressReturn(@QueryMap Map<String, Object> addaddressReturn);


    @POST("update_address")
    Observable<ComomBeanReturn> changeAddressReturn(@QueryMap Map<String, Object> changeaddressReturn);


    @POST("show_address")
    Observable<String> getAddressListReturn(@QueryMap Map<String, Object> addaddressList);



    /**
     * 发布共享需求
     */
    @POST("get_needList")
    Observable<String> getNeedList(@QueryMap Map<String, Object> needReturn);

    /*发布我想要吃*/
    @POST("pub_eatFood")
    Observable<ComomBeanReturn> PostWantEatReturn(@QueryMap Map<String, Object> wanteatReturn);

    /*发布我想要吃*/
    @POST("get_eatFoodList")
    Observable<String> getDoWantListReturn(@QueryMap Map<String, Object> doeatReturn);

    /*进入推荐获取同厨数据*/
    @POST("look_makeFood")
    Observable<String> getEatOrtherReturn(@QueryMap Map<String, Object> lookMakefoodReturn);

    /**
     * 添加购物车
     */

    @POST("add_shopCart")
    Observable<ComomBeanReturn> addCartReturn(@QueryMap Map<String, Object> addCartReturn);


    /*获取购物车列表*/
    @POST("look_shopCart")
    Observable<String> getCartListReturn(@QueryMap Map<String, Object> getCartListReturn);


    /*修改购物车数量*/
    @POST("update_shopCart")
    Observable<String> changeCartCountReturn(@QueryMap Map<String, Object> changCartReturn);

    /*结算购物车*/
    @POST("pay_shopCart")
    Observable<ComomBeanReturn> payCarttReturn(@QueryMap Map<String, Object> payCartReturn);


    /*删除购物车*/
    @POST("delete_shopCart")
    Observable<ComomBeanReturn> deleteCartReturn(@QueryMap Map<String, Object> deleteCartReturn);


    /**********************************************************************************************/
                                                   /*订单*/

/*我要吃所有推荐*/
    @POST("cusAllEatOrder")
    Observable<String> getBuyAllOrderListReturn(@QueryMap Map<String, Object> allorderReturn);

    /*我要吃等待接单推荐*/
    @POST("cusWaitEatOrder")
    Observable<String> getBuyWaitReceiveOrderListReturn(@QueryMap Map<String, Object> waitOrderReturn);

    /*我要吃等待送餐推荐*/
    @POST("cusDeliveryEatOrder")
    Observable<String> getBuyWaitSendOrderListReturn(@QueryMap Map<String, Object> waitSendReturn);

    /*我要吃等待完成推荐*/
    @POST("cusFinishEatOrder")
    Observable<String> getBuyWaitFinishOrderListReturn(@QueryMap Map<String, Object> finishOrderReturn);

    /*定做我要吃的饭*/
    /*我要吃所有定做*/
    @POST("cusAllMakeOrder")
    Observable<String> getBuyMakeAllOrderListReturn(@QueryMap Map<String, Object> allOrderReturn);

    /*我要吃等待接单定做*/
    @POST("cusWaitMakeOrder")
    Observable<String> getBuyMakeWaitOrderListReturn(@QueryMap Map<String, Object> waitOrderReturn);

    /*我要吃等待送餐定做*/
    @POST("cusDeliveryMakeOrder")
    Observable<String> getBuyMakeWaitSendOrderListReturn(@QueryMap Map<String, Object> waitSendOrderReturn);

    /*我要吃已完成定做*/
    @POST("cusFinishMakeOrder")
    Observable<String> getBuyMakeFinishOrderListReturn(@QueryMap Map<String, Object> finishOrderReturn);

}
