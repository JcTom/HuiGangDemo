package com.suctan.huigangdemo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.androidbase.mvp.MvpFragment;
import com.suctan.huigangdemo.R;
import com.suctan.huigangdemo.mvp.login.index.find.FindPresenter;
import com.suctan.huigangdemo.mvp.login.index.find.FindView;

public class FragmentFind extends MvpFragment<FindPresenter> implements View.OnClickListener, FindView {

    private View viewFind;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (viewFind == null) {
            viewFind = inflater.inflate(R.layout.fragment_circle, container, false);
        }
        ViewGroup parent = (ViewGroup) viewFind.getParent();
        if (parent != null) {
            parent.removeView(viewFind);
        }
        return viewFind;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }


    @Override
    public void onClick(View v) {

    }

    @Override
    public void getDataFail(String msg) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    protected FindPresenter createPresenter() {
        return new FindPresenter(this);
    }
}
