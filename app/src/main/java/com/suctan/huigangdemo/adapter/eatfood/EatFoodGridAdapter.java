package com.suctan.huigangdemo.adapter.eatfood;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.androidbase.BaseApplication;
import com.example.androidbase.LoadImageManager;
import com.example.androidbase.widget.CircleImageView;
import com.suctan.huigangdemo.R;
import com.suctan.huigangdemo.bean.wanteat.WantEatOtherComment;
import com.suctan.huigangdemo.widget.StarBar;

import java.util.ArrayList;

/**
 * Created by 黄淑翰 on 2017/4/25.
 */
public class EatFoodGridAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<WantEatOtherComment> wantEatOtherCommentList;

    public EatFoodGridAdapter(Context context, ArrayList<WantEatOtherComment> wantEatOtherCommentLists) {
        this.context = context;
        this.wantEatOtherCommentList = wantEatOtherCommentLists;
    }

    @Override
    public int getCount() {
        return wantEatOtherCommentList.size();
    }

    @Override
    public Object getItem(int i) {
        return wantEatOtherCommentList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        MyViewHolder holder = null;
        WantEatOtherComment wantEatOtherComment = wantEatOtherCommentList.get(i);
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.bottom_one_food_details_listitem, viewGroup, false);
            holder = new MyViewHolder();
            holder.circle_WEOC_userHead = (CircleImageView) view.findViewById(R.id.circle_WEOC_userHead);
            holder.tv_WEOC_userName = (TextView) view.findViewById(R.id.tv_WEOC_userName);
            holder.StarBar_WEOC_level = (StarBar) view.findViewById(R.id.StarBar_WEOC_level);
            holder.tv_WEOC_time = (TextView) view.findViewById(R.id.tv_WEOC_time);
            holder.tv_WEOC_content = (TextView) view.findViewById(R.id.tv_WEOC_content);
            holder.tv_WEOC_score = (TextView) view.findViewById(R.id.tv_WEOC_score);
            view.setTag(holder);
        } else {
            holder = (MyViewHolder) view.getTag();
        }

        if (wantEatOtherComment.getUser_icon() != null) {
            LoadImageManager.getImageLoader().displayImage(wantEatOtherComment.getUser_icon(), holder.circle_WEOC_userHead);
        }

        holder.tv_WEOC_userName.setText(wantEatOtherComment.getUser_name());
        holder.tv_WEOC_score.setText(wantEatOtherComment.getAverage() + ".0");
        holder.StarBar_WEOC_level.setCurrentChoose(wantEatOtherComment.getAverage());
        holder.tv_WEOC_time.setText(wantEatOtherComment.getComment_time());
        holder.tv_WEOC_content.setText(wantEatOtherComment.getComment_content());

        return view;
    }


    class MyViewHolder {

        CircleImageView circle_WEOC_userHead;
        TextView tv_WEOC_userName;
        StarBar StarBar_WEOC_level;
        TextView tv_WEOC_time;
        TextView tv_WEOC_content;
        TextView tv_WEOC_score;
    }
}
