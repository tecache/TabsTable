package com.example.tabstable.database;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.tabstable.model.CourseTimeTable;
import com.example.tabstable.model.TaskEntity;

import java.util.List;

@Dao
public interface TaskDao {


        @Query("SELECT * FROM TaskTable WHERE taskSelected = :taskSelected ORDER BY timeInMilliseconds")
        LiveData<List<TaskEntity>> task(String taskSelected);

        @Insert
        void insert(TaskEntity taskEntity);

        @Update(onConflict = OnConflictStrategy.REPLACE)
        void Update(TaskEntity taskEntity);

        @Delete
        void delete(TaskEntity taskEntity);


    }
