package com.droid.bdapp.androidsqlitedb.datasources.offline.database.table;

import android.content.Context;

import com.droid.bdapp.androidsqlitedb.datasources.offline.database.DbConfig;

/**
 * Created by mdruhulamin on 12/10/15.
 */
public class TodoTable extends Table {

    public TodoTable(Context appContext) {
        super(appContext);
    }

    @Override
    public String defineTableColumnsToCreate() {
        return DbConfig.getTodoTableCreateStatement();

    }

    @Override
    public String defineTableNameToCreate() {
        return DbConfig.TABLE_TODO;
    }
}
