package com.suctan.huigangdemo.fragment.my.buy;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.suctan.huigangdemo.R;
import com.suctan.huigangdemo.acache.TokenManager;
import com.suctan.huigangdemo.activity.order.BuyOrderDetailActivity;
import com.suctan.huigangdemo.adapter.order.buyorder.WaitSendRecommdOrederAdapter;
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

public class MyPublishWaitSendOrder extends MvpFragment<MyBuyOrderPresenter> implements MyBuyOrderView, WaitSendRecommdOrederAdapter.WaitSendDetailOnClickListener {

    //这个fragment对应这个，我卖出的中的两个选项栏中的已完成
    private InterFaceOrderState Listner;
    private int orderSortKey;//当前显示的订单类型
    private ListView listView;
    private boolean isFirstCreate;
    private ArrayList<BuyRecommendBean> waitSendRecommendBeenList = new ArrayList<>();

    public static MyPublishWaitSendOrder getFragmentInstant() {
        MyPublishWaitSendOrder waitSendFrag = new MyPublishWaitSendOrder();
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
            waitSendRecommdOrederAdapter.onDetailOnclick(this);
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


    private void showComfrirmTip(final int position) {
        final TipsComfirmOrderDialog comfirmTip = new TipsComfirmOrderDialog(getActivity());
        comfirmTip.setTipClickLisener(new TipsComfirmOrderDialog.OnTipLisetner() {
            @Override
            public void comfirm() {
                comfirmTip.dismiss();
                requstComfirm(position);
            }

            @Override
            public void cancel() {
                comfirmTip.dismiss();
            }
        });


        comfirmTip.show();

    }

    //请求确认订单
    private void requstComfirm(int position) {
        Map<String, Object> map = new HashMap<>();
        map.put("user_token", TokenManager.getToken());
        map.put("order_id", waitSendRecommendBeenList.get(position).getOrder_id());
        mvpPresenter.buyPubComfirmOrder(map, waitSendRecommendBeenList.get(position).getOrder_id());
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

    @Override
    public void buyPuCancelSuc(int pisition) {

    }

    @Override
    public void buyPuComfirmSuc(int orderId) {
        for (int i = 0; i < waitSendRecommendBeenList.size(); i++) {
            if (orderId == waitSendRecommendBeenList.get(i).getOrder_id()) {
                waitSendRecommendBeenList.remove(waitSendRecommendBeenList.get(i));
                break;
            }
        }
        if (waitSendRecommdOrederAdapter != null) {
            waitSendRecommdOrederAdapter.setDataChange(waitSendRecommendBeenList);
        }

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

    }

    @Override
    public void getMakeWaitSendOrderFail() {

    }

    @Override
    public void getMakeFinishOrderSuc(ArrayList<BuyRecommendBean> makeFinishList) {

    }

    @Override
    public void getMakeFinishOrderFail() {

    }

    @Override
    public void buyAComfirmSuc(BuyRecommendBean orderId) {

    }

    @Override
    public void addBuyACommendSuc() {

    }

    @Override
    public void onItemOnClick(int position) {
        Intent intent = new Intent(getActivity(), BuyOrderDetailActivity.class);
        intent.putExtra("buy", waitSendRecommendBeenList.get(position));
        intent.putExtra("buySort", 1);
        getActivity().startActivity(intent);
    }

    @Override
    public void onComfrimOnclick(int position) {
        showComfrirmTip(position);
    }
}
