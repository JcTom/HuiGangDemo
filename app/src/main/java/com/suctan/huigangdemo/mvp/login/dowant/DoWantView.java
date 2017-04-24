package com.suctan.huigangdemo.mvp.login.dowant;

import com.example.androidbase.mvp.BaseMvpView;
import com.suctan.huigangdemo.bean.dowant.DoWantOrderBean;

import java.util.ArrayList;

/**
 * Created by Tom on 2017/4/11.
 */

public interface DoWantView extends BaseMvpView {

    void getDoWantOrderSuc(ArrayList<DoWantOrderBean> doWantOrderBeen);

    void getDoWantOrderFail();
}
