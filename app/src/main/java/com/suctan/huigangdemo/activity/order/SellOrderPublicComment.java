package com.suctan.huigangdemo.activity.order;

import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.androidbase.LoadImageManager;
import com.example.androidbase.mvp.MvpActivity;
import com.example.androidbase.widget.CircleImageView;
import com.jaeger.library.StatusBarUtil;
import com.suctan.huigangdemo.R;
import com.suctan.huigangdemo.acache.CurrentUser;
import com.suctan.huigangdemo.acache.TokenManager;
import com.suctan.huigangdemo.bean.commend.buy.BuyACommendReturn;
import com.suctan.huigangdemo.bean.commend.buy.BuyPCommendCookBean;
import com.suctan.huigangdemo.bean.commend.buy.BuyPCommendCusBean;
import com.suctan.huigangdemo.bean.commend.buy.BuyPCommendReturn;
import com.suctan.huigangdemo.bean.order.buy.BuyRecommendBean;
import com.suctan.huigangdemo.bean.order.sell.SellOrderBean;
import com.suctan.huigangdemo.mvp.login.buyorder.MyBuyOrderPresenter;
import com.suctan.huigangdemo.mvp.login.buyorder.MyBuyOrderView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 黄淑翰 on 2017/4/28.
 */
public class SellOrderPublicComment extends MvpActivity<MyBuyOrderPresenter> implements View.OnClickListener, MyBuyOrderView {

    CircleImageView cir_cusComment_pic;
    TextView tv_CurComment_userName;
    TextView tv_CurComment_content;
    TextView tv_CurComment_time;
    ImageView imv_BuyPcommend_back;
    CircleImageView cir_cookCommend_pic;
    TextView tv_cookCommend_userName;
    TextView tv_cookCommend_content;
    TextView tv_cookCommend_title;
    TextView tv_cookCommend_time;
    private boolean showBtn;

