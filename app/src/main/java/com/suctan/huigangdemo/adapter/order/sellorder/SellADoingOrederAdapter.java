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
public class SellADoingOrederAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<SellOrderBean> sellOrderBeenList;

    public SellADoingOrederAdapter(Context context, ArrayList<SellOrderBean> sellOrderBeenList) {
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
            view = LayoutInflater.from(context).inflate(R.layout.sella_donging_list_item, null, false);
            holder = new MyViewHolder();

            holder.imv_selldoingA_pic = (ImageView) view.findViewById(R.id.imv_selldoingA_pic);
            holder.tv_selldoingA_time = (TextView) view.findViewById(R.id.tv_selldoingA_time);
            holder.tv_selldoingA_foodname = (TextView) view.findViewById(R.id.tv_selldoingA_foodname);
            holder.tv_selldoingA_price = (TextView) view.findViewById(R.id.tv_selldoingA_price);
            holder.tv_selldoingA_orderId = (TextView) view.findViewById(R.id.tv_selldoingA_orderId);
            holder.tv_selldoingA_detail = (TextView) view.findViewById(R.id.tv_selldoingA_detail);
            view.setTag(holder);
        } else {
            holder = (MyViewHolder) view.getTag();
        }
        holder.tv_selldoingA_foodname.setText(sellOrderBean.getOrder_title());
        holder.tv_selldoingA_time.setText(sellOrderBean.getOrder_expect_time());
        holder.tv_selldoingA_price.setText(sellOrderBean.getOrder_price() + "");
        holder.tv_selldoingA_orderId.setText(sellOrderBean.getOrder_id() + "");
        if (sellOrderBean.getPic() != null) {
            ImageLoader.getInstance().displayImage(sellOrderBean.getPic(), holder.imv_selldoingA_pic);
        }

        holder.tv_selldoingA_detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                detialOnClick.onItemOnClick(i);
            }
        });
        return view;
    }

    SellADoingDetailOnClickListener detialOnClick;

    public void onDetailOnclick(SellADoingDetailOnClickListener detialOnClick) {
        this.detialOnClick = detialOnClick;
    }

    public void setDataChage(ArrayList<SellOrderBean> sellADoings) {
        this.sellOrderBeenList = sellADoings;
        notifyDataSetChanged();
    }

    public interface SellADoingDetailOnClickListener {
        void onItemOnClick(int position);

    }


    class MyViewHolder {
        TextView tv_selldoingA_time;
        ImageView imv_selldoingA_pic;
        TextView tv_selldoingA_foodname;
        TextView tv_selldoingA_price;
        TextView tv_selldoingA_orderId;
        TextView tv_selldoingA_detail;
    }

}
