package com.suctan.huigangdemo.fragment.my.sell;


import android.view.View;
import android.widget.AdapterView;

import com.suctan.huigangdemo.R;
import com.suctan.huigangdemo.fragmentinterface.InterfaceMysellOrderState;
import com.suctan.huigangdemo.mvp.login.sellorder.MySellOrderPresenter;
import com.suctan.huigangdemo.mvp.login.sellorder.MySellOrderView;
import com.suctan.huigangdemo.widget.MvpFragment;


public class MysellWaitAgreeFrag extends MvpFragment<MySellOrderPresenter> implements MySellOrderView {

    private InterfaceMysellOrderState Listener;

    public static MysellWaitAgreeFrag getFragInstant() {
        MysellWaitAgreeFrag mysellWaitAgreeFrag = new MysellWaitAgreeFrag();
        return mysellWaitAgreeFrag;
    }

    public void setListanter(InterfaceMysellOrderState Listener) {
        this.Listener = Listener;
    }

    @Override
    protected void onDataRefresh() {

    }

    @Override
    protected void onDataLoadMore() {

    }

    @Override
    protected void dataLazyLoad() {

    }

    @Override
    protected void initViewsAddEvents() {

    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.seconded_fragment;
    }

    @Override
    protected void onMItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void getDataFail(String msg) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    protected MySellOrderPresenter createPresenter() {
        return new MySellOrderPresenter(this);
    }
}
