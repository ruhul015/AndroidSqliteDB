package com.droid.bdapp.androidsqlitedb.ui.activities;

import android.database.Cursor;
import android.os.Bundle;

import com.droid.bdapp.androidsqlitedb.R;
import com.droid.bdapp.androidsqlitedb.app.AppBaseActivity;
import com.droid.bdapp.androidsqlitedb.datasources.offline.database.DbConfig;
import com.droid.bdapp.androidsqlitedb.datasources.offline.database.dao.TodoDAO;
import com.droid.bdapp.androidsqlitedb.models.Todo;

public class MainActivity extends AppBaseActivity {
    public static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TodoDAO todoDao = new TodoDAO(this);
        //add row
        Todo todo = new Todo("TodoTitle", "Todo note");
        long rowId=todoDao.insert(todo);

        final Todo getATodo = getRow();
        final int id = getATodo.getId();
        final String title = getATodo.getTitle();
        final String note=getATodo.getNote();

        System.out.print("------------ " + id + " - " + title + " - " + note);

    }

    public Todo getRow() {
        TodoDAO todoDao = new TodoDAO(this);
        Todo todo = new Todo();
        final Cursor result = todoDao.selectAll(DbConfig.TABLE_TODO, null);
        result.moveToFirst();
        //todo.setId(result.getInt(result.getColumnIndex(DbConfig.COL_ID)));
        todo.setTitle(result.getString(result.getColumnIndex(DbConfig.COL_TITLE)));
        todo.setNote(result.getString(result.getColumnIndex(DbConfig.COL_NOTE)));

        return todo;
    }
}
