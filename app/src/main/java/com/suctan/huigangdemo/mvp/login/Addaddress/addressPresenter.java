package com.suctan.huigangdemo.mvp.login.Addaddress;

import android.widget.Toast;

import com.example.androidbase.BaseApplication;
import com.example.androidbase.rxjava.ApiCallback;
import com.example.androidbase.rxjava.SubscriberCallBack;
import com.example.androidbase.utils.ToastTool;
import com.suctan.huigangdemo.bean.address.AddAddressResult;
import com.suctan.huigangdemo.bean.address.AddAdressReturn;
import com.suctan.huigangdemo.bean.address.AddressBean;
import com.suctan.huigangdemo.bean.user.ComomBeanReturn;
import com.suctan.huigangdemo.bean.user.ModifyReturn;
import com.suctan.huigangdemo.mvp.login.DemoBasePresenter;
import com.suctan.huigangdemo.util.JSONParstObject;

import java.util.Map;

/**
 * Created by B-305 on 2017/4/20.
 */

public class addressPresenter extends DemoBasePresenter<addressView> {
    public addressPresenter(addressView mvpView) {
        attachView(mvpView);
    }

    //把在activity里面定义好的参数,把封装在map里面的数据提取过来.
    /*添加用户地址*/
    public void addAddressActoin(Map map) {
        addSubscription(apiStores.addressReturn(map),
                new SubscriberCallBack<>(new ApiCallback<AddAddressResult>() {
                    @Override
                    public void onStart() {
                        System.out.println("onStart");
                    }

                    @Override
                    public void onSuccess(AddAddressResult model) {
                        if (model.getStatus() == 1) {
                            mvpView.addAdressSuc(model.getId());
                            ToastTool.showToast("地址修改成功！",1);
                        }else{
                            mvpView.changeAddressFail();
                            ToastTool.showToast("地址修改失败！",0);
                        }
                    }


                    @Override
                    public void onFailed(String msg) {
                        System.out.println("status=" + msg);
                    }

                    @Override
                    public void onCompleted() {
                        mvpView.hideLoading();
                    }
                })
        );

    }


    /*修改用户地址*/
    public void changeAddressActoin(Map map) {
        addSubscription(apiStores.changeAddressReturn(map),
                new SubscriberCallBack<>(new ApiCallback<ComomBeanReturn>() {
                    @Override
                    public void onStart() {
                        System.out.println("onStart");
                    }

                    @Override
                    public void onSuccess(ComomBeanReturn model) {
                        if(model.getStatus()==1){
                            mvpView.changeAddressSuc();
                            ToastTool.showToast("地址修改成功！",1);
                        }else{
                            mvpView.changeAddressFail();
                            ToastTool.showToast("地址修改失败！",1);
                        }
                    }


                    @Override
                    public void onFailed(String msg) {
                        System.out.println("status=" + msg);
                    }

                    @Override
                    public void onCompleted() {
                        mvpView.hideLoading();
                    }
                })
        );

    }


    /*获取用户地址列表*/
    public void getAddressList(Map map) {
        addSubscription(apiStores.getAddressListReturn(map),
                new SubscriberCallBack<>(new ApiCallback<String>() {
                    @Override
                    public void onStart() {

                    }

                    @Override
                    public void onSuccess(String model) {
                        System.out.println("获取用户地址列表" + model);
                        if (model != null) {
                            AddAdressReturn addAdressReturn = JSONParstObject.getAddressListObject(model);

                            if (addAdressReturn != null && addAdressReturn.getAddressList() != null) {

                                mvpView.getAddressListSuc(addAdressReturn.getAddressList());
                            }
                        }

                    }


                    @Override
                    public void onFailed(String msg) {
                        System.out.println("status=" + msg);
                    }

                    @Override
                    public void onCompleted() {
                        mvpView.hideLoading();
                    }
                })
        );

    }
}
