package com.suctan.huigangdemo.bean.wanteat;

import com.suctan.huigangdemo.bean.index.EatFoodBean;

import java.util.ArrayList;

/**
 * Created by 黄淑翰 on 2017/4/24.
 */
public class WantEatOtderReturn {
    private int status;
    private String msg;
    private ArrayList<EatFoodBean> eatFoodBeenList;
    private ArrayList<WantEatOtherComment> wantEatOrderCommentList;

    public ArrayList<EatFoodBean> getEatFoodBeenList() {
        return eatFoodBeenList;
    }

    public void setEatFoodBeenList(ArrayList<EatFoodBean> eatFoodBeenList) {
        this.eatFoodBeenList = eatFoodBeenList;
    }

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


    public ArrayList<WantEatOtherComment> getWantEatOrderCommentList() {
        return wantEatOrderCommentList;
    }

    public void setWantEatOrderCommentList(ArrayList<WantEatOtherComment> wantEatOrderCommentList) {
        this.wantEatOrderCommentList = wantEatOrderCommentList;
    }
}
