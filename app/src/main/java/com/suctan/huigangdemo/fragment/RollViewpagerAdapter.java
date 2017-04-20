package com.suctan.huigangdemo.fragment;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.androidbase.LoadImageManager;
import com.jude.rollviewpager.adapter.StaticPagerAdapter;
import com.suctan.huigangdemo.R;
import com.suctan.huigangdemo.bean.index.EatFoodBean;
import com.suctan.huigangdemo.bean.user.CompanyInfoBean;

import java.util.ArrayList;

/**
 * Created by Tom on 2017/4/11.
 */

public class RollViewpagerAdapter extends StaticPagerAdapter {

    private ArrayList<EatFoodBean> eatFoodList;
    private Context context;

    public RollViewpagerAdapter(Context context, ArrayList<EatFoodBean> eatFoodList) {
        this.context = context;
        this.eatFoodList = eatFoodList;
    }

    @Override
    public View getView(ViewGroup container, final int position) {
        EatFoodBean mEatFood = eatFoodList.get(position);
        ImageView imageView = new ImageView(container.getContext());
        if (mEatFood.getOrder_pic() != null) {
            LoadImageManager.getImageLoader().displayImage(mEatFood.getOrder_pic(), imageView);
        }
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Lisetner.nowRollViewPageItem(position);
            }
        });

        return imageView;
    }

    @Override
    public int getCount() {
        return eatFoodList.size();
    }

    OnItemRollLisener Lisetner;

    public void setOnRollItemt(OnItemRollLisener Lisetner) {
        this.Lisetner = Lisetner;
    }

    interface OnItemRollLisener {
        void nowRollViewPageItem(int position);
    }
}
