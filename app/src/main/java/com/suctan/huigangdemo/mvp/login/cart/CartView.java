package com.suctan.huigangdemo.mvp.login.cart;

import com.example.androidbase.mvp.BaseMvpView;
import com.suctan.huigangdemo.bean.cart.CartBean;
import com.suctan.huigangdemo.bean.index.EatFoodReturn;

import java.util.ArrayList;

/**
 * Created by Tom on 2017/4/11.
 */

public interface CartView extends BaseMvpView {
    void getEatfoodListSuc(EatFoodReturn mEatFoodReturn);

    void getEatfoodListFail();


    void getCartListSuc(ArrayList<CartBean> cartBeenList);

    void getCartListFail();


    void deleteCartSuc(int position);

    void payCartSuc();

    void payCartFail();
}
