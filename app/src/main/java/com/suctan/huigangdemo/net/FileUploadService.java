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
}
