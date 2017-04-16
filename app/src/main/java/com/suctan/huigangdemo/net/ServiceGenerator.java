package com.suctan.huigangdemo.net;


import com.squareup.okhttp.OkHttpClient;

import retrofit.RestAdapter;
import retrofit.client.OkClient;

/**
 * Created by renegens on 16/02/16.
 */
public class ServiceGenerator {


    public static final String API_BASE_URL = "http://10.0.2.2";


    private static RestAdapter.Builder builder = new RestAdapter.Builder()
            .setLogLevel(RestAdapter.LogLevel.FULL)
            .setEndpoint(API_BASE_URL)
            .setClient(new OkClient(new OkHttpClient()));

    public static <S> S createService(Class<S> serviceClass) {
        RestAdapter adapter = builder.build();
        return adapter.create(serviceClass);
    }


}
