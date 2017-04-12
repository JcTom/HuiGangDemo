package com.example.androidbase;

import android.app.Activity;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by chenxiaozhou on 16/6/15.
 */
public class ActivityTask {

    private List<Activity> activityList = new ArrayList<Activity>();

    private static ActivityTask tack = new ActivityTask();

    public static ActivityTask getInstanse() {
        return tack;
    }

    private ActivityTask() {

    }

    public void addActivity(Activity activity) {
        activityList.add(activity);
    }

    public void removeActivity(Activity activity) {
        activityList.remove(activity);
    }

    /**
     * 退出所有已经打开的Activity
     */
    public void exitApp() {
        while (activityList.size() > 0) {
            activityList.get(activityList.size() - 1).finish();
        }
    }

    /**
     * 根据class name获取activity
     *
     * @param name
     * @return
     */
    public Activity getActivityByClassName(String name) {
        for (Activity ac : activityList) {
            if (ac.getClass().getName().indexOf(name) >= 0) {
                return ac;
            }
        }
        return null;
    }

    public Activity getActivityByClass(Class<?> cs) {
        for (Activity ac : activityList) {
            if (ac.getClass().equals(cs)) {
                return ac;
            }
        }
        return null;
    }

    /**
     * 弹出activity
     */
    public void popActivity(Class<?>... cs) {
        for (int i = activityList.size() - 1; i >= 0; i--) {
            Activity ac = activityList.get(i);
            for (int j = 0; j < cs.length; j++) {
                if (ac.getClass().equals(cs[j])) {
                    removeActivity(ac);
                    ac.finish();
                    break;
                }
            }
        }
    }

    /**
     * 弹出activity
     *
     * @param cs
     */
    public void popUntilActivity(Class<?>... cs) {
        List<Activity> list = new ArrayList<>();
        for (int i = activityList.size() - 1; i >= 0; i--) {
            Activity ac = activityList.get(i);
            boolean isTop = false;
            for (int j = 0; j < cs.length; j++) {
                if (ac.getClass().equals(cs[j])) {
                    isTop = true;
                    break;
                }
            }
            if (!isTop) {
                list.add(ac);
            } else {
                break;
            }
        }
        for (Iterator<Activity> iterator = list.iterator(); iterator.hasNext();) {
            Activity activity = iterator.next();
            removeActivity(activity);
            activity.finish();
        }
    }

}
