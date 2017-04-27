package com.suctan.huigangdemo.fragment.my.sell;

import android.view.View;
import android.widget.AdapterView;

import com.suctan.huigangdemo.R;
import com.suctan.huigangdemo.fragmentinterface.InterfaceMysellOrderState;
import com.suctan.huigangdemo.mvp.login.sellorder.MySellOrderPresenter;
import com.suctan.huigangdemo.mvp.login.sellorder.MySellOrderView;
import com.suctan.huigangdemo.widget.MvpFragment;

/**
 * Created by B-305 on 2017/4/19.
 */

public class MySell_Three extends MvpFragment<MySellOrderPresenter>implements MySellOrderView {
    private InterfaceMysellOrderState Listener;

    public static MySell_Three getFragInstant() {
        MySell_Three mySell_three = new MySell_Three();
        return mySell_three;
    }

    public void setListanter(InterfaceMysellOrderState Listener) {
        this.Listener = Listener;
    }


    //这个fragment对应这个，我卖出的中的两个选项栏中的已完成


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
        return R.layout.mysell_fragment_three;
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
