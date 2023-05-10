package com.example.btl_android.RoomDatabase.Dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.btl_android.RoomDatabase.Entity.User;

@Dao
public interface UserDao {
    @Insert
    void insertUser(User user);

    @Query("SELECT * FROM users WHERE user_name = :username AND pass_word = :password")
    User getUserByUsernameAndPassword(String username, String password);
}

