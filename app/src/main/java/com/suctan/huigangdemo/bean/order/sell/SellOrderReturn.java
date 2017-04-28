package com.suctan.huigangdemo.bean.order.sell;

import java.util.ArrayList;

/**
 * Created by 黄淑翰 on 2017/4/27.
 */
public class SellOrderReturn {
    private int status;
    private String msg;
    private ArrayList<SellOrderBean> sellOrderBeenList;

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

    public ArrayList<SellOrderBean> getSellOrderBeenList() {
        return sellOrderBeenList;
    }

    public void setSellOrderBeenList(ArrayList<SellOrderBean> sellOrderBeenList) {
        this.sellOrderBeenList = sellOrderBeenList;
    }
}
