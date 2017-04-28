package com.suctan.huigangdemo.mvp.login.my_wallet;

import android.util.Log;

import com.example.androidbase.rxjava.ApiCallback;
import com.example.androidbase.rxjava.SubscriberCallBack;
import com.example.androidbase.utils.ToastTool;
import com.suctan.huigangdemo.bean.user.ModifyReturn;
import com.suctan.huigangdemo.mvp.login.DemoBasePresenter;

import java.util.Map;

/**
 * Created by B-305 on 2017/4/23.
 */

public class MymoneyPresenter extends DemoBasePresenter<MymoneyView>{
    String TAG = "MymoneyPresenter";
            public MymoneyPresenter(MymoneyView mvpView){
                attachView(mvpView);
            }

          public void MymoneyAction(Map map){
              addSubscription(apiStores.MymoneyReturn(map),
                      new SubscriberCallBack<>(new ApiCallback<ModifyReturn>(){
                          @Override
                          public void onStart() {

                          }

                          @Override
                          public void onSuccess(ModifyReturn model) {
                              Log.i(TAG, "onSuccess: ");
                              if (model.getStatus() == "1") {
                                  mvpView.getmoney(model.getMoney());
                              } else {
                                  ToastTool.showToast(model.getMsg(),0);
                              }
                              ToastTool.showToast("onSuccess: 返回状态是:"+model.getStatus()+"msg："+model.getMsg(),1);
                          }

                          @Override
                          public void onFailed(String msg) {
                              Log.i(TAG, "onFailed: ");
                              mvpView.getDataFail(msg);
                          }

                          @Override
                          public void onCompleted() {
                              mvpView.hideLoading();
                              Log.i(TAG, "onCompleted: ");
                          }
                      })

              );
          }
}
