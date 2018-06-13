package com.blankcat.android.base;

import android.app.Application;

import com.blankcat.android.utils.AppStatusTrackerUtils;
import com.blankcat.android.utils.StatusBarCompatUtils;


/**
 * @author: zjf
 * @Date: 2018/6/13
 * @Desc:
 */
public class BaseApp extends Application{
    private static BaseApp app;

    public static int VERSIONCODE = 1;

    public static BaseApp getInstance() {
        return app;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        AppStatusTrackerUtils.init(this);
        try {
            VERSIONCODE = StatusBarCompatUtils.getVersionCode(this);
        } catch (Exception e) {
            VERSIONCODE = 1;
            e.printStackTrace();
        }
    }

}
