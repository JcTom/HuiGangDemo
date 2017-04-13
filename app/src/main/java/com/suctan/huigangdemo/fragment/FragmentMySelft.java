package com.suctan.huigangdemo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.androidbase.mvp.MvpFragment;
import com.suctan.huigangdemo.R;
import com.suctan.huigangdemo.mvp.login.index.myselft.MySelftPresenter;
import com.suctan.huigangdemo.mvp.login.index.myselft.MySelftView;

import butterknife.BindView;

public class FragmentMySelft extends MvpFragment<MySelftPresenter> implements View.OnClickListener, MySelftView {


    private View viewMyselft;
    @BindView(R.id.setting)
    ImageButton setting;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (viewMyselft == null) {
            viewMyselft = inflater.inflate(R.layout.myself, container, false);
            viewMyselft.findViewById(R.id.setting);

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
        switch (v.getId()){

            case R.id.setting:

                break;


        }
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
