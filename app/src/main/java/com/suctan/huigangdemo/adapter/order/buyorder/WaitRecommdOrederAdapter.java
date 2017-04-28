package com.suctan.huigangdemo.adapter.order.buyorder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.suctan.huigangdemo.R;
import com.suctan.huigangdemo.bean.order.buy.BuyRecommendBean;

import java.util.ArrayList;

/**
 * Created by 黄淑翰 on 2017/4/26.
 */
 public class WaitRecommdOrederAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<BuyRecommendBean> AllBuyRecommendList;

    public WaitRecommdOrederAdapter(Context context, ArrayList<BuyRecommendBean> AllBuyRecommendList) {
        this.context = context;
        this.AllBuyRecommendList = AllBuyRecommendList;
    }

    @Override
    public int getCount() {
        return AllBuyRecommendList.size();
    }

    @Override
    public Object getItem(int i) {
        return AllBuyRecommendList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        MyViewHolder holder = null;
        BuyRecommendBean buyRecommendBean = AllBuyRecommendList.get(i);
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.recommend_waitorderlist_item, null, false);
            holder = new MyViewHolder();
            holder.tv_remodWait_time = (TextView) view.findViewById(R.id.tv_remodWait_time);
            holder.tv_remdWait_foodname = (TextView) view.findViewById(R.id.tv_remdWait_foodname);
            holder.tv_remdWait_price = (TextView) view.findViewById(R.id.tv_remdWait_price);
            holder.tv_remdWait_orderId = (TextView) view.findViewById(R.id.tv_remdWait_orderId);
            holder.tv_remdWait_recTime = (TextView) view.findViewById(R.id.tv_remdWait_recTime);
            holder.tv_remdWait_cancel = (TextView) view.findViewById(R.id.tv_remdWait_cancel);
            holder.tv_remdWait_detial = (TextView) view.findViewById(R.id.tv_remdWait_detial);
            view.setTag(holder);
        } else {
            holder = (MyViewHolder) view.getTag();
        }
        holder.tv_remdWait_foodname.setText(buyRecommendBean.getOrder_title());
        holder.tv_remdWait_recTime.setText(buyRecommendBean.getOrder_expect_time());
        holder.tv_remdWait_price.setText(buyRecommendBean.getOrder_price() + "");
        holder.tv_remdWait_orderId.setText(buyRecommendBean.getOrder_id() + "");
        holder.tv_remdWait_recTime.setText(buyRecommendBean.getOrder_res_time());

        return view;
    }

    class MyViewHolder {


        TextView tv_remodWait_time;
        TextView tv_remdWait_foodname;
        TextView tv_remdWait_price;
        TextView tv_remdWait_orderId;
        TextView tv_remdWait_recTime;
        TextView tv_remdWait_cancel;
        TextView tv_remdWait_detial;
    }

}
