package com.suctan.huigangdemo.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.TextView;

import com.example.androidbase.mvp.MvpFragment;
import com.jude.rollviewpager.RollPagerView;
import com.suctan.huigangdemo.activity.do_want.DoWant;
import com.suctan.huigangdemo.activity.recommend.RecommendActivity;
import com.suctan.huigangdemo.activity.want.Want;
import com.suctan.huigangdemo.adapter.IndexFoodGridAdapter;
import com.suctan.huigangdemo.bean.user.CompanyInfoBean;
import com.suctan.huigangdemo.bean.user.HomeBean;
import com.suctan.huigangdemo.mvp.login.index.home.HomePresenter;
import com.suctan.huigangdemo.mvp.login.index.home.HomeView;
import com.suctan.huigangdemo.R;
import com.suctan.huigangdemo.widget.SwpipeListViewOnScrollListener;


import java.util.ArrayList;


import butterknife.BindView;
import butterknife.ButterKnife;
import cn.bingoogolapple.badgeview.BGABadgeRadioButton;

public class FragmentIndex extends MvpFragment<HomePresenter> implements ViewPager.OnPageChangeListener, View.OnClickListener, HomeView, SwipeRefreshLayout.OnRefreshListener {
    View viewIndex;
    @BindView(R.id.tab_index)
    BGABadgeRadioButton tab_index;
    @BindView(R.id.tab_want)
    BGABadgeRadioButton tab_want;
    @BindView(R.id.tab_do)
    BGABadgeRadioButton tab_do;
    @BindView(R.id.rollViewpager)
    RollPagerView rollPagerView;
    @BindView(R.id.more_texts)
    TextView more_text;
    //    @BindView(R.id.pull_to_refresh)
//    PullToRefreshView pullToRefreshView;
    @BindView(R.id.common_swipe_fresh_layout)
    SwipeRefreshLayout SwithLayout;

    @BindView(R.id.gridview)
    GridView gridView;
    private boolean isFirstCreate;

    @Nullable
    public View onCreateView(LayoutInflater paramLayoutInflater, @Nullable ViewGroup paramViewGroup, @Nullable Bundle paramBundle) {
        if (this.viewIndex == null)
            this.viewIndex = paramLayoutInflater.inflate(R.layout.fragment_index, paramViewGroup, false);
        ViewGroup localViewGroup = (ViewGroup) this.viewIndex.getParent();
        if (localViewGroup != null)
            localViewGroup.removeView(this.viewIndex);
        return this.viewIndex;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (!isFirstCreate) {
            ButterKnife.bind(this, viewIndex);
            isFirstCreate = true;
            initGridData();
            rollPagerViewSet();
            Check();
            initRefreshView();
//            pullToRefreshView();
        }
    }

    private void initRefreshView() {
        SwithLayout.setOnRefreshListener(this);
        SwithLayout.setColorSchemeColors(
                getResources().getColor(R.color.gplus_color_1),
                getResources().getColor(R.color.gplus_color_2),
                getResources().getColor(R.color.gplus_color_3),
                getResources().getColor(R.color.gplus_color_4));
        SwpipeListViewOnScrollListener lisetner = new SwpipeListViewOnScrollListener(SwithLayout);
        gridView.setOnScrollListener(lisetner);

    }


    private void rollPagerViewSet() {
        rollPagerView.setPlayDelay(3000);//*播放间隔
        rollPagerView.setAnimationDurtion(500);//透明度
        ArrayList<CompanyInfoBean> companyList = new ArrayList<>();
        for (int i = 0; i <= 3; i++) {
            CompanyInfoBean companyInfoBean = new CompanyInfoBean("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1492091993911&di=804ff682760b588e56abfc96f9d43ecd&imgtype=0&src=http%3A%2F%2Fpic.58pic.com%2F58pic%2F13%2F82%2F51%2F77P58PICFKD_1024.jpg");
            companyList.add(companyInfoBean);
        }
        rollPagerView.setAdapter(new RollViewpagerAdapter(getActivity(), companyList));//配置适配器
    }


//    private void pullToRefreshView() {
//        pullToRefreshView.setOnRefreshListener(new PullToRefreshView.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//                pullToRefreshView.postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        pullToRefreshView.setRefreshing(false);
//                    }
//                }, 2000);
//            }
//        });
//    }

    //进入推荐菜品列表
    private void initGridData() {
        ArrayList<CompanyInfoBean> companys = new ArrayList<>();
        for (int i = 0; i <= 3; i++) {
            CompanyInfoBean companyInfoBean = new CompanyInfoBean("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1492091993911&di=804ff682760b588e56abfc96f9d43ecd&imgtype=0&src=http%3A%2F%2Fpic.58pic.com%2F58pic%2F13%2F82%2F51%2F77P58PICFKD_1024.jpg");
            companys.add(companyInfoBean);
        }
        IndexFoodGridAdapter gridAdapter = new IndexFoodGridAdapter(getActivity(), companys);
        gridView.setAdapter(gridAdapter);

    }


    private void Check() {
        tab_index.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goRecommend = new Intent(getActivity(), RecommendActivity.class);
                startActivity(goRecommend);
            }
        });

        tab_want.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goWant = new Intent(getActivity(), Want.class);
                startActivity(goWant);
            }
        });

        tab_do.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goDoWant = new Intent(getActivity(), DoWant.class);
                startActivity(goDoWant);
            }
        });

        more_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goRecommend = new Intent(getActivity(), RecommendActivity.class);
                startActivity(goRecommend);
            }
        });
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

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
    protected HomePresenter createPresenter() {
        return new HomePresenter(this);
    }

    @Override
    public void homeInfoDone(HomeBean homeBean) {

    }

    @Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                SwithLayout.setRefreshing(false);
            }
        }, 3000);
    }
}