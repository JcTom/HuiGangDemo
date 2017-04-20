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
import com.suctan.huigangdemo.bean.user.Recommend_indexBean;
import com.suctan.huigangdemo.numlib.AnimShopButton;

import java.util.ArrayList;

/**
 * Created by Tom on 2017/4/14.
 */

public class RecommendindexAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<EatFoodBean> eatFoodBeanArrayList;

    public RecommendindexAdapter(Context context, ArrayList<EatFoodBean> eatFoodBeanArrayList) {
        this.context = context;
        this.eatFoodBeanArrayList = eatFoodBeanArrayList;
    }

    @Override
    public int getCount() {
        return eatFoodBeanArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return eatFoodBeanArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        GridViewHolder holder = null;
        EatFoodBean mFoodBean = eatFoodBeanArrayList.get(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.list_item_today_food_release, parent, false);
            holder = new GridViewHolder();
            convertView.setTag(holder);
            holder.ItemImage = (ImageView) convertView.findViewById(R.id.recommend_today_ItemImage);
            holder.ItemText = (TextView) convertView.findViewById(R.id.recommend_today_ItemTitle);
            holder.recommend_today_ItemTitle = (TextView) convertView.findViewById(R.id.recommend_today_ItemTitle);
            holder.recommend_today_descript = (TextView) convertView.findViewById(R.id.recommend_today_descript);
            holder.recommend_today_ItemMoney = (TextView) convertView.findViewById(R.id.recommend_today_ItemMoney);
            holder.btn_book_eatfood = (AnimShopButton) convertView.findViewById(R.id.btn_book_eatfood);
        } else {
            holder = (GridViewHolder) convertView.getTag();
        }
        if (mFoodBean.getOrder_pic() != null) {
            LoadImageManager.getImageLoader().displayImage(mFoodBean.getOrder_pic(), holder.ItemImage);
        }
        holder.recommend_today_ItemTitle.setText(mFoodBean.getOrder_title());
        holder.recommend_today_descript.setText(mFoodBean.getFood_description());
        holder.recommend_today_ItemMoney.setText("￥"+mFoodBean.getOrder_price());
        holder.btn_book_eatfood.setCount(mFoodBean.getNum());
        holder.btn_book_eatfood.setMaxCount(mFoodBean.getNum());
        return convertView;
    }

    public class GridViewHolder {
        ImageView ItemImage;
        TextView ItemText;
        TextView recommend_today_ItemTitle;
        TextView recommend_today_descript;
        TextView recommend_today_ItemMoney;
        AnimShopButton btn_book_eatfood;
    }

    Recommend RecommendListener;

    public void setRecomendLisner(Recommend RecommendListener) {
        this.RecommendListener = RecommendListener;
    }

    public interface Recommend {
        void onCarChange(CompanyInfoBean mcompanyInfoBean);
    }

}
