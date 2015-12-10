package com.droid.bdapp.androidsqlitedb.datasources.online;

import java.util.HashMap;

public class TaskParameter extends HashMap<String, Object>
{
	public static final String TAG = TaskParameter.class.getSimpleName();
	
	private static final long serialVersionUID = -5111482748849799850L;

	private int resultCode = 0;
	public boolean isSilentMode = false;

	public int getResultCode()
	{
		return resultCode;
	}

	public void setResultCode(final int code)
	{
		resultCode = code;
	}

	public void safePut(final String key, final Object object)
	{
		if (object != null)
		{
			put(key, object);
		}
	}
}
