package com.droid.bdapp.androidsqlitedb.datasources.offline.database;

import android.database.sqlite.SQLiteDatabase;

import java.util.concurrent.atomic.AtomicInteger;

//http://dmytrodanylyk.com/pages/blog/concurrent-database.html

public class DatabaseManager {
    public static final String TAG = DatabaseManager.class.getName();

    private AtomicInteger mOpenCounter = new AtomicInteger();
    //
    private static DatabaseManager sInstance;
    private static DBHelper mDbHelper;
    private SQLiteDatabase mDatabase;

    public static synchronized void doInitialize() {
        if (sInstance == null) {
            sInstance = new DatabaseManager();
            mDbHelper = DBHelper.getInstance();
        }
    }

    public static synchronized DatabaseManager getInstance() {
        if (sInstance == null) {
            throw new IllegalStateException(TAG + " is not initialized, call initialize(..) method first.");
        }

        return sInstance;
    }

    /**
     * @throws NullPointerException
     */
    public synchronized SQLiteDatabase open() throws NullPointerException {
        if (mOpenCounter.incrementAndGet() == 1) {
            //if (this.mDatabase == null || !this.mDatabase.isOpen()) {
            this.mDatabase = mDbHelper.getWritableDatabase();
            // }
        }
        return mDatabase;
    }

    public synchronized void close() {
        if (mOpenCounter.decrementAndGet() == 0) {
            //if (this.mDatabase != null) {
            this.mDatabase.close();
            // }
        }
    }
}
