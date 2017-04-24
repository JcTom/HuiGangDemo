package com.suctan.huigangdemo.activity.circle;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.jaeger.library.StatusBarUtil;
import com.suctan.huigangdemo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by B-305 on 2017/4/13.
 */

public class MoreNotice extends AppCompatActivity {

    ImageButton noticeBack;
    Button noticeRelease;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.circle_notice);
        StatusBarUtil.setColor(this, getResources().getColor(R.color.colorPrimary), 0);
        initButton();

        mRecyclerView = (RecyclerView) findViewById(R.id.circle_notice);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter = new MoreNoticeAdapter());

        initData();
    }


    /*
    * 初始化返回、更多按钮
    * */
    private void initButton() {
        noticeBack = (ImageButton) findViewById(R.id.notice_back);
        noticeRelease = (Button) findViewById(R.id.notice_fb);

        noticeBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        noticeRelease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goNoticeRelease = new Intent(MoreNotice.this, PostRelease.class);
                goNoticeRelease.putExtra("isPic", false);
                startActivity(goNoticeRelease);
            }
        });

    }


    /*
    * 公告详情适配器
    * */
    private RecyclerView mRecyclerView;
    private List<String> mDatas;
    private MoreNoticeAdapter mAdapter;


    //初始化数据
    protected void initData() {
        mDatas = new ArrayList<String>();
        for (int i = 'A'; i < 'a'; i++) {
            mDatas.add("" + (char) i);
        }
    }

    class MoreNoticeAdapter extends RecyclerView.Adapter<MoreNoticeAdapter.MyViewHolder> {

        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            MyViewHolder holder = new MyViewHolder(LayoutInflater.from(
                    MoreNotice.this).inflate(R.layout.circle_notice_item, parent,
                    false));
            return holder;
        }


        public void onBindViewHolder(MyViewHolder holder, int position) {

            holder.notice_item_title.setText(mDatas.get(position));
            holder.notice_item_time.setText(mDatas.get(position));
            holder.notice_item_content.setText(mDatas.get(position));

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent goPostDetails = new Intent(MoreNotice.this, NoticeDetail.class);
                    startActivity(goPostDetails);
                }
            });


        }

        public int getItemCount() {
            return mDatas.size();
        }

        class MyViewHolder extends RecyclerView.ViewHolder {

            TextView notice_item_title, notice_item_time, notice_item_content;

            public MyViewHolder(View view) {
                super(view);
                notice_item_title = (TextView) view.findViewById(R.id.notice_item_title);
                notice_item_time = (TextView) view.findViewById(R.id.notice_item_time);
                notice_item_content = (TextView) view.findViewById(R.id.notice_item_content);
            }
        }


    }


}
