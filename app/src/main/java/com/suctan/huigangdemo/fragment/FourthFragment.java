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
import com.suctan.huigangdemo.bean.user.MyAssessUpBean;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


public class FourthFragment extends Fragment {
	View view;
	@BindView(R.id.I_have_received_Up_gridview)
	GridView I_have_received_Up_gridview;
	//这个fragment对应这个，我的评价中的 收到别人对我的评价，第二个栏
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		//加载布局，换成fragment所对应的布局，而这个布局，是一个临时调试的布局。
		if(view==null){
			view=LayoutInflater.from(getActivity()).inflate(R.layout.fourfragment,null,false);
		}
		ViewGroup viewGroup= (ViewGroup) view.getParent();
		if(viewGroup!=null){
			viewGroup.removeView(view);
		}

		return view;
	}

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		ButterKnife.bind(this,view);
		MyAssessUpAdapters();
	}

	private void MyAssessUpAdapters() {
		ArrayList<MyAssessUpBean> companys = new ArrayList<>();
		for(int i=0;i<=3;i++){
			MyAssessUpBean myAssessUpBean=new MyAssessUpBean("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1492091993911&di=804ff682760b588e56abfc96f9d43ecd&imgtype=0&src=http%3A%2F%2Fpic.58pic.com%2F58pic%2F13%2F82%2F51%2F77P58PICFKD_1024.jpg"
					,"http://img2.imgtn.bdimg.com/it/u=2324580144,3531001861&fm=214&gp=0.jpg");
			companys.add(myAssessUpBean);
		}
		MyAssessUpAdapter adapter = new MyAssessUpAdapter(getActivity(),companys);
		I_have_received_Up_gridview.setAdapter(adapter);
	}
}
