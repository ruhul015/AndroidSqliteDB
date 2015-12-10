package com.droid.bdapp.androidsqlitedb.datasources.online;

import android.content.Context;
import android.os.AsyncTask;

public abstract class TaskBase extends AsyncTask<TaskParameter, Integer, TaskParameter> {
    public static final String TAG = TaskBase.class.getSimpleName();

    protected Context context = null;
    protected TaskCallback callback = null;
    protected TaskType type = null;
    protected TaskParameter param = null;
    private Throwable th = null;
    private TaskManager manager = null;

    public void initialize(final Context context, final TaskManager manager, final TaskType type, final TaskCallback callback) {
        this.context = context;
        this.manager = manager;
        this.type = type;
        this.callback = callback;
    }

    public void setCallback(final TaskCallback callback) {
        this.callback = callback;
    }

    protected void onPreExecuteTask() {
    }

    ;

    @Override
    final protected void onPreExecute() {
        super.onPreExecute();
        onPreExecuteTask();
    }

    abstract protected TaskParameter doInBackgroundTask(final TaskParameter param) throws Exception;

    @Override
    final protected TaskParameter doInBackground(final TaskParameter... params) {
        try {
            if (null != params) {
                param = params[0];
            } else {
                param = null;
            }

            param = doInBackgroundTask(param);

        } catch (final Throwable th) {
            this.th = th;
        }

        return param;
    }

    public void cancel() {
        super.cancel(true);
    }

    protected void onCancelledTask() {
        manager.finished(this);
    }

    @Override
    protected void onCancelled() {
        super.onCancelled();
        onCancelledTask();

        if (null != callback) {
            callback.onCancelCallback(type, param);
        }
    }

    abstract protected void onPostExecuteTask(final TaskParameter result) throws Exception;

    @Override
    final protected void onPostExecute(final TaskParameter result) {
        if (null != th) {
            errorProcess(th);
        } else {
            try {
                onPostExecuteTask(result);
            } catch (final Throwable thx) {
                errorProcess(thx);
            }
        }
    }

    protected void callTaskEnd(final TaskParameter obj) throws Exception {
        manager.finished(this);

        if (null != callback) {
            callback.onEndCallback(type, obj);
        }
    }

    protected void errorProcess(final Throwable th) {
        manager.finished(this);
        // manager.cancelAll();
    }
}
