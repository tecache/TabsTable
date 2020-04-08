package com.example.tabstable;

import android.os.Bundle;


import com.example.tabstable.database.TableDatabase;
import com.google.android.material.tabs.TabLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;



import com.example.tabstable.ui.main.SectionsPagerAdapter;

public class CourseTable extends AppCompatActivity {


    private TabLayout tabLayouts;
    private ViewPager viewPager;

    private static final String POSITION = "Tab_Position";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_tables);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        viewPager = findViewById(R.id.course_viewpager);
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(CourseTable.this, getSupportFragmentManager());
        viewPager.setAdapter(sectionsPagerAdapter);

        tabLayouts = findViewById(R.id.tabLayout);
        tabLayouts.setupWithViewPager(viewPager);




       // TabLayout tablayout = findViewById(R.id.tabLayout);
       // tablayouts.setupWithViewPager(viewPager);



    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(POSITION, tabLayouts.getSelectedTabPosition());
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        viewPager.setCurrentItem(savedInstanceState.getInt(POSITION));
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}