package com.suctan.huigangdemo.fragment.my.buy;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.suctan.huigangdemo.R;
import com.suctan.huigangdemo.acache.TokenManager;
import com.suctan.huigangdemo.adapter.order.buyorder.AllRecommdOrederAdapter;
import com.suctan.huigangdemo.bean.order.buy.BuyRecommendBean;
import com.suctan.huigangdemo.fragmentinterface.InterFaceOrderState;
import com.suctan.huigangdemo.mvp.login.buyorder.MyBuyOrderPresenter;
import com.suctan.huigangdemo.mvp.login.buyorder.MyBuyOrderView;
import com.suctan.huigangdemo.widget.MvpFragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by B-305 on 2017/4/19.
 */

public class MyPublishAllOrder extends MvpFragment<MyBuyOrderPresenter> implements MyBuyOrderView {

    //这个fragment对应这个，我卖出的中的两个选项栏中的已完成
    private InterFaceOrderState Listner;
    private int orderSortKey;//当前显示的订单类型
    private ListView allListView;
    private ArrayList<BuyRecommendBean> allBuyRecommendList = new ArrayList<>();
    private boolean isFirstCreate;

    public static MyPublishAllOrder getFragmentInstant() {
        MyPublishAllOrder allOrderFrag = new MyPublishAllOrder();
        return allOrderFrag;
    }

    public void setOrderSortKey(int orderSortKey) {
        this.orderSortKey = orderSortKey;
    }

    public void setFragListner(InterFaceOrderState Listner) {
        this.Listner = Listner;
    }

    @Override
    protected void onDataRefresh() {

    }

    @Override
    protected void onDataLoadMore() {

    }

    @Override
    protected void dataLazyLoad() {
        getAllEatOrderList();
    }

    @Override
    protected void initViewsAddEvents() {
        allListView = getListView();
    }

    private void getAllEatOrderList() {
        Map<String, Object> map = new HashMap<>();
        map.put("user_token", TokenManager.getToken());
        mvpPresenter.getBuyPublishAllOrderList(map);
    }

    AllRecommdOrederAdapter allRecommdOrederAdapter;

    private void initAdapter(ArrayList<BuyRecommendBean> allBuyRecommendList) {
        if (!isFirstCreate && allListView != null) {
            allRecommdOrederAdapter = new AllRecommdOrederAdapter(getActivity(), allBuyRecommendList);
            allListView.setAdapter(allRecommdOrederAdapter);
            isFirstCreate=true;
        } else {
            allRecommdOrederAdapter.notifyDataSetChanged();
        }
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.mybuy_one;
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
    protected MyBuyOrderPresenter createPresenter() {
        return new MyBuyOrderPresenter(this);
    }

    @Override
    public void getAllRecommderOrderSuc(ArrayList<BuyRecommendBean> allRecommendOrder) {
        allBuyRecommendList.addAll(allRecommendOrder);
        initAdapter(allBuyRecommendList);
        onfinishRefreshOrLoad(true);
    }

    @Override
    public void getAllRecommderOrderFail() {
        onfinishRefreshOrLoad(false);
    }

    @Override
    public void getWaitRecommderOrderSuc(ArrayList<BuyRecommendBean> waitRecommendOrder) {

    }

    @Override
    public void getWaitRecommderOrderFail() {

    }

    @Override
    public void getWaitSendRecommderOrderSuc(ArrayList<BuyRecommendBean> waitSendRecommendOrder) {

    }

    @Override
    public void getWaitSendRecommderOrderFail() {

    }

    @Override
    public void getFinishRecommderOrderSuc(ArrayList<BuyRecommendBean> finishRecommendOrder) {

    }

    @Override
    public void getFinishRecommderOrderFail() {

    }
}
