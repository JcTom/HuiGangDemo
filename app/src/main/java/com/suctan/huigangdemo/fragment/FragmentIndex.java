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
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import com.example.androidbase.mvp.MvpFragment;
import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.hintview.ColorPointHintView;
import com.suctan.huigangdemo.R;
import com.suctan.huigangdemo.activity.do_want.DoWant;
import com.suctan.huigangdemo.activity.eatfood.EatFoodDetail;
import com.suctan.huigangdemo.activity.recommend.RecommendActivity;
import com.suctan.huigangdemo.activity.want.Want;
import com.suctan.huigangdemo.adapter.IndexFoodGridAdapter;
import com.suctan.huigangdemo.bean.index.EatFoodBean;
import com.suctan.huigangdemo.bean.index.EatFoodReturn;
import com.suctan.huigangdemo.mvp.login.index.home.HomePresenter;
import com.suctan.huigangdemo.mvp.login.index.home.HomeView;
import com.suctan.huigangdemo.widget.SwpipeListViewOnScrollListener;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.bingoogolapple.badgeview.BGABadgeRadioButton;

public class FragmentIndex extends MvpFragment<HomePresenter> implements ViewPager.OnPageChangeListener, View.OnClickListener, HomeView, SwipeRefreshLayout.OnRefreshListener, RollViewpagerAdapter.OnItemRollLisener {
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
    @BindView(R.id.common_swipe_fresh_layout)
    SwipeRefreshLayout swipeRayout;
    @BindView(R.id.gridview)
    GridView gridView;
    private boolean isFirstCreate;
    private boolean isFirstRollViewPage;
    private boolean isFirstRequestGridData;
    private ArrayList<EatFoodBean> eatRollFoodBeanList = new ArrayList<>();
    private ArrayList<EatFoodBean> eatFoodBeanList = new ArrayList<>();

    @Nullable
    public View onCreateView(LayoutInflater paramLayoutInflater, @Nullable ViewGroup paramViewGroup, @Nullable Bundle paramBundle) {
        if (this.viewIndex == null)
            this.viewIndex = paramLayoutInflater.inflate(R.layout.fragment_index, paramViewGroup, false);
        ViewGroup localViewGroup = (ViewGroup) this.viewIndex.getParent();
        if (localViewGroup != null)
            localViewGroup.removeView(this.viewIndex);
        return this.viewIndex;
    }


    //设置轮播图
    private void rollPagerViewSet() {
        ColorPointHintView hintView = new ColorPointHintView(getActivity(), getResources().getColor(R.color.colorPrimary), getResources().getColor(R.color.colord2));
        rollPagerView.setHintView(hintView);
        rollPagerView.setPlayDelay(3000);
        rollPagerView.setAnimationDurtion(500);
        mvpPresenter.getRollPageList();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (!isFirstCreate) {
            ButterKnife.bind(this, viewIndex);
            isFirstCreate = true;
            rollPagerViewSet();
            initGridData();
            Check();
            initRefreshView();
        }
    }

    private void initRefreshView() {
        swipeRayout.setOnRefreshListener(this);
        SwpipeListViewOnScrollListener Lisetner = new SwpipeListViewOnScrollListener(swipeRayout);
        gridView.setOnScrollListener(Lisetner);
        swipeRayout.setColorSchemeColors(
                getResources().getColor(R.color.gplus_color_1),
                getResources().getColor(R.color.gplus_color_2),
                getResources().getColor(R.color.gplus_color_3),
                getResources().getColor(R.color.gplus_color_4));


    }

    //进入推荐菜品列表
    private void initGridData() {
        mvpPresenter.getEatFoodList();
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                goEatDetail(i);
            }
        });
    }

    //跳到想吃的详情页
    private void goEatDetail(int position) {
        Intent intent = new Intent(getActivity(), EatFoodDetail.class);
        intent.putExtra("nowEatFood", eatFoodBeanList.get(position));
        startActivity(intent);
    }

    private void goEatDetailForRoll(int position) {
        Intent intent = new Intent(getActivity(), EatFoodDetail.class);
        intent.putExtra("nowEatFood", eatRollFoodBeanList.get(position));
        startActivity(intent);
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
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                swipeRayout.setRefreshing(false);
            }
        }, 3000);
    }

    private RollViewpagerAdapter rollAdapter;

    @Override
    public void getRollViewListSuc(EatFoodReturn mEatFoodReturn) {
        this.eatRollFoodBeanList.addAll(mEatFoodReturn.getEatFoodBeanList());
        if (eatRollFoodBeanList != null) {
            if (!isFirstRollViewPage) {
                rollAdapter = new RollViewpagerAdapter(getActivity(), mEatFoodReturn.getEatFoodBeanList());
                rollPagerView.setAdapter(rollAdapter);//配置适配器
                rollAdapter.setOnRollItemt(this);
                isFirstRollViewPage = true;
            } else {
                rollAdapter.notifyDataSetChanged();
            }
        }
    }

    @Override
    public void getRollViewListFail() {

    }

    private IndexFoodGridAdapter gridAdapter;

    @Override
    public void getEatfoodListSuc(EatFoodReturn mEatFoodReturn) {
        this.eatFoodBeanList.addAll(mEatFoodReturn.getEatFoodBeanList());
        if (eatFoodBeanList != null) {
            if (!isFirstRequestGridData) {
                gridAdapter = new IndexFoodGridAdapter(getActivity(), mEatFoodReturn.getEatFoodBeanList());
                gridView.setAdapter(gridAdapter);
                isFirstRequestGridData = true;
            } else {
                gridAdapter.notifyDataSetChanged();
            }
        }
    }


    @Override
    public void getEatfoodListFail() {

    }

    @Override
    public void nowRollViewPageItem(int position) {
        goEatDetailForRoll(position);
    }

    @Override
    protected void lazyLoad() {

    }
}