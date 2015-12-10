package com.droid.bdapp.androidsqlitedb.datasources.offline.database.dao;

import android.content.ContentValues;
import android.content.Context;

import com.droid.bdapp.androidsqlitedb.datasources.offline.database.DbConfig;
import com.droid.bdapp.androidsqlitedb.datasources.offline.database.table.TodoTable;
import com.droid.bdapp.androidsqlitedb.models.Todo;

/**
 * Created by mdruhulamin on 12/10/15.
 */
public class TodoDAO extends DAO {

    public TodoDAO(Context context) {
        super(context, new TodoTable(context));
    }

    @Override
    public String defineDatabaseNameToCreate() {
        return DbConfig.DATABASE_TEST;
    }

    public long insert(final Todo todo) {
        final ContentValues addNewTodoRow = new ContentValues();
        addNewTodoRow.put(DbConfig.COL_TITLE, "TodoTitle");
        addNewTodoRow.put(DbConfig.COL_NOTE, "Todo note");
        return insert(DbConfig.TABLE_TODO, addNewTodoRow);
    }
}
