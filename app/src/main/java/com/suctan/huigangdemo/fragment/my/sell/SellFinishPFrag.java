package com.suctan.huigangdemo.fragment.my.sell;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.suctan.huigangdemo.R;
import com.suctan.huigangdemo.acache.TokenManager;
import com.suctan.huigangdemo.activity.order.SellOrderDetailActivity;
import com.suctan.huigangdemo.activity.order.SellOrderPublicComment;
import com.suctan.huigangdemo.adapter.order.sellorder.SellPFinishOrederAdapter;
import com.suctan.huigangdemo.bean.order.sell.SellOrderBean;
import com.suctan.huigangdemo.fragmentinterface.InterfaceMysellOrderState;
import com.suctan.huigangdemo.mvp.login.sellorder.MySellOrderPresenter;
import com.suctan.huigangdemo.mvp.login.sellorder.MySellOrderView;
import com.suctan.huigangdemo.widget.MvpFragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by B-305 on 2017/4/19.
 */

public class SellFinishPFrag extends MvpFragment<MySellOrderPresenter> implements MySellOrderView, SellPFinishOrederAdapter.SellPFinishDetailOnClickListener {
    private InterfaceMysellOrderState Listener;
    private boolean isFirstCreate;
    private ListView sellFinshListView;
    private ArrayList<SellOrderBean> sellFinishLists = new ArrayList<>();


    public static SellFinishPFrag getFragInstant() {
        SellFinishPFrag mySell_three = new SellFinishPFrag();
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

    private void getSellFinishPOrderData() {
        Map<String, Object> map = new HashMap<>();
        map.put("user_token", TokenManager.getToken());
        mvpPresenter.getMySellFinishPOrderList(map);
    }

    @Override
    protected void dataLazyLoad() {
        getSellFinishPOrderData();
    }

    @Override
    protected void initViewsAddEvents() {
        sellFinshListView = getListView();
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.mysells_fragment_three;
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
    public void sellFinishPSuc(ArrayList<SellOrderBean> sellFinishList) {
        sellFinishLists.addAll(sellFinishList);
        initAdapter(sellFinishLists);
        onfinishRefreshOrLoad(true);

    }

    SellPFinishOrederAdapter finishAdapter;

    private void initAdapter(ArrayList<SellOrderBean> sellFinishLists) {
        if (!isFirstCreate) {
            finishAdapter = new SellPFinishOrederAdapter(getActivity(), sellFinishLists);
            sellFinshListView.setAdapter(finishAdapter);
            isFirstCreate = true;
            finishAdapter.onDetailOnclick(this);
        } else {
            finishAdapter.notifyDataSetChanged();
        }

    }

    @Override
    public void sellFinishPFail() {
        onfinishRefreshOrLoad(true);
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
        intent.putExtra("sell", sellFinishLists.get(position));
        intent.putExtra("buySort", 1);
        getActivity().startActivity(intent);
    }

    @Override
    public void onSellPCommentOnClicks(int position) {
        Intent intent = new Intent(getActivity(), SellOrderPublicComment.class);
        intent.putExtra("pComment", sellFinishLists.get(position));
        startActivity(intent);
    }
}
