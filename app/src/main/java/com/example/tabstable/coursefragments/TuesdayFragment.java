package com.example.tabstable.coursefragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tabstable.database.TableDatabase;
import com.example.tabstable.ui.main.CourseAdapter;
import com.example.tabstable.R;


public class TuesdayFragment extends Fragment {

    private static final String ARG_PARAM1 = "DAY_SELECTED" ;
    private Context context;
    public String selectedDay;

    public TuesdayFragment(Context context){
        this.context = context;
    }

    public TuesdayFragment() {
    }


    public static TuesdayFragment newInstance(String param1) {
        TuesdayFragment fragment = new TuesdayFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            selectedDay = getArguments().getString(ARG_PARAM1);
        }
    }
//    public TuesdayFragment(Context context, TableDatabase db){
//        this.context = context;
//        this.db = db;
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_monday, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        RecyclerView recyclerView = view.findViewById(R.id.recycler_course);
        CourseAdapter courseAdapter = new CourseAdapter(context);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(courseAdapter);
    }
}
