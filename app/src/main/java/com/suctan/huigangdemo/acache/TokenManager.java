package com.suctan.huigangdemo.acache;

import android.widget.Toast;

import com.example.androidbase.BaseApplication;
import com.example.androidbase.utils.ACache;

/**
 * Created by 黄淑翰 on 2017/4/14.
 */
public class TokenManager {
    public static String getToken() {
        String token = ACache.get(BaseApplication.getContext()).getAsString("nowToken");
        if (token == null) {
            Toast.makeText(BaseApplication.getContext(), "token==null", Toast.LENGTH_LONG).show();
        }
        return token;
    }

    public static void clearToken() {
        ACache acache = ACache.get(BaseApplication.getContext());
        if (acache.getAsString("nowToken") != null) {
            acache.remove("nowToken");
        }
    }


}
