package com.suctan.huigangdemo.mvp.login.myChiken;

import com.example.androidbase.mvp.BaseMvpView;
import com.suctan.huigangdemo.bean.user.MykitchenBean;
import com.suctan.huigangdemo.bean.user.MykitchenReturn;

import java.util.ArrayList;

/**
 * Created by Tom on 2017/4/11.
 */


public interface MyChikenView extends BaseMvpView {
    void getMakeOrderList(ArrayList<MykitchenBean> mykitchenBeenlist);
}
