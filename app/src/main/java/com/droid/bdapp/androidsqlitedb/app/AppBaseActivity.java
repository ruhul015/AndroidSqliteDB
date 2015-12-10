package com.droid.bdapp.androidsqlitedb.app;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;

import com.droid.bdapp.androidsqlitedb.datasources.online.TaskManager;


/**
 * Created by mdruhulamin on 02/12/15.
 */
public class AppBaseActivity extends AppCompatActivity {
    public static final String TAG = AppBaseActivity.class.getSimpleName();

    protected TaskManager taskManager = AppBaseApplication
            .getGlobalTaskManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void onResume() {
        super.onResume();
        taskManager.initialize(this);
    }



    public void setupToolbarAsActionbar(final Toolbar toolbar) {
        setupToolbarAsActionbar(toolbar, null, true);
    }

    public void setupToolbarAsActionbar(final Toolbar toolbar, final String titleText) {
        setupToolbarAsActionbar(toolbar, titleText, true);
    }

    public void setupToolbarAsActionbar(final Toolbar toolbar, final boolean hasEnableUp) {
        setupToolbarAsActionbar(toolbar, "", hasEnableUp);
    }

    public void setupToolbarAsActionbar(final Toolbar toolbar, final String titleText, final boolean hasEnableUp) {
        if (toolbar == null) {
            new ExceptionInInitializerError("toolbar initialization problem! Initialize Toolbar before used.");
        }
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(hasEnableUp);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        if (!TextUtils.isEmpty(titleText)) {
            getSupportActionBar().setTitle(titleText);
        }

    }

}
