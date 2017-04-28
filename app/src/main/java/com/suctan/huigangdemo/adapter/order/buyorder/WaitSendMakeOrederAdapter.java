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
public class WaitSendMakeOrederAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<BuyRecommendBean> AllBuyRecommendList;

    public WaitSendMakeOrederAdapter(Context context, ArrayList<BuyRecommendBean> AllBuyRecommendList) {
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
            view = LayoutInflater.from(context).inflate(R.layout.makebuyorder_waitsend_list_item, null, false);
            holder = new MyViewHolder();

            holder.imv_makebuyWS_pic = (ImageView) view.findViewById(R.id.imv_makebuyWS_pic);
            holder.tv_makebuyWS_time = (TextView) view.findViewById(R.id.tv_makebuyWS_time);
            holder.tv_makebuyWS_foodname = (TextView) view.findViewById(R.id.tv_makebuyWS_foodname);
            holder.tv_makebuyWS_price = (TextView) view.findViewById(R.id.tv_makebuyWS_price);
            holder.tv_makebuyWS_orderId = (TextView) view.findViewById(R.id.tv_makebuyWS_orderId);
            holder.tv_makebuyWS_comfirm = (TextView) view.findViewById(R.id.tv_makebuyWS_comfirm);
            holder.tv_makebuyWS_detail = (TextView) view.findViewById(R.id.tv_makebuyWS_detail);
            view.setTag(holder);
        } else {
            holder = (MyViewHolder) view.getTag();
        }
        holder.tv_makebuyWS_foodname.setText(buyRecommendBean.getOrder_title());
        holder.tv_makebuyWS_time.setText(buyRecommendBean.getOrder_expect_time());
        holder.tv_makebuyWS_price.setText(buyRecommendBean.getOrder_price() + "");
        holder.tv_makebuyWS_orderId.setText(buyRecommendBean.getOrder_id() + "");
        if (buyRecommendBean.getPic() != null) {
            ImageLoader.getInstance().displayImage(buyRecommendBean.getPic(), holder.imv_makebuyWS_pic);
        }
        holder.tv_makebuyWS_detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                detialOnClick.onItemOnClick(i);

            }
        });
        holder.tv_makebuyWS_detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                detialOnClick.onComfirmAOnclick(i);
            }
        });
        return view;
    }


    WaitSendMakeDetailOnClickListener detialOnClick;

    public void onDetailOnclick(WaitSendMakeDetailOnClickListener detialOnClick) {
        this.detialOnClick = detialOnClick;
    }

    public void setDataChange(ArrayList<BuyRecommendBean> makeWaitSendLists) {
        this.AllBuyRecommendList = makeWaitSendLists;
        notifyDataSetChanged();
    }

    public interface WaitSendMakeDetailOnClickListener {
        void onItemOnClick(int position);

        void onComfirmAOnclick(int position);
    }

    class MyViewHolder {

        TextView tv_makebuyWS_time;
        ImageView imv_makebuyWS_pic;
        TextView tv_makebuyWS_foodname;
        TextView tv_makebuyWS_price;
        TextView tv_makebuyWS_orderId;
        TextView tv_makebuyWS_comfirm;
        TextView tv_makebuyWS_detail;
    }

}
