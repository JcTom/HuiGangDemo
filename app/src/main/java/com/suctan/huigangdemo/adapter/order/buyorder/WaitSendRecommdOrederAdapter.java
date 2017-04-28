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
public class WaitSendRecommdOrederAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<BuyRecommendBean> AllBuyRecommendList;

    public WaitSendRecommdOrederAdapter(Context context, ArrayList<BuyRecommendBean> AllBuyRecommendList) {
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
    public View getView(final int i, View view, ViewGroup viewGroup) {
        MyViewHolder holder = null;
        BuyRecommendBean buyRecommendBean = AllBuyRecommendList.get(i);
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.recommend_waitsendorder_list_item, null, false);
            holder = new MyViewHolder();
            holder.tv_remdWS_time = (TextView) view.findViewById(R.id.tv_remdWS_time);
            holder.tv_remdWS_foodname = (TextView) view.findViewById(R.id.tv_remdWS_foodname);
            holder.tv_remdWS_price = (TextView) view.findViewById(R.id.tv_remdWS_price);
            holder.tv_remdWS_orderId = (TextView) view.findViewById(R.id.tv_remdWS_orderId);
            holder.tv_remdWS_comfirm = (TextView) view.findViewById(R.id.tv_remdWS_comfirm);
            holder.tv_remdWS_detail = (TextView) view.findViewById(R.id.tv_remdWS_detail);
            view.setTag(holder);
        } else {
            holder = (MyViewHolder) view.getTag();
        }
        holder.tv_remdWS_foodname.setText(buyRecommendBean.getOrder_title());
        holder.tv_remdWS_time.setText(buyRecommendBean.getOrder_expect_time());
        holder.tv_remdWS_price.setText(buyRecommendBean.getOrder_price() + "");
        holder.tv_remdWS_orderId.setText(buyRecommendBean.getOrder_id() + "");
        holder.tv_remdWS_detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                detialOnClick.onItemOnClick(i);
            }
        });
        holder.tv_remdWS_comfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                detialOnClick.onComfrimOnclick(i);
            }
        });
        return view;
    }

    WaitSendDetailOnClickListener detialOnClick;

    public void onDetailOnclick(WaitSendDetailOnClickListener detialOnClick) {
        this.detialOnClick = detialOnClick;
    }

    public void setDataChange(ArrayList<BuyRecommendBean> AllBuyRecommendList) {
        this.AllBuyRecommendList = AllBuyRecommendList;
        notifyDataSetChanged();
    }

    public interface WaitSendDetailOnClickListener {
        void onItemOnClick(int position);

        void onComfrimOnclick(int position);
    }

    class MyViewHolder {

        TextView tv_remdWS_time;
        TextView tv_remdWS_foodname;
        TextView tv_remdWS_price;
        TextView tv_remdWS_orderId;
        TextView tv_remdWS_comfirm;
        TextView tv_remdWS_detail;


    }

}
