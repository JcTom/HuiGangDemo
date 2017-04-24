package com.suctan.huigangdemo.bean.index;

import java.io.Serializable;

/**
 * Created by 黄淑翰 on 2017/4/20.
 */
public class EatFoodBean implements Serializable {
    private String order_pic;
    private int order_id;
    private String order_title;
    private String food_description;//菜的描述
    private double order_price;//
    private int num;
    private String user_name;


    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getOrder_pic() {
        return order_pic;
    }

    public void setOrder_pic(String order_pic) {
        this.order_pic = order_pic;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public String getOrder_title() {
        return order_title;
    }

    public void setOrder_title(String order_title) {
        this.order_title = order_title;
    }

    public String getFood_description() {
        return food_description;
    }

    public void setFood_description(String food_description) {
        this.food_description = food_description;
    }

    public double getOrder_price() {
        return order_price;
    }

    public void setOrder_price(double order_price) {
        this.order_price = order_price;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
}
