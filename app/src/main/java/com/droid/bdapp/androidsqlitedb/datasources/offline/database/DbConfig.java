package com.droid.bdapp.androidsqlitedb.datasources.offline.database;

import android.database.sqlite.SQLiteDatabase;

/**
 * Created by mdruhulamin on 12/10/15.
 */
public class DbConfig {

    public static final String DATABASE_TEST = "test";
    public static final int dbDefaultVersion = 1;
    public static final SQLiteDatabase.CursorFactory dbDefaultCursorFactory = null;

    public static final String TABLE_TODO = "todo";

    public static final String COL_ID = "id";
    public static final String COL_TITLE = "title";
    public static final String COL_NOTE = "note";


    public static String getTodoTableCreateStatement() {
        final String c0 = COL_TITLE + TEXT + COMMA;
        final String c1 = COL_NOTE + TEXT;

        return c0 + c1;
    }

    public static final String COMMA = ",";
    public static final String NULL = " NULL ";
    public static final String INTEGER = " INTEGER ";
    public static final String REAL = " REAL ";
    public static final String TEXT = " TEXT ";
    public static final String BLOB = " BLOB ";

}
