package com.blankcat.android.utils;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import com.orhanobut.logger.Logger;
import com.blankcat.android.constant.AppConstants;


/**
 * @author: zjf
 * @Date: 2018/6/13
 * @Desc: 监听当前app的生命周期和状态
 */
public class AppStatusTrackerUtils implements Application.ActivityLifecycleCallbacks {

    private static AppStatusTrackerUtils tracker;
    private int mAppStatus = AppConstants.STATUS_OFFLINE;
    private boolean isForeground;
    private Application mApplication;
    private int activeCount;
    private Activity mShowingActivity;

    private AppStatusTrackerUtils(Application application) {
        mApplication = application;
        mApplication.registerActivityLifecycleCallbacks(this);
    }

    public static void init(Application application) {
        tracker = new AppStatusTrackerUtils(application);
    }

    public static AppStatusTrackerUtils getInstance() {
        return tracker;
    }

    public void setAppStatus(int status) {
        this.mAppStatus = status;
    }

    public int getAppStatus() {
        return this.mAppStatus;
    }

    public boolean isForeground() {
        return isForeground;
    }

    public Activity getShowingActivity() {
        return mShowingActivity;
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle bundle) {
        Logger.w(activity.toString() + "---onActivityCreate");
    }

    @Override
    public void onActivityStarted(Activity activity) {
        Logger.w(activity.toString() + "---onActivityStarted");
        activeCount++;
//        if (mShowingActivity != null && activeCount == 1) {
//            //代表从后台切回来
//            RxBus.getDefault().post(activity);
//        }
    }

    @Override
    public void onActivityResumed(Activity activity) {
        Logger.w(activity.toString() + "---onActivityResumed");
        isForeground = true;
        mShowingActivity = activity;
    }

    @Override
    public void onActivityPaused(Activity activity) {
        Logger.w(activity.toString() + "---onActivityPaused");
    }

    @Override
    public void onActivityStopped(Activity activity) {
        Logger.w(activity.toString() + "---onActivityStopped");
        activeCount--;
        if (activeCount == 0) {
            isForeground = false;
        }
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        Logger.w(activity.toString() + "---onActivityDestroyed");
    }
}
