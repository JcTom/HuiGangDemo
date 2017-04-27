package com.suctan.huigangdemo.bean.cart;

import java.util.ArrayList;

/**
 * Created by 黄淑翰 on 2017/4/25.
 */
public class CartReturn {
    private int status;
    private String msg;
    private ArrayList<CartBean> cartBeenList;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public ArrayList<CartBean> getCartBeenList() {
        return cartBeenList;
    }

    public void setCartBeenList(ArrayList<CartBean> cartBeenList) {
        this.cartBeenList = cartBeenList;
    }
}
