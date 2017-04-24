package com.suctan.huigangdemo.adapter.dofood;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.suctan.huigangdemo.R;
import com.suctan.huigangdemo.bean.dowant.DoWantOrderBean;

import java.util.ArrayList;

/**
 * Created by 黄淑翰 on 2017/4/22.
 */
public class DofoodAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<DoWantOrderBean> mDowantBeanList;

    public DofoodAdapter(Context context, ArrayList<DoWantOrderBean> mDowantBeanList) {
        this.context = context;
        this.mDowantBeanList = mDowantBeanList;
    }

    @Override
    public int getCount() {
        return mDowantBeanList.size();
    }

    @Override
    public Object getItem(int i) {
        return mDowantBeanList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        MyViewHolder holder = null;
        DoWantOrderBean mwantBean = mDowantBeanList.get(i);
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.do_want_item, viewGroup, false);
            holder = new MyViewHolder();
            holder.tv_do_address = (TextView) view.findViewById(R.id.tv_do_address);
            holder.tv_do_hopetime = (TextView) view.findViewById(R.id.tv_do_hopetime);
            holder.tv_do_title = (TextView) view.findViewById(R.id.tv_do_title);
            holder.tv_do_momeny = (TextView) view.findViewById(R.id.tv_do_momeny);
            view.setTag(holder);
        } else {
            holder = (MyViewHolder) view.getTag();
        }
        holder.tv_do_title.setText(mwantBean.getOrder_title());
        holder.tv_do_momeny.setText(mwantBean.getOrder_price() + "");
        holder.tv_do_address.setText(mwantBean.getUser_address());
        return view;
    }

    class MyViewHolder {
        TextView tv_do_address;
        TextView tv_do_hopetime;
        TextView tv_do_title;
        TextView tv_do_momeny;
    }
}
