package com.example.tabstable.database;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.tabstable.model.CourseTimeTable;

import java.util.List;

@Dao
public interface TableDao {

    @Query("SELECT * FROM CourseTable WHERE day = :day ORDER BY selectInMilliseconds")
    LiveData<List<CourseTimeTable>> listCourses(String day);

    @Insert
    void insert(CourseTimeTable courseTimeTable);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void Update(CourseTimeTable courseTimeTable);

    @Delete
    void delete(CourseTimeTable courseTimeTable);


}
