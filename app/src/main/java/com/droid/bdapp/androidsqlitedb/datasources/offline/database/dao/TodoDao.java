package com.droid.bdapp.androidsqlitedb.datasources.offline.database.dao;

import android.content.Context;

import com.droid.bdapp.androidsqlitedb.datasources.offline.database.DbConfig;
import com.droid.bdapp.androidsqlitedb.datasources.offline.database.table.TodoTable;

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


    //TODO: Write your custom method if needed
}
