package com.suctan.huigangdemo.adapter.order.buyorder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.suctan.huigangdemo.R;
import com.suctan.huigangdemo.bean.order.buy.BuyRecommendBean;

import java.util.ArrayList;

/**
 * Created by 黄淑翰 on 2017/4/26.
 */
public class FinishMakeOrederAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<BuyRecommendBean> AllBuyRecommendList;

    public FinishMakeOrederAdapter(Context context, ArrayList<BuyRecommendBean> AllBuyRecommendList) {
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
            view = LayoutInflater.from(context).inflate(R.layout.makebuy_finish_list_item, null, false);
            holder = new MyViewHolder();

            holder.imv_makebuyF_pic = (ImageView) view.findViewById(R.id.imv_makebuyF_pic);
            holder.tv_makebuyF_time = (TextView) view.findViewById(R.id.tv_makebuyF_time);
            holder.tv_makebuyF_foodname = (TextView) view.findViewById(R.id.tv_makebuyF_foodname);
            holder.tv_makebuyF_price = (TextView) view.findViewById(R.id.tv_makebuyF_price);
            holder.tv_makebuyF_orderId = (TextView) view.findViewById(R.id.tv_makebuyF_orderId);
            holder.tv_makebuyF_checkCommend = (TextView) view.findViewById(R.id.tv_makebuyF_checkCommend);
            holder.tv_makebuyF_detail = (TextView) view.findViewById(R.id.tv_makebuyF_detail);
            view.setTag(holder);
        } else {
            holder = (MyViewHolder) view.getTag();
        }
        holder.tv_makebuyF_foodname.setText(buyRecommendBean.getOrder_title());
        holder.tv_makebuyF_time.setText(buyRecommendBean.getOrder_expect_time());
        holder.tv_makebuyF_price.setText(buyRecommendBean.getOrder_price() + "");
        holder.tv_makebuyF_orderId.setText(buyRecommendBean.getOrder_id() + "");
        return view;
    }

    class MyViewHolder {
        TextView tv_makebuyF_time;
        ImageView imv_makebuyF_pic;
        TextView tv_makebuyF_foodname;
        TextView tv_makebuyF_price;
        TextView tv_makebuyF_orderId;
        TextView tv_makebuyF_checkCommend;
        TextView tv_makebuyF_detail;
    }

}
