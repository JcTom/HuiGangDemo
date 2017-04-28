package com.suctan.huigangdemo.mvp.login.index.home;


import com.example.androidbase.rxjava.ApiCallback;
import com.example.androidbase.rxjava.SubscriberCallBack;
import com.suctan.huigangdemo.bean.index.EatFoodReturn;
import com.suctan.huigangdemo.mvp.login.DemoBasePresenter;
import com.suctan.huigangdemo.util.JSONParstObject;

import java.util.HashMap;

/**
 * Created by Administrator on 2017/3/4.
 */

public class HomePresenter extends DemoBasePresenter<HomeView> {
    public HomePresenter(HomeView mvpView) {
        attachView(mvpView);
    }

    /**
     * 获取轮播图列表
     */
    public void getRollPageList() {
        addSubscription(apiStores.getRollPageListReturn(new HashMap<String, Object>()),
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
                            System.out.println("获取轮播图的信息" + model);
                            EatFoodReturn eatFoodReturn = JSONParstObject.getRollViewDataList(model, 0);
                            if (eatFoodReturn != null) {
                                mvpView.getRollViewListSuc(eatFoodReturn);
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
     * 获取首页所有菜的列表
     */
    public void getEatFoodList() {
        addSubscription(apiStores.getEatFoodList(new HashMap<String, Object>()),
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
                            EatFoodReturn eatFoodReturn = JSONParstObject.getRollViewDataList(model, 1);
                            if (eatFoodReturn != null) {
                                System.out.println("首页获取菜的列表" + model);
                            }
                        }
                    }

                    @Override
                    public void onFailed(String msg) {
                        mvpView.getDataFail(msg);
                    }
                }));
    }


}