package com.suctan.huigangdemo.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.androidbase.mvp.MvpFragment;
import com.suctan.huigangdemo.R;
import com.suctan.huigangdemo.mvp.login.index.myselft.MySelftPresenter;
import com.suctan.huigangdemo.mvp.login.index.myselft.MySelftView;

public class FragmentMySelft extends MvpFragment<MySelftPresenter> implements View.OnClickListener, MySelftView {


    private View viewMyselft;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (viewMyselft == null) {
            viewMyselft = inflater.inflate(R.layout.fragment_myselft, container, false);
        }
        ViewGroup parent = (ViewGroup) viewMyselft.getParent();
        if (parent != null) {
            parent.removeView(viewMyselft);
        }
        return viewMyselft;
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
    protected MySelftPresenter createPresenter() {
        return new MySelftPresenter(this);
    }
}
