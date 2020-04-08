package com.example.tabstable.database;

import androidx.room.TypeConverter;

import java.util.Date;

public class TableConverter {

    @TypeConverter
    public static Date stringData(Long dateStamp){
        return dateStamp == null ? null : new Date(dateStamp);
    }

    @TypeConverter
    public static Long stringStamp(Date date){
        return date == null ? null :date.getTime();
    }
}
