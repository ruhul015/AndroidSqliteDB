package com.droid.bdapp.androidsqlitedb.datasources.offline.database.dao;

import android.database.sqlite.SQLiteDatabase;

import com.droid.bdapp.androidsqlitedb.datasources.offline.database.DatabaseManager;

/**
 * Created by mdruhulamin on 12/14/15.
 */
public class UserDao extends DAO {


    @Override
    public SQLiteDatabase getDatabaseConnection() {
        this.mDatabaseConnection = DatabaseManager.getInstance().open();
        return mDatabaseConnection;
    }
}

