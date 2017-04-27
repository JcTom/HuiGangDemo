package com.suctan.huigangdemo.bean.user;

import android.widget.ImageView;

/**
 * Created by Tom on 2017/4/14.
 */

public class MykitchenBean {
    /*private String ImageUrl;*/

    private int order_id;
    private String order_title;
    private int order_num;
    private double order_price;
    private String order_pic;
    private String food_description;
    private String order_type;
    private String order_makefood_time;
    private String order_pub_time;
    private String user_token;
    private int order_status;
    private String makefood_res;
    private String makefood_float;
    private String makefood_note;

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

    public int getOrder_num() {
        return order_num;
    }

    public void setOrder_num(int order_num) {
        this.order_num = order_num;
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

    public String getOrder_type() {
        return order_type;
    }

    public void setOrder_type(String order_type) {
        this.order_type = order_type;
    }

    public String getOrder_makefood_time() {
        return order_makefood_time;
    }

    public void setOrder_makefood_time(String order_makefood_time) {
        this.order_makefood_time = order_makefood_time;
    }

    public String getOrder_pub_time() {
        return order_pub_time;
    }

    public void setOrder_pub_time(String order_pub_time) {
        this.order_pub_time = order_pub_time;
    }

    public String getUser_token() {
        return user_token;
    }

    public void setUser_token(String user_token) {
        this.user_token = user_token;
    }

    public int getOrder_status() {
        return order_status;
    }

    public void setOrder_status(int order_status) {
        this.order_status = order_status;
    }

    public String getMakefood_res() {
        return makefood_res;
    }

    public void setMakefood_res(String makefood_res) {
        this.makefood_res = makefood_res;
    }

    public String getMakefood_float() {
        return makefood_float;
    }

    public void setMakefood_float(String makefood_float) {
        this.makefood_float = makefood_float;
    }

    public String getMakefood_note() {
        return makefood_note;
    }

    public void setMakefood_note(String makefood_note) {
        this.makefood_note = makefood_note;
    }
}
