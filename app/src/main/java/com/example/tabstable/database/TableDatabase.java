package com.example.tabstable.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;

import com.example.tabstable.model.CourseTimeTable;
import com.example.tabstable.model.TaskEntity;


@TypeConverters(TableConverter.class)
@Database(entities = {CourseTimeTable.class, TaskEntity.class},version = 2,exportSchema = false)
public abstract class TableDatabase extends RoomDatabase {

    public abstract TableDao dao();
    public abstract TaskDao taskDao();
    private static final String DATABASE_NAME = "Table database";
    private static TableDatabase instance;


    public static TableDatabase getInstance(Context context) {
        if (instance == null) {
            synchronized (TableDatabase.class) {
                instance = Room.databaseBuilder(context.getApplicationContext(),
                        TableDatabase.class, TableDatabase.DATABASE_NAME)
                        .allowMainThreadQueries()
                        .build();

            }
        }
        return instance;
    }


}

