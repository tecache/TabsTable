package com.example.tabstable;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.tabstable.database.CourseViewModel;
import com.example.tabstable.database.TableDatabase;

public class CourseTableViewModel implements ViewModelProvider.Factory {


    private final String day;
   // private final TableDatabase database;


    public CourseTableViewModel(String day){
       // this.database = database;
        this.day = day;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if(modelClass == null)
            throw new IllegalArgumentException("this is a shitty class");
        //modelClass.isAssignableFrom(CourseViewModel.class);
        return (T) new CourseViewModel(day);
    }

}


