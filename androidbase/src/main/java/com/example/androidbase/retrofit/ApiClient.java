package com.example.androidbase.retrofit;



import com.example.androidbase.BaseApplication;

import java.io.File;
import java.io.IOException;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.JavaNetCookieJar;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class ApiClient {

    public static Retrofit mRetrofit;

    public static Retrofit retrofit(String serverUrl) {
        if (mRetrofit == null) {
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            /**
             * 设置缓存
             */
//            File cacheFile = new File(BaseApplication.getPathName(), "/cache/net");
//            Cache cache = new Cache(cacheFile, 1024 * 1024 * 50);
//            Interceptor cacheInterceptor = new Interceptor() {
//                @Override
//                public Response intercept(Chain chain) throws IOException {
//                    Request request = chain.request();
//                    if (!NetConnectUtils.isNetConnected(BaseApplication.getContext())) {
//                        request = request.newBuilder()
//                                .cacheControl(CacheControl.FORCE_CACHE)
//                                .build();
//                        ToastTool.showToast("暂无网络",0);
//                    }
//                    Response response = chain.proceed(request);
//                    if (NetConnectUtils.isNetConnected(BaseApplication.getContext())) {
//                        int maxAge = 0;
//                        // 有网络时 设置缓存超时时间1个小时
//                        response.newBuilder()
//                                .header("Cache-Control", "public, max-age=" + maxAge)
//                                .removeHeader("Pragma")// 清除头信息，因为服务器如果不支持，会返回一些干扰信息，不清除下面无法生效
//                                .build();
//                    } else {
//                        // 无网络时，设置超时为1天
//                        int maxStale = 60 * 60 * 24;
//                        response.newBuilder()
//                                .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
//                                .removeHeader("Pragma")
//                                .build();
//                    }
//                    return response;
//                }
//            };
//            builder.cache(cache).addInterceptor(cacheInterceptor);

            /**
             * 公共参数
             */
//			Interceptor addQueryParameterInterceptor = new Interceptor() {
//				@Override
//				public Response intercept(Chain chain) throws IOException {
//					Request originalRequest = chain.request();
//					Request request;
//					HttpUrl modifiedUrl = originalRequest.url().newBuilder()
////                            .addQueryParameter("formMap.FROM_DYNA", "IOSPHONE")
//							.build();
//					request = originalRequest.newBuilder().url(modifiedUrl).build();
//					return chain.proceed(request);
//				}
//			};
//			builder.addInterceptor(addQueryParameterInterceptor);

            /**
             * 设置Header
             */
            Interceptor headerInterceptor = new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request originalRequest = chain.request();
                    Request.Builder requestBuilder = originalRequest.newBuilder()
                            .header("Content-Type", "application/json;charset=utf8")
                            .header("Accept", "application/json")
                            .method(originalRequest.method(), originalRequest.body());
                    Request request = requestBuilder.build();
                    return chain.proceed(request);
                }
            };
            builder.addInterceptor(headerInterceptor);

//            CookieManger cookieManager = new CookieManger(BaseApplication.getContext());
//            builder.cookieJar(cookieManager);

            CookieManager cookieManager = new CookieManager();
            cookieManager.setCookiePolicy(CookiePolicy.ACCEPT_ALL);
            builder.cookieJar(new JavaNetCookieJar(cookieManager));


            //设置超时
            builder.connectTimeout(15, TimeUnit.SECONDS);
            builder.readTimeout(20, TimeUnit.SECONDS);
            builder.writeTimeout(20, TimeUnit.SECONDS);
            //错误重连
            builder.retryOnConnectionFailure(true);

            OkHttpClient okHttpClient = builder.build();
            mRetrofit = new Retrofit.Builder()
                    .baseUrl(serverUrl)
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .client(okHttpClient)
                    .build();
        }
        return mRetrofit;
    }

    public static void cleanCache() {
        File cacheFile = new File(BaseApplication.getPathName(), "/cache/net");
        RecursionDeleteFile(cacheFile);
    }

    public static void RecursionDeleteFile(File cacheFile) {
        if (cacheFile.isFile()) {
            cacheFile.delete();
            return;
        }
        if (cacheFile.isDirectory()) {
            File[] childFiles = cacheFile.listFiles();
            if (childFiles == null || childFiles.length == 0) {
                cacheFile.delete();
                return;
            }
            for (File file : childFiles) {
                RecursionDeleteFile(file);
            }
            cacheFile.delete();
        }
    }

}
