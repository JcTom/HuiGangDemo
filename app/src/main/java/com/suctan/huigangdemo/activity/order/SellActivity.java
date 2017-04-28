package com.suctan.huigangdemo.activity.order;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jaeger.library.StatusBarUtil;
import com.sevenheaven.segmentcontrol.SegmentControl;
import com.suctan.huigangdemo.R;
import com.suctan.huigangdemo.adapter.order.sellorder.SellFragAdapter;
import com.suctan.huigangdemo.fragment.my.sell.SellAgreeAFrag;
import com.suctan.huigangdemo.fragment.my.sell.SellAllAFrag;
import com.suctan.huigangdemo.fragment.my.sell.SellAllPFrag;
import com.suctan.huigangdemo.fragment.my.sell.SellDoingAFrag;
import com.suctan.huigangdemo.fragment.my.sell.SellDoingPFrag;
import com.suctan.huigangdemo.fragment.my.sell.SellFinishAFrag;
import com.suctan.huigangdemo.fragment.my.sell.SellFinishPFrag;
import com.suctan.huigangdemo.fragmentinterface.InterfaceMysellOrderState;

import java.util.ArrayList;

/**
 * Created by B-305 on 2017/4/13.
 */

public class SellActivity extends FragmentActivity implements View.OnClickListener, ViewPager.OnPageChangeListener, InterfaceMysellOrderState {
    private ImageView sell_back;
    // 4个滑动页面

    private ViewPager sell_viewPageA;
    private ViewPager sell_viewPagep;
    // 控件
    private TextView tvmysell_all, tvmysell_dty, tvmysell_jxz, tvmysell_ywc;
    private LinearLayout llmysell_all, llmysell_dty, llmysell_jxz, llmysell_ywc;
    private SegmentControl segment_sellOrder_sort;

//    GridView my_first_framgment_gridview;
    /*GridView My_evaluation_thridFrament_gridview;*/

    // 滑动条颜色
    private int select_color;
    private int unselect_color;
    private int sortFrakey;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_sell);
        StatusBarUtil.setColor(this, getResources().getColor(R.color.colorPrimary), 0);

        initView();
        initFragment(sortFrakey);

    }

    private void initView() {
        sell_back = (ImageView) findViewById(R.id.sell_back);
        segment_sellOrder_sort = (SegmentControl) findViewById(R.id.segment_sellOrder_sort);
        sell_back.setOnClickListener(this);

        segment_sellOrder_sort.setOnSegmentControlClickListener(new SegmentControl.OnSegmentControlClickListener() {
            @Override
            public void onSegmentControlClick(int index) {
                initFragment(index);
            }
        });

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

        sell_viewPageA = (ViewPager) findViewById(R.id.sell_viewPageA);
        sell_viewPagep = (ViewPager) findViewById(R.id.sell_viewPagep);


    }

    //当前要显示哪个viewpage
    private void toogleShowViewPage(int sortKey) {
        resetTextColor();
        tvmysell_all.setTextColor(select_color);
        if (sortKey == 0) {
            sell_viewPageA.setVisibility(View.GONE);
            sell_viewPagep.setVisibility(View.VISIBLE);
            llmysell_dty.setVisibility(View.GONE);
        } else {
            llmysell_dty.setVisibility(View.VISIBLE);
            sell_viewPageA.setVisibility(View.VISIBLE);
            sell_viewPagep.setVisibility(View.GONE);
        }
    }

    SellFragAdapter adapter;

    private void initFragment(int sortKey) {
        this.sortFrakey = sortKey;
        toogleShowViewPage(sortKey);
        if (sortKey == 0) {
            ArrayList<Fragment> mDatas = new ArrayList<Fragment>();
            SellAllPFrag sellAllPFrag = SellAllPFrag.getFragInstant();
            sellAllPFrag.setListanter(this);
            SellDoingPFrag sellDoingPFrag = SellDoingPFrag.getFragInstant();
            sellDoingPFrag.setListanter(this);
            SellFinishPFrag sellFinishPFrag = SellFinishPFrag.getFragInstant();
            sellFinishPFrag.setListanter(this);
            mDatas.add(sellAllPFrag);
            mDatas.add(sellDoingPFrag);
            mDatas.add(sellFinishPFrag);
            adapter = new SellFragAdapter(getSupportFragmentManager(), mDatas);
            sell_viewPagep.setAdapter(adapter);
            sell_viewPagep.addOnPageChangeListener(this);
            sell_viewPagep.setCurrentItem(0);
        } else {
            ArrayList<Fragment> fragments = new ArrayList<Fragment>();
            SellAllAFrag sellAllAFrag = SellAllAFrag.getFragInstant();
            sellAllAFrag.setListanter(this);
            SellAgreeAFrag sellAgreeAFrag = SellAgreeAFrag.getFragInstant();
            sellAgreeAFrag.setListanter(this);
            SellDoingAFrag sellDoingAFrag = SellDoingAFrag.getFragInstant();
            sellDoingAFrag.setListanter(this);
            SellFinishAFrag sellFinishAFrag = SellFinishAFrag.getFragInstant();
            sellFinishAFrag.setListanter(this);
            fragments.add(sellAllAFrag);
            fragments.add(sellAgreeAFrag);
            fragments.add(sellDoingAFrag);
            fragments.add(sellFinishAFrag);
            adapter = new SellFragAdapter(getSupportFragmentManager(), fragments);
            sell_viewPageA.setAdapter(adapter);
            sell_viewPageA.addOnPageChangeListener(this);
            sell_viewPageA.setCurrentItem(0);
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
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
        if (sortFrakey == 0) {
            switch (position) {
                case 0:
                    tvmysell_all.setTextColor(select_color);
                    break;
                case 1:
                    tvmysell_jxz.setTextColor(select_color);
                    break;
                case 2:
                    tvmysell_ywc.setTextColor(select_color);
                    break;
            }
        } else {
            switch (position) {
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
                case R.id.llmysell_all:
                    tvmysell_all.setTextColor(select_color);
                    if (sortFrakey == 0) {
                        sell_viewPagep.setCurrentItem(index);
                    }
                    break;
                case R.id.llmysell_dty:
                    tvmysell_dty.setTextColor(select_color);
                    break;
                case R.id.llmysell_jxz:
                    tvmysell_jxz.setTextColor(select_color);
                    if (sortFrakey == 0) {
                        sell_viewPagep.setCurrentItem(1);
                    }

                    break;
                case R.id.llmysell_ywc:
                    tvmysell_ywc.setTextColor(select_color);
                    if (sortFrakey == 0) {
                        sell_viewPagep.setCurrentItem(1);
                    }
                    break;
            }
            if (sortFrakey == 1) {
                sell_viewPageA.setCurrentItem(index);
            }
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
