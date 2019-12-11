package com.appface.akhil.screencompanion.model.bean;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "tasks")
public class ToDo {

    @PrimaryKey(autoGenerate = true)
    private long id;

    @ColumnInfo(name = "toDo")
    private String toDo;

    @ColumnInfo(name = "place")
    private String place;

    @Ignore
    public ToDo() {
    }

    public ToDo(long id, String toDo, String place) {
        this.id = id;
        this.toDo = toDo;
        this.place = place;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getToDo() {
        return toDo;
    }

    public void setToDo(String toDo) {
        this.toDo = toDo;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }
}
