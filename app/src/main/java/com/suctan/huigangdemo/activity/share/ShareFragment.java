package com.suctan.huigangdemo.activity.share;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.suctan.huigangdemo.R;

import cn.bingoogolapple.badgeview.BGABadgeRadioButton;

/**
 * Created by B-305 on 2017/3/30.
 */

public class ShareFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_share, container, false);
        return view;
    }

    BGABadgeRadioButton share_jz;
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        share_jz = (BGABadgeRadioButton) getView().findViewById(R.id.share_jz);
        share_jz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent intent = new Intent(ShareFragment.this,Service_list.class);
                //intent.setClass(getActivity(),Service_list.class);
                //startActivity(intent);
                //ShareFragment.this.startActivity(intent);

            }
        });
    }



}