package com.droid.bdapp.androidsqlitedb.app;

import android.app.Application;

import com.droid.bdapp.androidsqlitedb.datasources.offline.database.DatabaseManager;
import com.droid.bdapp.androidsqlitedb.datasources.online.TaskManager;

/**
 * Created by mdruhulamin on 2/12/15.
 */
public class AppBaseApplication extends Application {
    public static final String TAG = AppBaseApplication.class.getSimpleName();

    private static AppBaseApplication sInstance;
    private static TaskManager sTaskManager;

    public static AppBaseApplication getInstance() {
        return sInstance;
    }

    public static AppBaseApplication getContext() {
        return sInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;

        //initialize App database manager
        DatabaseManager.doInitialize();

        sTaskManager = new TaskManager();
        //initialize goolge analytics
        // GoogleAnalytics.getInstance().initialize(sInstance);


    }

    public static TaskManager getGlobalTaskManager() {
        return sTaskManager;
    }
}
