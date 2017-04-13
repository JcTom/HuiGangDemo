package com.suctan.huigangdemo.fragment;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.androidbase.LoadImageManager;
import com.jude.rollviewpager.adapter.StaticPagerAdapter;
import com.suctan.huigangdemo.R;
import com.suctan.huigangdemo.bean.user.CompanyInfoBean;

import java.util.ArrayList;

/**
 * Created by Tom on 2017/4/11.
 */

public class rollViewpagerAdapter extends StaticPagerAdapter{

    ArrayList<CompanyInfoBean>companyList;
    private Context context;
    public rollViewpagerAdapter(Context context,ArrayList<CompanyInfoBean>companyList){
        this.context=context;
        this.companyList=companyList;
    }

//    private int[] res={R.mipmap.ic_launcher
//            ,R.mipmap.ic_launcher,
//            R.mipmap.ic_launcher,
//            R.mipmap.ic_launcher};

    @Override
    public View getView(ViewGroup container, int position) {
        CompanyInfoBean mcompany=companyList.get(position);
        ImageView imageView = new ImageView(container.getContext());
//        imageView.setIm(mcompany.getImageUrl());
        if(mcompany.getImageUrl()!=null){
            LoadImageManager.getImageLoader().displayImage(mcompany.getImageUrl(),imageView);
        }
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        return imageView;

    }
    @Override
    public int getCount() {
        return companyList.size();
    }
}
