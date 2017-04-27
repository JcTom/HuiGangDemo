package com.suctan.huigangdemo.fragment.my.sell;

import android.view.View;
import android.widget.AdapterView;

import com.suctan.huigangdemo.R;
import com.suctan.huigangdemo.fragmentinterface.InterfaceMysellOrderState;
import com.suctan.huigangdemo.mvp.login.sellorder.MySellOrderPresenter;
import com.suctan.huigangdemo.mvp.login.sellorder.MySellOrderView;
import com.suctan.huigangdemo.widget.MvpFragment;


public class MysellAllFrag extends MvpFragment<MySellOrderPresenter>implements MySellOrderView {

    private InterfaceMysellOrderState Listener;

    public static MysellAllFrag getFragInstant() {
        MysellAllFrag mysellAllFrag = new MysellAllFrag();
        return mysellAllFrag;
    }

    public void setListanter(InterfaceMysellOrderState Listener) {
        this.Listener = Listener;
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
    protected void onDataRefresh() {

    }

    @Override
    protected void onDataLoadMore() {

    }

    @Override
    protected void dataLazyLoad() {

    }

    @Override
    protected void initViewsAddEvents() {

    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.first_fragment;
    }

    @Override
    protected void onMItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    protected MySellOrderPresenter createPresenter() {
        return new MySellOrderPresenter(this);
    }

//	private void ProceedingAdapters() {
//		ArrayList<ProceedingBean> companys = new ArrayList<>();
//		for(int i=0;i<=3;i++){
//			ProceedingBean proceedingBean=new ProceedingBean("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1492091993911&di=804ff682760b588e56abfc96f9d43ecd&imgtype=0&src=http%3A%2F%2Fpic.58pic.com%2F58pic%2F13%2F82%2F51%2F77P58PICFKD_1024.jpg");
//			companys.add(proceedingBean);
//		}
//		ProceedingAdapter adapter = new ProceedingAdapter(getActivity(),companys);
//		my_first_framgment_gridview.setAdapter(adapter);
//	}

}
