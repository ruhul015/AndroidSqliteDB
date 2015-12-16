package com.droid.bdapp.androidsqlitedb.datasources.offline.database.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;

import com.droid.bdapp.androidsqlitedb.datasources.offline.database.DatabaseManager;
import com.droid.bdapp.androidsqlitedb.datasources.offline.database.DbConfig;

import java.util.List;

/**
 * Created by mdruhulamin on 12/10/15.
 */
public abstract class DAO {
    private static final String TAG = DAO.class.getSimpleName();

    public SQLiteDatabase mDatabaseConnection;

    /**
     * Defines the name of this database in the concrete implementation of this class
     */
    public abstract SQLiteDatabase getDatabaseConnection();

    public long insert(final String tableName, final List<ContentValues> listOfRowsToCreate) {
        this.mDatabaseConnection = getDatabaseConnection();
        long rowId = -1;
        mDatabaseConnection.beginTransaction();
        try {
            for (final ContentValues cvs : listOfRowsToCreate) {
                final int id = (int) cvs.get(DbConfig.COL_ID);
                if (!hasData(tableName, id)) {
                    rowId = mDatabaseConnection.insert(tableName, null, cvs);
                }
            }
            mDatabaseConnection.setTransactionSuccessful();
        } catch (Exception e) {
            Log.d(TAG, "Error while trying to insert all row in the database of table " + tableName);
        } finally {
            mDatabaseConnection.endTransaction();
            DatabaseManager.getInstance().close();
        }
        return rowId;
    }

    /**
     * @param tableName
     * @param rowToCreate
     * @return The row ID of the newly inserted row, or -1 if an error occurred
     */
    public long insert(final String tableName, final ContentValues rowToCreate) {
        this.mDatabaseConnection = getDatabaseConnection();
        long rowId = -1;
        try {
            rowId = mDatabaseConnection.insert(tableName, null, rowToCreate);
        } catch (Exception e) {
            Log.i(TAG, "Insert error");
        } finally {
            DatabaseManager.getInstance().close();
        }
        return rowId;
    }

    public void update(final String tableName, final int rowIdToUpdate, final ContentValues rowToUpdate) {
        this.mDatabaseConnection = getDatabaseConnection();
        final String whereClause = "ID=" + rowIdToUpdate;
        try {
            mDatabaseConnection.update(tableName, rowToUpdate, whereClause, null);
        } catch (Exception e) {
            Log.i(TAG, "update error");
        } finally {
            DatabaseManager.getInstance().close();
        }
    }

    public void dropTable(final String tableName) {
        this.mDatabaseConnection = getDatabaseConnection();
        String sql = "DROP TABLE IF EXISTS " + tableName;
        try {
            mDatabaseConnection.execSQL(sql);
        } catch (Exception e) {
            Log.i(TAG, "Insert error");
        } finally {
            DatabaseManager.getInstance().close();
        }
    }

    public boolean dropDatabase(final Context appContext, final String databaseName) {
        return appContext.deleteDatabase(databaseName);
    }

    public void deleteById(final String tableName, final int id) {
        this.mDatabaseConnection = getDatabaseConnection();
        String whereClause = "ID=" + id;
        String[] whereArgs = null;
        try {
            mDatabaseConnection.delete(tableName, whereClause, whereArgs);
        } catch (Exception e) {
            Log.i(TAG, " error occurred during delete row by id");
        } finally {
            DatabaseManager.getInstance().close();
        }
    }


    /**
     * NOTE: MUST Close database after geting data from this Cursor
     *
     * @param tableName
     * @param columnNamesToShow
     * @return
     */
    public Cursor selectAll(final String tableName, final String[] columnNamesToShow) throws SQLiteException {
        this.mDatabaseConnection = getDatabaseConnection();
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
    public Cursor selectAllOrderBy(final String tableName, final String[] columnNamesToShow, final String columnNameToOrderBy) throws SQLiteException {
        this.mDatabaseConnection = getDatabaseConnection();
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
    public Cursor selectAllDistinct(final String tableName, final String[] columnNamesToShow, final String columnNameToDistinct) throws SQLiteException {
        this.mDatabaseConnection = getDatabaseConnection();
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
    public Cursor selectRowById(final String tableName, final int id) throws SQLiteException {
        this.mDatabaseConnection = getDatabaseConnection();
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
    public Cursor getDifferences(final String tableName, final String timestamp) throws SQLiteException {
        this.mDatabaseConnection = getDatabaseConnection();
        String[] columnsToShow = null;
        String selection = "TIMESTAMP = '" + timestamp + "'";
        String[] selectionArgs = null;
        String groupBy = null;
        String having = null;
        String orderBy = null;
        return mDatabaseConnection.query(tableName, columnsToShow, selection, selectionArgs, groupBy, having, orderBy);
    }

    public int getRowCount(final String tableName) {
        this.mDatabaseConnection = getDatabaseConnection();
        int numRows = 0;
        try {
            numRows = (int) DatabaseUtils.queryNumEntries(mDatabaseConnection, tableName);
        } catch (Exception e) {
            // TODO: handle exception
        } finally {
            DatabaseManager.getInstance().close();
        }

        return numRows;
    }

    /**
     * @param tableName
     * @param id
     * @return
     */
    private boolean hasData(final String tableName, final int id) {
        this.mDatabaseConnection = getDatabaseConnection();
        boolean hasFound = false;
        final String SELECT_QUERY = String.format(
                "SELECT %s FROM %s WHERE %s=%s",
                DbConfig.COL_ID, tableName,
                DbConfig.COL_ID, String.valueOf(id));


        final Cursor cursor = mDatabaseConnection.rawQuery(SELECT_QUERY, null);
        try {
            if (null != cursor && cursor.getCount() > 0) {
                hasFound = true;
            }
        } catch (Exception e) {
            Log.d(TAG,
                    "Error while trying to check if has existed given id!");
        } finally {
            cursor.close();
            DatabaseManager.getInstance().close();
        }
        return hasFound;
    }


}