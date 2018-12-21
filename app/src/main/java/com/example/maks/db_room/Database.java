package com.example.maks.db_room;

import android.arch.persistence.room.RoomDatabase;

@android.arch.persistence.room.Database(entities = {Task.class}, version = 1)
public abstract class Database extends RoomDatabase {
    public abstract TaskDao taskDao();
}
