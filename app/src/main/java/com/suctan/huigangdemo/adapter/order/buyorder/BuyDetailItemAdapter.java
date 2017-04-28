package com.suctan.huigangdemo.adapter.order.buyorder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.suctan.huigangdemo.R;
import com.suctan.huigangdemo.bean.wanteat.WantEatFoodItem;

import java.util.ArrayList;

/**
 * Created by 黄淑翰 on 2017/4/22.
 */
public class BuyDetailItemAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<WantEatFoodItem> wantEatItemList;

    public BuyDetailItemAdapter(Context context, ArrayList<WantEatFoodItem> wantEatItemList) {
        this.context = context;
        this.wantEatItemList = wantEatItemList;
    }

    @Override
    public int getCount() {
        return wantEatItemList.size();
    }

    @Override
    public Object getItem(int i) {
        return wantEatItemList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        MyViewHolder holder = null;
        WantEatFoodItem wantEatFoodItem = wantEatItemList.get(i);
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.activity_dowant_item, viewGroup, false);
            holder = new MyViewHolder();
            holder.tv_dowant_orderdetail = (TextView) view.findViewById(R.id.tv_dowant_orderdetail);
            view.setTag(holder);
        } else {
            holder = (MyViewHolder) view.getTag();
        }
        String orderType = "";
        if (wantEatFoodItem.getFoodType() != -1) {
            if (wantEatFoodItem.getFoodType() == 0) {
                orderType = "(小份)";
            } else if (wantEatFoodItem.getFoodType() == 1) {
                orderType = "(中份)";
            } else {
                orderType = "(大份)";
            }
        }

        holder.tv_dowant_orderdetail.setText(wantEatFoodItem.getFoodName() + "            " + wantEatFoodItem.getFoodCount() + "          " + orderType);
        return view;
    }

    class MyViewHolder {
        TextView tv_dowant_orderdetail;
    }
}
