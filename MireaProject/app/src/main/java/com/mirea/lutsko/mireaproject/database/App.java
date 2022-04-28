package com.mirea.lutsko.mireaproject.database;

import androidx.room.Room;

public class App extends android.app.Application {
    public static App instance;
    private StoriesDatabase database;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        database = Room.databaseBuilder(this, StoriesDatabase.class, "database")
                .allowMainThreadQueries()
                .build();
    }
    public static App getInstance() {
        return instance;
    }
    public StoriesDatabase getDatabase() {
        return database;
    }
}
