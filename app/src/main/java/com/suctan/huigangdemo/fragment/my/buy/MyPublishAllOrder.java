package com.suctan.huigangdemo.fragment.my.buy;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.suctan.huigangdemo.R;
import com.suctan.huigangdemo.acache.TokenManager;
import com.suctan.huigangdemo.activity.order.BuyOrderDetailActivity;
import com.suctan.huigangdemo.activity.order.BuyOrderPublicComment;
import com.suctan.huigangdemo.adapter.order.buyorder.AllRecommdOrederAdapter;
import com.suctan.huigangdemo.bean.commend.buy.BuyACommendReturn;
import com.suctan.huigangdemo.bean.commend.buy.BuyPCommendReturn;
import com.suctan.huigangdemo.bean.order.buy.BuyRecommendBean;
import com.suctan.huigangdemo.fragmentinterface.InterFaceOrderState;
import com.suctan.huigangdemo.mvp.login.buyorder.MyBuyOrderPresenter;
import com.suctan.huigangdemo.mvp.login.buyorder.MyBuyOrderView;
import com.suctan.huigangdemo.widget.MvpFragment;
import com.suctan.huigangdemo.widget.TipsCancelOrderDialog;
import com.suctan.huigangdemo.widget.TipsComfirmOrderDialog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by B-305 on 2017/4/19.
 */

public class MyPublishAllOrder extends MvpFragment<MyBuyOrderPresenter> implements MyBuyOrderView, AllRecommdOrederAdapter.AllDetailOnClickListener {


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
            isFirstCreate = true;
            allRecommdOrederAdapter.onDetailOnclick(this);
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

    @Override
    public void buyPuCancelSuc(int order) {
        for (int i = 0; i < order; i++) {
            if (allBuyRecommendList.get(i).getOrder_id() == order) {
                allBuyRecommendList.get(i).setOrder_status(0);
                break;
            }

        }
        if (allRecommdOrederAdapter != null) {
            allRecommdOrederAdapter.setDataChange(allBuyRecommendList);
        }

    }

    @Override
    public void buyPuComfirmSuc(int orderId) {
        for (int i = 0; i < allBuyRecommendList.size(); i++) {
            if (allBuyRecommendList.get(i).getOrder_id() == orderId) {
                allBuyRecommendList.get(i).setOrder_status(3);
                break;
            }
        }
        if (allRecommdOrederAdapter != null) {
            allRecommdOrederAdapter.setDataChange(allBuyRecommendList);
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
        intent.putExtra("buy", allBuyRecommendList.get(position));
        intent.putExtra("buySort", 1);
        getActivity().startActivity(intent);
    }

    @Override
    public void onCancelOnclick(int position) {
        showCancelTip(position);
    }

    @Override
    public void onComfrimOnclick(int position) {
        showComfrirmTip(position);

    }

    @Override
    public void onCheckComment(int position) {
        Intent intent = new Intent(getActivity(), BuyOrderPublicComment.class);
        intent.putExtra("pComment", allBuyRecommendList.get(position));
        startActivity(intent);
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
        map.put("order_id", allBuyRecommendList.get(position).getOrder_id());
        mvpPresenter.buyPubComfirmOrder(map, allBuyRecommendList.get(position).getOrder_id());
    }


    //取消订单
    private void requestCancelOrder(int position) {
        Map<String, Object> map = new HashMap();
        map.put("user_token", TokenManager.getToken());
        map.put("order_id", allBuyRecommendList.get(position).getOrder_id());
        mvpPresenter.buyPubCancelOrder(map, allBuyRecommendList.get(position).getOrder_id());
    }

    private void showCancelTip(final int position) {
        final TipsCancelOrderDialog cancelDialog = new TipsCancelOrderDialog(getActivity());
        cancelDialog.setTipClickLisener(new TipsCancelOrderDialog.OnTipLisetner() {
            @Override
            public void comfirm() {
                cancelDialog.dismiss();
                requestCancelOrder(position);
            }

            @Override
            public void cancel() {
                cancelDialog.dismiss();
            }
        });
        cancelDialog.show();
    }
}
