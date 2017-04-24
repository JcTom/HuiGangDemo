package com.suctan.huigangdemo.bean.topic;

import java.util.ArrayList;

/**
 * Created by 黄淑翰 on 2017/4/21.
 */
public class TopicCommentReturn {
    private int status;
    private String msg;
    private ArrayList<TopicCommentBean> topicCommentList;

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

    public ArrayList<TopicCommentBean> getTopicCommentList() {
        return topicCommentList;
    }

    public void setTopicCommentList(ArrayList<TopicCommentBean> topicCommentList) {
        this.topicCommentList = topicCommentList;
    }
}
