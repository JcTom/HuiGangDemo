package com.example.androidbase.utils;

import android.content.Context;
import android.os.Looper;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.androidbase.BaseApplication;
import com.example.androidbase.R;

/**
 * author: lyfei
 * date:   2016/7/26.
 */
public class GlideUtil {

//    private static int placeholder = R.drawable.default_image;
    private static int error = R.mipmap.default_image;

    public static void load(String url, ImageView taget) {
        Glide.with(BaseApplication.getContext())
                .load(url)
                .centerCrop()
//                .placeholder(placeholder)
                .error(error)
                .crossFade()
                .into(taget);
    }

    public static void load(String url, ImageView taget,int place,int err) {
        Glide.with(BaseApplication.getContext())
                .load(url)
                .centerCrop()
                .placeholder(place)
                .error(err)
                .crossFade()
                .into(taget);
    }

    /**
     * 清除图片磁盘缓存
     */
    public static void clearImageDiskCache() {
        try {
            if (Looper.myLooper() == Looper.getMainLooper()) { //只能在非主线程执行
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Glide.get(BaseApplication.getContext()).clearDiskCache();
                    }
                });
            } else {
                Glide.get(BaseApplication.getContext()).clearDiskCache();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 清除图片内存缓存
     */
    public static void clearImageMemoryCache(Context context) {
        try {
            Glide.get(context).clearMemory();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
