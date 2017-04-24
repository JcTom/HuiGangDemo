package com.suctan.huigangdemo.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.androidbase.mvp.MvpFragment;
import com.suctan.huigangdemo.R;
import com.suctan.huigangdemo.acache.TokenManager;

import com.suctan.huigangdemo.activity.circle.CirclePostDetails;
import com.suctan.huigangdemo.adapter.topic.TopicRecycleAdapter;
import com.suctan.huigangdemo.bean.topic.AddCommentReturn;
import com.suctan.huigangdemo.bean.topic.TopicBean;
import com.suctan.huigangdemo.bean.topic.TopicCommentBean;

import com.suctan.huigangdemo.mvp.login.postRelease.PostPublishPresenter;
import com.suctan.huigangdemo.mvp.login.postRelease.PostPublishView;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.ButterKnife;

public class FragmentFind extends MvpFragment<PostPublishPresenter> implements View.OnClickListener, PostPublishView, SwipeRefreshLayout.OnRefreshListener, TopicRecycleAdapter.OnTopicListenter {

    private View viewFind;
    private SwipeRefreshLayout swipe_circle;
    private RecyclerView mRecyclerView;
    private List<String> mDatas;
    private ArrayList<TopicBean> topicBeanList = new ArrayList<>();
    private boolean isFirstCreateRecycle;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (viewFind == null) {
            viewFind = inflater.inflate(R.layout.fragment_circle, container, false);
        }
        ViewGroup parent = (ViewGroup) viewFind.getParent();
        if (parent != null) {
            parent.removeView(viewFind);
        }
        ButterKnife.bind(this, viewFind);
        return viewFind;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initViews();
        getTopicData();

    }

    private void getTopicData() {
        Map<String, Object> map = new HashMap<>();
        map.put("user_token", TokenManager.getToken());
        mvpPresenter.getTopicList(map);
    }

    @Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                swipe_circle.setRefreshing(false);
            }
        }, 3000);
    }


    private void initRefreshView() {
        swipe_circle.setColorSchemeColors(
                getResources().getColor(R.color.gplus_color_1),
                getResources().getColor(R.color.gplus_color_2),
                getResources().getColor(R.color.gplus_color_3),
                getResources().getColor(R.color.gplus_color_4));
        swipe_circle.setOnRefreshListener(this);
    }

    private void initViews() {
        mRecyclerView = (RecyclerView) getView().findViewById(R.id.circleView_topic);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        swipe_circle = (SwipeRefreshLayout) viewFind.findViewById(R.id.swipe_circle);
        initRefreshView();

    }


    @Override
    public void onClick(View v) {

    }

    @Override
    public void getDataFail(String msg) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    protected PostPublishPresenter createPresenter() {
        return new PostPublishPresenter(this);
    }

    private TopicRecycleAdapter topListAdatper;

    @Override
    public void getTopicListSrc(ArrayList<TopicBean> topicBeenList) {

        this.topicBeanList.addAll(topicBeenList);
        if (!isFirstCreateRecycle) {
            isFirstCreateRecycle = true;
            InitRecycleViewAdapter(this.topicBeanList);
        } else {
            topListAdatper.notifyDataSetChanged();
        }
    }

    private void InitRecycleViewAdapter(ArrayList<TopicBean> topicBeenList) {
        topListAdatper = new TopicRecycleAdapter(getActivity(), topicBeenList);
        topListAdatper.setOnClickTopicListner(this);
        mRecyclerView.setAdapter(topListAdatper);
    }

    @Override
    public void getTopicListFail() {

    }

    @Override
    public void postPublishCommentSuc(AddCommentReturn addCommentBean) {

    }


    @Override
    public void postPublishCommentFail(String msg) {

    }

    @Override
    public void getCommentListSuc(ArrayList<TopicCommentBean> topicCommentBeen) {

    }

    @Override
    public void getComemtnListFail() {

    }

    @Override
    public void onClickTopicItem(int position) {
        if (topicBeanList != null) {
            Intent intent = new Intent(getActivity(), CirclePostDetails.class);
            intent.putExtra("nowTopic", topicBeanList.get(position));
            startActivity(intent);
        }
    }

    @Override
    protected void lazyLoad() {

    }
}
