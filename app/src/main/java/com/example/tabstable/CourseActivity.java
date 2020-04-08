package com.example.tabstable;

import androidx.annotation.NonNull;


import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;

import com.example.tabstable.coursefragments.MondayFragment;
import com.example.tabstable.ui.main.TimeInPickerFragment;
import com.example.tabstable.ui.main.TimePickerViewModel;
import com.example.tabstable.database.TableDatabase;
import com.example.tabstable.model.CourseTimeTable;
import com.example.tabstable.ui.main.CourseAdapter;
import com.example.tabstable.ui.main.TimeOutDialogFragment;
import com.google.android.material.snackbar.Snackbar;

import java.util.Calendar;
import java.util.concurrent.Executor;

public class CourseActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener,Executor,
        TimePickerDialog.OnTimeSetListener{

    public static final String INSERTED_DAY = "Inserted Day";
    TimePicker timePicker;
    private Spinner courseSpinner;
    private Spinner daySpinnner;
    private EditText courseTitle;
    private EditText courseText;
    private EditText lecturer;
    private EditText location;
    private EditText timeIn;
    private EditText timeOut;
    private TableDatabase db;
    private CourseAdapter adapter;
    private TimeInPickerFragment pickerFragment;

    public static String selected;
    public Executor executor;
    public Runnable runnable;
    public Dialog mTimeDialog;
    public TimePickerViewModel timePickerViewModel;
    public Calendar calender;
    private int hourOfDay;
    private int minute;
    public long millseconds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        calender = Calendar.getInstance();


        db = TableDatabase.getInstance(getApplicationContext());


        courseTitle = findViewById(R.id.title_text);
        courseText = findViewById(R.id.name_text);
        lecturer = findViewById(R.id.lecturer_text);
        location = findViewById(R.id.location_text);
        timeIn = findViewById(R.id.time_in);
        timeOut = findViewById(R.id.time_out);

        mTimeDialog = new Dialog(this);

        courseSpinner = findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.subject_title, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        courseSpinner.setAdapter(adapter);

        timeOutValue();

        daySpinnner = findViewById(R.id.day_spinner);
        daySpinnner.setOnItemSelectedListener(this);
        ArrayAdapter<CharSequence> days_adapter = ArrayAdapter.createFromResource(this, R.array.days_of_week, android.R.layout.simple_spinner_item);
        days_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        daySpinnner.setAdapter(days_adapter);


        timeIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment timefragment = new TimeInPickerFragment();
                if (timefragment != null) {
                    timefragment.show(getSupportFragmentManager(), "Time_In Picker");
                }
            }
        });

        timeOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment newFragment = TimeOutDialogFragment.newInstance();
                newFragment.show(getSupportFragmentManager(), "Time_Out dialog");
            }
        });

    }

    public void timeOutValue() {
        timePickerViewModel = new ViewModelProvider(this).get(TimePickerViewModel.class);
        timePickerViewModel.getSelected().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                timeOut.setText(s);
            }
        });
    }


//                    Calendar calendar = Calendar.getInstance();
//                    int mHour = calendar.get(Calendar.HOUR);
//                    int mMinute = calendar.get(Calendar.MINUTE);
//
//                    TimePickerDialog timePickerDialog = new TimePickerDialog(getApplicationContext(),
//                            new TimePickerDialog.OnTimeSetListener() {
//
//                                @Override
//                                public void onTimeSet(TimePicker view, int hourOfDay,
//                                                      int minute) {
//                                    String timePicked = hourOfDay + ":" + minute;
//
//                                    timeOut.setText(timePicked);
//                                }
//                            }, mHour, mMinute, true);
//                    timePickerDialog.show();
//                }
//
//            });


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.coursemenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save_button:
                saveTable();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }

    }


    private void saveTable() {
        String mTitle = courseTitle.getText().toString();
        String mText = courseText.getText().toString();
        String mLecturer = lecturer.getText().toString();
        String mLocation = location.getText().toString();
        String mCourseTime = timeIn.getText().toString() + "-" + timeOut.getText().toString();
        long mMillis = millseconds;
        String mDay = selected;

        CourseTimeTable courseInfo = new CourseTimeTable(mText, mTitle, mLecturer, mLocation, mCourseTime, mDay,mMillis);
        db.dao().insert(courseInfo);
        fragmentList(mDay);
        Intent intent = new Intent(this, CourseTable.class);
        //intent.putExtra("day", mDay);

        startActivity(intent);


//        Runnable runnable = new Runnable() {
//            @Override
//            public void run() {
//
//                db.dao().insert(courseInfo);
//                Log.i("Database", "saveTable: table savec");
//            }
//        };
//
//         execute(runnable);

    }


    private void fragmentList(String mDay) {

        switch (mDay) {
            case "Monday":
//                MondayFragment fragment = new MondayFragment();
//                Bundle bundle = new Bundle();
//                bundle.putString(INSERTED_DAY, mDay);
//                fragment.setArguments(bundle);
//                break;
                MondayFragment.newInstance("Monday");

//
//            case "Tuesday":
//                TuesdayFragment tuesdayFragment = TuesdayFragment.newInstance("Tuesday");
//                return ;
//
//            default:
//
//        }
//
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Log.i("item Selected", "onItemSelected: position");
        selected = parent.getItemAtPosition(position).toString();
        Snackbar.make(courseSpinner, selected, Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void execute(Runnable command) {
        command.run();
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        millseconds = timeInMilliseconds(hourOfDay, minute);
        String timeSet = hourOfDay + ":" + minute;
        timeIn.setText(timeSet);

    }

    private long timeInMilliseconds(int hour, int mins) {
        calender.set(Calendar.HOUR_OF_DAY,hour);
        calender.set(Calendar.MINUTE, mins);
        calender.set(Calendar.SECOND, 0);
       return calender.getTimeInMillis();
    }


}
