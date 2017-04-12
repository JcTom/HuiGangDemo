package com.example.androidbase.rxjava;

public interface ApiCallback<T> {

    void onStart();

    void onSuccess(T model);

    void onFaild(String msg);

    void onCompleted();
}
