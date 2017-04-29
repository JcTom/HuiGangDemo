package com.suctan.huigangdemo.mvp.login.sellorder;

import com.example.androidbase.mvp.BaseMvpView;
import com.suctan.huigangdemo.bean.order.sell.SellOrderBean;

import java.util.ArrayList;

/**
 * Created by Tom on 2017/4/11.
 */

public interface MySellOrderView extends BaseMvpView {
    void sellAllPSuc(ArrayList<SellOrderBean> sellAll);

    void sellAllPFail();

    void sellDoingPSuc(ArrayList<SellOrderBean> sellDoing);

    void sellDoingPFail();

    void sellFinishPSuc(ArrayList<SellOrderBean> sellFinish);

    void sellFinishPFail();


    void sellAllASuc(ArrayList<SellOrderBean> sellAAll);

    void sellAllAFail();

    void sellAgreeASuc(ArrayList<SellOrderBean> sellAAgree);

    void sellAgreeAFail();

    void sellDoingASuc(ArrayList<SellOrderBean> sellADoing);

    void sellDoingAFail();

    void sellFinishASuc(ArrayList<SellOrderBean> sellAFinish);

    void sellFinishAFail();


    void sellCancleOrderSuc(int orderId);

    void sellAcceptOrderSuc(SellOrderBean orderId);

    void addSellCommendPSuc();
}
