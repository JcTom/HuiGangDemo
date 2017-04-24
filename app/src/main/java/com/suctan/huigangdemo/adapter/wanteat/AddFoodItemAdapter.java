package com.suctan.huigangdemo.adapter.wanteat;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.suctan.huigangdemo.R;
import com.suctan.huigangdemo.bean.wanteat.WantEatFoodItem;

import java.util.ArrayList;

/**
 * Created by 黄淑翰 on 2017/4/22.
 */
public class AddFoodItemAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<WantEatFoodItem> WantEatItemList;

    public AddFoodItemAdapter(Context context, ArrayList<WantEatFoodItem> WantEatItemList) {
        this.context = context;
        this.WantEatItemList = WantEatItemList;
    }

    @Override
    public int getCount() {
        return WantEatItemList.size();
    }

    @Override
    public Object getItem(int i) {
        return WantEatItemList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        MyViewHolder holder = null;
        WantEatFoodItem wantEatFoodItem = WantEatItemList.get(i);
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.want_eat_name, viewGroup, false);
            holder = new MyViewHolder();
            holder.tv_want_eat_name = (TextView) view.findViewById(R.id.tv_want_eat_name);
            holder.imvb_wanteat_item_delete = (ImageButton) view.findViewById(R.id.imvb_wanteat_item_delete);
            view.setTag(holder);
        } else {
            holder = (MyViewHolder) view.getTag();
        }
        String foodtyoer = null;

        if (wantEatFoodItem.getFoodType() == 0) {
            foodtyoer = "(小份)";
        } else if (wantEatFoodItem.getFoodType() == 1) {
            foodtyoer = "(中份)";
        } else {
            foodtyoer = "(大份)";
        }

        holder.tv_want_eat_name.setText(wantEatFoodItem.getFoodName() + "   " + wantEatFoodItem.getFoodCount() + "   " + foodtyoer);

        holder.imvb_wanteat_item_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Listener.deleteWantEatItem(i);
            }
        });
        return view;
    }

    OnWantEatItemOnclick Listener;

    public void setOnClickWantEatDeleteItem(OnWantEatItemOnclick Listener) {
        this.Listener = Listener;
    }

    public interface OnWantEatItemOnclick {
        void deleteWantEatItem(int position);
    }


    class MyViewHolder {
        TextView tv_want_eat_name;
        ImageButton imvb_wanteat_item_delete;

    }
}
