package com.suctan.huigangdemo.activity.myself;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.suctan.huigangdemo.R;
import com.suctan.huigangdemo.activity.Popupwindow.my_kitchen_popupwin_release;
import com.suctan.huigangdemo.adapter.IndexGridAdapter;
import com.suctan.huigangdemo.adapter.MykitchenAdaper;
import com.suctan.huigangdemo.bean.user.CompanyInfoBean;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by B-305 on 2017/4/13.
 */

public class MykitchenActity extends Activity{

    /*private  ListView lv;*/
    private ImageView Mykitchen_back;
    GridView Mykitchen_gridview;
    public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.my_kitchen);

        Mykitchen_gridview = (GridView) findViewById(R.id.Mykitchen_gridview);
        Mykitchen_back  = (ImageView) findViewById(R.id.Mykitchen_back);  //定义一个返回按钮

        goMykitchenAdapter();
        //实现返回按钮的点击事件
        Mykitchen_back.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                finish();
            }
        });




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

    private void goMykitchenAdapter() {
        ArrayList<CompanyInfoBean>companys=new ArrayList<>();
        for(int i=0;i<=10;i++){
            CompanyInfoBean companyInfoBean=new CompanyInfoBean("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1492091993911&di=804ff682760b588e56abfc96f9d43ecd&imgtype=0&src=http%3A%2F%2Fpic.58pic.com%2F58pic%2F13%2F82%2F51%2F77P58PICFKD_1024.jpg");
            companys.add(companyInfoBean);
        }
        MykitchenAdaper adapter=new MykitchenAdaper(this,companys);
        Mykitchen_gridview.setAdapter(adapter);
    }

    /*这个功能是我的厨房中的发布按钮弹出的popupwindow*/
    public void showPopFormBottom(View view) {
        my_kitchen_popupwin_release my_kitchen_popupwin_release = new my_kitchen_popupwin_release(this, onClickListener);
        my_kitchen_popupwin_release.showAtLocation(findViewById(R.id.main_view), Gravity.CENTER, 0, 0);
}

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            switch (v.getId()) {
                case R.id.btn_add_today_food:
                    break;
                case R.id.btn_add_new_food:
                    break;
            }
        }
    };
}
