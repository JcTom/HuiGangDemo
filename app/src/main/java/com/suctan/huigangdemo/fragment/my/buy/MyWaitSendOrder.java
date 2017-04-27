package com.suctan.huigangdemo.fragment.my.buy;

import android.support.annotation.ArrayRes;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.suctan.huigangdemo.R;
import com.suctan.huigangdemo.acache.TokenManager;
import com.suctan.huigangdemo.adapter.order.buyorder.WaitRecommdOrederAdapter;
import com.suctan.huigangdemo.adapter.order.buyorder.WaitSendRecommdOrederAdapter;
import com.suctan.huigangdemo.bean.order.buy.BuyRecommendBean;
import com.suctan.huigangdemo.fragmentinterface.InterFaceOrderState;
import com.suctan.huigangdemo.mvp.login.buyorder.MyBuyOrderPresenter;
import com.suctan.huigangdemo.mvp.login.buyorder.MyBuyOrderView;
import com.suctan.huigangdemo.widget.MvpFragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by B-305 on 2017/4/19.
 */

public class MyWaitSendOrder extends MvpFragment<MyBuyOrderPresenter> implements MyBuyOrderView {

    //这个fragment对应这个，我卖出的中的两个选项栏中的已完成
    private InterFaceOrderState Listner;
    private int orderSortKey;//当前显示的订单类型
    private ListView listView;
    private boolean isFirstCreate;
    private ArrayList<BuyRecommendBean> waitSendRecommendBeenList = new ArrayList<>();

    public static MyWaitSendOrder getFragmentInstant() {
        MyWaitSendOrder waitSendFrag = new MyWaitSendOrder();
        return waitSendFrag;
    }

    private void getWaitSendEatOrderList() {
        Map<String, Object> map = new HashMap<>();
        map.put("user_token", TokenManager.getToken());
        mvpPresenter.getBuyPublishWaitSendOrderList(map);
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
        getWaitSendEatOrderList();
    }

    @Override
    protected void initViewsAddEvents() {
        listView = getListView();
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.mybuy_three;
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

    WaitSendRecommdOrederAdapter waitSendRecommdOrederAdapter;

    private void initAdapter(ArrayList<BuyRecommendBean> waitRecommendOrder) {
        if (!isFirstCreate && listView != null) {
            waitSendRecommdOrederAdapter = new WaitSendRecommdOrederAdapter(getActivity(), waitRecommendOrder);
            listView.setAdapter(waitSendRecommdOrederAdapter);
            isFirstCreate = true;
        } else {
            waitSendRecommdOrederAdapter.notifyDataSetChanged();
        }

    }

    @Override
    public void getWaitSendRecommderOrderSuc(ArrayList<BuyRecommendBean> waitSendRecommendOrder) {
        waitSendRecommendBeenList.addAll(waitSendRecommendOrder);
        initAdapter(waitSendRecommendBeenList);
        onfinishRefreshOrLoad(true);
    }

    @Override
    public void getWaitSendRecommderOrderFail() {
        onfinishRefreshOrLoad(false);
    }

    @Override
    public void getFinishRecommderOrderSuc(ArrayList<BuyRecommendBean> finishRecommendOrder) {

    }

    @Override
    public void getFinishRecommderOrderFail() {

    }
}
