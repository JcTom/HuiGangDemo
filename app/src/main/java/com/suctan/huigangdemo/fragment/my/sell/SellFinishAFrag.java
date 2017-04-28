package com.suctan.huigangdemo.fragment.my.sell;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.suctan.huigangdemo.R;
import com.suctan.huigangdemo.acache.TokenManager;
import com.suctan.huigangdemo.activity.order.SellOrderAublicComment;
import com.suctan.huigangdemo.activity.order.SellOrderDetailActivity;
import com.suctan.huigangdemo.adapter.order.sellorder.SellAFinishOrederAdapter;
import com.suctan.huigangdemo.bean.order.sell.SellOrderBean;
import com.suctan.huigangdemo.fragmentinterface.InterfaceMysellOrderState;
import com.suctan.huigangdemo.mvp.login.sellorder.MySellOrderPresenter;
import com.suctan.huigangdemo.mvp.login.sellorder.MySellOrderView;
import com.suctan.huigangdemo.widget.MvpFragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by B-305 on 2017/4/19.
 */

public class SellFinishAFrag extends MvpFragment<MySellOrderPresenter> implements MySellOrderView, SellAFinishOrederAdapter.SellAFinishDetailOnClickListener {
    private InterfaceMysellOrderState Listener;
    private ListView listView;
    private boolean isFirstCreate;

    ArrayList<SellOrderBean> sellAFinishs = new ArrayList<>();


    public static SellFinishAFrag getFragInstant() {
        SellFinishAFrag mySell_three = new SellFinishAFrag();
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

    private void getSellFinishAOrderData() {
        Map<String, Object> map = new HashMap<>();
        map.put("user_token", TokenManager.getToken());
        mvpPresenter.getMySellFinishAOrderList(map);
    }

    @Override
    protected void dataLazyLoad() {
        getSellFinishAOrderData();
    }

    @Override
    protected void initViewsAddEvents() {
        listView = getListView();
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.mysell_fragment_four;
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

    }

    @Override
    public void sellDoingAFail() {

    }

    @Override
    public void sellFinishASuc(ArrayList<SellOrderBean> sellAFinish) {
        sellAFinishs.addAll(sellAFinish);
        initAdapter(sellAFinishs);
        onfinishRefreshOrLoad(true);
    }


    SellAFinishOrederAdapter finishAdapter;

    private void initAdapter(ArrayList<SellOrderBean> sellAFinishs) {
        if (!isFirstCreate) {
            finishAdapter = new SellAFinishOrederAdapter(getActivity(), sellAFinishs);
            listView.setAdapter(finishAdapter);
            isFirstCreate = true;
            finishAdapter.onDetailOnclick(this);
        } else {
            finishAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void sellFinishAFail() {
        onfinishRefreshOrLoad(false);
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
        intent.putExtra("sell", sellAFinishs.get(position));
        intent.putExtra("buySort", 0);
        getActivity().startActivity(intent);
    }

    @Override
    public void onSellAllComments(int position) {
        Intent intent = new Intent(getActivity(), SellOrderAublicComment.class);
        intent.putExtra("ACommend", sellAFinishs.get(position));
        startActivity(intent);
    }
}
