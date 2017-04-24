package com.suctan.huigangdemo.activity.do_want;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.jaeger.library.StatusBarUtil;
import com.suctan.huigangdemo.R;
import com.suctan.huigangdemo.adapter.dofood.DoWantItemAdapter;
import com.suctan.huigangdemo.bean.dowant.DoWantOrderBean;
import com.suctan.huigangdemo.bean.wanteat.WantEatFoodItem;

import java.util.ArrayList;

/**
 * Created by 黄淑翰 on 2017/4/23.
 */
public class DoWantOrderDetatil extends Activity implements View.OnClickListener {
    private ImageView dowant_ordertail_back;
    private TextView tv_dodeatil_title;//标题
    private ListView lv_dowant_detail;//订单详情
    private TextView tv_note_dodetail;//客户留言
    private TextView tv_dowant_price;//价格
    private TextView tv_dodetailreceivername;//收获人
    private TextView tv_dowant_servicetype;//服务类型
    private TextView tv_receiver_address;//收货地址
    private TextView tv_receiver_phone;//收获人电话
    private TextView tv_dowant_reponsetim;//截止时间
    private DoWantOrderBean mdowantben;
    private boolean isFirstCreate;//第一次创建

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.want_do_orders_receiver);
        StatusBarUtil.setColor(this, getResources().getColor(R.color.colorPrimary), 0);
        initView();

        getIntentData();


    }

    private void getIntentData() {
        mdowantben = (DoWantOrderBean) getIntent().getSerializableExtra("nowDoWantOrder");
        initViewData();
    }

    private void initViewData() {
        if (mdowantben == null) {
            return;
        }
        tv_dodeatil_title.setText(mdowantben.getOrder_title());
        tv_note_dodetail.setText(mdowantben.getOrder_note());
        tv_dowant_price.setText(mdowantben.getOrder_price() + "");
        tv_dodetailreceivername.setText(mdowantben.getUser_name());
        String serviceType = null;
        if (mdowantben.getOrder_type() == 0) {
            serviceType = "上门做";
        } else if (mdowantben.getOrder_type() == 1) {
            serviceType = "送上门";
        } else {
            serviceType = "自提";
        }
        tv_dowant_servicetype.setText(serviceType);
        if (mdowantben.getUser_address() != null) {
            tv_receiver_address.setText(mdowantben.getUser_address());
        }
        tv_receiver_phone.setText(mdowantben.getUser_phone());

        if (mdowantben.getEatstrarr() != null) {
            System.out.println("获得我要吃饭列表" + mdowantben.getEatstrarr());
            InitDoWantAdapter(culToObject(mdowantben.getEatstrarr()));
        }
    }

    DoWantItemAdapter dowantAdapter;

    private void InitDoWantAdapter(ArrayList<WantEatFoodItem> wantEatFoodItems) {
        if (!isFirstCreate) {
            dowantAdapter = new DoWantItemAdapter(this, wantEatFoodItems);
            lv_dowant_detail.setAdapter(dowantAdapter);
        } else {
            dowantAdapter.notifyDataSetChanged();
        }
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
        dowant_ordertail_back = (ImageView) findViewById(R.id.dowant_ordertail_back);
        tv_dodeatil_title = (TextView) findViewById(R.id.tv_dodeatil_title);
        lv_dowant_detail = (ListView) findViewById(R.id.lv_dowant_detail);
        tv_note_dodetail = (TextView) findViewById(R.id.tv_note_dodetail);
        tv_dowant_price = (TextView) findViewById(R.id.tv_dowant_price);
        tv_dodetailreceivername = (TextView) findViewById(R.id.tv_dodetailreceivername);
        tv_dowant_servicetype = (TextView) findViewById(R.id.tv_dowant_servicetype);
        tv_receiver_address = (TextView) findViewById(R.id.tv_receiver_address);
        tv_receiver_phone = (TextView) findViewById(R.id.tv_receiver_phone);
        tv_dowant_reponsetim = (TextView) findViewById(R.id.tv_dowant_reponsetime);


        //监听
        dowant_ordertail_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.dowant_ordertail_back:
                finish();
                break;

        }
    }
}
