package com.example.btl_android.RoomDatabase.Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.btl_android.RoomDatabase.Entity.User;

import java.util.List;

@Dao
public interface UserDao {
    @Insert
    void insertUser(User user);

    @Query("SELECT * FROM users WHERE user_name = :username AND pass_word = :password LIMIT 1")
    User getUserByUsernameAndPassword(String username, String password);

    @Query("SELECT * FROM users WHERE user_name = :username AND pass_word = :password")
    List<User> getValidUser(String username, String password);

    @Delete
    void deleteUser(User user);

    @Update
    void updateUser(User user);
}

