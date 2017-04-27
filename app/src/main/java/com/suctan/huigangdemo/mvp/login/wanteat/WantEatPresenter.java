package com.suctan.huigangdemo.mvp.login.wanteat;

import com.example.androidbase.rxjava.ApiCallback;
import com.example.androidbase.rxjava.SubscriberCallBack;
import com.example.androidbase.utils.ToastTool;
import com.suctan.huigangdemo.bean.user.ComomBeanReturn;
import com.suctan.huigangdemo.bean.user.ModifyReturn;
import com.suctan.huigangdemo.bean.wanteat.WantEatFoodItem;
import com.suctan.huigangdemo.bean.wanteat.WantEatOtderReturn;
import com.suctan.huigangdemo.mvp.login.DemoBasePresenter;
import com.suctan.huigangdemo.mvp.login.sellorder.MySellOrderView;
import com.suctan.huigangdemo.util.JSONParstObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Tom on 2017/4/11.
 */

public class WantEatPresenter extends DemoBasePresenter<WantEatView> {
    public WantEatPresenter(WantEatView mvpView) {
        attachView(mvpView);
    }

    /*发布我要吃饭*/
    public void PostWantEat(Map map) {
        addSubscription(apiStores.PostWantEatReturn(map),
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
                            mvpView.postWantEatSuc();
                        } else {
                            mvpView.postWantEatFail();
                            ToastTool.showToast(model.getMsg(), 0);
                        }
                    }

                    @Override
                    public void onFailed(String msg) {
                        mvpView.getDataFail(msg);
                    }
                }));
    }

    //获得食物推荐同厨数据列表的数据
    public void getEatOrtherList(Map map) {
        addSubscription(apiStores.getEatOrtherReturn(map),
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
                        if (model != null) {
                            System.out.println("获得其它的菜" + model);
                            WantEatOtderReturn wantEatOtderReturn = JSONParstObject.getOtherWantEatListObject(model);
                            if (wantEatOtderReturn != null) {
                                if (wantEatOtderReturn.getEatFoodBeenList() != null) {
                                    mvpView.getOtherWantEatListSuc(wantEatOtderReturn.getEatFoodBeenList());
                                }
                                if (wantEatOtderReturn.getWantEatOrderCommentList() != null) {
                                    mvpView.getOtherWantCommentListSuc(wantEatOtderReturn.getWantEatOrderCommentList());
                                }
                            }

                        }
                        System.out.println("获取所有同厨数据" + model);
                    }

                    @Override
                    public void onFailed(String msg) {
                        mvpView.getDataFail(msg);
                    }
                }));
    }

    /**
     * 今日推荐加减号加入购物车
     *
     * @param map
     */
    public void addCart(Map map) {
        addSubscription(apiStores.addCartReturn(map),
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
                             mvpView.addCartSuc();
                        }else{
                            ToastTool.showToast("系统繁忙请稍后再试！", 0);
                        }
                    }

                    @Override
                    public void onFailed(String msg) {
                        mvpView.getDataFail(msg);
                    }
                }));
    }

}
