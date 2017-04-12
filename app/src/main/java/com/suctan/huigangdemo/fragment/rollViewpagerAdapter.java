package com.suctan.huigangdemo.fragment;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.jude.rollviewpager.adapter.StaticPagerAdapter;
import com.suctan.huigangdemo.R;

/**
 * Created by Tom on 2017/4/11.
 */

public class rollViewpagerAdapter extends StaticPagerAdapter{

    private int[] res={R.mipmap.ic_launcher
            ,R.mipmap.ic_launcher,
            R.mipmap.ic_launcher,
            R.mipmap.ic_launcher};

    @Override
    public View getView(ViewGroup container, int position) {

        ImageView imageView = new ImageView(container.getContext());
        imageView.setImageResource(res[position]);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        return imageView;

    }

    @Override
    public int getCount() {
        return res.length;
    }
}
