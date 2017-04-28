package com.suctan.huigangdemo.fragment.my.sell;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.suctan.huigangdemo.R;
import com.suctan.huigangdemo.acache.TokenManager;
import com.suctan.huigangdemo.activity.order.SellOrderDetailActivity;
import com.suctan.huigangdemo.adapter.order.sellorder.SellAAgreeOrederAdapter;
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

/**
 * Created by B-305 on 2017/4/19.
 */

public class SellAgreeAFrag extends MvpFragment<MySellOrderPresenter> implements MySellOrderView, SellAAgreeOrederAdapter.SellAAgreeDetailOnClickListener {
    private InterfaceMysellOrderState Listener;
    private boolean isFirstCreate;
    private ListView listView;
    private ArrayList<SellOrderBean> sellAAgrees = new ArrayList<>();

    public static SellAgreeAFrag getFragInstant() {
        SellAgreeAFrag mySell_four = new SellAgreeAFrag();
        return mySell_four;
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


    private void getSellAgressAOrderData() {
        Map<String, Object> map = new HashMap<>();
        map.put("user_token", TokenManager.getToken());
        mvpPresenter.getMySellAgreeAOrderList(map);
    }


    @Override
    protected void dataLazyLoad() {
        getSellAgressAOrderData();
    }

    @Override
    protected void initViewsAddEvents() {
        listView = getListView();
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
        sellAAgrees.addAll(sellAAgree);
        initAdapter(sellAAgrees);

        onfinishRefreshOrLoad(true);
    }

    SellAAgreeOrederAdapter agressAdapter;

    private void initAdapter(ArrayList<SellOrderBean> sellAAgrees) {
        if (!isFirstCreate) {
            agressAdapter = new SellAAgreeOrederAdapter(getActivity(), sellAAgrees);
            listView.setAdapter(agressAdapter);
            isFirstCreate = true;
            agressAdapter.onDetailOnclick(this);
        } else {
            agressAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void sellAgreeAFail() {
        onfinishRefreshOrLoad(false);
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
        for (int i = 0; i < sellAAgrees.size(); i++) {
            if (orderId == sellAAgrees.get(i).getId()) {
                sellAAgrees.remove(sellAAgrees.get(i));
                break;
            }
        }
        if (agressAdapter != null) {
            agressAdapter.setDataChange(sellAAgrees);
        }
    }

    @Override
    public void sellAcceptOrderSuc(SellOrderBean sellOrderBean) {
        for (int i = 0; i < sellAAgrees.size(); i++) {
            if (sellOrderBean.getId() == sellAAgrees.get(i).getId()) {
                sellAAgrees.remove(sellAAgrees.get(i));
                break;
            }
        }
        if (agressAdapter != null) {
            agressAdapter.setDataChange(sellAAgrees);
        }
    }

    @Override
    public void addSellCommendPSuc() {

    }

    @Override
    public void onItemOnClick(int position) {
        Intent intent = new Intent(getActivity(), SellOrderDetailActivity.class);
        intent.putExtra("sell", sellAAgrees.get(position));
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

    //取消订单
    private void requestCancelOrder(int position) {
        Map<String, Object> map = new HashMap<>();
        map.put("user_token", TokenManager.getToken());
        map.put("id", sellAAgrees.get(position).getId());
        mvpPresenter.sellCancelOrderList(map, sellAAgrees.get(position).getId());
    }

    //接受订单
    private void requestAcceptOrder(int position) {
        Map<String, Object> map = new HashMap<>();
        map.put("user_token", TokenManager.getToken());
        map.put("id", sellAAgrees.get(position).getId());
        mvpPresenter.sellAcceptOrderList(map, sellAAgrees.get(position));
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
