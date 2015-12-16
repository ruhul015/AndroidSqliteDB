package com.droid.bdapp.androidsqlitedb.ui.activities;

import android.content.ContentValues;
import android.os.Bundle;
import android.util.Log;

import com.droid.bdapp.androidsqlitedb.R;
import com.droid.bdapp.androidsqlitedb.app.AppBaseActivity;
import com.droid.bdapp.androidsqlitedb.datasources.offline.database.DbConfig;
import com.droid.bdapp.androidsqlitedb.datasources.offline.database.dao.TodoDAO;
import com.droid.bdapp.androidsqlitedb.datasources.offline.database.dao.UserDao;
import com.droid.bdapp.androidsqlitedb.models.Todo;

import java.util.Date;

public class MainActivity extends AppBaseActivity {
    public static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addRow();

        addNewUser();


        //
        final TodoDAO todoDao = new TodoDAO();
        final Todo getATodo = todoDao.getRow();
        Log.d(TAG, getATodo.getId() + " - " + getATodo.getTitle() + " - " + getATodo.getNote());

    }

    private void addNewUser() {
        final UserDao userDao = new UserDao();

        final ContentValues addNewUserRow = new ContentValues();
        addNewUserRow.put(DbConfig.COL_NAME, "abc" + new Date().toString());
        addNewUserRow.put(DbConfig.COL_EMAIL, "abc@test.com");
        addNewUserRow.put(DbConfig.COL_PASSWORD, "123456");
        addNewUserRow.put(DbConfig.COL_TODO_ID, 111);

        final long rowId = userDao.insert(DbConfig.TABLE_USERS, addNewUserRow);
    }

    private void addRow() {
        final TodoDAO todoDao = new TodoDAO();
        final ContentValues addNewTodoRow = new ContentValues();
        addNewTodoRow.put(DbConfig.COL_TITLE, "TodoTitle");
        addNewTodoRow.put(DbConfig.COL_NOTE, "Todo note");

        final long rowId = todoDao.insert(DbConfig.TABLE_TODO, addNewTodoRow);
    }


}
