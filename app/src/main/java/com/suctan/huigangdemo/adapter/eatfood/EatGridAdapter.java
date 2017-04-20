package com.suctan.huigangdemo.adapter.eatfood;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.androidbase.LoadImageManager;
import com.suctan.huigangdemo.R;
import com.suctan.huigangdemo.bean.user.IndexGridBean;

import java.util.ArrayList;

/**
 * Created by Tom on 2017/4/13.
 */

public class EatGridAdapter extends BaseAdapter {
    private Context context;

    public EatGridAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return 5;
    }

    @Override
    public Object getItem(int position) {
        return getItem(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        GridViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.bottom_one_food_details_listitem, parent, false);
            holder = new GridViewHolder();

            convertView.setTag(holder);
        } else {
            holder = (GridViewHolder) convertView.getTag();
        }
        return convertView;
    }

    public class GridViewHolder {

    }
}
