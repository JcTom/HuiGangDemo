package com.suctan.huigangdemo.mvp.login.buyorder;

import com.example.androidbase.rxjava.ApiCallback;
import com.example.androidbase.rxjava.SubscriberCallBack;
import com.example.androidbase.utils.ToastTool;
import com.suctan.huigangdemo.bean.commend.buy.BuyACommendReturn;
import com.suctan.huigangdemo.bean.commend.buy.BuyPCommendReturn;
import com.suctan.huigangdemo.bean.order.buy.BuyRecommendBean;
import com.suctan.huigangdemo.bean.order.buy.BuyRecommendReturn;
import com.suctan.huigangdemo.bean.user.ComomBeanReturn;
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
                        System.out.println("获取我买到的我要吃饭定做的所有订单" + model);
                        BuyRecommendReturn recommendReturn = JSONParstObject.getMakeOrderList(model, 0);
                        if (recommendReturn.getStatus() == 1) {
                            if (recommendReturn != null && recommendReturn.getRecommendBeenList() != null) {
                                mvpView.getAllMakeOrderSrc(recommendReturn.getRecommendBeenList());
                            } else {
                                mvpView.getMakeFinishOrderFail();
                            }
                        } else {
                            mvpView.getAllMakeOrderFail();
                        }

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
                        BuyRecommendReturn recommendReturn = JSONParstObject.getMakeOrderList(model, 1);
                        if (recommendReturn.getStatus() == 1) {
                            if (recommendReturn != null && recommendReturn.getRecommendBeenList() != null) {
                                mvpView.getWaitMakeOrderSuc(recommendReturn.getRecommendBeenList());
                            } else {
                                mvpView.getMakeFinishOrderFail();
                            }
                        } else {
                            mvpView.getAllMakeOrderFail();
                        }


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
                        BuyRecommendReturn recommendReturn = JSONParstObject.getMakeOrderList(model, 2);
                        if (recommendReturn.getStatus() == 1) {
                            if (recommendReturn != null && recommendReturn.getRecommendBeenList() != null) {
                                mvpView.getMakeWaitSendOrderSuc(recommendReturn.getRecommendBeenList());
                            } else {
                                mvpView.getMakeFinishOrderFail();
                            }
                        } else {
                            mvpView.getMakeWaitSendOrderFail();
                        }
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
                        BuyRecommendReturn recommendReturn = JSONParstObject.getMakeOrderList(model, 3);
                        if (recommendReturn.getStatus() == 1) {
                            if (recommendReturn != null && recommendReturn.getRecommendBeenList() != null) {
                                mvpView.getMakeFinishOrderSuc(recommendReturn.getRecommendBeenList());
                            } else {
                                mvpView.getMakeFinishOrderFail();
                            }
                        } else {
                            mvpView.getMakeFinishOrderFail();
                        }
                        System.out.println("获取我买到的我要吃饭定做的等待送餐订单" + model);

                    }

                    @Override
                    public void onFailed(String msg) {
                        mvpView.getDataFail(msg);
                    }
                }));
    }


    /**
     * 我要吃定做取消订单
     *
     * @param
     * @param map
     * @param
     */
    public void buyPubCancelOrder(Map<String, Object> map, final int orderID) {
        addSubscription(apiStores.BuyPubCancelOrderReturn(map),
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
                            mvpView.buyPuCancelSuc(orderID);
                            ToastTool.showToast("订单取消成功！", 1);
                        } else {
                            ToastTool.showToast("订单取消失败！", 0);
                        }
                    }

                    @Override
                    public void onFailed(String msg) {
                        mvpView.getDataFail(msg);
                    }
                }));
    }


    /**
     * 我要吃定做确认收货
     *
     * @param
     * @param map
     * @param
     */
    public void buyPubComfirmOrder(Map<String, Object> map, final int orderID) {
        addSubscription(apiStores.BuyPubOrderRComfirmReturn(map),
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
                            mvpView.buyPuComfirmSuc(orderID);
                            ToastTool.showToast("订单确认收货成功！", 1);
                        } else {
                            ToastTool.showToast("订单确认收货失败！", 0);
                        }
                    }

                    @Override
                    public void onFailed(String msg) {
                        mvpView.getDataFail(msg);
                    }
                }));
    }


    /**
     * 我要吃查看评论
     *
     * @param
     * @param map
     * @param
     */
    public void buyCheckPCommentOrder(Map<String, Object> map) {
        addSubscription(apiStores.BuyCheckOrderCommentReturn(map),
                new SubscriberCallBack<>(new ApiCallback<BuyPCommendReturn>() {
                    @Override
                    public void onStart() {

                    }

                    @Override
                    public void onCompleted() {
                        mvpView.hideLoading();
                    }

                    @Override
                    public void onSuccess(BuyPCommendReturn model) {
                        System.out.println("查看评论" + model);

                        if (model.getStatus() == 1) {
                            mvpView.getCommendPSuc(model);
                        } else {
                            mvpView.getCommendPFail();
                        }

                    }

                    @Override
                    public void onFailed(String msg) {
                        mvpView.getDataFail(msg);
                    }
                }));
    }

    /**
     * 我要吃定做查看评论
     *
     * @param
     * @param map
     * @param
     */
    public void buyAddPCommentOrder(Map<String, Object> map) {
        addSubscription(apiStores.BuyAddPOrderCommentReturn(map),
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
                            mvpView.addBuyCommendPSuc();
                            ToastTool.showToast("添加评论成功", 1);
                        } else {
                            ToastTool.showToast("添加评论失败", 0);
                        }
                    }

                    @Override
                    public void onFailed(String msg) {
                        mvpView.getDataFail(msg);
                    }
                }));
    }


    /**
     * 我要吃推荐确认订单
     *
     * @param
     * @param
     * @param map
     * @param
     */
    public void buyComfirmAOrder(Map<String, Object> map, final BuyRecommendBean buyRecommendBean) {
        addSubscription(apiStores.BuyComfirmAOrderReturn(map),
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
                            mvpView.buyAComfirmSuc(buyRecommendBean);
                            ToastTool.showToast("订单确认收货成功！", 1);
                        } else {
                            ToastTool.showToast("订单确认收货失败！", 0);
                        }
                    }

                    @Override
                    public void onFailed(String msg) {
                        mvpView.getDataFail(msg);
                    }
                }));
    }


    /**
     * 我要吃推荐查看评论
     *
     * @param
     * @param map
     * @param
     */
    public void buyCheckACommentOrder(Map<String, Object> map) {
        addSubscription(apiStores.BuyCheckAOrderCommentReturn(map),
                new SubscriberCallBack<>(new ApiCallback<BuyACommendReturn>() {
                    @Override
                    public void onStart() {

                    }

                    @Override
                    public void onCompleted() {
                        mvpView.hideLoading();
                    }

                    @Override
                    public void onSuccess(BuyACommendReturn model) {
                        System.out.println("查看评论" + model);
                        if (model.getStatus() == 1) {
                            mvpView.getCommendASuc(model);
                        } else {
                            mvpView.getCommendAFail();
                        }

                    }

                    @Override
                    public void onFailed(String msg) {
                        mvpView.getDataFail(msg);
                    }
                }));
    }

    /**
     * 我要吃推荐添加评论
     *
     * @param
     * @param map
     * @param
     */
    public void buyAddACommentOrder(Map<String, Object> map) {
        addSubscription(apiStores.BuyAddAOrderCommentReturn(map),
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
                            mvpView.addBuyACommendSuc();
                            ToastTool.showToast("添加评论成功", 1);
                        } else {
                            ToastTool.showToast("添加评论失败", 0);
                        }
                    }

                    @Override
                    public void onFailed(String msg) {
                        mvpView.getDataFail(msg);
                    }
                }));
    }



    /**
     * 我要做定做添加评论
     *
     * @param
     * @param map
     * @param
     */
    public void sellAddPCommentOrder(Map<String, Object> map) {
        addSubscription(apiStores.SellAddPOrderCommentReturn(map),
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
                            mvpView.addBuyCommendPSuc();
                            ToastTool.showToast("添加评论成功", 1);
                        } else {
                            ToastTool.showToast("添加评论失败", 0);
                        }
                    }

                    @Override
                    public void onFailed(String msg) {
                        mvpView.getDataFail(msg);
                    }
                }));
    }




}
