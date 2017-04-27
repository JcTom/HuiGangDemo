package com.suctan.huigangdemo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.androidbase.BaseApplication;
import com.example.androidbase.LoadImageManager;
import com.suctan.huigangdemo.R;
import com.suctan.huigangdemo.bean.user.CompanyInfoBean;
import com.suctan.huigangdemo.bean.user.IndexGridBean;

import java.util.ArrayList;

/**
 * Created by Tom on 2017/4/13.
 */

public class IndexGridAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<IndexGridBean> companyList;

    public IndexGridAdapter(Context context, ArrayList<IndexGridBean> companyList) {
        this.context = context;
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
        IndexGridBean indexGridBean = companyList.get(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.recommend_item, parent, false);
            holder = new GridViewHolder();
            holder.ItemImage = (ImageView) convertView.findViewById(R.id.recommend_index_ItemImage);
            holder.ItemText = (TextView) convertView.findViewById(R.id.recommend_index_ItemText);
            convertView.setTag(holder);
        } else {
            holder = (GridViewHolder) convertView.getTag();
        }
        if (indexGridBean.getImageUrl() != null) {
            LoadImageManager.getImageLoader().displayImage(indexGridBean.getImageUrl(), holder.ItemImage);
        }
        /*RecommendListener.onCarChange(mcompanyInfoBean);*/
        return convertView;
    }

    public class GridViewHolder {
        ImageView ItemImage;
        TextView ItemText;
    }
    /*Recommend RecommendListener;
    public void setRecomendLisner(Recommend RecommendListener){
        this.RecommendListener=RecommendListener;*/
}

    /*public interface Recommend{
        void onCarChange(CompanyInfoBean mcompanyInfoBean);
    }

}*/
