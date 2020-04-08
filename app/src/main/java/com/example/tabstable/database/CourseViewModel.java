package com.example.tabstable.database;


import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.tabstable.model.CourseTimeTable;

import java.util.List;

public class CourseViewModel extends AndroidViewModel {

    private static Object Application;
    private LiveData<List<CourseTimeTable>> tableLiveData;


    public CourseViewModel( String day) {
        super((android.app.Application) Application);

        // if(database != null &&
        TableDatabase database = TableDatabase.getInstance(((Application) Application).getApplicationContext());
        if(!day.equals(""))
        tableLiveData = database.dao().listCourses(day);
    }

    public LiveData<List<CourseTimeTable>> getTableLiveData(){
        return tableLiveData;
    }
}
