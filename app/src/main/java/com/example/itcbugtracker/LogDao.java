package com.example.itcbugtracker;

import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface LogDao {
    @Query("INSERT INTO Logs  (`desc`, category1, category2, pDetails)VALUES ('New Log', 0, 0, 'Details')")
    void create();

    @Query("SELECT * FROM Logs")
    List<Log> getAllLogs();

    @Update
    void save(Log log);

    @Query("DELETE FROM Logs WHERE id = :id")
    void delete(int id);

}
