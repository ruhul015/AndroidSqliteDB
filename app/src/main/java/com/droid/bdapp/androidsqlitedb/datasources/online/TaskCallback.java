package com.droid.bdapp.androidsqlitedb.datasources.online;

public interface TaskCallback
{
	public void onEndCallback(final TaskType type, final TaskParameter param);
	public void onCancelCallback(final TaskType type, final TaskParameter param);
	public void onProgressCallback(final TaskType type, final TaskParameter param);
}
