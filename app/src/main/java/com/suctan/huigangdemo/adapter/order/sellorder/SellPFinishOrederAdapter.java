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
public class SellPFinishOrederAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<SellOrderBean> sellOrderBeenList;

    public SellPFinishOrederAdapter(Context context, ArrayList<SellOrderBean> sellOrderBeenList) {
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
            view = LayoutInflater.from(context).inflate(R.layout.sellfinishp_orderlist_item, null, false);
            holder = new MyViewHolder();
            holder.tv_sellfinshp_time = (TextView) view.findViewById(R.id.tv_sellfinshp_time);
            holder.tv_sellfinshp_foodname = (TextView) view.findViewById(R.id.tv_sellfinshp_foodname);
            holder.tv_sellfinshp_price = (TextView) view.findViewById(R.id.tv_sellfinshp_price);
            holder.tv_sellfinshp_orderId = (TextView) view.findViewById(R.id.tv_sellfinshp_orderId);
            holder.tv_sellfinshp_checkCommend = (TextView) view.findViewById(R.id.tv_sellfinshp_checkCommend);
            holder.tv_sellfinshp_detail = (TextView) view.findViewById(R.id.tv_sellfinshp_detail);
            view.setTag(holder);
        } else {
            holder = (MyViewHolder) view.getTag();
        }
        holder.tv_sellfinshp_foodname.setText(sellOrderBean.getOrder_title());
        holder.tv_sellfinshp_time.setText(sellOrderBean.getOrder_expect_time());
        holder.tv_sellfinshp_price.setText(sellOrderBean.getOrder_price() + "");
        holder.tv_sellfinshp_orderId.setText(sellOrderBean.getOrder_id() + "");

        holder.tv_sellfinshp_detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                detialOnClick.onItemOnClick(i);
            }
        });
        holder.tv_sellfinshp_checkCommend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                detialOnClick.onSellPCommentOnClicks(i);
            }
        });
        return view;
    }

    SellPFinishDetailOnClickListener detialOnClick;

    public void onDetailOnclick(SellPFinishDetailOnClickListener detialOnClick) {
        this.detialOnClick = detialOnClick;

    }

    public interface SellPFinishDetailOnClickListener {
        void onItemOnClick(int position);

        void onSellPCommentOnClicks(int position);
    }

    class MyViewHolder {
        TextView tv_sellfinshp_time;
        TextView tv_sellfinshp_foodname;
        TextView tv_sellfinshp_price;
        TextView tv_sellfinshp_orderId;
        TextView tv_sellfinshp_checkCommend;
        TextView tv_sellfinshp_detail;
    }

}
