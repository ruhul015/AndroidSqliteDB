package com.droid.bdapp.androidsqlitedb.datasources.offline.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.droid.bdapp.androidsqlitedb.datasources.offline.database.table.Table;

//http://dmytrodanylyk.com/pages/blog/concurrent-database.html

public class DatabaseManager {
    public static final String TAG = DatabaseManager.class.getName();

    //
    private static DatabaseManager sInstance;
    private SQLiteDatabase mDatabase;

    public static synchronized void doInitialize() {
        if (sInstance == null) {
            sInstance = new DatabaseManager();
        }
    }

    public static synchronized DatabaseManager getInstance() {
        if (sInstance == null) {
            throw new IllegalStateException(
                    DatabaseManager.class.getSimpleName()
                            + " is not initialized, call initialize(..) method first.");
        }

        return sInstance;
    }

    /**
     * @param appContext
     * @param tableToOpen
     * @throws NullPointerException
     */
    public SQLiteDatabase openDatabaseTable(final Context appContext, final Table tableToOpen) throws NullPointerException {
        grantConnection(appContext, tableToOpen);

        if (this.mDatabase == null || !this.mDatabase.isOpen()) {
            this.mDatabase = tableToOpen.getWritableDatabase();
        }

        return mDatabase;
    }

    public void closeDatabase() {
        if (this.mDatabase != null) {
            this.mDatabase.close();
        }
    }

    private void grantConnection(final Context appContext, final Table tableToOpen) {
        if (appContext == null || tableToOpen == null) {
            throw new NullPointerException("appContext and tableToOpen can't be set to null");
        }
    }
}
