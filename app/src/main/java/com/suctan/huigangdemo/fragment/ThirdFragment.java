package com.suctan.huigangdemo.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.suctan.huigangdemo.R;


public class ThirdFragment extends Fragment {

	//这个fragment对应这个，我的评价中的 对别人的评价，第一个栏
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		//加载布局，换成fragment所对应的布局，而这个布局，是一个临时调试的布局。
		return inflater.inflate(R.layout.thirdfragment, container, false);
	}

}
