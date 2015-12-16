package com.droid.bdapp.androidsqlitedb.datasources.offline.database.table;

/**
 * Created by mdruhulamin on 12/10/15.
 */
public abstract class Table {

    public abstract String defineTableColumnsToCreate();

    public abstract String defineTableNameToCreate();

    public abstract String defineTableIdToCreate();
}
