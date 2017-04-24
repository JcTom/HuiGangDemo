package com.suctan.huigangdemo.net;

import retrofit.Callback;
import retrofit.http.Multipart;
import retrofit.http.POST;
import retrofit.http.Part;
import retrofit.mime.TypedFile;

/**
 * Created by renegens on 16/02/16.
 */
public interface FileUploadService {

    @Multipart
    @POST("/tp/index.php/Home/Index/uploadTest")
    void upload(@Part("file") TypedFile file,
                @Part("description") String description,
                Callback<String> cb);

    @Multipart
    @POST("/tp/index.php/Home/Index/pub_makeFood")
    void FoodPublic(
            @Part("order_pic") TypedFile file,
            @Part("user_token") String token,
            @Part("order_title") String order_title,
            @Part("order_price") double order_price,
            @Part("order_type") int order_type,
            @Part("makeFood_res") String makeFood_res,
            @Part("makeFood_float") String makeFood_float,
            @Part("makeFood_note") String makeFood_note,
            Callback<String> cb);


    @Multipart
    @POST("/tp/index.php/Home/Index/pub_topic")
    void TopicPublic(
            @Part("topic_picture") TypedFile file,
            @Part("user_token") String user_token,
            @Part("topic_title") String topic_title,
            @Part("topic_content") String topic_content,
            Callback<String> cb);
/*    ,*/
}
