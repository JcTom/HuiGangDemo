package com.example.androidbase;

import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.widget.Toast;

import com.example.androidbase.utils.SdCardTool;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.UsingFreqLimitedMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;
import com.nostra13.universalimageloader.utils.StorageUtils;

import java.io.File;

/**
 * Created by chenxiaozhou on 16/6/15.
 */
public abstract class BaseApplication extends Application {

    private static DisplayImageOptions options;

    public static DisplayImageOptions getOptions() {
        return options;
    }

    private static ImageLoader imageLoader = ImageLoader.getInstance();

    public static ImageLoader getImageLoader() {
        return imageLoader;
    }

    private static String pathName;

    public static String getPathName() {
        return pathName;
    }

    private static Context context;

    public static Context getContext() {
        return context;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        context = this;
        Toast.makeText(this,context.toString(),Toast.LENGTH_LONG).show();
        Log.i("AAAA",context.toString());
        pathName = SdCardTool.getRootFilePath() + "/eenet";
        File path = new File(pathName);
        if (!path.exists()) {
            path.mkdir();
        }

        initImageLoader(this);

        otherInit(this);
    }

    public static void initImageLoader(Context context) {

        options = new DisplayImageOptions.Builder().showImageForEmptyUri(R.mipmap.default_image).showImageOnLoading(R.mipmap.default_image)
                .showImageOnFail(R.mipmap.default_image).cacheInMemory(true)
                .imageScaleType(ImageScaleType.IN_SAMPLE_INT).cacheOnDisk(true).bitmapConfig(Bitmap.Config.RGB_565).resetViewBeforeLoading(false)
                .displayer(new FadeInBitmapDisplayer(0)).build();

        File cacheDir = StorageUtils.getOwnCacheDirectory(context, "eenet/cache/images");
        ImageLoaderConfiguration.Builder config = new ImageLoaderConfiguration.Builder(context);
        config.diskCache(new UnlimitedDiskCache(cacheDir));
        config.memoryCacheExtraOptions(480, 800);
        config.threadPoolSize(3);
        config.denyCacheImageMultipleSizesInMemory();
        config.memoryCache(new UsingFreqLimitedMemoryCache(2 * 1024 * 1024));
        config.memoryCacheSize(2 * 1024 * 1024);
        config.threadPriority(Thread.NORM_PRIORITY - 2);
        config.denyCacheImageMultipleSizesInMemory();
        config.diskCacheFileNameGenerator(new Md5FileNameGenerator());
        config.diskCacheSize(50 * 1024 * 1024);
        config.tasksProcessingOrder(QueueProcessingType.LIFO);
        config.defaultDisplayImageOptions(options);
        config.imageDownloader(new BaseImageDownloader(context, 5 * 1000, 30 * 1000));
        imageLoader.init(config.build());
    }

    protected abstract void otherInit(Context context);

}
