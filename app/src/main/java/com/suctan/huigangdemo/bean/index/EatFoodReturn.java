package com.suctan.huigangdemo.bean.index;

import java.util.ArrayList;

/**
 * Created by 黄淑翰 on 2017/4/20.
 */
public class EatFoodReturn {
    private int status;
    private String msg;

    private ArrayList<EatFoodBean> eatFoodBeanList;

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

    public ArrayList<EatFoodBean> getEatFoodBeanList() {
        return eatFoodBeanList;
    }

    public void setEatFoodBeanList(ArrayList<EatFoodBean> eatFoodBeanList) {
        this.eatFoodBeanList = eatFoodBeanList;
    }
}
