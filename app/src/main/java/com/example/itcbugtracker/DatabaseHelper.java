package com.example.itcbugtracker;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "users.db";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME,  null, 1);

    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE users (id int PRIMARY KEY, username TEXT, password TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE if EXISTS users");

    }
    public Boolean insertData(String username, String password, int id){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("id", id);
        contentValues.put("username", username);
        contentValues.put("password", password);
        long result = db.insert("users", null, contentValues);
        if (result == -1)
        {
            return false;
        }
        return true;
    }
    public Boolean checkUsername(String username){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM users WHERE username = ?", new String[] {username});
        if (cursor.getCount() > 0){
            return false;
        }
        return true;
    }
    public Boolean checkPass(String username ,String password){
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM users WHERE username = ? and password = ?",new String[] {username, password} );
        if (cursor.getCount() > 0){
            return true;
        }
        return false;

    }
}
