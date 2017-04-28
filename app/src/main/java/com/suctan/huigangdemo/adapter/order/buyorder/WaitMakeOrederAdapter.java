package com.suctan.huigangdemo.adapter.order.buyorder;

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

import java.util.ArrayList;

/**
 * Created by 黄淑翰 on 2017/4/26.
 */
public class WaitMakeOrederAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<BuyRecommendBean> AllBuyRecommendList;

    public WaitMakeOrederAdapter(Context context, ArrayList<BuyRecommendBean> AllBuyRecommendList) {
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
            view = LayoutInflater.from(context).inflate(R.layout.makebuyorders_wait_list_item, null, false);
            holder = new MyViewHolder();
            holder.imv_makebuyW_pic = (ImageView) view.findViewById(R.id.imv_makebuyW_pic);
            holder.tv_makebuyW_time = (TextView) view.findViewById(R.id.tv_makebuyW_time);
            holder.tv_makebuyW_foodname = (TextView) view.findViewById(R.id.tv_makebuyW_foodname);
            holder.tv_makebuyW_price = (TextView) view.findViewById(R.id.tv_makebuyW_price);
            holder.tv_makebuyW_orderId = (TextView) view.findViewById(R.id.tv_makebuyW_orderId);
            holder.tv_makebuyW_expecttime = (TextView) view.findViewById(R.id.tv_makebuyW_expecttime);
            holder.tv_makebuyW_cancel = (TextView) view.findViewById(R.id.tv_makebuyW_cancel);
            holder.tv_makebuyW_detial = (TextView) view.findViewById(R.id.tv_makebuyW_detial);
            view.setTag(holder);
        } else {
            holder = (MyViewHolder) view.getTag();
        }
        holder.tv_makebuyW_foodname.setText(buyRecommendBean.getOrder_title());
        holder.tv_makebuyW_time.setText(buyRecommendBean.getOrder_expect_time());
        holder.tv_makebuyW_price.setText(buyRecommendBean.getOrder_price() + "");
        holder.tv_makebuyW_orderId.setText(buyRecommendBean.getOrder_id() + "");
        holder.tv_makebuyW_expecttime.setText(buyRecommendBean.getOrder_res_time());
        if (buyRecommendBean.getPic() != null) {
            ImageLoader.getInstance().displayImage(buyRecommendBean.getPic(), holder.imv_makebuyW_pic);
        }
        holder.tv_makebuyW_detial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                detialOnClick.onItemOnClick(i);
            }
        });
        return view;
    }


    WaitMakeDetailOnClickListener detialOnClick;

    public void onDetailOnclick(WaitMakeDetailOnClickListener detialOnClick) {
        this.detialOnClick = detialOnClick;
    }

    public interface WaitMakeDetailOnClickListener {
        void onItemOnClick(int position);

    }

    class MyViewHolder {

        TextView tv_makebuyW_time;
        ImageView imv_makebuyW_pic;
        TextView tv_makebuyW_foodname;
        TextView tv_makebuyW_price;
        TextView tv_makebuyW_orderId;
        TextView tv_makebuyW_expecttime;
        TextView tv_makebuyW_cancel;
        TextView tv_makebuyW_detial;


    }

}
