package com.droid.bdapp.androidsqlitedb.datasources.online;

import android.content.Context;

import com.droid.bdapp.androidsqlitedb.app.AppBaseApplication;

public class UserLoaderTask extends TaskBase {
    public static final String TAG = UserLoaderTask.class.getName();

    private static final Context APP_CONTEXT = AppBaseApplication.getContext();

    @Override
    protected TaskParameter doInBackgroundTask(final TaskParameter param)
            throws Exception {

        return param;
    }

    @Override
    protected void onPostExecuteTask(final TaskParameter result)
            throws Exception {
        callTaskEnd(result);
    }
}