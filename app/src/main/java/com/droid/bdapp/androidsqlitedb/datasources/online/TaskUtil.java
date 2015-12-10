package com.droid.bdapp.androidsqlitedb.datasources.online;

import java.io.File;

public class TaskUtil
{
	public static final String TAG = TaskUtil.class.getSimpleName();

	private static final String ILLEGAL_FILENAME_CHARS = "|\\?*<\":>+[]/'";

	/**
	 * replaces invalid chars from the file name into HEX encoded chars
	 */
	public static String cleanFilename(final String s)
	{
		if (s == null || s.length() == 0)
		{
			return s;
		}

		final StringBuffer result = new StringBuffer();
		for (int t = 0; t < s.length(); t++)
		{
			final char c = s.charAt(t);
			if (ILLEGAL_FILENAME_CHARS.indexOf(c) > -1)
			{
				// replace illegal character with an underscore
				result.append("_");
			}
			else
			{
				result.append(c);
			}
		}
		return result.toString();
	}

	public static String getFilePath(final String url)
	{
		// convert url to filePath
		final StringBuilder buf = new StringBuilder();

		final String[] elements = url.split("/");
		for (int i = 1; i < elements.length; i++)
		{
			final String element = elements[i].trim();

			if (element.equals("http:") || element.equals("https:") || element.equals(".") || element.equals("..") || element.length() == 0)
			{
				continue;
			}

			if (buf.length() > 0)
			{
				buf.append(File.separator);
			}

			buf.append(cleanFilename(element));
		}

		final String filePath = buf.toString();

		return filePath;
	}
}
