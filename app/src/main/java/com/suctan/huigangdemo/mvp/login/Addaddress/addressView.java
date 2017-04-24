package com.suctan.huigangdemo.mvp.login.Addaddress;

import com.example.androidbase.mvp.BaseMvpView;
import com.suctan.huigangdemo.bean.user.CourseBean;

/**
 * Created by B-305 on 2017/4/20.
 */

public interface addressView extends BaseMvpView {
    void addAdressSuc();

    void addAdressFail();

    void getAddressListSuc();

    void getAddressListFail();

}
