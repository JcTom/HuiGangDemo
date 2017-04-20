package com.suctan.huigangdemo.activity.myself;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;

import com.example.androidbase.LoadImageManager;
import com.example.androidbase.mvp.MvpActivity;
import com.suctan.huigangdemo.R;
import com.suctan.huigangdemo.acache.CurrentUser;
import com.suctan.huigangdemo.activity.Popupwindow.my_kitchen_popupwin_release;
import com.suctan.huigangdemo.adapter.MykitchenAdaper;
import com.suctan.huigangdemo.bean.user.MykitchenBean;
import com.suctan.huigangdemo.mvp.login.myChiken.MyChikenPresenter;
import com.suctan.huigangdemo.mvp.login.myChiken.MyChikenView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by B-305 on 2017/4/13.
 */

public class MykitchenActity extends MvpActivity<MyChikenPresenter> implements View.OnClickListener, MyChikenView {

    /*private  ListView lv;*/
    private ImageView Mykitchen_back;
    GridView Mykitchen_gridview;
    private ImageView chiken_UserHead;//头像


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_kitchen);
        initView();
        initViewData();
        getMyChikenData();
        goMykitchenAdapter();

            /*lv = (ListView) findViewById(R.id.list_view_food);*//*定义一个动态数组*//*
            ArrayList<HashMap<String, Object>> listItem = new ArrayList<HashMap<String, Object>>();*//*在数组中存放数据*//*
            for (int i = 0; i < 100; i++) {
                HashMap<String, Object> map = new HashMap<String, Object>();
                map.put("ItemImage", R.mipmap.ic_launcher);//加入图片
                map.put("ItemTitle", "第" + i + "行");
                map.put("ItemText", "这是第" + i + "行");
                map.put("ItemMoney","¥"   + i);
                map.put("ItemReturn",R.mipmap.setting_arrow);
                listItem.add(map);
            }
            SimpleAdapter mSimpleAdapter = new SimpleAdapter(this, listItem,//需要绑定的数据
                    R.layout.list_item_food,//每一行的布局//动态数组中的数据源的键对应到定义布局的View中
                    new String[]{"ItemImage", "ItemTitle", "ItemText","ItemMoney","ItemReturn"},
                    new int[]{R.id.ItemImage, R.id.ItemTitle, R.id.ItemText, R.id.ItemMoney, R.id.ItemReturn
                    }
            );

            lv.setAdapter(mSimpleAdapter);//为ListView绑定适配器
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    Toast.makeText(getApplicationContext(),"你点击了我"+position,Toast.LENGTH_SHORT).show();
                }



            });*/
    }

    @Override
    protected MyChikenPresenter createPresenter() {
        return new MyChikenPresenter(this);
    }

    private void initView() {
        Mykitchen_gridview = (GridView) findViewById(R.id.Mykitchen_gridview);
        Mykitchen_back = (ImageView) findViewById(R.id.Mykitchen_back);  //定义一个返回按钮
        chiken_UserHead = (ImageView) findViewById(R.id.chiken_UserHead);
//监听
        Mykitchen_back.setOnClickListener(this);
    }

    private void initViewData() {
        if (CurrentUser.getInstance().getUserBean().getUser_icon() != null) {
            LoadImageManager.getImageLoader().displayImage(CurrentUser.getInstance().getUserBean().getUser_icon(), chiken_UserHead);
        }

    }

    private void goMykitchenAdapter() {
        ArrayList<MykitchenBean> companys = new ArrayList<>();
        for (int i = 0; i <= 10; i++) {
            MykitchenBean mykitchenBean = new MykitchenBean("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1492091993911&di=804ff682760b588e56abfc96f9d43ecd&imgtype=0&src=http%3A%2F%2Fpic.58pic.com%2F58pic%2F13%2F82%2F51%2F77P58PICFKD_1024.jpg");
            companys.add(mykitchenBean);
        }
        MykitchenAdaper adapter = new MykitchenAdaper(this, companys);
        Mykitchen_gridview.setAdapter(adapter);
    }

    /*这个功能是我的厨房中的发布按钮弹出的popupwindow*/
    public void showPopFormBottom(View view) {
        my_kitchen_popupwin_release my_kitchen_popupwin_release = new my_kitchen_popupwin_release(this);
        my_kitchen_popupwin_release.showAtLocation(findViewById(R.id.main_view), Gravity.CENTER, 0, 0);

        my_kitchen_popupwin_release.setAddChiChenLisetner(new my_kitchen_popupwin_release.AddKitChen() {
            @Override
            public void AddToDayFood() {
                Intent gotoTodayfood = new Intent(MykitchenActity.this, release_todayfood_Activiity.class);
                startActivity(gotoTodayfood);
            }

            @Override
            public void AddNewFood() {
                Intent gotoNewfood = new Intent(MykitchenActity.this, release_new_todayfoodActivity.class);
                startActivity(gotoNewfood);
            }
        });
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.Mykitchen_back:
                finish();
                break;
        }

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

    public void getMyChikenData() {
        Map<String, Object> myChikenMap = new HashMap<String, Object>();

    }
}
