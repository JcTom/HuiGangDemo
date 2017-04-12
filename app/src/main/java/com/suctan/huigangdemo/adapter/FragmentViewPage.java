package com.suctan.huigangdemo.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class FragmentViewPage extends FragmentPagerAdapter {
    private ArrayList<Fragment> FragmentArray;//fragment子布局
    private Context context;//上下文
    public FragmentViewPage(FragmentManager fm, ArrayList<Fragment> fragmentArray, Context context) {
        super(fm);
        FragmentArray = fragmentArray;
        this.context = context;
    }
    @Override
    public Fragment getItem(int position) {
        return FragmentArray.get(position);
    }
    @Override
    public int getCount() {
        return FragmentArray.size();
    }
}
