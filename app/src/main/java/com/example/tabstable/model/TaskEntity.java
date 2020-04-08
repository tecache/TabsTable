package com.example.tabstable.model;


import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "TaskTable")
public class TaskEntity {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String taskSelected;
    private String taskName;
    private String datePicked;
    private String timePicked;
    private String reference;
    private int timeInMilliseconds;

    public TaskEntity(int id, String taskSelected, String taskName, String datePicked, String timePicked, String reference, int timeInMilliseconds) {
        this.id = id;
        this.taskSelected = taskSelected;
        this.taskName = taskName;
        this.datePicked = datePicked;
        this.timePicked = timePicked;
        this.reference = reference;
        this.timeInMilliseconds = timeInMilliseconds;
    }

    @Ignore
    public TaskEntity(String taskName, String taskSelected, String datePicked, String timePicked, String reference) {
        this.id = id;
        this.taskSelected = taskSelected;
        this.taskName = taskName;
        this.datePicked = datePicked;
        this.timePicked = timePicked;
        this.reference = reference;
        this.timeInMilliseconds = timeInMilliseconds;
    }


    public String getTaskSelected() {
        return taskSelected;
    }

    public void setTaskSelected(String taskSelected) {
        this.taskSelected = taskSelected;
    }

    public int getTimeInMilliseconds() {
        return timeInMilliseconds;
    }

    public void setTimeInMilliseconds(int timeInMilliseconds) {
        this.timeInMilliseconds = timeInMilliseconds;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getDatePicked() {
        return datePicked;
    }

    public void setDatePicked(String datePicked) {
        this.datePicked = datePicked;
    }

    public String getTimePicked() {
        return timePicked;
    }

    public void setTimePicked(String timePicked) {
        this.timePicked = timePicked;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }


}
