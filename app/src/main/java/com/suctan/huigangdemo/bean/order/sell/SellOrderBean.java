package com.suctan.huigangdemo.bean.order.sell;

import java.io.Serializable;

/**
 * Created by 黄淑翰 on 2017/4/27.
 */
public class SellOrderBean implements Serializable{
    private int order_status;
    private int order_id;
    private String order_title;
    private String eatstrarr;
    private double order_price;
    private int order_type;
    private String order_note;
    private String order_res_time;
    private String order_expect_time;
    private String order_pub_time;
    private String user_name;
    private String user_phone;
    private String uer_phone;
    private String user_address;
    private String pic;
    private double all_price;
    private int num;
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public double getAll_price() {
        return all_price;
    }

    public void setAll_price(double all_price) {
        this.all_price = all_price;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getOrder_status() {
        return order_status;
    }

    public void setOrder_status(int order_status) {
        this.order_status = order_status;
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

    public String getEatstrarr() {
        return eatstrarr;
    }

    public void setEatstrarr(String eatstrarr) {
        this.eatstrarr = eatstrarr;
    }

    public double getOrder_price() {
        return order_price;
    }

    public void setOrder_price(double order_price) {
        this.order_price = order_price;
    }

    public int getOrder_type() {
        return order_type;
    }

    public void setOrder_type(int order_type) {
        this.order_type = order_type;
    }

    public String getOrder_note() {
        return order_note;
    }

    public void setOrder_note(String order_note) {
        this.order_note = order_note;
    }

    public String getOrder_res_time() {
        return order_res_time;
    }

    public void setOrder_res_time(String order_res_time) {
        this.order_res_time = order_res_time;
    }

    public String getOrder_expect_time() {
        return order_expect_time;
    }

    public void setOrder_expect_time(String order_expect_time) {
        this.order_expect_time = order_expect_time;
    }

    public String getOrder_pub_time() {
        return order_pub_time;
    }

    public void setOrder_pub_time(String order_pub_time) {
        this.order_pub_time = order_pub_time;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_phone() {
        return user_phone;
    }

    public void setUser_phone(String user_phone) {
        this.user_phone = user_phone;
    }

    public String getUer_phone() {
        return uer_phone;
    }

    public void setUer_phone(String uer_phone) {
        this.uer_phone = uer_phone;
    }

    public String getUser_address() {
        return user_address;
    }

    public void setUser_address(String user_address) {
        this.user_address = user_address;
    }
}
