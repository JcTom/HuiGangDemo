package com.suctan.huigangdemo.activity.myself;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.suctan.huigangdemo.R;
import com.suctan.huigangdemo.fragment.FourthFragment;
import com.suctan.huigangdemo.fragment.ThirdFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by B-305 on 2017/4/14.
 */

public class my_assess  extends FragmentActivity implements View.OnClickListener,ViewPager.OnPageChangeListener {
    private ImageView assess_back_1;
    private ViewPager mViewPager;
    private FragmentPagerAdapter mAdapter;
    private List<Fragment> mDatas;
    // 控件
    private TextView my_assess = null;
    private TextView my_get_assess = null;

    private LinearLayout ll_my_assess = null;
    private LinearLayout ll_my_get_assess = null;
    // 滑动条颜色
    private int select_color;
    private int unselect_color;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_assess);
        initView();
        initFragment();
    }

    private void initFragment() {
        ThirdFragment mPPF = new ThirdFragment();
        FourthFragment mSSF = new FourthFragment();
        mDatas.add(mPPF);
        mDatas.add(mSSF);

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

    private void initView() {
        assess_back_1 = (ImageView) findViewById(R.id.assess_back_1);
        assess_back_1.setOnClickListener(this);

        // 获取颜色
        select_color = getResources().getColor(R.color.common_green);
        unselect_color = getResources().getColor(R.color.head_border_width_clo);

        my_assess = (TextView) findViewById(R.id.my_assess);
        my_get_assess = (TextView) findViewById(R.id.my_get_assess);
        ll_my_assess = (LinearLayout) findViewById(R.id.linear_my_assess);
        ll_my_get_assess = (LinearLayout) findViewById(R.id.linear_my_get_assess);
        //初始化, 传输这个case数值,过去进行判断
        ll_my_assess.setOnClickListener(new MyOnClickListenser(0));
        ll_my_get_assess.setOnClickListener(new MyOnClickListenser(1));

        mViewPager = (ViewPager) findViewById(R.id.assess_vp);
        mDatas = new ArrayList<Fragment>();
    }

    @Override
    public void onClick(View v) {
         switch (v.getId()){
             case R.id.assess_back_1:
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
                my_assess.setTextColor(select_color);
                break;
            case 1:
                my_get_assess.setTextColor(select_color);
                break;
        }

    }

    private void resetTextColor() {
        my_assess.setTextColor(unselect_color);
        my_get_assess.setTextColor(unselect_color);
    }

    public class MyOnClickListenser implements View.OnClickListener {

        private int index = 0;

        public MyOnClickListenser(int i) {
            index = i;
        }

        public void onClick(View v) {
            resetTextColor();
            switch (v.getId()) {
                case R.id.linear_my_assess:
                    my_assess.setTextColor(select_color);
                    break;
                case R.id.linear_my_get_assess:
                    my_get_assess.setTextColor(select_color);
                    break;
            }
            mViewPager.setCurrentItem(index);
        }

        private void resetTextColor() {
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
