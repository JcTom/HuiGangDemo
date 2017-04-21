package com.suctan.huigangdemo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.androidbase.LoadImageManager;
import com.suctan.huigangdemo.R;
import com.suctan.huigangdemo.bean.index.EatFoodBean;
import com.suctan.huigangdemo.bean.user.CompanyInfoBean;

import java.util.ArrayList;

/**
 * Created by 黄淑翰 on 2017/4/15.
 */
public class IndexFoodGridAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<EatFoodBean> eatFoodBeenList;

    public IndexFoodGridAdapter(Context context, ArrayList<EatFoodBean> eatFoodBeenList) {
        this.context = context;
        this.eatFoodBeenList = eatFoodBeenList;
    }

    @Override
    public int getCount() {
        return eatFoodBeenList.size();
    }

    @Override
    public Object getItem(int i) {
        return eatFoodBeenList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        FootGridHolder viewHolder = null;
        EatFoodBean eatFoodBean = eatFoodBeenList.get(i);

        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.recommend_item, viewGroup, false);
            viewHolder = new FootGridHolder();
            viewHolder.ItemImage = (ImageView) view.findViewById(R.id.recommend_index_ItemImage);
            viewHolder.ItemText = (TextView) view.findViewById(R.id.recommend_index_ItemText);
            view.setTag(viewHolder);
        } else {
            viewHolder = (FootGridHolder) view.getTag();
        }
        if (eatFoodBean.getOrder_pic() != null) {
            LoadImageManager.getImageLoader().displayImage(eatFoodBean.getOrder_pic(), viewHolder.ItemImage);
        }
        viewHolder.ItemText.setText(eatFoodBean.getOrder_title());
        return view;
    }


    class FootGridHolder {
        ImageView ItemImage;
        TextView ItemText;
    }
}
