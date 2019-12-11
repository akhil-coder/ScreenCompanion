package com.appface.akhil.screencompanion.model;



import com.appface.akhil.screencompanion.model.bean.ToDo;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface ToDoDao {

    @Insert
    public long addTask(ToDo toDo);

    @Update
    public void updateTask(ToDo toDo);

    @Delete
    public void deleteContact(ToDo toDo);

    @Query("Select * from tasks")
    public List<ToDo> getAllTasks();

    @Query("Select * from tasks where toDo ==:taskId")
    public List<ToDo> getATask(long taskId);
}
