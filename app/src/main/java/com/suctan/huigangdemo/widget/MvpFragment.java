package com.suctan.huigangdemo.widget;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.androidbase.mvp.BasePresenter;
import com.example.androidbase.utils.NetConnectUtils;
import com.suctan.huigangdemo.R;

/**
 * Create by kushanmao on 2017/3/23 0023 16:39
 */

public abstract class MvpFragment<presenter extends BasePresenter> extends
        com.example.androidbase.mvp.MvpFragment<presenter> implements
        SwipeRefreshLayout.OnRefreshListener, LoadMoreListView.OnLoadMoreListener,
        AdapterView.OnItemClickListener {

    protected View view;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private LoadMoreListView mListView;
    private TextView emptyView;
    protected boolean isRefreshing;
    protected boolean isLoading;
    protected boolean isLoadAll;
    protected boolean firstCreate = true;
    protected boolean isFirstVisible = true;
    protected boolean isDefaultLayout = true;
    protected boolean isPrepared;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(getContentLayoutId(), container, false);
        }
        ViewGroup parent = (ViewGroup) view.getParent();
        if (parent != null) {
            parent.removeView(view);
        }
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (isDefaultLayout) {
            initEvent();
        }
        initViewsAddEvents();
        setSwipeRefreshLayoutColors();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initPrepare();
    }

    /**
     * 第一次加载数据
     */
    private synchronized void initPrepare() {
        if (isPrepared) {
            if (mSwipeRefreshLayout != null) {
                mSwipeRefreshLayout.setRefreshing(true);
            }
            if (NetConnectUtils.isNetConnected(getActivity())) {
                dataLazyLoad();
            } else {
                onfinishRefreshOrLoad(true);
                Toast.makeText(getActivity(), "网络飞走了", Toast.LENGTH_SHORT).show();
            }

        } else {
            isPrepared = true;
        }
    }

    /**
     * 最多可以设置4中颜色
     */
    private void setSwipeRefreshLayoutColors() {

        if (mSwipeRefreshLayout == null) {
            return;
        }

        mSwipeRefreshLayout.setColorSchemeColors(
                getResources().getColor(R.color.gplus_color_1),
                getResources().getColor(R.color.gplus_color_2),
                getResources().getColor(R.color.gplus_color_3),
                getResources().getColor(R.color.gplus_color_4));
    }

    private void initEvent() {
        if (view != null) {
            mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.common_swipe_fresh_layout);
            mSwipeRefreshLayout.setOnRefreshListener(this);
            mListView = (LoadMoreListView) view.findViewById(R.id.common_swipe_fresh_listview);
            emptyView = (TextView) view.findViewById(R.id.txt_empty_tip);
            mListView.setEmptyView(emptyView);
            mListView.setOnLoadMoreListener(this);
            mListView.setOnItemClickListener(this);
        }
    }


    //设置listView的上下拉监听事件
    protected void initLRListener() {
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mListView.setEmptyView(emptyView);
        mListView.setOnLoadMoreListener(this);
        mListView.setOnItemClickListener(this);
    }

    /**
     * 返回 mSwipeRefreshLayout
     *
     * @return mSwipeRefreshLayout
     */
    protected SwipeRefreshLayout getSwipeRefreshLayout() {
        if (mSwipeRefreshLayout != null) {
            return mSwipeRefreshLayout;
        }
        return null;
    }

    /**
     * 若不是在基类里创建该布局则是直接将替换赋值给基类
     *
     * @return emptyView
     */
    protected void setSwipeLayout(SwipeRefreshLayout mSwipe) {
        if (mSwipe == null) {
            return;
        }
        this.mSwipeRefreshLayout = mSwipe;
    }


    /**
     * 返回listView 实例
     *
     * @return mListView
     */
    protected LoadMoreListView getListView() {
        if (mListView != null) {
            return mListView;
        }
        return null;
    }

    /**
     * 若不是在基类里创建该布局则是直接将替换赋值给基类
     *
     * @return emptyView
     */
    protected void setLoadMoreListView(LoadMoreListView mListView) {
        if (mListView == null) {
            return;
        }
        this.mListView = mListView;
    }


    /**
     * 返回 无数据时提示文本 控件
     *
     * @return emptyView
     */
    protected TextView getEmptyView() {
        if (emptyView != null) {
            return emptyView;
        }
        return null;
    }

    /**
     * 若不是在基类里创建该布局则是直接将替换赋值给基类
     *
     * @return emptyView
     */
    protected void setEmptyView(TextView emptyView) {
        if (emptyView == null) {
            return;
        }
        this.emptyView = emptyView;
    }

    @Override
    public void onRefresh() {
        if (isLoading) {//正在加载，则不去刷新
            isRefreshing = false;
            if (mSwipeRefreshLayout != null) {
                mSwipeRefreshLayout.setRefreshing(false);
            }
        } else if (!isRefreshing) {
            if (NetConnectUtils.isNetConnected(getActivity())) {
                isRefreshing = true;
                isLoading = false;
                mListView.setCanLoadMore(true);
                onDataRefresh();
            } else {
                onfinishRefreshOrLoad(true);
                Toast.makeText(getActivity(), "网络飞走了", Toast.LENGTH_SHORT).show();
            }
        }
    }


    @Override
    public void onLoadMore() {
        if (isLoadAll) {
            if (mListView != null) {
                mListView.onLoadMoreComplete();
                mListView.setCanLoadMore(false);
            }
        } else {
            if (isRefreshing) {//正在刷新，则不去加载
                isLoading = false;
                if (mListView != null) {
                    mListView.onLoadMoreComplete();
                }
            } else if (!isLoading) {
                if (NetConnectUtils.isNetConnected(getActivity())) {
                    isLoading = true;
                    isRefreshing = false;
                    onDataLoadMore();
                } else {
                    onfinishRefreshOrLoad(true);
                    Toast.makeText(getActivity(), "网络飞走了", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }


    @Override
    protected void lazyLoad() {
        if (isFirstVisible) {
            isFirstVisible = false;
            initPrepare();
        }
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (mListView != null) {
            onMItemClick(parent, view, position, id);
        }
    }

    /**
     * 刷新，加载--完成
     */
    protected void onfinishRefreshOrLoad() {
        isRefreshing = false;
        isLoading = false;
        if (mSwipeRefreshLayout != null) {
            mSwipeRefreshLayout.setRefreshing(false);
        }
        if (mListView != null) {
            mListView.onLoadMoreComplete();
        }
    }

    /**
     * 刷新，加载--完成
     */
    protected void onfinishRefreshOrLoad(boolean hasData) {
        isRefreshing = false;
        isLoading = false;
        if (emptyView != null) {
            if (hasData) {
                emptyView.setText(getString(R.string.hardLoeading));
            } else {
                emptyView.setText(getResources().getString(R.string.txt_empty_tip));
            }
        }

        if (mSwipeRefreshLayout != null) {
            mSwipeRefreshLayout.setRefreshing(false);
        }
        if (mListView != null) {
            mListView.onLoadMoreComplete();
        }
    }

    /**
     * 刷新数据
     */
    protected abstract void onDataRefresh();

    /**
     * 加载数据
     */
    protected abstract void onDataLoadMore();


    /**
     * 懒加载，只会执行一遍
     */
    protected abstract void dataLazyLoad();

    /**
     * 初始化控件
     */
    protected abstract void initViewsAddEvents();

    /**
     * 布局id
     *
     * @return
     */
    protected abstract int getContentLayoutId();


    /**
     * listView 的 item 点击事件
     *
     * @param parent
     * @param view
     * @param position
     * @param id
     */
    protected abstract void onMItemClick(AdapterView<?> parent, View view, int position, long id);


}

