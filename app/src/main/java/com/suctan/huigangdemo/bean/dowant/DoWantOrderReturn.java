package com.suctan.huigangdemo.bean.dowant;

import java.util.ArrayList;

/**
 * Created by 黄淑翰 on 2017/4/23.
 */
public class DoWantOrderReturn {
    private int status;
    private String msg;
    private ArrayList<DoWantOrderBean> doWantOrderBeenList;


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

    public ArrayList<DoWantOrderBean> getDoWantOrderBeenList() {
        return doWantOrderBeenList;
    }

    public void setDoWantOrderBeenList(ArrayList<DoWantOrderBean> doWantOrderBeenList) {
        this.doWantOrderBeenList = doWantOrderBeenList;
    }
}
