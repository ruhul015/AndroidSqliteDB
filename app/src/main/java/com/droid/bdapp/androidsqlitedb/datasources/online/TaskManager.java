package com.droid.bdapp.androidsqlitedb.datasources.online;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Build;
import android.util.Log;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executor;

public class TaskManager
{
	public static final String TAG = TaskManager.class.getSimpleName();
	
	private Context context = null;
	private final CopyOnWriteArrayList<TaskQueueItem> taskQueue = new CopyOnWriteArrayList<TaskQueueItem>();
	private String tag = TaskManager.class.getSimpleName();

	public void initialize(final Context context)
	{
		this.context = context;
	}

	@SuppressLint("NewApi")
	public void startTask(final TaskType type, final TaskCallback callback, final TaskParameter param)
	{
		startTask(type, callback, param, null);
	}

	@SuppressLint("NewApi")
	public void startTask(final TaskType type, final TaskCallback callback, final TaskParameter param, final Executor executor)
	{
		startTask(type, callback, param, executor, null);
	}

	@SuppressLint("NewApi")
	public TaskBase startTask(final TaskType type, final TaskCallback callback, final TaskParameter param, final Executor executor, final String tag)
	{
		if (tag != null)
		{
			this.tag = tag;
		}

		final TaskQueueItem item = new TaskQueueItem(type, callback, param, this.tag);
		final boolean canAdd = canAdd(taskQueue, item);

		if (!canAdd)
		{
			// Already running
			return null;
		}
		Log.v("Task Add", "Task Add " + taskQueue.size());
		taskQueue.add(item);
		item.base = item.type.createIns();
		item.base.initialize(context, this, item.type, item.callback);

		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB)
		{
			if (executor == null)
			{
				item.base.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, item.param);
			}
			else
			{
				item.base.executeOnExecutor(executor, item.param);
			}
		}
		else
		{
			item.base.execute(item.param);
		}

		return item.base;
	}

	private boolean canAdd(final CopyOnWriteArrayList<TaskQueueItem> list, final TaskQueueItem item)
	{
		//return !list.contains(item) && !contains(list, item);
		return !contains(list, item);
	}

	private boolean contains(final CopyOnWriteArrayList<TaskQueueItem> list, final TaskQueueItem item)
	{
		final Iterator<TaskQueueItem> ite = list.iterator();
		while (ite.hasNext())
		{
			final TaskQueueItem contain = ite.next();
			if (item.equals(contain))
			{
				return true;
			}
		}
		return false;
	}

	public void cancelAll()
	{
		final Iterator<TaskQueueItem> ite = taskQueue.iterator();
		while (ite.hasNext())
		{
			final TaskQueueItem item = ite.next();

			if (null != item.param && item.param.isSilentMode)
			{
				continue;
			}

			item.base.cancel(true);
			item.base = null;
			taskQueue.remove(item);
		}
	}

	public TaskBase getTask(final TaskType type)
	{
		final Iterator<TaskQueueItem> ite = taskQueue.iterator();
		while (ite.hasNext())
		{
			final TaskQueueItem item = ite.next();
			if (type == item.type)
			{
				return item.base;
			}
		}

		return null;
	}

	public TaskBase getTask(final String tag)
	{
		final Iterator<TaskQueueItem> ite = taskQueue.iterator();
		while (ite.hasNext())
		{
			final TaskQueueItem item = ite.next();
			if (tag.equalsIgnoreCase(item.taskTag))
			{
				return item.base;
			}
		}

		return null;
	}

	public void cancelLogic(final TaskBase targetBase)
	{
		final Iterator<TaskQueueItem> ite = taskQueue.iterator();
		while (ite.hasNext())
		{
			final TaskQueueItem item = ite.next();
			if (targetBase == item.base)
			{
				item.base.cancel();
				break;
			}
		}
	}

	public void cancelLogic(final String tag)
	{
		final Iterator<TaskQueueItem> ite = taskQueue.iterator();
		while (ite.hasNext())
		{
			final TaskQueueItem item = ite.next();
			if (tag.equalsIgnoreCase(item.taskTag))
			{
				item.base.cancel();
				Log.v("Task Cancel", "Task Cancel " + taskQueue.size());
				break;
			}
		}
	}

	public void finished(final TaskBase targetBase)
	{
		final Iterator<TaskQueueItem> ite = taskQueue.iterator();
		while (ite.hasNext())
		{
			TaskQueueItem item = ite.next();
			if (targetBase == item.base)
			{
				Log.v("Task Remove", "Task Remove " + taskQueue.size());
				taskQueue.remove(item);
				item = null;
				break;
			}
		}
	}

	private class TaskQueueItem
	{
		public TaskType type;
		public TaskCallback callback;
		public TaskParameter param;
		public TaskBase base;
		public String taskTag;

		public TaskQueueItem(final TaskType type, final TaskCallback callback, final TaskParameter param, final String tag)
		{
			this.type = type;
			this.callback = callback;
			this.param = param;
			this.taskTag = tag;
		}

		@Override
		public boolean equals(final Object obj)
		{
			if (!(obj instanceof TaskQueueItem))
			{
				return false;
			}
			final TaskQueueItem item = (TaskQueueItem) obj;

			if (!this.taskTag.equalsIgnoreCase(item.taskTag))
			{
				return false;
			}

			if (this.type != item.type)
			{
				return false;
			}

			if (this.callback != item.callback)
			{
				return false;
			}

			if (null == this.param)
			{
				if (null != item.param)
				{
					return false;
				}
			}
			else
			{
				if (!this.param.equals(item.param))
				{
					return false;
				}
			}
			return true;
		}
	}
}
