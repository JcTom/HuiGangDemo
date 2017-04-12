package com.suctan.huigangdemo.activity.circle;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.suctan.huigangdemo.R;
import com.suctan.huigangdemo.activity.LocalImageHolderView;

import java.util.ArrayList;

/**
 * Created by B-305 on 2017/3/30.
 */

public class CircleFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_circle, container, false);
        return view;
    }

    private ArrayList<Integer> localImages = new ArrayList<Integer>();
    private Integer image[]={R.mipmap.banner01,R.mipmap.banner01,
            R.mipmap.banner01};
    ConvenientBanner convenientBanner;


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        loadTestDatas();
        initViews();
    }


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
    }
    private void loadTestDatas() {
        for (int position = 0; position < 3; position++)
            localImages.add(image[position]);

    }


}
