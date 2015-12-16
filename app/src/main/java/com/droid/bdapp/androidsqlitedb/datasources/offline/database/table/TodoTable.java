package com.droid.bdapp.androidsqlitedb.datasources.offline.database.table;

import com.droid.bdapp.androidsqlitedb.datasources.offline.database.DbConfig;

/**
 * Created by mdruhulamin on 12/10/15.
 */
public class TodoTable extends Table {

    @Override
    public String defineTableColumnsToCreate() {
        return getTodoTableCreateStatement();

    }

    @Override
    public String defineTableNameToCreate() {
        return DbConfig.TABLE_TODO;
    }

    @Override
    public String defineTableIdToCreate() {
        return DbConfig.getTableIdAsPrimaryKeyCreateStatement();
    }

    public static String getTodoTableCreateStatement() {
        final String c1 = DbConfig.COL_TITLE + DbConfig.TEXT + DbConfig.COMMA;
        final String c2 = DbConfig.COL_NOTE + DbConfig.TEXT;

        return c1 + c2;
    }


}
