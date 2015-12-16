package com.droid.bdapp.androidsqlitedb.datasources.offline.database.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.droid.bdapp.androidsqlitedb.datasources.offline.database.DatabaseManager;
import com.droid.bdapp.androidsqlitedb.datasources.offline.database.DbConfig;
import com.droid.bdapp.androidsqlitedb.models.Todo;

/**
 * Created by mdruhulamin on 12/10/15.
 */
public class TodoDAO extends DAO {

    @Override
    public SQLiteDatabase getDatabaseConnection() {
        this.mDatabaseConnection = DatabaseManager.getInstance().open();
        return mDatabaseConnection;
    }

    public Todo getRow() {
        final TodoDAO todoDao = new TodoDAO();
        Todo todo = new Todo();
        final Cursor result = selectRowById(DbConfig.TABLE_TODO, 1);

        result.moveToFirst();
        todo.setTitle(result.getString(result.getColumnIndex(DbConfig.COL_TITLE)));
        todo.setNote(result.getString(result.getColumnIndex(DbConfig.COL_NOTE)));

        //
        DatabaseManager.getInstance().close();
        return todo;
    }
}
