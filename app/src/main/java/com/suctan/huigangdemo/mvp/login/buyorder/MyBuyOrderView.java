package com.suctan.huigangdemo.mvp.login.buyorder;

import com.example.androidbase.mvp.BaseMvpView;
import com.suctan.huigangdemo.bean.order.buy.BuyRecommendBean;

import java.util.ArrayList;

/**
 * Created by Tom on 2017/4/11.
 */

public interface MyBuyOrderView extends BaseMvpView {
    void getAllRecommderOrderSuc(ArrayList<BuyRecommendBean> allRecommendOrder);

    void getAllRecommderOrderFail();

    void getWaitRecommderOrderSuc(ArrayList<BuyRecommendBean> waitRecommendOrder);

    void getWaitRecommderOrderFail();

    void getWaitSendRecommderOrderSuc(ArrayList<BuyRecommendBean> waitSendRecommendOrder);

    void getWaitSendRecommderOrderFail();

    void getFinishRecommderOrderSuc(ArrayList<BuyRecommendBean> finishRecommendOrder);

    void getFinishRecommderOrderFail();
}
