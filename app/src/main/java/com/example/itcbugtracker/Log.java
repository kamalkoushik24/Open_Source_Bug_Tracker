package com.example.itcbugtracker;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Logs")
public class Log {
    @PrimaryKey
    public int id;

    @ColumnInfo(name = "desc")
    public String desc;

    @ColumnInfo(name = "category1")
    public String category1;

    @ColumnInfo(name = "category2")
    public String category2;

    @ColumnInfo(name = "pDetails")
    public String pDetails;
}
