package com.example.itcbugtracker;

import androidx.room.Query;

import java.util.List;

public interface LogDao {
    @Query("INSERT INTO Logs  (`desc`)VALUES ('New Log')")
    void create();

    @Query("SELECT * FROM Logs")
    List<Log> getAllLogs();

    @Query("UPDATE Logs SET `desc` = :desc, `category1` = :category2, `category2` = :category2, `pDetails` = :pDetails")
    void save(String desc, String category1, String category2, String pDetails, int id);

    @Query("DELETE FROM Logs WHERE id = :id")
    void delete(int id);
}
