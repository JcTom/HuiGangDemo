package com.suctan.huigangdemo.fragment.my.sell;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.suctan.huigangdemo.R;
import com.suctan.huigangdemo.acache.TokenManager;
import com.suctan.huigangdemo.activity.order.SellOrderAublicComment;
import com.suctan.huigangdemo.activity.order.SellOrderDetailActivity;
import com.suctan.huigangdemo.adapter.order.sellorder.SellAAllOrederAdapter;
import com.suctan.huigangdemo.bean.order.sell.SellOrderBean;
import com.suctan.huigangdemo.fragmentinterface.InterfaceMysellOrderState;
import com.suctan.huigangdemo.mvp.login.sellorder.MySellOrderPresenter;
import com.suctan.huigangdemo.mvp.login.sellorder.MySellOrderView;
import com.suctan.huigangdemo.widget.MvpFragment;
import com.suctan.huigangdemo.widget.TipsAcceptOrderDialog;
import com.suctan.huigangdemo.widget.TipsCancelOrderDialog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class SellAllAFrag extends MvpFragment<MySellOrderPresenter> implements MySellOrderView, SellAAllOrederAdapter.SellAAllDetailOnClickListener {

    private InterfaceMysellOrderState Listener;
    private ListView sellALlAListView;
    private boolean isFirstCreate;
    private ArrayList<SellOrderBean> sellAAlls = new ArrayList<>();

    public static SellAllAFrag getFragInstant() {
        SellAllAFrag mysellAllFrag = new SellAllAFrag();
        return mysellAllFrag;
    }

    public void setListanter(InterfaceMysellOrderState Listener) {
        this.Listener = Listener;
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
    protected void onDataRefresh() {

    }

    @Override
    protected void onDataLoadMore() {

    }

    private void getSellAllAOrderData() {
        Map<String, Object> map = new HashMap<>();
        map.put("user_token", TokenManager.getToken());
        mvpPresenter.getMySellAllAOrderList(map);
    }

    @Override
    protected void dataLazyLoad() {
        getSellAllAOrderData();
    }


    @Override
    protected void initViewsAddEvents() {
        sellALlAListView = getListView();
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.first_fragment;
    }

    @Override
    protected void onMItemClick(AdapterView<?> parent, View view, int position, long id) {

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
        sellAAlls.addAll(sellAAll);
        initAdapter(sellAAlls);
        onfinishRefreshOrLoad(true);
    }

    SellAAllOrederAdapter allAdaper;

    private void initAdapter(ArrayList<SellOrderBean> sellAAlls) {
        if (!isFirstCreate) {
            isFirstCreate = true;
            allAdaper = new SellAAllOrederAdapter(getActivity(), sellAAlls);
            sellALlAListView.setAdapter(allAdaper);
            allAdaper.onDetailOnclick(this);
        } else {
            allAdaper.notifyDataSetChanged();
        }
    }

    @Override
    public void sellAllAFail() {
        onfinishRefreshOrLoad(false);
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
        for (int i = 0; i < sellAAlls.size(); i++) {
            if (orderId == sellAAlls.get(i).getId()) {
                sellAAlls.get(i).setOrder_status(0);
                break;
            }
        }
        if (allAdaper != null) {
            allAdaper.setDataChange(sellAAlls);
        }
    }

    @Override
    public void sellAcceptOrderSuc(SellOrderBean sellOrderBean) {
        for (int i = 0; i < sellAAlls.size(); i++) {
            if (sellOrderBean.getId() == sellAAlls.get(i).getId()) {
                sellAAlls.get(i).setOrder_status(2);
                break;
            }
        }
        if (allAdaper != null) {
            allAdaper.setDataChange(sellAAlls);
        }
    }

    @Override
    public void addSellCommendPSuc() {

    }

    @Override
    public void onItemOnClick(int position) {
        Intent intent = new Intent(getActivity(), SellOrderDetailActivity.class);
        intent.putExtra("sell", sellAAlls.get(position));
        intent.putExtra("buySort", 0);
        getActivity().startActivity(intent);
    }

    @Override
    public void onCancelOnCkick(int position) {
        showCancelTips(position);
    }

    @Override
    public void onAcceptOnClick(int position) {
        showAcceptTips(position);
    }

    @Override
    public void onSellAllComment(int position) {
        Intent intent = new Intent(getActivity(), SellOrderAublicComment.class);
        intent.putExtra("ACommend", sellAAlls.get(position));
        startActivity(intent);
    }

    //取消订单
    private void requestCancelOrder(int position) {
        Map<String, Object> map = new HashMap<>();
        map.put("user_token", TokenManager.getToken());
        map.put("id", sellAAlls.get(position).getId());
        mvpPresenter.sellCancelOrderList(map, sellAAlls.get(position).getId());
    }

    //接受订单
    private void requestAcceptOrder(int position) {
        Map<String, Object> map = new HashMap<>();
        map.put("user_token", TokenManager.getToken());
        map.put("id", sellAAlls.get(position).getId());
        mvpPresenter.sellAcceptOrderList(map, sellAAlls.get(position));
    }


    private void showCancelTips(final int position) {
        final TipsCancelOrderDialog cancelTip = new TipsCancelOrderDialog(getActivity());
        cancelTip.setTipClickLisener(new TipsCancelOrderDialog.OnTipLisetner() {
            @Override
            public void comfirm() {
                requestCancelOrder(position);
                cancelTip.dismiss();
            }

            @Override
            public void cancel() {
                cancelTip.dismiss();
            }
        });
        cancelTip.show();
    }


    private void showAcceptTips(final int position) {
        final TipsAcceptOrderDialog acceptTip = new TipsAcceptOrderDialog(getActivity());
        acceptTip.setTipClickLisener(new TipsAcceptOrderDialog.OnTipLisetner() {
            @Override
            public void comfirm() {
                requestAcceptOrder(position);
                acceptTip.dismiss();
            }

            @Override
            public void cancel() {
                acceptTip.dismiss();
            }
        });
        acceptTip.show();
    }
}
