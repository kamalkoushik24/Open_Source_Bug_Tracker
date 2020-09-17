package com.example.itcbugtracker;


import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Log.class}, version = 1)
public abstract class LogDatabase extends RoomDatabase {
    public abstract LogDao logDao();
}
