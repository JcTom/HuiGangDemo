package com.suctan.huigangdemo.mvp.login.ModifityPsw;

import android.widget.Toast;

import com.example.androidbase.BaseApplication;
import com.example.androidbase.rxjava.ApiCallback;
import com.example.androidbase.rxjava.SubscriberCallBack;
import com.suctan.huigangdemo.bean.user.ModifyReturn;
import com.suctan.huigangdemo.mvp.login.DemoBasePresenter;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by B-305 on 2017/4/19.
 */

public class ModifityPswPresenter extends DemoBasePresenter<ModifityPswView>{
    public ModifityPswPresenter(ModifityPswView mvpView){
        attachView(mvpView);
    }
        //把在activity里面定义好的参数,把封装在map里面的数据提取过来.
        public void ModifityPsw(Map map){
           addSubscription(apiStores.ModifyPswReturn(new HashMap<String, Object>()),
                   new SubscriberCallBack<>(new ApiCallback<ModifyReturn>() {
                       @Override
                       public void onStart() {

                       }

                       @Override
                       public void onSuccess(ModifyReturn model) {
                           Toast.makeText(BaseApplication.getContext(),"修改用户密码成功" + model.getStatus()+model.getMsg(),Toast.LENGTH_SHORT).show();
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
