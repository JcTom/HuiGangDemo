package com.suctan.huigangdemo.bean.user;

import com.suctan.huigangdemo.bean.index.EatFoodBean;

import java.util.ArrayList;

/**
 * Created by Tom on 2017/4/24.
 */

public class MykitchenReturn {

    private int status;
    private String msg;

    private ArrayList<MykitchenBean> MykitchenBeanList;

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

    public ArrayList<MykitchenBean> getMykitchenBeanList() {
        return MykitchenBeanList;
    }

    public void setMykitchenBeanList(ArrayList<MykitchenBean> mykitchenBeanList) {
        MykitchenBeanList = mykitchenBeanList;
    }
}
