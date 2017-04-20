package com.suctan.huigangdemo.mvp.login.my_wallet_cz;

import android.widget.Toast;

import com.example.androidbase.BaseApplication;
import com.example.androidbase.rxjava.ApiCallback;
import com.example.androidbase.rxjava.SubscriberCallBack;
import com.suctan.huigangdemo.bean.user.ModifyReturn;
import com.suctan.huigangdemo.mvp.login.DemoBasePresenter;

import java.util.Map;

/**
 * Created by B-305 on 2017/4/20.
 */

public class cz_walletPresenter extends DemoBasePresenter<cz_walletView>{
    public cz_walletPresenter(cz_walletView mvpView){
        attachView(mvpView);
    }


    public void Addmoney(Map map){
          addSubscription(apiStores.addmoneyReturn(map),
                  new SubscriberCallBack<>(new ApiCallback<ModifyReturn>() {
                      @Override
                      public void onStart() {

                      }

                      @Override
                      public void onSuccess(ModifyReturn model) {
                          Toast.makeText(BaseApplication.getContext(),"充值成功" + model.getStatus()+model.getMsg(),Toast.LENGTH_SHORT).show();
                      }

                      @Override
                      public void onFailed(String msg) {
                          mvpView.getDataFail(msg);
                      }

                      @Override
                      public void onCompleted() {
                          mvpView.hideLoading();
                      }
                  })

          );
    }
}
