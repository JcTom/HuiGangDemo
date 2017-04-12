package com.suctan.huigangdemo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.suctan.huigangdemo.R;
import com.suctan.huigangdemo.bean.user.CompanyInfoBean;
import java.util.List;

/**
 * Created by Tom on 2017/4/12.
 */

public class IndexCompanyAdapter extends BaseAdapter {

    private LayoutInflater inflater;
    private List<CompanyInfoBean> data;

    private IndexCompanyAdapter(Context context, List<CompanyInfoBean> data){
        this.inflater = LayoutInflater.from(context);
        this.data = data;
    }

    public int getCount() {
        return data.size();
    }

    @Override
    public CompanyInfoBean getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHelper viewHelper;
        if (null==convertView){
           convertView = inflater.inflate(R.layout.fragment_index,parent,false);
            viewHelper = new ViewHelper();
            convertView = convertView.findViewById(R.id.rollViewpager);
            
        }
        return null;
    }

    static class ViewHelper{
        public TextView companyName;
        public ImageView companyIcon;
    }
}
