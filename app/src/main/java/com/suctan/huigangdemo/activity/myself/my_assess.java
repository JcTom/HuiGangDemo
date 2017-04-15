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
    private TextView text_seller_description = null;
    private TextView text_common_problem = null;
    private TextView text_purchase_process = null;
    private TextView text_same_shop = null;
    private LinearLayout ll_seller_description = null;
    private LinearLayout ll_common_problem = null;
    private LinearLayout ll_purchase_process = null;
    private LinearLayout ll_same_shop = null;
    private ImageView img_line;

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
        select_color = getResources().getColor(R.color.text_orange);
        unselect_color = getResources().getColor(R.color.black);

        text_purchase_process = (TextView) findViewById(R.id.text_purchase_process);
        text_same_shop = (TextView) findViewById(R.id.text_same_shop);
        ll_seller_description = (LinearLayout) findViewById(R.id.linear_seller_description);
        ll_common_problem = (LinearLayout) findViewById(R.id.linear_common_problem);


        ll_purchase_process.setOnClickListener(new MyOnClickListenser(2));
        ll_same_shop.setOnClickListener(new MyOnClickListenser(3));

        mViewPager = (ViewPager) findViewById(R.id.mViewpager);
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
            case 2:
                text_purchase_process.setTextColor(select_color);
                break;
            case 3:
                text_same_shop.setTextColor(select_color);
                break;
        }

    }

    private void resetTextColor() {
        text_purchase_process.setTextColor(unselect_color);
        text_same_shop.setTextColor(unselect_color);
    }

    public class MyOnClickListenser implements View.OnClickListener {

        private int index = 0;

        public MyOnClickListenser(int i) {
            index = i;
        }

        public void onClick(View v) {
            resetTextColor();
            switch (v.getId()) {
                case R.id.linear_purchase_process:
                    text_purchase_process.setTextColor(select_color);
                    break;
                case R.id.linear_same_shop:
                    text_same_shop.setTextColor(select_color);
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
