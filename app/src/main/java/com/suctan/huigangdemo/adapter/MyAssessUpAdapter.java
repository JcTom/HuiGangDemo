package com.suctan.huigangdemo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.androidbase.LoadImageManager;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.suctan.huigangdemo.R;
import com.suctan.huigangdemo.bean.user.MyAssessGoBean;
import com.suctan.huigangdemo.bean.user.MyAssessUpBean;

import java.util.ArrayList;

/**
 * Created by Tom on 2017/4/16.
 */

public class MyAssessUpAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<MyAssessUpBean> myAssessUpList;

    public MyAssessUpAdapter(Context context,ArrayList<MyAssessUpBean> myAssessUpList){
        this.context = context;
        this.myAssessUpList = myAssessUpList;
    }

    @Override
    public int getCount() {
        return myAssessUpList.size();
    }

    @Override
    public Object getItem(int position) {
        return myAssessUpList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        GridViewHolder holder = null;
        MyAssessUpBean myAssessUpBean = myAssessUpList.get(position);
        if (convertView==null){
            convertView= LayoutInflater.from(context).inflate(R.layout.my_assess_up_item,parent,false);
            holder = new GridViewHolder();
            holder.I_have_received_Up_hand = (ImageView) convertView.findViewById(R.id.I_have_received_Up_hand);
            holder.I_have_received_Up_Image = (ImageView) convertView.findViewById(R.id.I_have_received_Up_Image);
            holder.I_have_received_Up_Text = (TextView) convertView.findViewById(R.id.I_have_received_Up_Text);

            convertView.setTag(holder);
        }else {
            holder = (GridViewHolder) convertView.getTag();
        }if (myAssessUpBean.getImage_up_imageUrl()!=null){
            LoadImageManager.getImageLoader().displayImage(myAssessUpBean.getImage_up_imageUrl(),holder.I_have_received_Up_Image);
            LoadImageManager.getImageLoader().displayImage(myAssessUpBean.getImage_up_evaluate_User(),holder.I_have_received_Up_hand);
        }

        return convertView;
    }

    /*我收到的评价*/
    public class GridViewHolder{
        ImageView I_have_received_Up_Image;//菜品图片
        ImageView I_have_received_Up_hand;//用户头像
        TextView I_have_received_Up_Text;//用户评论
    }
}
