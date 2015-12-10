package com.droid.bdapp.androidsqlitedb.datasources.offline.database.table;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.droid.bdapp.androidsqlitedb.datasources.offline.database.DbConfig;

/**
 * Created by mdruhulamin on 12/10/15.
 */
public abstract class Table extends SQLiteOpenHelper {

    public Table(Context appContext) {
        super(appContext, DbConfig.DATABASE_TEST, DbConfig.dbDefaultCursorFactory, DbConfig.dbDefaultVersion);
    }

    public abstract String defineTableColumnsToCreate();

    public abstract String defineTableNameToCreate();


    @Override
    public void onCreate(SQLiteDatabase db) {
        final String sqlCmdStart = "CREATE TABLE ";
        final String primaryKeyCmd = " (ID integer primary key autoincrement, ";
        final String sqlCmdEnd = ");";
        final String createQuery = sqlCmdStart + defineTableNameToCreate() + primaryKeyCmd + defineTableColumnsToCreate() + sqlCmdEnd;
        db.execSQL(createQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // NOP
    }
}
