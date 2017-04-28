package com.suctan.huigangdemo.adapter.topic;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.androidbase.BaseApplication;
import com.example.androidbase.LoadImageManager;
import com.example.androidbase.widget.CircleImageView;
import com.suctan.huigangdemo.R;
import com.suctan.huigangdemo.bean.topic.TopicBean;

import java.util.ArrayList;

public class TopicRecycleAdapter extends RecyclerView.Adapter<TopicRecycleAdapter.MyViewHolder> {
    private Context context;
    private ArrayList<TopicBean> topicBeenList;


    public TopicRecycleAdapter(Context context, ArrayList<TopicBean> topicBeenList) {
        this.context = context;
        this.topicBeenList = topicBeenList;
    }

    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(
                context).inflate(R.layout.circle_list, parent,
                false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    public void onBindViewHolder(MyViewHolder holder, final int position) {
        TopicBean mtoPicBean = topicBeenList.get(position);

        MyViewHolder myViewHolder = holder;
        myViewHolder.item_name.setText(mtoPicBean.getUser_name());
        myViewHolder.item_time.setText(mtoPicBean.getPub_topic_time());
        myViewHolder.item_title.setText(mtoPicBean.getTopic_title());
        myViewHolder.item_reply.setText(mtoPicBean.getComment_num()+"");
        if(mtoPicBean.getUser_icon()!=null){
            LoadImageManager.getImageLoader().displayImage(mtoPicBean.getUser_icon(),myViewHolder.item_imv);
        }

        myViewHolder.ly_topic_arroud.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Lisetner.onClickTopicItem(position);
            }
        });
    }

    public int getItemCount() {
        return topicBeenList.size();
    }

    private OnTopicListenter Lisetner;

    public void setOnClickTopicListner(OnTopicListenter Lisetner) {
        this.Lisetner = Lisetner;
    }

   public interface OnTopicListenter {
        void onClickTopicItem(int position);
    }


    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView item_name, item_time, item_title, item_reply;
        CircleImageView item_imv;
        LinearLayout ly_topic_arroud;

        public MyViewHolder(View view) {
            super(view);
            item_name = (TextView) view.findViewById(R.id.item_name);
            item_time = (TextView) view.findViewById(R.id.item_time);
            item_title = (TextView) view.findViewById(R.id.item_title);
            item_reply = (TextView) view.findViewById(R.id.item_reply);
            item_imv = (CircleImageView) view.findViewById(R.id.item_tx);
            ly_topic_arroud = (LinearLayout) view.findViewById(R.id.ly_topic_arroud);
        }
    }
}