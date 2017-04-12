package com.example.androidbase;

import android.support.v4.app.Fragment;

/**
 * Created by chenxiaozhou on 16/6/15.
 */
public abstract class BaseFragment extends Fragment {

    /** Fragment当前状态是否可见 */
    protected boolean isVisible;

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

        if (getUserVisibleHint()) {
            onVisible();
        } else {
            onInvisible();
        }

    }

    /**
     * 可见
     */
    protected void onVisible() {
        isVisible = true;
        lazyLoad();
    }

    /**
     * 不可见
     */
    protected void onInvisible() {
        isVisible = false;
    }

    /**
     * 延迟加载 子类必须重写此方法
     */
    protected abstract void lazyLoad();

}
