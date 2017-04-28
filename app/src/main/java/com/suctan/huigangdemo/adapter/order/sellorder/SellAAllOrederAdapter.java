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
public class SellAAllOrederAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<SellOrderBean> sellOrderBeenList;

    public SellAAllOrederAdapter(Context context, ArrayList<SellOrderBean> sellOrderBeenList) {
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
            view = LayoutInflater.from(context).inflate(R.layout.sella_alllist_item, null, false);
            holder = new MyViewHolder();
            holder.imv_sellAllA_pic = (ImageView) view.findViewById(R.id.imv_sellAllA_pic);
            holder.tv_sellAllA_status = (TextView) view.findViewById(R.id.tv_sellAllA_status);
            holder.tv_sellAllA_time = (TextView) view.findViewById(R.id.tv_sellAllA_time);
            holder.tv_sellAllA_foodName = (TextView) view.findViewById(R.id.tv_sellAllA_foodName);
            holder.tv_sellAllA_price = (TextView) view.findViewById(R.id.tv_sellAllA_price);
            holder.tv_sellAllA_orderId = (TextView) view.findViewById(R.id.tv_sellAllA_orderId);
            holder.tv_sellAllA_result = (TextView) view.findViewById(R.id.tv_sellAllA_result);
            holder.tv_sellAllA_checkCommend = (TextView) view.findViewById(R.id.tv_sellAllA_checkCommend);
            holder.tv_sellAllA_cancel = (TextView) view.findViewById(R.id.tv_sellAllA_cancel);
            holder.tv_sellAllA_accept = (TextView) view.findViewById(R.id.tv_sellAllA_accept);
            holder.tv_sellAllA_detail = (TextView) view.findViewById(R.id.tv_sellAllA_detail);
            view.setTag(holder);
        } else {
            holder = (MyViewHolder) view.getTag();
        }
        switch (sellOrderBean.getOrder_status()) {
            case 0:
                holder.tv_sellAllA_status.setText("已取消");
                holder.tv_sellAllA_result.setVisibility(View.GONE);
                holder.tv_sellAllA_checkCommend.setVisibility(View.GONE);
                holder.tv_sellAllA_cancel.setVisibility(View.GONE);
                holder.tv_sellAllA_accept.setVisibility(View.GONE);
                holder.tv_sellAllA_detail.setVisibility(View.VISIBLE);
                break;
            case 1:
                holder.tv_sellAllA_status.setText("待同意");
                holder.tv_sellAllA_result.setText("距离截止时间:");
                holder.tv_sellAllA_checkCommend.setVisibility(View.GONE);
                holder.tv_sellAllA_cancel.setVisibility(View.VISIBLE);
                holder.tv_sellAllA_accept.setVisibility(View.VISIBLE);
                holder.tv_sellAllA_detail.setVisibility(View.VISIBLE);
                break;
            case 2:
                holder.tv_sellAllA_status.setText("进行中");
                holder.tv_sellAllA_result.setVisibility(View.GONE);
                holder.tv_sellAllA_checkCommend.setVisibility(View.GONE);
                holder.tv_sellAllA_cancel.setVisibility(View.GONE);
                holder.tv_sellAllA_accept.setVisibility(View.GONE);
                holder.tv_sellAllA_detail.setVisibility(View.VISIBLE);
                break;
            case 3:
                holder.tv_sellAllA_status.setText("已完成");
                holder.tv_sellAllA_result.setText("订单已完成");
                holder.tv_sellAllA_checkCommend.setVisibility(View.VISIBLE);
                holder.tv_sellAllA_cancel.setVisibility(View.GONE);
                holder.tv_sellAllA_accept.setVisibility(View.GONE);
                holder.tv_sellAllA_detail.setVisibility(View.VISIBLE);
                break;

        }
        holder.tv_sellAllA_time.setText(sellOrderBean.getOrder_expect_time());
        holder.tv_sellAllA_foodName.setText(sellOrderBean.getOrder_title());
        holder.tv_sellAllA_price.setText(sellOrderBean.getOrder_price() + "");
        holder.tv_sellAllA_orderId.setText(sellOrderBean.getOrder_id() + "");
        if (sellOrderBean.getPic() != null) {
            ImageLoader.getInstance().displayImage(sellOrderBean.getPic(), holder.imv_sellAllA_pic);
        }
        holder.tv_sellAllA_detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                detialOnClick.onItemOnClick(i);
            }
        });
        holder.tv_sellAllA_accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                detialOnClick.onAcceptOnClick(i);
            }
        });
        holder.tv_sellAllA_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                detialOnClick.onCancelOnCkick(i);
            }
        });
        holder.tv_sellAllA_checkCommend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                detialOnClick.onSellAllComment(i);
            }
        });
        return view;
    }

    SellAAllDetailOnClickListener detialOnClick;

    public void onDetailOnclick(SellAAllDetailOnClickListener detialOnClick) {
        this.detialOnClick = detialOnClick;
    }

    public void setDataChange(ArrayList<SellOrderBean> sellAAlls) {
        this.sellOrderBeenList = sellAAlls;
        notifyDataSetChanged();
    }

    public interface SellAAllDetailOnClickListener {
        void onItemOnClick(int position);

        void onCancelOnCkick(int position);

        void onAcceptOnClick(int position);

        void onSellAllComment(int position);
    }


    class MyViewHolder {

        TextView tv_sellAllA_status;
        TextView tv_sellAllA_time;
        ImageView imv_sellAllA_pic;
        TextView tv_sellAllA_foodName;
        TextView tv_sellAllA_price;
        TextView tv_sellAllA_orderId;
        TextView tv_sellAllA_result;
        TextView tv_sellAllA_checkCommend;
        TextView tv_sellAllA_accept;
        TextView tv_sellAllA_cancel;
        TextView tv_sellAllA_detail;
    }
}
