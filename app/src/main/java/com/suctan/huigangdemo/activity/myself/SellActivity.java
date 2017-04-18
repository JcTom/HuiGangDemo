package com.suctan.huigangdemo.activity.myself;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.suctan.huigangdemo.R;
import com.suctan.huigangdemo.fragment.FirstFragment;
import com.suctan.huigangdemo.fragment.SecondFragment;
import com.suctan.huigangdemo.fragment.ZeroFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by B-305 on 2017/4/13.
 */

public class SellActivity  extends FragmentActivity implements View.OnClickListener,ViewPager.OnPageChangeListener {
    private ImageView sell_back;
    // 2个滑动页面
    private ViewPager mViewPager;
    private FragmentPagerAdapter mAdapter;
    private List<Fragment> mDatas;
    // 控件
    private TextView text_seller_description = null;
    private TextView text_common_problem = null;
    //新增加的今日上架的菜色
    private TextView text_release_food=null;

    private LinearLayout ll_seller_description = null;
    private LinearLayout ll_common_problem = null;
    //新增加的 今日上架的菜色 的布局
    private  LinearLayout linear_release_food=null;

    GridView my_first_framgment_gridview;
    /*GridView My_evaluation_thridFrament_gridview;*/

    // 滑动条颜色
    private int select_color;
    private int unselect_color;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_sell);
        initView();
        initFragment();

    }
    private void initView() {
        my_first_framgment_gridview = (GridView) findViewById(R.id.my_first_framgment_gridview);
        sell_back = (ImageView) findViewById(R.id.sell_back);
        sell_back.setOnClickListener(this);
        // 获取颜色
        select_color = getResources().getColor(R.color.common_green);
        unselect_color = getResources().getColor(R.color.head_border_width_clo);

        text_seller_description = (TextView) findViewById(R.id.text_seller_description);
        text_common_problem = (TextView) findViewById(R.id.text_common_problem);
        //新增加的今日上架的文本
        text_release_food = (TextView) findViewById(R.id.text_release_food);


        ll_seller_description = (LinearLayout) findViewById(R.id.linear_seller_description);
        ll_common_problem = (LinearLayout) findViewById(R.id.linear_common_problem);
        //新增加的今日上架的文本的布局
        linear_release_food = (LinearLayout) findViewById(R.id.linear_release_food);


        ll_seller_description.setOnClickListener(new MyOnClickListenser(0));
        ll_common_problem.setOnClickListener(new MyOnClickListenser(1));
        //新增加的今日上架的文本的布局的点击事件
        linear_release_food.setOnClickListener(new MyOnClickListenser(2));

        mViewPager = (ViewPager) findViewById(R.id.mViewpager);
        mDatas = new ArrayList<Fragment>();

    }

    private void initFragment() {
        //新增加的zerofragmentactivity 初始化
        FirstFragment mSDF = new FirstFragment();
        SecondFragment mCPF = new SecondFragment();
        ZeroFragment rlDF = new ZeroFragment();
        mDatas.add(mSDF);
        mDatas.add(mCPF);
        //新增加的fragment 数据对接 添加入口
        mDatas.add(rlDF);

        mAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {

            @Override
            public int getCount() {
                return mDatas == null ? 0 : mDatas.size();
            }

            @Override
            public Fragment getItem(int position) {
                return mDatas.get(position);
            }
        };
        mViewPager.setAdapter(mAdapter);
        mViewPager.addOnPageChangeListener(this);
        mViewPager.setCurrentItem(0);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.sell_back:
                finish();
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    @Override
    public void onPageSelected(int position) {
        resetTextColor();
        switch (mViewPager.getCurrentItem()) {
            case 0:
                text_seller_description.setTextColor(select_color);
                break;
            case 1:
                text_common_problem.setTextColor(select_color);
                break;
            case 2:
                text_release_food.setTextColor(select_color);
                break;
        }

    }

    public class MyOnClickListenser implements View.OnClickListener{

        private int index = 0;

        public MyOnClickListenser(int i) {
            index = i;
        }

        @Override
        public void onClick(View v) {
            resetTextColor();
            switch (v.getId()) {
                case R.id.linear_seller_description:
                    text_seller_description.setTextColor(select_color);
                    break;
                case R.id.linear_common_problem:
                    text_common_problem.setTextColor(select_color);
                    break;
                case R.id.linear_release_food:
                    text_release_food.setTextColor(select_color);
            }
            mViewPager.setCurrentItem(index);
        }
    }

    private void resetTextColor() {
        text_seller_description.setTextColor(unselect_color);
        text_common_problem.setTextColor(unselect_color);
        text_release_food.setTextColor(unselect_color);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
