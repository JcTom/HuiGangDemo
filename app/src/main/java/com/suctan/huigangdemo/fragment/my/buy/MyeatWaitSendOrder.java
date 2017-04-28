package com.suctan.huigangdemo.fragment.my.buy;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.suctan.huigangdemo.R;
import com.suctan.huigangdemo.acache.TokenManager;
import com.suctan.huigangdemo.activity.order.BuyOrderDetailActivity;
import com.suctan.huigangdemo.adapter.order.buyorder.WaitSendMakeOrederAdapter;
import com.suctan.huigangdemo.bean.commend.buy.BuyACommendReturn;
import com.suctan.huigangdemo.bean.commend.buy.BuyPCommendReturn;
import com.suctan.huigangdemo.bean.order.buy.BuyRecommendBean;
import com.suctan.huigangdemo.fragmentinterface.InterFaceOrderState;
import com.suctan.huigangdemo.mvp.login.buyorder.MyBuyOrderPresenter;
import com.suctan.huigangdemo.mvp.login.buyorder.MyBuyOrderView;
import com.suctan.huigangdemo.widget.MvpFragment;
import com.suctan.huigangdemo.widget.TipsComfirmOrderDialog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by B-305 on 2017/4/19.
 */

public class MyeatWaitSendOrder extends MvpFragment<MyBuyOrderPresenter> implements MyBuyOrderView, WaitSendMakeOrederAdapter.WaitSendMakeDetailOnClickListener {

    //这个fragment对应这个，我卖出的中的两个选项栏中的已完成
    private InterFaceOrderState Listner;
    private int orderSortKey;//当前显示的订单类型
    private ArrayList<BuyRecommendBean> makeWaitSendLists = new ArrayList<>();
    private boolean isFirstCreate;
    private ListView waitSendListView;

    public static MyeatWaitSendOrder getFragmentInstant() {
        MyeatWaitSendOrder waitSendFrag = new MyeatWaitSendOrder();
        return waitSendFrag;
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

    private void getMakeBuyWaitSendOrderList() {
        Map<String, Object> map = new HashMap<>();
        map.put("user_token", TokenManager.getToken());
        mvpPresenter.getBuyMakeWaitSendOrderList(map);
    }

    @Override
    protected void dataLazyLoad() {
        getMakeBuyWaitSendOrderList();
    }

    @Override
    protected void initViewsAddEvents() {
        waitSendListView = getListView();
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

    @Override
    public void buyPuCancelSuc(int pisition) {

    }

    @Override
    public void buyPuComfirmSuc(int orderId) {

    }

    @Override
    public void getCommendPSuc(BuyPCommendReturn buyPCommendReturn) {

    }

    @Override
    public void getCommendPFail() {

    }

    @Override
    public void addBuyCommendPSuc() {

    }

    @Override
    public void getCommendASuc(BuyACommendReturn buyACommendReturn) {

    }



    @Override
    public void getCommendAFail() {

    }


    @Override
    public void getAllMakeOrderSrc(ArrayList<BuyRecommendBean> makeAllList) {

    }

    @Override
    public void getAllMakeOrderFail() {

    }

    @Override
    public void getWaitMakeOrderSuc(ArrayList<BuyRecommendBean> makeWaitList) {

    }

    @Override
    public void getWaitMakeOrderFail() {

    }


    @Override
    public void getMakeWaitSendOrderSuc(ArrayList<BuyRecommendBean> makeWaitSendList) {
        makeWaitSendLists.addAll(makeWaitSendList);
        initAdapter(makeWaitSendLists);

        onfinishRefreshOrLoad(true);
    }

    WaitSendMakeOrederAdapter waitSendOrderAdapter;

    private void initAdapter(ArrayList<BuyRecommendBean> makeWaitSendLists) {
        if (!isFirstCreate) {
            waitSendOrderAdapter = new WaitSendMakeOrederAdapter(getActivity(), makeWaitSendLists);
            waitSendListView.setAdapter(waitSendOrderAdapter);
            isFirstCreate = true;
            waitSendOrderAdapter.onDetailOnclick(this);
        } else {
            waitSendOrderAdapter.notifyDataSetChanged();
        }


    }

    @Override
    public void getMakeWaitSendOrderFail() {
        onfinishRefreshOrLoad(false);
    }

    @Override
    public void getMakeFinishOrderSuc(ArrayList<BuyRecommendBean> makeFinishList) {

    }

    @Override
    public void getMakeFinishOrderFail() {

    }

    @Override
    public void buyAComfirmSuc(BuyRecommendBean buyRecommendBean) {
        for (int i = 0; i < makeWaitSendLists.size(); i++) {
            if (buyRecommendBean.getId() == makeWaitSendLists.get(i).getId()) {
                makeWaitSendLists.remove(makeWaitSendLists.get(i));
                break;
            }
        }
        if (waitSendOrderAdapter != null) {
            waitSendOrderAdapter.setDataChange(makeWaitSendLists);
        }

    }

    @Override
    public void addBuyACommendSuc() {

    }

    @Override
    public void onItemOnClick(int position) {
        Intent intent = new Intent(getActivity(), BuyOrderDetailActivity.class);
        intent.putExtra("buy", makeWaitSendLists.get(position));
        intent.putExtra("buySort", 0);
        getActivity().startActivity(intent);
    }

    private void requestComfirmOrder(int position) {
        Map<String, Object> map = new HashMap<>();
        map.put("user_token", TokenManager.getToken());
        map.put("id", makeWaitSendLists.get(position).getId());
        mvpPresenter.buyComfirmAOrder(map, makeWaitSendLists.get(position));
    }

    private void showComfirmTips(final int position) {
        final TipsComfirmOrderDialog comfirmTip = new TipsComfirmOrderDialog(getActivity());
        comfirmTip.setTipClickLisener(new TipsComfirmOrderDialog.OnTipLisetner() {
            @Override
            public void comfirm() {
                requestComfirmOrder(position);
                comfirmTip.dismiss();
            }

            @Override
            public void cancel() {
                comfirmTip.dismiss();
            }
        });
        comfirmTip.show();

    }

    @Override
    public void onComfirmAOnclick(int position) {
        showComfirmTips(position);
    }
}
