package com.suctan.huigangdemo.adapter.wanteat;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.suctan.huigangdemo.R;

/**
 * Created by 黄淑翰 on 2017/4/22.
 */
public class AddFoodSpinneItemAdapter extends BaseAdapter {
    private Context context;
    private String[] foodType;
    private int index;
    MyViewHolder holder;

    public AddFoodSpinneItemAdapter(Context context, String[] foodType) {
        this.context = context;
        this.foodType = foodType;
    }

    @Override
    public int getCount() {
        return foodType.length;
    }

    @Override
    public Object getItem(int i) {
        return foodType[i];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {

        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.activity_foodtype_item, viewGroup, false);
            holder = new MyViewHolder();
            holder.tv_eat_type = (TextView) view.findViewById(R.id.tv_eat_type);
            holder.ry_eat_type = (RelativeLayout) view.findViewById(R.id.ry_eat_type);
            view.setTag(holder);
        } else {
            holder = (MyViewHolder) view.getTag();
        }
        holder.tv_eat_type.setText(foodType[i]);
        if (index == i) {
            holder.tv_eat_type.setTextColor(context.getResources().getColor(R.color.colorPrimary));
        } else {
            holder.tv_eat_type.setTextColor(Color.parseColor("#444444"));
        }

        return view;
    }

    public void setDataChange(int index) {
        this.index = index;
        notifyDataSetChanged();
    }

    class MyViewHolder {
        TextView tv_eat_type;
        RelativeLayout ry_eat_type;
    }
}
