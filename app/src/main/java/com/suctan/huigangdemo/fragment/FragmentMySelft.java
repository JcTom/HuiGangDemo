package com.suctan.huigangdemo.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.example.androidbase.mvp.MvpFragment;
import com.suctan.huigangdemo.R;
import com.suctan.huigangdemo.activity.myself.SettingActivity;
import com.suctan.huigangdemo.mvp.login.index.myselft.MySelftPresenter;
import com.suctan.huigangdemo.mvp.login.index.myselft.MySelftView;
import butterknife.BindView;
import butterknife.ButterKnife;


public class FragmentMySelft extends MvpFragment<MySelftPresenter> implements View.OnClickListener, MySelftView {


    private View viewMyselft;
    @BindView(R.id.setting)
    ImageView setting;
    @Nullable
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (viewMyselft == null) {
            this.viewMyselft = inflater.inflate(R.layout.myself, container, false);
        }
        ViewGroup parent = (ViewGroup) viewMyselft.getParent();
        if (parent != null) {
            parent.removeView(viewMyselft);
        }
        ButterKnife.bind(this ,viewMyselft);
        return this.viewMyselft;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initview();
    }

    private void initview() {

        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotosetting = new Intent(getActivity(), SettingActivity.class);
                startActivity(gotosetting);
            }
        });
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
