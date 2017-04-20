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
import com.suctan.huigangdemo.activity.recommend.RecommendActivity;
import com.suctan.huigangdemo.bean.user.CompanyInfoBean;
import com.suctan.huigangdemo.bean.user.Recommend_indexBean;

import java.util.ArrayList;

/**
 * Created by Tom on 2017/4/14.
 */

public class RecommendindexAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Recommend_indexBean> companyList;

    public RecommendindexAdapter (Context context,ArrayList<Recommend_indexBean> companyList){
        this.context=context;
        this.companyList = companyList;
    }



    @Override
    public int getCount() {
        return companyList.size();
    }

    @Override
    public Object getItem(int position) {
        return companyList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        GridViewHolder holder = null;
        Recommend_indexBean recommend_indexBean = companyList.get(position);
        if (convertView==null){
            convertView= LayoutInflater.from(context).inflate(R.layout.list_item_today_food_release,parent,false);
            holder = new GridViewHolder();
            convertView.setTag(holder);
            holder.ItemImage= (ImageView) convertView.findViewById(R.id.recommend_today_ItemImage);
            holder.ItemText= (TextView) convertView.findViewById(R.id.recommend_today_ItemTitle);
        }else {
            holder = (GridViewHolder) convertView.getTag();
        }
        if(recommend_indexBean.getImageUrl()!=null){
            LoadImageManager.getImageLoader().displayImage(recommend_indexBean.getImageUrl(),holder.ItemImage);
        }
        return convertView;
    }
    public class GridViewHolder{
        ImageView ItemImage;
        TextView ItemText;
    }

    Recommend RecommendListener;
    public void setRecomendLisner(Recommend RecommendListener){
        this.RecommendListener=RecommendListener;
    }

    public interface Recommend{
        void onCarChange(CompanyInfoBean mcompanyInfoBean);
    }

}
