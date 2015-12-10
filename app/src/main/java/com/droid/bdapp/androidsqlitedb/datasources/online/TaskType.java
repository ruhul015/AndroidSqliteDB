package com.droid.bdapp.androidsqlitedb.datasources.online;

public enum TaskType {
    USER_LOADER;


    public TaskBase createIns() {
        switch (this) {
            case USER_LOADER:
                return new UserLoaderTask();
        }
        return null;
    }
}
