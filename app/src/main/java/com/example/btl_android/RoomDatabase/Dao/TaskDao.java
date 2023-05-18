package com.example.btl_android.RoomDatabase.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.btl_android.RoomDatabase.Entity.Task;

import java.util.List;

@Dao
public interface TaskDao {
    @Insert
    long insertTask(Task task);

    @Delete
    void deleteTask(Task task);

    @Update
    void updateTask(Task task);

    @Query("SELECT * FROM tasks WHERE id = :id LIMIT 1")
    Task findById(int id);

    @Query("SELECT * FROM tasks WHERE user_id = :user_id")
    LiveData<List<Task>> getAllTask(int user_id);

    @Query("SELECT * FROM tasks WHERE user_id = :user_id AND stats = 'uncompleted'")
    LiveData<List<Task>> getUncompletedTask(int user_id);

    @Query("SELECT * FROM tasks WHERE user_id = :user_id AND stats = 'completed'")
    LiveData<List<Task>> getCompletedTask(int user_id);

    @Query("SELECT * FROM tasks WHERE user_id = :user_id AND stats = 'next_task'")
    LiveData<List<Task>> getNextTask(int user_id);

    @Query("SELECT * FROM tasks WHERE user_id = :user_id AND time = :time AND title = :title LIMIT 1")
    Task getTaskInTime(int user_id, String time, String title);

    @Query("SELECT * FROM tasks WHERE user_id = :user_id AND time LIKE :time AND stats = 'next_task'")
    LiveData<List<Task>> getNextTaskInDay(int user_id, String time);

    @Query("SELECT * FROM tasks WHERE user_id = :user_id AND time LIKE :time AND stats = 'completed'")
    LiveData<List<Task>> getCompletedTaskInDay(int user_id, String time);

    @Query("SELECT * FROM tasks WHERE user_id = :user_id AND time LIKE :time AND stats = 'uncompleted'")
    LiveData<List<Task>> getUnCompletedTaskInDay(int user_id, String time);
}

