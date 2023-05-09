package com.example.btl.Entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.Relation;

import java.util.List;

@Entity(tableName = "users")
public class User {
    @PrimaryKey
    public int id;

    @ColumnInfo(name = "user_name")
    public String userName;
    @ColumnInfo(name = "pass_word")
    public String passWord;

    public String name;
    @ColumnInfo(name = "phone_number")
    public String phoneNumber;
    public String email;

    @Relation(parentColumn = "id", entityColumn = "user_id")
    public List<Task> tasks;
}

