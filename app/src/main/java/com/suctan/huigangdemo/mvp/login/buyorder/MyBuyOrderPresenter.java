package com.suctan.huigangdemo.mvp.login.buyorder;

import com.example.androidbase.rxjava.ApiCallback;
import com.example.androidbase.rxjava.SubscriberCallBack;
import com.suctan.huigangdemo.bean.order.buy.BuyRecommendReturn;
import com.suctan.huigangdemo.mvp.login.DemoBasePresenter;
import com.suctan.huigangdemo.util.JSONParstObject;

import java.util.Map;


/**
 * Created by Tom on 2017/4/11.
 */

public class MyBuyOrderPresenter extends DemoBasePresenter<MyBuyOrderView> {
    public MyBuyOrderPresenter(MyBuyOrderView mvpView) {
        attachView(mvpView);
    }

    //获取我买到的我要吃饭推荐的所有订单
    public void getBuyPublishAllOrderList(Map<String, Object> map) {
        addSubscription(apiStores.getBuyAllOrderListReturn(map),
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
                        System.out.println("获取我买到的我要吃饭的所有订单" + model);
                        BuyRecommendReturn buyRecommendReturn = JSONParstObject.getBuyRecommendList(model, 0);
                        if (buyRecommendReturn.getStatus() == 1) {
                            if (buyRecommendReturn != null && buyRecommendReturn.getRecommendBeenList() != null) {
                                mvpView.getAllRecommderOrderSuc(buyRecommendReturn.getRecommendBeenList());
                            } else {
                                mvpView.getAllRecommderOrderFail();
                            }
                        } else {
                            mvpView.getAllRecommderOrderFail();
                        }
                    }

                    @Override
                    public void onFailed(String msg) {
                        mvpView.getDataFail(msg);
                    }
                }));
    }


    /**
     * 获取我买到的我要吃饭的推荐待接单订单
     *
     * @param
     * @param map
     */
    public void getBuyPublishWaitReceiveOrderList(Map<String, Object> map) {
        addSubscription(apiStores.getBuyWaitReceiveOrderListReturn(map),
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
                        BuyRecommendReturn buyRecommendReturn = JSONParstObject.getBuyRecommendList(model, 1);
                        if (buyRecommendReturn.getStatus() == 1) {
                            if (buyRecommendReturn != null && buyRecommendReturn.getRecommendBeenList() != null) {
                                mvpView.getWaitRecommderOrderSuc(buyRecommendReturn.getRecommendBeenList());
                            } else {
                                mvpView.getWaitRecommderOrderFail();
                            }
                        } else {
                            mvpView.getWaitRecommderOrderFail();
                        }
                        System.out.println("获取我买到的我要吃饭的待接单订单" + model);

                    }

                    @Override
                    public void onFailed(String msg) {
                        mvpView.getDataFail(msg);
                    }
                }));
    }

    /**
     * 获取我买到的我要吃饭的推荐待送餐订单
     *
     * @param
     * @param map
     */
    public void getBuyPublishWaitSendOrderList(Map<String, Object> map) {
        addSubscription(apiStores.getBuyWaitSendOrderListReturn(map),
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
                        System.out.println("获取我买到的我要吃饭的待送餐订单" + model);
                        BuyRecommendReturn buyRecommendReturn = JSONParstObject.getBuyRecommendList(model, 2);
                        if (buyRecommendReturn.getStatus() == 1) {
                            if (buyRecommendReturn != null && buyRecommendReturn.getRecommendBeenList() != null) {
                                mvpView.getWaitSendRecommderOrderSuc(buyRecommendReturn.getRecommendBeenList());
                            } else {
                                mvpView.getWaitSendRecommderOrderFail();
                            }
                        } else {
                            mvpView.getWaitSendRecommderOrderFail();
                        }
                    }

                    @Override
                    public void onFailed(String msg) {
                        mvpView.getDataFail(msg);
                    }
                }));
    }


    /**
     * 获取我买到的我要吃饭的推荐已完成订单
     *
     * @param
     * @param map
     */
    public void getBuyPublishFinishOrderList(Map<String, Object> map) {
        addSubscription(apiStores.getBuyWaitFinishOrderListReturn(map),
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
                        BuyRecommendReturn buyRecommendReturn = JSONParstObject.getBuyRecommendList(model, 3);
                        if (buyRecommendReturn.getStatus() == 1) {
                            if (buyRecommendReturn != null && buyRecommendReturn.getRecommendBeenList() != null) {
                                mvpView.getFinishRecommderOrderSuc(buyRecommendReturn.getRecommendBeenList());
                            } else {
                                mvpView.getFinishRecommderOrderFail();
                            }
                        } else {
                            mvpView.getFinishRecommderOrderFail();
                        }
                        System.out.println("获取我买到的我要吃饭的已完成订单" + model);

                    }

                    @Override
                    public void onFailed(String msg) {
                        mvpView.getDataFail(msg);
                    }
                }));
    }


    /***********************************************************************************/


    /**
     * 获取我买到的我要吃饭的定做已所有订单
     *
     * @param
     * @param map
     */
    public void getBuyMakeAllOrderList(Map<String, Object> map) {
        addSubscription(apiStores.getBuyMakeAllOrderListReturn(map),
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
                     /*   JSONParstObject.getMakeOrderList();*/
                        System.out.println("获取我买到的我要吃饭定做的所有订单" + model);

                    }

                    @Override
                    public void onFailed(String msg) {
                        mvpView.getDataFail(msg);
                    }
                }));
    }

    /**
     * 获取我买到的我要吃饭的定做已等待接单
     *
     * @param
     * @param map
     */
    public void getBuyMakeWaitOrderList(Map<String, Object> map) {
        addSubscription(apiStores.getBuyMakeWaitOrderListReturn(map),
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

                        System.out.println("获取我买到的我要吃饭定做的等待接单订单" + model);

                    }

                    @Override
                    public void onFailed(String msg) {
                        mvpView.getDataFail(msg);
                    }
                }));
    }


    /**
     * 获取我买到的我要吃饭的定做已等待定单
     *
     * @param
     * @param map
     */
    public void getBuyMakeWaitSendOrderList(Map<String, Object> map) {
        addSubscription(apiStores.getBuyMakeWaitSendOrderListReturn(map),
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

                        System.out.println("获取我买到的我要吃饭定做的等待送餐订单" + model);

                    }

                    @Override
                    public void onFailed(String msg) {
                        mvpView.getDataFail(msg);
                    }
                }));
    }

    /**
     * 获取我买到的我要吃饭的定做已完成单
     *
     * @param
     * @param map
     */
    public void getBuyMakeFinishOrderList(Map<String, Object> map) {
        addSubscription(apiStores.getBuyMakeFinishOrderListReturn(map),
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

                        System.out.println("获取我买到的我要吃饭定做的等待送餐订单" + model);

                    }

                    @Override
                    public void onFailed(String msg) {
                        mvpView.getDataFail(msg);
                    }
                }));
    }
}
