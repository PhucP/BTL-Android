package com.example.btl_android.RoomDatabase.Dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.btl_android.RoomDatabase.Entity.Task;
import com.example.btl_android.RoomDatabase.Entity.User;

@Dao
public interface TaskDao {
    @Insert
    void insertUser(Task task);
}

