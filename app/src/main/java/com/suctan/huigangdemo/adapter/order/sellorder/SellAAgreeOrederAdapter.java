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
public class SellAAgreeOrederAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<SellOrderBean> sellOrderBeenList;

    public SellAAgreeOrederAdapter(Context context, ArrayList<SellOrderBean> sellOrderBeenList) {
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
            view = LayoutInflater.from(context).inflate(R.layout.sella_agress_list_item, null, false);
            holder = new MyViewHolder();

            holder.imv_sellAllA_pic = (ImageView) view.findViewById(R.id.imv_sellAllA_pic);
            holder.tv_sellAgreeA_time = (TextView) view.findViewById(R.id.tv_sellAgreeA_time);
            holder.tv_sellAgreeA_foodName = (TextView) view.findViewById(R.id.tv_sellAgreeA_foodName);
            holder.tv_sellAgreeA_price = (TextView) view.findViewById(R.id.tv_sellAgreeA_price);
            holder.tv_sellAgreeA_orderId = (TextView) view.findViewById(R.id.tv_sellAgreeA_orderId);
            holder.tv_sellAgreeA_expecttime = (TextView) view.findViewById(R.id.tv_sellAgreeA_expecttime);
            holder.tv_sellAgreeA_cancel = (TextView) view.findViewById(R.id.tv_sellAgreeA_cancel);
            holder.tv_sellAgreeA_detail = (TextView) view.findViewById(R.id.tv_sellAgreeA_detail);
            holder.tv_sellAgreeA_accept = (TextView) view.findViewById(R.id.tv_sellAgreeA_accept);

            view.setTag(holder);
        } else {
            holder = (MyViewHolder) view.getTag();
        }
        holder.tv_sellAgreeA_foodName.setText(sellOrderBean.getOrder_title());
        holder.tv_sellAgreeA_time.setText(sellOrderBean.getOrder_expect_time());
        holder.tv_sellAgreeA_price.setText(sellOrderBean.getOrder_price() + "");
        holder.tv_sellAgreeA_orderId.setText(sellOrderBean.getOrder_id() + "");
        holder.tv_sellAgreeA_expecttime.setText(sellOrderBean.getOrder_res_time());
        if (sellOrderBean.getPic() != null) {
            ImageLoader.getInstance().displayImage(sellOrderBean.getPic(), holder.imv_sellAllA_pic);
        }
        holder.tv_sellAgreeA_detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                detialOnClick.onItemOnClick(i);
            }
        });
        holder.tv_sellAgreeA_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                detialOnClick.onCancelOnCkick(i);
            }
        });
        holder.tv_sellAgreeA_accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                detialOnClick.onAcceptOnClick(i);
            }
        });
        return view;
    }


    SellAAgreeDetailOnClickListener detialOnClick;

    public void onDetailOnclick(SellAAgreeDetailOnClickListener detialOnClick) {
        this.detialOnClick = detialOnClick;
    }

    public void setDataChange(ArrayList<SellOrderBean> sellAAgrees) {
        this.sellOrderBeenList = sellAAgrees;
        notifyDataSetChanged();
    }

    public interface SellAAgreeDetailOnClickListener {
        void onItemOnClick(int position);

        void onCancelOnCkick(int position);

        void onAcceptOnClick(int position);
    }

    class MyViewHolder {
        TextView tv_sellAgreeA_expecttime;
        TextView tv_sellAgreeA_time;
        ImageView imv_sellAllA_pic;
        TextView tv_sellAgreeA_foodName;
        TextView tv_sellAgreeA_price;
        TextView tv_sellAgreeA_orderId;
        TextView tv_sellAgreeA_accept;
        TextView tv_sellAgreeA_cancel;
        TextView tv_sellAgreeA_detail;


    }

}
