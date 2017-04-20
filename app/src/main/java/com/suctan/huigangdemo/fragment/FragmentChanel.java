package com.suctan.huigangdemo.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.androidbase.mvp.MvpFragment;
import com.suctan.huigangdemo.R;
import com.suctan.huigangdemo.activity.share.ServiceList;
import com.suctan.huigangdemo.mvp.login.index.chanel.ChanelPresenter;
import com.suctan.huigangdemo.mvp.login.index.chanel.ChanelView;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.bingoogolapple.badgeview.BGABadgeRadioButton;

public class FragmentChanel extends MvpFragment<ChanelPresenter> implements View.OnClickListener, ChanelView {
    //此处定义共享需求类型常量
    private static final int SHARE_JZ= 1; //家政
    private static final int SHARE_DG= 2;     //代购
    private static final int SHARE_SFC = 3;    //顺风车
    private static final int SHARE_DSKD = 4;    //代收快递
    private static final int SHARE_JSXH = 5;    //接送小孩
    private static final int SHARE_PHLR = 6;    //陪护老人
    private static final int SHARE_ESJY = 7;    //二手交易
    private static final int SHARE_GD = 8;    //二手交易


    View viewChanel;
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
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (viewChanel == null) {
            viewChanel = inflater.inflate(R.layout.fragment_share, container, false);
        }
        ViewGroup parent = (ViewGroup) viewChanel.getParent();
        if (parent != null) {
            parent.removeView(viewChanel);
        }
        ButterKnife.bind(this, viewChanel);
        return viewChanel;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initOnclick();
    }

    private void initOnclick(){
        share_jz.setOnClickListener(this);
        share_dg.setOnClickListener(this);
        share_sfc.setOnClickListener(this);
        share_dskd.setOnClickListener(this);
        share_jsxh.setOnClickListener(this);
        share_phlr.setOnClickListener(this);
        share_esjy.setOnClickListener(this);
        share_jqqd.setOnClickListener(this);
    }
/***
 *
 */
    private void Check(String ServiceType , int needType) {
        Intent goService = new Intent(getActivity(), ServiceList.class);
        goService.putExtra("serviceType",ServiceType);
        goService.putExtra("needType", needType);
        startActivity(goService);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.share_jz:
                Check(share_jz.getText().toString(),SHARE_JZ);
                break;
            case R.id.share_dg:
                Check(share_dg.getText().toString(),SHARE_DG);
                break;
            case R.id.share_sfc:
                Check(share_sfc.getText().toString(),SHARE_SFC);
                break;
            case R.id.share_dskd:
                Check(share_dskd.getText().toString(),SHARE_DSKD);
                break;
            case R.id.share_jsxh:
                Check(share_jsxh.getText().toString(),SHARE_JSXH);
                break;
            case R.id.share_phlr:
                Check(share_phlr.getText().toString(),SHARE_PHLR);
                break;
            case R.id.share_esjy:
                Check(share_esjy.getText().toString(),SHARE_ESJY);
                break;
            case R.id.share_jqqd:
               //敬请期待
                break;
            default:
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
    protected ChanelPresenter createPresenter() {
        return new ChanelPresenter(this);
    }
}


