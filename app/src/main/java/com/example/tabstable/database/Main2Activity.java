package com.example.tabstable.database;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.content.Intent;
import android.os.Bundle;

import com.example.tabstable.CourseTableViewModel;
import com.example.tabstable.R;
import com.example.tabstable.model.CourseTimeTable;
import com.example.tabstable.ui.main.CourseAdapter;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.Executor;

import static androidx.recyclerview.widget.ItemTouchHelper.*;

public class Main2Activity extends AppCompatActivity implements Executor {


    TableDatabase db;
    public String selectedDay1;
    public CourseAdapter courseAdapter;
    public RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Toolbar toolbar = findViewById(R.id.toolbar3);
        setSupportActionBar(toolbar);


        db = TableDatabase.getInstance(getApplicationContext());

        recyclerView = findViewById(R.id.recycler_input);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(layoutManager);
        courseAdapter = new CourseAdapter(this);
        recyclerView.setAdapter(courseAdapter);

        Intent iet = getIntent();
        if (iet != null && iet.hasExtra("day")) {
            selectedDay1 = iet.getStringExtra("day");
        }




//        CourseTableViewModel viewFactory = new CourseTableViewModel(selectedDay1);
//        if (viewFactory == null) {
//            CourseViewModel model;
//            model = ViewModelProviders.of(this, viewFactory).get(CourseViewModel.class);
//            model.getTableLiveData().observe(this, new Observer<List<CourseTimeTable>>() {
//                @Override
//                public void onChanged(List<CourseTimeTable> courseTimeTables) {
                    LiveData<List<CourseTimeTable>> listLiveData = db.dao().listCourses(selectedDay1);
                     listLiveData.observe(this, new Observer<List<CourseTimeTable>>() {
            @Override
            public void onChanged(List<CourseTimeTable> courseTimeTables) {
                    courseAdapter.notifyView(courseTimeTables);
                }
            });

            new ItemTouchHelper(new SimpleCallback(0, LEFT | RIGHT) {
                @Override
                public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                    return false;
                }

                @Override
                public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                    int adapterPosition = viewHolder.getAdapterPosition();
                    List<CourseTimeTable> gettable = courseAdapter.gettable();
                    final CourseTimeTable courseTimeTable = gettable.get(adapterPosition);

                    Runnable runnable = new Runnable() {
                        @Override
                        public void run() {
                            db.dao().delete(courseTimeTable);
                        }
                    };
                    execute(runnable);

                }
            }).attachToRecyclerView(recyclerView);

            // }
//
        }




    @Override
    public void execute(Runnable command) {
        command.run();
    }
}