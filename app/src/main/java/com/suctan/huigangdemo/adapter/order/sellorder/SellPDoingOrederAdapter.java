package com.suctan.huigangdemo.adapter.order.sellorder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.suctan.huigangdemo.R;
import com.suctan.huigangdemo.bean.order.buy.BuyRecommendBean;
import com.suctan.huigangdemo.bean.order.sell.SellOrderBean;

import java.util.ArrayList;

/**
 * Created by 黄淑翰 on 2017/4/26.
 */
public class SellPDoingOrederAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<SellOrderBean> sellOrderBeenList;

    public SellPDoingOrederAdapter(Context context, ArrayList<SellOrderBean> sellOrderBeenList) {
        this.context = context;
        this.sellOrderBeenList = sellOrderBeenList;
    }

    @Override
    public int getCount() {
        return sellOrderBeenList.size();
    }

    @Override
    public Object getItem(int i) {
        return sellOrderBeenList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        MyViewHolder holder = null;
        SellOrderBean sellOrderBean = sellOrderBeenList.get(i);
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.selldongingp_orderlist_item, null, false);
            holder = new MyViewHolder();


            holder.tv_selldoingp_time = (TextView) view.findViewById(R.id.tv_selldoingp_time);
            holder.tv_selldoingp_foodname = (TextView) view.findViewById(R.id.tv_selldoingp_foodname);
            holder.tv_selldoingp_price = (TextView) view.findViewById(R.id.tv_selldoingp_price);
            holder.tv_selldoingp_orderId = (TextView) view.findViewById(R.id.tv_selldoingp_orderId);
            holder.tv_selldoingp_detail = (TextView) view.findViewById(R.id.tv_selldoingp_detail);
            view.setTag(holder);
        } else {
            holder = (MyViewHolder) view.getTag();
        }
        holder.tv_selldoingp_foodname.setText(sellOrderBean.getOrder_title());
        holder.tv_selldoingp_time.setText(sellOrderBean.getOrder_expect_time());
        holder.tv_selldoingp_price.setText(sellOrderBean.getOrder_price() + "");
        holder.tv_selldoingp_orderId.setText(sellOrderBean.getOrder_id() + "");
        holder.tv_selldoingp_detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                detialOnClick.onItemOnClick(i);
            }
        });
        return view;
    }


    SellPDoingDetailOnClickListener detialOnClick;

    public void onDetailOnclick(SellPDoingDetailOnClickListener detialOnClick) {
        this.detialOnClick = detialOnClick;
    }

    public interface SellPDoingDetailOnClickListener {
        void onItemOnClick(int position);

    }

    class MyViewHolder {

        TextView tv_selldoingp_time;
        TextView tv_selldoingp_foodname;
        TextView tv_selldoingp_price;
        TextView tv_selldoingp_detail;
        TextView tv_selldoingp_orderId;

    }

}
