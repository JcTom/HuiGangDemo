package com.suctan.huigangdemo.activity.eatfood;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.androidbase.LoadImageManager;
import com.suctan.huigangdemo.R;
import com.suctan.huigangdemo.adapter.eatfood.EatGridAdapter;
import com.suctan.huigangdemo.adapter.eatfood.EatRecycleViewAdapter;
import com.suctan.huigangdemo.bean.index.EatFoodBean;

/**
 * Created by 黄淑翰 on 2017/4/20.
 */
public class EatFoodDetail extends AppCompatActivity {
    private LinearLayout ly_arroud_detail;
    private RecyclerView bottom_recycle_detail;
    private ListView bottom_listview_comment;
    private EatFoodBean mEatBean;
    private ImageView imv_eat_deail;//详情图
    private TextView tv_eat_name;
    private TextView tv_eat_num;
    private TextView tv_eat_momeny;

    @Override

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.top_one_food_details);
        initView();
        getIntentData();
    }

    private void getIntentData() {
        mEatBean = (EatFoodBean) getIntent().getSerializableExtra("nowEatFood");
        initViewData();
    }

    private void initViewData() {
        if (mEatBean != null) {
            if (mEatBean.getOrder_pic() != null) {
                LoadImageManager.getImageLoader().displayImage(mEatBean.getOrder_pic(), imv_eat_deail);
            }
            tv_eat_name.setText(mEatBean.getOrder_title());
            tv_eat_num.setText(mEatBean.getNum() + "");
            tv_eat_momeny.setText("￥" + mEatBean.getOrder_price());
        }
    }

    private void initView() {
        ly_arroud_detail = (LinearLayout) findViewById(R.id.ly_arroud_detail);
        imv_eat_deail = (ImageView) findViewById(R.id.imv_eat_deail);
        tv_eat_name = (TextView) findViewById(R.id.tv_eat_name);
        tv_eat_num = (TextView) findViewById(R.id.tv_eat_num);
        tv_eat_momeny = (TextView) findViewById(R.id.tv_eat_momeny);




        /*底部*/
        /**********************************************************************************/
        View view = LayoutInflater.from(this).inflate(R.layout.bottom_one_food_details, null, false);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.gravity = Gravity.BOTTOM;
        ly_arroud_detail.addView(view, params);
        bottom_recycle_detail = (RecyclerView) view.findViewById(R.id.bottom_recycle_detail);
        initRecycleViewAdapter();
        bottom_listview_comment = (ListView) view.findViewById(R.id.bottom_listview_comment);
        initGridViewAdater();
    }

    //初始化recycleviewdata
    private void initRecycleViewAdapter() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        bottom_recycle_detail.setLayoutManager(layoutManager);

        EatRecycleViewAdapter adapter = new EatRecycleViewAdapter(this);
        bottom_recycle_detail.setAdapter(adapter);

    }

    private void initGridViewAdater() {
        EatGridAdapter commentAdapter = new EatGridAdapter(this);
        bottom_listview_comment.setAdapter(commentAdapter);

    }


}
