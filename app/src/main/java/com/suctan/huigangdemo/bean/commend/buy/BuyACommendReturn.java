package com.suctan.huigangdemo.bean.commend.buy;

/**
 * Created by 黄淑翰 on 2017/4/28.
 */
public class BuyACommendReturn {
    int status;
    String msg;
    BuyACommendBean buyACommendBean;

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

    public BuyACommendBean getBuyACommendBean() {
        return buyACommendBean;
    }

    public void setBuyACommendBean(BuyACommendBean buyACommendBean) {
        this.buyACommendBean = buyACommendBean;
    }
}
