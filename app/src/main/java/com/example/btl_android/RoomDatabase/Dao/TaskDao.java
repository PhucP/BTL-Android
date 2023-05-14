package com.example.btl_android.RoomDatabase.Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.btl_android.RoomDatabase.Entity.Task;
import com.example.btl_android.RoomDatabase.Entity.User;

import java.util.List;

@Dao
public interface TaskDao {
    @Insert
    void insertTask(Task task);

    @Delete
    void deleteTask(Task task);

    @Update
    void updateTask(Task task);

    @Query("SELECT * FROM tasks WHERE user_id = :user_id")
    List<Task> getAllTask(int user_id);

    @Query("SELECT * FROM tasks WHERE user_id = :user_id AND stats = 'uncompleted'")
    List<Task> getUncompletedTask(int user_id);

    @Query("SELECT * FROM tasks WHERE user_id = :user_id AND stats = 'completed'")
    List<Task> getCompletedTask(int user_id);

    @Query("SELECT * FROM tasks WHERE user_id = :user_id AND stats = 'next_task'")
    List<Task> getNextTask(int user_id);
}

