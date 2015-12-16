package com.droid.bdapp.androidsqlitedb.datasources.offline.database;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.droid.bdapp.androidsqlitedb.app.AppBaseApplication;
import com.droid.bdapp.androidsqlitedb.datasources.offline.database.table.Table;
import com.droid.bdapp.androidsqlitedb.datasources.offline.database.table.TodoTable;
import com.droid.bdapp.androidsqlitedb.datasources.offline.database.table.UserTable;


/**
 * Created by mdruhulamin on 12/15/15.
 */
class DBHelper extends SQLiteOpenHelper {
    public static final String TAG = DBHelper.class.getSimpleName();

    private static DBHelper sInstance;

    protected static DBHelper getInstance() {

        if (sInstance == null) {
            sInstance = new DBHelper();
        }
        return sInstance;
    }

    private DBHelper() {
        super(AppBaseApplication.getContext(), DbConfig.DATABASE_TEST, DbConfig.dbDefaultCursorFactory,
                DbConfig.dbDefaultVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //
        createTable(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //
        upgradeTable(db, oldVersion, newVersion);
    }

    //
    private void createTable(final SQLiteDatabase db) {
        db.execSQL(getTableCreateQuery(new UserTable()));
        db.execSQL(getTableCreateQuery(new TodoTable()));
    }

    private String getTableCreateQuery(final Table table) {
        final String sqlCmdStart = "CREATE TABLE IF NOT EXISTS ";
        final String sqlCmdEnd = ");";
        final String createQuery = sqlCmdStart + table.defineTableNameToCreate() + table.defineTableIdToCreate() + table.defineTableColumnsToCreate() + sqlCmdEnd;

        return createQuery;
    }

    //
    private void upgradeTable(SQLiteDatabase db, int oldVersion, int newVersion) {
        //TODO: nothing to do for this version
    }


}
