package com.example.btl.Entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "tasks")
public class Task {
    @PrimaryKey
    public int id;

    public String title;

    @ColumnInfo(name = "user_id")
    public int userId;
}
