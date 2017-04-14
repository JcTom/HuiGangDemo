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

public class RollViewpagerAdapter extends StaticPagerAdapter{

    ArrayList<CompanyInfoBean>companyList;
    private Context context;
    public RollViewpagerAdapter(Context context, ArrayList<CompanyInfoBean>companyList){
        this.context=context;
        this.companyList=companyList;
    }
/*
    https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1492091993911&di=804ff682760b588e56abfc96f9d43ecd&imgtype=0&src=http%3A%2F%2Fpic.58pic.com%2F58pic%2F13%2F82%2F51%2F77P58PICFKD_1024.jpg
*/
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
