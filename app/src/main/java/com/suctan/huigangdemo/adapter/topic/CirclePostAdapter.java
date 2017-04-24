package com.suctan.huigangdemo.adapter.topic;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.androidbase.BaseApplication;
import com.example.androidbase.LoadImageManager;
import com.example.androidbase.widget.CircleImageView;
import com.sqk.emojirelease.EmojiUtil;
import com.suctan.huigangdemo.R;
import com.suctan.huigangdemo.activity.circle.CirclePostDetails;
import com.suctan.huigangdemo.bean.topic.TopicCommentBean;

import java.io.IOException;
import java.util.ArrayList;

public class CirclePostAdapter extends RecyclerView.Adapter<CirclePostAdapter.MyViewHolder> {
    private Context context;
    private ArrayList<TopicCommentBean> topicCommentList;

    public CirclePostAdapter(Context context, ArrayList<TopicCommentBean> topicCommentList) {
        this.context = context;
        this.topicCommentList = topicCommentList;
    }

    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.post_reply_item, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }


    public void onBindViewHolder(MyViewHolder holder, int position) {
        TopicCommentBean topicCommentBean = topicCommentList.get(position);
        MyViewHolder holder1 = holder;

        holder1.tv_comment_time.setText(topicCommentBean.getComment_time());
        holder1.tv_comemnt_username.setText(topicCommentBean.getUser_name());
        try {
            EmojiUtil.handlerEmojiText(holder1.tv_comment_content, topicCommentBean.getContent(),context);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (topicCommentBean.getUser_icon() != null) {
            LoadImageManager.getImageLoader().displayImage(topicCommentBean.getUser_icon(), holder1.recycleImv_comment_userHead);
        }
    }

    public int getItemCount() {
        return topicCommentList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv_comment_content, tv_comemnt_username, tv_comment_time;
        ImageButton imvb_comment_delete;
        CircleImageView recycleImv_comment_userHead;

        public MyViewHolder(View view) {
            super(view);
            imvb_comment_delete = (ImageButton) view.findViewById(R.id.imvb_comment_delete);
            tv_comment_time = (TextView) view.findViewById(R.id.tv_comment_time);
            tv_comemnt_username = (TextView) view.findViewById(R.id.tv_comemnt_username);
            tv_comment_content = (TextView) view.findViewById(R.id.tv_comment_content);
            recycleImv_comment_userHead = (CircleImageView) view.findViewById(R.id.recycleImv_comment_userHead);


        }
    }


}
