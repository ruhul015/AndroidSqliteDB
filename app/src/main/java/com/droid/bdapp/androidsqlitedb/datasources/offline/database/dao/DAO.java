package com.droid.bdapp.androidsqlitedb.datasources.offline.database.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.droid.bdapp.androidsqlitedb.datasources.offline.database.DatabaseManager;
import com.droid.bdapp.androidsqlitedb.datasources.offline.database.table.Table;

/**
 * Created by mdruhulamin on 12/10/15.
 */
public abstract class DAO {
    private static final String TAG = DAO.class.getSimpleName();

    private SQLiteDatabase mDatabaseConnection;

    public DAO(Context appContext, Table tableToOpen) throws NullPointerException {
        this.mDatabaseConnection = DatabaseManager.getInstance().openDatabaseTable(appContext, tableToOpen);
    }

    /**
     * Defines the name of this database in the concrete implementation of this class
     */
    public abstract String defineDatabaseNameToCreate();

    /**
     * @param tableName
     * @param rowToCreate
     * @return The row ID of the newly inserted row, or -1 if an error occurred
     */
    public long insert(final String tableName, final ContentValues rowToCreate) {
        long rowId = -1;
        try {
            rowId = mDatabaseConnection.insert(tableName, null, rowToCreate);
        } catch (Exception e) {
            Log.i(TAG, "Insert error");
        } finally {
            DatabaseManager.getInstance().closeDatabase();
        }
        return rowId;
    }

    public void update(final String tableName, final int rowIdToUpdate, final ContentValues rowToUpdate) {
        final String whereClause = "ID=" + rowIdToUpdate;
        try {
            mDatabaseConnection.update(tableName, rowToUpdate, whereClause, null);
        } catch (Exception e) {
            Log.i(TAG, "update error");
        } finally {
            DatabaseManager.getInstance().closeDatabase();
        }
    }

    public void dropTable(final String tableName) {

        String sql = "DROP TABLE IF EXISTS " + tableName;
        try {
            mDatabaseConnection.execSQL(sql);
        } catch (Exception e) {
            Log.i(TAG, "Insert error");
        } finally {
            DatabaseManager.getInstance().closeDatabase();
        }
    }

    public boolean dropDatabase(final Context appContext, final String databaseName) {
        return appContext.deleteDatabase(databaseName);
    }

    public void deleteById(final String tableName, final int id) {

        String whereClause = "ID=" + id;
        String[] whereArgs = null;
        try {
            mDatabaseConnection.delete(tableName, whereClause, whereArgs);
        } catch (Exception e) {
            Log.i(TAG, " error occurred during delete row by id");
        } finally {
            DatabaseManager.getInstance().closeDatabase();
        }
    }


    /**
     * NOTE: MUST Close database after geting data from this Cursor
     *
     * @param tableName
     * @param columnNamesToShow
     * @return
     */
    public Cursor selectAll(final String tableName, final String[] columnNamesToShow) {

        String[] columnsToShow = columnNamesToShow;
        String selection = null;
        String[] selectionArgs = null;
        String groupBy = null;
        String having = null;
        String orderBy = null;
        return mDatabaseConnection.query(tableName, columnsToShow, selection, selectionArgs, groupBy, having, orderBy);
    }

    /**
     * NOTE: MUST Close database after geting data from this Cursor
     *
     * @param tableName
     * @param columnNamesToShow
     * @param columnNameToOrderBy
     * @return
     */
    public Cursor selectAllOrderBy(final String tableName, final String[] columnNamesToShow, final String columnNameToOrderBy) {

        String[] columnsToShow = columnNamesToShow;
        String selection = null;
        String[] selectionArgs = null;
        String groupBy = null;
        String having = null;
        String orderBy = columnNameToOrderBy;
        return mDatabaseConnection.query(tableName, columnsToShow, selection, selectionArgs, groupBy, having, orderBy);
    }

    /**
     * NOTE: MUST Close database after geting data from this Cursor
     *
     * @param tableName
     * @param columnNamesToShow
     * @param columnNameToDistinct
     * @return
     */
    public Cursor selectAllDistinct(final String tableName, final String[] columnNamesToShow, final String columnNameToDistinct) {

        String[] columnsToShow = columnNamesToShow;
        String selection = null;
        String[] selectionArgs = null;
        String groupBy = columnNameToDistinct;
        String having = null;
        String orderBy = columnNameToDistinct;
        return mDatabaseConnection.query(tableName, columnsToShow, selection, selectionArgs, groupBy, having, orderBy);
    }

    /**
     * NOTE: MUST Close database after geting data from this Cursor
     *
     * @param tableName
     * @param id
     * @return
     */
    public Cursor selectRowById(final String tableName, final int id) {

        String[] columnsToShow = null;
        String selection = "ID=" + id;
        String[] selectionArgs = null;
        String groupBy = null;
        String having = null;
        String orderBy = null;
        return mDatabaseConnection.query(tableName, columnsToShow, selection, selectionArgs, groupBy, having, orderBy);
    }

    /**
     * NOTE: MUST Close database after geting data from this Cursor
     *
     * @param tableName
     * @param timestamp
     * @return
     */
    public Cursor getDifferences(final String tableName, final String timestamp) {

        String[] columnsToShow = null;
        String selection = "TIMESTAMP = '" + timestamp + "'";
        String[] selectionArgs = null;
        String groupBy = null;
        String having = null;
        String orderBy = null;
        return mDatabaseConnection.query(tableName, columnsToShow, selection, selectionArgs, groupBy, having, orderBy);
    }

    private void grantConnection(Context appContext, Table tableToOpen) {
        if (appContext == null || tableToOpen == null) {
            throw new NullPointerException("appContext and tableToOpen can't be set to null");
        }
    }

}