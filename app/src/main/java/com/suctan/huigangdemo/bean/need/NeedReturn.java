package com.suctan.huigangdemo.bean.need;

import com.suctan.huigangdemo.bean.topic.TopicBean;

import java.util.ArrayList;

/**
 * Created by haily on 2017/4/27.
 */

public class NeedReturn {
    private int status;
    private String msg;
    private ArrayList<NeedBean> tipBeanList;

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

    public ArrayList<NeedBean> getTipBeanList() {
        return tipBeanList;
    }

    public void setTipBeanList(ArrayList<NeedBean> tipBeanList) {
        this.tipBeanList = tipBeanList;
    }
}
