package com.droid.bdapp.androidsqlitedb.datasources.offline.database.table;

import com.droid.bdapp.androidsqlitedb.datasources.offline.database.DbConfig;

/**
 * Created by mdruhulamin on 12/14/15.
 */
public class UserTable extends Table {


    @Override
    public String defineTableColumnsToCreate() {
        return getUsersTableCreateStatement();

    }

    @Override
    public String defineTableNameToCreate() {
        return DbConfig.TABLE_USERS;
    }

    @Override
    public String defineTableIdToCreate() {
        return DbConfig.getTableIdAsPrimaryKeyCreateStatement();
    }

    public static String getUsersTableCreateStatement() {
        final String c1 = DbConfig.COL_NAME + DbConfig.TEXT + DbConfig.COMMA;
        final String c2 = DbConfig.COL_EMAIL + DbConfig.TEXT + DbConfig.COMMA;
        final String c3 = DbConfig.COL_PASSWORD + DbConfig.TEXT + DbConfig.COMMA;
        final String c4 = DbConfig.COL_TODO_ID + DbConfig.INTEGER;

        return c1 + c2 + c3 + c4;
    }
}

