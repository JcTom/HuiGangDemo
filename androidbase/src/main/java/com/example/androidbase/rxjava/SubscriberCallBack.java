package com.example.androidbase.rxjava;

import rx.Subscriber;

public class SubscriberCallBack<T> extends Subscriber<T> {
    private ApiCallback<T> apiCallback;

    public SubscriberCallBack(ApiCallback<T> apiCallback) {
        this.apiCallback = apiCallback;
    }

    @Override
    public void onStart() {
        apiCallback.onStart();
    }

    @Override
    public void onCompleted() {
        apiCallback.onCompleted();
    }

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
        apiCallback.onFailed("系统繁忙，请稍后访问");
        apiCallback.onCompleted();
    }

    @Override
    public void onNext(T t) {
        apiCallback.onSuccess(t);
    }
}
