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
import android.widget.Toast;

import com.example.androidbase.BaseApplication;
import com.suctan.huigangdemo.R;
import com.suctan.huigangdemo.adapter.MyAssessGoAdapter;
import com.suctan.huigangdemo.bean.user.MyAssessGoBean;
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
    GridView My_evaluation_thridFrament_gridview;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_assess);
        initView();
        /*My_evaluation_thridFrament_gridview= (GridView) findViewById(R.id.My_evaluation_thridFrament_gridview);
        MyAssessGoAdapters();*/
        initFragment();
    }

    /*private void MyAssessGoAdapters() {

        Toast.makeText(this, getResources().getString(R.string.inputActTip), Toast.LENGTH_SHORT).show();
        ArrayList<MyAssessGoBean>companys=new ArrayList<>();
        for(int i=0;i<=3;i++){
            MyAssessGoBean myAssessGoBean=new MyAssessGoBean("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1492091993911&di=804ff682760b588e56abfc96f9d43ecd&imgtype=0&src=http%3A%2F%2Fpic.58pic.com%2F58pic%2F13%2F82%2F51%2F77P58PICFKD_1024.jpg"
                    ,"http://img2.imgtn.bdimg.com/it/u=2324580144,3531001861&fm=214&gp=0.jpg");
            companys.add(myAssessGoBean);
        }
        MyAssessGoAdapter adapter=new MyAssessGoAdapter(this,companys);
        if(adapter!=null){
            Toast.makeText(BaseApplication.getContext(),"adapter!=null",Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(BaseApplication.getContext(),"adapter==null",Toast.LENGTH_LONG).show();

        }

//        My_evaluation_thridFrament_gridview.setAdapter(adapter);

    }*/

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
