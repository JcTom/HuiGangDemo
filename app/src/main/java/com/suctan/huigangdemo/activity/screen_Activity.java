package com.suctan.huigangdemo.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;


import com.suctan.huigangdemo.R;
import com.suctan.huigangdemo.bean.user.PlaceBean;
import com.suctan.huigangdemo.bean.user.TimeBean;

import java.util.ArrayList;
import java.util.List;

public class screen_Activity extends BaseActivity {
    /**
     * 展示全部类型的数据源
     */
    List<PlaceBean> mPopBeens = new ArrayList<>();
    /**
     * 展示每一栋楼宇的分类数据源
     */
    List<String> mTypes = new ArrayList<>();
    /**
     * 展示份量的数据
     */
    List<TimeBean> mTimes = new ArrayList<>();
    /**
     * 展示的份量str集合
     */
    List<String> mTimeStr = new ArrayList<>();
    /**
     * 筛选地区整体
     */
    LinearLayout mPlaceAll;
    /**
     * 筛选城市cb
     */
    CheckBox mPlaceCb;
    /**
     * 筛选类型整体
     */
    LinearLayout mTypeAll;
    /**
     * 筛选类型整体
     */
    CheckBox mTypeCb;
    /**
     * 筛选快递上门类型整体
     */
    LinearLayout mTimeAll;
    /**
     * 筛选快递上门服务类型整体
     */
    CheckBox mTimeCb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.header_screen);
        initDate();
        initView();
    }


    /**
     * 初始化数据
     */
    private void initDate() {

        // 初始化全部（全部类型）
        PlaceBean placeBean11=new PlaceBean("全部");
        PlaceBean placeBean1 = new PlaceBean("混合所有");
        //加载数据类型,并且按照顺序排列
        mPopBeens.add(placeBean11);
        mPopBeens.add(placeBean1);


        // 初始化类型（全部楼宇），没有初始化，直接赋予参数，这样会直接写死后台数据
        mTypes.add("全部");
        mTypes.add("A栋");
        mTypes.add("B栋");
        mTypes.add("C栋");
        mTypes.add("D栋");
        mTypes.add("E栋");
        mTypes.add("F栋");
        mTypes.add("G栋");
        mTypes.add("H栋");

        // 初始化份量（剩余份量）
        TimeBean timeBean4 =new TimeBean("全部");
        TimeBean timeBean1 = new TimeBean("送上门");
        TimeBean timeBean2 = new TimeBean("自提");
        TimeBean timeBean3 = new TimeBean("送上门");
        //加载初始化的数据
        mTimes.add(timeBean4);
        mTimes.add(timeBean1);
        mTimes.add(timeBean2);
        mTimes.add(timeBean3);


        // 获取服务类型中可用于筛选的数据
        for (TimeBean bean : mTimes) {
            mTimeStr.add(bean.getTimeStr());
        }
    }

    /**
     * 初始化控件
     */
    private void initView() {
        mPlaceAll = (LinearLayout) findViewById(R.id.ll_place_tab);
        mPlaceCb = (CheckBox) findViewById(R.id.cb_place);
        mTypeAll = (LinearLayout) findViewById(R.id.ll_type);
        mTypeCb = (CheckBox) findViewById(R.id.cb_type);
        mTimeAll = (LinearLayout) findViewById(R.id.ll_time);
        mTimeCb = (CheckBox) findViewById(R.id.cb_time);
        // 点击选择城市整体
        mPlaceAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mPlaceCb.isChecked())
                    mPlaceCb.setChecked(false);
                else
                    mPlaceCb.setChecked(true);
            }
        });
        // 点击选择类型整体
        mTypeAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mTypeCb.isChecked())
                    mTypeCb.setChecked(false);
                else
                    mTypeCb.setChecked(true);
            }
        });
        // 点击选择时间整体
        mTimeAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mTimeCb.isChecked())
                    mTimeCb.setChecked(false);
                else
                    mTimeCb.setChecked(true);
            }
        });

        // 选择城市cb
        mPlaceCb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                filterTabToggleT(isChecked, mPlaceAll, mPopBeens, new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                        hidePopListView();
                        mPlaceCb.setText(mPopBeens.get(position).getFilterStr());
                    }
                }, mPlaceCb, mTypeCb, mTimeCb);
            }
        });

        // 选择类型cb
        mTypeCb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                filterTabToggle(isChecked, mTypeAll, mTypes, new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                        hidePopListView();
                        mTypeCb.setText(mTypes.get(position));
                    }
                }, mTypeCb, mPlaceCb, mTimeCb);
            }
        });
        // 选择时间cb
        mTimeCb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                filterTabToggle(isChecked, mTimeAll, mTimeStr, new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                        hidePopListView();
                        mTimeCb.setText(mTimeStr.get(position));
                    }
                }, mTimeCb, mPlaceCb, mTypeCb);
            }
        });

    }
}
