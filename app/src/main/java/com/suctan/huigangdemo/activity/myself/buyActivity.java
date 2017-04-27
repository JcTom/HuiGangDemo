package com.suctan.huigangdemo.activity.myself;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.androidbase.BaseApplication;
import com.sevenheaven.segmentcontrol.SegmentControl;
import com.suctan.huigangdemo.R;
import com.suctan.huigangdemo.adapter.order.buyorder.BuyFragAdapter;
import com.suctan.huigangdemo.fragment.my.buy.MyPublishFinishOrder;
import com.suctan.huigangdemo.fragment.my.buy.MyPublishAllOrder;
import com.suctan.huigangdemo.fragment.my.buy.MyWaitSendOrder;
import com.suctan.huigangdemo.fragment.my.buy.MyPublishWaitOrder;
import com.suctan.huigangdemo.fragment.my.buy.MyeatAllOrder;
import com.suctan.huigangdemo.fragment.my.buy.MyeatFinishOrder;
import com.suctan.huigangdemo.fragment.my.buy.MyeatWaitOrder;
import com.suctan.huigangdemo.fragment.my.buy.MyeatWaitSendOrder;
import com.suctan.huigangdemo.fragmentinterface.InterFaceOrderState;

import java.util.ArrayList;

/**
 * Created by B-305 on 2017/4/13.
 */

public class buyActivity extends FragmentActivity implements View.OnClickListener, ViewPager.OnPageChangeListener, InterFaceOrderState {
    private TextView tv_mybuy_all, tv_mybuy_djd, tv_mybuy_dsc, tv_mybuy_ywc;
    private LinearLayout ll_mybuy_all, ll_mybuy_djd, ll_mybuy_dsc, ll_mybuy_ywc;
    private ViewPager myBuyViewpager;
    private SegmentControl segment_buyOrder_sort;

    // 滑动条颜色
    private int select_color;
    private int unselect_color;
    private ArrayList<Fragment> mDatas = new ArrayList<Fragment>();
    ;
    private ImageView buy_back; //定义一个图片名字

    private MyPublishAllOrder mdoAllF;
    private MyPublishWaitOrder mdoWaitOrder;
    private MyPublishFinishOrder mdoWaitFinish;
    private MyWaitSendOrder mdoWaitSend;


    private MyeatAllOrder myeatAllOrder;
    private MyeatWaitOrder myeatWaitOrder;
    private MyeatWaitSendOrder myeatWaitSendOrder;
    private MyeatFinishOrder myeatFinishOrder;
    private boolean isFirstCrateViewPage;
    private int orderSortKey;
    private BuyFragAdapter mAdapter;
    private ViewPager myBuyMakeViewpager;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_buy);
        initView();
        initFragment(orderSortKey);
    }

    private void initView() {
        //初始化，图片返回按钮点击事件
        buy_back = (ImageView) findViewById(R.id.buy_back);
        segment_buyOrder_sort = (SegmentControl) findViewById(R.id.segment_buyOrder_sort);
        buy_back.setOnClickListener(this);
        segment_buyOrder_sort.setOnSegmentControlClickListener(new SegmentControl.OnSegmentControlClickListener() {
            @Override
            public void onSegmentControlClick(int index) {
                initFragment(index);
            }
        });
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
        myBuyMakeViewpager = (ViewPager) findViewById(R.id.myBuyMakeViewpager);
    }

    private void toogleShowViewPage(int orderSortKey) {
        if (orderSortKey == 0) {
            myBuyViewpager.setVisibility(View.VISIBLE);
            myBuyMakeViewpager.setVisibility(View.GONE);
        } else {
            myBuyViewpager.setVisibility(View.GONE);
            myBuyMakeViewpager.setVisibility(View.VISIBLE);
        }
    }

    private void initFragment(int orderSortKey) {
        this.orderSortKey = orderSortKey;
        toogleShowViewPage(orderSortKey);
        if (orderSortKey == 0) {
            mdoAllF = MyPublishAllOrder.getFragmentInstant();
            mdoAllF.setFragListner(this);
            mdoWaitOrder = MyPublishWaitOrder.getFragmentInstant();
            mdoWaitOrder.setFragListner(this);
            mdoWaitSend = MyWaitSendOrder.getFragmentInstant();
            mdoWaitSend.setFragListner(this);
            mdoWaitFinish = MyPublishFinishOrder.getFragmentInstant();
            mdoWaitFinish.setFragListner(this);
            mDatas.add(mdoAllF);
            mDatas.add(mdoWaitOrder);
            mDatas.add(mdoWaitSend);
            mDatas.add(mdoWaitFinish);
            mAdapter = new BuyFragAdapter(getSupportFragmentManager(), mDatas);
            myBuyViewpager.setAdapter(mAdapter);
            myBuyViewpager.addOnPageChangeListener(this);
            myBuyViewpager.setCurrentItem(0);
        } else {
            ArrayList<Fragment> fragmentsList = new ArrayList<>();
            myeatAllOrder = MyeatAllOrder.getFragmentInstant();
            myeatAllOrder.setFragListner(this);
            myeatWaitOrder = MyeatWaitOrder.getFragmentInstant();
            myeatWaitOrder.setFragListner(this);
            myeatWaitSendOrder = MyeatWaitSendOrder.getFragmentInstant();
            myeatWaitSendOrder.setFragListner(this);
            myeatFinishOrder = MyeatFinishOrder.getFragmentInstant();
            myeatFinishOrder.setFragListner(this);
            fragmentsList.add(myeatAllOrder);
            fragmentsList.add(myeatWaitOrder);
            fragmentsList.add(myeatWaitSendOrder);
            fragmentsList.add(myeatFinishOrder);
            mAdapter = new BuyFragAdapter(getSupportFragmentManager(), fragmentsList);
            myBuyMakeViewpager.setAdapter(mAdapter);
            myBuyMakeViewpager.addOnPageChangeListener(this);
            myBuyMakeViewpager.setCurrentItem(0);
        }
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

