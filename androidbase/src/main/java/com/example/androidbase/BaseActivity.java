package com.example.androidbase;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;

import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.inputmethod.InputMethodManager;

import java.util.List;

/**
 * Created by chenxiaozhou on 16/6/15.
 */
public class BaseActivity extends AppCompatActivity {

    private Context mContext;

    public Context getContext() {
        return mContext;
    }

    private ActivityTask tack = ActivityTask.getInstanse();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        tack.addActivity(this);
        overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
        getWindow().setBackgroundDrawable(null);
    }

    @Override
    public void finish() {
        hideSoftInput();
        tack.removeActivity(this);
        overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
        super.finish();
    }

    /**
     * 隐藏软键盘
     */
    public void hideSoftInput() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(), 0);
    }

    /**
     * 通过Class跳转界面
     **/
    public void startActivity(Class<?> cls) {
        startActivity(cls, null);
    }

    /**
     * 含有Bundle通过Class跳转界面
     **/
    public void startActivity(Class<?> cls, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(this, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    /**
     * 含有Bundle通过Class打开编辑界面
     **/
    public void startActivityForResult(Class<?> cls, Bundle bundle, int requestCode) {
        Intent intent = new Intent();
        intent.setClass(this, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, requestCode);
    }

    /**
     * 程序是否在前台运行
     *
     * @return
     */
    public boolean isAppOnForeground() {

        ActivityManager activityManager = (ActivityManager) getApplicationContext().getSystemService(Context.ACTIVITY_SERVICE);
        String packageName = getApplicationContext().getPackageName();

        List<RunningAppProcessInfo> appProcesses = activityManager.getRunningAppProcesses();
        if (appProcesses == null)
            return false;

        for (RunningAppProcessInfo appProcess : appProcesses) {
            if (appProcess.processName.equals(packageName) && appProcess.importance == RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            finish();
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }

    /**
     * 为子类提供一个权限检查方法
     *
     * @param permissions 请求的权限
     * @return
     */
    public boolean hasPermission(String... permissions) {

        for (String permission : permissions) {

            if (ContextCompat.checkSelfPermission(this, permission) !=
                    PackageManager.PERMISSION_GRANTED) {

                return false;
            }
        }
        return true;
    }

    /**
     * 为子类提供一个权限请求方法
     *
     * @param code        请求码
     * @param permissions 请求的权限
     */
    public void requestPermission(int code, String... permissions) {

        ActivityCompat.requestPermissions(this, permissions, code);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        switch (requestCode) {

            case Constants.WRITE_EXTERNAL_CODE:
                doSDCardPermission();
                break;

            case Constants.CAMERA_CODE:
                doCameraPermission();
                break;
        }
    }

    /**
     * 默认的写SD卡权限处理
     */
    public void doSDCardPermission() {
    }

    /**
     * 默认的相机权限处理
     */
    public void doCameraPermission() {
    }
}
