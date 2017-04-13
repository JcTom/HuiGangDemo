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

import butterknife.BindView;
import cn.bingoogolapple.badgeview.BGABadgeRadioButton;

public class FragmentChanel extends MvpFragment<ChanelPresenter> implements View.OnClickListener, ChanelView {

    private View viewChanel;
    @BindView(R.id.share_jz)
    BGABadgeRadioButton share_jz;
    @BindView(R.id.share_dg)
    BGABadgeRadioButton share_dg;
    @BindView(R.id.share_sfc)
    BGABadgeRadioButton share_sfc;
    @BindView(R.id.share_dskd)
    BGABadgeRadioButton share_dskd;
    @BindView(R.id.share_jsxh)
    BGABadgeRadioButton share_jsxh;
    @BindView(R.id.share_phlr)
    BGABadgeRadioButton share_phlr;
    @BindView(R.id.share_esjy)
    BGABadgeRadioButton share_esjy;
    @BindView(R.id.share_jqqd)
    BGABadgeRadioButton share_jqqd;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (viewChanel == null) {
            viewChanel = inflater.inflate(R.layout.fragment_share, container, false);
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


