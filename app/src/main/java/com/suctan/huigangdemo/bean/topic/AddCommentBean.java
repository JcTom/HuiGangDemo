package com.suctan.huigangdemo.bean.topic;

/**
 * Created by 黄淑翰 on 2017/4/21.
 */
public class AddCommentBean {
    private int status;
    private String msg;
    private AddCommentReturn topic;

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

    public AddCommentReturn getTopic() {
        return topic;
    }

    public void setTopic(AddCommentReturn topic) {
        this.topic = topic;
    }
}
