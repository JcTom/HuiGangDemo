package com.suctan.huigangdemo.mvp.login.sellorder;

import com.example.androidbase.rxjava.ApiCallback;
import com.example.androidbase.rxjava.SubscriberCallBack;
import com.example.androidbase.utils.ToastTool;
import com.suctan.huigangdemo.bean.order.sell.SellOrderBean;
import com.suctan.huigangdemo.bean.order.sell.SellOrderReturn;
import com.suctan.huigangdemo.bean.user.ComomBeanReturn;
import com.suctan.huigangdemo.bean.user.ModifyReturn;
import com.suctan.huigangdemo.mvp.login.DemoBasePresenter;
import com.suctan.huigangdemo.mvp.login.buyorder.MyBuyOrderView;
import com.suctan.huigangdemo.util.JSONParstObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Tom on 2017/4/11.
 */

public class MySellOrderPresenter extends DemoBasePresenter<MySellOrderView> {
    public MySellOrderPresenter(MySellOrderView mvpView) {
        attachView(mvpView);
    }

    /**
     * 获取我要做饭接单的所有订单
     *
     * @param map
     */
    public void getMySellAllPOrderList(Map map) {
        addSubscription(apiStores.getSellAllPOrderListReturn(map),
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
                        System.out.println("获取我要做饭接单所有" + model);
                        SellOrderReturn sellOrderReturn = JSONParstObject.getSellPOrderList(model, 0);
                        if (sellOrderReturn.getStatus() == 1) {
                            if (sellOrderReturn != null && sellOrderReturn.getSellOrderBeenList() != null) {
                                mvpView.sellAllPSuc(sellOrderReturn.getSellOrderBeenList());
                            } else {
                                mvpView.sellAllPFail();
                            }
                        } else {
                            mvpView.sellAllPFail();

                        }


                    }

                    @Override
                    public void onFailed(String msg) {
                        mvpView.getDataFail(msg);
                    }
                }));
    }


    /**
     * 获取我要做饭接单的进行中订单
     *
     * @param map
     */
    public void getMySellDoingPOrderList(Map map) {
        addSubscription(apiStores.getSellDoingPOrderListReturn(map),
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
                        SellOrderReturn sellOrderReturn = JSONParstObject.getSellPOrderList(model, 2);
                        if (sellOrderReturn.getStatus() == 1) {
                            if (sellOrderReturn != null && sellOrderReturn.getSellOrderBeenList() != null) {
                                mvpView.sellDoingPSuc(sellOrderReturn.getSellOrderBeenList());
                            } else {
                                mvpView.sellDoingPFail();
                            }
                        } else {
                            mvpView.sellDoingPFail();

                        }
                        System.out.println("获取我要做饭接单进行中" + model);

                    }

                    @Override
                    public void onFailed(String msg) {
                        mvpView.getDataFail(msg);
                    }
                }));
    }

    /**
     * 获取我要做饭接单的已完成订单
     *
     * @param map
     */
    public void getMySellFinishPOrderList(Map map) {
        addSubscription(apiStores.getSellFinishPOrderListReturn(map),
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
                        SellOrderReturn sellOrderReturn = JSONParstObject.getSellPOrderList(model, 3);
                        if (sellOrderReturn.getStatus() == 1) {
                            if (sellOrderReturn != null && sellOrderReturn.getSellOrderBeenList() != null) {
                                mvpView.sellFinishPSuc(sellOrderReturn.getSellOrderBeenList());
                            } else {
                                mvpView.sellFinishPFail();
                            }
                        } else {
                            mvpView.sellFinishPFail();

                        }
                        System.out.println("获取我要做饭接单已完" + model);
                    }

                    @Override
                    public void onFailed(String msg) {
                        mvpView.getDataFail(msg);
                    }
                }));
    }


    /**
     * 获取我要做饭卖出的所有订单
     *
     * @param map
     */
    public void getMySellAllAOrderList(Map map) {
        addSubscription(apiStores.getSellAllAOrderListReturn(map),
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
                        System.out.println("获取我要做饭卖出的所有订单" + model);
                        SellOrderReturn sellOrderReturn = JSONParstObject.getSellAOrderList(model, 0);
                        if (sellOrderReturn.getStatus() == 1) {
                            if (sellOrderReturn != null && sellOrderReturn.getSellOrderBeenList() != null) {
                                mvpView.sellAllASuc(sellOrderReturn.getSellOrderBeenList());
                            } else {
                                mvpView.sellAllAFail();
                            }
                        } else {
                            mvpView.sellAllAFail();

                        }

                    }

                    @Override
                    public void onFailed(String msg) {
                        mvpView.getDataFail(msg);
                    }
                }));
    }

    /**
     * 获取我要做饭卖出的待同意订单
     *
     * @param map
     */
    public void getMySellAgreeAOrderList(Map map) {
        addSubscription(apiStores.getSellAgreeAOrderListReturn(map),
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
                        System.out.println("获取我要做饭卖出的待同意订单" + model);
                        SellOrderReturn sellOrderReturn = JSONParstObject.getSellAOrderList(model, 1);
                        if (sellOrderReturn.getStatus() == 1) {
                            if (sellOrderReturn != null && sellOrderReturn.getSellOrderBeenList() != null) {
                                mvpView.sellAgreeASuc(sellOrderReturn.getSellOrderBeenList());
                            } else {
                                mvpView.sellAgreeAFail();
                            }
                        } else {
                            mvpView.sellAgreeAFail();

                        }

                    }

                    @Override
                    public void onFailed(String msg) {
                        mvpView.getDataFail(msg);
                    }
                }));
    }


    /**
     * 获取我要做饭卖出的进行中订单
     *
     * @param map
     */
    public void getMySellDoingAOrderList(Map map) {
        addSubscription(apiStores.getSellDoingAOrderListReturn(map),
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
                        System.out.println("获取我要做饭卖出的进行中订单" + model);
                        SellOrderReturn sellOrderReturn = JSONParstObject.getSellAOrderList(model, 2);
                        if (sellOrderReturn.getStatus() == 1) {
                            if (sellOrderReturn != null && sellOrderReturn.getSellOrderBeenList() != null) {
                                mvpView.sellDoingASuc(sellOrderReturn.getSellOrderBeenList());
                            } else {
                                mvpView.sellDoingAFail();
                            }
                        } else {
                            mvpView.sellDoingAFail();

                        }

                    }

                    @Override
                    public void onFailed(String msg) {
                        mvpView.getDataFail(msg);
                    }
                }));
    }


    /**
     * 获取我要做饭卖出的已完成订单
     *
     * @param map
     */
    public void getMySellFinishAOrderList(Map map) {
        addSubscription(apiStores.getSellFinishAOrderListReturn(map),
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
                        System.out.println("获取我要做饭卖出的已完成订单" + model);
                        SellOrderReturn sellOrderReturn = JSONParstObject.getSellAOrderList(model, 3);
                        if (sellOrderReturn.getStatus() == 1) {
                            if (sellOrderReturn != null && sellOrderReturn.getSellOrderBeenList() != null) {
                                mvpView.sellFinishASuc(sellOrderReturn.getSellOrderBeenList());
                            } else {
                                mvpView.sellFinishAFail();
                            }
                        } else {
                            mvpView.sellFinishAFail();
                        }
                    }

                    @Override
                    public void onFailed(String msg) {
                        mvpView.getDataFail(msg);
                    }
                }));
    }


    /**
     * 我要做取消订单
     *
     * @param map
     */
    public void sellCancelOrderList(Map map, final int orderId) {
        addSubscription(apiStores.sellCancelOrderReturn(map),
                new SubscriberCallBack<>(new ApiCallback<ComomBeanReturn>() {
                    @Override
                    public void onStart() {

                    }

                    @Override
                    public void onCompleted() {
                        mvpView.hideLoading();
                    }

                    @Override
                    public void onSuccess(ComomBeanReturn model) {
                        if (model.getStatus() == 1) {
                            mvpView.sellCancleOrderSuc(orderId);
                            ToastTool.showToast("订单取消成功", 1);
                        } else {
                            ToastTool.showToast("订单取消失败", 0);
                        }
                    }

                    @Override
                    public void onFailed(String msg) {
                        mvpView.getDataFail(msg);
                    }
                }));
    }


    /**
     * 我要做接受订单
     *
     * @param map
     * @param orderId
     */
    public void sellAcceptOrderList(Map map, final SellOrderBean sellOrderBean) {
        addSubscription(apiStores.sellAcceptOrderReturn(map),
                new SubscriberCallBack<>(new ApiCallback<ComomBeanReturn>() {
                    @Override
                    public void onStart() {

                    }

                    @Override
                    public void onCompleted() {
                        mvpView.hideLoading();
                    }

                    @Override
                    public void onSuccess(ComomBeanReturn model) {
                        if (model.getStatus() == 1) {
                            ToastTool.showToast("订单接受成功", 1);
                            mvpView.sellAcceptOrderSuc(sellOrderBean);
                        } else {
                            ToastTool.showToast("订单接受失败", 0);
                        }
                    }

                    @Override
                    public void onFailed(String msg) {
                        mvpView.getDataFail(msg);
                    }
                }));
    }


}
