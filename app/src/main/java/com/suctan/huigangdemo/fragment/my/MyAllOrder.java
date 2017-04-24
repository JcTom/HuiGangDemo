package com.suctan.huigangdemo.fragment.my;
import android.view.View;
import android.widget.AdapterView;
import com.suctan.huigangdemo.R;
import com.suctan.huigangdemo.fragmentinterface.InterFaceOrderState;
import com.suctan.huigangdemo.mvp.login.buyorder.MyBuyOrderPresenter;
import com.suctan.huigangdemo.mvp.login.buyorder.MyBuyOrderView;
import com.suctan.huigangdemo.widget.MvpFragment;

/**
 * Created by B-305 on 2017/4/19.
 */

public class MyAllOrder extends MvpFragment<MyBuyOrderPresenter> implements MyBuyOrderView {

    //这个fragment对应这个，我卖出的中的两个选项栏中的已完成
    private InterFaceOrderState Listner;

    public static MyAllOrder getFragmentInstant() {
        MyAllOrder allOrderFrag = new MyAllOrder();
        return allOrderFrag;
    }

    public void setFragListner(InterFaceOrderState Listner) {
        this.Listner = Listner;
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
        return R.layout.mybuy_one;
    }

    @Override
    protected void onMItemClick(AdapterView<?> parent, View view, int position, long id) {

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
    protected MyBuyOrderPresenter createPresenter() {
        return new MyBuyOrderPresenter(this);
    }
}
