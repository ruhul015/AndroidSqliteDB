package com.droid.bdapp.androidsqlitedb.ui.activities;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;

import com.droid.bdapp.androidsqlitedb.R;
import com.droid.bdapp.androidsqlitedb.app.AppBaseActivity;
import com.droid.bdapp.androidsqlitedb.datasources.offline.database.DatabaseManager;
import com.droid.bdapp.androidsqlitedb.datasources.offline.database.DbConfig;
import com.droid.bdapp.androidsqlitedb.datasources.offline.database.dao.TodoDAO;
import com.droid.bdapp.androidsqlitedb.models.Todo;

public class MainActivity extends AppBaseActivity {
    public static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addRow();

        //
        final Todo getATodo = getRow();
        Log.d(TAG, getATodo.getId() + " - " + getATodo.getTitle() + " - " + getATodo.getNote());

    }

    private void addRow() {
        final TodoDAO todoDao = new TodoDAO(this);

        final ContentValues addNewTodoRow = new ContentValues();
        addNewTodoRow.put(DbConfig.COL_TITLE, "TodoTitle");
        addNewTodoRow.put(DbConfig.COL_NOTE, "Todo note");

        final long rowId = todoDao.insert(DbConfig.TABLE_TODO, addNewTodoRow);
    }

    public Todo getRow() {
        final TodoDAO todoDao = new TodoDAO(this);
        Todo todo = new Todo();
        final Cursor result = todoDao.selectRowById(DbConfig.TABLE_TODO, 1);

        result.moveToFirst();
        todo.setTitle(result.getString(result.getColumnIndex(DbConfig.COL_TITLE)));
        todo.setNote(result.getString(result.getColumnIndex(DbConfig.COL_NOTE)));

        //
        DatabaseManager.getInstance().closeDatabase();
        return todo;
    }
}
