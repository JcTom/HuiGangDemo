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
public class FinishRecommdOrederAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<BuyRecommendBean> AllBuyRecommendList;

    public FinishRecommdOrederAdapter(Context context, ArrayList<BuyRecommendBean> AllBuyRecommendList) {
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
            view = LayoutInflater.from(context).inflate(R.layout.recommend_finishorderlist_item, null, false);
            holder = new MyViewHolder();
            holder.tv_remdf_time = (TextView) view.findViewById(R.id.tv_remdf_time);
            holder.tv_remdf_foodname = (TextView) view.findViewById(R.id.tv_remdf_foodname);
            holder.tv_remdf_price = (TextView) view.findViewById(R.id.tv_remdf_price);
            holder.tv_remdf_orderId = (TextView) view.findViewById(R.id.tv_remdf_orderId);
            holder.tv_remdf_checkCommend = (TextView) view.findViewById(R.id.tv_remdf_checkCommend);
            holder.tv_remdf_detail = (TextView) view.findViewById(R.id.tv_remdf_detail);
            view.setTag(holder);
        } else {
            holder = (MyViewHolder) view.getTag();
        }
        holder.tv_remdf_foodname.setText(buyRecommendBean.getOrder_title());
        holder.tv_remdf_time.setText(buyRecommendBean.getOrder_expect_time());
        holder.tv_remdf_price.setText(buyRecommendBean.getOrder_price() + "");
        holder.tv_remdf_orderId.setText(buyRecommendBean.getOrder_id() + "");
        holder.tv_remdf_checkCommend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                detialOnClick.onCheckComments(i);
            }
        });

        holder.tv_remdf_detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                detialOnClick.onItemOnClick(i);
            }
        });
        return view;
    }

    FinishDetailOnClickListener detialOnClick;

    public void onDetailOnclick(FinishDetailOnClickListener detialOnClick) {
        this.detialOnClick = detialOnClick;
    }

    public interface FinishDetailOnClickListener {
        void onItemOnClick(int position);

        void onCheckComments(int position);
    }

    class MyViewHolder {
        TextView tv_remdf_time;
        TextView tv_remdf_foodname;
        TextView tv_remdf_price;
        TextView tv_remdf_orderId;
        TextView tv_remdf_checkCommend;
        TextView tv_remdf_detail;
    }

}
