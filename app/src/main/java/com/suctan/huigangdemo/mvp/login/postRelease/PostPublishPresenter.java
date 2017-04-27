package com.suctan.huigangdemo.mvp.login.postRelease;

import android.widget.Toast;

import com.example.androidbase.BaseApplication;
import com.example.androidbase.rxjava.ApiCallback;
import com.example.androidbase.rxjava.SubscriberCallBack;
import com.example.androidbase.utils.ToastTool;
import com.suctan.huigangdemo.bean.topic.AddCommentBean;
import com.suctan.huigangdemo.bean.topic.DellCommentBean;
import com.suctan.huigangdemo.bean.topic.TopicCommentReturn;
import com.suctan.huigangdemo.bean.topic.TopicReturn;
import com.suctan.huigangdemo.bean.user.ComomBeanReturn;
import com.suctan.huigangdemo.bean.user.ModifyReturn;
import com.suctan.huigangdemo.mvp.login.DemoBasePresenter;
import com.suctan.huigangdemo.util.JSONParstObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by B-305 on 2017/4/19.
 */

public class PostPublishPresenter extends DemoBasePresenter<PostPublishView> {
    public PostPublishPresenter(PostPublishView mvpView) {
        attachView(mvpView);
    }
    /**
     * 发布评论
     *
     * @param map
     */
    public void postPublishComment(Map map) {
        addSubscription(apiStores.postComment(map),
                new SubscriberCallBack<>(new ApiCallback<AddCommentBean>() {

                    @Override
                    public void onStart() {

                    }

                    @Override
                    public void onSuccess(AddCommentBean model) {
                      if(model!=null){
                          if (model.getStatus() == 1) {
                              if(model.getTopic()!=null ){
                                  mvpView.postPublishCommentSuc(model.getTopic());
                                  System.out.println("获得当前我添加评论的对象"+model);
                              }
                          } else {
                              mvpView.postPublishCommentFail(model.getMsg());
                          }
                      }
                    }

                    @Override
                    public void onFailed(String msg) {
                        mvpView.getDataFail(msg);
                    }

                    @Override
                    public void onCompleted() {
                        mvpView.hideLoading();
                    }
                })

        );
    }

    /*
    * 删除帖子
    * */

    public void getdeletetopicSSS(Map map) {
        addSubscription(apiStores.getdeletetopic(map),
                new SubscriberCallBack<>(new ApiCallback<String>() {

                    @Override
                    public void onStart() {

                    }

                    @Override
                    public void onSuccess(String model) {
                        System.out.println("删除我添加的评论"+model);
                        /*if (model.getStatus()==1){
                            mvpView.getdeleteTopicdel(model.getMsg());
                        }*/

                        /*if(model!=null){
                            if (model.getStatus()==1) {
                                if(model.getTopid()!=null ){
                                    mvpView.postPublishCommentSuc(model.getTopid());
                                    System.out.println("获得当前我添加评论的对象"+model);
                                }
                            } else {
                                mvpView.postPublishCommentFail(model.getMsg());
                            }
                        }*/

                    }

                    @Override
                    public void onFailed(String msg) {
                        mvpView.getDataFail(msg);
                    }

                    @Override
                    public void onCompleted() {
                        mvpView.hideLoading();
                    }
                })

        );
    }

    /**
     * 获取话题列表
     *
     * @param map
     */
    public void getTopicList(Map map) {
        addSubscription(apiStores.getPostTopicList(map),
                new SubscriberCallBack<>(new ApiCallback<String>() {

                    @Override
                    public void onStart() {

                    }

                    @Override
                    public void onSuccess(String model) {

                        TopicReturn topicReturn = JSONParstObject.getTopicStringObject(model);
                        if (topicReturn != null && topicReturn.getTipBeanList() != null) {
                            System.out.println("获取话题列表" + model);
                            mvpView.getTopicListSrc(topicReturn.getTipBeanList());
                        }
                    }

                    @Override
                    public void onFailed(String msg) {
                        mvpView.getDataFail(msg);
                    }

                    @Override
                    public void onCompleted() {
                        mvpView.hideLoading();
                    }
                })

        );
    }


    /**
     * 获取某个话题的评论列表
     *
     * @param map
     */
    public void getTopicCommentList(Map map) {
        addSubscription(apiStores.getTopicCommentListReturn(map),
                new SubscriberCallBack<>(new ApiCallback<String>() {

                    @Override
                    public void onStart() {

                    }

                    @Override
                    public void onSuccess(String model) {
                        System.out.println("获取某话题评论列表" + model);
                        if (model != null) {
                            TopicCommentReturn topicCommentReturn = JSONParstObject.getTopicCommentObject(model);
                            if (topicCommentReturn != null && topicCommentReturn.getTopicCommentList() != null) {
                                mvpView.getCommentListSuc(topicCommentReturn.getTopicCommentList());
                            } else {
                                mvpView.getComemtnListFail();
                            }
                        }
                    }

                    @Override
                    public void onFailed(String msg) {
                        mvpView.getDataFail(msg);
                    }

                    @Override
                    public void onCompleted() {
                        mvpView.hideLoading();
                    }
                })

        );
    }

}
