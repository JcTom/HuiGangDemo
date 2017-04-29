package com.suctan.huigangdemo.fragment.my.sell;


import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.suctan.huigangdemo.R;
import com.suctan.huigangdemo.acache.TokenManager;
import com.suctan.huigangdemo.activity.order.SellOrderDetailActivity;
import com.suctan.huigangdemo.adapter.order.sellorder.SellPDoingOrederAdapter;
import com.suctan.huigangdemo.bean.order.sell.SellOrderBean;
import com.suctan.huigangdemo.fragmentinterface.InterfaceMysellOrderState;
import com.suctan.huigangdemo.mvp.login.sellorder.MySellOrderPresenter;
import com.suctan.huigangdemo.mvp.login.sellorder.MySellOrderView;
import com.suctan.huigangdemo.widget.MvpFragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class SellDoingPFrag extends MvpFragment<MySellOrderPresenter> implements MySellOrderView, SellPDoingOrederAdapter.SellPDoingDetailOnClickListener {

    private InterfaceMysellOrderState Listener;
    private ListView sellDoingListView;
    private boolean isFirstCreate;
    private ArrayList<SellOrderBean> sellDoings = new ArrayList<>();

    public static SellDoingPFrag getFragInstant() {
        SellDoingPFrag mysellWaitAgreeFrag = new SellDoingPFrag();
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

    private void getSellDoingPOrderData() {
        Map<String, Object> map = new HashMap<>();
        map.put("user_token", TokenManager.getToken());
        mvpPresenter.getMySellDoingPOrderList(map);
    }

    @Override
    protected void dataLazyLoad() {
        getSellDoingPOrderData();
    }

    @Override
    protected void initViewsAddEvents() {
        sellDoingListView = getListView();
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.secondeds_fragment;
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

    @Override
    public void sellAllPSuc(ArrayList<SellOrderBean> sellAll) {

    }

    @Override
    public void sellAllPFail() {

    }

    @Override
    public void sellDoingPSuc(ArrayList<SellOrderBean> sellDoing) {
        sellDoings.addAll(sellDoing);
        initAdapter(sellDoings);
        onfinishRefreshOrLoad(true);
    }

    SellPDoingOrederAdapter doingAdapter;

    private void initAdapter(ArrayList<SellOrderBean> sellDoing) {
        if (!isFirstCreate) {
            doingAdapter = new SellPDoingOrederAdapter(getActivity(), sellDoing);
            sellDoingListView.setAdapter(doingAdapter);
            isFirstCreate = true;
            doingAdapter.onDetailOnclick(this);
        } else {
            doingAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void sellDoingPFail() {
        onfinishRefreshOrLoad(false);
    }

    @Override
    public void sellFinishPSuc(ArrayList<SellOrderBean> sellFinish) {

    }

    @Override
    public void sellFinishPFail() {

    }

    @Override
    public void sellAllASuc(ArrayList<SellOrderBean> sellAAll) {

    }

    @Override
    public void sellAllAFail() {

    }

    @Override
    public void sellAgreeASuc(ArrayList<SellOrderBean> sellAAgree) {

    }

    @Override
    public void sellAgreeAFail() {

    }

    @Override
    public void sellDoingASuc(ArrayList<SellOrderBean> sellADoing) {

    }

    @Override
    public void sellDoingAFail() {

    }

    @Override
    public void sellFinishASuc(ArrayList<SellOrderBean> sellAFinish) {

    }

    @Override
    public void sellFinishAFail() {

    }

    @Override
    public void sellCancleOrderSuc(int orderId) {

    }

    @Override
    public void sellAcceptOrderSuc(SellOrderBean orderId) {

    }

    @Override
    public void addSellCommendPSuc() {

    }

    @Override
    public void onItemOnClick(int position) {
        Intent intent = new Intent(getActivity(), SellOrderDetailActivity.class);
        intent.putExtra("sell", sellDoings.get(position));
        intent.putExtra("buySort", 1);
        getActivity().startActivity(intent);
    }
}
