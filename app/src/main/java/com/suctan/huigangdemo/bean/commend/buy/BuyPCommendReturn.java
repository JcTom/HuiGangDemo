package com.suctan.huigangdemo.bean.commend.buy;

import java.util.ArrayList;

/**
 * Created by 黄淑翰 on 2017/4/28.
 */
public class BuyPCommendReturn {
    int status;
    String msg;
    BuyPCommendCusBean cusComment;
    BuyPCommendCookBean cookComment;


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

    public BuyPCommendCusBean getCusComment() {
        return cusComment;
    }

    public void setCusComment(BuyPCommendCusBean cusComment) {
        this.cusComment = cusComment;
    }

    public BuyPCommendCookBean getCookComment() {
        return cookComment;
    }

    public void setCookComment(BuyPCommendCookBean cookComment) {
        this.cookComment = cookComment;
    }
}
