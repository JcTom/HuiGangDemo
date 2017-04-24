package com.suctan.huigangdemo.bean.user;

/**
 * Created by 黄淑翰 on 2017/4/15.
 */
public class ModifyReturn {
    private String status;//返回状态
    private String msg;//返回信息
    private String money;//返回信息

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
