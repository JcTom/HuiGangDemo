package com.example.androidbase.utils;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build;
import android.telephony.TelephonyManager;

import com.example.androidbase.BaseApplication;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class PhoneInfomation {

    /**
     * APP版本号
     *
     * @return
     */
    public static int getAppVersion(Context context) {

        PackageManager pm = context.getPackageManager();
        PackageInfo pi;
        try {
            pi = pm.getPackageInfo(context.getPackageName(), 0);
            return pi.versionCode;
        } catch (NameNotFoundException e) {
            return 1;
        }

    }

    /**
     * APP版本号
     *
     * @return
     */
    public static String getAppVersion() {

        PackageManager pm = BaseApplication.getContext().getPackageManager();
        PackageInfo pi;
        try {
            pi = pm.getPackageInfo(BaseApplication.getContext().getPackageName(), 0);
            return pi.versionName;
        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 手机IMEI号
     *
     * @param context
     * @return
     */
    public static String getIMEI(Context context) {

        if (PackageManager.PERMISSION_GRANTED == context.getPackageManager().checkPermission(Manifest.permission.READ_PHONE_STATE, context.getPackageName())) {
            return ((TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE)).getDeviceId();
        } else {
            return null;
        }

    }

    /**
     * 手机操作系统
     *
     * @return
     */
    public static int getSysVersion() {
        return Build.VERSION.SDK_INT;
    }

    /**
     * 手机品牌
     *
     * @return
     */
    public static String getProductName() {
        return Build.BRAND;
    }

    /**
     * 手机型号
     *
     * @return
     */
    public static String getModelName() {
        return Build.MODEL;
    }

    /**
     * 手机制造商
     *
     * @return
     */
    public static String getManufacturerName() {
        return Build.MANUFACTURER;
    }

    /**
     * CPU型号
     *
     * @return
     */
    public static String[] getCpuInfo() {

        String str1 = "/proc/cpuinfo";
        String str2 = "";
        String[] cpuInfo = {"", ""};
        String[] arrayOfString;
        try {
            FileReader fr = new FileReader(str1);
            BufferedReader localBufferedReader = new BufferedReader(fr, 8192);
            str2 = localBufferedReader.readLine();
            arrayOfString = str2.split("\\s+");
            for (int i = 2; i < arrayOfString.length; i++) {
                cpuInfo[0] = cpuInfo[0] + arrayOfString[i] + " ";
            }
            str2 = localBufferedReader.readLine();
            arrayOfString = str2.split("\\s+");
            cpuInfo[1] += arrayOfString[2];
            localBufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return cpuInfo;
    }

}
