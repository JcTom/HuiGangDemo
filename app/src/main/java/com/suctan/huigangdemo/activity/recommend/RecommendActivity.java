package com.suctan.huigangdemo.activity.recommend;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.androidbase.utils.ToastTool;
import com.jaeger.library.StatusBarUtil;
import com.suctan.huigangdemo.R;
import com.suctan.huigangdemo.activity.BaseActivity;
import com.suctan.huigangdemo.adapter.IndexGridAdapter;
import com.suctan.huigangdemo.adapter.RecommendindexAdapter;
import com.suctan.huigangdemo.bean.user.CompanyInfoBean;
import java.util.ArrayList;


/**
 * Created by Tom on 2017/4/10.
 */

public class RecommendActivity extends BaseActivity implements View.OnClickListener,RecommendindexAdapter.Recommend{
    private static final String ACTIVITY_TAG="RecommendActivity";
    private ImageView login_recommend_back;
    private TextView login_recommend_title;
    private ImageView recommend_ItemImage;
    GridView recommend_gridView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recommend);
        StatusBarUtil.setColor(this, getResources().getColor(R.color.colorPrimary), 0);
        init();
        addCarListerner();
        /*recommendindex();*/
    }
    /*今日推荐item*/
    /*private void recommendindex() {
        ArrayList<CompanyInfoBean> companyList=new ArrayList<>();
        for(int i=0;i<=10;i++){
            CompanyInfoBean companyInfoBean=new CompanyInfoBean("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1492091993911&di=804ff682760b588e56abfc96f9d43ecd&imgtype=0&src=http%3A%2F%2Fpic.58pic.com%2F58pic%2F13%2F82%2F51%2F77P58PICFKD_1024.jpg");
            companyList.add(companyInfoBean);
        }
        RecommendindexAdapter adapter=new RecommendindexAdapter(this,companyList);
        recommend_gridView.setAdapter(adapter);

    }*/

    private void init() {
        /*recommend_ItemImage = (ImageView) findViewById(R.id.recommend_today_ItemImage);*/
        recommend_gridView = (GridView) findViewById(R.id.recommend_gridview);

        recommend_gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });

        login_recommend_back = (ImageView) findViewById(R.id.login_recommend_back);

        login_recommend_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        login_recommend_title = (TextView) findViewById(R.id.login_recommend_title);

        login_recommend_title.setText("今日推荐");
    }

    @Override
    public void onClick(View v) {

    }


    private void addCarListerner(){
        ArrayList<CompanyInfoBean> companyList=new ArrayList<>();
        for(int i=0;i<=10;i++){
            CompanyInfoBean companyInfoBean=new CompanyInfoBean("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1492091993911&di=804ff682760b588e56abfc96f9d43ecd&imgtype=0&src=http%3A%2F%2Fpic.58pic.com%2F58pic%2F13%2F82%2F51%2F77P58PICFKD_1024.jpg");
            companyList.add(companyInfoBean);
        }
        RecommendindexAdapter adapter=new RecommendindexAdapter(this,companyList);
        recommend_gridView.setAdapter(adapter);
        adapter.setRecomendLisner(this);
        /*Toast.makeText(this,"我的委托",Toast.LENGTH_SHORT).show();*/
    }
    @Override
    public void onCarChange(CompanyInfoBean mcompanyInfoBean) {
        Toast.makeText(this,"我的委托",Toast.LENGTH_SHORT).show();
        recommend_gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                /*Log.d(RecommendActivity.ACTIVITY_TAG, "Error.");*/
            }
        });
       /* Toast.makeText(this,"我的委托",Toast.LENGTH_SHORT).show();*/
    }
}
