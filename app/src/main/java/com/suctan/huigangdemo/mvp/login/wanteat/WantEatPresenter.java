package com.suctan.huigangdemo.mvp.login.wanteat;

import com.example.androidbase.rxjava.ApiCallback;
import com.example.androidbase.rxjava.SubscriberCallBack;
import com.example.androidbase.utils.ToastTool;
import com.suctan.huigangdemo.bean.user.ComomBeanReturn;
import com.suctan.huigangdemo.bean.user.ModifyReturn;
import com.suctan.huigangdemo.bean.wanteat.WantEatFoodItem;
import com.suctan.huigangdemo.mvp.login.DemoBasePresenter;
import com.suctan.huigangdemo.mvp.login.sellorder.MySellOrderView;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Tom on 2017/4/11.
 */

public class WantEatPresenter extends DemoBasePresenter<WantEatView> {
    public WantEatPresenter(WantEatView mvpView) {
        attachView(mvpView);
    }

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
}
