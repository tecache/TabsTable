package com.example.tabstable.ui.main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;

import com.example.tabstable.R;
import com.example.tabstable.database.TableDatabase;
import com.example.tabstable.model.TaskEntity;
import com.google.android.material.snackbar.Snackbar;

public class TaskActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener,
        TimePickerDialog.OnTimeSetListener, DatePickerDialog.OnDateSetListener {

    private Spinner taskSpinner;
    private String taskSelected;

    private TableDatabase taskDatabase;

    EditText taskText;
    EditText taskDate;
    EditText taskTime;
    EditText reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);

        taskDatabase = TableDatabase.getInstance(getApplicationContext());

        taskText = findViewById(R.id.name_text);
        taskDate = findViewById(R.id.date_text);
        taskTime = findViewById(R.id.time_task);
        reference = findViewById(R.id.reference_task);

        taskSpinner = findViewById(R.id.task_spinner);
        taskSpinner.setOnItemSelectedListener(this);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.event_task, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        taskSpinner.setAdapter(adapter);

        taskDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment dateFragment = new TaskDatePicker();
                dateFragment.show(getSupportFragmentManager(),"Task date dialog");
            }
        });

        taskTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    DialogFragment newFragment = new TaskTimeDialog();
                    newFragment.show(getSupportFragmentManager(), "Task time dialog");
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.taskmenu,menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save_task_button:
                saveTask();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }

    }

    private void saveTask() {
        String task_title = taskDate.getText().toString();
        String task_date = taskDate.getText().toString();
        String task_time = taskTime.getText().toString();
        String task_reference = reference.getText().toString();

        TaskEntity taskEntity = new TaskEntity(task_title, taskSelected,  task_date,task_time,task_reference);

        taskDatabase.taskDao().insert(taskEntity);

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Log.i("item Selected", "onItemSelected: position");
        taskSelected = parent.getItemAtPosition(position).toString();
        Snackbar.make(taskSpinner, taskSelected, Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        String timeSet = hourOfDay + ":" + minute;
        taskTime.setText(timeSet);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        String dateSet = dayOfMonth +"." + month + "." +year;
        taskDate.setText(dateSet);
    }
}
