package com.example.androidbase;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

/**
 * Created by chenxiaozhou on 16/6/15.
 */
public class LoadImageManager {

    public static DisplayImageOptions getImageOption() {
        return BaseApplication.getOptions();
    }

    public static ImageLoader getImageLoader() {
        return BaseApplication.getImageLoader();
    }

}
