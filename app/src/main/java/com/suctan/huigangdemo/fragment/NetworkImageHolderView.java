package com.suctan.huigangdemo.fragment;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bigkoo.convenientbanner.holder.Holder;
import com.bumptech.glide.Glide;
import com.facebook.drawee.view.SimpleDraweeView;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.suctan.huigangdemo.R;

/**
 * Created by Tom on 2017/4/10.
 */

public class NetworkImageHolderView implements Holder<BannerItem> {
    private View view;
    @Override
    public View createView(Context context) {
        view = LayoutInflater.from(context).inflate(R.layout.banner_item, null, false);
        return view;
    }

    @Override
    public void UpdateUI(Context context, int position, BannerItem data) {
        ((TextView)view.findViewById(R.id.tv_title)).setText(data.getTitle());
        ((ImageView)view.findViewById(R.id.sdv_background)).setImageURI(Uri.parse(data.getImage()));
    }
}

