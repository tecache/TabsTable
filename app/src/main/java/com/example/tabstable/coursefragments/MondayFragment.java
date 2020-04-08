package com.example.tabstable.coursefragments;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tabstable.CourseActivity;

import com.example.tabstable.database.TableDatabase;
import com.example.tabstable.model.CourseTimeTable;
import com.example.tabstable.ui.main.CourseAdapter;
import com.example.tabstable.R;

import java.util.List;
import java.util.Objects;

import static android.os.AsyncTask.execute;
import static androidx.recyclerview.widget.ItemTouchHelper.LEFT;
import static androidx.recyclerview.widget.ItemTouchHelper.RIGHT;


public class MondayFragment extends Fragment {

    private TableDatabase db;
    private CourseAdapter courseAdapter;
    private String selectedDay;

    public MondayFragment() {

    }

//
    public static MondayFragment newInstance(String param1) {
        MondayFragment fragment = new MondayFragment();
        Bundle args = new Bundle();
        args.putString(CourseActivity.INSERTED_DAY, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = TableDatabase.getInstance(getContext());

        if (getArguments() != null) {
            selectedDay = getArguments().getString(CourseActivity.INSERTED_DAY);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View rootView =  inflater.inflate(R.layout.fragment_monday, container, false);
        RecyclerView recyclerView = rootView.findViewById(R.id.recycler_course);
        courseAdapter = new CourseAdapter(Objects.requireNonNull(getActivity()).getBaseContext());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity().getBaseContext());

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(courseAdapter);

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, LEFT | RIGHT) {
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

        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        LiveData<List<CourseTimeTable>> listLiveDay = db.dao().listCourses("Monday");
            listLiveDay.observe(getViewLifecycleOwner(), new Observer<List<CourseTimeTable>>() {
                @Override
                public void onChanged(List<CourseTimeTable> courseTimeTables) {
                    courseAdapter.notifyView(courseTimeTables);
                }
            });

        }

        }




