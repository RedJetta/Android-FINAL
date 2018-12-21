package com.example.maks.db_room;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface TaskDao {

    @Query("SELECT * FROM task")
    public List<Task> getTasks();

    @Query("SELECT COUNT(*) FROM task")
    public int getTasksCount();

    @Query("SELECT * FROM task WHERE name = :name")
    public List<Task> getTaskByName(String name);

    @Insert
    public void createTask(Task... tasks);

    @Query("DELETE FROM task")
    public void deleteTasks();
}
