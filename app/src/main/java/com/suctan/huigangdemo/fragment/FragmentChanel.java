package com.suctan.huigangdemo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.androidbase.mvp.MvpFragment;
import com.suctan.huigangdemo.R;
import com.suctan.huigangdemo.mvp.login.index.chanel.ChanelPresenter;
import com.suctan.huigangdemo.mvp.login.index.chanel.ChanelView;

public class FragmentChanel extends MvpFragment<ChanelPresenter> implements View.OnClickListener, ChanelView {

    private View viewChanel;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (viewChanel == null) {
            viewChanel = inflater.inflate(R.layout.fragment_chanel, container, false);
        }
        ViewGroup parent = (ViewGroup) viewChanel.getParent();
        if (parent != null) {
            parent.removeView(viewChanel);
        }
        return viewChanel;
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
    protected ChanelPresenter createPresenter() {
        return new ChanelPresenter(this);
    }
}


