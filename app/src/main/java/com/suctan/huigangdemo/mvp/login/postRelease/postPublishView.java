package com.suctan.huigangdemo.mvp.login.postRelease;

import com.example.androidbase.mvp.BaseMvpView;
import com.suctan.huigangdemo.bean.topic.AddCommentBean;
import com.suctan.huigangdemo.bean.topic.AddCommentReturn;
import com.suctan.huigangdemo.bean.topic.TopicBean;
import com.suctan.huigangdemo.bean.topic.TopicCommentBean;
import com.suctan.huigangdemo.bean.user.CourseBean;

import java.util.ArrayList;

/**
 * Created by B-305 on 2017/4/19.
 */

public interface PostPublishView extends BaseMvpView {
    void getTopicListSrc(ArrayList<TopicBean> topicBeenList);

    void getTopicListFail();

    void postPublishCommentSuc(AddCommentReturn addCommentBean);

    void postPublishCommentFail(String msg);

    void getCommentListSuc(ArrayList<TopicCommentBean>topicCommentBeen);

    void getComemtnListFail();

}
