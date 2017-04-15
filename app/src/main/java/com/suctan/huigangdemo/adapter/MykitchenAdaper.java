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
 * Created by Tom on 2017/4/14.
 */

public class MykitchenAdaper extends BaseAdapter {

    private Context context;
    private ArrayList<CompanyInfoBean> companyList;

    public MykitchenAdaper(Context context,ArrayList<CompanyInfoBean> companyList){
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
        GridViewHolder holder= null;
        CompanyInfoBean companyInfoBean = companyList.get(position);
        if (convertView==null){
            convertView= LayoutInflater.from(context).inflate(R.layout.mykitchen_item,parent,false);
            holder = new GridViewHolder();
            holder.ItemImage= (ImageView) convertView.findViewById(R.id.Mykitchen_ItemImage);
            holder.ItemText= (TextView) convertView.findViewById(R.id.Mykitchen_ItemTitle);
            convertView.setTag(holder);
        }else {
            holder = (GridViewHolder) convertView.getTag();}
       if (companyInfoBean.getImageUrl()!=null){
           LoadImageManager.getImageLoader().displayImage(companyInfoBean.getImageUrl(),holder.ItemImage);
       }
        return convertView;
    }
    public class GridViewHolder{
        ImageView ItemImage;
        TextView ItemText;
    }
}
