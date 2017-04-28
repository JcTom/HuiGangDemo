package com.suctan.huigangdemo.mvp.login.myChiken;

import com.example.androidbase.rxjava.ApiCallback;
import com.example.androidbase.rxjava.SubscriberCallBack;
import com.example.androidbase.utils.ToastTool;
import com.suctan.huigangdemo.bean.index.EatFoodReturn;
import com.suctan.huigangdemo.bean.user.ComomBeanReturn;
import com.suctan.huigangdemo.bean.user.MykitchenBean;
import com.suctan.huigangdemo.bean.user.MykitchenReturn;
import com.suctan.huigangdemo.mvp.login.DemoBasePresenter;
import com.suctan.huigangdemo.util.JSONParstObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Tom on 2017/4/11.
 */

public class MyChikenPresenter extends DemoBasePresenter<MyChikenView> {
    public MyChikenPresenter(MyChikenView mvpView) {
        attachView(mvpView);
    }

    public void AddChiken(final Map map) {
        addSubscription(apiStores.getMakeOrderListReturn(map),
                new SubscriberCallBack<>(new ApiCallback<String>() {
                    @Override
                    public void onStart() {
                    }

                    @Override
                    public void onSuccess(String model) {
                        if (model!=null){
                            MykitchenReturn mykitchenReturn = JSONParstObject.getMykitchenBeanList(model);
                            System.out.println("我的厨房获取菜的列表" + model);
                            if(mykitchenReturn!=null) {
                                mvpView.getMakeOrderList(mykitchenReturn.getMykitchenBeanList());
                            }
                        }
                    }
                   /* @Override
                    public void onSuccess(String model) {
                        if (model!=null){
                            MykitchenReturn mykitchenReturn = JSONParstObject.getMykitchenBeanList(model);
                            System.out.println("我的厨房获取菜的列表" + model);
                            if(mykitchenReturn!=null){
                                mvpView.getMakeOrderList(mykitchenReturn);
                            }
                        }
                    }*/


                    @Override
                    public void onCompleted() {
                        mvpView.hideLoading();
                    }


                    @Override
                    public void onFailed(String msg) {
                        mvpView.getDataFail(msg);
                    }
                }));
    }
    /*public void AddChiken(Map map) {
        addSubscription(apiStores.ModifyUserReturn(new HashMap<String, Object>()),
                new SubscriberCallBack<>(new ApiCallback<ModifyReturn>() {
                    @Override
                    public void onStart() {

                    }

                    @Override
                    public void onCompleted() {
                        mvpView.hideLoading();
                    }

                    @Override
                    public void onSuccess(ModifyReturn model) {

                    }
                    @Override
                    public void onFailed(String msg) {
                        mvpView.getDataFail(msg);
                    }
                }));
    }*/
}
