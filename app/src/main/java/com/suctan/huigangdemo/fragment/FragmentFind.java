package com.suctan.huigangdemo.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.example.androidbase.mvp.MvpFragment;
import com.example.androidbase.widget.CircleImageView;
import com.suctan.huigangdemo.R;
import com.suctan.huigangdemo.activity.LocalImageHolderView;
import com.suctan.huigangdemo.activity.circle.CirclePostDetails;
import com.suctan.huigangdemo.activity.circle.NoticeDetail;
import com.suctan.huigangdemo.mvp.login.index.find.FindPresenter;
import com.suctan.huigangdemo.mvp.login.index.find.FindView;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

public class FragmentFind extends MvpFragment<FindPresenter> implements View.OnClickListener, FindView {

    private View viewFind;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (viewFind == null) {
            viewFind = inflater.inflate(R.layout.fragment_circle, container, false);
        }
        ViewGroup parent = (ViewGroup) viewFind.getParent();
        if (parent != null) {
            parent.removeView(viewFind);
        }
        ButterKnife.bind(this, viewFind);
        return viewFind;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        loadTestDatas();
        initViews();

        mRecyclerView = (RecyclerView) getView().findViewById(R.id.circle_item);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(mAdapter = new FragmentFindAdapter());
        initData();

    }


    private RecyclerView mRecyclerView;
    private List<String> mDatas;
    private FragmentFindAdapter mAdapter;


    protected void initData()
    {
        mDatas = new ArrayList<String>();
        for (int i = 'A'; i < 'z'; i++)
        {
            mDatas.add("" + (char) i);
        }

    }


    class FragmentFindAdapter extends RecyclerView.Adapter<FragmentFindAdapter.MyViewHolder>{

        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            MyViewHolder holder = new MyViewHolder(LayoutInflater.from(
                    getActivity()).inflate(R.layout.circle_list, parent,
                    false));
            return holder;
        }

        public void onBindViewHolder(MyViewHolder holder, int position) {

            holder.item_name.setText(mDatas.get(position));
            holder.item_time.setText(mDatas.get(position));
            holder.item_reply.setText(mDatas.get(position));
            //LoadImageManager.getImageLoader().displayImage("",holder.item_tx);

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent goPostDetails = new Intent(getActivity(), CirclePostDetails.class);
                    startActivity(goPostDetails);
                }
            });

        }

        public int getItemCount() {
            return mDatas.size();
        }


        class MyViewHolder extends RecyclerView.ViewHolder {

            TextView item_name,item_time,item_title,item_reply;
            CircleImageView item_tx;

            public MyViewHolder(View view) {
                super(view);
                item_name = (TextView) view.findViewById(R.id.item_name);
                item_time = (TextView) view.findViewById(R.id.item_time);
                item_title = (TextView) view.findViewById(R.id.item_title);
                item_reply = (TextView) view.findViewById(R.id.item_reply);
                item_tx = (CircleImageView) view.findViewById(R.id.item_tx);
            }
        }


    }





    /*
    * 轮播图
    * */
    private ArrayList<Integer> localImages = new ArrayList<Integer>();
    private Integer image[]={R.mipmap.banner01,R.mipmap.banner01,
            R.mipmap.banner01};
    ConvenientBanner convenientBanner;

    private void initViews() {
        convenientBanner = (ConvenientBanner) getView().findViewById(R.id.ad_banner);
        convenientBanner.setPages(
                new CBViewHolderCreator<LocalImageHolderView>() {
                    @Override
                    public LocalImageHolderView createHolder() {
                        return new LocalImageHolderView();
                    }
                }, localImages)
                //设置两个点图片作为翻页指示器，不设置则没有指示器，可以根据自己需求自行配合自己的指示器,不需要圆点指示器可用不设
                .setPageIndicator(new int[]{R.mipmap.point_w, R.mipmap.point_g})
                //设置指示器的方向
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL)
                .startTurning(3000)//设置自动切换（同时设置了切换时间间隔）
                .setManualPageable(true);
        convenientBanner.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent goNotice = new Intent(getActivity(),NoticeDetail.class);
                startActivity(goNotice);
            }
        });
    }
    private void loadTestDatas() {
        localImages.clear();
        for (int position = 0; position < 3; position++)
            localImages.add(image[position]);

    }


    @Override
    public void onClick(View v) {

    }

    @Override
    public void getDataFail(String msg) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    protected FindPresenter createPresenter() {
        return new FindPresenter(this);
    }
}
