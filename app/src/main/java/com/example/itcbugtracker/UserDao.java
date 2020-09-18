package com.example.itcbugtracker;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface UserDao {

    @Insert
    void register(User user);

    @Query("SELECT * FROM users WHERE username= :username and password = :password")
    User login(String username, String password);

    @Query("SELECT * FROM users where username = :username")
    User checkuser(String username);


}
