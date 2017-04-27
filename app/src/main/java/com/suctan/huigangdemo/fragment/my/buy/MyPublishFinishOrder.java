package com.suctan.huigangdemo.fragment.my.buy;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.suctan.huigangdemo.R;
import com.suctan.huigangdemo.acache.TokenManager;
import com.suctan.huigangdemo.adapter.order.buyorder.FinishRecommdOrederAdapter;
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

public class MyPublishFinishOrder extends MvpFragment<MyBuyOrderPresenter> implements MyBuyOrderView {

    //这个fragment对应这个，我卖出的中的两个选项栏中的已完成
    private InterFaceOrderState Listner;
    private int orderSortKey;//当前显示的订单类型
    private ListView finishListView;
    private ArrayList<BuyRecommendBean> finishRecommendBeenList = new ArrayList<>();
    private boolean isFirstCreate;


    public static MyPublishFinishOrder getFragmentInstant() {
        MyPublishFinishOrder finishOrderFrag = new MyPublishFinishOrder();
        return finishOrderFrag;
    }

    public void setOrderSortKey(int orderSortKey) {
        this.orderSortKey = orderSortKey;
    }

    public void setFragListner(InterFaceOrderState Listner) {
        this.Listner = Listner;
    }

    private void getFinishEatOrderList() {
        Map<String, Object> map = new HashMap<>();
        map.put("user_token", TokenManager.getToken());
        mvpPresenter.getBuyPublishFinishOrderList(map);
    }

    @Override
    protected void onDataRefresh() {

    }

    @Override
    protected void onDataLoadMore() {

    }

    @Override
    protected void dataLazyLoad() {
        getFinishEatOrderList();
    }

    @Override
    protected void initViewsAddEvents() {
        finishListView = getListView();
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.mybuy_four;
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

    }

    @Override
    public void getAllRecommderOrderFail() {

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
        finishRecommendBeenList.addAll(finishRecommendOrder);
        initAdapter(finishRecommendBeenList);

        onfinishRefreshOrLoad(true);
    }

    FinishRecommdOrederAdapter finishAdapter;

    private void initAdapter(ArrayList<BuyRecommendBean> finishRecommendBeenList) {
        if (!isFirstCreate && finishListView != null) {
            finishAdapter = new FinishRecommdOrederAdapter(getActivity(), finishRecommendBeenList);
            finishListView.setAdapter(finishAdapter);
            isFirstCreate = true;
        } else {
            finishAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void getFinishRecommderOrderFail() {
        onfinishRefreshOrLoad(false);
    }
}
