package com.example.tabstable.model;


import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "CourseTable")
public class CourseTimeTable {

    @PrimaryKey(autoGenerate = true)
    private int courseId;
    private String courseTimetable;
    private String courseName;
    private String courseTitle;
    private String lecturer;
    private String location;
    private String courseTime;
    private long selectInMilliseconds;
    private String day;


    public CourseTimeTable(int courseId, String courseTimetable,String courseTitle, String courseName, String lecturer, String location, String courseTime, String day, long selectInMilliseconds){
        this.courseId = courseId;
        this.courseTimetable = courseTimetable;
        this.courseName = courseName;
        this.courseTitle = courseTitle;
        this.lecturer = lecturer;
        this.location = location;
        this.courseTime = courseTime;
        this.day = day;
        this.selectInMilliseconds = selectInMilliseconds;
    }
    @Ignore
    public CourseTimeTable(String courseTitle,String courseName, String lecturer, String location,String courseTime, String day, long selectInMilliseconds){

        this.courseName = courseName;
        this.courseTitle = courseTitle;
        this.lecturer = lecturer;
        this.location = location;
        this.courseTime = courseTime;
        this.day = day;
       this.selectInMilliseconds = selectInMilliseconds;
    }

    public void setSelectInMilliseconds(long selectInMilliseconds) {
        this.selectInMilliseconds = selectInMilliseconds;
    }

    public long getSelectInMilliseconds() {
        return selectInMilliseconds;
    }

    public String getCourseTimetable() {
        return courseTimetable;
    }

    public void setCourseTimetable(String courseTimetable) {
        this.courseTimetable = courseTimetable;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public int getCourseId(){
        return courseId;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public void setLecturer(String lecturer) {
        this.lecturer = lecturer;
    }

    public String getLecturer() {
        return lecturer;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLocation() {
        return location;
    }

    public void setCourseTime(String courseTime) {
        this.courseTime = courseTime;
    }

    public String getCourseTime() {
        return courseTime;
    }

}
