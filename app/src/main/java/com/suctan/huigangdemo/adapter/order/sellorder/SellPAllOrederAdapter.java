package com.suctan.huigangdemo.adapter.order.sellorder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.suctan.huigangdemo.R;
import com.suctan.huigangdemo.bean.order.buy.BuyRecommendBean;
import com.suctan.huigangdemo.bean.order.sell.SellOrderBean;

import java.util.ArrayList;

/**
 * Created by 黄淑翰 on 2017/4/26.
 */
public class SellPAllOrederAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<SellOrderBean> sellOrderBeenList;

    public SellPAllOrederAdapter(Context context, ArrayList<SellOrderBean> sellOrderBeenList) {
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
            view = LayoutInflater.from(context).inflate(R.layout.sellallp_orderlist_item, null, false);
            holder = new MyViewHolder();
            holder.tv_sellAllP_status = (TextView) view.findViewById(R.id.tv_sellAllP_status);
            holder.tv_tv_sellAllP_time = (TextView) view.findViewById(R.id.tv_tv_sellAllP_time);
            holder.tv_tv_sellAllP_foodName = (TextView) view.findViewById(R.id.tv_tv_sellAllP_foodName);
            holder.tv_tv_sellAllP_price = (TextView) view.findViewById(R.id.tv_tv_sellAllP_price);
            holder.tv_tv_sellAllP_orderId = (TextView) view.findViewById(R.id.tv_tv_sellAllP_orderId);
            holder.tv_tv_sellAllP_result = (TextView) view.findViewById(R.id.tv_tv_sellAllP_result);
            holder.tv_sellAllP_checkCommend = (TextView) view.findViewById(R.id.tv_sellAllP_checkCommend);
            holder.tv_sellAllP_cancel = (TextView) view.findViewById(R.id.tv_sellAllP_cancel);
            holder.tv_sellAllP_accept = (TextView) view.findViewById(R.id.tv_sellAllP_accept);
            holder.tv_sellAllP_detail = (TextView) view.findViewById(R.id.tv_sellAllP_detail);
            view.setTag(holder);
        } else {
            holder = (MyViewHolder) view.getTag();
        }
        switch (sellOrderBean.getOrder_status()) {
            case 0:
                holder.tv_sellAllP_status.setText("已取消");
                holder.tv_tv_sellAllP_result.setVisibility(View.GONE);
                holder.tv_sellAllP_checkCommend.setVisibility(View.GONE);
                holder.tv_sellAllP_cancel.setVisibility(View.GONE);
                holder.tv_sellAllP_accept.setVisibility(View.GONE);
                holder.tv_sellAllP_detail.setVisibility(View.VISIBLE);
                break;
            case 2:
                holder.tv_sellAllP_status.setText("进行中");
                holder.tv_tv_sellAllP_result.setVisibility(View.GONE);
                holder.tv_sellAllP_checkCommend.setVisibility(View.GONE);
                holder.tv_sellAllP_cancel.setVisibility(View.GONE);
                holder.tv_sellAllP_accept.setVisibility(View.GONE);
                holder.tv_sellAllP_detail.setVisibility(View.VISIBLE);
                break;
            case 3:
                holder.tv_sellAllP_status.setText("已完成");
                holder.tv_tv_sellAllP_result.setText("订单已完成");
                holder.tv_sellAllP_checkCommend.setVisibility(View.VISIBLE);
                holder.tv_sellAllP_cancel.setVisibility(View.GONE);
                holder.tv_sellAllP_accept.setVisibility(View.GONE);
                holder.tv_sellAllP_detail.setVisibility(View.VISIBLE);
                break;

        }
        holder.tv_tv_sellAllP_time.setText(sellOrderBean.getOrder_expect_time());
        holder.tv_tv_sellAllP_foodName.setText(sellOrderBean.getOrder_title());
        holder.tv_tv_sellAllP_price.setText(sellOrderBean.getOrder_price() + "");
        holder.tv_tv_sellAllP_orderId.setText(sellOrderBean.getOrder_id() + "");
        holder.tv_sellAllP_detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                detialOnClick.onItemOnClick(i);
            }
        });
        holder.tv_sellAllP_checkCommend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                detialOnClick.onSellPCommentOnClick(i);
            }
        });
        return view;
    }

    SellPAllDetailOnClickListener detialOnClick;

    public void onDetailOnclick(SellPAllDetailOnClickListener detialOnClick) {
        this.detialOnClick = detialOnClick;
    }

    public interface SellPAllDetailOnClickListener {
        void onItemOnClick(int position);

        void onSellPCommentOnClick(int position);
    }

    class MyViewHolder {
        TextView tv_sellAllP_status;
        TextView tv_tv_sellAllP_time;
        TextView tv_tv_sellAllP_foodName;
        TextView tv_tv_sellAllP_price;
        TextView tv_tv_sellAllP_orderId;
        TextView tv_tv_sellAllP_result;
        TextView tv_sellAllP_checkCommend;
        TextView tv_sellAllP_accept;
        TextView tv_sellAllP_cancel;
        TextView tv_sellAllP_detail;
    }

}
