package com.suctan.huigangdemo.activity.recommend;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.androidbase.mvp.MvpActivity;
import com.jaeger.library.StatusBarUtil;
import com.suctan.huigangdemo.R;
import com.suctan.huigangdemo.activity.BaseActivity;
import com.suctan.huigangdemo.activity.MainActivity;
import com.suctan.huigangdemo.activity.eatfood.EatFoodDetail;
import com.suctan.huigangdemo.adapter.RecommendindexAdapter;
import com.suctan.huigangdemo.bean.index.EatFoodBean;
import com.suctan.huigangdemo.bean.index.EatFoodReturn;
import com.suctan.huigangdemo.bean.user.CompanyInfoBean;
import com.suctan.huigangdemo.bean.user.Recommend_indexBean;
import com.suctan.huigangdemo.mvp.login.index.home.HomePresenter;
import com.suctan.huigangdemo.mvp.login.index.home.HomeView;

import java.util.ArrayList;


/**
 * Created by Tom on 2017/4/10.
 */

public class RecommendActivity extends MvpActivity<HomePresenter> implements View.OnClickListener, RecommendindexAdapter.Recommend, HomeView {
    private static final String ACTIVITY_TAG = "RecommendActivity";
    private ImageView login_recommend_back;
    private TextView login_recommend_title;
    private ImageView recommend_ItemImage;
    GridView recommend_gridView;

    private boolean initFirstCreateGrid;

    private ArrayList<EatFoodBean> eatFoodList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recommend);
        StatusBarUtil.setColor(this, getResources().getColor(R.color.colorPrimary), 0);
        getRecommendFoodList();
        init();
    }

    @Override
    protected HomePresenter createPresenter() {
        return new HomePresenter(this);
    }

    private void getRecommendFoodList() {
        mvpPresenter.getEatFoodList();

    }

    //跳到想吃的详情页
    private void goEatDetail(int position) {
        Intent intent = new Intent(this, EatFoodDetail.class);
        intent.putExtra("nowEatFood", eatFoodList.get(position));
        startActivity(intent);
    }

    private void init() {
        /*recommend_ItemImage = (ImageView) findViewById(R.id.recommend_today_ItemImage);*/
        recommend_gridView = (GridView) findViewById(R.id.recommend_gridview);

        recommend_gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                goEatDetail(position);
            }
        });

        login_recommend_back = (ImageView) findViewById(R.id.login_recommend_back);

        login_recommend_back.setOnClickListener(this);
        login_recommend_title = (TextView) findViewById(R.id.login_recommend_title);

        login_recommend_title.setText("今日推荐");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_recommend_back:
                finish();
                break;
        }
    }
//    private void addCarListerner() {
//        ArrayList<Recommend_indexBean> ReconmmenList = new ArrayList<>();
//        for (int i = 0; i <= 10; i++) {
//            Recommend_indexBean companyInfoBean = new Recommend_indexBean("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1492091993911&di=804ff682760b588e56abfc96f9d43ecd&imgtype=0&src=http%3A%2F%2Fpic.58pic.com%2F58pic%2F13%2F82%2F51%2F77P58PICFKD_1024.jpg");
//            ReconmmenList.add(companyInfoBean);
//        }
//
//        adapter.setRecomendLisner(this);
//        /*Toast.makeText(this,"我的委托",Toast.LENGTH_SHORT).show();*/
//    }

    @Override
    public void onCarChange(CompanyInfoBean mcompanyInfoBean) {
//        Toast.makeText(this, "我的委托", Toast.LENGTH_SHORT).show();
//        recommend_gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                /*Log.d(RecommendActivity.ACTIVITY_TAG, "Error.");*/
//            }
//        });
       /* Toast.makeText(this,"我的委托",Toast.LENGTH_SHORT).show();*/
    }


    @Override
    public void getRollViewListSuc(EatFoodReturn mEatFoodReturn) {

    }

    @Override
    public void getRollViewListFail() {

    }

    RecommendindexAdapter eatFoodAdapter;

    @Override
    public void getEatfoodListSuc(EatFoodReturn mEatFoodReturn) {
        this.eatFoodList.addAll(mEatFoodReturn.getEatFoodBeanList());
        if (!initFirstCreateGrid) {
            initEataFoodAdapter();
            initFirstCreateGrid = true;
        } else {
            eatFoodAdapter.notifyDataSetChanged();
        }
    }

    private void initEataFoodAdapter() {
        eatFoodAdapter = new RecommendindexAdapter(this, eatFoodList);
        recommend_gridView.setAdapter(eatFoodAdapter);
    }

    @Override
    public void getEatfoodListFail() {

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
}
