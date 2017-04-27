package com.suctan.huigangdemo.adapter.address;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.androidbase.LoadImageManager;
import com.suctan.huigangdemo.R;
import com.suctan.huigangdemo.bean.address.AddressBean;
import com.suctan.huigangdemo.bean.user.IndexGridBean;

import java.util.ArrayList;

/**
 * Created by Tom on 2017/4/13.
 */
public class AddressListAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<AddressBean> addressBeenList;

    public AddressListAdapter(Context context, ArrayList<AddressBean> addressBeenList) {
        this.context = context;
        this.addressBeenList = addressBeenList;
    }

    @Override
    public int getCount() {
        return addressBeenList.size();
    }

    @Override
    public Object getItem(int position) {
        return addressBeenList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MyViewHolder holder = null;
        AddressBean addressBean = addressBeenList.get(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.address_manager_listview, parent, false);
            holder = new MyViewHolder();
            holder.tv_address_username = (TextView) convertView.findViewById(R.id.tv_address_username);
            holder.tv_address_userPhone = (TextView) convertView.findViewById(R.id.tv_address_userPhone);
            holder.tv_address_area = (TextView) convertView.findViewById(R.id.tv_address_area);
            holder.tv_address_community = (TextView) convertView.findViewById(R.id.tv_address_community);
            holder.tv_address_detail = (TextView) convertView.findViewById(R.id.tv_address_detail);
            holder.tv_inorge_statu = (TextView) convertView.findViewById(R.id.tv_inorge_statu);
            convertView.setTag(holder);
        } else {
            holder = (MyViewHolder) convertView.getTag();
        }
        holder.tv_address_username.setText(addressBean.getName());
        holder.tv_address_userPhone.setText(addressBean.getPhone());
        holder.tv_address_area.setText(addressBean.getArea());
        holder.tv_address_community.setText(addressBean.getCommunity());
        holder.tv_address_detail.setText(addressBean.getAddress());
        if (addressBean.getStatus() == 1) {
            holder.tv_inorge_statu.setVisibility(View.VISIBLE);
        } else {
            holder.tv_inorge_statu.setVisibility(View.GONE);
        }
        return convertView;
    }

    public class MyViewHolder {
        RadioButton rdb_address;
        TextView tv_address_username;
        TextView tv_address_userPhone;
        TextView tv_address_area;
        TextView tv_address_community;
        TextView tv_address_detail;
        TextView tv_inorge_statu;
    }
}
