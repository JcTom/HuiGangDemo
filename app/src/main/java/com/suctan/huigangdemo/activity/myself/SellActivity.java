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
import com.suctan.huigangdemo.fragment.my.MysellAllFrag;
import com.suctan.huigangdemo.fragment.my.MysellWaitAgreeFrag;
import com.suctan.huigangdemo.fragment.my.MySell_Four;
import com.suctan.huigangdemo.fragment.my.MySell_Three;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by B-305 on 2017/4/13.
 */

public class SellActivity  extends FragmentActivity implements View.OnClickListener,ViewPager.OnPageChangeListener {
    private ImageView sell_back;
    // 4个滑动页面
    private ViewPager mViewPager;
    private FragmentPagerAdapter mAdapter;
    private List<Fragment> mDatas;

    // 控件
    private TextView tvmysell_all,tvmysell_dty,tvmysell_jxz,tvmysell_ywc;
    private LinearLayout llmysell_all,llmysell_dty,llmysell_jxz,llmysell_ywc;

//    GridView my_first_framgment_gridview;
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
//        my_first_framgment_gridview = (GridView) findViewById(R.id.my_first_framgment_gridview);
        sell_back = (ImageView) findViewById(R.id.sell_back);
        sell_back.setOnClickListener(this);
        // 获取颜色
        select_color = getResources().getColor(R.color.common_green);
        unselect_color = getResources().getColor(R.color.head_border_width_clo);

        tvmysell_all = (TextView) findViewById(R.id.tvmysell_all);
        tvmysell_dty = (TextView) findViewById(R.id.tvmysell_dty);
        tvmysell_jxz = (TextView) findViewById(R.id.tvmysell_jxz);
        tvmysell_ywc = (TextView) findViewById(R.id.tvmysell_ywc);

        llmysell_all = (LinearLayout) findViewById(R.id.llmysell_all);
        llmysell_dty = (LinearLayout) findViewById(R.id.llmysell_dty);
        llmysell_jxz = (LinearLayout) findViewById(R.id.llmysell_jxz);
        llmysell_ywc = (LinearLayout) findViewById(R.id.llmysell_ywc);

        llmysell_all.setOnClickListener(new MyOnClickListenser(0));
        llmysell_dty.setOnClickListener(new MyOnClickListenser(1));
        llmysell_jxz.setOnClickListener(new MyOnClickListenser(2));
        llmysell_ywc.setOnClickListener(new MyOnClickListenser(3));

        mViewPager = (ViewPager) findViewById(R.id.mViewpager);
        mDatas = new ArrayList<Fragment>();

    }

    private void initFragment() {

        MysellAllFrag mSDF = new MysellAllFrag();
        MysellWaitAgreeFrag mCPF = new MysellWaitAgreeFrag();
        MySell_Three mTDF = new MySell_Three();
        MySell_Four mFPF = new MySell_Four();

        mDatas.add(mSDF);
        mDatas.add(mCPF);
        mDatas.add(mTDF);
        mDatas.add(mFPF);


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
                tvmysell_all.setTextColor(select_color);
                break;
            case 1:
                tvmysell_dty.setTextColor(select_color);
                break;
            case 2:
                tvmysell_jxz.setTextColor(select_color);
                break;
            case 3:
                tvmysell_ywc.setTextColor(select_color);
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
                case R.id.llmysell_all:
                    tvmysell_all.setTextColor(select_color);
                    break;
                case R.id.llmysell_dty:
                    tvmysell_dty.setTextColor(select_color);
                    break;
                case R.id.llmysell_jxz:
                    tvmysell_jxz.setTextColor(select_color);
                    break;
                case R.id.llmysell_ywc:
                    tvmysell_ywc.setTextColor(select_color);
                    break;

            }
            mViewPager.setCurrentItem(index);
        }
    }

    private void resetTextColor() {
        tvmysell_all.setTextColor(unselect_color);
        tvmysell_dty.setTextColor(unselect_color);
        tvmysell_jxz.setTextColor(unselect_color);
        tvmysell_ywc.setTextColor(unselect_color);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
