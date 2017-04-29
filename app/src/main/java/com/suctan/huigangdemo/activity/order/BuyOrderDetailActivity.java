package com.suctan.huigangdemo.activity.order;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.jaeger.library.StatusBarUtil;
import com.suctan.huigangdemo.R;
import com.suctan.huigangdemo.adapter.order.buyorder.BuyDetailItemAdapter;
import com.suctan.huigangdemo.bean.order.buy.BuyRecommendBean;
import com.suctan.huigangdemo.bean.wanteat.WantEatFoodItem;

import java.util.ArrayList;

/**
 * Created by 黄淑翰 on 2017/4/27.
 */
public class BuyOrderDetailActivity extends Activity implements View.OnClickListener {

    private ImageView tv_orderdetail_back;
    private TextView tv_orderdetail_nubmer;
    private ListView lv_orderdetail_item;
    private TextView tv_orderdetail_note;
    private TextView tv_orderdetail_allprice;
    private TextView tv_orderdetail_username;
    private TextView tv_ordertail_servicetype;
    private TextView tv_ordertail_address;
    private TextView tv_ordertail_phone;
    private TextView tv_dowant_expecttime;


    private BuyRecommendBean mBuyRecommend;
    private int orderSortKey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_detail_item);
        StatusBarUtil.setColor(this, getResources().getColor(R.color.colorPrimary), 0);
        initView();
        getIntentData();
    }

    private void getIntentData() {
        Intent deliverIntent = getIntent();
        mBuyRecommend = (BuyRecommendBean) deliverIntent.getSerializableExtra("buy");
        orderSortKey = deliverIntent.getIntExtra("buySort", 0);
        if (mBuyRecommend != null) {
            initViewData();
        }
    }

    private void initViewData() {
        tv_orderdetail_nubmer.setText(mBuyRecommend.getOrder_id() + "");
        tv_orderdetail_note.setText(mBuyRecommend.getOrder_note());
        tv_orderdetail_username.setText(mBuyRecommend.getUser_name());
        if (orderSortKey == 0) {
            tv_orderdetail_allprice.setText(mBuyRecommend.getAll_price() + "");
            ArrayList<WantEatFoodItem> wantEatFoodItemList = new ArrayList<>();
            wantEatFoodItemList.add(new WantEatFoodItem(mBuyRecommend.getOrder_title(), -1, mBuyRecommend.getNum()));
            initAdateper(wantEatFoodItemList);
        } else {
            tv_orderdetail_allprice.setText(mBuyRecommend.getOrder_price() + "");
            initAdateper(culToObject(mBuyRecommend.getEatstrarr()));
        }
        String type = null;
        if (mBuyRecommend.getOrder_type() == 0) {
            type = "上门做";
        } else if (mBuyRecommend.getOrder_type() == 1) {
            type = "送上门";
        } else {
            type = "自提";
        }
        tv_ordertail_servicetype.setText(type);
        tv_ordertail_address.setText(mBuyRecommend.getUer_address());
        tv_ordertail_phone.setText(mBuyRecommend.getUser_phone());
        tv_dowant_expecttime.setText(mBuyRecommend.getOrder_expect_time());
    }

    private void initAdateper(ArrayList<WantEatFoodItem> wantEatFoodItems) {
        BuyDetailItemAdapter adapter = new BuyDetailItemAdapter(this, wantEatFoodItems);
        lv_orderdetail_item.setAdapter(adapter);
    }

    String sortCamma[];

    //将字符串转化成对象
    private ArrayList<WantEatFoodItem> culToObject(String stringobject) {
        ArrayList<WantEatFoodItem> wantEatItemList = new ArrayList<>();
        String sortPeriod[] = stringobject.split("period");
        if (sortPeriod.length == 0) {
            sortCamma = stringobject.split("comma");
            WantEatFoodItem wantEat = new WantEatFoodItem(sortCamma[0], Integer.parseInt(sortCamma[1]), Integer.parseInt(sortCamma[2]));
            wantEatItemList.add(wantEat);
        } else {
            for (int i = 0; i < sortPeriod.length; i++) {
                sortCamma = sortPeriod[i].split("comma");
                if (sortCamma[1] != null && sortCamma[2] != null) {
                    WantEatFoodItem wantEat = new WantEatFoodItem(sortCamma[0], Integer.parseInt(sortCamma[1]), Integer.parseInt(sortCamma[2]));
                    wantEatItemList.add(wantEat);
                }
            }
        }
        return wantEatItemList;
    }

    private void initView() {
        tv_orderdetail_back = (ImageView) findViewById(R.id.tv_orderdetail_back);
        tv_orderdetail_nubmer = (TextView) findViewById(R.id.tv_orderdetail_nubmer);
        lv_orderdetail_item = (ListView) findViewById(R.id.lv_orderdetail_item);
        tv_orderdetail_note = (TextView) findViewById(R.id.tv_orderdetail_note);
        tv_orderdetail_allprice = (TextView) findViewById(R.id.tv_orderdetail_allprice);
        tv_orderdetail_username = (TextView) findViewById(R.id.tv_orderdetail_username);
        tv_ordertail_servicetype = (TextView) findViewById(R.id.tv_ordertail_servicetype);
        tv_ordertail_address = (TextView) findViewById(R.id.tv_ordertail_address);
        tv_ordertail_phone = (TextView) findViewById(R.id.tv_ordertail_phone);
        tv_dowant_expecttime = (TextView) findViewById(R.id.tv_dowant_expecttime);

        //监听
        tv_orderdetail_back.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_orderdetail_back:
                finish();
                break;
        }
    }
}
