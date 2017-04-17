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
import com.suctan.huigangdemo.bean.user.CompanyInfoBean;

import java.util.ArrayList;

/**
 * Created by 黄淑翰 on 2017/4/15.
 */
public class IndexFoodGridAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<CompanyInfoBean> companyLists;

    public IndexFoodGridAdapter(Context context, ArrayList<CompanyInfoBean> companyLists) {
        this.context = context;
        this.companyLists = companyLists;
    }

    @Override
    public int getCount() {
        return companyLists.size();
    }

    @Override
    public Object getItem(int i) {
        return companyLists.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        FootGridHolder viewHolder = null;
        CompanyInfoBean companyInfoBean = companyLists.get(i);

        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.recommend_item, viewGroup, false);
            viewHolder = new FootGridHolder();
            viewHolder.ItemImage = (ImageView) view.findViewById(R.id.recommend_index_ItemImage);
            viewHolder.ItemText = (TextView) view.findViewById(R.id.recommend_index_ItemText);
            view.setTag(viewHolder);
        } else {
            viewHolder = (FootGridHolder) view.getTag();
        }
        LoadImageManager.getImageLoader().displayImage(companyInfoBean.getImageUrl(), viewHolder.ItemImage);
        return view;
    }


    class FootGridHolder {
        ImageView ItemImage;
        TextView ItemText;
    }
}
