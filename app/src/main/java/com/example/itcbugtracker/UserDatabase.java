package com.example.itcbugtracker;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {User.class}, version = 1)
public abstract class UserDatabase extends RoomDatabase {
    public static final String dbName = "user.db";
    public static UserDatabase userDatabase;
    public static synchronized UserDatabase getUserDatabase(Context context){
        if(userDatabase == null){
            userDatabase = Room.databaseBuilder(context,UserDatabase.class,dbName).fallbackToDestructiveMigration().allowMainThreadQueries().build();

        }
        return userDatabase;

    }

    public abstract UserDao userDao();


}
