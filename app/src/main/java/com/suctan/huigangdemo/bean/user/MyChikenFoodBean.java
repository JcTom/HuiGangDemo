package com.suctan.huigangdemo.bean.user;

import java.io.Serializable;

/**
 * Created by 黄淑翰 on 2017/4/16.
 */
public class MyChikenFoodBean implements Serializable {
    private String order_title;//菜名
    private double order_price;//价格
    private String order_pic;//菜图
    private String food_description;//订单描述
    private int order_type;//订单类型
    private String makeFood_res;//做法材料
    private String makeFood_float;//做法流程
    private String makeFood_note;//注意事项


    public String getOrder_title() {
        return order_title;
    }

    public void setOrder_title(String order_title) {
        this.order_title = order_title;
    }

    public double getOrder_price() {
        return order_price;
    }

    public void setOrder_price(double order_price) {
        this.order_price = order_price;
    }

    public String getOrder_pic() {
        return order_pic;
    }

    public void setOrder_pic(String order_pic) {
        this.order_pic = order_pic;
    }

    public String getFood_description() {
        return food_description;
    }

    public void setFood_description(String food_description) {
        this.food_description = food_description;
    }

    public int getOrder_type() {
        return order_type;
    }

    public void setOrder_type(int order_type) {
        this.order_type = order_type;
    }

    public String getMakeFood_res() {
        return makeFood_res;
    }

    public void setMakeFood_res(String makeFood_res) {
        this.makeFood_res = makeFood_res;
    }

    public String getMakeFood_float() {
        return makeFood_float;
    }

    public void setMakeFood_float(String makeFood_float) {
        this.makeFood_float = makeFood_float;
    }

    public String getMakeFood_note() {
        return makeFood_note;
    }

    public void setMakeFood_note(String makeFood_note) {
        this.makeFood_note = makeFood_note;
    }
}
