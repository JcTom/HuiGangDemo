package com.suctan.huigangdemo.fragment.my.sell;


import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.suctan.huigangdemo.R;
import com.suctan.huigangdemo.acache.TokenManager;
import com.suctan.huigangdemo.activity.order.SellOrderDetailActivity;
import com.suctan.huigangdemo.adapter.order.sellorder.SellADoingOrederAdapter;
import com.suctan.huigangdemo.bean.order.sell.SellOrderBean;
import com.suctan.huigangdemo.fragmentinterface.InterfaceMysellOrderState;
import com.suctan.huigangdemo.mvp.login.sellorder.MySellOrderPresenter;
import com.suctan.huigangdemo.mvp.login.sellorder.MySellOrderView;
import com.suctan.huigangdemo.widget.MvpFragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class SellDoingAFrag extends MvpFragment<MySellOrderPresenter> implements MySellOrderView, SellADoingOrederAdapter.SellADoingDetailOnClickListener {

    private InterfaceMysellOrderState Listener;
    private boolean isFirstCreate;
    private ListView listview;
    ArrayList<SellOrderBean> sellADoings = new ArrayList<>();

    public static SellDoingAFrag getFragInstant() {
        SellDoingAFrag mysellWaitAgreeFrag = new SellDoingAFrag();
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

    private void getSellDoingAOrderData() {
        Map<String, Object> map = new HashMap<>();
        map.put("user_token", TokenManager.getToken());
        mvpPresenter.getMySellDoingAOrderList(map);
    }

    @Override
    protected void dataLazyLoad() {
        getSellDoingAOrderData();
    }

    @Override
    protected void initViewsAddEvents() {
        listview = getListView();
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

    @Override
    public void sellAllPSuc(ArrayList<SellOrderBean> sellAll) {

    }

    @Override
    public void sellAllPFail() {

    }

    @Override
    public void sellDoingPSuc(ArrayList<SellOrderBean> sellDoing) {

    }

    @Override
    public void sellDoingPFail() {

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
        sellADoings.addAll(sellADoing);
        initAdapter(sellADoings);

        onfinishRefreshOrLoad(true);
    }

    SellADoingOrederAdapter doingAdapter;

    private void initAdapter(ArrayList<SellOrderBean> sellADoings) {
        if (!isFirstCreate) {
            isFirstCreate = true;
            doingAdapter = new SellADoingOrederAdapter(getActivity(), sellADoings);
            listview.setAdapter(doingAdapter);
            doingAdapter.onDetailOnclick(this);
        } else {
            doingAdapter.notifyDataSetChanged();
        }

    }

    @Override
    public void sellDoingAFail() {
        onfinishRefreshOrLoad(false);
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
    public void sellAcceptOrderSuc(SellOrderBean sellOrderBean) {
        sellOrderBean.setOrder_status(2);
        sellADoings.add(0, sellOrderBean);
        if (doingAdapter != null) {
            doingAdapter.setDataChage(sellADoings);
        }else{
            isFirstCreate = true;
            doingAdapter = new SellADoingOrederAdapter(getActivity(), sellADoings);
            listview.setAdapter(doingAdapter);
            doingAdapter.onDetailOnclick(this);
        }
    }

    @Override
    public void addSellCommendPSuc() {

    }

    @Override
    public void onItemOnClick(int position) {
        Intent intent = new Intent(getActivity(), SellOrderDetailActivity.class);
        intent.putExtra("sell", sellADoings.get(position));
        intent.putExtra("buySort", 0);
        getActivity().startActivity(intent);
    }
}
