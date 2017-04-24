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
import com.suctan.huigangdemo.fragment.my.MyFinishOrder;
import com.suctan.huigangdemo.fragment.my.MyAllOrder;
import com.suctan.huigangdemo.fragment.my.MyWaitSendOrder;
import com.suctan.huigangdemo.fragment.my.MyWaitOrder;
import com.suctan.huigangdemo.fragmentinterface.InterFaceOrderState;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by B-305 on 2017/4/13.
 */

public class buyActivity extends FragmentActivity implements View.OnClickListener, ViewPager.OnPageChangeListener, InterFaceOrderState {


    private TextView tv_mybuy_all, tv_mybuy_djd, tv_mybuy_dsc, tv_mybuy_ywc;
    private LinearLayout ll_mybuy_all, ll_mybuy_djd, ll_mybuy_dsc, ll_mybuy_ywc;
    private ViewPager myBuyViewpager;

    // 滑动条颜色
    private int select_color;
    private int unselect_color;

    private FragmentPagerAdapter mAdapter;
    private List<Fragment> mDatas;
    private ImageView buy_back; //定义一个图片名字

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_buy);
        initView();
        initFragment();
    }

    private void initView() {
        //初始化，图片返回按钮点击事件
        buy_back = (ImageView) findViewById(R.id.buy_back);
        buy_back.setOnClickListener(this);


        // 获取颜色
        select_color = getResources().getColor(R.color.common_green);
        unselect_color = getResources().getColor(R.color.head_border_width_clo);

        tv_mybuy_all = (TextView) findViewById(R.id.tv_mybuy_all);
        tv_mybuy_djd = (TextView) findViewById(R.id.tv_mybuy_djd);
        tv_mybuy_dsc = (TextView) findViewById(R.id.tv_mybuy_dsc);
        tv_mybuy_ywc = (TextView) findViewById(R.id.tv_mybuy_ywc);

        ll_mybuy_all = (LinearLayout) findViewById(R.id.ll_mybuy_all);
        ll_mybuy_djd = (LinearLayout) findViewById(R.id.ll_mybuy_djd);
        ll_mybuy_dsc = (LinearLayout) findViewById(R.id.ll_mybuy_dsc);
        ll_mybuy_ywc = (LinearLayout) findViewById(R.id.ll_mybuy_ywc);

        ll_mybuy_all.setOnClickListener(new MyOnClickListenser(0));
        ll_mybuy_djd.setOnClickListener(new MyOnClickListenser(1));
        ll_mybuy_dsc.setOnClickListener(new MyOnClickListenser(2));
        ll_mybuy_ywc.setOnClickListener(new MyOnClickListenser(3));

        myBuyViewpager = (ViewPager) findViewById(R.id.myBuyViewpager);
        mDatas = new ArrayList<Fragment>();
    }

    private void initFragment() {

        MyAllOrder mSDF = MyAllOrder.getFragmentInstant();
        mSDF.setFragListner(this);
        MyWaitOrder mCPF = MyWaitOrder.getFragmentInstant();
        mCPF.setFragListner(this);
        MyWaitSendOrder mTDF = MyWaitSendOrder.getFragmentInstant();
        mTDF.setFragListner(this);
        MyFinishOrder mFPF = MyFinishOrder.getFragmentInstant();
        mFPF.setFragListner(this);
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
        myBuyViewpager.setAdapter(mAdapter);
        myBuyViewpager.addOnPageChangeListener(this);
        myBuyViewpager.setCurrentItem(0);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buy_back:
                finish();   //实现返回事件，回退前一个页面
        }
    }


    public class MyOnClickListenser implements View.OnClickListener {

        private int index = 0;

        public MyOnClickListenser(int i) {
            index = i;
        }

        @Override
        public void onClick(View v) {
            resetTextColor();
            switch (v.getId()) {
                case R.id.tv_mybuy_all:
                    tv_mybuy_all.setTextColor(select_color);
                    break;
                case R.id.tv_mybuy_djd:
                    tv_mybuy_djd.setTextColor(select_color);
                    break;
                case R.id.tv_mybuy_dsc:
                    tv_mybuy_dsc.setTextColor(select_color);
                    break;
                case R.id.tv_mybuy_ywc:
                    tv_mybuy_ywc.setTextColor(select_color);
                    break;
            }
            myBuyViewpager.setCurrentItem(index);
        }
    }

    private void resetTextColor() {
        tv_mybuy_all.setTextColor(unselect_color);
        tv_mybuy_djd.setTextColor(unselect_color);
        tv_mybuy_dsc.setTextColor(unselect_color);
        tv_mybuy_ywc.setTextColor(unselect_color);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        resetTextColor();
        switch (myBuyViewpager.getCurrentItem()) {
            case 0:
                tv_mybuy_all.setTextColor(select_color);
                break;
            case 1:
                tv_mybuy_djd.setTextColor(select_color);
                break;
            case 2:
                tv_mybuy_dsc.setTextColor(select_color);
                break;
            case 3:
                tv_mybuy_ywc.setTextColor(select_color);
                break;

        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}

