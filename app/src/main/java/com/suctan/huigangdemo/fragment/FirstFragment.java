package com.suctan.huigangdemo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.suctan.huigangdemo.R;
import com.suctan.huigangdemo.adapter.MyAssessUpAdapter;
import com.suctan.huigangdemo.adapter.ProceedingAdapter;
import com.suctan.huigangdemo.bean.user.MyAssessUpBean;
import com.suctan.huigangdemo.bean.user.ProceedingBean;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


public class FirstFragment extends Fragment {
	View view;
	@BindView(R.id.my_first_framgment_gridview)
	GridView my_first_framgment_gridview;
    //这个fragment对应这个，我卖出的中的两个选项栏中的进行中
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		//加载布局，换成fragment所对应的布局，而这个fragment_view布局，是一个临时调试的布局。

		if(view==null){
			view=LayoutInflater.from(getActivity()).inflate(R.layout.first_fragment,null,false);
		}
		ViewGroup viewGroup= (ViewGroup) view.getParent();
		if(viewGroup!=null){
			viewGroup.removeView(view);
		}
		ButterKnife.bind(this,view);
		return view;
	}

	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		ProceedingAdapters();
	}

	private void ProceedingAdapters() {
		ArrayList<ProceedingBean> companys = new ArrayList<>();
		for(int i=0;i<=3;i++){
			ProceedingBean proceedingBean=new ProceedingBean("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1492091993911&di=804ff682760b588e56abfc96f9d43ecd&imgtype=0&src=http%3A%2F%2Fpic.58pic.com%2F58pic%2F13%2F82%2F51%2F77P58PICFKD_1024.jpg");
			companys.add(proceedingBean);
		}
		ProceedingAdapter adapter = new ProceedingAdapter(getActivity(),companys);
		my_first_framgment_gridview.setAdapter(adapter);
	}

}
