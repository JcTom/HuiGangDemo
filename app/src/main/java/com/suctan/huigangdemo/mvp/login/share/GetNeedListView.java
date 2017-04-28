package com.suctan.huigangdemo.mvp.login.share;

import com.example.androidbase.mvp.BaseMvpView;
import com.suctan.huigangdemo.bean.need.NeedBean;

import java.util.ArrayList;

/**
 * Created by haily on 2017/4/19.
 */

public interface GetNeedListView extends BaseMvpView {
    void getNeedListSrc(ArrayList<NeedBean> needBeenList);
    void getNeedBack();


}
