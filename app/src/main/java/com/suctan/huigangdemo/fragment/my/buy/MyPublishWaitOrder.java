package com.suctan.huigangdemo.fragment.my.buy;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.suctan.huigangdemo.R;
import com.suctan.huigangdemo.acache.TokenManager;
import com.suctan.huigangdemo.activity.order.BuyOrderDetailActivity;
import com.suctan.huigangdemo.adapter.order.buyorder.WaitRecommdOrederAdapter;
import com.suctan.huigangdemo.bean.commend.buy.BuyACommendReturn;
import com.suctan.huigangdemo.bean.commend.buy.BuyPCommendReturn;
import com.suctan.huigangdemo.bean.order.buy.BuyRecommendBean;
import com.suctan.huigangdemo.fragmentinterface.InterFaceOrderState;
import com.suctan.huigangdemo.mvp.login.buyorder.MyBuyOrderPresenter;
import com.suctan.huigangdemo.mvp.login.buyorder.MyBuyOrderView;
import com.suctan.huigangdemo.widget.MvpFragment;
import com.suctan.huigangdemo.widget.TipsCancelOrderDialog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by B-305 on 2017/4/19.
 */

public class MyPublishWaitOrder extends MvpFragment<MyBuyOrderPresenter> implements MyBuyOrderView, WaitRecommdOrederAdapter.WaitDetailOnClickListener {

    //这个fragment对应这个，我卖出的中的两个选项栏中的已完成
    private InterFaceOrderState Listner;
    private int orderSortKey;//当前显示的订单类型
    private ListView listView;

    private boolean isFirstCreate;
    private ArrayList<BuyRecommendBean> waitRecommendBeanList = new ArrayList<>();


    public static MyPublishWaitOrder getFragmentInstant() {
        MyPublishWaitOrder waitReceiveFrag = new MyPublishWaitOrder();
        return waitReceiveFrag;
    }

    public void setOrderSortKey(int orderSortKey) {
        this.orderSortKey = orderSortKey;
    }

    public void setFragListner(InterFaceOrderState Listner) {
        this.Listner = Listner;
    }

    private void getWaitEatOrderList() {
        Map<String, Object> map = new HashMap<>();
        map.put("user_token", TokenManager.getToken());
        mvpPresenter.getBuyPublishWaitReceiveOrderList(map);
    }

    @Override
    protected void onDataRefresh() {

    }

    @Override
    protected void onDataLoadMore() {

    }

    @Override
    protected void dataLazyLoad() {
        getWaitEatOrderList();
    }

    @Override
    protected void initViewsAddEvents() {
        listView = getListView();
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.mybuy_two;
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
        waitRecommendBeanList.addAll(waitRecommendOrder);
        initAdapter(waitRecommendOrder);
        onfinishRefreshOrLoad(true);

    }

    WaitRecommdOrederAdapter waitRecomendAdapter;

    private void initAdapter(ArrayList<BuyRecommendBean> waitRecommendOrder) {
        if (!isFirstCreate && listView != null) {
            waitRecomendAdapter = new WaitRecommdOrederAdapter(getActivity(), waitRecommendOrder);
            listView.setAdapter(waitRecomendAdapter);
            isFirstCreate = true;
            waitRecomendAdapter.onDetailOnclick(this);
        } else {
            waitRecomendAdapter.notifyDataSetChanged();
        }

    }

    @Override
    public void getWaitRecommderOrderFail() {
        onfinishRefreshOrLoad(false);
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
    public void buyPuCancelSuc(int orderId) {
        for (int i = 0; i < waitRecommendBeanList.size(); i++) {
            if (waitRecommendBeanList.get(i).getOrder_id() == orderId) {
                waitRecommendBeanList.remove(waitRecommendBeanList.get(i));
                break;
            }
        }
        if (waitRecomendAdapter != null) {
            waitRecomendAdapter.setDataChange(waitRecommendBeanList);
        }
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
        intent.putExtra("buy", waitRecommendBeanList.get(position));
        intent.putExtra("buySort", 1);
        getActivity().startActivity(intent);
    }

    @Override
    public void onCancelOnclick(int position) {
        showCancelTip(position);
    }

    //取消订单
    private void requestCancelOrder(int position) {
        Map<String, Object> map = new HashMap();
        map.put("user_token", TokenManager.getToken());
        map.put("order_id", waitRecommendBeanList.get(position).getOrder_id());
        mvpPresenter.buyPubCancelOrder(map, waitRecommendBeanList.get(position).getOrder_id());
    }


    private void showCancelTip(final int Position) {
        final TipsCancelOrderDialog cancelDialog = new TipsCancelOrderDialog(getActivity());
        cancelDialog.setTipClickLisener(new TipsCancelOrderDialog.OnTipLisetner() {
            @Override
            public void comfirm() {
                cancelDialog.dismiss();
                requestCancelOrder(Position);
            }

            @Override
            public void cancel() {
                cancelDialog.dismiss();
            }
        });
        cancelDialog.show();
    }
}
