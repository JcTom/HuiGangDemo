package com.suctan.huigangdemo.activity.recommend;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.androidbase.BaseApplication;
import com.example.androidbase.mvp.MvpActivity;
import com.jaeger.library.StatusBarUtil;
import com.suctan.huigangdemo.R;
import com.suctan.huigangdemo.acache.TokenManager;
import com.suctan.huigangdemo.activity.BaseActivity;
import com.suctan.huigangdemo.activity.MainActivity;
import com.suctan.huigangdemo.activity.cart.ShoppingCart;
import com.suctan.huigangdemo.activity.eatfood.EatFoodDetail;
import com.suctan.huigangdemo.adapter.RecommendindexAdapter;
import com.suctan.huigangdemo.bean.cart.CartBean;
import com.suctan.huigangdemo.bean.index.EatFoodBean;
import com.suctan.huigangdemo.bean.index.EatFoodReturn;
import com.suctan.huigangdemo.bean.user.CompanyInfoBean;
import com.suctan.huigangdemo.bean.user.Recommend_indexBean;
import com.suctan.huigangdemo.mvp.login.cart.CartPresenter;
import com.suctan.huigangdemo.mvp.login.cart.CartView;
import com.suctan.huigangdemo.mvp.login.index.home.HomePresenter;
import com.suctan.huigangdemo.mvp.login.index.home.HomeView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by Tom on 2017/4/10.
 */

public class RecommendActivity extends MvpActivity<CartPresenter> implements View.OnClickListener, RecommendindexAdapter.Recommend, CartView {
    private static final String ACTIVITY_TAG = "RecommendActivity";
    private ImageView login_recommend_back;
    private TextView login_recommend_title;
    private ImageView recommend_ItemImage;
    GridView recommend_gridView;
    private ImageView shopping_cart;
    private boolean initFirstCreateGrid;

    private ArrayList<EatFoodBean> eatFoodList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recommend);
        StatusBarUtil.setColor(this, getResources().getColor(R.color.colorPrimary), 0);
        initView();
        getRecommendFoodList();
    }

    @Override
    protected CartPresenter createPresenter() {
        return new CartPresenter(this);
    }

    private void getRecommendFoodList() {
        mvpPresenter.getEatFoodList();

    }

    //跳到想吃的详情页
    private void goEatDetail(int position) {
        Intent intent = new Intent(this, EatFoodDetail.class);
        intent.putExtra("nowEatFood", eatFoodList.get(position));
        startActivity(intent);
    }

    private void initView() {
        /*recommend_ItemImage = (ImageView) findViewById(R.id.recommend_today_ItemImage);*/
        recommend_gridView = (GridView) findViewById(R.id.recommend_gridview);
        login_recommend_back = (ImageView) findViewById(R.id.login_recommend_back);

        login_recommend_title = (TextView) findViewById(R.id.login_recommend_title);


        login_recommend_back.setOnClickListener(this);
        shopping_cart = (ImageView) findViewById(R.id.shopping_cart);
        shopping_cart.setOnClickListener(this);
        login_recommend_title.setText("今日推荐");
        recommend_gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                goEatDetail(position);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_recommend_back:
                finish();
                break;
            case R.id.shopping_cart:
                Intent intent = new Intent(this, ShoppingCart.class);
                startActivity(intent);
                break;

        }
    }


    RecommendindexAdapter eatFoodAdapter;

    @Override
    public void getEatfoodListSuc(EatFoodReturn mEatFoodReturn) {
        this.eatFoodList.addAll(mEatFoodReturn.getEatFoodBeanList());
        if (!initFirstCreateGrid) {
            initEataFoodAdapter();
            initFirstCreateGrid = true;
        } else {
            eatFoodAdapter.notifyDataSetChanged();
        }
    }

    private void initEataFoodAdapter() {
        eatFoodAdapter = new RecommendindexAdapter(this, eatFoodList);
        recommend_gridView.setAdapter(eatFoodAdapter);
        eatFoodAdapter.setRecomendOnclickLisner(this);
    }

    @Override
    public void getEatfoodListFail() {

    }

    @Override
    public void getCartListSuc(ArrayList<CartBean> cartBeenList) {

    }

    @Override
    public void getCartListFail() {

    }

    @Override
    public void deleteCartSuc(int position) {

    }

    @Override
    public void payCartSuc() {

    }

    @Override
    public void payCartFail() {

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

    private void CartDeal(int index, int count) {
        Map<String, Object> map = new HashMap<>();
        map.put("user_token", TokenManager.getToken());
        map.put("order_id", eatFoodList.get(index).getOrder_id());
        map.put("order_title", eatFoodList.get(index).getOrder_title());
        map.put("order_pic", eatFoodList.get(index).getOrder_pic());
        map.put("order_price", eatFoodList.get(index).getOrder_price());
        map.put("order_type", eatFoodList.get(index).getOrder_type());
        map.put("order_num", count);
        mvpPresenter.dealCart(map);
    }

    @Override
    public void addCartItemListener(int addCartPosition, int count) {
        CartDeal(addCartPosition, count);

    }

    @Override
    public void deleteCartItemListener(int deleteCartPosition, int count) {
        CartDeal(deleteCartPosition, count);
    }
}
