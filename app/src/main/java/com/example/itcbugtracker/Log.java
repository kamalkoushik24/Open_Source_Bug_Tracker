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
    public int category1;

    @ColumnInfo(name = "category2")
    public int category2;

    @ColumnInfo(name = "pDetails")
    public String pDetails;

    @ColumnInfo(name = "archived")
    public boolean archived;


    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getCategory1() {
        return category1;
    }

    public void setCategory1(int category1) {
        this.category1 = category1;
    }

    public int getCategory2() {
        return category2;
    }

    public void setCategory2(int category2) {
        this.category2 = category2;
    }

    public String getpDetails() {
        return pDetails;
    }

    public void setpDetails(String pDetails) {
        this.pDetails = pDetails;
    }

    public boolean getArchived() {
        return archived;
    }
    public void setArchived(boolean archived){
        this.archived = archived;
    }

}
