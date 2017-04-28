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
import com.suctan.huigangdemo.bean.user.MykitchenBean;

import java.util.ArrayList;

/**
 * Created by Tom on 2017/4/14.
 */

public class MykitchenAdaper extends BaseAdapter {

    private Context context;
    private ArrayList<MykitchenBean> companyList;

    public MykitchenAdaper(Context context,ArrayList<MykitchenBean> companyList){
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
        MykitchenBean mykitchenBean = companyList.get(position);
        if (convertView==null){
            convertView= LayoutInflater.from(context).inflate(R.layout.mykitchen_item,parent,false);
            holder = new GridViewHolder();
            holder.ItemImage= (ImageView) convertView.findViewById(R.id.Mykitchen_ItemImage);
            holder.Mykitchen_ItemTitle = (TextView) convertView.findViewById(R.id.Mykitchen_ItemTitle);
            holder.Mykitchen_ItemText= (TextView) convertView.findViewById(R.id.Mykitchen_ItemText);
            holder.Mykitchen_ItemMoney = (TextView) convertView.findViewById(R.id.Mykitchen_ItemMoney);
            convertView.setTag(holder);
        }else {
            holder = (GridViewHolder) convertView.getTag();}
       if (mykitchenBean.getOrder_pic()!=null){
           LoadImageManager.getImageLoader().displayImage(mykitchenBean.getOrder_pic(),holder.ItemImage);
       }

        holder.Mykitchen_ItemTitle.setText(mykitchenBean.getOrder_title());
        holder.Mykitchen_ItemText.setText(mykitchenBean.getFood_description());
        holder.Mykitchen_ItemMoney.setText("￥"+(int) mykitchenBean.getOrder_price());
        return convertView;
    }
    public class GridViewHolder{
        ImageView ItemImage;
        TextView Mykitchen_ItemTitle;
        TextView Mykitchen_ItemText;
        TextView Mykitchen_ItemMoney;
    }
}
