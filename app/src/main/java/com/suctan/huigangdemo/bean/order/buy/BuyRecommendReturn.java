package com.suctan.huigangdemo.bean.order.buy;

import java.util.ArrayList;

/**
 * Created by 黄淑翰 on 2017/4/26.
 */
public class BuyRecommendReturn {
    private int status;
    private String msg;
    private ArrayList<BuyRecommendBean> recommendBeenList;

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

    public ArrayList<BuyRecommendBean> getRecommendBeenList() {
        return recommendBeenList;
    }

    public void setRecommendBeenList(ArrayList<BuyRecommendBean> recommendBeenList) {
        this.recommendBeenList = recommendBeenList;
    }
}
