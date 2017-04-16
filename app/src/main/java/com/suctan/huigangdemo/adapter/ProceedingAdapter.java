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
import com.suctan.huigangdemo.bean.user.ProceedingBean;

import java.util.ArrayList;

/**
 * Created by Tom on 2017/4/16.
 */

public class ProceedingAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<ProceedingBean>proceedingList;
   public ProceedingAdapter(Context context, ArrayList<ProceedingBean> proceedingList){
       this.context = context;
       this.proceedingList = proceedingList;
   }

    @Override
    public int getCount() {
        return proceedingList.size();
    }

    @Override
    public Object getItem(int position) {
        return proceedingList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        GridViewHolder holder = null;
        ProceedingBean proceedingBean = proceedingList.get(position);
        if (convertView==null){
            convertView= LayoutInflater.from(context).inflate(R.layout.first_my_sell_list_item,parent,false);
            holder  = new GridViewHolder();
            holder.first_ItemImage = (ImageView) convertView.findViewById(R.id.first_ItemImage);
            holder.first_ItemTitle = (TextView) convertView.findViewById(R.id.first_ItemTitle);
            convertView.setTag(holder);
        }else {
            holder = (GridViewHolder) convertView.getTag();
        }
        if (proceedingBean.getProceedImageUrl()!=null){
            LoadImageManager.getImageLoader().displayImage(proceedingBean.getProceedImageUrl(),holder.first_ItemImage);
        }
        return convertView;
    }

    public class GridViewHolder{

        private ImageView first_ItemImage;
        private TextView first_ItemTitle;

    }
}
