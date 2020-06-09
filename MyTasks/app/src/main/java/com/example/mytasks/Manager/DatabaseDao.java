package com.example.mytasks.Manager;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.mytasks.Model.task;

import java.util.List;

@Dao
public interface DatabaseDao {

    @Insert
    void insert(task t);

    @Update
    void update(task t);

    @Delete
    void delete(task t);

    @Query("SELECT * FROM notes")
    LiveData<List<task>> getall();
}
