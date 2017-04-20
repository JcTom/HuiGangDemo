package com.suctan.huigangdemo.activity.share;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.androidbase.widget.CircleImageView;
import com.suctan.huigangdemo.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.suctan.huigangdemo.R.id.login_title;

/**
 * Created by B-305 on 2017/4/13.
 */

public class ServiceList extends AppCompatActivity {

    @BindView(R.id.login_back)
    ImageView loginBack;
    @BindView(login_title)
    TextView loginTitle;
    @BindView(R.id.search)
    ImageView search;
    @BindView(R.id.add_post)
    Button serviceFb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.service_list);
        ButterKnife.bind(this);

        initView();


        mRecyclerView = (RecyclerView) findViewById(R.id.service_list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter = new ServiceListAdapter());
        initData();

    }

    /*
    * 初始化标题栏
    * */
    private void initView() {
        search.setVisibility(View.GONE);
        serviceFb.setVisibility(View.VISIBLE);



        Intent it = getIntent();
        final String serviceType = it.getStringExtra("serviceType");
        final int needType = it.getIntExtra("needType",0);

        loginTitle.setText(serviceType);
        loginBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        serviceFb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goReleaseService = new Intent(ServiceList.this, ReleaseService.class);
                goReleaseService.putExtra("serviceType",serviceType);
                goReleaseService.putExtra("needType",needType);
                startActivity(goReleaseService);
            }
        });
    }



    private RecyclerView mRecyclerView;
    private List<String> mDatas;
    private ServiceListAdapter mAdapter;

    //初始化数据
    protected void initData()
    {
        mDatas = new ArrayList<String>();
        for (int i = 'A'; i < 'z'; i++)
        {
            mDatas.add("" + (char) i);
        }


    }

    class ServiceListAdapter extends RecyclerView.Adapter<ServiceListAdapter.MyViewHolder> {

        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            MyViewHolder holder = new MyViewHolder(LayoutInflater.from(
                    ServiceList.this).inflate(R.layout.service_list_item, parent,
                    false));
            return holder;
        }


        public void onBindViewHolder(MyViewHolder holder, int position) {

            holder.item_name.setText(mDatas.get(position));
            holder.item_time.setText(mDatas.get(position));
            holder.item_boolean.setText(mDatas.get(position));
            holder.item_time.setText(mDatas.get(position));
            holder.item_monry.setText(mDatas.get(position));
            //LoadImageManager.getImageLoader().displayImage("",holder.item_tx);

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent goServiceDetail = new Intent(ServiceList.this, ServiceDetail.class);
                    startActivity(goServiceDetail);
                }
            });

        }
        public int getItemCount() {
            return mDatas.size();
        }

        class MyViewHolder extends RecyclerView.ViewHolder {

            TextView item_name,item_time,item_content,item_monry,item_boolean;
            CircleImageView item_tx;

            public MyViewHolder(View view) {
                super(view);
                item_name = (TextView) view.findViewById(R.id.item_name);
                item_time = (TextView) view.findViewById(R.id.item_time);
                item_boolean = (TextView) view.findViewById(R.id.item_boolean);
                item_content = (TextView) view.findViewById(R.id.item_content);
                item_monry = (TextView) view.findViewById(R.id.item_money);
                item_tx = (CircleImageView) view.findViewById(R.id.item_tx);
            }
        }


    }






}