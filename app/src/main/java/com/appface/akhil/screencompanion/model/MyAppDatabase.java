package com.appface.akhil.screencompanion.model;


import com.appface.akhil.screencompanion.model.bean.ToDo;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {ToDo.class}, version = 1)
public abstract class MyAppDatabase extends RoomDatabase {

    public abstract ToDoDao getToDoDao();
}
