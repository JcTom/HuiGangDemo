package com.suctan.huigangdemo.mvp.login.my_wallet_tx;

import com.example.androidbase.rxjava.ApiCallback;
import com.example.androidbase.rxjava.SubscriberCallBack;
import com.suctan.huigangdemo.bean.user.ModifyReturn;
import com.suctan.huigangdemo.mvp.login.DemoBasePresenter;

import java.util.Map;

/**
 * Created by B-305 on 2017/4/20.
 */

public class tx_walletPresenter extends DemoBasePresenter<tx_walletView>{

    public tx_walletPresenter(tx_walletView mvpView){
        attachView(mvpView);
    }

    public void withrawalsAction(Map map){
        addSubscription(apiStores.outmoneyReturn(map),
          new SubscriberCallBack<>(new ApiCallback<ModifyReturn>(){
              @Override
              public void onStart() {
                  System.out.println("onStart");
              }

              @Override
              public void onSuccess(ModifyReturn model) {
                  System.out.println("onSuccess");
                  System.out.println("status="+model.getStatus()+"msg="+model.getMsg());
              }


              @Override
              public void onFailed(String msg) {
                  System.out.println("status="+msg);
                  System.out.println("onFailed");
              }

              @Override
              public void onCompleted() {
                  mvpView.hideLoading();
              }
          })
        );
    }


}