    Button btn_add_pcomment;
    EditText et_checkcomment_reply;
    Button btn_checkcomment_comment;
    private PopupWindow popupW;
    private SellOrderBean sellOrderBean;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publiccomment_detail);
        StatusBarUtil.setColor(this, getResources().getColor(R.color.colorPrimary), 0);
        initView();
        getIntentData();
    }

    private void getIntentData() {
        sellOrderBean = (SellOrderBean) getIntent().getSerializableExtra("pComment");
        if (sellOrderBean != null) {
            Map<String, Object> map = new HashMap();
            map.put("user_token", TokenManager.getToken());
            map.put("order_id", sellOrderBean.getOrder_id());
            mvpPresenter.buyCheckPCommentOrder(map);
        }
    }


    @Override
    protected MyBuyOrderPresenter createPresenter() {
        return new MyBuyOrderPresenter(this);
    }

    private void initWindowView(View view) {
        et_checkcomment_reply = (EditText) view.findViewById(R.id.et_checkcomment_reply);
        btn_checkcomment_comment = (Button) view.findViewById(R.id.btn_checkcomment_comment);
        et_checkcomment_reply.setOnClickListener(this);
        btn_checkcomment_comment.setOnClickListener(this);
    }

    private void initView() {
        btn_add_pcomment = (Button) findViewById(R.id.btn_add_pcomment);
        cir_cusComment_pic = (CircleImageView) findViewById(R.id.cir_cusComment_pic);
        tv_CurComment_userName = (TextView) findViewById(R.id.tv_CurComment_userName);
        tv_CurComment_content = (TextView) findViewById(R.id.tv_CurComment_content);
        tv_CurComment_time = (TextView) findViewById(R.id.tv_CurComment_time);
        imv_BuyPcommend_back = (ImageView) findViewById(R.id.imv_BuyPcommend_back);
        cir_cookCommend_pic = (CircleImageView) findViewById(R.id.cir_cookCommend_pic);
        tv_cookCommend_userName = (TextView) findViewById(R.id.tv_cookCommend_userName);
        tv_cookCommend_content = (TextView) findViewById(R.id.tv_cookCommend_content);
        tv_cookCommend_title = (TextView) findViewById(R.id.tv_cookCommend_title);
        tv_cookCommend_time = (TextView) findViewById(R.id.tv_cookCommend_time);

        btn_add_pcomment.setOnClickListener(this);
    }

    private void toogleShowPop(boolean isShow) {
        if (isShow) {
            popupW = new PopupWindow(this);
            popupW.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
            popupW.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
            popupW.setBackgroundDrawable(new BitmapDrawable());
            popupW.setOutsideTouchable(true);
            popupW.setFocusable(true);
            popupW.setTouchable(true);
            popupW.setAnimationStyle(R.style.CommentPopWindowStyle);
            View view = LayoutInflater.from(this).inflate(R.layout.add_order_comment, null, false);
            initWindowView(view);
            popupW.setContentView(view);
            popupW.showAsDropDown(findViewById(R.id.ly_pcomment));

        } else {
            popupW.dismiss();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_checkcomment_comment:
                toogleShowPop(false);
                toolgleSendComment(false);
                postCommentVearity();
                break;
            case R.id.et_checkcomment_reply:

                break;
            case R.id.btn_add_pcomment:
                toogleShowPop(true);
                break;
        }
    }


    //上传前进行判断
    private void postCommentVearity() {
        if (et_checkcomment_reply.getText().toString().isEmpty()) {
            toolgleSendComment(true);
            return;
        }
        publishComment();
    }

    private void publishComment() {
        Map<String, Object> map = new HashMap<>();
        map.put("user_token", TokenManager.getToken());
        map.put("order_id", sellOrderBean.getOrder_id());
        map.put("comment", et_checkcomment_reply.getText());
        mvpPresenter.sellAddPCommentOrder(map);
    }

    private void toolgleSendComment(boolean isClick) {
        if (isClick) {
            btn_checkcomment_comment.setClickable(false);
        } else {
            btn_checkcomment_comment.setClickable(true);
        }
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
    public void buyPuCancelSuc(int orderID) {

    }

    @Override
    public void buyPuComfirmSuc(int orderId) {

    }

    @Override
    public void getCommendPSuc(BuyPCommendReturn buyPCommendReturn) {
        initViewData(buyPCommendReturn);
    }

    private void initViewData(BuyPCommendReturn buyPCommendReturn) {
        if (buyPCommendReturn.getCookComment() != null) {
            BuyPCommendCusBean buyCusComment = buyPCommendReturn.getCusComment();
            if (buyCusComment.getUser_icon() != null) {
                LoadImageManager.getImageLoader().displayImage(buyCusComment.getUser_icon(), cir_cookCommend_pic);
            }
            tv_cookCommend_userName.setText(buyCusComment.getUser_name());
            tv_cookCommend_content.setText(buyCusComment.getOrder_cookcomment());
        } else {
            tv_cookCommend_content.setText("暂无对该定单进行评论！");
        }
        if (buyPCommendReturn.getCusComment() != null) {
            BuyPCommendCookBean buyPcommendCook = buyPCommendReturn.getCookComment();

            tv_CurComment_content.setText(buyPcommendCook.getOrder_cookcomment());
            if (buyPcommendCook.getUser_icon() != null) {
                LoadImageManager.getImageLoader().displayImage(buyPcommendCook.getUser_icon(), cir_cusComment_pic);
            }
            tv_CurComment_userName.setText(buyPcommendCook.getUser_name());
            btn_add_pcomment.setVisibility(View.GONE);
        } else {
            tv_CurComment_content.setText("暂无对该定单进行评论！");
            btn_add_pcomment.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void getCommendPFail() {

    }

    @Override
    public void addBuyCommendPSuc() {
        btn_add_pcomment.setVisibility(View.GONE);
        tv_CurComment_content.setText(et_checkcomment_reply.getText());

        LoadImageManager.getImageLoader().displayImage(CurrentUser.getInstance().getUserBean().getUser_icon(), cir_cusComment_pic);

        tv_CurComment_userName.setText(CurrentUser.getInstance().getUserBean().getUser_name());
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
    public void buyAComfirmSuc(BuyRecommendBean buyRecommendBean) {

    }

    @Override
    public void addBuyACommendSuc() {

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
}
