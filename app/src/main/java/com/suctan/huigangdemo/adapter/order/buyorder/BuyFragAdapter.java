package com.suctan.huigangdemo.adapter.order.buyorder;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by 黄淑翰 on 2017/4/26.
 */
public class BuyFragAdapter extends FragmentPagerAdapter {
    private ArrayList<Fragment> fragList;

    public BuyFragAdapter(FragmentManager fm, ArrayList<Fragment> fragList) {
        super(fm);
        this.fragList = fragList;
    }

    @Override
    public Fragment getItem(int position) {
        return fragList.get(position);
    }

    public void setDataFraChange(ArrayList<Fragment> fragList) {
        this.fragList = fragList;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return fragList.size();
    }
}
