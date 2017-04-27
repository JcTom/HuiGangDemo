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
public class AllRecommdOrederAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<BuyRecommendBean> AllBuyRecommendList;

    public AllRecommdOrederAdapter(Context context, ArrayList<BuyRecommendBean> AllBuyRecommendList) {
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
            view = LayoutInflater.from(context).inflate(R.layout.recommend_allfood_list_item, null, false);
            holder = new MyViewHolder();

            holder.tv_remdAll_order_status = (TextView) view.findViewById(R.id.tv_remdAll_order_status);
            holder.tv_remdAll_time = (TextView) view.findViewById(R.id.tv_remdAll_time);
            holder.tv_remdAll_foodname = (TextView) view.findViewById(R.id.tv_remdAll_foodname);
            holder.tv_remdAll_foodprice = (TextView) view.findViewById(R.id.tv_remdAll_foodprice);
            holder.tv_remdAll_orderNumber = (TextView) view.findViewById(R.id.tv_remdAll_orderNumber);
            holder.tv_remdAll_orderReuslt = (TextView) view.findViewById(R.id.tv_remdAll_orderReuslt);
            holder.tv_remdAll_checkcommend = (TextView) view.findViewById(R.id.tv_remdAll_checkcommend);
            holder.tv_remdAll_cancel_order = (TextView) view.findViewById(R.id.tv_remdAll_cancel_order);
            holder.tv_remdAll_comfirm_food = (TextView) view.findViewById(R.id.tv_remdAll_comfirm_food);
            holder.tv_remdAll_checkdetial = (TextView) view.findViewById(R.id.tv_remdAll_checkdetial);
            view.setTag(holder);
        } else {
            holder = (MyViewHolder) view.getTag();
        }
        switch (buyRecommendBean.getOrder_status()) {
            case 0:
                holder.tv_remdAll_order_status.setText("已取消");
                holder.tv_remdAll_orderReuslt.setVisibility(View.GONE);
                holder.tv_remdAll_checkcommend.setVisibility(View.GONE);
                holder.tv_remdAll_cancel_order.setVisibility(View.GONE);
                holder.tv_remdAll_comfirm_food.setVisibility(View.GONE);
                holder.tv_remdAll_checkdetial.setVisibility(View.VISIBLE);
                break;
            case 1:
                holder.tv_remdAll_order_status.setText("待接单");
                holder.tv_remdAll_orderReuslt.setText("距离截至接单时间:");
                holder.tv_remdAll_checkcommend.setVisibility(View.GONE);
                holder.tv_remdAll_cancel_order.setVisibility(View.VISIBLE);
                holder.tv_remdAll_comfirm_food.setVisibility(View.GONE);
                holder.tv_remdAll_checkdetial.setVisibility(View.VISIBLE);
                break;
            case 2:
                holder.tv_remdAll_order_status.setText("待送餐");
                holder.tv_remdAll_orderReuslt.setText("已受理");
                holder.tv_remdAll_checkcommend.setVisibility(View.GONE);
                holder.tv_remdAll_cancel_order.setVisibility(View.GONE);
                holder.tv_remdAll_comfirm_food.setVisibility(View.VISIBLE);
                holder.tv_remdAll_checkdetial.setVisibility(View.VISIBLE);
                break;
            case 3:
                holder.tv_remdAll_order_status.setText("已完成");
                holder.tv_remdAll_orderReuslt.setText("订单已完成");
                holder.tv_remdAll_checkcommend.setVisibility(View.VISIBLE);
                holder.tv_remdAll_cancel_order.setVisibility(View.GONE);
                holder.tv_remdAll_comfirm_food.setVisibility(View.GONE);
                holder.tv_remdAll_checkdetial.setVisibility(View.VISIBLE);
                break;

        }
        holder.tv_remdAll_time.setText(buyRecommendBean.getOrder_expect_time());
        holder.tv_remdAll_foodname.setText(buyRecommendBean.getOrder_title());
        holder.tv_remdAll_foodprice.setText(buyRecommendBean.getOrder_price() + "");
        holder.tv_remdAll_orderNumber.setText(buyRecommendBean.getOrder_id() + "");

        return view;
    }

    class MyViewHolder {
        TextView tv_remdAll_order_status;
        TextView tv_remdAll_time;
        TextView tv_remdAll_foodname;
        TextView tv_remdAll_foodprice;
        TextView tv_remdAll_orderNumber;
        TextView tv_remdAll_orderReuslt;
        TextView tv_remdAll_checkcommend;
        TextView tv_remdAll_cancel_order;
        TextView tv_remdAll_comfirm_food;
        TextView tv_remdAll_checkdetial;
    }

}
