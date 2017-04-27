package com.suctan.huigangdemo.activity.cart;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.androidbase.mvp.MvpActivity;
import com.example.androidbase.utils.ScreenTools;
import com.jaeger.library.StatusBarUtil;
import com.roger.catloadinglibrary.CatLoadingView;
import com.suctan.huigangdemo.R;
import com.suctan.huigangdemo.acache.CurrentUser;
import com.suctan.huigangdemo.acache.TokenManager;
import com.suctan.huigangdemo.activity.address.addressActivity;
import com.suctan.huigangdemo.adapter.cart.ShoppingCartAdapter;
import com.suctan.huigangdemo.bean.cart.CartBean;
import com.suctan.huigangdemo.bean.index.EatFoodReturn;
import com.suctan.huigangdemo.mvp.login.cart.CartPresenter;
import com.suctan.huigangdemo.mvp.login.cart.CartView;
import com.suctan.huigangdemo.widget.TimePickDialog;
import com.suctan.huigangdemo.widget.TipsAddAddressDialog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 黄淑翰 on 2017/4/24.
 */
public class ShoppingCart extends MvpActivity<CartPresenter> implements View.OnClickListener, CartView, ShoppingCartAdapter.OnCartListClickListner {
    private ImageButton imb_cart_back;
    private TextView tv_anore_address;
    private TextView tv_receiver_time;
    private ListView lv_cart_item;
    private CheckBox checkbox_cart_all;
    private TextView tv_cart_allMomeny;
    private TimePickDialog timePickDialg;
    private LinearLayout ly_change_receiveTime;
    private int selectHour, selectMinute;
    private double cartAllPrice;
    private ArrayList<CartBean> cartBeanList = new ArrayList<>();

    private Button btn_pay_cart;
    private boolean isFirstCreatCartAdapter;
    private String tempTime = "01:01:00";

