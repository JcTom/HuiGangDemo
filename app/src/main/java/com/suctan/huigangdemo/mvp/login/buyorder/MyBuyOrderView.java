package com.suctan.huigangdemo.mvp.login.buyorder;

import com.example.androidbase.mvp.BaseMvpView;
import com.suctan.huigangdemo.bean.commend.buy.BuyACommendReturn;
import com.suctan.huigangdemo.bean.commend.buy.BuyPCommendReturn;
import com.suctan.huigangdemo.bean.order.buy.BuyRecommendBean;

import java.util.ArrayList;

/**
 * Created by Tom on 2017/4/11.
 */

public interface MyBuyOrderView extends BaseMvpView {
    /*推荐*/
    void getAllRecommderOrderSuc(ArrayList<BuyRecommendBean> allRecommendOrder);

    void getAllRecommderOrderFail();

    void getWaitRecommderOrderSuc(ArrayList<BuyRecommendBean> waitRecommendOrder);

    void getWaitRecommderOrderFail();

    void getWaitSendRecommderOrderSuc(ArrayList<BuyRecommendBean> waitSendRecommendOrder);

    void getWaitSendRecommderOrderFail();

    void getFinishRecommderOrderSuc(ArrayList<BuyRecommendBean> finishRecommendOrder);

    void getFinishRecommderOrderFail();

    void buyPuCancelSuc(int orderID);

    void buyPuComfirmSuc(int orderId);


    void getCommendPSuc(BuyPCommendReturn buyPCommendReturn);

    void getCommendPFail();

    void addBuyCommendPSuc();

    void getCommendASuc(BuyACommendReturn buyACommendReturn);

    void getCommendAFail();

    /*定做*/

    void getAllMakeOrderSrc(ArrayList<BuyRecommendBean> makeAllList);

    void getAllMakeOrderFail();

    void getWaitMakeOrderSuc(ArrayList<BuyRecommendBean> makeWaitList);

    void getWaitMakeOrderFail();

    void getMakeWaitSendOrderSuc(ArrayList<BuyRecommendBean> makeWaitSendList);

    void getMakeWaitSendOrderFail();

    void getMakeFinishOrderSuc(ArrayList<BuyRecommendBean> makeFinishList);

    void getMakeFinishOrderFail();

    void buyAComfirmSuc(BuyRecommendBean buyRecommendBean);

    void addBuyACommendSuc();
}
