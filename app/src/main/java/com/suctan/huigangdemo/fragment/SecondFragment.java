package com.suctan.huigangdemo.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.suctan.huigangdemo.R;


public class SecondFragment extends Fragment {

	//这个fragment对应这个，我卖出的中的两个选项栏中的已完成
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_view, container, false);
	}

}
