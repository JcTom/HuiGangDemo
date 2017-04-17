package com.suctan.huigangdemo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.androidbase.LoadImageManager;
import com.suctan.huigangdemo.R;
import com.suctan.huigangdemo.bean.user.MyAssessGoBean;
import java.util.ArrayList;
/**
 * Created by Tom on 2017/4/16.
 */

public class MyAssessGoAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<MyAssessGoBean> myAssessGoList;

    public MyAssessGoAdapter(Context context,ArrayList<MyAssessGoBean> myAssessGoList){
        this.context=context;
        this.myAssessGoList=myAssessGoList;
    }

    @Override
    public int getCount() {
        return myAssessGoList.size();
    }

    @Override
    public Object getItem(int position) {
        return myAssessGoList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        GridViewHolder holder = null;
        MyAssessGoBean myAssessGoBean=myAssessGoList.get(position);
        if(convertView==null){
            convertView= LayoutInflater.from(context).inflate(R.layout.my_assess_go_item,parent,false);
            holder = new GridViewHolder();
            holder.myAssessGo_ImageView= (ImageView) convertView.findViewById(R.id.myAssessGo_ImageView);
            holder.evaluate_hand_Image = (ImageView) convertView.findViewById(R.id.evaluate_hand_Image);
            //holder.evaluate_star = (ImageView) convertView.findViewById(R.id.evaluate_star);
            holder.evaluate_user = (TextView) convertView.findViewById(R.id.evaluate_user);
            holder.evaluate_Text = (TextView) convertView.findViewById(R.id.evaluate_Text);
            holder.ItemText= (TextView) convertView.findViewById(R.id.myAssessGo_TextView);

            convertView.setTag(holder);
        }
        else{
            holder = (GridViewHolder) convertView.getTag();}
        if(myAssessGoBean.getImageUrl()!=null){
            LoadImageManager.getImageLoader().displayImage(myAssessGoBean.getImageUrl(),holder.myAssessGo_ImageView);
            LoadImageManager.getImageLoader().displayImage(myAssessGoBean.getImage_evaluate_User(),holder.evaluate_hand_Image);
        }
        return convertView;
    }
    /*我得评价页面*/
    public class GridViewHolder{
        ImageView myAssessGo_ImageView;//菜品图片
        ImageView evaluate_hand_Image;//用户头像
        ImageView evaluate_star;//评星
        TextView evaluate_user;//用户名字
        TextView evaluate_Text;//评价内容
        TextView ItemText;//菜名
    }
}
