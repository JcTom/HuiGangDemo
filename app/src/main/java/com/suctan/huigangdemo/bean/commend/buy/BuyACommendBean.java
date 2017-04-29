package com.suctan.huigangdemo.bean.commend.buy;

/**
 * Created by 黄淑翰 on 2017/4/28.
 */
public class BuyACommendBean {
    int id;
    int order_id;
    String comment;
    int comment_describe;
    int comment_service;
    int comment_taste;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getComment_describe() {
        return comment_describe;
    }

    public void setComment_describe(int comment_describe) {
        this.comment_describe = comment_describe;
    }

    public int getComment_service() {
        return comment_service;
    }

    public void setComment_service(int comment_service) {
        this.comment_service = comment_service;
    }

    public int getComment_taste() {
        return comment_taste;
    }

    public void setComment_taste(int comment_taste) {
        this.comment_taste = comment_taste;
    }
}
