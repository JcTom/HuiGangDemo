package com.suctan.huigangdemo.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.example.androidbase.mvp.MvpFragment;
import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.hintview.ColorPointHintView;
import com.suctan.huigangdemo.activity.do_want.DoWant;
import com.suctan.huigangdemo.activity.recommend.RecommendActivity;
import com.suctan.huigangdemo.activity.want.Want;
import com.suctan.huigangdemo.bean.user.CompanyInfoBean;
import com.suctan.huigangdemo.bean.user.HomeBean;
import com.suctan.huigangdemo.mvp.login.index.home.HomePresenter;
import com.suctan.huigangdemo.mvp.login.index.home.HomeView;
import com.suctan.huigangdemo.R;
import com.yalantis.phoenix.PullToRefreshView;

import java.util.ArrayList;
import java.util.HashMap;


import butterknife.BindView;
import butterknife.ButterKnife;
import cn.bingoogolapple.badgeview.BGABadgeRadioButton;

public class FragmentIndex extends MvpFragment<HomePresenter> implements ViewPager.OnPageChangeListener, View.OnClickListener, HomeView {
    View viewIndex;
    @BindView(R.id.tab_index)
    BGABadgeRadioButton tab_index;
    @BindView(R.id.tab_want)
    BGABadgeRadioButton tab_want;
    @BindView(R.id.tab_do)
    BGABadgeRadioButton tab_do;
    @BindView(R.id.rollViewpager)
    RollPagerView rollPagerView;
    @BindView(R.id.pull_to_refresh)
    PullToRefreshView pullToRefreshView;
    @BindView(R.id.gridview)
    GridView gridView;
    ArrayList<HashMap<String, Object>> lstImageItem = new ArrayList<HashMap<String, Object>>();

    @Nullable
    public View onCreateView(LayoutInflater paramLayoutInflater, @Nullable ViewGroup paramViewGroup, @Nullable Bundle paramBundle) {
        if (this.viewIndex == null)
            this.viewIndex = paramLayoutInflater.inflate(R.layout.fragment_index, paramViewGroup, false);
        ViewGroup localViewGroup = (ViewGroup) this.viewIndex.getParent();
        if (localViewGroup != null)
            localViewGroup.removeView(this.viewIndex);
        ButterKnife.bind(this, viewIndex);
        item();
        return this.viewIndex;
    }


    private void rollPagerViewSet() {
        rollPagerView.setPlayDelay(3000);//*播放间隔
        rollPagerView.setAnimationDurtion(500);//透明度
        ArrayList<CompanyInfoBean>companyList=new ArrayList<>();
        for(int i=0;i<=3;i++){
            CompanyInfoBean companyInfoBean=new CompanyInfoBean("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1492068213958&di=530b4e4796309358cc9f3d4ab750b9d7&imgtype=0&src=http%3A%2F%2Fstatic.leiphone.com%2Fuploads%2Fnew%2Farticle%2Fpic%2F201509%2F5602525bec148.jpg");
            companyList.add(companyInfoBean);
        }


        rollPagerView.setAdapter(new rollViewpagerAdapter(getActivity(),companyList));//配置适配器
        rollPagerView.setHintView(new ColorPointHintView(getActivity(), Color.BLUE, Color.WHITE));
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        rollPagerViewSet();
       /* Toast.makeText(getContext(),"你好",Toast.LENGTH_LONG).show();*/
        Check();
    }

    private void item() {
        for (int i = 0; i < 10; i++) {
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("image", R.mipmap.ic_launcher);
            map.put("Name", "No" + String.valueOf(i));
            lstImageItem.add(map);
        }
        SimpleAdapter saImageItems = new SimpleAdapter(getActivity(),
                lstImageItem,
                R.layout.recommend_item,
                new String[]{"image", "Name"},
                new int[]{R.id.ItemImage, R.id.ItemText});
        gridView.setAdapter(saImageItems);
        Toast.makeText(getContext(), "你好", Toast.LENGTH_LONG).show();
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                HashMap<String, Object> item = (HashMap<String, Object>) arg0.getItemAtPosition(arg2);
                item.get("I");
            }
        });
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
    public void homeInfoDone(HomeBean homeBean) {
    }
}