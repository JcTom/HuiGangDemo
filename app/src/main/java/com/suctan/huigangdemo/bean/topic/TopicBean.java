package com.suctan.huigangdemo.bean.topic;

import java.io.Serializable;

/**
 * Created by 黄淑翰 on 2017/4/21.
 */
public class TopicBean implements Serializable {
    private int topic_id;
    private String user_icon;
    private String user_name;

    private String topic_title;
    private String topic_content;
    private String topic_picture;
    private String user_token;
    private int comment_num;
    private String pub_topic_time;

    public int getTopic_id() {
        return topic_id;
    }

    public void setTopic_id(int topic_id) {
        this.topic_id = topic_id;
    }

    public String getTopic_title() {
        return topic_title;
    }

    public void setTopic_title(String topic_title) {
        this.topic_title = topic_title;
    }

    public String getTopic_content() {
        return topic_content;
    }

    public void setTopic_content(String topic_content) {
        this.topic_content = topic_content;
    }

    public String getTopic_picture() {
        return topic_picture;
    }

    public void setTopic_picture(String topic_picture) {
        this.topic_picture = topic_picture;
    }

    public String getUser_token() {
        return user_token;
    }

    public void setUser_token(String user_token) {
        this.user_token = user_token;
    }

    public int getComment_num() {
        return comment_num;
    }

    public void setComment_num(int comment_num) {
        this.comment_num = comment_num;
    }

    public String getUser_icon() {
        return user_icon;
    }

    public void setUser_icon(String user_icon) {
        this.user_icon = user_icon;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getPub_topic_time() {
        return pub_topic_time;
    }

    public void setPub_topic_time(String pub_topic_time) {
        this.pub_topic_time = pub_topic_time;
    }
}
