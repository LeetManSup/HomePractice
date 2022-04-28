package com.mirea.lutsko.mireaproject.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.mirea.lutsko.mireaproject.ui.stories.Story;
import com.mirea.lutsko.mireaproject.ui.stories.StoryDAO;

@Database(entities = {Story.class}, version = 1)
public abstract class StoriesDatabase extends RoomDatabase {

    public abstract StoryDAO storyDAO();
}
