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
public class SellAFinishOrederAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<SellOrderBean> sellOrderBeenList;

    public SellAFinishOrederAdapter(Context context, ArrayList<SellOrderBean> sellOrderBeenList) {
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
            view = LayoutInflater.from(context).inflate(R.layout.sella_finishorder_list_item, null, false);
            holder = new MyViewHolder();

            holder.imv_sellfinshA_pic = (ImageView) view.findViewById(R.id.imv_sellfinshA_pic);
            holder.tv_sellfinshA_time = (TextView) view.findViewById(R.id.tv_sellfinshA_time);
            holder.tv_sellfinshA_foodname = (TextView) view.findViewById(R.id.tv_sellfinshA_foodname);
            holder.tv_sellfinshA_price = (TextView) view.findViewById(R.id.tv_sellfinshA_price);
            holder.tv_sellfinshA_orderId = (TextView) view.findViewById(R.id.tv_sellfinshA_orderId);
            holder.tv_sellfinshA_checkCommend = (TextView) view.findViewById(R.id.tv_sellfinshA_checkCommend);
            holder.tv_sellfinshA_detail = (TextView) view.findViewById(R.id.tv_sellfinshA_detail);
            view.setTag(holder);
        } else {
            holder = (MyViewHolder) view.getTag();
        }
        holder.tv_sellfinshA_foodname.setText(sellOrderBean.getOrder_title());
        holder.tv_sellfinshA_time.setText(sellOrderBean.getOrder_expect_time());
        holder.tv_sellfinshA_price.setText(sellOrderBean.getOrder_price() + "");
        holder.tv_sellfinshA_orderId.setText(sellOrderBean.getOrder_id() + "");

        if (sellOrderBean.getPic() != null) {
            ImageLoader.getInstance().displayImage(sellOrderBean.getPic(), holder.imv_sellfinshA_pic);
        }
        holder.tv_sellfinshA_detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                detialOnClick.onItemOnClick(i);
            }
        });
        holder.tv_sellfinshA_checkCommend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                detialOnClick.onSellAllComments(i);
            }
        });
        return view;
    }

    SellAFinishDetailOnClickListener detialOnClick;

    public void onDetailOnclick(SellAFinishDetailOnClickListener detialOnClick) {
        this.detialOnClick = detialOnClick;

    }

    public interface SellAFinishDetailOnClickListener {
        void onItemOnClick(int position);

        void onSellAllComments(int position);
    }

    class MyViewHolder {
        ImageView imv_sellfinshA_pic;
        TextView tv_sellfinshA_time;
        TextView tv_sellfinshA_foodname;
        TextView tv_sellfinshA_price;
        TextView tv_sellfinshA_orderId;
        TextView tv_sellfinshA_checkCommend;
        TextView tv_sellfinshA_detail;
    }

}
