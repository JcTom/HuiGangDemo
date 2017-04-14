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
		return inflater.inflate(R.layout.fragment_view, container, false);
	}

}