    private CatLoadingView catLoadingView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shopping_cart);
        StatusBarUtil.setColor(this, getResources().getColor(R.color.colorPrimary), 0);
        initView();
        getCartListRequest();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (tv_anore_address != null) {
            if (CurrentUser.getInstance().getUserBean().getUser_address() != null) {
                tv_anore_address.setText(CurrentUser.getInstance().getUserBean().getUser_address());
            }
        }
    }

    @Override
    protected CartPresenter createPresenter() {
        return new CartPresenter(this);
    }

    private void getCartListRequest() {
        Map<String, Object> map = new HashMap<>();
        map.put("user_token", TokenManager.getToken());
        mvpPresenter.getCartList(map);
    }

    private void initView() {
        catLoadingView = new CatLoadingView();
        ly_change_receiveTime = (LinearLayout) findViewById(R.id.ly_change_receiveTime);
        imb_cart_back = (ImageButton) findViewById(R.id.imb_cart_back);
        tv_anore_address = (TextView) findViewById(R.id.tv_anore_address);
        tv_receiver_time = (TextView) findViewById(R.id.tv_receiver_time);
        lv_cart_item = (ListView) findViewById(R.id.lv_cart_item);
        checkbox_cart_all = (CheckBox) findViewById(R.id.checkbox_cart_all);
        tv_cart_allMomeny = (TextView) findViewById(R.id.tv_cart_allMomeny);
        btn_pay_cart = (Button) findViewById(R.id.btn_pay_cart);


        //监听
        imb_cart_back.setOnClickListener(this);
        tv_anore_address.setOnClickListener(this);
        btn_pay_cart.setOnClickListener(this);
        ly_change_receiveTime.setOnClickListener(this);

//全选反选的选择
        checkbox_cart_all.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (cartBeanList.size() == 0 | cartAdapter == null) {
                    return;
                }
                toogleCheckAll(b);
                cartAdapter.notifyDataSetChanged();
            }
        });
    }

    private void toogleShowCataloading(boolean isShow) {
        if (isShow) {
            catLoadingView.show(getSupportFragmentManager(), "");
        } else {
            catLoadingView.dismiss();
        }
    }


    //请求结算购物车
    private void requestCartPay() {
        if (CurrentUser.getInstance().getUserBean().getUser_address() != null) {
            Map<String, Object> map = new HashMap<>();
            map.put("user_token", TokenManager.getToken());
            map.put("expect_time", tempTime);
            map.put("address", CurrentUser.getInstance().getUserBean().getUser_address());
            map.put("orderArrStr", PayCart());
            mvpPresenter.payCartPrice(map);
        } else {
            final TipsAddAddressDialog tipsAddAddressDialog = new TipsAddAddressDialog(this);
            tipsAddAddressDialog.setTipClickLisener(new TipsAddAddressDialog.OnTipLisetner() {
                @Override
                public void comfirm() {
                    Intent intent = new Intent(ShoppingCart.this, addressActivity.class);
                    startActivity(intent);
                    tipsAddAddressDialog.dismiss();
                }

                @Override
                public void cancel() {
                    tipsAddAddressDialog.dismiss();
                }
            });

            tipsAddAddressDialog.show();
        }

    }


    //结算购物车
    private String PayCart() {
        StringBuffer cartBuffer = new StringBuffer();
        for (int i = 0; i < cartBeanList.size(); i++) {
            if (cartBeanList.get(i).isCheck() == true) {
                CartBean cartBean = cartBeanList.get(i);
                if (cartBeanList.size() == cartBeanList.size() - 1) {
                    String item = cartBean.getOrder_id() + "comma" + cartBean.getOrder_title() + "comma" + cartBean.getOrder_pic()
                            + "comma" + cartBean.getOrder_num() + "comma" + cartBean.getOrder_price() + "comma" + cartBean.getOrder_type();
                    cartBuffer.append(item);
                } else {
                    String item = cartBean.getOrder_id() + "comma" + cartBean.getOrder_title() + "comma" + cartBean.getOrder_pic()
                            + "comma" + cartBean.getOrder_num() + "comma" + cartBean.getOrder_price() + "comma" + cartBean.getOrder_type() + "peroid";
                    cartBuffer.append(item);
                }
            }
        }
        return cartBuffer.toString();
    }


    ShoppingCartAdapter cartAdapter;

    private void initCartAdapter(ArrayList<CartBean> cartList) {
        if (!isFirstCreatCartAdapter) {
            cartAdapter = new ShoppingCartAdapter(this, cartList);
            cartAdapter.setOnCartListClick(this);
            lv_cart_item.setAdapter(cartAdapter);
            isFirstCreatCartAdapter = true;
        } else {
            cartAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imb_cart_back:
                finish();
                break;
            case R.id.btn_pay_cart:
                requestCartPay();
                toogleShowCataloading(true);
                break;
            case R.id.tv_anore_address:

                break;
            case R.id.ly_change_receiveTime:
                initTimerTip();
                break;
        }
    }

    private void initTimerTip() {
        selectHour = 1;
        selectMinute = 1;
        timePickDialg = new TimePickDialog(this, ScreenTools.getWindowsWidth(this));
        timePickDialg.setOnTimePickOnclick(new TimePickDialog.OnHourPickListener() {
            @Override
            public void onHourSeletTime(int selectHours) {
                selectHour = selectHours;
            }

            @Override
            public void onMinuteSelectTime(int selectMinutes) {
                selectMinute = selectMinutes;
            }

            @Override
            public void comfirm() {
                String hour, mimute = null;
                if (selectHour < 10) {
                    hour = "0" + selectHour;
                } else {
                    hour = "" + selectHour;
                }
                if (selectMinute < 10) {
                    mimute = "0" + selectMinute;
                } else {
                    mimute = "" + selectMinute;
                }
                tempTime = hour + ":" + mimute + ":" + "00";
                tv_receiver_time.setText(hour + ":" + mimute);
                timePickDialg.dismiss();
            }

            @Override
            public void cancel() {
                timePickDialg.dismiss();
            }
        });
        timePickDialg.show();

    }

    /**
     * 更新购物车数量
     *
     * @param position
     * @param count
     */
    private void updateCartCount(int position, int count) {
        Map<String, Object> map = new HashMap();
        map.put("sc_id", cartBeanList.get(position).getSc_id());
        map.put("order_num", count);
        map.put("order_price", cartBeanList.get(position).getOrder_price());
        mvpPresenter.changeCartCount(map);
    }

    private void dealCartItme(int position) {
        Map<String, Object> map = new HashMap<>();
        map.put("sc_id", cartBeanList.get(position).getSc_id());
        mvpPresenter.dealCart(map);
    }


    @Override

    public void getEatfoodListSuc(EatFoodReturn mEatFoodReturn) {

    }

    @Override
    public void getEatfoodListFail() {

    }

    @Override
    public void getCartListSuc(ArrayList<CartBean> cartBeenLists) {
        cartBeanList.addAll(cartBeenLists);
        initCartAdapter(cartBeanList);
    }

    @Override
    public void getCartListFail() {

    }

    @Override
    public void deleteCartSuc(int position) {
        for (int i = 0; i < cartBeanList.size(); i++) {
            if (i == position) {
                cartBeanList.remove(cartBeanList.get(i));
                cartAdapter.notifyDataSetChanged();
                break;
            }
        }
    }

    @Override
    public void payCartSuc() {
        toogleShowCataloading(false);
    }

    @Override
    public void payCartFail() {
        toogleShowCataloading(false);
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
    public void onDecreaseCart(int position, int count) {
        updateCartCount(position, count);
    }

    @Override
    public void onAriseCart(int position, int count) {
        updateCartCount(position, count);
    }

    @Override
    public void onDetele(int position) {
        dealCartItme(position);
    }

    private void toogleCheckAll(boolean isCheck) {
        if (isCheck) {
            for (int i = 0; i < cartBeanList.size(); i++) {
                cartBeanList.get(i).setCheck(true);
            }
        } else {
            for (int i = 0; i < cartBeanList.size(); i++) {
                cartBeanList.get(i).setCheck(false);
            }
        }
    }

    @Override
    public void onCheckBoxItemClick(int position) {
        for (int i = 0; i < cartBeanList.size(); i++) {
            if (cartBeanList.get(i).isCheck()) {
                cartAllPrice = cartAllPrice + cartBeanList.get(i).getOrder_price();
            }
        }
    }
}
